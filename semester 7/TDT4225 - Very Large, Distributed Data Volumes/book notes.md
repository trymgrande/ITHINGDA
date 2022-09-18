### *These notes are from [this](https://youtube.com/playlist?list=PL4KdJM8LzAMecwInbBK5GJ3Anz-ts75RQ) youtube playlist, along with [these](https://docs.google.com/presentation/d/1UMDZtvgYUksip_Jx1nzAuDYAPeaoB4heYZr6gbyjvb8/edit#slide=id.g8bf0aaecfb_0_8) slides.*

# 1 - reliability, scalability, maintainability
- data intensive application needs different tools:
    - storage (db)
    - cache
    - keyword search (search indexes)
    - async process communication (stream processing)
    - periodically process accumulated data (batch processing)
- result:
    - reliable, scalable, maintainable data systems

- thinking about data systems
    - different tools stitched together using application code
    - problems:
        - ensure correct data, even when things go wrong
        - ensure performance, even when parts of system are degraded
        - scale to handle load
        - designing good API for given service
        - designing:
            - legacy dependencies
            - time scale for delivery
            - risk tolerance
            - regulatory constraints
        - most important problems
            - reliability
                - continue to work correctly, even during hw/sw/human faults
            - scalability
                - as system grows (data, traffic, complexity), system should be scalable to adapt
            - maintainability
                - easily maintained by different people

- reliability
    - faults can lead to failiure
    - tolerating (error handling) or preventing faults (testing) in order to prevent failiure
    - types of faults:
        - hw
            - add redundency component (raid, ram, cpu, power)
            - recently, systems can tolerate loss of entire machines, making rolling upgrade possible
        - sw
            - harder to predict (edge cases) and often cause multiple faults
        - human
            - leading cause
            - restrict critical access
            - test everything
            - allow quick and easy recovery
            - monitoring
        - no unauthorized access
        - chaos testing (simulating failiures)
        - full machine failiures
        - bugs - automated tests
        - staging/testing env.
        - quck roll-back

- scalability
    - describing load (load parameters)
        - requests/s (peak, avg)
        - reads/writes to db (peak, avg)
        - concurrent active users
        - hit rate on cache
    - e.g. twitter caches tweets at write, instead of requesting all for every read, resulting in lower average load
        - drawback is expensive write for every tweet, especially for users with high follower count
        - followers per user (/tweets posted) is a load parameter
        - twitter actually uses a hybrid where celebrities' tweets are fetched on read
    - describing performance
        - throughput, latency (how long the request is waiting to be handled on the server), response time (what the user sees)
        - using mean rather than average to compute percentile response time
    - approacehes for coping with load
        - scaling up - more power: simpler, scaling out - more machines: cheaper
        - type of architecture depends on type of system
    - response time vs throughput
    - end user response time is network and system (90th%)

- maintainability
    - main cost
    - minimize maintenance pain
        - operability: easy to operate
            - keep it running smoothly by monitoring system health
        - simplicity: managing complexity
            - easy for new engineers by removing complexity e.g. using abstraction
        - evolvability: making change easy
            - prepare for extensions/modifications to system
            - updates, new features, new platforms, new platforms
            - agile (TDD, refactoring)


# 2 - data models (techniques, architectures, algorithms for RSM)
- relational model
- document model
- graph based model
- model and querying go hand in hand



# 3 - storage & retrieval
- transaction processing
    - OLTP (online transaction processing database)
        - optimized for latency
        - flexibility
        - e.g. mysql, oracle
        - row based (for end users)
    - OLAP (online analytical processing database)
        - optimized for data crunching
        - data warehousing
        - column oriented (for non end users)
        - e.g. hbase, hive, spark

- storage engines
    - optimized for fast disk storage
    - phonebook is example of storage/retrieval
    - db index
        - secondary structure (phonebook index) derived from primary data
        - optimize for reads/writes depending on use case
        - file format (encoding)
        - deleting records
        - crash recovery (write ahead log)
        - partially writte records (checksums)
        - concurrency control (overwriting, atm withdrawal exploit)
        - range queries (cities between latlongs)

    - log-structured 
        - lsm-trees (log structured merge tree) e.g. SSTables -> HBase, Cassandra
            - compaction (background process)
            - merges keys together, only need to store most recent
        - key-value-pairs are sorted on keys
        - getting popular

    - b-trees
        - most widely used
        - only one key, at one place
        - key-value pairs also sorted by keys
        - more hw optimization
        - optimized for reads
        - RDBMS
        - can optimize by connecting leaves for seq reads
        - has to write at least twice
            - to write-ahead log
            - to tree page itself
        - overhead from writing entire page at a time
        - When reading data later, a sparse index is used to index each segment.
        - Bloom filters is a data structure for preventing infinite search when looking for non-existing entries.

    - many databases use both

