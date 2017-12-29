package be.helmo.natamobile.reception;


import java.sql.Timestamp;

import be.helmo.natamobile.models.Observation;

public class RObservation extends IdentifiedModel
	  implements ReceptionObject<Observation> {

	private String latitude;
	private String longitude;
	private Timestamp dateTime;
	private int nbrObs;
	private boolean validation;
	private String onlinePath;
	private String analyseResult;
	private long birdId;

	private RMediaType mediaType;

	private RSession session;

	private String localPath;

	private RBird bird;

	public RObservation() {
	}

	public RObservation(Observation obs) {
		this.setId(obs.getId());
		this.latitude = obs.getLatitude();
		this.longitude = obs.getLongitude();
		this.dateTime = new Timestamp(obs.getDate().getTime());
		this.nbrObs = obs.getNumberOfBird();
		this.validation = obs.isValid();
		this.onlinePath = obs.getMediaPath();
		this.birdId = obs.getBird().getId();
		this.bird = new RBird(obs.getBird());
		this.mediaType = new RMediaType(obs.getMediaType());
	}

	@Override
	public Observation getModel() {
		Observation rtn = new Observation();
		rtn.setId(this.getId());
		rtn.setLatitude(this.latitude);
		rtn.setLongitude(this.longitude);
		rtn.setDate(this.dateTime);
		rtn.setNumberOfBird(this.nbrObs);
		rtn.setValid(this.validation);
		rtn.setMediaPath(this.onlinePath);
		rtn.setBird(
			  (bird != null)
					? bird.getModel()
					: null);
		rtn.setMediaType(this.mediaType.getName());
		return rtn;
	}
}
