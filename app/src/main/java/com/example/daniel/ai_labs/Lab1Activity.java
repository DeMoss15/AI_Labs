package com.example.daniel.ai_labs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Lab1Activity extends AppCompatActivity {

    private int x = 0;
    private int smoothing = 2;
    private int sensebility = 2;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);

        xValue = (TextView)findViewById(R.id.xValue);
        sensetiveValue = (TextView)findViewById(R.id.sensetiveValue);
        blondePercent = (TextView)findViewById(R.id.blondePercent);
        redPercent = (TextView)findViewById(R.id.redPercent);
        brunetePercent = (TextView)findViewById(R.id.brunetePercent);
        textResult = (TextView)findViewById(R.id.textResult);
        graph = (GraphView)findViewById(R.id.graph);

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

        final SeekBar xChanger = (SeekBar)findViewById(R.id.xChanger);
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

        final SeekBar smoothChanger = (SeekBar)findViewById(R.id.smoothChanger);
        smoothChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                smoothing = 2 + smoothChanger.getProgress() * 2;
                onChanges();
            }
        });

        final SeekBar sensetiveChanger = (SeekBar)findViewById(R.id.sensetiveChanger);
        sensetiveChanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sensebility = 2 + sensetiveChanger.getProgress() * 2;
                onChanges();
            }
        });
    }

    private void onChanges(){
        double[] valuesArray = {0.0, 0.0, 0.0};

        /*Update here canvas (graphics) and values*/
        valuesArray[0] = function(Blonde.getShift(), sensebility, smoothing, x);
        valuesArray[1] = function(Redhead.getShift(), sensebility, smoothing, x);
        valuesArray[2] = function(Brunete.getShift(), sensebility, smoothing, x);

        Blonde.setPercentage(valuesArray[0] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));
        Redhead.setPercentage(valuesArray[1] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));
        Brunete.setPercentage(valuesArray[2] * 100 /
                (valuesArray[0] + valuesArray[1] + valuesArray[2]));

        xValue.setText(Integer.toString(x));
        sensetiveValue.setText(Integer.toString(sensebility) + "/" + Integer.toString(smoothing));
        blondePercent.setText(Double.toString(Math.round(Blonde.getPercentage())) + "%");
        redPercent.setText(Double.toString(Math.round(Redhead.getPercentage()))  + "%");
        brunetePercent.setText(Double.toString(Math.round(Brunete.getPercentage()))  + "%");

        String output = "";
        output += toneSwitch(Blonde.getPercentage(), Blonde.getColorValue());
        output += toneSwitch(Redhead.getPercentage(), Redhead.getColorValue());
        output += toneSwitch(Brunete.getPercentage(), Brunete.getColorValue());

        textResult.setText(output);
        buildGraph();
    }

    private void buildGraph(){
        graph.removeAllSeries();
        seriesBlonde = new LineGraphSeries<DataPoint>();
        seriesRedhead = new LineGraphSeries<DataPoint>();
        seriesBrunete = new LineGraphSeries<DataPoint>();
        seriesX = new LineGraphSeries<DataPoint>();

        for (int i = 0; i <= 100; ++i) {
            seriesBlonde.appendData(
                    new DataPoint(i, function(Blonde.getShift(), sensebility, smoothing, i + 0.0)),
                    true,
                    100);
            seriesRedhead.appendData(
                    new DataPoint(i, function(Redhead.getShift(), sensebility, smoothing, i + 0.0)),
                    true,
                    100);
            seriesBrunete.appendData(
                    new DataPoint(i, function(Brunete.getShift(), sensebility, smoothing, i + 0.0)),
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
        graph.getViewport().setMaxX(150);

        //graph.getViewport().setScrollable(true);
        //graph.canScrollHorizontally(0);
    }

    private String toneSwitch(double perc, String hairColor){
        String res = "";
        if (perc < 1)
            return res;
        else if (perc < 20)
            res = "not";
        else if (perc < 40)
            res = "already not";
        else if (perc < 60)
            res = "either";
        else if (perc < 80)
            res = "still";
        else
            res = "already";
        return res + " " + hairColor + " ";
    }

    private double function(int a, int b, int c, double x){
        /*a - shift of func
        * b -
        * с -
        * x - value of seekBar
        * */
        double res;
        if (b != 0)
            res = 1.0/(1 + Math.pow(((x-a)/b), c));
        else
            res = 0;
        return res;
    }
}

