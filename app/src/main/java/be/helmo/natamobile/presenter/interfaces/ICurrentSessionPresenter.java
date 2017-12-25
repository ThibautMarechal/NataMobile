package be.helmo.natamobile.presenter.interfaces;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface ICurrentSessionPresenter extends IBasePresenter{
    void newObservationPicture();

    void newObservationVideo();

    void newObservationAudio();

    void newObservationNoMedia();

    String[][] getObservations();
}
