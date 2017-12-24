package be.helmo.natamobile.presenter.implementations;

import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.implementations.ViewEnum;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionPresenter implements ICurrentSessionPresenter {
    private final ICurrentSessionView view;

    public CurrentSessionPresenter(ICurrentSessionView currentSessionView) {
        this.view = currentSessionView;
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
