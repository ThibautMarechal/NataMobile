package be.helmo.natamobile.view.interfaces;

public interface IHomeView extends IView {
	void updateUserUI();

	void updateSessionList();

	String getSharedEmail();

	String getSharedPassword();

	long getSharedId();
}
