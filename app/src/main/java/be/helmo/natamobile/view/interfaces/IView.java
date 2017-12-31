package be.helmo.natamobile.view.interfaces;

import be.helmo.natamobile.view.implementations.ViewEnum;

public interface IView {
	void showView(ViewEnum view);

	void displayToast(String message);
}
