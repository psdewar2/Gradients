package com.android.gradients;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.gradients.fragments.HueFragment;
import com.android.gradients.fragments.ResultsFragment;
import com.android.gradients.fragments.SaturationFragment;
import com.android.gradients.fragments.ValueFragment;

/**
 * Created by Peyt Spencer Dewar
 * Gradients App
 * Android App Dev Final project
 * April 2015
 */

public class GradientsActivity extends ActionBarActivity implements HSVData {
    private Button button;
    private FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradients);

        makeHueFragment();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HueFragment hueFragment = (HueFragment) getSupportFragmentManager().findFragmentById(R.layout.fragment_hue);
                if (hueFragment != null) {
                    Toast.makeText(getApplicationContext(), "STOP!", Toast.LENGTH_SHORT).show();
                } else {
                    makeHueFragment();

                }
            }
        });

        if (getSupportFragmentManager().isDestroyed()) {
            button.setVisibility(View.VISIBLE);
        }

    }

    //returns hue fragment
    public Fragment makeHueFragment() {
        HueFragment hueFragment = new HueFragment();
        //adding to back stack removes the fragment without removing the activity
        fm.beginTransaction().add(R.id.container, hueFragment).addToBackStack(null).commit();
        return hueFragment;
    }

    public Fragment makeSaturationFragment() {
        SaturationFragment satFragment = new SaturationFragment();
        fm.beginTransaction().replace(R.id.container, satFragment).addToBackStack(null).commit();
        return satFragment;
    }

    public Fragment makeValueFragment() {
        ValueFragment valFragment = new ValueFragment();
        fm.beginTransaction().replace(R.id.container, valFragment).addToBackStack(null).commit();
        return valFragment;
    }

    public Fragment makeResultsFragment() {
        ResultsFragment resFragment = new ResultsFragment();
        fm.beginTransaction().replace(R.id.container, resFragment).addToBackStack(null).commit();
        return resFragment;
    }



    //INTERFACE METHODS
    //Hue -----> Sat
    @Override
    public void sendSameHue(HSVDrawable hsvDrawable) {
        //get Saturation Fragment
        SaturationFragment satFragment = (SaturationFragment) makeSaturationFragment();
        //call fragment method
        satFragment.grabHueData(hsvDrawable);
    }

    //Sat -----> Val
    @Override
    public void sendSameSat(HSVDrawable hsvDrawable) {
        //get Value Fragment
        ValueFragment valFragment = (ValueFragment) makeValueFragment();
        //call fragment method
        valFragment.grabSatData(hsvDrawable);
    }

    //GET TOTAL RESULTS
    @Override
    public void getResults(HSVDrawable hsvDrawable) {
        //to send data from Fragment to fragment
        Bundle bundle = new Bundle();

        ResultsFragment resFragment = (ResultsFragment) makeResultsFragment();
        bundle.putString("HUE", "Hue Range: " + (int) hsvDrawable.getLeft().getHue() + "° - " + (int) hsvDrawable.getRight().getHue() + "°");
        bundle.putString("SAT", "Saturation: " + (int) (hsvDrawable.takeSaturation(hsvDrawable) * 100) + "%");
        bundle.putString("VAL", "Value: " + (int) (hsvDrawable.takeValue(hsvDrawable) * 100) + "%");
        resFragment.setArguments(bundle);
        button.setVisibility(View.GONE);

        resFragment.makeSimilarColors(hsvDrawable);

    }
}
