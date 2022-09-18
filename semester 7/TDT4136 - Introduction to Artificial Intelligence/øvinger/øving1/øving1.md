##### 1. Define artificial intelligence (AI). Find at least 3 definitions of AI that are not covered in the lecture.
* The theory and development of computer systems able to perform tasks normally requiring human intelligence, such as visual perception, speech recognition, decision-making, and translation between languages.
* Artificial intelligence refers to the simulation of human intelligence in machines that are programmed to think like humans and mimic their actions.
* Artificial intelligence is a technique used to give computer programs the abitlity to construct a response that is as intelligent as possible. 

##### 2. What is the Turing test, and how it is conducted?
The turing test is a test for determining the degree of which an AI can behave as intelligently as a human. It is conducted by a human asking for a response from both a human and an AI. If the human asking can not tell which is which, then the AI will pass the turing test, but fail otherwise.

##### 3. What is the relationship between thinking rationally and acting rationally? Is rational thinking an absolute condition for acting rationally?
Thinking rationally means to logically reason a way into an action, while acting rationally can simply be to mimic behavior without knowing the underlying reason for the action. Therefore, rational thinking is not a condition for acting rationally.

##### 4. Describe rationality. How is it defined?
Rationality is to be in order with logic and/or reasoning - meaning actions are thought out. A rational agent is one that acts so as to achieve the best outcome or, when there is uncertainty, the best expected outcome. A rational agent can be described as the following:
For each possible percept sequence, a rational agent should select an action that is expected to maximize its performance measure, given the evidence provided by the percept sequence and whatever built-in knowledge the agent has. This rationality has to be done completely autonomously.

##### 5. What is Aristotle’s argument about the connection between knowledge and action? Does he make any further suggestion that could be used to implement his idea in AI? Who was/were the first AI researcher(s) to implement these ideas? What is the name of the program/system they developed? Google about this system and write a short description about it.
Aristotle argued (in De Motu Animalium) that actions are justified by a logical connection between goals and knowledge of the action’s outcome. Meaning that in order to achieve a goal, you put your knowledge into the necessary action.
In the Nicomachean Ethics (Book III. 3, 1112b), Aristotle further elaborates on this topic, suggesting an algorithm that was implemented 2300 years lateer by Newell and Simon in their **General Problem Solver** program. We would now call it a greedy regression planning system. Greedy algorithms depend on dividing a problem into smaller subproblems that can be solved more easily, and combining them to find a solution. Methods based on logical planning to achieve definite goals dominated the first few decades of theoretical research  in AI.

##### 6. Consider a robot whose task it is to cross the road. Its action portfolio looks like this: look-back, lookforward, look-left-look-right, go-forward, go-back, go-left and go-right.
###### (a) While crossing the road, a helicopter falls down on the robot and smashes it. Is the robot rational?
Yes, because the robot was not able to "look-up" and check for the helicopter.

###### (b) While crossing the road on a green light, a passing car crashes into the robot, preventing it from crossing. Is the robot rational?
No, because the robot could have looked to check both sides of the road before crossing.

##### 7. Consider the vacuum cleaner world described in Chapter 2.1 of the textbook. Let us modify this vacuum environment so that the agent is penalized 1 point for each movement.
###### (a) Can a simple reflex agent be rational for this environment? Explain your answer
It depends. Firstly, its intelligence will be limited due to not knowing its current position in the environment or its last move, which can lead to unnecessary movement or even infinite loops where the robot constantly moves right from B or left from A.
Secondly, it depends on the reward given for cleaning the tile. Knowing this, the agent needs to only execute an action if the net performance measure is positive. 

The problem is that the agent cannot evaluate wether it will move the right direction or not. A solution to this would be to randomize the direction it moves, meaning it would be right 1/2 times. However, there is still no guarantee that the tile it just moved to actually is dirty. This would mean that in order to achieve a net reward gain, the following expression would have to be true for situations where the current tile is clean:
`(1/2 * reward_for_cleaning) > (probability_of_other_tile_being_clean * penalty_for_moving)`
Otherwise, it would have to stand still. In other words, the risk has to be worth the reward for it to move over.

So, if the agent already *knows* about the structure of the environment (that there are no more than 2 tiles - left and right), the penalty value, the reward value, and also a constant probability of a given tile being dirty, it can still be rational by taking this into consideration by randomizing movement, and calculating risk vs reward. If it does not know all this, then it can't be rational. 

###### (b) Can a reflex agent with state be rational in this environment? Explain your answer.
Yes it can because it can definitely determine the net reward simply by observing the previous states and acting accordingly.

