package be.helmo.natamobile.controller.interfaces;

import android.net.Uri;

import java.util.Date;
import java.util.List;

import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.models.Observation;

public interface ICurrentSessionController extends IBaseController {
	void newObservationPicture(Uri filePath, String onlinePath);

	void newObservationVideo(Uri filePath, String onlinePath);

	void newObservationAudio(Uri filePath, String onlinePath);

	void newObservationNoMedia();

	List<Observation> getObservations();

	void saveSession();

	void defineObservation(Uri uri, FileType type, String onlinePath);

	void finishDefineObservation(long idBird, int numberObs, String name);

	void aboardLastObservation();

	Date getDateStart();
}
