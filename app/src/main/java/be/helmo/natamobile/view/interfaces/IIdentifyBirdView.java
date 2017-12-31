package be.helmo.natamobile.view.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Bird;

public interface IIdentifyBirdView extends IView {
	void populateListView(List<Bird> birds);
	String getSharedEmail();

	String getSharedPassword();
}