- index
    - clustered index - inline storing of row values
    - secondary index - helps with joins
    - covering index - few columns are included
    - multi-column index - multiple keys concatenated
    - IR
    - in memory stores

# 4 - agile code evolution & data encoding
- rolling upgrade
    - canary - small subset of users beta testing
    - need multiple versions
    - backward/forward compatibility
    - data is encoded (serialized) in schema determined by version
        - eg. json - unoptimized for size, but readable
        - make sure compatibility is not broken

# 5 - replication
- intro
    - syncing between dbs
    - stepping in for other dbs during downtime
        - move users to other machine
    - locality for users for latency
    - scale to more users
- leadership
    - single leader
        - leader takes writes, replicas replicate
        - only replicas take reads to optimize load
        - sync vs async replication
            - should user get ack before data replication?
            - if so: faster but less durability
            - replication lag is replicas being behind
            - replication topoligy is who is responsible for replicating
        - cons:
            - leader re-ellection on downing
        - multiple tools and algos between leader/replica

    - multi-leader
        - multiple can now take writes
        - usually different data centers
        - cons:
            - writes need to sync between leaders
                - e.g. user can withdraw same money from leaders before sync
            - which write is first?
            - syncing after outage at one leader

    - leaderless
        - client application needs to 
            - read and write to all
            - resolve conflicts
        - e.g. amazon dynamo
        - resilliant
        - conflict resolution is harder
        - read after write concistency
            - should get same return as was just written
        - monotonic reads
            - read should be same order as writes
        - consistent prefix of reads
            - want to order reads/writes sequentially
        - conflict algorithm
            - e.g. last writer wins
        - eventual concistency
            - time until

# 6 - partitioning
- intro
    - why
    - how it impacts indexes
    - request routing to right replica/db
    - same as sharding/splitting
    - necessary for horizontal scalability
- why
    - routing layer directs user to correct node (machine)
    - hot spots
        - e.g. popular users need skewed nodes
        - key-hashing can be used for partitioning

- secondary index
    - structuring of pks
    - local index
        - every node tracks own changes
        - heavy read because need to search for occurrence
        - only write to one partition
    - global index
        - global node tracks all nodes' changes
        - easy reads because it knows all locations
        - heavy writes because needs to search for changes
    - need rebalancing strategies for hot spots
     
- routing
    - routing layer know where to send user req
    - routing layer in client
    - routing info in each node
        - req goes to random node, then redirected to correct node
    - routing layer obsolete with leaderless


# 7 - transactions
- intro
    - issues
        - memory, disk, network, application, concurrncy
    - tell user to retry
    - tell if transaction succeeded or not
    - acid
        - atomicity - all or nothing (abortability)
            - e.g. booking - all criteria need to go through (seat, food..)
        - consistency - foreign key constraints 
        - isolation - serializability (concurrency)
        - durability - if db says value stored, actually gets stored
    -  weak isilation levels
        - read committed
            - no dirty reads/writes
        - snapshot isolation - repeated reads
            - db only returns new value after transaction is committed
        - no lost updates (overwrites)
        - no write skews (premise false)
        - no phantom reads (read invalidated)
                - premise is invalidated, need to retry
                - read/written data turns out was not true
        - serializability 
            - actual serialization (single thread)
            - two phase locking
                - pessimistic locking
                - everyone waits until single user finishes booking
            - serializable snapshot isolation 
                - optimistic locking - abort if not serial (premise falsified)

# 8 - troubles with distributed system
- distributed
    - shared nothing
    - uses commodity hw over network
    - cloud
    - way to go
    - low cost
    - can scale up
    - low latency because of geographical locality
    - undeterministic because of communication
    - need to simulate failiure because of murphy's law
    - more complex
    - machines can go down
        - need fault detection and recovery
    - opportunistic throughout
        - fast, but can cause problems
    - network faults are unreliable
        - often humans
        - fault detection
            - load balancer stops sending req to dead node
            - need new leader if it dies
        - feedback
            - os will close connection on process crash
            - if node's os still runs, can notify other nodes about crashing
        - timeout
            - tradeoff between early and late death declaration
    - network queing
        - network switch sends packet to correct port
        - new requests are queued up if cpu busy
        - hard to predict network delay because of traffic

- monolyth 
    - shared everything
    - one big machine
    - hard to scale
    - expensive
    - deterministic software
    - uses custom hardware
    - limitation of power, capacity, etc.
    - limited upgrades
    - non-opportunistic
        - slow, but reliable

- unreliable clocks
    - sync and accuracy
        - quartz go out of sync (drifts 17s/d)
            - need to sync over network (ntp)
                - depends on network round trip
    - time-of-day clocks
        - wall-clock
    - monotonic clock
        - measures time interval
    - logical clock
        - ordering range confidence interval
