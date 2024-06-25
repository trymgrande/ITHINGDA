from logging import RootLogger
from DbConnector import DbConnector
from tabulate import tabulate
from haversine import haversine
import csv
import os

class ExampleProgram:
    def __init__(self):
        self.connection = DbConnector()
        self.db_connection = self.connection.db_connection
        self.cursor = self.connection.cursor

        self.labeled_ids_list = []
        self.user_table = []
        self.activity_unlabeled_table = []
        self.activity_labeled_table = []
        self.activity_table = [] # labeled and unlabeled merged
        self.trackpoint_table = []


    def load_dataset(self):
        print('loading dataset, please wait...\n')
        for user_id in range(182):
            print("loading data from user", user_id)

            # load labeled_ids
            user_id_str = str(user_id).zfill(3)
            with open('src/dataset/dataset/labeled_ids.txt') as labeled_ids:
                csv_reader = csv.reader(labeled_ids)
                for labeled_id in csv_reader:
                    self.labeled_ids_list.append(int(labeled_id[0]))

            # add user using labeled_ids
            has_labels = True if user_id in self.labeled_ids_list else False
            self.user_table.append(["1" if has_labels else "0"])

            # add activity with label
            if has_labels:
                with open('src/dataset/dataset/Data/{}/labels.txt'.format(user_id_str)) as labels:
                    labels_reader = csv.reader(labels, delimiter='\t')
                    # skip first line
                    for line_number in range(1):
                        next(labels_reader)
                    # add activity
                    for activity_i, activity in enumerate(labels_reader):
                        start_datetime = activity[0].replace('/', '-')
                        end_datetime = activity[1].replace('/', '-')
                        transportation_mode = activity[2]
                        self.activity_labeled_table.append([user_id+1, transportation_mode, start_datetime, end_datetime])

            # add trackpoint and activity without label
            rootdir = 'src/dataset/dataset/Data/{}/Trajectory'.format(user_id_str)
            for root, dirs, files in os.walk(rootdir):
                for activity_i, file in enumerate(files):
                    activity_i_1 = activity_i + 1
                    # skip activity if too many trackpoints
                    file_for_count = open('{}/{}'.format(rootdir, file))
                    reader = csv.reader(file_for_count)
                    trackpoints_this_activity = len(list(reader))-6
                    if trackpoints_this_activity > 2500:
                        continue

                    first_trackpoint_this_activity = len(self.trackpoint_table)
                    with open('{}/{}'.format(rootdir, file)) as file:
                        plt_reader = csv.reader(file, delimiter=',')
                        # skip first lines this activity
                        for line_number in range(6):
                            next(plt_reader)

                        # add trackpoint
                        for trackpoint_i, trackpoint in enumerate(plt_reader):
                            latitude = trackpoint[0] # decimal degrees
                            longitude = trackpoint[1] # decimal degrees
                            altitude = trackpoint[3] # feet
                            date = trackpoint[5]
                            time = trackpoint[6]
                            datetime = '{} {}'.format(date, time) # YYYY-MM-DD HH:MM:SS
                            self.trackpoint_table.append([activity_i+1, latitude, longitude, altitude, datetime])
                        # add activity
                    last_trackpoint_this_activity = len(self.trackpoint_table)-1
                    start_time = self.trackpoint_table[first_trackpoint_this_activity][4]
                    end_time = self.trackpoint_table[last_trackpoint_this_activity][4]
                    self.activity_unlabeled_table.append([user_id+1, 'NULL', start_time, end_time])

    def merge_activities(self):
        # matches labeled and unlabeled activities on start date and end date
        print("merging activity tables...")
        for activity_unlabeled_i, activity_unlabeled,  in enumerate(self.activity_unlabeled_table):
            for activity_labeled_i, activity_labeled in enumerate(self.activity_labeled_table):
                if activity_labeled[2] == activity_unlabeled[2] and activity_labeled[3] == activity_unlabeled[3] and activity_labeled[0] == activity_unlabeled[0]:
                    self.activity_unlabeled_table[activity_unlabeled_i] = activity_labeled
        self.activity_table = self.activity_unlabeled_table
        print("activity tables merged")

    def insert_data_universal(self, table_name, column_names, rows):
        pass
        # print("preparing to insert into", table_name)
        # val = self.format_rows(rows)
        # print(val)

        # items_placeholders = ', '.join(['%s'] * len(column_names))
        # items_placeholders = '({})'.format(items_placeholders)

        # # redundant if input is pure string instead of array...
        # column_names_str = ', '.join(column_names)
        # column_names_str = '({})'.format(column_names_str)

        # sql = "INSERT INTO {} {} VALUES {}".format(table_name, column_names_str, items_placeholders)

        # print("sql:", sql)
        # for row in rows:
        #     # Take note that the name is wrapped in '' --> '%s' because it is a string,
        #     # while an int would be %s etc
        #     # query = "INSERT INTO %s (name) VALUES ('%s')"
        #     self.cursor.execute(sql % ())
        # # self.cursor.executemany(sql, val)
        # self.db_connection.commit()
        # columns_len = len(rows[0])

    def insert_data(self, rows, query):
        print(query)
        rows_len = len(rows) # 177
        for row in range(rows_len):
            if row % 100000 == 0:
                progress = str(round(100 * row/rows_len))
                print("progress:", progress + '%')
            self.cursor.execute(query, tuple(rows[row]))
        self.db_connection.commit()
        print("table insertion finished")

    def create_user_table(self):
        query = """CREATE TABLE IF NOT EXISTS user (
                id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                has_labels BOOLEAN)
                """
        # This adds table_name to the %s variable and executes the query
        self.cursor.execute(query)
        self.db_connection.commit()

    def create_activity_table(self):
        query = """CREATE TABLE IF NOT EXISTS activity (
                id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                user_id INT,
                transportation_mode VARCHAR(30),
                start_date_time DATETIME,
                end_date_time DATETIME,
                FOREIGN KEY (user_id) REFERENCES user(id))
                """
        # This adds table_name to the %s variable and executes the query
        self.cursor.execute(query)
        self.db_connection.commit()

    def create_trackpoint_table(self):
        query = """CREATE TABLE IF NOT EXISTS trackpoint (
                id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                activity_id INT,
                lat DOUBLE,
                lon DOUBLE,
                altitude INT,
                date_days DOUBLE,
                date_time DATETIME,
                FOREIGN KEY (activity_id) REFERENCES activity(id))
                """
        # This adds table_name to the %s variable and executes the query
        self.cursor.execute(query)
        self.db_connection.commit()

    def insert_users(self):
        print("inserting users...")
        query = "INSERT INTO user (has_labels) VALUES (%s)"
        rows = self.user_table
        self.insert_data(rows, query)

    def insert_activities(self):
        print("inserting activities...")
        query = "INSERT INTO activity (user_id, transportation_mode, start_date_time, end_date_time) VALUES (%s, %s, %s, %s)"
        rows = self.activity_table
        self.insert_data(rows, query)
        # self.cursor.execute("INSERT INTO activity (id, user_id, transportation_mode, start_date_time, end_date_time) VALUES (0, 100, NULL, '2011-08-02 23:49:22', '2011-08-03 00:02:42')")

    def insert_trackpoints(self):
        query = "INSERT INTO trackpoint (activity_id, lat, lon, altitude, date_time) VALUES (%s, %s, %s, %s, %s)"
        rows = self.trackpoint_table
        # self.cursor.execute("INSERT INTO trackpoint (activity_id, lat, lon, altitude, date_time) VALUES (1, '39.976924916', '116.30532667', '323.299124015748', '2011-08-02 23:49:22')")
        self.insert_data(rows, query)

    def fetch_data(self, table_name):
        if (table_name == "activity"):
            query = "SELECT * FROM %s WHERE start_date_time = '2008-10-23 02:53:04'"
        else:
            query = "SELECT * FROM %s LIMIT 100"

        self.cursor.execute(query % table_name)
        rows = self.cursor.fetchall()
        # print("Data from table %s, raw format:" % table_name)
        # print(rows)
        # Using tabulate to show the table in a nice way
        print("Data from table %s, tabulated:" % table_name)
        print(tabulate(rows, headers=self.cursor.column_names))
        return rows

    def drop_table(self, table_name):
        print("Dropping table %s..." % table_name)
        query = "DROP TABLE if exists %s"
        self.cursor.execute(query % table_name)

    def show_tables(self):
        self.cursor.execute("SHOW TABLES")
        rows = self.cursor.fetchall()
        print(tabulate(rows, headers=self.cursor.column_names))

    def execute_query(self, query_string):
        self.cursor.execute(query_string)
        rows = self.cursor.fetchall()
        print(tabulate(rows, headers=self.cursor.column_names))
        return rows


