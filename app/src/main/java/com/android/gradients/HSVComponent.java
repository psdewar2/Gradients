package com.android.gradients;

import android.graphics.Color;

/**
 * Created by PSD on 4/19/15.
 * Took me long enough...
 */
public class HSVComponent {
    private float hue, saturation, value;
    private float[] hsv;

    public HSVComponent(float hue, float saturation, float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
        hsv = new float[]{this.hue, this.saturation, this.value};
    }

    public float getHue() {return hue;}
    public float getSaturation() {return saturation;}
    public float getValue() {return value;}
    public float[] getHsv() {return hsv;}

    public void setHue(float hue) {this.hue = hue;}
    public void setSaturation(float saturation) {this.saturation = saturation;}
    public void setValue(float value) {this.value = value;}


    public int convertToColor() {
        return Color.HSVToColor(hsv);
    }

    public String toString() {
        return hsv[0] + "is my hue";
    }
}
