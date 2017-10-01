package com.example.daniel.ai_labs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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

    private SemaNet() {
        mAnswers.add("Какой текст будем анализировать?");
        mAnswers.add("На какое слово опираться?");
        mAnswers.add("Мавр сделал свое дело, Мавр может уходить!");
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
            if (mSplitedText[i].equals(mValues.get("word"))) {
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

        if (!mAssociativeArray.isEmpty()){
            for (Map.Entry<String, Integer> i : mAssociativeArray.entrySet()) {
                res += i.getKey() + " : " + i.getValue() + "\n";
            }
        } else res = "Введенное вами слово не найдено!";

        return res;
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
        boolean isTextClear = false;
        while (!isTextClear) {
            if (text.contains(".")) {
                text = text.substring(0, text.indexOf(".")) +
                        text.substring(text.indexOf(".") + 1);
            } else  if (text.contains(",")) {
                text = text.substring(0, text.indexOf(",")) +
                        text.substring(text.indexOf(",") + 1);
            } else if (text.contains("!")) {
                text = text.substring(0, text.indexOf("!")) +
                        text.substring(text.indexOf("!") + 1);
            } else  if (text.contains("?")) {
                text = text.substring(0, text.indexOf("?")) +
                        text.substring(text.indexOf("?") + 1);
            } else if (text.contains(";")) {
                text = text.substring(0, text.indexOf(";")) +
                        text.substring(text.indexOf(";") + 1);
            } else if (text.contains(":")) {
                text = text.substring(0, text.indexOf(":")) +
                        text.substring(text.indexOf(":") + 1);
            } else isTextClear = true;
        }
        mSplitedText = text.split(" ");
    }
}
