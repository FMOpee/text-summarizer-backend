package com.example.textsummarizer.summarizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.textsummarizer.summarizer.dataset.DatasetReader;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import static com.example.textsummarizer.summarizer.dataset.DatasetReader.wvd;

public class SummarizerStartingPoint {
    private static SummarizerStartingPoint instance = null;
    private SummarizerStartingPoint(){

    }
    public static SummarizerStartingPoint getInstance() throws IOException, ClassNotFoundException {
        if(wvd == null){
            DatasetReader.read();
        }
        if(instance == null){
            instance = new SummarizerStartingPoint();
        }
        return instance;
    }

    private static final String[] stopWords = new String[]{"অবশ্য","অনেক","অনেকে","অনেকেই","অন্তত","অথবা","অথচ","অর্থাত","অন্য","আজ","আছে","আপনার","আপনি","আবার","আমরা","আমাকে","আমাদের","আমার","আমি","আরও","আর","আগে","আগেই","আই","অতএব","আগামী","অবধি","অনুযায়ী","আদ্যভাগে","এই","একই","একে","একটি","এখন","এখনও","এখানে","এখানেই","এটি","এটা","এটাই","এতটাই","এবং","একবার","এবার","এদের","এঁদের","এমন","এমনকী","এল","এর","এরা","এঁরা","এস","এত","এতে","এসে","একে","এ","ঐ"," ই","ইহা","ইত্যাদি","উনি","উপর","উপরে","উচিত","ও","ওই","ওর","ওরা","ওঁর","ওঁরা","ওকে","ওদের","ওঁদের","ওখানে","কত","কবে","করতে","কয়েক","কয়েকটি","করবে","করলেন ","করার","কারও","করা ","করি","করিয়ে","করার","করাই","করলে","করলেন","করিতে ","করিয়া","করেছিলেন","করছে","করছেন","করেছেন","করেছে ","করেন","করবেন","করায়","করে","করেই","কাছ","কাছে","কাজে","কারণ","কিছু","কিছুই ","কিন্তু","কিংবা","কি","কী ","কেউ ","কেউই","কাউকে","কেন ","কে ","কোনও ","কোনো","কোন ","কখনও","ক্ষেত্রে","খুব","গুলি","গিয়ে","গিয়েছে","গেছে ","গেল","গেলে","গোটা","চলে","ছাড়া","ছাড়াও","ছিলেন ","ছিল","জন্য","জানা ","ঠিক","তিনি","তিনঐ","তিনিও","তখন ","তবে ","তবু","তাঁদের ","তাঁাহারা","তাঁরা ","তাঁর","তাঁকে","তাই","তেমন","তাকে ","তাহা","তাহাতে","তাহার","তাদের ","তারপর ","তারা ","তারৈ","তার","তাহলে","তিনি","তা","তাও","তাতে","তো ","তত","তুমি ","তোমার ","তথা","থাকে","থাকা ","থাকায়","থেকে","থেকেও","থাকবে","থাকেন","থাকবেন","থেকেই ","দিকে ","দিতে ","দিয়ে","দিয়েছে","দিয়েছেন","দিলেন","দু","দুটি","দুটো","দেয়","দেওয়া","দেওয়ার","দেখা ","দেখে","দেখতে","দ্বারা ","ধরে","ধরা","নয় ","নানা ","না","নাকি","নাগাদ","নিতে","নিজে","নিজেই","নিজের","নিজেদের","নিয়ে","নেওয়া","নেওয়ার","নেই","নাই","পক্ষে","পর্যন্ত","পাওয়া ","পারেন ","পারি","পারে","পরে","পরেই","পরেও","পর","পেয়ে ","প্রতি ","প্রভৃতি ","প্রায়","ফের","ফলে ","ফিরে","ব্যবহার ","বলতে ","বললেন","বলেছেন","বলল ","বলা ","বলেন ","বলে ","বহু","বসে","বার","বা","বিনা","বরং","বদলে","বাদে","বার","বিশেষ","বিভিন্ন","বিষয়টি ","ব্যবহার","ব্যাপারে","ভাবে","ভাবেই","মধ্যে","মধ্যেই","মধ্যেও","মধ্যভাগে","মাধ্যমে","মাত্র ","মতো","মতোই","মোটেই","যখন ","যদি ","যদিও","যাবে ","যায়","যাকে","যাওয়া","যাওয়ার","যত","যতটা","যা","যার","যারা","যাঁর","যাঁরা","যাদের","যান","যাচ্ছে ","যেতে","যাতে ","যেন ","যেমন ","যেখানে","যিনি","যে","রেখে","রাখা","রয়েছে","রকম","শুধু ","সঙ্গে","সঙ্গেও","সমস্ত","সব","সবার","সহ ","সুতরাং ","সহিত","সেই","সেটা","সেটি","সেটাই","সেটাও","সম্প্রতি","সেখান","সেখানে ","সে","স্পষ্ট ","স্বয়ং","হইতে ","হইবে ","হৈলে","হইয়া ","হচ্ছে ","হত","হতে ","হতেই","হবে ","হবেন","হয়েছিল ","হয়েছে","হয়েছেন","হয়ে","হয়নি","হয়","হয়েই","হয়তো","হল","হলে","হলেই","হলেও","হলো","হিসাবে","হওয়া","হওয়ার","হওয়ায়","হন","হোক","জন","জনকে","জনের","জানতে","জানায়","জানিয়ে","জানানো","জানিয়েছে","জন্য","জন্যওজে","জে","বেশ","দেন","তুলে","ছিলেন","চান","চায়","চেয়ে","মোট","যথেষ্ট","টি"};



