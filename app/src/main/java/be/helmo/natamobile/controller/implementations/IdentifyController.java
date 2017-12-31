package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.IIdentifyController;
import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.restAdapter.BirdAdapter;
import be.helmo.natamobile.view.interfaces.IIdentifyView;
import okhttp3.Credentials;

/**
 * Created by marechthib on 22/12/2017.
 */

public class IdentifyController implements IIdentifyController {
	private IIdentifyView view;
	private List<Bird> birds;

	private BirdAdapter birdAdapter;
	private Handler mHandler;

	public IdentifyController(IIdentifyView view) {
		this.view = view;
		birdAdapter = new BirdAdapter();

		birds = new ArrayList<>();
		defineBirds();
	}

	@Override
	public long getBirdIdByName(String name) {
		long rtn = 0;
		for (Bird bird : birds)
			if (bird.getName().equals(name))
				rtn = bird.getId();
		return rtn;
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
	public String[] getBirds() {
		String[] returnValue = new String[birds.size()];
		for (int i = 0; i < birds.size(); i++) {
			returnValue[i] = birds.get(i).getName();
		}
		return returnValue;
	}

	private void defineBirds() {
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
//					view.updateUserUI();
//					view.updateSessionList();
					view.updateSpinnerBird();
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
					birds = birdAdapter.getAll(credentials);
					Message message = mHandler.obtainMessage(1, birds);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, birds = null);
					message.sendToTarget();
				}
			}
		}).start();
	}
}
