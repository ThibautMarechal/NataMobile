package be.helmo.natamobile.models;

import java.util.List;

public class Attribute extends IdentifiedModel {
	private String key;
	private List<String> values;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
