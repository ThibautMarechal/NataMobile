package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.controller.interfaces.IIdentifyHelperController;
import be.helmo.natamobile.models.Attribute;
import be.helmo.natamobile.restAdapter.AttributeAdapter;
import be.helmo.natamobile.view.interfaces.IIdentifyHelperView;
import okhttp3.Credentials;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class IdentifyHelperController implements IIdentifyHelperController {
	private IIdentifyHelperView view;

	private AttributeAdapter attributeAdapter;
	private Handler mHandler;

	private List<Attribute> attributes = new ArrayList<>();

	public IdentifyHelperController(IIdentifyHelperView view) {
		this.view = view;
		this.attributeAdapter = new AttributeAdapter();
//		for (int i = 0; i < 3; i++) {
//			Attribute a = new Attribute();
//			a.setKey("key" + i);
//			List<String> values = new ArrayList<>();
//			values.add("v1-" + i);
//			values.add("v2-" + i);
//			values.add("v3-" + i);
//			a.setValues(values);
//			attributes.add(a);
//		}

		defineAttributes();
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
	public List<Attribute> getAttributes() {
		return attributes;
	}

	private void defineAttributes() {
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
					view.updateAttributes();
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
					attributes = attributeAdapter.getAll(credentials);
					Message message = mHandler.obtainMessage(1, attributes);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, attributes = null);
					message.sendToTarget();
				}
			}
		}).start();
	}
}
