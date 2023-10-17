package com.example.indoorfit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.indoorfit.R;


public class AbsAdapter extends BaseAdapter {

    int[] absWorkoutImages;
    String[] absWorkoutNames;
    private final Context context;
    private LayoutInflater inflater;

    public AbsAdapter(Context context, String[] fullBodWorkoutNames, int[] fullBodyWorkoutImages) {
        this.context = context;
        this.absWorkoutNames = fullBodWorkoutNames;
        this.absWorkoutImages = fullBodyWorkoutImages;
    }

    @Override
    public int getCount() {
        return absWorkoutImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_workout, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.workoutImageId);
        imageView.setImageResource(absWorkoutImages[position]);

        TextView textView = convertView.findViewById(R.id.workoutNameId);
        textView.setText(absWorkoutNames[position]);

        return convertView;
    }
}