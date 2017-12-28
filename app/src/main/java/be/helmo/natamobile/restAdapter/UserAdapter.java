package be.helmo.natamobile.restAdapter;

import android.os.AsyncTask;

import java.io.IOException;

import be.helmo.natamobile.models.User;
import be.helmo.natamobile.reception.RUser;
import be.helmo.natamobile.service.UserService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Maréchal Thibaut on 28/12/2017.
 */

public class UserAdapter {

	private UserService service;

	public UserAdapter() {
		service = RESTClient.getClient().create(UserService.class);
	}

	public User getByEmail(String cred) throws IllegalAccessException {
		Call<RUser> call = service.getByEmail(cred);
		try {
			Response<RUser> response = call.execute();

			if (response.isSuccessful()) {
				RUser rUser = response.body();
				return rUser.getModel();
			} else if(response.code() == 403) {
				throw new IllegalAccessException("Bad credentials");
			} else {
				throw new IllegalAccessError(response.message()); //TODO Change exception type
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalAccessError(); //TODO Change exception type
		}
	}
}
