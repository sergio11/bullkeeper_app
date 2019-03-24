package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contact List Fragment View
 */
interface IContactListFragmentView extends ISupportLCEView<ContactEntity> {

    /**
     * On Contact Disabled Successfully
     */
    void onContactDisabledSuccessfully();

    /**
     * On Error Disabling Contact
     */
    void onErrorDisablingContact();

}
