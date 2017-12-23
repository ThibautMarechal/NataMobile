package be.helmo.natamobile.presenter.implementations;

import android.location.Location;

import be.helmo.natamobile.presenter.interfaces.IHomePresenter;
import be.helmo.natamobile.view.interfaces.IHomeView;
import be.helmo.natamobile.view.implementations.ViewEnum;

public class HomePresenter implements IHomePresenter {
    private final IHomeView view;

    public HomePresenter(IHomeView homeView) {
        this.view = homeView;
    }

    @Override
    public void startNewSession(double longitude, double latitude) {
        this.view.showView(ViewEnum.CURRENT_SESSION);
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
