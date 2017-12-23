package be.helmo.natamobile.presenter.interfaces;

/**
 * Created by Maréchal Thibaut on 23-12-17.
 */

public interface IBasePresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
