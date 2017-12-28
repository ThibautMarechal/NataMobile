package be.helmo.natamobile.controller.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.helmo.natamobile.controller.interfaces.IIdentifyBirdController;
import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.view.interfaces.IIdentifyBirdView;

/**
 * Created by Maréchal Thibaut on 27/12/2017.
 */

public class IdentifyBirdController implements IIdentifyBirdController {
    private final Map<String, String> attributes;
    private final IIdentifyBirdView view;
    private List<Bird> possibleBirds = new ArrayList<>();

    public IdentifyBirdController(IIdentifyBirdView view, Map<String, String> attributes) {
        this.view = view;
        this.attributes = attributes;
        //TODO CALL REST SEARCH
        //TEMP
        Bird b1 = new Bird();
        b1.setName("b1");
        b1.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
        Bird b2 = new Bird();
        b2.setName("b2");
        b2.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
        Bird b3 = new Bird();
        b3.setName("b3");
        b3.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
        Bird b4 = new Bird();
        b4.setName("b4");
        b4.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
        Bird b5 = new Bird();
        b5.setName("b5");
        b5.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
        possibleBirds.add(b1);
        possibleBirds.add(b2);
        possibleBirds.add(b3);
        possibleBirds.add(b4);
        possibleBirds.add(b5);
        view.populateListView(possibleBirds);
    }

    @Override
    public List<Bird> getPossibleBirds() {
        return possibleBirds;
    }
}
