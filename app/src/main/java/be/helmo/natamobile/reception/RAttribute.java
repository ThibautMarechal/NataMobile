package be.helmo.natamobile.reception;

import java.util.List;

import be.helmo.natamobile.models.Attribute;

public class RAttribute extends MongoIdentifiedModel
	  implements ReceptionObject<Attribute> {
	
	private String name;
	private List<String> values;
	
	public RAttribute() {
	}
	
	public RAttribute(Attribute attribute) {
		this.setId(attribute.getId());
		this.name = attribute.getKey();
		this.values = attribute.getValues();
	}
	
	@Override
	public Attribute getModel() {
		Attribute rtn = new Attribute();
		rtn.setId(this.getId());
		rtn.setKey(this.name);
		rtn.setValues(this.values);
		return rtn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
