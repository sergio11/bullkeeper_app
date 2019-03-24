package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Contact Detail View
 */
public interface IContactDetailView extends ISupportView {

    /**
     * On Contact Detail Loaded
     * @param contactEntity
     */
    void onContactDetailLoaded(final ContactEntity contactEntity);

    /**
     * On Phone Number Blocked Error
     */
    void onPhoneNumberBlockedError();

    /**
     * On Phone Number Blocked Successfully
     * @param phoneNumberBlockedEntity
     */
    void onPhoneNumberBlockedSuccessfully(final PhoneNumberBlockedEntity phoneNumberBlockedEntity);

    /**
     * On Phone Number Unlocked Successfully
     */
    void onPhoneNumberUnlockedSuccessfully();

    /**
     * On Phone Number Unlocked Successfully
     */
    void onPhoneNumberUnlockedError();

    /**
     * On Contact Successfully Disabled
     */
    void onContactSuccessfullyDisabled();

    /**
     * On Error Disabling Contact
     */
    void onErrorDisablingContact();

}
