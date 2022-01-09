---
title: thread synchronization
updated: 2019-04-21 15:50:39Z
created: 2019-02-27 09:25:59Z
author: trym.grande@gmail.com
---

- motivation
    - two threads write simultaniously - corruption or only one writes (race condition)
    - behaviour in non-deterministic because of other things running
    - compiler can reorder instructions
    - multi-word operations are not atomic (multiple operations inside)
- example
- why reordering
    - can reorder independent things
    - optimization impossible with spontaneous changes
    - keep cpu busy while writing to memory to save time - fixes memory speed gap (memory barrier)
- too much milk example
    - multiple operations cause problems
    - leave note before milk trip to avoid duplicate operation
- definitions
    - lock: leave milk note before critical section
    - critical section: shared data access
    - race condition: output of a concurrent program depends on order of
- too much milk solve #1
    - leave note (test value of register and set if not set) (test and set)
- too much milk solve #2
    - leave note before checking and writing note
    - can loop
- too much milk solve #3
    - use two extra variables
    - polling (cost cpu time)
- lessons
    - complicated
- roadmap
- lock
    - wait until lock is free, then take it
    - deadlocks are locks that will not be set free
- why only acquire/release
    - suppose we add a method for checking lock that returns true, will lock be free?
- rules for lock
    - acquire first, release after
    - do not pass on lock to other thread
    - never access shared data without lock
- condition variables
    - waiting inside critical section when holding lock
    - wait: atomically release lock
    - signal: wake up waiter if any
    - broadcaster: ....
    - condition variable design pattern
    - check reason we woke up is that we were signaled
- example bounded buffer
    - don't read when empty, don't write when full (wait instead)
- multicpu
    - ...
- spincocks
    - cpu waits in loop for lock to free
    - ...
- how many spinlocks
    - one spinlock per lock
    - one spinlock per core

-

- semaphores
    - limit the number of accessors for a specific resource (club bouncer)
    - wait and signal
    - useful for
        - avoiding overwriting buffer

        -

topics: locks, mutexes ,semophores, spinlocks, condition variables, why we use all mechanisms, which situation we use each?