package be.helmo.natamobile.service;

import java.util.List;

import be.helmo.natamobile.reception.RSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SessionsService {

	@GET("sessions/for/{id}")
	Call<List<RSession>> getFor(@Header("Authorization") String credentials, @Path("id") long id);

	@POST("sessions")
	Call<List<RSession>> addSessions(@Header("Authorization") String credentials, @Body List<RSession> sessions);
}
