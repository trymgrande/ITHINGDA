# TDT4305 2021 - Assignment 1

## Intro
### 1) For each of the three Vs, answer the following:
#### a) Why is this property showing up in the Big Data era and not previously?
Volume: More (and richer) data has been generated over time.
Velocity: More computing power and more data sources.
Variety: Better information retrieval and data retrieval.
Veracity:  More data means more accuracy, as well as AI for missing data.
Value: More business attraction.

#### b) What challenges does this property give for traditional RDBMSs?
Volume: slower data access and medium cardinality (uncompressed).
Velocity: need to write fast enough.
Variety: slow access
Veracity: Missing values
Value: Not utilized without analytics (OLAP)

### 2) Given Big Data from a field of your choice, how could you use it to create value for yourself or others? E.g. Big Data in medicine can be used for diagnosing diseases based on patients’ health history.
Big data in drones can be used to optimize flight characteristics using PIDs, based on stick movement as input and gyroscopic data as output. This also applies to other PID based systems.

### 3) What are the challenges in ensuring the trustworthiness of Big Data?
- ensuring data quality
- test strategy

## MapReduce and HDFS
### 1) Solve the following exercises from the main course book MMDS:
#### 1. 2.2.1 (a) and (b)
##### (a) Suppose we do not use a combiner at the Map tasks. Do you expect there to be significant skew in the times taken by the various reducers to process their value list? Why or why not?
First of all, there will be more latency due to a higher network traffic caused by transmitting less compressed data. Secondly, there will be no overall time loss as the combining will simply be moved from the mapper to the reducer. This is assuming the shuffling is done, meaning there is no overhead while reducing. This will however, take longer on the reduce side as it needs to reduce a larger set of keys. 

Yes, because document sizes are different.

##### (b) If we combine the reducers into a small number of Reduce tasks, say 10 tasks, at random, do you expect the skew to be significant? What if we instead combine the reducers into 10,000 Reduce tasks?
Assuming there are 10 000 reducers, it will be more effective to combine into a small number of reduce tasks allowing all reducers to be fully utilized. This is because performance is not left on the table by having too few tasks, while there is also no need for more iterations as the tasks are not more than the reducers.

No, because samples are randomized meaning about equal sample sizes.


#### 2. 2.3.1 (a), (b), and (c)
#### Exercise 2.3.1: Design MapReduce algorithms to take a very large file of integers and produce as output:
##### (a) The largest integer.
ints.map(x=>x).reduce((a, b) => (a if a>b else b))

##### (b) The average of all the integers.
ints.map(x => x).reduce((a,b) => (a+b))

##### (c) The same set of integers, but with each integer appearing only once.
ints.map(x=>x).distinct()
ints.filter(x => True if x not in ints)
ints.sort().filter()

#### 3. 2.3.2


### 2) What is the role of the DFS (GFS or HDFS) in a MapReduce system?
DFS allows for a cheaper, scalable system with redundancy.

### 3) What is the difference between a NameNode and a DataNode in HDFS?
The NameNode is considered the master and contains metadata. The DataNode is the slave and contains data blocks that works with reading/writing and takes creation/replication/deletion commands from master. The datanode sends out status on what is being stored using hearthbeats.

### 4) What is a data block in HDFS and how is it replicated across data nodes?
A data block is stored inside a DataNode. It is represented similar to a regular local file. They are the result of storing a large file in the DFS so that it needs to be split into smaller blocks. The blocks can be replicated in multiple levels to achieve redundancy. 