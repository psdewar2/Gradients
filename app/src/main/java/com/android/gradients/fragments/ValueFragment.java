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
public class ValueFragment extends ListFragment {

    //ListView
    ListView lv;

    //interface
    HSVData hsvData;

    private ArrayList<HSVDrawable> mList; //arraylist to be attached to the adapter
    private HSVDrawableAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_value, container, false);

        lv = (ListView)layout.findViewById(android.R.id.list);
        mAdapter = new HSVDrawableAdapter(getActivity(), mList);
        setListAdapter(mAdapter);
        return layout;
    }

    @Override
    public void onListItemClick(ListView lv, final View v, final int position, long id) {
        hsvData.getResults(mAdapter.getItem(position));


        /*
        v.animate().setDuration(500).alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                mAdapter.remove(position);
                v.setAlpha(1);
            }
        });
        */

    }

    public void onAttach(Activity a) {
        super.onAttach(a);
        hsvData = (HSVData) a;
    }

    public void grabSatData(HSVDrawable hsvDrawable) {
        float leftHue = hsvDrawable.getLeft().getHue();
        float rightHue = hsvDrawable.getRight().getHue();
        float saturation = hsvDrawable.takeSaturation(hsvDrawable);
        //create new list to display diff saturations
        mList = new ArrayList<>();
        for (float i = 10; i >= 0; i--) {
            //same hues, different saturations, same values
            HSVDrawable newDrawable =
                    new HSVDrawable(
                            new HSVComponent(leftHue, saturation, i / 10.0f),
                            new HSVComponent(rightHue, saturation, i / 10.0f));
            mList.add(newDrawable);
        }
    }
}

