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
 *  1 define the data source
 *  2 tell adapter how to display the data by specifying a layout
 *  3 define what happens when user interacts with a row in the list view
 */
public class HueFragment extends ListFragment {
    //use setListAdapter when extending ListFragment, use listview.setAdapter when extending fragment

    //interface object
    HSVData hsvData;

    //ListView
    ListView lv;

    private ArrayList<HSVDrawable> mList; //arraylist to be attached to the adapter
    private HSVDrawableAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_hue, container, false);

        lv = (ListView)layout.findViewById(android.R.id.list);

        if (mList == null) {
            mList = new ArrayList<>();
            //first color for the mList
            HSVDrawable hsvd1 = new HSVDrawable(new HSVComponent(345, 1, 1), new HSVComponent(15, 1, 1));
            mList.add(hsvd1);
            for (int i = 15; i < 345; i+=30) {
                //different hues, same saturations, same values
                HSVDrawable hsvd = new HSVDrawable(new HSVComponent(i, 1, 1), new HSVComponent(i + 30, 1, 1));
                mList.add(hsvd);

            }
        }

        if (mAdapter == null) {
            mAdapter = new HSVDrawableAdapter(getActivity(), mList);
        }

        setListAdapter(mAdapter);
        return layout;
    }

    @Override
    public void onListItemClick(ListView lv, final View v, final int position, long id) {
        //create new fragment
        //hsvData.hello("Hi");
        hsvData.sendSameHue(mAdapter.getItem(position));
        //Log.d("Chose item w/ left hue", " " + mAdapter.getItem(position).getLeft().getHue());

    }

    public void onAttach(Activity a) {
        super.onAttach(a);
        hsvData = (HSVData) a;
    }

}
