package com.example.daniel.ai_labs;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Daniel on 03.11.2017.
 */

final class Person {

    private final static int sNUMBER_OF_VAL = Population.sNUMBER_OF_VAL;
    private final static double sRANGE_MIN = -1.5;
    private final static double sRANGE_MAX = 1.5;
    private final static double sDELTA = 0.009f;//1.0f - new Random().nextInt(3) * 0.009f;
    private static double[] mFunctionValues;
    private double[] mValues = new double[sNUMBER_OF_VAL];

    Person() {
        for (int i = 0; i < sNUMBER_OF_VAL; i++) {
            mValues[i] = sRANGE_MIN + (sRANGE_MAX - sRANGE_MIN) * new Random().nextDouble();
        }
    }

    static void setFunctionValues(double[] mFunctionValues) {
        Person.mFunctionValues = mFunctionValues;
    }

    double[] getValues(){
        return mValues;
    }

    double getS() {
        double S = 0.0;

        for (int i = 0; i < sNUMBER_OF_VAL; i++) {
            S += Math.abs(mValues[i] - mFunctionValues[i]);
        }

        return S;
    }

    void mutatePerson(Person female) {
        // mutation for Person
        Set<Integer> mutatedValues = new LinkedHashSet<>();

        while(mutatedValues.size() != 10) {
            mutatedValues.add(new Random().nextInt(100));
        }

        for (int i : mutatedValues) {
            if (this.mValues[i] - mFunctionValues[i] > 0) {
                this.mValues[i] = female.getValues()[i] - sDELTA;
            } else {
                this.mValues[i] = female.getValues()[i] + sDELTA;
            }
        }
    }
}
