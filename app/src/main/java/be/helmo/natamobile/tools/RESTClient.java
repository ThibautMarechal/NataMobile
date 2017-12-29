package be.helmo.natamobile.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {
	private static Retrofit client;

	public static Retrofit getClient() {
		OkHttpClient okHttpClient = getOkHttpClient();
		GsonBuilder builder = new GsonBuilder();

		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});

		Gson gson = builder.create();
		Retrofit rtn;

		if (client == null) {
			rtn = new Retrofit.Builder()
				  .baseUrl("http://192.168.128.13:8081/GRIMAR-1.0/")
				  .client(okHttpClient)
				  .addConverterFactory(GsonConverterFactory.create(gson))
				  .build();
		} else {
			rtn = client;
		}
		return rtn;
	}

	private static OkHttpClient getOkHttpClient() {
		return new OkHttpClient().newBuilder()
			  .writeTimeout(45, TimeUnit.SECONDS)
			  .connectTimeout(45, TimeUnit.SECONDS)
			  .readTimeout(45, TimeUnit.SECONDS)
			  .build();
	}
}
