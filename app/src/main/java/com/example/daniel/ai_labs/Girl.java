package com.example.daniel.ai_labs;

/**
 * Created by Daniel on 18.09.2017.
 */

final public class Girl {

    private int shift;
    private double percentage;
    private String hairColor;
    private static int smoothing = 2;
    private static int sensebility = 2;

    public Girl(int shiftP, String hairColorP, double percentageP) {
        shift = shiftP;
        percentage = percentageP;
        hairColor = hairColorP;
    }

    public double function(double x) {
        /*a - shift of func
        * b - sensebility
        * с - smoothing
        * x - value of seekBar
        * */
        double res;
        if (sensebility != 0)
            res = 1.0 / (1 + Math.pow(((x - this.getShift()) / sensebility), smoothing));
        else
            res = 0;
        return res;
    }

    String getColorValue() {
        return hairColor;
    }

    public String getTone() {
        String res = "";
        if (percentage < 1)
            return res;
        else if (percentage < 20)
            res = "not";
        else if (percentage < 40)
            res = "already not";
        else if (percentage < 60)
            res = "either";
        else if (percentage < 80)
            res = "still";
        else
            res = "already";
        return res + " " + hairColor + " ";
    }

    int getShift() {
        return shift;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double newPercentage) {
        percentage = newPercentage;
    }

    public static void setSmoothing(int smoothing1) {
        smoothing = smoothing1;
    }

    public static int getSmoothing() {
        return smoothing;
    }

    public static void setSensebility(int sensebility1) {
        sensebility = sensebility1;
    }

    public static int getSensebility() {
        return sensebility;
    }
}
