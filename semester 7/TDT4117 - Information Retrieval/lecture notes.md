
# 2
- naive approach to structuring books
    - main text is in-accessible
- searching web
    - fast, user centric, relevance sorting, text content
- the boolean model (boolean word matrix)
    -modeling documents
        - term document matrix: documents x words
    - term
        - normalization: all toLower()
        - stemming: running == run, method not perfect
        - stopword removal: to, or, and, the etc.
        - sign removal
    - boolean retreival
        - boolean operations to derermine relevance based on boolean query
        - disadvantages:
            - large size with 0 >>> 1
                - inverted indexed matrix solves problem: only documents with given word
            - no ranking of relevance, only exact matches to terms
                - solution: store weight number instead of bit
        - document frequency:
            - 
        - term frequencey:
            - 
- length normalization
    - document length are more likely to be retreived
        - solution: divide by own length
- vector space model
    - partial matching instead of exact
        -   similarity is cosine of angle between two vectors
    - assumes independence of index terms: lyrics wont match
    - document frequency:
        - number of documents the term occurs in the collection
    - term frequency:
        - frequency of term in document
    - inverse document frequency (tf-idf)
        - measure of whether a terme is common or rare in collection
        - weighted term vectors
            - weights are normalized to language using e.g. zipf's law
            - combining language normalization with collection frequency
- summary
    - 
    
# 3
Boolean model
Models a document collection using a term document matrix that. Each term has a vector (columns) representing which documents it exists in and vice versa. This can be used by translating a query into boolean algebra and applying it to the terms vectors.

cons:
Term-document matrix is sparse. Can not be ranked, only exact matches. Index terms assumed independent.

VSM
Uses a similar matrix with frequency weights instead. Documents are ranked using cosine between document and query vector.

Index terms assumed independent.

Probabilistic model
Initial set is retrieved and inspected as feedback. Can be ranked. Uses inverse document frequency.

Need feedback. Index terms assumed independent. Hard to assume what docs are relevant.

BM25
Variation of probobilistic model. Generally outperforms VSM.
Uses:
- inverse document frequency
- term frequency
- document length normalization

Statistical language models
- finite state automation
- trigram language models adopted for speech recognition (n=3)
- unigram (n=1) used for IR because of term independence

Smoothing
Non-occuring term is possible even though it didn't occur. But not more than expected from the collection.

# 4 - evaluation
![](images/2021-09-15-13-33-33.png)
- precision-recall tradeoff
    - precision: relevant retrieved/retrieved
    - recall: relevant retrieved/relevant (hits left behind)
    - f-measure to trade off
        - single vale summary for precicion and recall


# web search
- zipf's law
    - ![](2021-11-26-12-33-50.png)
    - term frequency distribution
    - can recommend stop words (highest frequency/probability terms)
- heap's law
    - ![](2021-11-26-12-34-07.png)
    - counts distinct words (size of vocabulary)
  - Distributed architecture
    - harvest
        - gatherers
            - occationally collect and extract indexing info from web servers
        - brokers
            - retrieve info from gatherers/brokers
            - update incrementally their indices
