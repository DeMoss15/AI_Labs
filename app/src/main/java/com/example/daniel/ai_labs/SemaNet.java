package com.example.daniel.ai_labs;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Daniel on 29.09.2017.
 */

final public class SemaNet {

    private static SemaNet sPointerToNet = new SemaNet();//pointer Singleton
    private Set<String> mAnswers = new LinkedHashSet<>(); //messages for user
    private Map<String, String> mValues = new LinkedHashMap<>();//key values user's text and key word
    private String[] mSplitedText;// splited text
    private boolean mIsEnoughData = false;//status of button 'send'
    private Map<String, Integer> mAssociativeArray = new LinkedHashMap<>();//map of word and it's weight
    private String[] endingsArray;// array for word endings

    private SemaNet() {
        mAnswers.add("Какой текст будем анализировать?");
        mAnswers.add("На какое слово опираться?");
        mAnswers.add("Мавр сделал свое дело, Мавр может уходить!");

        endingsArray = ("ешь ете ишь ите ет ем ут ют ит им ат ят ой ёй ом ем яя ая ое ее ие ые " +
                "а я ы е у ю е и о").split(" ");
    }

    public static SemaNet getInstance() {
        return sPointerToNet;
    }

    public boolean isEnoughData() {
        return mIsEnoughData;
    }

    public String welcomeMessage() {
        mIsEnoughData = false;
        mValues = new LinkedHashMap<>();
        mAssociativeArray = new LinkedHashMap<>();
        return mAnswers.toArray()[0].toString();
    }

    public String generateAnswer(String userMessage) {

        userMessage = userMessage.toLowerCase();
        if (mValues.isEmpty()) {

            mValues.put("text", userMessage);
            splitText();
            return mAnswers.toArray()[1].toString();

        } else if (mValues.size() == 1) {

            mValues.put("word", userMessage);
            mIsEnoughData = true;
            return analyzeText();

        } else {
            return mAnswers.toArray()[2].toString();
        }
    }

    private String analyzeText() {
        /*TODO
        * Write here alghorithm of analyzing text
        * */
        for (int i = 0; i < mSplitedText.length; i++) {
            if (mValues.get("word").contains(mSplitedText[i])) {
                if (0 <= i-2)
                    addNewWeight(mSplitedText[i-2], 1);
                if (mSplitedText.length > i+2)
                    addNewWeight(mSplitedText[i+2], 1);
                if (0 <= i-1)
                    addNewWeight(mSplitedText[i-1], 2);
                if (mSplitedText.length > i+1)
                    addNewWeight(mSplitedText[i+1], 2);
            }
        }

        String res = "";

        mAssociativeArray = sortByValue(mAssociativeArray);

        if (!mAssociativeArray.isEmpty()){
            for (Map.Entry<String, Integer> i : mAssociativeArray.entrySet()) {
                res += i.getKey() + " : " + i.getValue() + "\n";
            }
        } else {
            res = "Введенное вами слово не найдено!";
        }

        return res;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map ){
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private void addNewWeight(String key, int weight) {
        if (!mAssociativeArray.isEmpty() && mAssociativeArray.containsKey(key)) {
            int value = mAssociativeArray.get(key);
            mAssociativeArray.put(key, value + weight);
        } else {
            mAssociativeArray.put(key, weight);
        }
    }

    private void splitText() {
        String text = mValues.get("text").toLowerCase();

        text = text.replaceAll("\\s", "_").replaceAll("\\W", "").replaceAll("_", " ");
        text = text.replaceAll("\\s.\\s", " ").replaceAll("\\s..\\s", " ");
        Log.d("Text", text);

        for (String ending : endingsArray)
            text = text.replaceAll(ending + "\\s", " ");

        mSplitedText = text.split(" ");

        /*for (String word : mSplitedText) {
            for (String ending : endingsArray){
                if (word.length() >= 4){
                    *//*if (replaceEnding(word, ending))
                        break;*//*
                    if (word.endsWith(ending)) {
                        word = word.substring(0, word.length() - ending.length() - 1);
                        break;
                    }
                }
            }
        }*/
    }

   /* private boolean replaceEnding(String word, String ending) {
        if (word.endsWith(ending)) {
            word = word.substring(0, word.length() - ending.length() - 1);
            return true;
        }
        return false;
    }*/
}
