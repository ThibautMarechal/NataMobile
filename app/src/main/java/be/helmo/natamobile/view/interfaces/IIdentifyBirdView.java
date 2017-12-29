package be.helmo.natamobile.view.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Bird;

/**
 * Created by Maréchal Thibaut on 28/12/2017.
 */

public interface IIdentifyBirdView extends IView {
	void populateListView(List<Bird> birds);
}
