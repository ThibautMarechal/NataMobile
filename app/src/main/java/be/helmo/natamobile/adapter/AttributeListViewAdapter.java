package be.helmo.natamobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Attribute;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class AttributeListViewAdapter extends ArrayAdapter<Attribute> {

	private final List<Attribute> values;
	private Context context;

	public AttributeListViewAdapter(Context context, List<Attribute> values) {
		super(context, R.layout.list_attribute, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_attribute, parent, false);
		//KEY ATTRIBUTE
		TextView attributeKeyTextView = rowView.findViewById(R.id.list_attribute_key_textView);
		attributeKeyTextView.setText(values.get(position).getKey());
		//VALUES ATTRIBUTE
		Spinner attributeValueSpinner = rowView.findViewById(R.id.list_attribute_value_spinner);
		String[] attributeArray = new String[values.get(position).getValues().size()];
		for (int i = 0; i < values.get(position).getValues().size(); i++) {
			attributeArray[i] = values.get(position).getValues().get(i);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, attributeArray);
		attributeValueSpinner.setAdapter(adapter);

		return rowView;
	}
}
