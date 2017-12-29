package be.helmo.natamobile.controller.implementations;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.ICurrentSessionController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionController implements ICurrentSessionController {
	private final ICurrentSessionView view;
	private List<Observation> observations;

	private StorageReference mStorageRef;

	public CurrentSessionController(ICurrentSessionView currentSessionView) {
		this.view = currentSessionView;
		observations = new ArrayList<>();
	}

	@Override
	public void newObservationPicture(String filePath) {
		Observation o = new Observation();
		o.setFileType(FileType.PICTURE);
		o.setFilePath(filePath);
		observations.add(o);
		view.identifyBird(o);
	}

	@Override
	public void newObservationVideo(String filePath) {
		Observation o = new Observation();
		o.setFileType(FileType.VIDEO);
		o.setFilePath(filePath);
		observations.add(o);
		view.identifyBird(o);
	}

	@Override
	public void newObservationAudio(String filePath) {
		Observation o = new Observation();
		o.setFileType(FileType.AUDIO);
		o.setFilePath(filePath);
		observations.add(o);
		view.identifyBird(o);
	}

	@Override
	public void newObservationNoMedia() {
		Observation o = new Observation();
		observations.add(o);
		view.identifyBird(o);
	}

	@Override
	public List<Observation> getObservations() {
		return observations;
	}

	@Override
	public void onCreate() {
		mStorageRef = FirebaseStorage.getInstance().getReference();
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
}
