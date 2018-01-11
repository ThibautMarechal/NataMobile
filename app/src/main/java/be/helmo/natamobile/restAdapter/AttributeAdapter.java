package be.helmo.natamobile.restAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Attribute;
import be.helmo.natamobile.reception.RAttribute;
import be.helmo.natamobile.service.AttributeService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;



public class AttributeAdapter {

	private AttributeService service;
	
	public AttributeAdapter() {
		service = RESTClient.getClient().create(AttributeService.class); 
	}
	
	public List<Attribute> getAll(String credentials) throws IllegalAccessException {
		Call<List<RAttribute>> call = service.getAll(credentials);
		try {
			Response<List<RAttribute>> response = call.execute();

			if (response.isSuccessful()) {
				List<Attribute> sessions = new ArrayList<>();
				for (RAttribute rAttribute : response.body()) {
					sessions.add(rAttribute.getModel());
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
}