    private String inputText;
    private ArrayList<String> sentences;
    private ArrayList<ArrayList<String>> sentenceDividedIntoWords;
    private ArrayList<float[]> vectorField;

    private double[][] affinityMatrix;
    private double[][] degreeMatrix;
    private double[][] graphLaplacian;

    private double secondEigenValue;
    private EigenVector secondEigenVector;

    public String driver(String s) throws Exception {
        inputText = s;
        tokenization();
        vectorField = getVectorField();
//        printVectorField();
//        System.out.println("=============================================================");
        affinityMatrix = MatrixCalculator.getAffinityMatrix(vectorField);
        affinityMatrix = MatrixCalculator.normalizeMatrix(affinityMatrix);
//        printMatrix(affinityMatrix);
//        System.out.println("=============================================================");
        degreeMatrix = MatrixCalculator.getDegreeMatrix(affinityMatrix);
//        printMatrix(degreeMatrix);
//        System.out.println("=============================================================");
        graphLaplacian = MatrixCalculator.subtractMatrix(degreeMatrix,affinityMatrix);
        printMatrix(graphLaplacian);
        System.out.println("=============================================================");
//        graphLaplacian = MatrixCalculator.normalizeLaplacian(graphLaplacian);
//        printMatrix(graphLaplacian);
        secondEigenVector = MatrixCalculator.getEigenValueAndEigenVector(graphLaplacian);
//        secondEigenVector.sort();
        return s;
    }

    private void tokenization(){
        inputText = removeSquareBracketTexts(inputText);
        getSentences();
        getWords();
        stopWordRemoval();
//        printToken();
    }

    private String removeSquareBracketTexts(String input) {
        Pattern pattern = Pattern.compile("\\[[^]]*]"); // Matches anything between square brackets.
        Matcher matcher = pattern.matcher(input);
        StringBuilder s = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(s, "");
        }
        matcher.appendTail(s);

        return s.toString();
    }

    private void getSentences(){
        sentences = new ArrayList<>();
        String[] splitSentences = inputText.split("[!?।;\\n]",0);
        for(String sentence:splitSentences){
            if(!sentence.isEmpty()) sentences.add(sentence);
        }
//        Collections.addAll(sentences, splitSentences);
    }

    private void getWords(){
        sentenceDividedIntoWords = new ArrayList<>();
        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

        for(String individualSentence: sentences){
            ArrayList<String> tokenizedSentence = new ArrayList<>();
            String[] tokens = tokenizer.tokenize(individualSentence);
            //whitespace removal doesn't remove some uncommon punctuations
            for (String token: tokens){
                String[] trimmed = token.split("[- ,“”\"()–\\[\\]]",0);
                for(String word: trimmed) if(!word.isEmpty()) tokenizedSentence.add(word);
            }

            sentenceDividedIntoWords.add(tokenizedSentence);
        }

    }

    private void stopWordRemoval(){
        for(ArrayList<String> sentence: sentenceDividedIntoWords){
            sentence.removeAll(List.of(stopWords));
        }
    }

    private ArrayList<float[]> getVectorField(){
        ArrayList<float[]> vectorField = new ArrayList<>();
        for (int i = 0; i<sentenceDividedIntoWords.size();i++){
            float[] indSentenceVectorField = new float[301];
            int totalWordsInTheSentence = sentenceDividedIntoWords.get(i).size();
            for (int j=0;j<sentenceDividedIntoWords.get(i).size();j++){
                float[] indWordVectorField = wvd.dataset.get(sentenceDividedIntoWords.get(i).get(j));

                if(indWordVectorField == null){
                    totalWordsInTheSentence --;
                    continue;
                }
                for (int k=0; k<300;k++){
                    indSentenceVectorField[k] +=  indWordVectorField[k];
                }
            }
            for (int k=0; k<300; k++){
                indSentenceVectorField[k] /= totalWordsInTheSentence;
            }
            indSentenceVectorField[300] = (float) i /sentenceDividedIntoWords.size();

            vectorField.add(indSentenceVectorField);
        }
        return vectorField;
    }

    private void printToken(){
        for (ArrayList<String> sentence : sentenceDividedIntoWords){
            for (String word : sentence){
                System.out.print(word+"|");
            }
            System.out.println();
        }
    }

    private void printVectorField(){
        for(int i=0; i<sentenceDividedIntoWords.size();i++){
            System.out.println(sentences.get(i));
            System.out.println(Arrays.toString(vectorField.get(i)));
        }
    }

    private void printMatrix(double[][] matrix){
        for (double[] row: matrix){
            for(double cell: row){
                System.out.printf("%4.3f  ",cell);
            }
            System.out.println();
        }
    }
}
