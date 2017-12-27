package be.helmo.natamobile.controller.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Attribute;

/**
 * Created by Maréchal Thibaut on 26/12/2017.
 */

public interface IIdentifyHelperController extends IBaseController{
    List<Attribute> getAttributes();
}
