package be.helmo.natamobile.presenter.interfaces;

import android.location.Location;

public interface IHomePresenter {
    void startNewSession(Location location);

    void newObservationPicture();

    void newObservationVideo();

    void newObservationAudio();

    void newObservationNoMedia();
}
