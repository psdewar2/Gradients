package com.android.gradients;

import android.graphics.drawable.GradientDrawable;

/**
 * Created by PSD on 4/19/15.
 */
public class HSVDrawable {
    private HSVComponent left, right;

    public HSVDrawable(HSVComponent left, HSVComponent right) {
        this.left = left;
        this.right = right;

    }
    public HSVComponent getLeft() { return left; }
    public HSVComponent getRight() { return right; }
    public void setLeft(HSVComponent hsvComponentLeft) { this.left = hsvComponentLeft; }
    public void setRight(HSVComponent hsvComponentRight) { this.right = hsvComponentRight; }

    public GradientDrawable generateGradientDrawable() { //makes a HSVDrawable object and grabs left and right components
        HSVDrawable newDrawable = new HSVDrawable(left, right);
        return new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[] {newDrawable.getLeft().convertToColor(), newDrawable.getRight().convertToColor()});
        //use 5 colors for RGB interpolation
    }

    public float takeSaturation(HSVDrawable hsvDrawable) {
        if (hsvDrawable.getLeft().getSaturation() == hsvDrawable.getRight().getSaturation()) {
            return hsvDrawable.getLeft().getSaturation();
        }
        return -1;
    }

    public float takeValue(HSVDrawable hsvDrawable) {
        if (hsvDrawable.getLeft().getValue() == hsvDrawable.getRight().getValue()) {
            return hsvDrawable.getLeft().getValue();
        }
        return -1;
    }

    /*
    public static GradientDrawable generateGradient(HSVComponent left, HSVComponent right) {
        //set default values for now


        //get them
        //left = new HSVComponent(left.getHue(), left.getSaturation(), left.getValue());
        //right = new HSVComponent(right.getHue(), right.getSaturation(), right.getValue());

        return new GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        new int[] {left.convertToColor(), right.convertToColor()});
    }
    */

}
