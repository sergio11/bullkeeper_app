package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;


/**
 * Add School View
 */
public interface IAddSchoolView extends ISupportView {

    /**
     * On School Added
     * @param schoolEntity
     */
    void onSchoolAdded(final SchoolEntity schoolEntity);

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

}
