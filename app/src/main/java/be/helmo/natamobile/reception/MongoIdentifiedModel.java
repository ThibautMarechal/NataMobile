package be.helmo.natamobile.reception;

public abstract class MongoIdentifiedModel {

    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoIdentifiedModel)) return false;
        MongoIdentifiedModel that = (MongoIdentifiedModel) o;

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
