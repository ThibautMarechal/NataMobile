package be.helmo.natamobile.reception;

import be.helmo.natamobile.models.MediaType;

public class RMediaType extends IdentifiedModel
	  implements ReceptionObject<MediaType> {

	private String name;

	public RMediaType() {
	}

	public RMediaType(String mediaType) {
		this.name = mediaType;
	}

	@Override
	public MediaType getModel() {
		MediaType rtn = new MediaType();
		rtn.setId(this.getId());
		rtn.setName(this.name);
		return rtn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
