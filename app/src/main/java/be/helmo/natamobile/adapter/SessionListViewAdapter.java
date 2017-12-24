package be.helmo.natamobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import be.helmo.natamobile.R;

/**
 * Created by Maréchal Thibaut on 24-12-17.
 */

public class SessionListViewAdapter<T> extends ArrayAdapter<T> {
    private final String[][] values;
    private final Context context;

    public SessionListViewAdapter(@NonNull Context context, String [][] values) {
        super(context, R.layout.list_session, (T[]) values);
        this.values = values;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_session, parent, false);

        //Set Schedule Name
        TextView sesName = rowView.findViewById(R.id.list_ses_name);
        sesName.setText(values[position][0]);
        //Set Schedule Trigger Description
        TextView sesNbrObs = rowView.findViewById(R.id.list_ses_nbr_obs);
        sesNbrObs.setText(values[position][1]);
        //Set Schedul Hour
        ImageView sesImg = rowView.findViewById(R.id.list_ses_img);
        //TODO IMAGE FROM INTERNET

        return rowView;
    }
}
