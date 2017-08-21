import json

reviews = []
with open("review.json","r") as filejson:
    templines = filejson.readlines()

for line in templines:
    reviews.append(json.loads(line))

print(len(reviews))

text = [review.get('text').lower() for review in reviews]

# from nltk.tokenize import RegexpTokenizer
# from stop_words import get_stop_words
# from nltk.stem.porter import PorterStemmer
# p_stemmer = PorterStemmer()
# en_stop = get_stop_words('en')
# tokenizer = RegexpTokenizer(r'\w+')
#
# text = text[:10]
#
# for review in text:
#     tokens = tokenizer.tokenize(review)
#     stopped_words = [i for i in tokens if i not in en_stop]
#     stemmed_words = [p_stemmer.stem(i) for i in stopped_words]
#
# from gensim import corpora, models
#
# dictionary = corpora.Dictionary(stemmed_words)
# corpus = []
# for text in stemmed_words:
#     corpus.append(dictionary.doc2bow(text))
#


doc1 = "Sugar is bad to consume. My sister likes to have sugar, but not my father."
doc2 = "My father spends a lot of time driving my sister around to dance practice."
doc3 = "Doctors suggest that driving may cause increased stress and blood pressure."
doc4 = "Sometimes I feel pressure to perform well at school, but my father never seems to drive my sister to do better."
doc5 = "Health experts say that Sugar is not good for your lifestyle."

# compile documents
doc_complete = text[:1]

import pprint
pprint.pprint(doc_complete)
from nltk.corpus import stopwords
from nltk.stem.wordnet import WordNetLemmatizer
from stop_words import get_stop_words
import string
stop = set(get_stop_words('en'))
exclude = set(string.punctuation)
lemma = WordNetLemmatizer()
def clean(doc):
    stop_free = " ".join([i for i in doc.lower().split() if i not in stop])
    punc_free = ''.join(ch for ch in stop_free if ch not in exclude)
    normalized = " ".join(lemma.lemmatize(word) for word in punc_free.split())
    return normalized

doc_clean = [clean(doc).split() for doc in doc_complete]

# Importing Gensim
import gensim
from gensim import corpora

# Creating the term dictionary of our courpus, where every unique term is assigned an index. dictionary = corpora.Dictionary(doc_clean)
dictionary = corpora.Dictionary(doc_clean)
# Converting list of documents (corpus) into Document Term Matrix using dictionary prepared above.
doc_term_matrix = [dictionary.doc2bow(doc) for doc in doc_clean]
import pprint
# pprint.pprint(doc_term_matrix)
# Creating the object for LDA model using gensim library
Lda = gensim.models.ldamodel.LdaModel

# Running and Trainign LDA model on the document term matrix.
ldamodel = Lda(doc_term_matrix, num_topics=6, id2word = dictionary, passes=50)

print(ldamodel.print_topics(num_topics=6, num_words=3))
