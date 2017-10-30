package com.example.daniel.ai_labs;

import android.util.Log;

import java.util.Random;

/**
 * Created by Daniel on 26.10.2017.
 */

public class Neuron {

    private final static Neuron pointer = new Neuron();

    private double[] mWeightsBefore = new double[7];
    private double[] mWeightsAfter = new double[7];
    private double mSum = 0.0f;
    private int mNumOfIterations = 0;
    private int mResultOfTraining;

    private Neuron(){
        for (double i: mWeightsBefore){
            i = 0.001 * (double)(new Random().nextInt(9));
            Log.d("Neuron", "random el: " + i);
        }
        mWeightsAfter = mWeightsBefore.clone();
    }

    public static Neuron getInstance(){
        return pointer;
    }

    public void Train(int expectedResult, double speed, int[] vecToTrain){

        mWeightsBefore = mWeightsAfter.clone();

        mNumOfIterations = 0;

        TrainingProcess(expectedResult, speed, vecToTrain);
    }

    private void TrainingProcess(int expectedResult, double speed, int[] vecToTrain){

        mNumOfIterations++;

        mSum = 0.0;

        for (int i = 0; i < mWeightsAfter.length; i++) {
            mSum += mWeightsAfter[i] * vecToTrain[i];
        }

        if (mSum < 1.0)
            mResultOfTraining = 0;
        else
            mResultOfTraining = 1;

        if (mResultOfTraining != expectedResult) {
            for (int i = 0; i < mWeightsAfter.length; i++) {
                mWeightsAfter[i] = mWeightsAfter[i] + speed
                        * (expectedResult - mResultOfTraining)
                        * vecToTrain[i];
            }
            TrainingProcess(expectedResult, speed, vecToTrain);
        }
    }

    public int getmNumOfIterations() {
        return mNumOfIterations;
    }

    public double[] getmWeightsBefore() {
        return mWeightsBefore;
    }

    public double[] getmWeightsAfter() {
        return mWeightsAfter;
    }

    public double getmSum() {
        return mSum;
    }

    public int getmResultOfTraining() {
        return mResultOfTraining;
    }

    public void reset() {
        mNumOfIterations = 0;
        for (double i: mWeightsBefore){
            i = 0.001 * ((double)(new Random().nextInt(8) + 1.0f));
            Log.d("Neuron", "random el: " + i);
        }
        mWeightsAfter = mWeightsBefore.clone();
        mSum = 0.0;
        mResultOfTraining = 0;
    }
}
