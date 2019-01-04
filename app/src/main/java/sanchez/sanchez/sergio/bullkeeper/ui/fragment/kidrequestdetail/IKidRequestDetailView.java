package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request Detail View
 */
public interface IKidRequestDetailView extends ISupportView {

    /**
     * On Kid Request Loaded
     * @param kidRequestEntity
     */
    void onKidRequestLoaded(final KidRequestEntity kidRequestEntity);

    /**
     * On Kid Request Deleted
     */
    void onKidRequestDeleted();

    /**
     * On Kid Request Not Found
     */
    void onKidRequestNotFound();

}
