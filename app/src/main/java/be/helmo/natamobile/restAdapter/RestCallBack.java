package be.helmo.natamobile.restAdapter;

/**
 * Created by Maréchal Thibaut on 28/12/2017.
 */

public interface RestCallBack<T> {
	void onRestCallComplete(T object);
}
