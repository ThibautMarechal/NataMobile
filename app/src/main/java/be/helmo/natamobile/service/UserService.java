package be.helmo.natamobile.service;

import be.helmo.natamobile.reception.RUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserService {

	@GET("users/email/")
	Call<RUser> getByEmail(@Header("Authorization") String credentials);

	@GET("users/{id}")
	Call<RUser> getById(@Header("Authorization") String credentials, @Path("id") long id);
}
