package com.android.gradients.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.gradients.HSVComponent;
import com.android.gradients.HSVData;
import com.android.gradients.HSVDrawable;
import com.android.gradients.adapters.HSVDrawableAdapter;
import com.android.gradients.R;

import java.util.ArrayList;

/**
 * Created by PSD on 4/19/15.
 */
public class SaturationFragment extends ListFragment {

    //ListView
    ListView lv;

    //interface object
    HSVData hsvData;

    //arraylist to be attached to the adapter
    private ArrayList<HSVDrawable> mList;
    private HSVDrawableAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_saturation, container, false);

        lv = (ListView)layout.findViewById(android.R.id.list);
        //ALWAYS attach adapter onCreateView
        mAdapter = new HSVDrawableAdapter(getActivity(), mList);
        setListAdapter(mAdapter);
        return layout;
    }

    @Override
    public void onListItemClick(ListView lv, final View v, final int position, long id) {
        hsvData.sendSameSat(mAdapter.getItem(position));
    }

    public void onAttach(Activity a) {
        super.onAttach(a);
        hsvData = (HSVData) a;
    }

    public void grabHueData(HSVDrawable hsvDrawable) {
        float leftHue = hsvDrawable.getLeft().getHue();
        float rightHue = hsvDrawable.getRight().getHue();

        mList = new ArrayList<>();
        for (float i = 10; i >= 0; i--) {
            //same hues, different saturations, same values
            HSVDrawable newDrawable =
                    new HSVDrawable(new HSVComponent(leftHue, i / 10.0f, 1),
                                    new HSVComponent(rightHue, i / 10.0f, 1));

            mList.add(newDrawable);

        }
    }

}
