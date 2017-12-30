package be.helmo.natamobile.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maréchal Thibaut on 22-12-17.
 */

public class Session extends IdentifiedModel {
	private List<Observation> observations;
	private String name;
	private Date start;
	private Date end;
	private String latitude;
	private String longitude;
	private User user;

	public Session() {
		this.observations = new ArrayList<>();
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	public String getOnePicture() {
		return "http://www.thibautmarechal.be/natagora/QuentinGriGri.jpg";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addObservation(Observation observation) {
		observations.add(observation);
	}
}
