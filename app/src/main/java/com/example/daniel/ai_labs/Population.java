package com.example.daniel.ai_labs;

import android.util.Log;

import com.example.daniel.ai_labs.fragments.Lab6;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
    private LineGraphSeries<DataPoint> mSeries = new LineGraphSeries<>();

    private Population() {
        for (int i = 0; i < 20; i++) {
            mPopulationArray.add(new Person());
        }
    }

    public static Population getInstance() {
        return sPOINTER;
    }

    public boolean isAdopted() {
        for (Person i:mPopulationArray) {
            if (i.getS() <= 1.0) {
                return true;
            }
        }
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
            ((Person)mPopulationArray.toArray()[10+i]).mutatePerson((Person)mPopulationArray.toArray()[i]);
        }

        // logs
        for (Person p : mPopulationArray) {
            Log.d("Population", "" + p.getS());
        }

        // create series
    }

    public LineGraphSeries<DataPoint> getSeries() {
        return mSeries;
    }
}
