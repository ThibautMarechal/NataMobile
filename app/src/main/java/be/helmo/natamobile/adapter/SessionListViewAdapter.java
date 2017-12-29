package be.helmo.natamobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Session;

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
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_session, parent, false);


		TextView sesName = rowView.findViewById(R.id.list_ses_name);
		sesName.setText(values.get(position).getName());

		TextView sesNbrObs = rowView.findViewById(R.id.list_ses_nbr_obs);
		sesNbrObs.setText(String.valueOf(values.get(position).getObservations().size()));

		ImageView sesImg = rowView.findViewById(R.id.list_ses_img);
		Picasso.with(sesImg.getContext()).load(values.get(position).getOnePicture()).centerCrop().fit().into(sesImg);
		return rowView;
	}
}
