package be.helmo.natamobile.presenter.implementations;

import java.util.ArrayList;
import java.util.List;

import be.helmo.natamobile.models.Observation;
import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.implementations.ViewEnum;
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
    public void newObservationPicture() {

    }

    @Override
    public void newObservationVideo() {

    }

    @Override
    public void newObservationAudio() {
        this.view.showView(ViewEnum.AUDIO_RECORDER);
    }

    @Override
    public void newObservationNoMedia() {

    }

    @Override
    public String[][] getObservations() {
        String [][] retrunValue = new String[observations.size()][];


        return new String[0][];
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
