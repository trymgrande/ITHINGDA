from pprint import pprint
from bson import SON, Int64
from DbConnector import DbConnector
from haversine import haversine
import csv
import os
from tqdm import tqdm
import datetime

class DatabaseProgram:

    def __init__(self):
        self.connection = DbConnector()
        self.client = self.connection.client
        self.db = self.connection.db


    def load_dataset(self):    
        """Loads dataset and returns document collections"""
        users_table = []
        activities_unlabeled_table = []
        activities_labeled_table = []
        trackpoints_table = []

        activity_id = 1
        trackpoint_id = 1

        for user_directory in tqdm (range(182), desc="Loading data from users"):
            user_id = user_directory + 1

            # load labeled_ids
            labeled_ids_list = []
            user_directory_str = str(user_directory).zfill(3)
            with open('dataset/dataset/labeled_ids.txt') as labeled_ids:
                csv_reader = csv.reader(labeled_ids)
                for labeled_id in csv_reader:
                    labeled_ids_list.append(int(labeled_id[0]))

            # add user using labeled_ids
            has_labels = True if user_directory in labeled_ids_list else False
            users_table.append({"_id": user_id, "has_label": True if has_labels else False})

            # add activity with label
            if has_labels:
                with open('dataset/dataset/Data/{}/labels.txt'.format(user_directory_str)) as labels:
                    labels_reader = csv.reader(labels, delimiter='\t')
                    # skip first line
                    for line_number in range(1):
                        next(labels_reader)
                    # add activity
                    for activity_i, activity in enumerate(labels_reader):
                        start_datetime = activity[0].replace('/', '-')
                        start_datetime_iso = datetime.datetime.strptime(start_datetime, '%Y-%m-%d %H:%M:%S').isoformat()+'.000Z'
                        start_datetime_iso = datetime.datetime.strptime(start_datetime_iso, '%Y-%m-%dT%H:%M:%S.000Z')
                        end_datetime = activity[1].replace('/', '-')
                        end_datetime_iso = datetime.datetime.strptime(end_datetime, '%Y-%m-%d %H:%M:%S').isoformat()+'.000Z'
                        end_datetime_iso = datetime.datetime.strptime(end_datetime_iso, '%Y-%m-%dT%H:%M:%S.000Z')
                        transportation_mode = activity[2]
                        activities_labeled_table.append({"_id": activity_id, "user_id": user_id, "transportation_mode": transportation_mode, "start_date_time": start_datetime_iso, "end_date_time": end_datetime_iso})
                        activity_id += 1

            # add trackpoint and activity without label
            rootdir = 'dataset/dataset/Data/{}/Trajectory'.format(user_directory_str)
            for root, dirs, files in os.walk(rootdir):
                for activity_i, file in enumerate(files):
                    activity_i_1 = activity_i + 1
                    # skip activity if too many trackpoints
                    file_for_count = open('{}/{}'.format(rootdir, file))
                    reader = csv.reader(file_for_count)
                    trackpoints_this_activity = len(list(reader))-6
                    if trackpoints_this_activity > 2500:
                        continue

                    first_trackpoint_this_activity = len(trackpoints_table)
                    with open('{}/{}'.format(rootdir, file)) as file:
                        plt_reader = csv.reader(file, delimiter=',')
                        # skip first lines this activity
                        for line_number in range(6):
                            next(plt_reader)

                        # add trackpoint without label
                        for trackpoint_i, trackpoint in enumerate(plt_reader):
                            latitude = float(trackpoint[0]) # decimal degrees
                            longitude = float(trackpoint[1]) # decimal degrees
                            altitude = float(trackpoint[3]) # feet
                            date = trackpoint[5]
                            time = trackpoint[6]
                            date_time = '{} {}'.format(date, time) # YYYY-MM-DD HH:MM:SS
                            date_time_iso = datetime.datetime.strptime(date_time, '%Y-%m-%d %H:%M:%S').isoformat()+'.000Z'
                            date_time_iso = datetime.datetime.strptime(date_time_iso, '%Y-%m-%dT%H:%M:%S.000Z')
                            trackpoints_table.append({"_id": trackpoint_id, "activity_id": activity_i+1, "latlong": [latitude, longitude], "altitude": altitude, "date_time": date_time_iso})
                            trackpoint_id += 1

                    # add activity without label
                    last_trackpoint_this_activity = len(trackpoints_table)-1
                    start_time_iso = trackpoints_table[first_trackpoint_this_activity]["date_time"]
                    end_time_iso = trackpoints_table[last_trackpoint_this_activity]["date_time"]                 
                    activities_unlabeled_table.append({"_id": activity_id, "user_id": user_id, "transportation_mode": 'NULL', "start_date_time": start_time_iso, "end_date_time": end_time_iso})
                    activity_id += 1

        activities_table = self.merge_activities(activities_unlabeled_table, activities_labeled_table)

        return users_table, activities_table, trackpoints_table

    def merge_activities(self, activities_unlabeled_table, activities_labeled_table):
        """matches labeled and unlabeled activities on start date and end date"""
        print("Merging activities tables...")
        for activity_unlabeled_i, activity_unlabeled in enumerate(activities_unlabeled_table):
            for activity_labeled_i, activity_labeled in enumerate(activities_labeled_table):
                if activity_labeled["start_date_time"] == activity_unlabeled["start_date_time"] and activity_labeled["end_date_time"] == activity_unlabeled["end_date_time"] and activity_labeled["user_id"] == activity_unlabeled["user_id"]:
                    activities_unlabeled_table[activity_unlabeled_i] = activity_labeled
        activities_table_merged = activities_unlabeled_table
        return activities_table_merged


    def generate_database(self, users, activities, trackpoints):

        self.create_collection("users")
        self.create_collection("activities")
        self.create_collection("trackpoints")

        self.insert_documents("users", users)
        self.insert_documents("activities", activities)
        self.insert_documents("trackpoints", trackpoints)

        self.drop_collection("users")
        self.drop_collection("activities")
        self.drop_collection("trackpoints")
        
        self.show_collections()

        print("Database created successfully!")


    def create_collection(self, collection_name):
        collection = self.db.create_collection(collection_name)    
        print('Created collection: ', collection)

    def insert_documents(self, collection_name, new_collection):       
        print("Inserting documents into {}...".format(collection_name))
        collection = self.db[collection_name]
        collection.insert_many(new_collection)
        
    def fetch_documents(self, collection_name):
        collection = self.db[collection_name]
        documents = collection.find({})
        for doc in documents: 
            pprint(doc)

    def drop_collection(self, collection_name):
        collection = self.db[collection_name]
        collection.drop()
        
    def show_collections(self):
        collections = self.client['admin'].list_collection_names()
        print("Collections:", collections)


    def task_2_1(self):
        print("Task 2.1:")
        print(self.db["users"].count_documents({}))
        print(self.db["activities"].count_documents({}))
        print(self.db["trackpoints"].count_documents({}))

    def task_2_2(self):
        print("task 2.2:")
        collection = self.db["activities"]
        pipeline = [
            {
                '$group': {
                    '_id': '$user_id',
                    'count': {
                        '$sum': 1
                    }
                }
            }, {
                '$group': {
                    '_id': 'null',
                    'Average': {
                        '$avg': '$count'
                    },
                    'Minimum': {
                        '$min': '$count'
                    },
                    'Maximum': {
                        '$max': '$count'
                    }
                }
            },
            {
                "$project": {
                    "_id": 0
                }
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)

    def task_2_3(self):
        print("task 2.3:")
        collection = self.db["users"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "users": "$$ROOT"
                }
            },
            {
                "$lookup": {
                    "localField": "users._id",
                    "from": "activities",
                    "foreignField": "user_id",
                    "as": "activities"
                }
            },
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": True
                }
            },
            {
                "$group": {
                    "_id": {
                        "_id": "$users._id"
                    },
                    "COUNT(*)": {
                        "$sum": 1
                    }
                }
            },
            {
                "$project": {
                    "COUNT(*)": "$COUNT(*)",
                    "users._id": "$_id._id",
                    "_id": 0
                }
            },
            {
                "$sort": SON([ ("COUNT(*)", -1) ])
            },
            {
                "$limit": 10
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)


    def task_2_4(self):
        print("task 2.4:")
        collection = self.db["users"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "users": "$$ROOT"
                }
            }, 
            {
                "$lookup": {
                    "localField": "users._id",
                    "from": "activities",
                    "foreignField": "user_id",
                    "as": "activities"
                }
            }, 
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": False
                }
            }, 
            {
                "$match": {
                    "$expr": {
                        "$eq": [{
                            "$dateDiff": {
                                "startDate": {"$toDate": "$activities.start_date_time"},
                                "endDate": {"$toDate": "$activities.end_date_time"},
                                "unit": "day"
                            }}, 1
                        ]
                    }
                }
            }, 
            {
                "$group": {
                    "_id": {
                        "_id": "$users._id"
                    }
                }
            }, 
            {
                "$project": {
                    "users._id": "$_id",
                    "_id": 0
                }
            }
        ]

        cursor = collection.aggregate(
            pipeline, 
            allowDiskUse = True
        )
        count = 0
        for doc in cursor:
            count += 1
        print(count)

    def task_2_5(self):
        print("task 2.5:")
        collection = self.db["activities"]
        pipeline = [
            {
                "$group": {
                    "_id": {
                        "transportation_mode": "$transportation_mode",
                        "end_date_time": "$end_date_time",
                        "user_id": "$user_id",
                        "start_date_time": "$start_date_time"
                    },
                    "COUNT(*)": {
                        "$sum": 1
                    }
                }
            },
            {
                "$project": {
                    "user_id": "$_id.user_id",
                    "transportation_mode": "$_id.transportation_mode",
                    "start_date_time": "$_id.start_date_time",
                    "end_date_time": "$_id.end_date_time",
                    "COUNT(*)": "$COUNT(*)",
                    "_id": 0
                }
            },
            {
                "$match": {
                    "COUNT(*)": {
                        "$gt": Int64(1)
                    }
                }
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)


    def task_2_6(self):
        print("task 2.6: (too long runtime, limit is needed)")
        collection = self.db["trackpoints"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "trackpoints": "$$ROOT"
                }
            }, 
            {
                "$lookup": {
                    "localField": "trackpoints.activity_id",
                    "from": "activities",
                    "foreignField": "_id",
                    "as": "activities"
                }
            }, 
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": False
                }
            }, 
            {
                "$lookup": {
                    "localField": "activities.user_id",
                    "from": "users",
                    "foreignField": "_id",
                    "as": "users"
                }
            }, 
            {
                "$unwind": {
                    "path": "$users",
                    "preserveNullAndEmptyArrays": False
                }
            }, 
            {
                "$project": {
                    "users._id": "$users._id",
                    "trackpoints._id": "$trackpoints._id",
                    "trackpoints.latlong": "$trackpoints.latlong",
                    "trackpoints.date_time": "$trackpoints.date_time",
                    "_id": 0
                }
            },
            {
                "$limit": 1000
            }
        ]

        cursor = collection.aggregate(
            pipeline, 
            allowDiskUse = True
        )
        docs = []
        for doc in cursor:
            docs.append(doc)
        
        contact_list = [False] * len(docs)
        for i in tqdm (range(len(docs))):
            for j in range(len(docs)):
                d = haversine((docs[i]["trackpoints"]["latlong"][0], docs[i]["trackpoints"]["latlong"][1]), (docs[j]["trackpoints"]["latlong"][0], docs[j]["trackpoints"]["latlong"][1])) # calculate distance
                d_meters = d * 1000
                if d_meters <= 100.0 and d_meters > 0.0: # closer than 100 meters and not himself
                    if i != j and docs[i]["users"]["_id"] != docs[j]["users"]["_id"]: # skip self comparison
                        if docs[i]["trackpoints"]["date_time"] == docs[j]["trackpoints"]["date_time"]: # compare time stamp within one minute
                            contact_list[docs[i]["users"]["_id"]] = True
                            contact_list[docs[j]["users"]["_id"]] = True
        users_in_contact = 0
        for i in range(len(contact_list)):
            if contact_list[i]:
                users_in_contact += 1
                print(users_in_contact)
                print("user id:", i+1)
        print("users in contact:", users_in_contact)

    def task_2_7(self):
        print("task 2.7:")
        collection = self.db["activities"]
        query = {}

        query["transportation_mode"] = "taxi"

        projection = {}

        projection["user_id"] = "$user_id"
        projection["_id"] = 0

        cursor = collection.find(query, projection=projection)
        taxi_ids = []
        for doc in cursor:
            taxi_ids.append(doc["user_id"])

        cursor2 = self.db["users"].find({"_id": {"$nin": taxi_ids}})
        for user_id in cursor2:
            print(user_id)

    def task_2_8(self):
        print("task 2.8:")
        collection = self.db["activities"]
        pipeline = [
            {
                "$match": {
                    "transportation_mode": {
                        "$ne": "NULL"
                    }
                }
            },
            {
                "$project": {
                    "transportation_mode": "$transportation_mode",
                    "_id": 0
                }
            },
            {
                "$group": {
                    "_id": None,
                    "distinct": {
                        "$addToSet": "$$ROOT"
                    }
                }
            },
            {
                "$unwind": {
                    "path": "$distinct",
                    "preserveNullAndEmptyArrays": False
                }
            },
            {
                "$replaceRoot": {
                    "newRoot": "$distinct"
                }
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )
        transport_modes = []
        for doc in cursor:
            transport_modes.append(doc["transportation_mode"])

        collection = self.db["users"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "users": "$$ROOT"
                }
            },
            {
                "$lookup": {
                    "localField": "users._id",
                    "from": "activities",
                    "foreignField": "user_id",
                    "as": "activities"
                }
            },
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": False
                }
            },
            {
                "$match": {
                    "activities.transportation_mode": {
                        "$in": transport_modes
                    }
                }
            },
            {
                "$project": {
                    "users._id": "$users._id",
                    "activities.transportation_mode": "$activities.transportation_mode",
                    "_id": 0
                }
            },
            {
                "$group": {
                    "_id": None,
                    "distinct": {
                        "$addToSet": "$$ROOT"
                    }
                }
            },
            {
                "$unwind": {
                    "path": "$distinct",
                    "preserveNullAndEmptyArrays": False
                }
            },
            {
                "$replaceRoot": {
                    "newRoot": "$distinct"
                }
            },
            {
                "$sort": SON([("activities.transportation_mode", 1)])
            }
        ]
        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )
        transport_modes_instances = []
        count = 0
        for doc in cursor:
            transport_modes_instances.append(doc)
            count += 1

        transportation_mode = []
        mode_count = []
        current = ""
        for inst in transport_modes_instances:
            if inst["activities"]["transportation_mode"] != current:
                current = inst["activities"]["transportation_mode"]
                transportation_mode.append(current)
            mode_count.append(0)
            mode_count[len(transportation_mode)-1] += 1

        for i, transport in enumerate(transportation_mode):
            print(transport, mode_count[i])


    def task_2_9a(self):
        print("task 2.9a:")
        collection = self.db["activities"]
        pipeline = [
            {
                "$group": {
                    "_id": {
                        "month": {"$month": "$start_date_time"},
                        "year": {"$year": "$start_date_time"}
                    },
                    "COUNT(*)": {
                        "$sum": 1
                    }
                }
            },
            {
                "$project": {
                    "year": "$_id.year",
                    "month": "$_id.month",
                    "COUNT(*)": "$COUNT(*)",
                    "_id": 0
                }
            },
            {
                "$sort": SON([ ("COUNT(*)", -1) ])
            },
            {
                "$limit": 1
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)

    def task_2_9b1(self):
        print("task 2.9b - Which user had the most activities 2008-11?")
        start_date_time = "2008-11-01 00:00:00"
        start = datetime.datetime.strptime(start_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        start = datetime.datetime.strptime(start, '%Y-%m-%dT%H:%M:%S.000Z')

        end_date_time = "2008-11-30 23:59:59"
        end = datetime.datetime.strptime(end_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        end = datetime.datetime.strptime(end, '%Y-%m-%dT%H:%M:%S.000Z')

        collection = self.db["users"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "users": "$$ROOT"
                }
            },
            {
                "$lookup": {
                    "localField": "users._id",
                    "from": "activities",
                    "foreignField": "user_id",
                    "as": "activities"
                }
            },
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": False
                }
            },
            {
                "$match": {
                    "activities.start_date_time": {"$gte": start, "$lt": end}
                }
            },
            {
                "$group": {
                    "_id": {
                        "user_id": "$activities.user_id"
                    },
                    "COUNT(user_id)": {
                        "$sum": 1
                    }
                }
            },
            {
                "$project": {
                    "activities.user_id": "$_id.user_id",
                    "COUNT(activities.user_id)": "$COUNT(user_id)",
                    "_id": 0
                }
            },
            {
                "$sort": SON([("COUNT(activities.user_id)", -1)])
            },
            {
                "$project": {
                    "_id": 0,
                    "activities.user_id": "$activities.user_id",
                    "COUNT(user_id)": "$COUNT(activities.user_id)"
                }
            },
            {
                "$limit": 1
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)

    def task_2_9b2(self):
        print("task 2.9b2 - How many recorded hours does user 63 have in 2008-11?")
        start_date_time = "2008-11-01 00:00:00"
        start = datetime.datetime.strptime(start_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        start = datetime.datetime.strptime(start, '%Y-%m-%dT%H:%M:%S.000Z')

        end_date_time = "2008-11-30 23:59:59"
        end = datetime.datetime.strptime(end_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        end = datetime.datetime.strptime(end, '%Y-%m-%dT%H:%M:%S.000Z')

        collection = self.db["activities"]


        pipeline = [
          {"$match": {
              "start_date_time": {"$gte": start, "$lte": end},
              "user_id": 63
          }},
          {"$project": {
            "difference": {
              "$dateDiff": {
                  "startDate": {"$toDate": "$start_date_time"},
                  "endDate": {"$toDate": "$end_date_time"},
                  "unit": "minute"
              }
            }
          }}
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )
        sum = 0
        for doc in cursor:
            sum += doc["difference"]
            # print(doc["difference"])
        print(sum)

    def task_2_9b3(self):
        print("task 2.9b3 - Which user had the second most activities 2008-11?")
        start_date_time = "2008-11-01 00:00:00"
        start = datetime.datetime.strptime(start_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        start = datetime.datetime.strptime(start, '%Y-%m-%dT%H:%M:%S.000Z')

        end_date_time = "2008-11-30 23:59:59"
        end = datetime.datetime.strptime(end_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        end = datetime.datetime.strptime(end, '%Y-%m-%dT%H:%M:%S.000Z')

        collection = self.db["users"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "users": "$$ROOT"
                }
            },
            {
                "$lookup": {
                    "localField": "users._id",
                    "from": "activities",
                    "foreignField": "user_id",
                    "as": "activities"
                }
            },
            {
                "$unwind": {
                    "path": "$activities",
                    "preserveNullAndEmptyArrays": False
                }
            },
            {
                "$match": {
                    "activities.start_date_time": {"$gte": start, "$lt": end}
                }
            },
            {
                "$group": {
                    "_id": {
                        "user_id": "$activities.user_id"
                    },
                    "COUNT(user_id)": {
                        "$sum": 1
                    }
                }
            },
            {
                "$project": {
                    "activities.user_id": "$_id.user_id",
                    "COUNT(activities.user_id)": "$COUNT(user_id)",
                    "_id": 0
                }
            },
            {
                "$sort": SON([("COUNT(activities.user_id)", -1)])
            },
            {
                "$project": {
                    "_id": 0,
                    "activities.user_id": "$activities.user_id",
                    "COUNT(user_id)": "$COUNT(activities.user_id)"
                }
            },
            {
                "$skip": 1
            },
            {
                "$limit": 1
            }
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )

        for doc in cursor:
            print(doc)

    def task_2_9b4(self):
        print("task 2.9b4 - Does the user with the most activities 2008-11 have more recorded hours than the user with second most?")
        start_date_time = "2008-11-01 00:00:00"
        start = datetime.datetime.strptime(start_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        start = datetime.datetime.strptime(start, '%Y-%m-%dT%H:%M:%S.000Z')

        end_date_time = "2008-11-30 23:59:59"
        end = datetime.datetime.strptime(end_date_time, '%Y-%m-%d %H:%M:%S').isoformat() + '.000Z'
        end = datetime.datetime.strptime(end, '%Y-%m-%dT%H:%M:%S.000Z')

        collection = self.db["activities"]


        pipeline = [
          {"$match": {
              "start_date_time": {"$gte": start, "$lte": end},
              "user_id": 129
          }},
          {"$project": {
            "difference": {
              "$dateDiff": {
                  "startDate": {"$toDate": "$start_date_time"},
                  "endDate": {"$toDate": "$end_date_time"},
                  "unit": "minute"
              }
            }
          }}
        ]

        cursor = collection.aggregate(
            pipeline,
            allowDiskUse=True
        )
        sum = 0
        for doc in cursor:
            sum += doc["difference"]
            # print(doc["difference"])
        print(sum)
        print("2835 < 4092 - The user with the most activities does not have more recorded hours than the user with the second most activities.")

    def task_2_10(self):
        print("task 2.10")
        collection = self.db["activities"]
        pipeline = [
            {
                "$project": {
                    "_id": 0,
                    "activities": "$$ROOT"
                }
            }, 
            {
                "$lookup": {
                    "localField": "activities.user_id",
                    "from": "users",
                    "foreignField": "_id",
                    "as": "users"
                }
            }, 
            {
                "$unwind": {
                    "path": "$users",
                    "preserveNullAndEmptyArrays": False
                }
            }, 
            {
                "$match": {
                    "$expr": {
                        "$eq": [{
                            "$dateDiff": {
                                "startDate": "$activities.start_date_time",
                                "endDate": {"$toDate": "2008-01-01"},
                                "unit": "year"
                            }}, 0
                        ]
                    },
                    "users._id": 113,
                    "activities.transportation_mode": "walk"
                }
            }, 
            {
                "$project": {
                    "activities._id": "$activities._id",
                    "_id": 0
                }
            }
        ]

        cursor = collection.aggregate(
            pipeline, 
            allowDiskUse = True
        )
        activity_ids = []
        for doc in cursor:
            activity_ids.append(int(doc['activities']['_id']))
        print("activity_ids:", activity_ids)

        latlongs = []
        trackpoints = self.db["trackpoints"].find({"activity_id": {"$in": activity_ids}})
        for doc in trackpoints:
            print(doc)
            latlongs.append(doc['latlong'])
        print("trackpoints with latlongs:", latlongs)
    
def main():
    program = None

    try:

        program = DatabaseProgram()
        # users, activities, trackpoints = program.load_dataset()
        # program.generate_database(users, activities, trackpoints)

        program.task_2_1()
        program.task_2_2()
        program.task_2_3()
        program.task_2_4()
        program.task_2_5()
        program.task_2_6()
        program.task_2_7()
        program.task_2_8()
        program.task_2_9a()
        program.task_2_9b1()
        program.task_2_9b2()
        program.task_2_9b3()
        program.task_2_9b4()

    except Exception as e:
        print("ERROR: Failed to use database:", e)
    finally:
        if program:
            program.connection.close_connection()


if __name__ == '__main__':
    main()
