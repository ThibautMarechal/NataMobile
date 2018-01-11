package be.helmo.natamobile.restAdapter;

import java.io.IOException;
import java.util.List;

import be.helmo.natamobile.models.BirdFinder;
import be.helmo.natamobile.reception.RBirdFinder;
import be.helmo.natamobile.service.FinderService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;

public class FinderAdapter {


	private FinderService service;

	public FinderAdapter() {
		service = RESTClient.getClient().create(FinderService.class);
	}

	public List<List<Object>> search(String credentials, BirdFinder seed) throws IllegalAccessException {
		Call<List<List<Object>>> call = service.search(credentials, new RBirdFinder(seed));
		try {
			Response<List<List<Object>>> response = call.execute();

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
