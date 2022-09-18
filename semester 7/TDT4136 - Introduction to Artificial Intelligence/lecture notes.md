
# 2
- agents
    - performance measure
    - environment
    - actuators
    - sensors
- *"percieving its envoronment through sensors and acting upon them using actuators"*
- autonomous actions means ability to adapt to change in environment instead of doing the same thing as before.
- perfect rationality is only feasable in ideal environments
- rationality is often not a good model of reality (e.g. humans are bad at estimating probabilities)
- rationality depends on PEAS
    - performance measure
    - environment
    - actions that the agent can perform
    - persept sequence
- representation and reasoning is important
- different properties of environments:
    - SINGLE-AGENT VS. MULTIAGENT:
        - single: no other agents as part of env
        - multi: other agent's scores depend on my agent's actions. ex: prisoner's dilemma, game theory
    - Deterministic vs. nondeterministic:
        - deterministic: guaranteed outcome
        - stochastic: unsertain outcome
        - nondeterministic: known outcomes, but unknown probability for each
    - EPISODIC VS. SEQUENTIAL:
        - episidoc: action only affect current episode, simpler as of no need for thinking ahead
        - sequential: action affects all coming episodes 
    - STATIC VS. DYNAMIC:
        - dynamic: env might change while agent is making choice
        - static: env does not change
        - semidynamic: env does not change, but agent's score might. ex: chess
    - DISCRETE VS. CONTINUOUS:
        - descrete has finite number of states, percepts, and actions: ex: chess
        - continous flows through time
    - known vs unknown environment
        - strictly not a property of the environment, but rather the agent's or designer's knowledge about the laws of physics and rules for the environment
        - known: agent knows rules and evolvement of env, but can still be only partially known
        - unknown env: agent has to learn about the env through observation, and env can still be fully observable.
- cannot write perseption sequence for complex environment
- simple reflex agents: condition -> action (if infinite loops -> randomize to solve)
- different types of agents:
    - model-based relex agents
        - has knowledge about world
        - updates world view based on what actions do
    - goal-based agents
        - asks: what will it be like if i do action x?
        - less efficient than reflex agents, but more flexible
    - utility-based agents
        - looks at different ways of doing something and picks the best based on reward
    - learning agents
        - main subject in reinforcement learning
        - takes critique

# 3
- representation of states and transitions
    - atomic
    - structured
    - 
- search
    - never expand already visited nodes (expanded means visited)
    - every state has a state space
    - problem solving process
        - goal formulation
        - problem formulation
        - search 
        - execution
    - informed serach
        - A*, greedy
            - ![](./assets/Screenshot%20from%202022-08-08%2022-23-58.png)
        - admissible heuristic means it does not overestimate the cost from node to goal
    - uninformed search
        - goal check is when children are generated
        - DFS, BFS
            - ![](./assets/Screenshot%20from%202022-08-09%2022-19-45.png)
            - only optimal when the step costs are identical with each other
            - goal found immidiately on impact
    - frontier
        - priority
        - fifo - Breadth
        - lifo - depth
    - uniform cost search (cheapest first)
        - expand like bfs 
        - calculate cost to each node
        - then expand cheapest leaf
        - only expand goal if it is the cheapest leaf node
        - finished when goal expanded
        
    
# chap 4 + A* from chap 3 search in complex envs
- evaluation function f(n) = g(n) + h(n)
    - g(n) = cost so far to reach n
    - h(n) = estimated cost of the cheapest path from n to goal
    - f(n) = estimated cost of the cheapest solution through n to goal

# lecture 5 - adversarial search


# assignment lecture 2 - MiniMax Algorithm


# lecture 6 - chapter 5 - constraint satisfaction problems
- backtracking
    - brute force variable values until all constraints are satisfied.
- ac-3
    - add all arcs and invert them
    - add all arcs to agenda
    - remove impossible values from the left variable's domain using the next arc on the agenda
        - every value in domain of variable on left side of arc needs some value in domain of variable on right side of arc
    - remove the arc from the agenda
    - add to the agenda again all arcs that have the affected variable on the right side if the domain changed
    - 



# logic
- Validity:  If a term is valid it is a tauntology => always true
- Satisfyability: If a term is satisfiable it is sometimes true 
FOL extends prepositional logic by using quantified variables over non-logical objects. Rather than prepositions such as "Socrates is a man", one can have the expression "there exists x such that x  is Socrates and x is a man", where "there exists" is a quantifier, while x is a variable. This means prepositional logic is the foundation of first order logic.
- implication: a => b
    - ![](./artificial-practicing/Notes/Images/implication_table.png)
    - only false when a=1 ^ b=0