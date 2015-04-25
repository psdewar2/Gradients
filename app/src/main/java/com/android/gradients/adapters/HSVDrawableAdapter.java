package com.android.gradients.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.gradients.HSVDrawable;
import com.android.gradients.R;

import java.util.List;

public class HSVDrawableAdapter extends ArrayAdapter<HSVDrawable> {
    private final List<HSVDrawable> gradientList;

    public HSVDrawableAdapter(Context context, List<HSVDrawable> objects) {
        super(context, 0, objects);

        this.gradientList = objects;
    }

    private static class ViewHolder {
        TextView preview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // cache views to avoid future lookups.

        // Check if an existing View is being recycled; if not,
        // inflate a new one.
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.hues, parent, false);

            // Find the (sub)Views to populate with data.
            viewHolder.preview = (TextView) convertView.findViewById(R.id.hue);

            // cache the views
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate those (sub)Views with data
        HSVDrawable gDrawable = getItem(position);
        viewHolder.preview.setBackground(gDrawable.generateGradientDrawable());

        return convertView;
    }

    public void remove(int position) {
        gradientList.remove(position);
        notifyDataSetChanged();
    }

}
