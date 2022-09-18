# Problem 1 Use Case

__Order Pizza Online__

_UC1_

| | |
|--|--|
| **Function**      | Search Pizza Type |
| **Description**    | In order for the customer to see the offered pizza types, they have to be able to search. |
| **Inputs**        | Search query |
| **Source**        | User input |
| **Outputs**       | Pizza names and descriptions matching the query |
| **Destination**   | User interface and database |
| **Action**        | When a user provides a search query, the database should respond with all the different pizzas which suit the given query, both by description and name. |
| **Requires**      | User query / desires |
| **Precondition**  | Various pizzas are registred with name and description (ingredients) |
| **Postcondition** | The user is shown all the pizzas which match the query - this may be none.|
| **Side effects**  | None |

_UC2_

| | |
|--|--|
| **Function**      | Choose type and ammount of pizza |
| **Description**    | For a user to order desired amount and desired type of pizza. |
| **Inputs**        | Pizza type and ammount |
| **Source**        | User input type and ammount |
| **Outputs**       | User interface validation of updated order |
| **Destination**   | User interface |
| **Action**        | When a user has searched and found their desired pizza, they should be able to select this pizza and input the desired amount of pizza. |
| **Requires**      | Pizza object from search and number input|
| **Precondition**  | Pizza found from search function |
| **Postcondition** | The users order is visually updated. |
| **Side effects**  | None |

_UC3_

| | |
|--|--|
| **Function**      | Specify address delivery and time |
| **Description**    | The user can put in order ahead of time. So that they can order dinner on their lunch break and have it delivered home at 18 o clock for instance. The database should have an idea of where orders can be delivered, so that the user cannot order pizza to places which are unreasonably far away from the restaurant.|
| **Inputs**        | Valid address and time |
| **Source**        | User input |
| **Outputs**       | Validation of the address and time |
| **Destination**   | User interface and database|
| **Action**        | When a user has given their desired address, the database and backend should validate that the order can be delivered to that address. Moreover the desired delivery time should be validated so that the delivery will be made during opening hours of the restaurant. |
| **Requires**      | User input address and time |
| **Precondition**  | None |
| **Postcondition** | Time and delivery place is added to the order  information. |
| **Side effects**  | None |

_UC4_

| | |
|--|--|
| **Function**      | The user can provide payment information |
| **Description**    | The user should be able to make the payment of the order online. |
| **Inputs**        | Valid credit card information |
| **Source**        | User input |
| **Outputs**       | Validation of the card |
| **Destination**   | User interface |
| **Action**        | When the user has inserted their credit card information, the UI should verify that the format of the data is a valid credit card. |
| **Requires**      | User input of credit card information |
| **Precondition**  | None |
| **Postcondition** | Backend communicates with the bank to make the transaction. |
| **Side effects**  | None |

_UC5_

| | |
|--|--|
| **Function**      | The system can communicate with the bank to check the correctness of the payment card information and charge the card. |
| **Description**    | After the user has sent their payment information, the backend system should be able to communicate with the bank to verify that the information given is valid, and charge the card for the amount ordered.|
| **Inputs**        | Credit card information |
| **Source**        | UC4 |
| **Outputs**       | Validation of the payment |
| **Destination**   | User interface and database|
| **Action**        | When the user has submitted their credit card details in UC4, the backend should communicate with the bank to verify that the information provided is valid, and if so charge the card. When this is done, the payment should be logged in the database, and user should receive a confirmation of the valid payment. If it failed then the user should be given an error message. |
| **Requires**      | Credit card info from UC4 |
| **Precondition**  | Valid order input from UC2 and UC3 |
| **Postcondition** | The order is registered in the database. And the payment is registered in the database and confirmed for the user. |
| **Side effects**  | Money is transferred from user account to restaurant. |

_UC6_

