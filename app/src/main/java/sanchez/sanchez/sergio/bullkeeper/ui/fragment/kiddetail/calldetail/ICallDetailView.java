package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail;

import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Call Detail View
 */
public interface ICallDetailView extends ISupportView {

    /**
     * On Call Detail Loaded
     * @param callDetailEntity
     */
    void onCallDetailLoaded(final CallDetailEntity callDetailEntity);

    /**
     * On Phone Number Blocked Error
     */
    void onPhoneNumberBlockedError();

    /**
     * On Phone Number Blocked Successfully
     * @param phoneNumberBlockedEntity
     */
    void onPhoneNumberBlockedSuccessfully(final List<PhoneNumberBlockedEntity> phoneNumberBlockedEntity);

    /**
     * On Phone Number Unlocked Successfully
     */
    void onPhoneNumberUnlockedSuccessfully();

    /**
     * On Phone Number Unlocked Successfully
     */
    void onPhoneNumberUnlockedError();

}
