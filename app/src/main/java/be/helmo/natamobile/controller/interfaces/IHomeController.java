package be.helmo.natamobile.controller.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Session;

public interface IHomeController extends IBaseController {
    void startNewSession(double longitude, double latitude);
    String getUserPictureProfile();
    String getUsername();
    String getUserEmail();
    List<Session> getSessions();
}
