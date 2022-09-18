# intro
- the 3 Vs
    - volume
    - velocity
    - variety
- real-time vs data storage
    - stream processing with ingestion vs batch processing
        - both output analytics
- hadoop
    - distributed machines
    - resource handling
    - processing
        - apache spark
            - open source
    - ![](images/2022-02-09-15-56-38.png)
- datasett
    - wikipedia pageviews (with/without edits)
    - twitter firehose (1% sample)
    - web scraping (muig copyright)
        e.g. allrecipies.com, tripadvisor.com, booking.com
- bruk python/spark

# hdfs og mapreduce
- tema
    - hadoop distributed file system (hdfs)
    - mapreduce framework
- standard hw smaller (scalable) and cheaper
- hadoop eco system
    - distributed fs
        - mapreduce / db
            - processing framework
- distributed fs
    - blocks spread across cpus, file pointers to cpus
    - implements replication
        - backup and faster read
    - huge files, slow access, shelf hw
    - inspired by google fs
    - made for data stream access rather than random
    - can have only one writer, multiple readers
    - append to files only (not write to middle)
    - 3-way replication gives fault tolerance
    - blocks
        - v1: 64MB, v2: 128MB
        - big blocks -> less seeking
        - blocks stored as normal "local" files
            - files less than 64/128MB only use actual file size on disk
        - fixed size
            - allows files bigger than physical disk
        - replication
            - variable number of replicas (replication level)
            - each file has pointers to blocks
    - node types
        - name node
            - master
            - maps file name to block pointer
            - metadata of actual data (filename, path, block level, id, location, replicas...)
            - write ahead logging
            - regulates client access to file data
            - fault tolerance
                - storage on different disk
                - hadoop 2: active standby namenode
        - data node
            - slave
            - takes creation/replication/deletion of blocks by master commands
            - one for each block
            - worker node for read/write
            - fault tolerance
                - only replication
                - use checksum to detect fault
        - network distance between computers/racks/data centers is increasingly expensive
        - reading
            - ask namenode, then read from any of replicated blocks in data node
        - writing
            - ask namenode, then write to all replicated datanodes (directly to first, redirected to next etc.), datanodes report to namenode when written
        - replicated datanodes are spread across racks for fault tolerance and load balancing
    - maintenance
        - need to check correct replication level (rebalancing)
        - block scanner: verify checksum
        - for removal of nodes: replicate before removal, not after (decomitioning?)
    - hadoop 2.0
        - federing?: multiple namenodes by separate dir paths
        - better security, performance
    - hadoop 3: multiple namenodes
    - experience
        - reliable
        - simple maintenance
        - scalable
- mapreduce
    - on top of fs
    - send function to data instead of data to function
    - framework for parallell, scalable processing of big data
    - goals
        - linear scaling
        - many nodes
        - big files
        - hide complexity of distribution, parallellization, fault tolerance
        - separate distributed (mapreduce) and application code
        - standard hw
    - map
        - (k1,v1) -> list(k2,v2)
        - number of docs
            - each node computes list of (word:occurences)
    - (shuffling keys to same node)
        - partition
            - load balancing
        - combiner
            - local reducer before sending over network (if any, could be local, meaning same cpu for map as reduce)
    - reduce
        - (k2, list(v2)) -> list(k3,v3)
        - number of docs
            - each node reduces same key to same machine (word:total_occurences)
        - input sorted on keysV
    - ![](images/2022-04-25-14-02-41.png)
    - pig and hive
    
        
# 4 - spark
(simple examples)
- background/motivation
    - improvement on mapreduce
        - makes big data analysis easier by abstracting scaling
        - weakness
    - spark made from skratch
- goal
    - support recyclihng of dataset
    - manage fault tolerance/scalability
    - experiment with programmability
- programming model
    - resilient distributed datasets (RDDs) (transformations)
        - immutable collection of transformable objects made from:
            - external storage
            - arrays for testing
            - result of transformation
        - can be buffered
            - saves disk read/write
        - resilient
            - lost partition can be reconstructed from original RDD
    - actions on RDDs
        - count/reduce/collect/save 
        - produce data, not new RDD
    - lazy evaluation
        - execution waits until action
    - programming
        - spark shell (scala interpreter)
        - pyspark (python interpreter)
        - sparksql (sql syntax)
- scala
    - improvement of java with functional programming
- (simple examples)
- RDD representation
    - dataset partitions
    - t.d. RDD that represents HDFS file has a partition for each block (1 partition per block in file)
    - partitioning
        - RDD is hash/range partitioned
    - dependecies
        - parent RDD(s)
            - narrow dependency: used by only one child RDD
            - wide dependency: used by multiple child RDDs
- dataframes
    - tables
    - can be queried using sql
    - logical plan with optimizing on execution
- datasets
    - df, but "type safe"

