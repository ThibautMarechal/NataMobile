package be.helmo.natamobile.presenter.interfaces;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface ICurrentSessionPresenter extends IBasePresenter{
    void newObservationPicture(String filePath);

    void newObservationVideo(String filePath);

    void newObservationAudio(String filePath);

    void newObservationNoMedia();

    String[][] getObservations();
}
