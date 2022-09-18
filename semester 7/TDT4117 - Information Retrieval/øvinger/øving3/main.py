import random
import codecs
import re
import string
import nltk
import gensim
from nltk.tokenize import word_tokenize
from nltk.stem.porter import PorterStemmer

class DocumentFormatter:

    def task_1_0(self, seed):
        """initialize random number generator"""
        random.seed(seed)


    def task_1_1(self):
        """Opens file and returns content"""
        file = codecs.open("pg3300.txt", "r", "utf-8")
        # nltk_file = nltk.load(file)
        # nltk_file.split('\n')
        content = file.read()
        return content

    def task_1_2(self, content):
        """splits content into paragraphs (documents), still with newlines"""
        documents_with_newlines = content.split('\n\n')
        # remove remaining newlines
        # documents = list(filter(None, documents_with_newlines))
        documents = documents_with_newlines
        # for i in range(len(documents_with_newlines)):
        #     documents[i] = documents_with_newlines[i].replace('\n', '')
        return documents

    def task_1_3(self, documents):
        """Removes documents with phrase "Gutenberg" inside"""
        print("len before Gutenberg deletion", len(documents))
        documents = [ document for document in documents if "Gutenberg" not in document ]
        print("len after Gutenberg deletion", len(documents))
        return documents

    def task_1_5(self, documents):
        """Punctuates documents using character symbols, newlines, and tabs"""
        string_punctuation = string.punctuation+"\n\r\t"
        print(string_punctuation)
        print("document 0 before punctuation", documents[0])
        for i in range(len(documents)):
            documents[i] = documents[i].translate(str.maketrans('', '', string_punctuation))
        print("document 0 after punctuation", documents[0])
        return documents

    def task_1_4(self, documents):
        """Tokenizes documents into words"""
        print("document 0 before tokenize", documents[0])
        documents_tokenized = []
        for i in range(len(documents)):
            documents_tokenized.append(word_tokenize(documents[i]))
        print("document 0 after tokenize", documents_tokenized[0])
        return documents_tokenized

    def task_1_6(self, documents):
        """Stems documents"""
        stemmer = PorterStemmer()
        for i in range(len(documents)):
            for j in range(len(documents[i])):
                # print("test", documents[i][j])
                documents[i][j] = stemmer.stem(documents[i][j], to_lowercase=True)
        return documents

    def task_2_1(self, documents):
        """Removes stopwords"""
        cannot = False
        for i in range(len(documents)):
            for j in range(len(documents[i])):
                if "cannot" in documents[i][j]:
                    cannot = True
        print("cannot in documents", cannot)
        print("stemmed", documents[0])

        stopwords_file = open("common-english-words.txt", mode='r')
        stopwords_string = stopwords_file.read()
        stopwords_file.close()
        stopwords = stopwords_string.split(",")
        # stopwords = self.task_1_6([stopwords])
        # print("stopwords stemmed", stopwords)
        # print("stopwords", stopwords)
        dictionary = gensim.corpora.Dictionary(documents)
        # dictionary.add_documents()
        print("dict før removal: ", dictionary)

        stemmer = PorterStemmer()
        bad_ids = []
        print(dictionary.token2id["across"])
        stopwords_stemmed = []
        for stopword in stopwords:
            # if stopword == "cannot":
            #     break


            stopword_stemmed = stemmer.stem(stopword, to_lowercase=True)
            stopwords_stemmed.append(stopword_stemmed)

            # print(stopword_stemmed)
            # stopword_id = dictionary.token2id[stopword_stemmed]
            # bad_ids.append(stopword_id)
        print(stopwords_stemmed)
        

        dictionary.filter_tokens(bad_ids)

        print("dict etter removal: ", dictionary)

documentFormatter = DocumentFormatter()
documentFormatter.task_1_0(321)
file_content = documentFormatter.task_1_1()
documents = documentFormatter.task_1_2(file_content)
documents = documentFormatter.task_1_3(documents)
documents = documentFormatter.task_1_5(documents)
documents = documentFormatter.task_1_4(documents)
documents = documentFormatter.task_1_6(documents)
documentFormatter.task_2_1(documents)
