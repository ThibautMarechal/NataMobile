package be.helmo.natamobile.controller.implementations;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.IIdentifyController;
import be.helmo.natamobile.models.Bird;

/**
 * Created by marechthib on 22/12/2017.
 */

public class IdentifyController implements IIdentifyController {
    private List<Bird> birds;
    public IdentifyController(){
        birds = new ArrayList<>();
        Bird b = new Bird();
        b.setName("Oiseau1");
        birds.add(b);
        Bird b2 = new Bird();
        b2.setName("Oiseau2");
        birds.add(b2);
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
    public String[] getBirds() {
        String [] returnValue = new String[birds.size()];
        for (int i = 0; i < birds.size(); i++) {
            returnValue[i] = birds.get(i).getName();
        }
        return returnValue;
    }
}
