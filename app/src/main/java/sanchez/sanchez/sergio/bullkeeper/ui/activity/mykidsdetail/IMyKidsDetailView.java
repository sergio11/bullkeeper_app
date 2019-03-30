package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * My Kids Detail View
 */
public interface IMyKidsDetailView extends ISupportView {

    /**
     * On Kid Guardian Loaded
     * @param kidGuardianEntity
     */
    void onKidGuardianLoaded(final KidGuardianEntity kidGuardianEntity);

    /**
     * On Supervised Children Confirmed Not Found
     */
    void onSupervisedChildrenConfirmedNotFound();

}