###### (c) Assume now that the simple reflex agent (i.e., no internal state) can perceive the clean/dirty status of both locations at the same time. Can this agent be rational? Explain your answer. In case it can be rational, design the agent function.

Referring back to exercise (a), the expression would now be without the unknown probability of a clean tile. Additionally, it can temporarily determine which one it is inside when one tile is clean and one is dirty. When both are clean, the robot does not have to move, and when both are dirty, it can suck the current tile, disregarding which one it is. This deduction eliminates the 1/2 factor from the equation, leaving us with the following:
`reward_for_cleaning > penalty_for_moving`

The agent would now be able to be rational as long as it knows about the structure of the environment, the penalty value, and the reward value.

Agent function:

| Percept sequence:                    | action: |
| ------------------------------------ | ------- |
| [A: clean, B: dirty, current: dirty] | left    |
| [A: clean, B: dirty, current: clean] | right   |
| [A: dirty, B: dirty, current: dirty] | suck    |
| [A: dirty, B: clean, current: dirty] | suck    |
| [A: clean, B: clean, current: clean] | nothing |
| [A: dirty, B: clean, current: clean] | nothing |


##### 8. Consider the vacuum cleaner environment shown in Figure 2.2 in the textbook. Describe the environment using properties from Chapter 2.3.2, e.g. episodic/sequential, deterministic/stochastic etc. Explain selected values for properties in regards to the vacuum cleaner environment.
**FULLY OBSERVABLE VS. PARTIALLY OBSERVABLE:**
This environment would now be considered **fully observable** because it knows what to do in every situation. Although it does not know its position in the cases of [A: clean, B: clean, current: clean] and [A: dirty, B: dirty, current: dirty], this does not factor in on its decision anyways.

**SINGLE-AGENT VS. MULTIAGENT:**
This environment is a single-agent environment as there are no other agents present (unless the dust particles actually are tiny agents).

**Deterministic vs. nondeterministic:**
The environment is **nondeterministic**, as we know the possible outcomes, but it is uncertain about the probability for dust to appear at a given time. So cleaning and moving is deterministic (given that the robot never malfunctions) because we know that the current tile will be clean after sucking. And we know where we will end up after moving a given direction, but the unsertainty comes from the randomization of dust appearing. However, it is not stochastic, as we know the different possible outcomes.

**EPISODIC VS. SEQUENTIAL:**
Environment is in this case **sequential** because choosing not to clean a tile this episode, will lead to the same tile being dirty the next episodes until it is cleaned.

**STATIC VS. DYNAMIC:**
This environment would be classified as **dynamic** as the tiles might get dirty while the agent is "thinking" about its decition.

**DISCRETE VS. CONTINUOUS:**
The environment is discrete as there are a finite number of states, percepts, and actions possible.

**KNOWN VS. UNKNOWN:**
In this case, all the "rules" of the environment is **unknown** to the agent. This is because things like the probability of dust appearing in a given tile at a given time, the performance measure, as well as the penalty is unknown. In addition, there could be more unknown rules about the environment, such as a failiure rate for the vacuum itself.

##### 9. Discuss the advantages and limitations of these four basic kinds of agents:
###### (a) Simple reflex agents
![](./assets/../notater/AI/reflex_agent.png)
**Pros:**
- Can funtion without a state as it does not depend on the location in the environment.
- Reduced possible percept/action situations as it only uses the current percepts and does not need to look at states.
- Used even in complex agents where a condition-action rule is needed.
- Simple to implement.

**Cons:**
- Has no state.
- Limited intelligence.
- Can introduce infinite loops where it tries to constantly move into a wall because it does not have a state to store its location (randomization of movement can be used here to get unstuck).
- Less flexible.


###### (b) Model-based reflex agents
![](./assets/../notater/AI/model-based_reflex_agent.png)
**Pros:**
- Can use inernal state to model unobserved parts of environment:
    - Effects caused by agent's actions.
    - Environment changes independent from agent's actions.
    - How sensors change (e.g. camera lens getting wet).

**Cons**
- More complex to implement.
- Requires the use of states.
- Less efficient.

###### (c) Goal-based agents
![](./assets/../notater/AI/goal-based_agent.png)
**Pros:**
- Can know what to do in situations where the decision is not straight forward (e.g. search and planning).
- More flexible for finding solutions.

**Cons:**
- Less efficient.
- More complex.

###### (d) Utility-based agents
![](./assets/../notater/AI/utility-based_agent.png)
**Pros:**
- Internalization of the performance measure (e.g. compares different routes or selects speed/safety tradeoff correctly).
- More intelligent than goal-based because it can maximazes performance measure.
- Uses utility function to measure preferred state in the world.

**Cons:**
- Very complex, even unachieveble.
- Requires correct utility function.