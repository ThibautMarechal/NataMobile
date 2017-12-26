package be.helmo.natamobile.presenter.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Observation;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface ICurrentSessionController extends IBaseController {
    void newObservationPicture(String filePath);

    void newObservationVideo(String filePath);

    void newObservationAudio(String filePath);

    void newObservationNoMedia();

    List<Observation> getObservations();
}