- process pause
    - machines wait for each other
    - stop the world to do gc
    - ctrl-z
    - heart beat checks if all systems are alive
    - if node dies, need to replace it
    - need to handle lock on process pause
- knowledge, truth, and lies
    - only message communication over network
    - truth defined by majority
    - node cannot trust itself
    - quorum: voting among nodes - consensus algorithm
    - byzantine faults: nodes lying


# 9 - consistency and concensus
- example
    - two simultanious withdrawals from same account
- real life
    - apache zookeeper
        - consensus algo that provides linearizable
        - guarantees:
            - leader is the leader etc.
            - ordering
            - failiure detection
            - hearthbeat
            - node work partitioning
            - ...coordination
- consistency guarantees
    - replicas are inconsistent
    - but eventually consistent
    - strong guarantee systems may have less performance/tolerance
    - need cordination about replica state in delay/fault cases
- linearizability
    - ordering
        - row must be created before updating
        - need to keep track of ordering (causally consistent)
        - linearizability: total order of operations
        - need to order events using sequence numbers/timestamps
    - idea is appearence of one data copy with atomic operations
    - recency guarantee on reads and writes
    - implementation
        - implementation of serializability using 2PL or actual serial execution
        - all nodes must agree which node (leader) owns lock
        - allows for constraints like uniqueness
        - replication methods
            - single-leader (potentially linearizable)
            - consensus algo (linearizable): zookeper and etcd
            - multi-leader (not linearizable)
            - leaderless (probobaly not linearizable)
    - cons:
        - network interruption forces unavailability
    - network delays
        - response time proportional to network delay variance
        - dropping linearizability gives higher performance


- serializability
    - guarantees transactions behave same as if executed in serial
- two phase commits (2pc)
    - prepare and commit step
    - prepare: cordinator asks participants (dbs): are you ready for my data?
    - commit (phase 2) if all participants say yes, else coordinator aborts
    - costly because of disk forcing (fsync) for crash recovery and network round trips
- fault tolerant consensus
    - node(s) propose values, consensus algo decides one of the values to be adopted by all nodes
- epoch numbering and quorums
    - algos guarantee unique leader each epoch
    - leader ellection on death, decided by highest epoch number
- limits of consensus
    - algos were a breakthrough, but are costly
    - require majority: network failiure may be problematic
    - rely on timeout to detect failed nodes, but network time is problematic
    - RAFT: if one network link is problematic, algo may alternate leaders
- membership and coordination services
    - zookeeper and etcd hold small amounts of data that fit in memory
    - zookeeper (modelled after google's chubby lock service)
        - runs on fixed number of nodes (3-5)
    - membership service determines active/live members of cluster
- 

- lamport timestamp
    - no physical clocks
    - every client tracks counter, increases and passes it every request
    - can see logical time between different dbs
    - can be used to check wether or not to say yes to prepare

- fidelity - read your own writes to avoid user posting twice
- split brain - two leaders elected by accident
- need to understand guarantees


<!-- 
# 10 - batch processing 
- intro
    - data derived from single truth source
    - how index is buildt
    - how analytic systems work
- unix batch processing
    - piping linux commands to find log stats
    - map reduce system
        - mappers
            - filtering out
        - reducers
            - summarizing
        - integrated into dbs allows inclusion of arbitrary code
            - large machine scale can be utilized with fault tolerance
        - need join algo
- beyond map reduce
    - pig/hive abstraction (like sql)
    - efficiency improvement
        - spark
        - improves map reduce system
        - allows stream support
        - no unneccessary sorting
        - map reduce chaining gives excess temp data
        - spark has single flow without data
        - no new jvm per task
        - resilient distributed dataset
- hadoop ecosystem
    - ![](images/2021-12-06-14-05-03.png)

# 11 - stream processing
- intro
    - unbounded data keeps coming
    - streaming solves problem
    - near real time application processing
        - fraud detection
        - fault detection
- stream system architecture
    - producer - user that adds to cart (event)
    - broker processes events (stream processor)
    - consumer handles events (async)
- comparisons
    - latency vs throughput
    - storm, flint, samza, spark
    - difference of microbatching, caching, etc.
- concepts
    - comparisons
        - latency, throughput, ordering, backpressure, elasticity, state management, strictness delivery
        - polling vs notification
        - streaming allows multiple views of same data
        - log knows entire history of db and events
        - micro batching, idempotent writes, checkpointing, transa

# 12 - future of data systems
- overview
    - ![](images/2021-12-06-15-52-44.png)
- evaluate data flow
- design around company policy
    - e.g. airlines overbooking
- handle errors correctly in the entire system for the user 
- understand the user
- avoid privacy/tracking
- unbundling dbs
    - individual components piped together


 -->