| | |
|--|--|
| **Function**      | The system can issue a receipt to the users |
| **Description**    | After the user has performed a valid transaction in UC5, the system should be able to send the user a receipt of the order. |
| **Inputs**        | User request of the receipt |
| **Source**        | User input |
| **Outputs**       | Receipt |
| **Destination**   | User interface |
| **Action**        | When a user has received confirmation of valid order in UC5, the system should ask if they want a receipt of the order. If the user says yes then the receipt should be generated using the information stored in the database from UC5. |
| **Requires**      | Valid payment in UC5 |
| **Precondition**  | None |
| **Postcondition** | Receipt is generated and sent to the user. |
| **Side effects**  | None |

_UC7_

| | |
|--|--|
| **Function**      | The system can inform the delivering company of the user's order sot hat the delivering company can come to pick up the pizza for delivery |
| **Description**    | The restaurant doesn't deliver the pizza themselves. To minimize the overhead, the system should contact the delivery company automatically about the order. |
| **Inputs**        | Time and place |
| **Source**        | UC4 |
| **Outputs**       | None |
| **Destination**   | Delivery company |
| **Action**        | After a user has made a valid payment for an order with time and place, the backend system should contact the delivery company about the time and place for the delivery, as well as when they should be present at the restaurant to pick up the order. |
| **Requires**      | Valid order from UC4 |
| **Precondition**  | None |
| **Postcondition** | Delivery company will show up at the restaurant at the given time. |
| **Side effects**  | None |

# Problem 2 - GORE 
![](images/2022-06-01-09-37-47.png)

# Problem 3 - Software tersting

_Scalability test_

We must make sure that the system can scale with the number of users. This means that the system remains usable when for example 100 000 users use the system. Issues related to scaling is hard for a developer to find without defined tests. The consequence of failing scalability requirements may be in worst case that the system will not work when deployed at voting booths.

_Stress test_

In order to ensure that the system we develop can handle the load required at voting days, we must develop tests which can determine whether the system remains operational with multiple concurrent users. Without this test in place we risk the system failing under maximum load.

_Load and stability test_

We must develop tests to make sure that the system will perform close to as well on day 1 as on day 250. These tests will find maintenance related issues before they arise in production. Even if the issues found during these tests cannot be solved, we can still figure out ways of working around them. For instance if we know that the system will perform worse after 125 days, without knowing the cause, we can instruct production to restart the system after a given time. 

_User acceptance test_

It is vital that we make sure that regular users understand how to use the system. By performing user acceptance tests we can catch design issues before they are implemented in production on voting day.

_Operational acceptance test_

Usually the team operating the system is not the same people who have developed the system. We must therefore produce adequate documentation so that the production team knows what to do if something goes wrong, regular maintenance tasks, training manual, security procedures etc. 

# Problem 4 - Code review and testing

Code smell is a degenerate term for code that is either poorly written, contains old artifacts or have unnecessary clauses etc.

During the exercises we found these code smells:

* Lack of documentation

The severity of this smell varies, but it should be considered a baseline that the code contains documentation that explains why the code is there. 

```js

function similarity(a,b){
    return a==b
}
```

Can be refactored to

```js

function similarity(a,b){
    /**
     * Method that checks if two objects are the same
     * */

    return a==b
}
```

* Unused variable declaration

Variables that are not used should not be defined. This is not a big smell in the cases of functions that does not return a value, but in those cases certain conventions should be followed:

```py

aa = print("Test")

```

Should be refactored to 

```py
print("Test")
```

```py
_ = print("Test")
```

* Inconsistent indentation

This code smell can make it harder to read certain code snippets. This smell can be fixed by using automated tools. In some languages like python such code smells can alter the functionality of the program itself. But in languages like javascript its makes no functional difference:

```js
function test(){
a = 1+3
return a*2
}

function test2(){
    a = 1+3
        return a*2
}
```
should be refactored to:

```js
function test(){
    a = 1+3
    return a*2
}

function test2(){
    a = 1+3
    return a*2
}
```

* Methods exceeding required length

If a method is too long then it might be hard to follow the logic and debug the method. Additionally it makes it opens for the possibility of duplicated code. By restricting the length of methods you are forced to generalize your methods which makes code easier to reuse and maintain. 
