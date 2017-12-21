package be.helmo.natamobile.view.implementations;

/**
 * Created by marechthib on 19/12/2017.
 */

public enum ViewEnum {
    HOME(HomeActivity.class),
    LOG_IN(LoginActivity.class),
    CURRENT_SESSION(CurrentSessionActivity.class);

    private final Class activityClass;

    ViewEnum(Class activityClass) {
        this.activityClass =activityClass;
    }

    public Class getActivityClass() {
        return activityClass;
    }
}
