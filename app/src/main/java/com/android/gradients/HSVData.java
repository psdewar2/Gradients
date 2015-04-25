package com.android.gradients;

/**
 * Created by PSD on 4/20/15.
 */
public interface HSVData {
    public void sendSameHue(HSVDrawable hsvDrawable);
    public void sendSameSat(HSVDrawable hsvDrawable);
    public void getResults(HSVDrawable hsvDrawable);
}
