package be.helmo.natamobile.view.interfaces;

/**
 * Created by marechthib on 20/12/2017.
 */

public interface IHomeView extends IView {
	void updateUserUI();

	void updateSessionList();

	String getSharedEmail();

	String getSharedPassword();

	long getSharedId();
}
