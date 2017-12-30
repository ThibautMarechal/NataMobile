package be.helmo.natamobile.restAdapter;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.reception.RSession;
import be.helmo.natamobile.service.SessionsService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;

public class SessionAdapter {

	private SessionsService service;

	public SessionAdapter() {
		service = RESTClient.getClient().create(SessionsService.class);
	}

	public List<Session> getFor(String credentials, Long id) throws IllegalAccessException {
		Call<List<RSession>> call = service.getFor(credentials, id);
		try {
			Response<List<RSession>> response = call.execute();

			if (response.isSuccessful()) {
				List<Session> sessions = new ArrayList<>();
				for (RSession rSession : response.body()) {
					sessions.add(rSession.getModel());
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

	public List<RSession> addSessions(String credentials, List<RSession> sessions) throws IllegalAccessException {
		Call<List<RSession>> call = service.addSessions(credentials, sessions);
		try {
			Response<List<RSession>> response = call.execute();

			if (response.isSuccessful()) {
				return response.body();
			} else if (response.code() == 403) {
				throw new IllegalAccessException("Bad credentials");
			} else {
				throw new IllegalAccessError(response.errorBody().toString()); //TODO Change exception type
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalAccessError(); //TODO Change exception type
		}
	}
}
