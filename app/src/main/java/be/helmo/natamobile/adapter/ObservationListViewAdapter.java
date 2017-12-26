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

import java.util.List;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Observation;

/**
 * Created by Maréchal Thibaut on 25/12/2017.
 */

public class ObservationListViewAdapter extends ArrayAdapter<Observation> {
    private final List<Observation> values;
    private final Context context;

    public ObservationListViewAdapter(@NonNull Context context, List<Observation> values) {
        super(context, R.layout.list_observation, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_observation, parent, false);


        TextView birdName = rowView.findViewById(R.id.list_obs_bird_name);
        birdName.setText(values.get(position).getBird().getName());

        TextView obsNumBird = rowView.findViewById(R.id.list_obs_number_bird);
        obsNumBird.setText(values.get(position).getNumberOfBird());

        ImageView sesImg = rowView.findViewById(R.id.list_obs_img);
        //TODO WHAT IF IT'S A VIDEO OR AUDIO ?
        //sesImg.setImageURI(Uri.parse(values.get(position).getFilePath()));
        return rowView;
    }
}
