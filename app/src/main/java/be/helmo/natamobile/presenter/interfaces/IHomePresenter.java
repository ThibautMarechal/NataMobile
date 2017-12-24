package be.helmo.natamobile.presenter.interfaces;

public interface IHomePresenter extends IBasePresenter {
    void startNewSession(double longitude, double latitude);
    String getUserPictureProfile();
    String getUsername();
    String getUserEmail();
}
