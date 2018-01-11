package be.helmo.natamobile.controller.implementations;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.helmo.natamobile.controller.interfaces.IIdentifyBirdController;
import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.models.BirdFinder;
import be.helmo.natamobile.models.BirdScore;
import be.helmo.natamobile.reception.RBird;
import be.helmo.natamobile.restAdapter.FinderAdapter;
import be.helmo.natamobile.view.interfaces.IIdentifyBirdView;
import okhttp3.Credentials;

public class IdentifyBirdController implements IIdentifyBirdController {
	private final Map<String, String> attributes;
	private final IIdentifyBirdView view;
	private List<Bird> possibleBirds = new ArrayList<>();

	private FinderAdapter finderAdapter;
	private Handler mHandler;

	private BirdFinder finder;
	private List<List<Object>> result;

	public IdentifyBirdController(IIdentifyBirdView view, Map<String, String> attributes) {
		this.view = view;
		this.attributes = attributes;
		this.finderAdapter = new FinderAdapter();
		this.possibleBirds = new ArrayList<>();

		finder = new BirdFinder();
		finder.setStringItems(attributes);
		search();

		//TODO CALL REST SEARCH
		//TEMP
//		Bird b1 = new Bird();
//		b1.setName("b1");
//		b1.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
//		Bird b2 = new Bird();
//		b2.setName("b2");
//		b2.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
//		Bird b3 = new Bird();
//		b3.setName("b3");
//		b3.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
//		Bird b4 = new Bird();
//		b4.setName("b4");
//		b4.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
//		Bird b5 = new Bird();
//		b5.setName("b5");
//		b5.setPicturePath("http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg");
//		possibleBirds.add(b1);
//		possibleBirds.add(b2);
//		possibleBirds.add(b3);
//		possibleBirds.add(b4);
//		possibleBirds.add(b5);
//		view.populateListView(possibleBirds);
	}

	@Override
	public List<Bird> getPossibleBirds() {
		return possibleBirds;
	}

	private void search() {
		String credentials = Credentials.basic(view.getSharedEmail(), view.getSharedPassword());
		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message message) {
				if (message.obj != null) {
//					view.updateAttributes();
					List<BirdScore> scores = new ArrayList<>();
					for (List<Object> item : result) {
//						scores.add(
//							  new BirdScore((Double) item.get(0), ((RBird) item.get(1)).getModel()));
						scores.add(
							  new BirdScore((Double) item.get(0),
									extractBird((LinkedTreeMap<String, Object>) item.get(1))));
					}

					Collections.sort(scores);

					for (BirdScore bird : scores) {
						possibleBirds.add(bird.getBird());
					}

					view.populateListView(possibleBirds);
				} else {
					view.displayToast("Something Happened");
				}
			}
		};
		callREST(credentials);
	}

	private Bird extractBird(LinkedTreeMap<String, Object> datas) {
		Bird bird = new Bird();
		bird.setId(((Double) datas.get("id")).longValue());
		bird.setName((String) datas.get("name"));
		bird.setDescription((String) datas.get("description"));
		bird.setAttributes((Map<String, List<String>>) datas.get("data"));
		List<String> pictures = (List<String>) datas.get("publicLinks");
		bird.setPicturePath(pictures != null && pictures.size() > 0 ? pictures.get(0) : "");

		return bird;
	}

	private void callREST(final String credentials) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					result = finderAdapter.search(credentials, finder);
					Message message = mHandler.obtainMessage(1, result);
					message.sendToTarget();
				} catch (IllegalAccessException | IllegalAccessError ex) {
					Message message = mHandler.obtainMessage(0, result = null);
					message.sendToTarget();
				}
			}
		}).start();
	}
}
