package com.example.daniel.ai_labs;

import android.util.Log;

import java.util.Random;

/**
 * Created by Daniel on 26.10.2017.
 */

public final class Neuron {

    private final static Neuron pointer = new Neuron();

    private double[] mWeightsBefore = new double[7];
    private double[] mWeightsAfter = new double[7];
    private double mSum = 0.0f;
    private int mNumOfIterations = 0;
    private int mResultOfTraining;

    private Neuron(){
        reset();
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

    public int getNumOfIterations() {
        return mNumOfIterations;
    }

    public double[] getWeightsBefore() {
        return mWeightsBefore;
    }

    public double[] getWeightsAfter() {
        return mWeightsAfter;
    }

    public double getSum() {
        return mSum;
    }

    public int getResultOfTraining() {
        return mResultOfTraining;
    }

    public void reset() {
        for (int i = 0; i < mWeightsAfter.length; i++){
            mWeightsAfter[i] = 0.001 * ((double)(new Random().nextInt(8) + 1.0f));
            Log.d("Neuron", "random el: " + mWeightsAfter[i]);
        }
        mWeightsAfter = mWeightsBefore.clone();
        mWeightsAfter[1] += 0.001;
        mSum = 0.0;
        mNumOfIterations = 0;
        mResultOfTraining = 0;
    }
}
