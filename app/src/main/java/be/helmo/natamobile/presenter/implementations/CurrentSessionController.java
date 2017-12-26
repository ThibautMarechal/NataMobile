package be.helmo.natamobile.presenter.implementations;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.presenter.interfaces.ICurrentSessionController;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionController implements ICurrentSessionController {
    private final ICurrentSessionView view;
    private List<Observation> observations;

    public CurrentSessionController(ICurrentSessionView currentSessionView) {
        this.view = currentSessionView;
        observations = new ArrayList<>();
    }

    @Override
    public void newObservationPicture(String filePath) {
        Observation o = new Observation();
        Bird b = new Bird();
        b.setName("Picture");
        o.setBird(b);
        observations.add(o);
        this.view.updateObservationList();
    }

    @Override
    public void newObservationVideo(String filePath) {
        Observation o = new Observation();
        Bird b = new Bird();
        b.setName("Video");
        o.setBird(b);
        observations.add(o);
        this.view.updateObservationList();
    }

    @Override
    public void newObservationAudio(String filePath) {
        Observation o = new Observation();
        Bird b = new Bird();
        b.setName("Audio");
        o.setBird(b);
        observations.add(o);
        this.view.updateObservationList();
    }

    @Override
    public void newObservationNoMedia() {
        Observation o = new Observation();
        Bird b = new Bird();
        b.setName("NoMedia");
        o.setBird(b);
        observations.add(o);
        this.view.updateObservationList();
    }

    @Override
    public List<Observation> getObservations() {
        return observations;
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
