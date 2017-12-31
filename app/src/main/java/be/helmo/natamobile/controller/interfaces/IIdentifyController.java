package be.helmo.natamobile.controller.interfaces;

public interface IIdentifyController extends IBaseController {
	String[] getBirds();

	long getBirdIdByName(String name);
}
