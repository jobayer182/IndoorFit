
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


public class ShoulderBackAdapter extends BaseAdapter {

    int[] ShoulderBackWorkoutImages;
    String[] ShoulderBackWorkoutNames;
    private final Context context;
    private LayoutInflater inflater;

    public ShoulderBackAdapter(Context context, String[] ShoulderBackWorkoutNames, int[] ShoulderBackWorkoutImages) {
        this.context = context;
        this.ShoulderBackWorkoutNames = ShoulderBackWorkoutNames;
        this.ShoulderBackWorkoutImages = ShoulderBackWorkoutImages;
    }

    @Override
    public int getCount() {
        return ShoulderBackWorkoutImages.length;
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
        imageView.setImageResource(ShoulderBackWorkoutImages[position]);

        TextView textView = convertView.findViewById(R.id.workoutNameId);
        textView.setText(ShoulderBackWorkoutNames[position]);

        return convertView;
    }
}