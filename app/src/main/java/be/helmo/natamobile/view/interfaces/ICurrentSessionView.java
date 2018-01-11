package be.helmo.natamobile.view.interfaces;

import android.net.Uri;

import be.helmo.natamobile.models.Observation;

public interface ICurrentSessionView extends IView {
	void updateObservationList();

	void identifyBird(Observation observation);

	String getSharedEmail();

	String getSharedPassword();

	void getLocation();

	String getSharedLatitude();

	String getSharedLongitude();

	long getSharedId();

	String getSharedLastObsLat();

	String getSharedLastObsLon();

	void upload(Uri uri, String online);
}
