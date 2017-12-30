package be.helmo.natamobile.controller.implementations;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.ICurrentSessionController;
import be.helmo.natamobile.models.FileType;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;
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

	private Session session;

	public CurrentSessionController(ICurrentSessionView currentSessionView) {
		this.view = currentSessionView;
		observations = new ArrayList<>();
		sesAda = new SessionAdapter();

		session = new Session();
		session.setLatitude(view.getSharedLatitude());
		session.setLongitude(view.getSharedLongitude());
		session.setStart(new Timestamp(new Date().getTime()));
		User owner = new User();
		owner.setId(view.getSharedId());
		session.setUser(owner);
		session.setObservations(new ArrayList<Observation>());
	}

	@Override
	public void newObservationPicture(Uri filePath, String onlinePath) {
		observations.add(defineObservation(filePath, FileType.PICTURE, onlinePath));
	}

	@Override
	public void newObservationVideo(Uri filePath, String onlinePath) {
		observations.add(defineObservation(filePath, FileType.VIDEO, onlinePath));
	}

	@Override
	public void newObservationAudio(Uri filePath, String onlinePath) {
		observations.add(defineObservation(filePath, FileType.AUDIO, onlinePath));
	}

	@Override
	public void newObservationNoMedia() {
		observations.add(defineObservation(null, FileType.NoMedia, null));
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
	public Observation defineObservation(final Uri uri, FileType type, final String onlinePath) {
		final Observation newObs = new Observation();
		newObs.setDate(new Timestamp(new Date().getTime()));
		newObs.setFileType(type);
		newObs.setValid(true);

		view.getLocation(); //TODO LONG ET LAT NOT OK
		newObs.setLatitude(view.getSharedLastObsLat());
		newObs.setLongitude(view.getSharedLastObsLon());

		if (type != FileType.NoMedia) {
			newObs.setFilePath(uri.toString());
			newObs.setMediaPath(onlinePath);

			new Thread(new Runnable() {
				@Override
				public void run() {
					view.upload(uri, onlinePath);
				}
			}).start();
		}

		//TODO Define ID Bird and nbr
		view.identifyBird(newObs);
		newObs.setNumberOfBird(1);

		return newObs;
	}

	@Override
	public void saveSession() {
		session.setEnd(new Timestamp(new Date().getTime()));
		session.setName("Test-" + new Timestamp(new Date().getTime()));
		session.setObservations(observations);
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());

		List<RSession> toAdd = new ArrayList<>();
		toAdd.add(new RSession(session));
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
					view.displayToast("Session ajoutée");
					view.showView(ViewEnum.HOME);
				} else {
					view.displayToast("Problème lors de l'ajout de la session");
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

	@Override
	public Date getDateStart() {
		return session.getStart();
	}
}
