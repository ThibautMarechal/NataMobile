package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import be.helmo.natamobile.controller.interfaces.IHomeController;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;
import be.helmo.natamobile.restAdapter.SessionAdapter;
import be.helmo.natamobile.restAdapter.UserAdapter;
import be.helmo.natamobile.view.implementations.ViewEnum;
import be.helmo.natamobile.view.interfaces.IHomeView;
import okhttp3.Credentials;

public class HomeController implements IHomeController {
	private final IHomeView view;
	private User user;
	private UserAdapter userAda;
	private SessionAdapter sesAda;
	List<Session> sessions;
	private StorageReference mStorageRef;
	private Handler mHandler;

	public HomeController(IHomeView homeView) {
		this.view = homeView;
		mStorageRef = FirebaseStorage.getInstance().getReference();

		userAda = new UserAdapter();
		sesAda = new SessionAdapter();
		mStorageRef = FirebaseStorage.getInstance().getReference();
	}

	@Override
	public void startNewSession(double longitude, double latitude) {

		this.view.showView(ViewEnum.CURRENT_SESSION);
	}

	@Override
	public String getUserPictureProfile() {
		return user.getPicture();
	}

	@Override
	public String getUsername() {
		return user.getFullName();
	}

	@Override
	public String getUserEmail() {
		return user.getEmail();
	}

	@Override
	public List<Session> getSessions() {
		return sessions;
	}

	@Override
	public void onCreate() {
//		userAda = new UserAdapter();
//		sesAda = new SessionAdapter();
//		mStorageRef = FirebaseStorage.getInstance().getReference();
//		defineUser();
	}

	private void defineUser() {
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
					view.updateUserUI();
					view.updateSessionList();
				} else {
					view.displayToast("Something Happened");
				}
			}
		};
		callREST(credentials);
	}

	private void callREST(final String credentials) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sessions = sesAda.getFor(credentials, view.getSharedId());
					Message message = mHandler.obtainMessage(1, sessions);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, sessions = null);
					message.sendToTarget();
				}
			}
		}).start();
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
	public void updateValues() {
		defineUser();
	}
}
