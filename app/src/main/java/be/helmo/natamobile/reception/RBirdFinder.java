package be.helmo.natamobile.reception;

import java.util.Map;

import be.helmo.natamobile.models.BirdFinder;

public class RBirdFinder implements ReceptionObject<BirdFinder> {

	private Map<String, String> stringItems;

	public RBirdFinder(BirdFinder src) {
		this.setStringItems(src.getStringItems());
	}

	@Override
	public BirdFinder getModel() {
		BirdFinder rtn = new BirdFinder();
		rtn.setStringItems(this.stringItems);
		return rtn;
	}

	public Map<String, String> getStringItems() {
		return stringItems;
	}

	public void setStringItems(Map<String, String> stringItems) {
		this.stringItems = stringItems;
	}
}
