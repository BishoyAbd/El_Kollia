package com.projects.cactus.el_kollia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.cactus.el_kollia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 5/8/2017.
 */
public  class MyCustomSpinnerAdapter extends ArrayAdapter<ArrayList> {
    private List objectsList = new ArrayList<String>();

    Context context;
    public MyCustomSpinnerAdapter(Context context, int textViewResourceId, List objects) {
        super(context, textViewResourceId, objects);
        this.objectsList = objects;
        this.context=context;

        // System.out.println("mak" + objectsList.size());
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        //view show after click spinner
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView1(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_drop_down_spinner, parent,
                false);
        LinearLayout rowTitle = (LinearLayout) rowView
                .findViewById(R.id.title);

        TextView textView = (TextView) rowView.findViewById(R.id.custom_spinner);

        textView.setText(objectsList.get(position).toString().trim());
        return rowView;
    }

    public View getCustomView1(int position, View convertView,
                               ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_drop_down_spinner, parent,
                false);
        LinearLayout rowTitle = (LinearLayout) rowView
                .findViewById(R.id.title);
        TextView textView = (TextView) rowView.findViewById(R.id.custom_spinner);
        textView.setText(objectsList.get(position).toString().trim());
        // System.out.println("position " + position);
        return rowView;
    }
}