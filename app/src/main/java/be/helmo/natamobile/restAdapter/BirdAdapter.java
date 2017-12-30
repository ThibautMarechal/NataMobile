package be.helmo.natamobile.restAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.reception.RBird;
import be.helmo.natamobile.service.BirdService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;

public class BirdAdapter {

	private BirdService service;

	public BirdAdapter() {
		service = RESTClient.getClient().create(BirdService.class);
	}

	public List<Bird> getAll(String credentials) throws IllegalAccessException {
		Call<List<RBird>> call = service.getAll(credentials);
		try {
			Response<List<RBird>> response = call.execute();

			if (response.isSuccessful()) {
				List<Bird> sessions = new ArrayList<>();
				for (RBird rBird : response.body()) {
					sessions.add(rBird.getModel());
				}
				return sessions;
			} else if (response.code() == 403) {
				throw new IllegalAccessException("Bad credentials");
			} else {
				throw new IllegalAccessError(); //TODO Change exception type
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalAccessError(); //TODO Change exception type
		}
	}

	public List<String> getNames(String credentials) throws IllegalAccessException {
		Call<List<String>> call = service.getNames(credentials);
		try {
			Response<List<String>> response = call.execute();

			if (response.isSuccessful()) {
				return response.body();
			} else if (response.code() == 403) {
				throw new IllegalAccessException("Bad credentials");
			} else {
				throw new IllegalAccessError(); //TODO Change exception type
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalAccessError(); //TODO Change exception type
		}
	}
}
