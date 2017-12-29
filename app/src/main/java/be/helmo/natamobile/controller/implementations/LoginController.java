package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import be.helmo.natamobile.controller.interfaces.ILoginController;
import be.helmo.natamobile.models.User;
import be.helmo.natamobile.restAdapter.RestCallBack;
import be.helmo.natamobile.restAdapter.UserAdapter;
import be.helmo.natamobile.view.implementations.ViewEnum;
import be.helmo.natamobile.view.interfaces.ILoginView;
import okhttp3.Credentials;

/**
 * Created by marechthib on 19/12/2017.
 */

public class LoginController implements ILoginController, RestCallBack<User> {
	private ILoginView view;

	private Handler mHandler;

	private User loggedUser;

	public LoginController(ILoginView loginView) {
		this.view = loginView;
	}


	@Override
	public void connect() {
		final String username = view.getUserName();
		final String password = view.getPassword();
		//TODO Connect
		String credentials = Credentials.basic(username, password);
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {

					view.displayToast("Connecté");
					view.showView(ViewEnum.HOME);
				} else {
					view.displayToast("Identifiants Incorrectes");
				}
				// This is where you do your work in the UI thread.
				// Your worker tells you in the message what to do.
			}
		};
		callREST(credentials);

	}

	private void callREST(final String credentials) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				UserAdapter adapter = new UserAdapter();
				try {
					loggedUser = adapter.getByEmail(credentials);
					Message message = mHandler.obtainMessage(1, loggedUser);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, loggedUser = null);
					message.sendToTarget();
				}
			}
		}).start();
	}

	@Override
	public void onCreate() {
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
	public void onRestCallComplete(User object) {

	}
}