# 5 - guest lecture



# 6 - shingles, minhash, LSH
- shingling
    - ![](images/2022-05-11-14-19-33.png)
    
- context
    - goal: need to find similar pairs
    - applications
        - mirror websites, stories on same topic
    - problems
        - many documents
    - process
        - ![](images/2022-05-28-10-27-39.png)
    - docs -> sets conversion
    - k-shingle (k-gram) is a sequence of k tokens that appears on the doc
        - tokens can be characters, words etc.
            - e.g. doc={abcd}, k=2 => {ab,bc,cd}
        - k is selected with low probability of documents sharing single
        - similarity if > 0 shingles matching
        - jaccard similarity
    - many comparisons is slow
    - need smaller representations of documents (sets) for faster comparisons
        - minhash signatures
            - sets are generated with token combinations
            - sets can be compared in term matrix
            - minhash signature algorithm
            - similarity comparison (jaccard)
                - ![](images/2022-05-28-11-08-42.png)
                - ![](images/2022-05-28-11-09-12.png)
                - ...
    - number of pairs problem
        - need lsh
            - returns candidate pairs
- exercises




# 7 - LSH (2)



# 8 - signature to LSH
- ![](images/2022-05-29-10-05-48.png)
- ![](images/2022-05-29-10-06-11.png)



# 9 - h1 - mining streaming data del 1
- not enough memory for streams
    - need to handle immidiately
- query types
    - ad hoc ("for this") 
        - one-off query that contain variables
        - You ask a query and there is an immediate response
            - E.g: What is the maximum value seen so far in the stream S?
    - standing query
        - asking continously e.g. report each new maximum value ever
        - You are asking a query to the system say “ Anytime you have an answer to this query send me the response”
            - here you don’t get the answer immediately .
        - continous (e.g. max value today or return when value exceeds threshold)
- sliding windows
    - window of N elements received or time passed
    - should fit in main memory for fast runtime
    - queries about *window* of time or elements received
    - can run out of memory
    - protocol
        - 
