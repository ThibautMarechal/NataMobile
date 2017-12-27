package be.helmo.natamobile.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.service.SessionsService;
import be.helmo.natamobile.tools.ImageViewUrlBinder;

/**
 * Created by Maréchal Thibaut on 24-12-17.
 */

public class SessionListViewAdapter extends ArrayAdapter<Session> {
    private final List<Session> values;
    private final Context context;

    public SessionListViewAdapter(@NonNull Context context, List<Session> values) {
        super(context, R.layout.list_session, values);
        this.values = values;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_session, parent, false);


        TextView sesName = rowView.findViewById(R.id.list_ses_name);
        sesName.setText(values.get(position).getName());

        TextView sesNbrObs = rowView.findViewById(R.id.list_ses_nbr_obs);
        sesNbrObs.setText(String.valueOf(values.get(position).getObservations().size()));

        ImageView sesImg = rowView.findViewById(R.id.list_ses_img);
        ImageViewUrlBinder.bind(sesImg, values.get(position).getOnePicture());
        return rowView;
    }
}
