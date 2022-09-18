# chapter 1
- Agents
    - *"As a general rule, it is better to design performance measures according to what one actually wants to be achieved in the environment, rather than according to how one thinks the agent should behave."*
    - *"Which is better—a reckless life of highs and lows, or a safe but humdrum existence? Which is better—an economy where everyone lives in moderate poverty, or one in which some live in plenty while others are very poor?"*

# chapter 2
## 2.4
- agent program maps percepts -> action
- agent architecture: computer type, sensors, actuators
- agent = architecture + program
- agent types:
- simple reflex agent:
    **Pros:**
    - no need for state as it does not depend on the location in the environment
    - reduced instruction set as it only uses the current percepts
    - used even in complex agents where a condition-action rule is needed
    - simple to implement
    **Cons:**
    - limited intelligence
    - can introduce infinite loops where it tries to constantly move into a wall because it does not have a state to store its location. Randomization can be used here to get unstuck.
- model based reflex agent:
    - can use inernal state to model unobserved parts of environment:
    - how the world changes over time (transition model)
        - effects of agent's actions
        - world evolving independent of agent's actions
    - how the sensors change (eg: water on camera lens) (sensor model)
- goal-based agent
    - differs from reflex agent completely e.g. when car ahead is breaking, reflex agent breaks simply when it sees lights, has no idea why. Goal-based agent breaks because it is the only way it can see that will avoid the crash.
- utility-based agent
    - internalization of the performance measure (e.g. compares different routes or selects speed/safety tradeoff correctly)
    - more intelligent than goal-based because it can maximazes performance measure
    - uses utility function to measure preferred state in the world
    - very complex, even unachieveble
    - requires correct utility function
