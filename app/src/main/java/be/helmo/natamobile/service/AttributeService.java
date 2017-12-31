package be.helmo.natamobile.service;

import java.util.List;

import be.helmo.natamobile.reception.RAttribute;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AttributeService {

	@GET("attributes")
	Call<List<RAttribute>> getAll(@Header("Authorization") String credentials);
}