- want to count bit ocurrences
    - DGIM method
        - https://medium.com/fnplus/dgim-algorithm-169af6bb3b0c
        - store only O(log2(N) bits per stream, N=windows size
        - 50% accuracy
        - estimate bits:
            - Buckets i DGIM algoritmen er brukt til å estimere antall 1ere i en bitstrøm. algoritmen bruker log2(N) bits for å representere et vindu av N bits. Hver bit som kommer inn i vinduet har en egen timestamp, hvor den første biten som kommer vil ha timestamp 1, neste vil ha timestamp 2 osv. Man deler vinduet inn i buckets som inneholder 1ere og 0ere. Alle størrelsene for buckets må være 2^x, altså blir første størrelse 2⁰ = 1, andre blir 2¹ = 2, tredje blir 2² = 4 osv. Dersom man har en bøtte av størrelse 4, betyr det at det er fire 1ere i bøtta. Biten lengst til høyre i bøtta må alltid være en 1er. Størrelsene på bøtta øker alltid fra høyre side av vinduet. Altså er den minste bøtta alltid lengst til høyre, og den største er alltid lengst til venstre. For hver størrelse, er det alltid maks 1 eller 2 bøtter. Så man kan ikke ha tre bøtter av samme størrelse. Ved bruk av alle disse reglene, ville jeg altså ha delt inn vinduet av bitstrømmen (hvor bitverdi er 1 dersom kunden som ser på produktet er den vi leter etter, og 0 hvis det er noen andre) inn i buckets. La oss si at lengst til høyre i vinduet så blir det to buckets av størrelse 1 (dvs det er én bit i hver av bøttene). Det kommer en ny bit. Hvis denne biten er 0 gjør vi ingen endringer, men hvis biten er 1, blir det 3 bøtter av størrelse 1, som ikke er tillatt. Da merger vi sammen de to eldste 1er bøttene til en bøtte av størrelse 2. Da kan den nye biten bli med i vinduet som en bøtte av størrelse 1. Dersom vi allerede hadde to bøtter av størrelse 2 før vi foretok mergingen, må vi fortsette prosessen videre til venstre i vinduet ved å merge de to bøttene av størrelse 2 sammen til en bøtte av størrelse 4. Dette må vi gjøre helt til vi har maks 1 eller 2 bøtter av hver størrelse. 
            
            Dette kan vi bruke for å estimere antall 1ere i de nyeste k bitene, hvor k < n. Dette gjør vi ved å summere alle bøttene som har slutt-timestamp mindre eller lik k bits i fortiden. Når det gjelder den siste bøtta, legger vi til bare halvparten av størrelsen, siden vi ikke vet hvor mange 1ere i den siste og største bøtta som fortsatt er i vinduet og ikke utenfor. På grunn av denne “gjettingen” for siste bøtte, får vi kun et estimat av antallet. Dette estimatet har en maksimal error på 50%, altså vil antallet vi estimerer alltid være innenfor +-50% av det eksakte antallet.


- buckets
    - combine counts into pairs with power of 2
    - expand when lowest bucket is full by combining into higher bucket
    - oldest buckets cut out if window full
    - 2 buckets of each size
    - logN different sizes
    - at most 2logN 
- error bound
    - 2^(i-1)
- bloom filters
    - calculate filter:
        - accumulate OR
        - 0-9 left to right
        - filter length = modulo
    - checks if site has been seen before (crawlers)
    - false positives (caused by collisions)
        - need to restart filter occationally
    - uses hash functions
        - false positives if maybe seen before

# h2 - mining streaming data del 2 - sampling & sketching
- sampling
    - only use fraction of data to save time/space
    - potential loss of unique data
- hash sampling using (many) buckets
- distinct elements estimation
    - flajolet-martin (FM)
        - approximates numnber of distinct objects in stream or db in one pass
        - if stream contains n elements with m of them unique, it takes O(n) time, O(logm) memory
        - hashmaps numbers and counts number of values=1



# 10 - h3 - streaming data systems
- storm vs spark
    - ![](images/2022-02-07-11-17-51.png)
- storm
    - architecture
        - ![](images/2022-05-31-14-58-00.png)
        - master-slave model where processes are coordinated through zookeeper
        - components
            - nimbus
                - master that distributes application code across worker nodes (similar to hadoop)
                - stateless single node
                    - fail-fast unlike hadoop
                        - can be restarted without recovery
            - supervisor nodes
                - worker
                - runs daemon (process) responsible for creating, starting, stopping worker processes to execute assigned tasks
                    - several executors inside each worker
                - fail-fast
                    - stores state in zookeeper
            - zookeeper
                - cordinates between the two
                - all data (state) is stored here


    - delivery guarantees
        - messages sent at most once
            - means it will not be retransmitted after received
            - may cause data loss
            - for less important data
        - at least once
            - means it can be retransmitted until processing is complete
            - may duplicate data
                - system needs to be duplicatin tolerant
                    - idempotent (processing again does not impact system (e.g. upsert - insert first, update after))
            - for more important data
            - most common
        - exactly once
            - assuming data will not get lost
            - difficult to achieve, rarely used

    - fault tolerance
        - soft failiure
            - inability to process a request
        - hard failiure
            - node failiure
    - distributed real-time computation system
    - streams
        - sequence of tuples
    - spout
        - source of stream
    - bolts
        - functions that produce new streams
    - topology vs architecture
    - hierarchy of workers, supervisors, zookeepers, numbus

    - pros
        - real-time streaming
        - fault tolerance
            - automatic restart
        - scalability
        - ease of use
            - deploying/operation
        - built-in user defined functions
    - cons
        - no natural data storage and recovery handling
        - real-time focus means unable to implement windowing
- spark
    - pros
        - micro batching allowing natural windowing (sliding windows)
        - easy to use
        - connect to existing systems giving flexibility
    - cons
        - uses tuples, hard to implement java
        - micro-batching not easy to handle/analyze real-time
        - no natural data storage and recovery handling
        - single point of failiure in the stream

- asterixDB
    - for semi-structured data, data-intensive computing, parallell db systems
    - scalable storage/indexing
        - hash partitioning
        - lsm indexing
    - declarative query support
    - data feed (stream)
        - need data feed management
    - data ingestion support
    - cascading network of feeds
        - competing for resources
            - need data ingestion policy 
    - scalability acording to data ingestion
    - data indigestion
        - data may arrive faster than ability to pre-process
            - different policies for solving
    - fault tolerance
        - solft/hard failiure recovery
        - recovery from data ingestion

| category            | AsterixDB | Storm                              | Spark                                   |
|---------------------|---2--------|------------------------------------|-----------------------------------------|
| pros                |           |                                    |                                         |
| cons                |           |                                    |                                         |
| processing model    |           | event-streaming                    | micro-batching / batch (spark core)     |
| delivery guarantees |           | at most once / at least once       | exactly once                            |
| latency             |           | sub-second                         | seconds                                 |
| language options    |           | java, clojure, scala, python, ruby | java, scala, python (pyspark)           |
| development         |           | use other tool for batch           | batching and streaming are very similar |





# 11 - adweb (web advertisement)
- actors bidding on ads
    - greedy
        - For a query pick any advertiser who has bid for that query


    - select as candidate even though budget is not enough
    - candidates are actors who bid on a given q

    - competitive ratio is total accumulated income



# 12 - h4/h5 - recommender systems (h5 is practical examples)
- want to recommend e.g. movies to the user
- the problem is *how*
- recommendations are built based on e.g. search history etc.
- long tail phenomenon
    - 20 - 80
    - 20% popular, 80% inventory
- types
    - list of recommended for all users
    - tailored to individual users
- formal model
    - set of customers: x
    - set of items: s
    - utility function: u = x * s -> r
    - r = total set of ratings e.g. 0-5 stars
    - implicit rating
        - need to extrapolate ourself if user is lazy e.g. purchase implies high rating
            - problems
                - sparsity of u - most people have not rated
                - cold start: new users - no ratings/history
            - 3 approaches
                - content-based
                    - recommend based on similar liked content
                    - no cold start or sparsity problems for new items
                    - hard to recommend to new users
                        - overspecialization: bubble of content, repeating
                    - gdpr: able to explain how it is recommended
                - collaborative filtering
                    - most used method
                    - filtering away other items than user's taste
                    - sparsity
                    - first rater problem
                        - cannot recommend unrated item
                    - popularity bias
                        - cannot recommend popular items to users with unique taste, tends to recommend popular items
                    - no need to find features unlike content-based, works with any item type
                    - cold start: not enough users to find a match
                    - use quality judgement from other users for recommendations
                        - users who like the same will be recommended the same
                            - need to estimate new user's rating for given item
                                - find similar users
                                    - jaccard similarity measure
                                        - count number of common ratings for 2 users divided by number of rated items by either user (a^b/avb)
                                        - ignores rating value
                                    - cosine similarity
                                        - subtracts average rating from each user
                                        - treats missing ratings as negative (-5 stars)
                                    - pearson
                                        - hybrid
                                        - average rating of only common ratings
                - latent factor based
    - explicit rating
        - crowd sourcing
- item profiles
    - set of features of item e.g. tf-idf (term freq * inverse doc freq)
- user profiles and prediction
    - ml model by item profile (item features) and user profile (liked content)

- item-item often better than user-user because items are simple, users have multiple tastes
- assume subtract the avg to avoid similarity -> 0 on exam when using cos sim (?)
- data/rating sparsity
    - combine implicit and explicit ratings
- hybrid methods

# 13 - H6 - mining social graphs
- examples
    - friend recommendation
    - ad targeting
- networks can be clustered
    - can be overlapping
    - adjecency matrix can show if overlapping or not
    - important characteristic is how many edges between clusters
- methods on how to find clusters
    - method 1 - edge betweeenness (girvan newman)
        - number of shortest paths passing over the edge
        - algorithm
            - unweighed, undirected graphs
            - remove edges with highest betweenness until no edges left
        
        | edges | edge betweenness |
        |-------|------------------|
        - find subgraph by bfs for each node
        - calculate number of paths to each node
        - calculate edge values from leaf to root:
            - all nodes have value 1
            - edge has the sum of edges and nodes from leaf
            - needs to be divided by number of incoming paths

    - spectral clustering - graph partitioning
        - how to define and identify a good partition
            - maximize intra group connections
            - minimize inter group connections
            - represent with adjecency matrix
                - summarize on diagonal
                - use laplaccian matrix 
                    - meaning set non-diagonals to 0 for non-connections and -1 for connections
                    - trivial eigenpair ?


# hadoop vs spark
- hadoop
    - slow as it runs operations on disk
    - cheap, open source
    - requires more disk memory
    - can run on normal computers
    - fault tolerant because of replication
        - splits each file into blocks and replicates multiple times across machines
    - batch processing using MapReduce
        - reads data from cluster, performs operations on data, writes results back to cluster
    - MapReduce has no interactive mode and is complex
        - needs to handle low-level APIs meaning lots of code
    - developed in Java, used with Python, R, C++
    - highly scalable, highest node cluster is 42k+ using enterprise computers
    - security
        - supports Kerberos and LDAP for autentication
        - supports access control lists (ACLs) and a traditional file permissions model
    - uses Mahut for processing data and building models
    - Samsara, a Scala-backend DSL language can be used for in-memory agebraic operations and allows users to write own algos
    - mapreduce is dependent on an external scheduler


- spark
    - runs 100x faster in memory, 10x faster on disk
    - open source
    - requires lots of ram to run in-memory
        - increases cluster size and cost
    - uses RDDs - fault tolerant element collections able to operate in parallel
    - batch, real-time, and graph processing of data
        - reads data from cluster, performs operations on data, writes results back to cluster
    - user friendly APIs for different languages
        - 100 lines of mapreduce -> potential oneliner
    - interactive mode and provides intermidiate feedback for queries and actions
    - developed in Scala, used in Python, R, Java
    - scalable with highest node cluster is 8k
    - sparse security
        - authentication via passwords
        - can use HDFS ACLs and file-level permissions if ran on HDFS
        - can run on YARN, giving it capability of uing Kerberos authentication
    - built-in ml library that can be used for classification and regression
        - can also build ml pipelines with hyperparameter tuning
        - faster
    - has its own scheduler
