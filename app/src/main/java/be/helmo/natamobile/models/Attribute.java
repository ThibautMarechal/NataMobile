package be.helmo.natamobile.models;

import java.util.List;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public class Attribute {
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
