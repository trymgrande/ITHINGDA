# TDT4117 Information Retrieval - Autumn 2021
## Assignment 1
##### Deadline for delivery is 22.09.2019
##### September 6, 2021
##### Note: Use log base 2 for all your computations.

#### Task 1: Basic Definitions
###### Explain the main differences between:
###### 1. Information Retrieval vs Data Retrieval
Information retrieval deals with retrieval and evaluation of information from repositories of documents, while data retrieval deals with getting data from a database system. Data that exists in a database is well structured, and has defined semantics, while IR systems deal with mostly unstructured data. When querying a database, the queries have to be very precise and match exactly with the defined semantics, while when querying an IR system, the queries don't have to be as specific. This means that the results are also way more specific for data retrieval systems. They return exact results, or nothing at all. IR systems on the other hand, return the best matches in ranked order, which means that partial matches are also allowed. An example of an IR system is Google search engine. 

###### 2. Structured Data vs Unstructured Data
Structured data is stored and organized in predefined formats, while unstructured data is stored and organized in its native form. This does not neccesarily mean that there is no structure in the unstructured data. There can still be document structure and linguistic structure etc. but a formal, semantically overt structure is missing, as opposed to structured data often stored in relational databases. Querying for structured data often demands less over all, but requires the querier to be exact. Structured data consists of a select amount of defined datatypes, while unstructured data can in theory consist of an infinete amount of intertwined and cross-referenced datatypes. 

#### Task 2: Term Weighting
###### Explain:
###### 1. Term Frequency (tf)
Term frequency is the number of times a certain term appears in a document. It is a very important parameter for ranking the relevance of documents in relationship to a query. 

###### 2. Document Frequency (df)
Document frequency is the number of documents in a search index that contain a certain term. If we have four documents in a search index, and the term we are looking for appears three times in one of the documents, the document frequency is one, while the term frequency for the document containing the term is three. 

###### 3. Inverse Document Frequency (idf)
Inverse document frequency is meant to decide how important a term in the query is. The more common a certain word is across all searched documents, the less important it is for the serach, as it does not contain a lot of topical information. An example is the word "the". It is very commonly used, and exists in almost every english document. Therefore, it does not really narrow the search down, and should not be highly prioritized in a search. 

###### 4. Why idf is important for term weighting
idf is important for term weighting because it can tell us/the search algorithms if it is a general term, or a more specific term for the topics we are looking for. The more specific a term is for the topic, the more it should weigh. 

#### Task 3: IR Models
###### Given the following document collection containing words from the set O = {Big, Cat,Small, Dog }, answer the questions in subtasks 3.1 and 3.2.
###### doc1={Big Cat Small Dog}
###### doc2={Dog}
###### doc3={Cat Dog}
###### doc4={Big Cat Big Small Cat Dog}
###### doc5={Big Small}
###### doc6={Small Cat Dog Big }
###### doc7={Big Big Big}
###### doc8={Dog Cat Cat }
###### doc9={Cat Small }
###### doc10={Small Small Big Dog}

##### SubTask 3.1: Boolean Model and Vector Space Model
###### Given the following queries:
###### q1 = "Cat AND Dog"
###### q2 = "Cat AND Small"
###### q3 = "Dog OR Big"
###### q4 = "Dog AND NOT Small"
###### q5 = "Cat"
###### 1. Which of the documents will be returned as the result for the above queries using the Boolean model? Explain your answers and draw a figure to illustrate.

Term Document Matrix:
|     | w1 | w2 | w3 | w4 |
|-----|----|----|----|----|
| d1  | 1  | 1  | 1  | 1  |
| d2  | 0  | 0  | 0  | 1  |
| d3  | 0  | 1  | 0  | 1  |
| d4  | 1  | 1  | 1  | 1  |
| d5  | 1  | 0  | 1  | 0  |
| d6  | 1  | 1  | 1  | 1  |
| d7  | 1  | 0  | 0  | 0  |
| d8  | 0  | 1  | 0  | 1  |
| d9  | 0  | 1  | 1  | 0  |
| d10 | 1  | 0  | 1  | 1  |

Term vector for w1 = "big" =    <1,0,0,1,1,1,1,0,0,1>
Term vector for w2 = "cat" =    <1,0,1,1,0,1,0,1,1,0>
Term vector for w3 = "small" =  <1,0,0,1,1,1,0,0,1,1>
Term vector for w4 = "dog" =    <1,1,1,1,0,1,0,1,0,1>

