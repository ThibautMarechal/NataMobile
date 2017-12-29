package be.helmo.natamobile.controller.interfaces;

import java.util.List;

import be.helmo.natamobile.models.Bird;

/**
 * Created by Maréchal Thibaut on 27/12/2017.
 */

public interface IIdentifyBirdController {
	List<Bird> getPossibleBirds();
}
