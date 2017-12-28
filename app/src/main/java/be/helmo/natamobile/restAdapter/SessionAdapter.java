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

public class SessionAdapter extends AsyncTask<Long, Void, List<Session>> {

	private SessionsService service;

	public SessionAdapter() {
		service = RESTClient.getClient().create(SessionsService.class);
	}

	@Override
	protected List<Session> doInBackground(Long... longs) {
		Call<List<RSession>> call = service.getFor(longs[0]);
		try {
			Response<List<RSession>> response = call.execute();

			if (response.isSuccessful()) {
				List<Session> sessions = new ArrayList<>();
				for (RSession rSession : response.body()) {
					sessions.add(rSession.getModel());
				}
				return sessions;
			} else {
				throw new IllegalAccessError(); //TODO Change exception type
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IllegalAccessError(); //TODO Change exception type
		}
	}

	@Override
	protected void onPostExecute(List<Session> sessions) {
		super.onPostExecute(sessions);
	}

	//	public List<Session> getFor(long id) throws IOException {
//
//	}
}
