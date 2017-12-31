package be.helmo.natamobile.controller.interfaces;

import be.helmo.natamobile.models.User;

/**
 * Created by marechthib on 19/12/2017.
 */

public interface ILoginController extends IBaseController {
	void connect();

	User getLoggedUser();
}
