## Swahili Tweets Sentiment Analysis 
#### ~ from Classical Machine Learning to Advanced Neural Networks using Tensorflow

This Project entails biulding a sentiment Classifier for Tweets in Swahili in order to categorize them as either being Positive, negative or neutral. The data for this project was sourced from the zindi platform: https://zindi.africa/competitions/swahili-social-media-sentiment-analysis-challenge .

The notebook begins with basic Exploratory data analysis where for instance the target column's distribution is plotted to check wether the target classes are properly balanced. 
Secondly some basic text preprocessing is done to remove punctuations for instance followed by removing numbers as well as removing stopwords. from there some basic text analysis techniques are done that is 
lexicon and vader techniques as explained in the notebooks markdown cells, both of which are traditional techniques of text analysis. Wordclouds are then plotted for each target as seen in the notebook

#### Modelling
 First i began be trying classical machine learning approaches comparing 3 vectorization techniques i.e:
 1. TFIDFVectorizer
 2. CountVectorizer
 3. Word2Vec Technique
 
 Using accuracy metric for evaluation the following results were achieved for 5 different Machine learning algorithms as shown. From this, TFIDFVectorizer seemed to give better results as shown
 
![image](https://user-images.githubusercontent.com/39335569/157759536-97385b73-9a23-4766-ba46-4e90f95f8374.png)

From this i then went for a deep learning approach using tensorflow to realise some deep layers. first i created some embeddings layer, followed by a 1 dimensional convolutional layer
then a maxpooling layer followed by a bidirectional LSTM layer.
i then copiled my neural nets layers then fiited our training set for 50 epochs. The results are as shown in the graph below which plots training accuracy as well as validation accuracy.

![image](https://user-images.githubusercontent.com/39335569/157760609-f1698de0-c919-4aa2-90fe-d0b53d6cd2a0.png)

A confusion matrix plot was also plotted for our models perfomance as shown:

![image](https://user-images.githubusercontent.com/39335569/157761111-dec35ed6-d664-40aa-91e5-1f4838d8f9d7.png)



