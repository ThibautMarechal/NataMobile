package be.helmo.natamobile.restAdapter;

import android.os.AsyncTask;

import java.io.IOException;

import be.helmo.natamobile.models.User;
import be.helmo.natamobile.reception.RUser;
import be.helmo.natamobile.service.UserService;
import be.helmo.natamobile.tools.RESTClient;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Maréchal Thibaut on 28/12/2017.
 */

public class UserAdapter  extends AsyncTask<String, Void, User> {

    private final RestCallBack<User> callBack;
    private UserService service;

    public UserAdapter(RestCallBack<User> callBack) {
        this.callBack = callBack;
        service = RESTClient.getClient().create(UserService.class);
    }

    @Override
    protected User doInBackground(String... creditential) {
        Call<RUser> call = service.getByEmail(creditential[0]);
        try {
            Response<RUser> response = call.execute();

            if (response.isSuccessful()) {
                RUser rUser = response.body();
                return rUser.getModel();
            } else {
                throw new IllegalAccessError(); //TODO Change exception type
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalAccessError(); //TODO Change exception type
        }
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        callBack.onRestCallComplete(user);
    }
}
