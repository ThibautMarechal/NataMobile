package be.helmo.natamobile.presenter.implementations;

import android.location.Location;

import java.util.List;

import be.helmo.natamobile.models.Session;
import be.helmo.natamobile.models.User;
import be.helmo.natamobile.presenter.interfaces.IHomePresenter;
import be.helmo.natamobile.view.interfaces.IHomeView;
import be.helmo.natamobile.view.implementations.ViewEnum;

public class HomePresenter implements IHomePresenter {
    private final IHomeView view;
    private final User user;

    public HomePresenter(IHomeView homeView) {
        this.view = homeView;
        //TODO GET USER FROM REST
        this.user = new User();
        user.setFullName("Quentin Grignet");
        user.setEmail("quentin.grignet@gmail.com");
        user.setPicture("http://img.app-liv.jp.s3.amazonaws.com/icon/001101516/bafaa5e6cdd2233656a9a387b7d8f87a_256.png");
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
    public String[][] getSession(){
        List<Session> sessions = user.getSessions();
        String [][] sessionsArray = new String[sessions.size()][3];
        for (int i = 0; i < sessions.size(); i++) {
            sessionsArray[i][0] = sessions.get(i).getName();
            sessionsArray[i][1] = String.valueOf(sessions.get(i).getObservations().size());
            sessionsArray[i][2] = sessions.get(i).getOnePicture();
        }
        return sessionsArray;
    }

    @Override
    public void onCreate() {

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
}
