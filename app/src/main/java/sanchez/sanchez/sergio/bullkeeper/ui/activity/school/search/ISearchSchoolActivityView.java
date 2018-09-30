package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * Search School Activity View
 */
public interface ISearchSchoolActivityView extends ISupportLCEView<SchoolEntity> {

    /**
     * No Registered School
     */
    void noRegisteredSchool();

}
