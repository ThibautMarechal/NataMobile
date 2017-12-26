package be.helmo.natamobile.presenter.implementations;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionPresenter implements ICurrentSessionPresenter {
    private final ICurrentSessionView view;
    private List<Observation> observations;

    public CurrentSessionPresenter(ICurrentSessionView currentSessionView) {
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
    public String[][] getObservations() {
        String [][] returnValue = new String[observations.size()][3];
        for (int i = 0; i < observations.size(); i++) {
            returnValue[i][0] = observations.get(i).getBird().getName();
            returnValue[i][1] = String.valueOf(observations.get(i).getNumberOfBird());
            returnValue[i][2] = observations.get(i).getFilePath();
        }
        return returnValue;
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
