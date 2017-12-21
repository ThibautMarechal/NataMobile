package be.helmo.natamobile.presenter.implementations;

import be.helmo.natamobile.presenter.interfaces.ICurrentSessionPresenter;
import be.helmo.natamobile.view.interfaces.ICurrentSessionView;

/**
 * Created by marechthib on 20/12/2017.
 */

public class CurrentSessionPresenter implements ICurrentSessionPresenter {
    private final ICurrentSessionView view;

    public CurrentSessionPresenter(ICurrentSessionView currentSessionView) {
        this.view = currentSessionView;
    }
}
