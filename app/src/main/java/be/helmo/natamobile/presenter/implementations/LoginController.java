package be.helmo.natamobile.presenter.implementations;

import be.helmo.natamobile.presenter.interfaces.ILoginPresenter;
import be.helmo.natamobile.view.interfaces.ILoginView;
import be.helmo.natamobile.view.implementations.ViewEnum;

/**
 * Created by marechthib on 19/12/2017.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    public LoginPresenter(ILoginView loginview) {
        this.view = loginview;
    }

    @Override
    public void connect() {
        String username = view.getUserName();
        String password = view.getPassword();
        //TODO Connect
        view.showView(ViewEnum.HOME);
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
