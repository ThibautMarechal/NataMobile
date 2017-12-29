package be.helmo.natamobile.view.interfaces;

import be.helmo.natamobile.models.Observation;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface ICurrentSessionView extends IView {
	void updateObservationList();

	void identifyBird(Observation observation);
}
