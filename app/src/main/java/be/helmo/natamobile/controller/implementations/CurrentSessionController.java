package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.renderscript.RenderScript;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.ICurrentSessionController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.reception.RSession;
import be.helmo.natamobile.restAdapter.SessionAdapter;
import be.helmo.natamobile.view.implementations.ViewEnum;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;
import okhttp3.Credentials;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionController implements ICurrentSessionController {
	private final ICurrentSessionView view;
	private List<Observation> observations;

	private StorageReference mStorageRef;

	private SessionAdapter sesAda;
	private Handler mHandler;

	public CurrentSessionController(ICurrentSessionView currentSessionView) {
		this.view = currentSessionView;
		observations = new ArrayList<>();
		sesAda = new SessionAdapter();
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

	@Override
	public void saveSession(Session session) {
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());

		List<RSession> toAdd = new ArrayList<>();
		toAdd.add(new RSession(session));
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
					view.displayToast("Session added");
					view.showView(ViewEnum.HOME);
				} else {
					view.displayToast("Something happened while adding the session");
				}
			}
		};
		addCallREST(credentials, toAdd);
	}

	private void addCallREST(final String credentials, final List<RSession> rSessions) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					List<RSession> back = sesAda.addSessions(credentials, rSessions);
					Message message = mHandler.obtainMessage(1, back);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, null);
					message.sendToTarget();
				}
			}
		}).start();
	}
}
