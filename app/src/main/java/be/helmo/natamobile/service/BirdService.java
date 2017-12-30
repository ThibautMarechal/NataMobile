package be.helmo.natamobile.service;

import java.util.List;

import be.helmo.natamobile.reception.RBird;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BirdService {
	@GET("birds/names")
	Call<List<String>> getNames(@Header("Authorization") String credentials);

	@GET("birds")
	Call<List<RBird>> getAll(@Header("Authorization") String credentials);
}
