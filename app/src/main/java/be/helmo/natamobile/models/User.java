package be.helmo.natamobile.models;

import java.util.List;

/**
 * Created by Maréchal Thibaut on 22-12-17.
 */

public class User
{
    private long id;
    private String email;
    private String fullName;
    private List<Session> sessions;
}
