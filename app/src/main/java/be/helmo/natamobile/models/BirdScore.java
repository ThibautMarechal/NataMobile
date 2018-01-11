package be.helmo.natamobile.models;

import android.support.annotation.NonNull;

public class BirdScore implements Comparable<BirdScore> {

	private Double score;
	private Bird bird;

	public BirdScore(Double score, Bird bird) {
		this.score = score;
		this.bird = bird;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	@Override
	public int compareTo(@NonNull BirdScore o) {
		return (Double.valueOf(this.score*100).intValue() - Double.valueOf(o.score*100).intValue());
	}
}
