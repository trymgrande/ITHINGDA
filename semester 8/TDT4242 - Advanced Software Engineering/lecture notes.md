# django tutorial


# requirements engineering
- requirements
    - requirement
        - system description of services and constraints
        - high to low level
        - dual function
            - proposal for contract - open
            - basis for contract itself - detailed
    - stakeholders
        - people with interest
            - end users, system managers, system owners, external stakeholders
    - functional
        - system services
        - how system acts
        - implementation
            - system properties: reliability, response time, storage requirements
            - constraints: I/O device capability, system representations etc.
    - non-functional
        - constraints on timing, development process etc.
        - often applied to whole system rather than features
        - implementation
            - critical
            - affects architecture as a whole
        - ![](images/2022-01-24-16-29-44.png)
    - non-function requirement can generate multiple functional requirements
    - generate requirements
        - elicitation
        - analysis
        - validation
        - management
        - ![](images/2022-01-24-15-40-26.png)
    - agile
        - requirements, design, code, test, analysis, repeat
- requirement elicitation
    - discovery
    - work with customer
    - e.g. domain, services, operation (performance, hw) etc.
    - involves stakeholders
    - problems
        - stakeholders (what they want, conflicts, changes)
    - process
        1. discovery (stakeholder interaction)
        2. classification/organization
        3. prioritization (conflict resolution)
        4. specification (documentation)
- goal oriented requirement engineering (gore)
    - model
        - down: how
        - up: why
    - sub goals
    - soft vs hard
