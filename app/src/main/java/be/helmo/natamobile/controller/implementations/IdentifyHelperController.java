package be.helmo.natamobile.controller.implementations;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.IIdentifyHelperController;
import be.helmo.natamobile.models.Attribute;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class IdentifyHelperController implements IIdentifyHelperController {
    private List<Attribute> attributes = new ArrayList<>();

    public IdentifyHelperController(){

        for (int i = 0; i < 3; i++) {
            Attribute a = new Attribute();
            a.setKey("key"+i);
            List<String> values = new ArrayList<>();
            values.add("v1-"+i);
            values.add("v2-"+i);
            values.add("v3-"+i);
            a.setValues(values);
            attributes.add(a);
        }

    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }
}
