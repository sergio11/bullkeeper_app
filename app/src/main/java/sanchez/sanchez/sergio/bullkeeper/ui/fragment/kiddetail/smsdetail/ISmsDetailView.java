package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.SmsEntity;

/**
 * Sms Detail View
 */
public interface ISmsDetailView extends ISupportView {

    /**
     * On SMS Detail Loaded
     * @param smsEntity
     */
    void onSmsDetailLoaded(final SmsEntity smsEntity);

}
