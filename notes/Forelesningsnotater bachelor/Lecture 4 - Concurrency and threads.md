---
title: Lecture 4 - Concurrency and threads
updated: 2018-10-31 14:39:02Z
created: 2018-10-31 12:10:58Z
author: trym.grande@gmail.com
---

process: create entire address space
threads: use address space of existing process

Motivation

- multitasking
- threads help us multitask

why concurrency?

- servers need to handle multiple connections
- parallel programs for better performance with multicores
- gui programs
- network and disk work simultaneously
- word processor with thread for input/output/gui/spellcheck

Definitions

- thread is
    - a single excecution sequence
    - schedulable (os can suspend thread at any time)

threads in kernel and at user-level

- multi-threaded kernel
    - high privileged
- multiprocess kernell
    - multiple single-threaded processes
    - system calls access kernel data structures
- multiple multi-threaded user procecces

thread abstraction

- infinite cpus
- threads excecute with variable speed
    - therefore programs should be time flexible

programmers view vs processor view

- programmer sees instructions excecutet linearly
- processor sees scheduling between threads

 thread operations

- thread_create
    - create new thread to run func
- thread_yield
    - give up control
- thread_join
    - (in parent) wait for forked thread to exit
- thread_exit
    - quit thread and wakes joiner

fork/join concurrency

- threads can create children and wait for their completion
- data only shared before fork/after join - ensures independence and no sync problems
- ex:
    - web server: fork new thread for each connection
    - merge sort
    - parallel memory copy

thread data structures

- need tcb to save
    - thread state
        - stack info
        - registers
        - metadata
        - stack
- shared:
    - code
    - global vars
    - heap

-
thread lifecycle

- init
- ready
    - can be scheduled
- running
- thread_join -> waiting - > ready
- finished (thread exit call)

implementing threads

- thread_create
    - allocate tcb
    - allocate stack
    - stack frame for base of stack
    - put func, args on stack
    - put thread on ready list
- stub(func, args
    - call (*func)(args)
    - if return, call thread_exit

thread context switch

- voluntary
    - thread_yield
    - thread_join (if child is not done yet)
- involuntary
    - interrupt or exception
    - some other thread is higher priority

volantary thread context switch

- save registers on old stack
- swithc to new stack, new hread
- restore registers from new stack
- return
- exactly same with kernel threads or user threads

a subtlety

- thread_create puts new thread on ready list
- when first runs, some threads calls switchframe
- set up new thread's stack as if it had saved its state in switchframe

involuntary thread/process switch

- timer or i/o interrupt tells os other thread should run
- alternative
    - end of interrupt handler calls switch
    - when resumed return from handler resumes kernel thread or user process
    - thus, processor context is saved/restored twce (once by interrupt handler, once by thread switch)