Query vector for q1 = "Cat AND Dog" = w2 ∧ w4 =         <1,0,1,1,0,1,0,1,0,0>
Query vector for q2 = "Cat AND Small" = w2 ∧ w3 =       <1,0,0,1,0,1,0,0,1,0>
Query vector for q3 = "Dog OR Big" = w4 ∨ w1 =          <1,1,1,1,1,1,1,1,0,1>
Query vector for q4 = "Dog AND NOT Small" = w4 ∧ !w3 =  <0,1,1,0,0,0,0,1,0,0>
Query vector for q5 = "Cat" = w2 =                      <1,0,1,1,0,1,0,1,1,0>

Each query will return the respective documents listed below according to their term vectors:

q1 will return the following documents: doc1,doc3,doc4,doc6,doc8
q2 will return the following documents: doc1,doc4,doc6,doc9
q3 will return the following documents: doc1,doc2,doc3,doc4,doc5,doc6,doc7,doc8,doc10
q4 will return the following documents: doc2,doc3,doc8
q5 will return the following documents: doc1,doc3,doc4,doc6,doc8,doc9


###### 2. What is the dimension of the vector space representing this document collection when you use the vector model and how is it obtained?
D = document collection
d = document: w1, w2... $\epsilon$ V, occurs in D
V = vocabulary: w1, w2...

|D| = 10
|V| = 4

The dimension of the vector space is defined as:
Dimensions of the term-document matrix:
$$|D| \times |V| = 10 \times 4b = 40b $$

###### 3. Calculate the weights for the documents and the terms using tf and idf weighting.Put these values into a document-term-matrix. 
Document-term-matrix:
|     | big | cat | small | dog |
|-----|----|----|----|----|
| d1  | 0.222  | 0.222  | 0.222  | 0.155  |
| d2  | 0  | 0  | 0  | 0.155  |
| d3  | 0  | 0.222  | 0  | 0.155  |
| d4  | 0.289  | 0.289  | 0.222  | 0.155  |
| d5  | 0.222  | 0  | 0.222  | 0  |
| d6  | 0.222  | 0.222  | 0.222  | 0.155  |
| d7  | 0.328  | 0  | 0  | 0  |
| d8  | 0  | 0.289  | 0  | 0.155  |
| d9  | 0  | 0.222  | 0.222  | 0  |
| d10 | 0.222  | 0  | 0.289  | 0.155  |


###### 4. Study the documents 2, 3, 5 and 7 and compare them to document 9. Calculate the similarity between document 9 and these four documents according to Euclidean distance. (Use tf-idf weights for your computations).
Euclidean distance between 2 and 9:
$$\sqrt{(0-0)^2+(0.222-0)^2+(0.222-0)^2+(0-0.155)^2} = 0.35$$

Euclidean distance between 3 and 9:
$$\sqrt{(0-0)^2+(0.222-0.222)^2+(0.222-0)^2+(0-0.155)^2} = 0.27$$

Euclidean distance between 5 and 9:
$$\sqrt{(0-0.222)^2+(0.222-0)^2+(0.222-0.222)^2+(0-0)^2} = 0.31$$

Euclidean distance between 7 and 9:
$$\sqrt{(0-0.328)^2+(0.222-0)^2+(0.222-0)^2+(0-0)^2} = 0.45$$

###### 5. Rank the documents for query q5 using cosine similarity.
The cosine similarity between d1 and q5 is:
$$sim(d1,q5) = \frac{(1*1)+(1*0)+(1*0)+(1*0)}{\sqrt{1^2+1^2+1^2+1^2}*\sqrt{1^2+0^2+0^2+0^2}} = 0.5$$
And for the rest: 

|     | q5 | 
|-----|----|
| d1  | 0.5  |
| d2  | 0  |
| d3  | 0.71  |
| d4  | 0.63  |
| d5  | 0  |
| d6  | 0.5  |
| d7  | 0  |
| d8  | 0.89  |
| d9  | 0.71  |
| d10 | 0  |
In ranked order: d8, (d3,d9), d4, (d1,d6), (d2,d5,d7,d10) 

##### SubTask 3.2: Probabilistic Models
###### Given the following queries:
###### q1 = "Cat Dog"
###### q2 = "Small"
###### 1. What are the main differences between BM25 model and the probabilistic model introduced by Robertson-Jones?


###### 2. Rank the documents using the BM25 model. Set the parameters to k = 1.2 and b = 0.75. (Here we assume relevance information is not provided.)
###### Hint: To avoid getting negative numbers, you need to use idf = log [ N/dft] in the BM25 model.


###### Important notes
###### Please carefully read the following notes and consider them for the assignment delivery.Submissions that do not fulfill these requirements will not be assessed and should be submitted again.
###### 1. The assignment must be delivered in pdf format. Other formats such as .docx and .txt are not allowed.
###### 2. The assignment must be typed. Handwritten assignments are not accepted.
###### 3. Final scores are required, but not sufficient. You need to explicitly write the details of your computations (with no redundancy).
###### 4. You may work in groups of maximum 2 students.
    