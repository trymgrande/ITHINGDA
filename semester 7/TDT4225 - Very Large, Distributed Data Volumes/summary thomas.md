# 1
hdd
ssd
gc
lsm
b+
leveldb
rocskdb

# 2-4
merging + compaction (hashmaps with stables)
sstables + lsm
b+
indexing
in-mem hashmap
oltp vs olap
data warehousing
- hw-close optimizing
- pre-computing
data cubes / olap cubes
evolvability - fw/bw compatibility
encodig/decoding
schema evolution
dataflow

# 5 - replication
distributed data
- scalability
replication
- fault tolerance
- latency
- leaders
- sync/async replication
hw sharing
takeover
- need leader checking to avoid split-brain
statement based replication logs (insert, update, delete)
- nondeterministics (now, rand) won't work
write-ahead log-shipping
logical (row-based) log replication
trigger-based replication
replication lag - read-your-writes
multi-leader replication
- better optimized
- need conflict resolution
multi-leader
- write propagation paths
leaderless
- read repair using quorums
- sloppy quorums + hinted handoff
- application side recovery

# 6 - partitioning
partitioning
- mainly for scalability
range partitioning
hash partitioning
- good load balance, no range scans
local / global index
rebalancing partitioning
dynamic partitioning
request routing
coordination of services (e.g. gossip protocol)

# 7 - transactions
crash recovery
concurrency
no partial data
race conditions
read committed
snapshot isolation
serializability
acid
single/multi object transactions
retries
isolation levels (concurrency control)
- read committed (low level)
- serializable isolation (low level)
snapshot isolation + repeatable read
update locking
compare-and-set
write skew
phantom
serializability
- stored procedures
2pl
- used by serializable isolation level in mysql, repeatable-read in db2
- overhead
- deadlocks
predicate locks
serializable snapshot isolation (ssi)
- conflict detection rather than recovery
- kicks in whenever old value is read
- no waiting for lock release
- not limited to single core

# 8 - the trouble with distributed systems
unpredictable
breaks
high performance computing
cloud computing
- breaks
no response
network faults
death declaring
eunreliable clocks
time-of-day
- wall-clocks
monotonic clocks
- duration
- local
time drift
hard to sync
false info
- failiures, clocks, process pause
- consensus often by quorums
fencing token
- preventing use of expired lock by incrementing lock number
byzentine faults
- 51% attacks
weak lying (bugs)
timing
- synchronous vs asynchronous (timing assumptions vs no timing assumptions)
node failiure
algorithm correctness
- uniqueness
- monotinic sequence
- availability

# 9 - consistency and concensus
consistency vs availability 
serializability using 2pl is also linearizability
linearizibility implies causality
timestamp ordering
total order broadcast
2pc
- write, prepare, commit

# 14 - time and global states
physical time
- timestamps
    - can derive event order, requires synced clocks
- logical time
    - ordering, no syncing
skew (offset)
drift (e.g. 5s/d)
utc
external sync
- christians algorithm
internal sync
- berkley algo
ntp
logical time
- event numbering
- cause effect
a happens-before b => L(a) < L(b), not opposite
vector clocks
- increment on events
check happens-before by tracing route
global states
- gc, deadlocks, debugging
cuts
- inconsistency - can't skip time
distributed debugging


# RAFT - consensus algorithm for replicated logs
single leader
log replication
- done by consensus module
- followed by state machine update
- 51% for updating state machine
    - fail-stop
symmetric, leaderless (not in use)
- equal roles
raft uses asymmetric, leader-based
- 1 leader, client talks to him
- simplifies
crash detection
hard to sync log after crashes (consistency is complicated)
state
- leader, follower, candidate
hearthbeats
- timeout if missing hearthbeats
election
- safety: max 1 winner
- liveness: min 1 winner (eventually)
log
normal operation
log consistency (challenge)
- follower's log must be updated to pull
leader change
commitment and election rules makes it safe
repairing follower log
killing old leader
client protocol
- forward req to leader
- if leader crashes before returning, uid is included in log
config changes
- cannot switch config
2-phase joint consensus

# dynamo - amazon's highly available key-value store
availability over consistency
services
- performance, reliability, efficiency, scalable
- reliability important for customer trust
reliability == availability
complex querying, isolation, consistency not needed
simple read/write queries
99.9th% customer performance
service level agreement
design
- many services with own priorities
- eventually consistant, but not always
always writable
incremental scalability
symmetry
decentralization
heterogeneity
interface
- get(key)
- put(context, key, object)
consistent hashing
- hash ring
replication
get/put execution
- generic load balancer
partition aware client library
durability tuning
- r - min readers
- w - min writers
handle failiure - hinted handoff
replica reconstruction
- merkle trees
membership / failiure detection
adding / removing nodes
big advantage: can tune n, r, w
client vs server-driven coordination
