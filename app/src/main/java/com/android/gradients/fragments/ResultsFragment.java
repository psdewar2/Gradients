package com.android.gradients.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gradients.HSVComponent;
import com.android.gradients.HSVDrawable;
import com.android.gradients.adapters.HSVDrawableAdapter;
import com.android.gradients.R;

import java.util.ArrayList;
import java.util.List;


public class ResultsFragment extends ListFragment {
    TextView mHue, mSaturation, mValue;

    //ListView
    ListView lv;

    private List<HSVDrawable> mList;
    private HSVDrawableAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_results, container, false);

        mHue = (TextView) layout.findViewById(R.id.hueView);
        mSaturation = (TextView) layout.findViewById(R.id.satView);
        mValue = (TextView) layout.findViewById(R.id.valView);
        lv = (ListView) layout.findViewById(android.R.id.list);

        //data from bundle
        mHue.setText(getArguments().getString("HUE"));
        mSaturation.setText(getArguments().getString("SAT"));
        mValue.setText(getArguments().getString("VAL"));

        //data for list
        mAdapter = new HSVDrawableAdapter(getActivity(), mList);
        setListAdapter(mAdapter);

        return layout;
    }

    public void onListItemClick(ListView lv, final View v, final int position, long id) {
        if (position != 0) {
            float hue = mAdapter.getItem(position).getLeft().getHue();
            float sat = mAdapter.getItem(position).takeSaturation(mAdapter.getItem(position));
            float val = mAdapter.getItem(position).takeValue(mAdapter.getItem(position));
            Toast resultToast = Toast.makeText(getActivity(),
                    "Hue: " + hue + "\nSaturation: " + sat + "\nValue: " + val , Toast.LENGTH_SHORT);
            resultToast.show();

        }
    }

    public void makeSimilarColors(HSVDrawable hsvDrawable) {
        float leftHue = hsvDrawable.getLeft().getHue();
        float rightHue = hsvDrawable.getRight().getHue();
        float sat = hsvDrawable.takeSaturation(hsvDrawable);
        float val = hsvDrawable.takeValue(hsvDrawable);

        float delta = 10;

        mList = new ArrayList<>();
        HSVDrawable newDrawable =
                new HSVDrawable(new HSVComponent(leftHue, sat, val), new HSVComponent(rightHue, sat, val));
        mList.add(newDrawable);

        HSVDrawable newDrawable2 =
                new HSVDrawable(new HSVComponent(rightHue, sat + .1f, val - .1f), new HSVComponent(rightHue, sat + .1f, val - .1f));
        mList.add(newDrawable2);

        HSVDrawable newDrawable3 =
                new HSVDrawable(new HSVComponent(rightHue, sat - .1f, val + .1f), new HSVComponent(rightHue, sat - .1f, val + .1f));
        mList.add(newDrawable3);

    }

}
