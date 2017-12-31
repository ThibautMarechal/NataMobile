package be.helmo.natamobile.view.interfaces;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public interface IIdentifyHelperView extends IView {
	String getSharedEmail();

	String getSharedPassword();

	void updateAttributes();
}
