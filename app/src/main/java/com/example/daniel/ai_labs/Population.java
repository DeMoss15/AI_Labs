package com.example.daniel.ai_labs;

import android.util.Log;

import com.example.daniel.ai_labs.fragments.Lab6;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Daniel on 03.11.2017.
 */

public final class Population {

    final static int sNUMBER_OF_VAL = Lab6.sNUMBER_OF_VAL;
    private static final Population sPOINTER = new Population();
    private List<Person> mPopulationArray = new ArrayList<>();

    private Population() {
        reset();
    }

    public static Population getInstance() {
        return sPOINTER;
    }

    public boolean isAdopted() {
        if (mPopulationArray.get(0).getS() <= 1.0f)
            return true;
        mutate();
        return false;
    }

    public void setFunctionValues(double[] FunctionValues) {
        Person.setFunctionValues(FunctionValues);
    }

    private void mutate() {
        // sorting values
        Collections.sort(mPopulationArray, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Double.compare(p1.getS(), p2.getS());
            }
        });

        // mutate
        for (int i = 0; i < 10; i++) {
            mPopulationArray.get(10 + i).mutatePerson(mPopulationArray.get(i));
        }

        // logs
        for (Person p : mPopulationArray) {
            Log.d("Population", "" + p.getS());
        }
    }

    public DataPoint[] getSeries() {
        List<DataPoint> newSeriesData = new ArrayList<>();
        double[] values = mPopulationArray.get(0).getValues();

        for (int i = 0; i < sNUMBER_OF_VAL; i++) {
            double x = i * 0.1f;
            newSeriesData.add(new DataPoint(x, values[i]));
        }

        return newSeriesData.toArray(new DataPoint[sNUMBER_OF_VAL]);
    }

    public void reset() {
        mPopulationArray = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mPopulationArray.add(new Person());
        }
    }
}
