package be.helmo.natamobile.service;

import java.util.List;

import be.helmo.natamobile.reception.RBirdFinder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FinderService {

	@POST("birds/helper")
	Call<List<List<Object>>> search(@Header("Authorization") String credentials, @Body RBirdFinder finder);
}
