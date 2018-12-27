package sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Phone Numbers Blocked List View
 */
public interface IPhoneNumbersBlockedListView
        extends ISupportLCEView<PhoneNumberBlockedEntity> {

    /**
     * On All Phone Numbers Deleted
     */
    void onAllPhoneNumbersDeleted();

    /**
     * On Phone Number Deleted
     */
    void onPhoneNumberDeleted();


}
