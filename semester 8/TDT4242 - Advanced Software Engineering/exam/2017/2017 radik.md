# Task 1 - Requirements engineering

## 1 Requirement quality 

* _ambiguity_ 
* _inconsistency_ 
* _forward referencing_ 
* _opacity_ 
* _noise_

| Requirement | Issue | Issue Explanation | Fixed |
|-|-|-|-|
| Req2 | Forward referencing | What is certain depth? | When the robot detects more than 1cm of snow on the ground, it should automatically start to move and clear the snow. |
| Req5 | Ambiguity | What is an obstacle? | The robot should be able to identify obstacles such as light poles, walls, cars, fence, etc. and avoid these obstacles. |
| Req6 | Forward referencing | What is an unsafe environment? And what is safe mode? | The robot should have a safe mode, in which the robot comes to a stop, when it is in an unsafe environment, which is when there are too many obstacles for the robot to avoid colliding with. |
| Req7 | Forward referencing | What is safe speed? | The robot should move at 3m/s|
| Req3 | Inconsistency | says that the user will have full control of the robot, but that the robot can still decide not to move, this means that the user does not have full control. | When the user presses the remote-control key, the user can take over and have partial control of the robot to be able to move it [...], the safety features of the robot are still active, and if the robot thinks it will crash into an obstacle by following a command, then the robot will not follow the command. |
| Req3 | Opacity | What is the remote key, what features does it have?| The robot should have a remote control with buttons for power-off, steering (right, left, forward, reverse), return to charging spot and turn on light. |
| Req1 | Noise | What information should be on the instrument panel? Where is it located?| The robot should have an instrument panel on top with battery information, and power-button.  |


## 3 Requirements prioritization

Cumulative voting is a type of requirement prioritization, in where the users or developers vote on which requirements/features they would like to see implemented. The requirements with the most votes should be prioritized by the development team. With this approach the result can be in any possible order: 

1. Req2
2. Req4
3. Req3
4. Req1
5. Req6
6. Req5

The binary priority list will prioritize the most important items:

1. Req2
2. Req4
3. Req5
4. Req3
5. Req1
6. Req6

# Task 2 - testing

## 1 - Domain testing

With 2-way combinatorial test we want to combine two variables. Since we want to do this for all variables the possible values are:

* Availability-Payment
  * <AVA,CC>, <AVA,GV>, <NIS,CC>, <NIS,GV>, <DIS,CC>, <DIS,GV>
* Availability-Delivery
  * <AVA,MA>, <AVA,UPS>, <AVA,FE>, <NIS,MA>, <NIS,UPS>, <NIS,FE>, <DIS,MA>, <DIS,UPS>, <DIS,FE>
* Payment-Delivery
  * <CC,MA>, <CC,UPS>, <CC,FE>, <GV,MA>, <GV,UPS>, <GV,FE>

| Availability | Payment | Delivery |
|-|-|-|
| AVA | CC | MA |
| AVA | GV | UPS |
| AVA | CC | FE |
| NIS | CC | MA |
| NIS | GV | UPS |
| NIS | CC | FE |
| DIS | CC | MA |
| DIS | GV | UPS |
| DIS | CC | FE |

## Regression testing

Our test-cases are:

* <x=1, y=2>
* <x=1, y=3>
* <x=3, y=1>
* <x=2, y=2>

With re-testable subset we only have to re-test the parts of the program where the code has changed. From the CFG we can select the cases where f1 is called (case 3 and 4). From this set we choose the case where b*b would be expanded (case 3 and4). So the subset we have to test is 

* <x=2, y=2>
* <x=3, y=1>

## Data flow testing and test coverage

With the AU strategy we want to find all paths from definition to usage of the definition: <def, usage>

* x: <1,2>, <1,8>
* y: <1,4>, <1,7>, <1,9>, <9,7>, <9,9>
* z: <6,8>, <8,8>, <6,10>, <8,10>

# Task 3 - advanced topics

## OSS

* Free as libre
* Free as in free to use
* Non free as in not free 

## 3.2

> Explain what “operating system-centric” software ecosystem is (1 point) and list its success factors (1 point) and challenges (1 point).

This is software which is developed for a certain operating system. For instance if I am a retard I can choose to only develop my software for Gigashit Winclown. It will probably not work on good operating systems like Gnu/Linux and MacOs. 

## 3.3 

> Explain SLA (Service level agreement) and its content (1 points), OLA (Operations level agreement) and its content (1 points), and the relationship between SLA and OLA (2 points).

# Multiple choice 

## What are the desirable properties of the component-based architecture?

1. __Parameterizable components__
2. Components should not be customizable
3. __Supports component development in multiple programming languages__
4. __Allows easy distribution of components from seller to buyer__

## Which are the challenges of service oriented software engineering?

1. Dynamic software evolution
2. __Single point of failure__
3. __Complex negotiation process__
4. __Hard to understand when automated__

## What are benefits of TDD (Test-Driven Development)?

1. TDD is always applicable
2. __TDD can lead to higher code coverage__
3. TDD will guarantee a shorter development time
4. __TDD helps simplify debugging__

## What are differences between Model-Based Testing (MBT) and traditional manual testing?

1. __For MBT, test cases are tightly coupled to the model__
2. __For MBT, test cases are generated automatically from models__
3. __For MBT, there is a high cost in early phases of the project__
4. For MBT, test oracles still need to be input manually

## What can cause project cost/effort derives from its original cost/effort estimation?

1. __Pressure from managers to give low bid to get the project__
2. __Over-optimistic estimates__
3. __Frequent major requirement changes__
4. __Major changes in design and implementation__

## Which are advantages of scenario-based testing?

1. Easy to reuse
2. __It is a user focused testing__
3. Does not require working features before testing
4. __Can expose requirement related issues__