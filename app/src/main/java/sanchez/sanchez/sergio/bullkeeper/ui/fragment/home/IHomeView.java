package sanchez.sanchez.sergio.bullkeeper.ui.fragment.home;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Home View
 */
public interface IHomeView extends ISupportView {

    /**
     * On User Profile Loaded
     * @param parentEntity
     */
    void onUserProfileLoaded(final ParentEntity parentEntity);

    /**
     * On Last Alerts Loaded
     * @param lastAlerts
     */
    void onLastAlertsLoaded(final List<AlertEntity> lastAlerts);

    /**
     * On Children Loaded
     * @param children
     */
    void onChildrenLoaded(final List<SonEntity> children);

    /**
     * On No Children Founded
     */
    void onNoChildrenFounded();

}
