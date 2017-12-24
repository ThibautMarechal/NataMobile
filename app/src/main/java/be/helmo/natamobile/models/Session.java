package be.helmo.natamobile.models;

import android.location.Location;

import java.util.List;

/**
 * Created by Maréchal Thibaut on 22-12-17.
 */

public class Session
{
    private List<Observation> observations;
    private String name;

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public String getOnePicture(){
        return "http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
