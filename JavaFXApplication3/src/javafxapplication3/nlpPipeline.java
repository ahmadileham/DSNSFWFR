package javafxapplication3;

//nlpPipeline.java
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class nlpPipeline {
    static StanfordCoreNLP pipeline;
    public static void init()
    {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }
    public static void estimatingSentiment(String text)
    {
        int sentimentInt;
        String sentimentName;
        Annotation annotation = pipeline.process(text);
        for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
        {
            Tree tree = sentence.get(SentimentAnnotatedTree.class);
            sentimentInt = RNNCoreAnnotations.getPredictedClass(tree);
            sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(sentimentName + "\t" + sentimentInt + "\t" + sentence);
        }
    }

    public static float getReviewSentiment(String review, float weight)
    {
        int sentenceSentiment;
        int reviewSentimentAverageSum = 0;
        int reviewSentimentWeightedSum = 0;
        Annotation annotation = pipeline.process(review);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        int numOfSentences = sentences.size();
        int factor = Math.round(numOfSentences*weight);
        if (factor == 0) {
            factor = 1;
        }
        int divisorLinear = numOfSentences;
        int divisorWeighted = 0;

        for (int i = 0; i < numOfSentences; i++)
        {
            Tree tree = sentences.get(i).get(SentimentAnnotatedTree.class);
            sentenceSentiment = RNNCoreAnnotations.getPredictedClass(tree);
            reviewSentimentAverageSum = reviewSentimentAverageSum + sentenceSentiment;
            if(i == 0 || i == numOfSentences -1) {
                reviewSentimentWeightedSum = reviewSentimentWeightedSum + sentenceSentiment*factor;
                divisorWeighted += factor;
            }
            else
            {
                reviewSentimentWeightedSum = reviewSentimentWeightedSum + sentenceSentiment;
                divisorWeighted += 1;
            }
        }

        float score = Math.round((float) reviewSentimentWeightedSum/divisorWeighted);
        return score;
    }

    public static void getStorySentiment(String story)
    {
        int sentenceSentiment;
        int reviewSentimentWeightedSum = 0;
        Annotation annotation = pipeline.process(story);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        int divisorWeighted = 0;
        for (int i = 1; i <= sentences.size(); i++)
        {
            Tree tree = sentences.get(i-1).get(SentimentAnnotatedTree.class);
            sentenceSentiment = RNNCoreAnnotations.getPredictedClass(tree);
            reviewSentimentWeightedSum = reviewSentimentWeightedSum + sentenceSentiment*i;
            divisorWeighted += i;
        }
        System.out.println("Weighted average sentiment:\t" + (double)(2*Math.floor((reviewSentimentWeightedSum/divisorWeighted)/2) + 1.0d));
    }


}