- quility of requirements
    - complete
        - all situations must be covered
    - unambiguous
        - only one interpretation
    - consistent
        - no conflicting requirements (rea-world objects, actions' logic, different terms for same object)
    - correct
    - verifiable
        - easy to test
    - traceable
        - follow life of a requirement
    - ranked for importance/stability
        - essential, conditional, optional
    - modifiable
        - changes can be made easily, completely, consistently
    - no forward reference
- conflicts in software requirements

- QUALITY OF REQUIREMENTS
    - ambiguity, inconsistency, forward referencing, opacity, noise
    - noise is undefined in domain
    - opacity is undefined relation
    - inconsistency: conflict between requirements
    

# devops gest lecture



# 5 - control flow and data flow testing
- Industry standards
    - IEEE covers general software verification/validation
    - IEC covers general safety software
    - ISO covers automotive software
        - ASIL (automotive system integrity level (A/B/C/D))
            - severety/probability/controllability of event
        ![](images/2022-04-08-13-48-15.png)
- test coverage
    - statement
        - reported by gitlab
    - decision
        - true if *all* if/else branch executed (0/100%)
        - branch    
            - counts *each* if/else branch executed (continous %)
    - path
        - all possible paths should be tested
    - white-box
        - generate control/data flow graph first
        - use test inputs to achieve different coverages
        - path coverage more difficult
            - mccabe's cyclomatic complexity (v(G) to find how many test cases are sufficient by counting paths
                - TODO learn P (connected components)
                - loops mean graph is not directed acyclic -> infinite paths
            - decision table to systematically create test cases
        - data flow
            - all definitions
                - every definition of every var used
            - all computational/predicate use
                - path from every definition to every c/p use
            - all uses
                - path from every definition to every use
    - mutation testing
        - coverage by seeded faults
            - insert faults and use ratio of found faults to estimate remaining real faults
        - where and how to seed faults
            - use old faults / db as seeds
        


# 6 - blackbox testing
- locate all inputs/outputs and design test cases to cover them
- equivialence class testing
    - testing normal values
- need to avoid combination explotion of variables
- boundary value testing
    - min max, nom. values
    - single/multi
- robust boundary testing
- n-way test
    - make sure at least n variable combination is included
    - trades combinations for less test cases
- all-pairs combination testing
- the challenge
    - number of input combinations is explosive
- testing approaches
    - equivalence classes, boundary values
    - decision tables, pairwise testing
    - error guessing / risk-based
- domains
    - single variable
        - linear/non-linear
        - string
        - enumerated
        - multidimensional
        - partitioning
            - partition each variable into equivalence classes based on identified risks based on domain
    - boundary value testing
        - because errors tend to occur near extreme values of input
            - min/max/nom
            - special values (non-numbers, negative)
            - edge cases e.g. 29. feb

    - multiple variables
        - need to test combinations in case of special interactions
        - usually only need combination of 2 variables
        - n-way 
        - all-pairs
        - in paramaeter order
            - initialization phase
            - horizontal growth phase
            - vertical growth phase  
- error guessing / risk based
    - experience based
        - different fault types
        - probability of the faults
        - consequence of the faults
    - bach's risk-based testing
        - bach's heuristics is based on his testing experience
        - risk analysis approaches
            - inside-out: what can go wrong here?
            - outside-in: what things are associated with this risk type?
        - identified
            - generic risk list - important things to test
            - risk catalogue - frequent fault in specific domain
- output coverage and testing
    - problem is different detail levels 
        - e.g. printout for specific user will be unique
- summary
    - domain of input/output vars
    - derive test case from single/multiple domain vars
    - applies to
        - unit test
        - integration test
        - system test
        - acceptance test

# 7 - integration, system, and acceptance regression tests
- v-model
    - ![](images/2022-05-25-09-40-16.png)
- integration testing
    - testing interfaces between components
    - need documents to know how components should interact
    - not many tools
        - relies on system design
    - strategies
        - decomposition-based
            - relies on function decomposition trees
            - top down / bottom up
            - incremental approach
        - call graph-based/interface matrix
            - call graph 
                - pair-wise
                - neighborhoods integration
                    - measures node (system) depth from root (main)
                    - neighborhood table
            - system x system matrix
                - mark true for systems that interface
                - peer-to-peer test
        - path based
            - similar to coverage unit test
            - focus on unit integration
            - all paths from root to all nodes
- system tests categories
    - covered in non-functional requirements lecture
        - functionality
        - reliability
        - usability
        - performance
    - robustness
        - tolerates wierd inputs from user / faulty system component
    - scalability
        - identify how system can scale 
        - check load per server in order to calculate how many servers will be needed
    - stress
        - max load e.g. black friday
        - knowing worst case is hard
    - load and stability
        - 24/7 stable service e.g. cloud
        - apache jmeter is used for load testing by ddosing
- acceptance testing
    - user testing to see if requirements/needs are met
    - typical forms of acceptance testing
        - user
            - performed by end user
            - not based on requirements, as that would be requirement testing
        - operational 
            - ready to operate?
        - contract and regulation
            - test against standards using checklists
        - alpha and beta (or field)
            - alpha
                - developers' sites by internal staff before customer release
            - beta
                - field testing at customers' sites before full customer release
    - testing in agile
        - functional/acceptance/customer test terms used interchangeably
        - referring to user stories
- regression testing
        - types
            - corrective
                - no requirements change
            - progressive
                - requirements change
    - revalidation
        - if test cases are obsolete
            - changes in specification
            - code modifications
            - code structure changed
    - case selection
        - only consider corrective regression testing in curriculum
        - retest all can be costly and/or irrelevant
        - random selection does not ensure coverage
        - selecting modification traversing tests
            - safe regression test selection
            - technique that keeps all tests that cover changed code
            - helps catching cases, but not selecting/updating
                - selecting/updating is manual
        - general selection process
            - establish traces between program and test set
                - execute program and record entities while running tests
            - compare old code to new code to find differences
                - identify code changes
            - select test cases that traverse the changed program and impacted entities
        - firewall approach
            - identify classes changed
            - identify descendants of changed classes
            - identify classes that communicate with changed class
            - this means that components surrounding a changed component are checked 
    - minimization
        - reduce number of safe selections
            - if code or inherited code is already covered, we don't cover it again
    - case prioritization
        - prioritizes cases using ranking, depending on time/budget
        - keeps all safe cases
    - automated regression testing
        - click-based end-to-end

# 8
## part 1 - testing planning
- layes foundation for testing in terms of coverage, testing requirements, budget etc.
- 






## part 2 - testing execution




# summary
<!-- Requirement module
| Description             | Students should be able to        | To read                                        |
|-------------------------|-----------------------------------|------------------------------------------------|
| Requirement elicitation | • Apply goal oriented RE approach | • Ian Sommerville (2016), Software Engineering (10th ed.), ISBN 978- 0133943030, chapter 4 • Paper 1: Goal-oriented RE A guided tour|
|Requirement quality|• Identify quality issues of requirements and fix them• Define different function and non-functional requirements|• Paper 1: IEEE Recommended Practice for Software Requirements Specifications Std 830-1998 • Paper 2: Glinz,M. (2007). On Non-Functional Requirements. 15th IEEE International Requirements Engineering Conference (RE 2007), 21–26. • Paper 3: Ontology-Driven Guidance for Requirements Elicitation • Paper 4: Bidirectional requirement traceability| -->

![](images/2022-06-01-10-21-46.png)
- Apply goal oriented RE approach
    - see gore diagram from exam 2021
- Identify qualityissues of requirements and fix them
    - see exam 2019
- Define different function and non-functional requirements
    - see ex. 1
![](images/2022-06-01-10-22-04.png)
- Explain code, decision, path, and output coverages
    - 
![](images/2022-06-01-10-22-16.png)
![](images/2022-06-01-10-22-28.png)
![](images/2022-06-01-10-22-37.png)
![](images/2022-06-01-10-22-46.png)
![](images/2022-06-01-10-22-54.png)

