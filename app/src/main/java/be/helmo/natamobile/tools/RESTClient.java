package be.helmo.natamobile.tools;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTClient {
    private static Retrofit client;

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = getOkHttpClient();
        Gson gson = new Gson();
        Retrofit rtn;

        if(client == null) {
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
                .readTimeout(45,TimeUnit.SECONDS)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Chain chain) throws IOException {
//                        Request originalRequest = chain.request();
//                        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                                Credentials.basic("system@nat.be", "rootroot"));
//                        Request newRequest = builder.build();
//                        return chain.proceed(newRequest);
//                    }
//                })
                .build();
    }
}
