package be.helmo.natamobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import be.helmo.natamobile.R;

/**
 * Created by Maréchal Thibaut on 25/12/2017.
 */

public class ObservationListViewAdapter<T> extends ArrayAdapter<T> {
    public ObservationListViewAdapter(@NonNull Context context, String [][] values) {
        super(context, R.layout.list_session, (T[]) values);
    }
}
