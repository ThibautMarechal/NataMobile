package be.helmo.natamobile.models;

/**
 * Created by Maréchal Thibaut on 22-12-17.
 */

public class Observation {
    private String filePath;
    private Bird bird;
    private int numberOfBird;

    public int getNumberOfBird() {
        return numberOfBird;
    }

    public void setNumberOfBird(int numberOfBird) {
        this.numberOfBird = numberOfBird;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
