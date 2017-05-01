package com.example.hp.myapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 4/10/2017.
 */

public class list extends ArrayAdapter<student> {
    private Activity context;
    List<student> artists;

    public list(Activity context, List<student> artists) {
        super(context, R.layout.mylayout, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.mylayout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.collg);

        student ss = artists.get(position);
        textViewName.setText(ss.getStdname());
        textViewGenre.setText(ss.getStdcollege());

        return listViewItem;
    }
}
