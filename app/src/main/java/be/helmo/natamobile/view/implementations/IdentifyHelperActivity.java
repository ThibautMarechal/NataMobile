package be.helmo.natamobile.view.implementations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

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
    private ListView attributeListView;
    private IIdentifyHelperController controller;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_helper);
        controller = new IdentifyHelperController();

        attributeListView = findViewById(R.id.attributeHelperListView);
        AttributeListViewAdapter adapter = new AttributeListViewAdapter(this, controller.getAttributes());
        attributeListView.setAdapter(adapter);

        searchButton = findViewById(R.id.identify_helper_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForBirds();
            }
        });
    }

    private void searchForBirds() {
        Map<String, String> searchAttributes = new HashMap<>();
        for (int i = 0; i < attributeListView.getCount(); i++) {
            View row = attributeListView.getChildAt(i);
            String key = ((Attribute)attributeListView.getItemAtPosition(i)).getKey();
            Spinner attrSpinner = row.findViewById(R.id.list_attribute_value_spinner);
            String value = attrSpinner.getSelectedItem().toString();
            searchAttributes.put(key, value);
        }
        //TODO CALL REST FOR RESULT
    }
}
