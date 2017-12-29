package be.helmo.natamobile.service;

import java.util.List;

import be.helmo.natamobile.reception.RSession;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SessionsService {

	@GET("sessions/for/")
	Call<List<RSession>> getFor(@Query("q") long id);
}
