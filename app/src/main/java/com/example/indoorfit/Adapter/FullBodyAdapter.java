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


public class FullBodyAdapter extends BaseAdapter {

    int[] fullBodyWorkoutImages;
    String[] fullBodyWorkoutNames;
    private final Context context;
    private LayoutInflater inflater;

    public FullBodyAdapter(Context context, String[] fullBodyWorkoutNames, int[] fullBodyWorkoutImages) {
        this.context = context;
        this.fullBodyWorkoutNames = fullBodyWorkoutNames;
        this.fullBodyWorkoutImages = fullBodyWorkoutImages;
    }

    @Override
    public int getCount() {
        return fullBodyWorkoutImages.length;
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
        imageView.setImageResource(fullBodyWorkoutImages[position]);

        TextView textView = convertView.findViewById(R.id.workoutNameId);
        textView.setText(fullBodyWorkoutNames[position]);

        return convertView;
    }
}