package be.helmo.natamobile.controller.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Attribute;

public interface IIdentifyHelperController extends IBaseController {
	List<Attribute> getAttributes();
}
