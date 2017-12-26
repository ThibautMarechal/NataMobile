package be.helmo.natamobile.presenter.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Created by marechthib on 22/12/2017.
 */

public interface IIdentifierController extends IBaseController {
    Map<String,List<String>> getAttributes();
    /**
     * @param attributes
     * @return String array : #0 id, #1 name, #2pictureUrl
     */
    List<String[]> getBirdFor(Map<String, List<String>> attributes);
    void validate(long idBird);
}
