package be.helmo.natamobile.models;

public abstract class IdentifiedModel {

	private long id;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IdentifiedModel)) return false;
		IdentifiedModel that = (IdentifiedModel) o;

		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}