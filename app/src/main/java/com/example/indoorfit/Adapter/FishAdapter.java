package com.example.indoorfit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.indoorfit.R;

public class FishAdapter extends BaseAdapter {


    Context context;
    int[] nutritionImages;
    String[] nutritionNames;
    private LayoutInflater inflater;

    public FishAdapter(Context context, String[] nutritionNames, int[] nutritionImages){

        this.context = context;
        this.nutritionNames= nutritionNames;
        this.nutritionImages = nutritionImages;

    }
    @Override
    public int getCount() {
        return nutritionNames.length;
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

        if (convertView==null){

            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_nutrition,parent,false);


        }

        ImageView imageView = convertView.findViewById(R.id.imageViewId);
        TextView textView = convertView.findViewById(R.id.textViewId);

        imageView.setImageResource(nutritionImages[position]);
        textView.setText(nutritionNames[position]);

        return convertView;
    }
}
