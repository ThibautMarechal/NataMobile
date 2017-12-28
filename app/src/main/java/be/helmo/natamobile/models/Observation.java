package be.helmo.natamobile.models;

import java.util.Date;

/**
 * Created by Maréchal Thibaut on 22-12-17.
 */

public class Observation extends IdentifiedModel {
    private String filePath = "";
    private Bird bird;
    private int numberOfBird = 1;
    private FileType fileType= FileType.NoMedia;
    private Date date;
    private String latitude;
    private String longitude;
    private boolean isValid;
    private String mediaType;
    private String mediaPath;

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

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
