# example 1

## step 0
| ID | Transaction |
|----|-------------|
| 1  | ABDE        |
| 2  | BCE         |
| 3  | ABDE        |
| 4  | ABCE        |
| 5  | ABCDE       |
| 6  | BCD         |

minsup = 3

## step 1
sort items by support descending

| item | support |
|------|---------|
| B    | 6       |
| E    | 5       |
| A    | 4       |
| C    | 4       |
| D    | 4       |

## step 2
sort items in transaction in descending order of support

| ID | Transaction |
|----|-------------|
| 1  | BEAD        |
| 2  | BEC         |
| 3  | BEAD        |
| 4  | BEAC        |
| 5  | BEACD       |
| 6  | BCD         |


## step 3
construct FP-tree (R) by adding each of the transactions
this serves as an index in lieu of original db


## step 4
can remove leaf node for projected tree if sub-count < minsup
<!-- should remove leaves with support < minsup in this step after drawing a tree -->

R_D:
| path  | count |
|-------|-------|
| BEAD  | 2     |
| BEACD | 1     |
| BCD   | 1     |

R_C:

| path | count |
|------|-------|
| BEC  | 1     |
| BEAC | 2     |
| BC   | 1     |

R_A:

| path | count |
|------|-------|
| BEA  | 4     |

R_E:

| path | count |
|------|-------|
| BE   | 5     |

R_B:

| path | count |
|------|-------|
| B    | 6     |

## step 5
D combined with BEA

=> DB(4),DE(3),DA(3),DBE(3),DBA(3),DEA(3),DBAE(3)

C combined with BEA

=> CB(4),CE(3),CBE(3)


A combined with BE

=> AB(4),AE(4),ABE(4)


E combined with B

=> EB(5)


B combined with Ø


<!-- | item | conditional pattern base      | conditional fp-tree | frequent itemsets |
|------|-------------------------------|---------------------|-------------------|
| D    | (B,E,A:2),{B,E,A,C:1},{B,C:1} |                     |                   |
| C    |                               |                     |                   |
| A    |                               |                     |                   |
| E    |                               |                     |                   |
| B    |                               |                     |                   |

 -->

































# example 2

Transaction database

| ID | Transaction |
|----|-------------|
| 1  | ABG         |
| 2  | ABCD        |
| 3  | ACJ         |
| 4  | BC          |
| 5  | ACH         |
| 6  | BCL         |
| 7  | ABCD        |
| 8  | ABCDE       |
| 9  | ABK         |

minsup = 3

## step 1
sort items by support descending
abcdeghjkl

| Item | Support |
|------|---------|
| A    |  7      |
| B    |  7      |
| C    |  7      |
| D    |  3      |
| E    |  1      |
| G    |  1      |
| H    |  1      |
| J    |  1      |
| K    |  1      |
| L    |  1      |

## step 2
sort order of items in each transaction with descending order of support

| ID | Transaction |
|----|-------------|
| 1  | ABG         |
| 2  | ABCD        |
| 3  | ACJ         |
| 4  | BC          |
| 5  | ACH         |
| 6  | BCL         |
| 7  | ABCD        |
| 8  | ABCDE       |
| 9  | ABK         |

## step 3
construct the FP-tree (R) step-by-step by adding each transaction
<!-- remove leaf items with (original) support < minsup -->



## step 4
can remove leaf node for projected tree if sub-count < minsup
project fp-trees for each frequent item in R in increasing order of support (D,C,B,A) (exclude < minsup)
(check if support = sum(count) to validate answer)
Ø = support ?
end node is excluded in the graph, but not in the table path

D
| paths | count |
|-------|-------|
| ABCD  | 3     |


C
| paths | count |
|-------|-------|
| ABC   | 3     |
| AC    | 2     |
| BC    | 2     |

(cannot remove leaves < minsup ?)
can remove them in step 5

since it's not a path, we recursively project R_C
find next item with lowest support count in sub-db for c (does not have to be adjecent):
max(a2,b5,d3,e0,g0,h1,j1,k0,l1)=>b(5)

CB

| paths | count |
|-------|-------|
| AB    | 3     |
| B     | 2     |

CA

| paths | count |
|-------|-------|
| A     | 5     |

B

| paths | count |
|-------|-------|
| AB    | 5     |
| B     | 2     |

A

| paths | count |
|-------|-------|
| A     | 7     |

## step 5
look at sub-trees instead if splitted
when looking at sub-tree inside split e.g. R_AC, there is at leas one FP RC (itself)
remove leaf nodes with projected support < minsup for each projection
look at sub db for each item and set support=number of matches, exclude misses
D combined with ABC
exlclude frequent patterns < minsup

=> DA(3),DB(3),DC(3),DAB(3),DBC(3),DAC(3),DABC(3)


<!-- C combined with AB
=> CA(5),CB(4),CAB(3) -->

R_CB
CB combined with A
CBA(3),CB(5)

R_CA
CA combined with Ø
CA(5)

B combined with A
BA(5)

A combined with Ø
