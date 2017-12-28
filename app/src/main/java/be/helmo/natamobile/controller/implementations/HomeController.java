package be.helmo.natamobile.controller.implementations;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import be.helmo.natamobile.controller.interfaces.IHomeController;
import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;
import be.helmo.natamobile.restAdapter.RestCallBack;
import be.helmo.natamobile.restAdapter.UserAdapter;
import be.helmo.natamobile.view.implementations.ViewEnum;
import be.helmo.natamobile.view.interfaces.IHomeView;
import okhttp3.Credentials;

public class HomeController implements IHomeController, RestCallBack<User> {
    private final IHomeView view;
    private User user;

    private StorageReference mStorageRef;

    public HomeController(IHomeView homeView) {
        this.view = homeView;
        UserAdapter userAda = new UserAdapter();
        String cre = Credentials.basic("admin@nat.be", "adminadmin");
//        userAda.execute(cre);
    }

    @Override
    public void startNewSession(double longitude, double latitude) {
        this.view.showView(ViewEnum.CURRENT_SESSION);
    }

    @Override
    public String getUserPictureProfile() {
        return user.getPicture();
    }

    @Override
    public String getUsername() {
        return user.getFullName();
    }

    @Override
    public String getUserEmail() {
        return user.getEmail();
    }
    @Override
    public List<Session> getSessions(){
        return this.user.getSessions();
    }

    @Override
    public void onCreate() {
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRestCallComplete(User user) {
        this.user = user;
        view.updateUserUI();
    }
}
