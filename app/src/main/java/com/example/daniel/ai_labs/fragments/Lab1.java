package com.example.daniel.ai_labs.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.daniel.ai_labs.Girl;
import com.example.daniel.ai_labs.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Lab1 extends Fragment {

    private int x = 0;
    Girl Blonde, Redhead, Brunete;
    TextView xValue;
    TextView sensetiveValue;
    TextView blondePercent;
    TextView redPercent;
    TextView brunetePercent;
    TextView textResult;
    LineGraphSeries<DataPoint> seriesBlonde;
    LineGraphSeries<DataPoint> seriesRedhead;
    LineGraphSeries<DataPoint> seriesBrunete;
    LineGraphSeries<DataPoint> seriesX;
    GraphView graph;

    private OnFragmentInteractionListener mListener;

    public Lab1() {
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
        return inflater.inflate(R.layout.fragment_lab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        xValue = (TextView) view.findViewById(R.id.xValue);
        sensetiveValue = (TextView) view.findViewById(R.id.sensetiveValue);
        blondePercent = (TextView) view.findViewById(R.id.blondePercent);
        redPercent = (TextView) view.findViewById(R.id.redPercent);
        brunetePercent = (TextView) view.findViewById(R.id.brunetePercent);
        textResult = (TextView) view.findViewById(R.id.textResult);
        graph = (GraphView) view.findViewById(R.id.graph);

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1.5);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(false);

        Blonde = new Girl(0, "Blonde", 100.0);
        Redhead = new Girl(50, "Redhead", 0);
        Brunete = new Girl(100, "Brunete", 0);

        onChanges();
        buildGraph();

        final SeekBar xChanger = (SeekBar) view.findViewById(R.id.xChanger);
        xChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                x = xChanger.getProgress();
                onChanges();
            }
        });

        final SeekBar smoothChanger = (SeekBar) view.findViewById(R.id.smoothChanger);
        smoothChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Girl.setSmoothing(2 + smoothChanger.getProgress() * 2);
                onChanges();
            }
        });

        final SeekBar sensetiveChanger = (SeekBar) view.findViewById(R.id.sensetiveChanger);
        sensetiveChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Girl.setSensebility(2 + sensetiveChanger.getProgress() * 2);
                onChanges();
            }
        });
    }

    private void onChanges() {
        double[] valuesArray = {0.0, 0.0, 0.0};

        /*Update here canvas (graphics) and values*/
        valuesArray[0] = Blonde.function(x);
        valuesArray[1] = Redhead.function(x);
        valuesArray[2] = Brunete.function(x);
        double summ = valuesArray[0] + valuesArray[1] + valuesArray[2];

        Blonde.setPercentage(valuesArray[0] * 100 / summ);
        Redhead.setPercentage(valuesArray[1] * 100 / summ);
        Brunete.setPercentage(valuesArray[2] * 100 / summ);

        xValue.setText(Integer.toString(x));
        sensetiveValue.setText(Integer.toString(Girl.getSensebility()) + "/"
                + Integer.toString(Girl.getSmoothing()));
        blondePercent.setText(Double.toString(Math.round(Blonde.getPercentage())) + "%");
        redPercent.setText(Double.toString(Math.round(Redhead.getPercentage())) + "%");
        brunetePercent.setText(Double.toString(Math.round(Brunete.getPercentage())) + "%");

        String output = "";
        output += Blonde.getTone();
        output += Redhead.getTone();
        output += Brunete.getTone();

        textResult.setText(output);
        buildGraph();
    }

    private void buildGraph() {
        graph.removeAllSeries();
        seriesBlonde = new LineGraphSeries<DataPoint>();
        seriesRedhead = new LineGraphSeries<DataPoint>();
        seriesBrunete = new LineGraphSeries<DataPoint>();
        seriesX = new LineGraphSeries<DataPoint>();

        for (int i = 0; i <= 100; ++i) {
            seriesBlonde.appendData(
                    new DataPoint(i, Blonde.function(i * 1.0f)),
                    true,
                    100);
            seriesRedhead.appendData(
                    new DataPoint(i, Redhead.function(i * 1.0f)),
                    true,
                    100);
            seriesBrunete.appendData(
                    new DataPoint(i, Brunete.function(i * 1.0f)),
                    true,
                    100);
            seriesX.appendData(
                    new DataPoint(x, i - 1),
                    true,
                    100);
        }

        seriesX.setColor(Color.GREEN);
        seriesBrunete.setColor(Color.BLACK);
        seriesBlonde.setColor(Color.YELLOW);
        seriesRedhead.setColor(Color.RED);
        graph.addSeries(seriesBlonde);
        graph.addSeries(seriesRedhead);
        graph.addSeries(seriesBrunete);
        graph.addSeries(seriesX);

        graph.getViewport().setMaxY(1);
        graph.getViewport().setMaxX(100);

        //graph.getViewport().setScrollable(true);
        //graph.canScrollHorizontally(0);
    }


        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
