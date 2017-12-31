package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.HashMap;

import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.AttributeListViewAdapter;
import be.helmo.natamobile.controller.implementations.IdentifyHelperController;
import be.helmo.natamobile.controller.interfaces.IIdentifyHelperController;
import be.helmo.natamobile.models.Attribute;
import be.helmo.natamobile.view.interfaces.IIdentifyHelperView;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class IdentifyHelperActivity extends AbstractActivity implements IIdentifyHelperView {
	private static final int REQUEST_BIRD_IDENTIFY = 1;
	private ListView attributeListView;
	private IIdentifyHelperController controller;
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.identify_helper);
		controller = new IdentifyHelperController(this);

		updateAttributes();

		searchButton = findViewById(R.id.identify_helper_search_button);
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				searchForBirds();
			}
		});
	}

	private void searchForBirds() {
		HashMap<String, String> searchAttributes = new HashMap<>();
		for (int i = 0; i < attributeListView.getCount(); i++) {
			View row = attributeListView.getChildAt(i);
			String key = ((Attribute) attributeListView.getItemAtPosition(i)).getKey();
			Spinner attrSpinner = row.findViewById(R.id.list_attribute_value_spinner);
			String value = attrSpinner.getSelectedItem().toString();
			searchAttributes.put(key, value);
		}
		Intent birdIdentifier = new Intent(this, IdentifyBirdActivity.class);
		birdIdentifier.putExtra("mapAttributes", searchAttributes);
		startActivityForResult(birdIdentifier, REQUEST_BIRD_IDENTIFY);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_BIRD_IDENTIFY && resultCode == RESULT_OK) {
			//READ BIRD NAME FROM EXTRA
			//GO BACK TO PREVIOUS INTENT WITH BIRD NAME IN EXTRA
			setResult(RESULT_OK, intent);
			finish();
		}
	}

	@Override
	public void updateAttributes() {
		attributeListView = findViewById(R.id.attributeHelperListView);
		AttributeListViewAdapter adapter = new AttributeListViewAdapter(this, controller.getAttributes());
		attributeListView.setAdapter(adapter);
	}
}