def main():

    program = None
    try:
        program = ExampleProgram()

        program.drop_table(table_name="trackpoint")
        program.drop_table(table_name="activity")
        program.drop_table(table_name="user")

        program.load_dataset()
        program.merge_activities()

        program.create_user_table()
        program.create_activity_table()
        program.create_trackpoint_table()

        program.insert_users()
        program.insert_activities()
        program.insert_trackpoints()

        # task 2.1
        print("task 2.1")
        program.execute_query("""
            SELECT (SELECT COUNT(*) FROM user) AS users,
            (SELECT COUNT(*) FROM activity) AS activities,
            (SELECT COUNT(*) FROM trackpoint) AS trackpoints;
        """)

        # task 2.2
        print("task 2.2")
        program.execute_query("""
            SELECT avg(number_of_activities), min(number_of_activities), max(number_of_activities) 
                FROM 
                (
                    SELECT count(activity.user_id) as number_of_activities
                    FROM user
                    LEFT JOIN activity
                    ON (activity.user_id = user.id)
                    GROUP BY user.id
                ) AS t
        """)

        # task 2.3
        print("task 2.3")
        program.execute_query("""
            SELECT  
            COUNT(*) AS activities, user.id
            FROM user 
            JOIN activity ON activity.user_id = user.id 
            GROUP BY user.id
            ORDER BY activities desc 
            LIMIT 10;
        """)

        # task 2.4
        print("2.4")
        program.execute_query("""
            SELECT COUNT(*) FROM
            (
                SELECT
                user.id
                FROM user
                JOIN activity ON user.id = activity.user_id
                where DATEDIFF(activity.end_date_time, activity.start_date_time) = 1
                GROUP BY user.id
            ) AS users_with_1_day_activity;
        """)
        # task 2.5
        print("task 2.5")
        program.execute_query("""
            SELECT user_id, transportation_mode, start_date_time, end_date_time, COUNT(*)
            FROM activity
            GROUP BY user_id, start_date_time, end_date_time, transportation_mode
            HAVING COUNT(*) > 1
        """)

        # task 2.6
        query = """
            select user.id, trackpoint.id, trackpoint.lat, trackpoint.lon, SUBSTRING(trackpoint.date_time, 1, 16) # substring to round down to nearest minute
            from trackpoint
            join activity on trackpoint.activity_id = activity.id
            join user on activity.user_id = user.id
            limit 1000;
        """
        program.cursor.execute(query)
        res = program.cursor.fetchall()
        print("task 2.6: (too long runtime, limit is needed)")
        contact_list = [False] * len(res)
        for i in range(len(res)):
            # if i % 100 == 0:
                # print("progress:", round(i/len(res)*100), "%")
            for j in range(len(res)):
                d = haversine((res[i][2], res[i][3]), (res[j][2], res[j][3])) # calculate distance
                d_meters = d * 1000
                if d_meters <= 100.0 and d_meters > 0.0: # closer than 100 meters and not himself
                    if i != j and res[i][0] != res[j][0]: # skip self comparison
                        if res[i][4] == res[j][4]: # compare time stamp within one minute
                            contact_list[res[i][0]] = True
                            contact_list[res[j][0]] = True
        users_in_contact = 0
        for i in range(len(contact_list)):
            if contact_list[i]:
                users_in_contact += 1
                print("user id:", i+1)
        print("users in contact:", users_in_contact)

        # task 2.7
        print("task 2.7")

        program.execute_query("""SELECT * FROM user where id NOT IN
                                (
                                    SELECT user_id 
                                    FROM activity
                                    WHERE transportation_mode = 'taxi'
                                )""")

        # Real query for task 2.8
        print("task 2.8")
        program.execute_query("""SELECT transportation_mode, count(transportation_mode) AS number_of_users FROM
                                (SELECT DISTINCT user.id AS id, activity.transportation_mode AS transportation_mode
                                FROM user JOIN activity on (activity.user_id = user.id) WHERE activity.transportation_mode IN
                                (
                                    SELECT DISTINCT transportation_mode 
                                    FROM activity 
                                    WHERE transportation_mode != 'NULL'
                                ) ORDER BY activity.transportation_mode) AS x GROUP BY transportation_mode
                                    """)

        # task 2.9a
        print("task 2.9a")
        program.execute_query("""SELECT YEAR(start_date_time), MONTH(start_date_time), count(*) AS number_of_activities 
                                FROM activity 
                                GROUP BY YEAR(start_date_time), MONTH(start_date_time) ORDER BY number_of_activities DESC LIMIT 1""")

        # task 2.9b
        print("task 2.9b - Which user had the most activities 2008-11?")
        program.execute_query("""SELECT activity.user_id, count(activity.user_id) AS number_of_activities
                                FROM user
                                JOIN activity ON (activity.user_id = user.id)
                                WHERE YEAR(activity.start_date_time) = '2008' AND MONTH(activity.start_date_time) = '11' 
                                GROUP BY activity.user_id
                                ORDER BY number_of_activities DESC LIMIT 1""")

        print("task 2.9b - How many recorder hours does user 63 have in 2008-11?")
        program.execute_query("""SELECT SUM(activity_duration_minutes) FROM
                                (
                                    SELECT *, TIMESTAMPDIFF(MINUTE, start_date_time, end_date_time) AS activity_duration_minutes
                                    FROM activity 
                                    WHERE user_id = '63' AND YEAR(start_date_time) = '2008' AND MONTH(start_date_time) = '11'
                                ) as x""")

        print("task 2.9b - Which user had the second most activities 2008-11?")
        program.execute_query("""SELECT activity.user_id, count(activity.user_id) AS number_of_activities
                                FROM user
                                JOIN activity ON (activity.user_id = user.id)
                                WHERE YEAR(activity.start_date_time) = '2008' AND MONTH(activity.start_date_time) = '11' 
                                GROUP BY activity.user_id
                                ORDER BY number_of_activities DESC LIMIT 1 OFFSET 1""")

        print("task 2.9b - Does the user with the most activities 2008-11 have more recorder hours than the user with second most?")
        program.execute_query("""SELECT SUM(activity_duration_minutes) FROM
                                (
                                    SELECT *, TIMESTAMPDIFF(MINUTE, start_date_time, end_date_time) AS activity_duration_minutes
                                    FROM activity 
                                    WHERE user_id = '129' AND YEAR(start_date_time) = '2008' AND MONTH(start_date_time) = '11'
                                ) as x""")
        print("2773 < 4058 - The user with the most activities does not have more recorder hours than the user with the second most activities.")

        # task 2.10
        print("task 2.10 - Find the total distance walked (in km) in 2008 by user with id = 112")
        program.execute_query("""SELECT lon, lat
                                FROM trackpoint
                                WHERE activity_id IN
                                (
                                    SELECT id
                                    FROM activity
                                    WHERE transportation_mode = 'walk' AND YEAR(start_date_time) = '2008' AND user_id = '113'
                                )
                                """)

        
        program.drop_table(table_name="trackpoint")
        program.drop_table(table_name="activity")
        program.drop_table(table_name="user")

        program.show_tables()

    except Exception as e:
        print("ERROR: Failed to use database:", e)
    finally:
        if program:
            program.connection.close_connection()

if __name__ == '__main__':
    main()