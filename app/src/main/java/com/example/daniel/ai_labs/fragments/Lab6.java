package com.example.daniel.ai_labs.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daniel.ai_labs.Population;
import com.example.daniel.ai_labs.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lab6 extends Fragment {

    public final static int sNUMBER_OF_VAL = 100;
    @BindView(R.id.graph_adoptation)
    GraphView mGraph;
    @BindView(R.id.button_reset)
    Button mButtonReset;
    @BindView(R.id.button_adopt)
    Button mButtonAdopt;

    LineGraphSeries<DataPoint> mSeriesFunction;
    LineGraphSeries<DataPoint> mSeriesAdoptation;

    Population mPopulation = Population.getInstance();

    double[] mValuesOfFunction = new double[sNUMBER_OF_VAL];

    public Lab6() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lab6, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set manual X bounds
        mGraph.getViewport().setYAxisBoundsManual(true);
        mGraph.getViewport().setMinY(-1);
        mGraph.getViewport().setMaxY(1);

        mGraph.getViewport().setXAxisBoundsManual(true);
        mGraph.getViewport().setMinX(0);
        mGraph.getViewport().setMaxX(10);

        // enable scaling and scrolling
        mGraph.getViewport().setScalable(true);
        mGraph.getViewport().setScalableY(false);

        // creating array with values of function and series of it for ghaph
        mSeriesFunction = new LineGraphSeries<>();
        mSeriesFunction.setColor(Color.CYAN);

        for (int i = 0; i < sNUMBER_OF_VAL; i++){
            double x = i * 0.1f;
            mValuesOfFunction[i] = myFunction(x);
            mSeriesFunction.appendData(
                    new DataPoint(x, mValuesOfFunction[i]),
                    true,
                    sNUMBER_OF_VAL);
        }

        buildGraph();

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_adopt: {
                        mPopulation.setFunctionValues(mValuesOfFunction);
                        adoptCycle();
                    }
                }
            }
        };

        mButtonAdopt.setOnClickListener(listener);
        mButtonReset.setOnClickListener(listener);
    }

    private void adoptCycle() {
        while (!mPopulation.isAdopted()) {
            mSeriesAdoptation = mPopulation.getSeries();
            buildGraph();
        }
    }

    private void buildGraph() {
        mGraph.removeAllSeries();

//        mGraph.addSeries(mSeriesAdoptation);
        mGraph.addSeries(mSeriesFunction);
    }

    private double myFunction(double x) {
        return Math.sin(x * 3.0f);
    }
}
