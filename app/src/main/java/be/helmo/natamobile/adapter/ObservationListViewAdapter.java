package be.helmo.natamobile.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import be.helmo.natamobile.R;

/**
 * Created by Maréchal Thibaut on 25/12/2017.
 */

public class ObservationListViewAdapter<T> extends ArrayAdapter<T> {
    private final String[][] values;
    private final Context context;

    public ObservationListViewAdapter(@NonNull Context context, String [][] values) {
        super(context, R.layout.list_observation, (T[]) values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_observation, parent, false);


        TextView birdName = rowView.findViewById(R.id.list_obs_bird_name);
        birdName.setText(values[position][0]);

        TextView obsNumBird = rowView.findViewById(R.id.list_obs_number_bird);
        obsNumBird.setText(values[position][1]);

        ImageView sesImg = rowView.findViewById(R.id.list_obs_img);
        sesImg.setImageURI(Uri.parse(values[position][2]));
        return rowView;
    }
}
