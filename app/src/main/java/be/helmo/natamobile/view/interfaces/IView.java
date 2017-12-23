package be.helmo.natamobile.view.interfaces;

import be.helmo.natamobile.view.implementations.ViewEnum;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface IView {
    void showView(ViewEnum view);
    void displayToast(String message);
}
