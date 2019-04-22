package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.ContactDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.contactdetail.IContactDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Contact Detail Activity Fragment
 */
public class ContactDetailActivityMvpFragment extends SupportMvpFragment<ContactDetailFragmentPresenter,
        IContactDetailView, IContactDetailActivityHandler, ContactDetailComponent>
        implements IContactDetailView, CompoundButton.OnCheckedChangeListener {

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String CHILD_ID_ARG = "KID_ID_ARG";
    public static String CONTACT_ID_ARG = "CONTACT_ID_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Contact Photo
     */
    @BindView(R.id.contactPhoto)
    protected CircleImageView contactPhotoImageView;

    /**
     * Contact Name Text View
     */
    @BindView(R.id.contactNameTextView)
    protected TextView contactNameTextView;

    /**
     * Phone Number Text View
     */
    @BindView(R.id.phoneNumberTextView)
    protected TextView phoneNumberTextView;

    /**
     * Phone Number Is Blocked Text View
     */
    @BindView(R.id.phoneNumberIsBlockedTextView)
    protected TextView phoneNumberIsBlockedTextView;

    /**
     * Switch Block Status Widget
     */
    @BindView(R.id.switchBlockStatusWidget)
    protected SupportSwitchCompat switchBlockStatusWidget;

    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;


    /**
     * State
     * =============
     */

    /**
     * Terminal Id
     */
    @State
    protected String terminalId;

    /**
     * Child ID
     */
    @State
    protected String childId;

    /**
     * Contact ID
     */
    @State
    protected String contactId;

    /**
     * Phone Number
     */
    @State
    protected ArrayList<String> phoneNumberList;

    /**
     * Phone Number Is Blocked
     */
    @State
    protected boolean phoneNumberIsBlocked;


    public ContactDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param terminal
     */
    public static ContactDetailActivityMvpFragment newInstance(final String terminal, final String kid, final String contact) {
        final ContactDetailActivityMvpFragment contactDetailActivityFragment =
                new ContactDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(CHILD_ID_ARG, kid);
        args.putString(CONTACT_ID_ARG, contact);
        contactDetailActivityFragment.setArguments(args);
        return contactDetailActivityFragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(CHILD_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CHILD_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        childId = getArgs().getString(CHILD_ID_ARG);

        // Get Terminal Id
        if(!getArgs().containsKey(TERMINAL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(TERMINAL_ID_ARG)))
            throw new IllegalStateException("You must provide a terminal id");

        terminalId = getArgs().getString(TERMINAL_ID_ARG);

        // Get Call Id
        if(!getArgs().containsKey(CONTACT_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CONTACT_ID_ARG)))
            throw new IllegalStateException("You must provide a contact id");

        contactId = getArgs().getString(CONTACT_ID_ARG);

        switchBlockStatusWidget.setEnabled(false);
        /**
         * Set On Checked Change Listener
         */
        switchBlockStatusWidget.setOnCheckedChangeListener(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_contact_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(ContactDetailComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ContactDetailFragmentPresenter providePresenter() {
        return component.contactDetailFragmentPresenter();
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Contact Detail Loaded
     * @param contactEntity
     */
    @Override
    public void onContactDetailLoaded(final ContactEntity contactEntity) {
        Preconditions.checkNotNull(contactEntity, "Contact entity can not be null");

        // Set Contact Photo
        if(contactEntity.getPhotoEncodedString() != null &&
                !contactEntity.getPhotoEncodedString().isEmpty()) {
            byte[] decodedString = Base64
                    .decode(contactEntity.getPhotoEncodedString(),
                            Base64.DEFAULT);
            final Bitmap decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            contactPhotoImageView.setImageBitmap(decodedByte);
        } else {
            contactPhotoImageView.setImageResource(R.drawable.user_default);
        }

        // Set Contact Name
        contactNameTextView.setText(contactEntity.getName());

        phoneNumberList = new ArrayList<>();
        final Iterator<ContactEntity.PhoneContactEntity> ite = contactEntity.getPhoneList().iterator();
        while(ite.hasNext()) {
            final ContactEntity.PhoneContactEntity phoneContactEntity = ite.next();
            if(phoneContactEntity != null && !phoneNumberList.contains(phoneContactEntity.getPhone()))
                phoneNumberList.add(phoneContactEntity.getPhone());
        }


        // Set Phone Number
        phoneNumberTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.contact_phonenumber), StringUtils.join(phoneNumberList, ",")));

        // Set Phone Number is blocked
        phoneNumberIsBlockedTextView.setText(contactEntity.isBlocked() ? R.string.phone_number_blocked :
                R.string.phone_number_unlocked);

        switchBlockStatusWidget.setEnabled(true);
        switchBlockStatusWidget.setChecked(contactEntity.isBlocked(), false);

        phoneNumberIsBlocked = contactEntity.isBlocked();

    }

    /**
     * On Phone Number Blocked Error
     */
    @Override
    public void onPhoneNumberBlockedError() {
        showNoticeDialog(R.string.phone_number_blocked_error);
        switchBlockStatusWidget.setChecked(false, false);
    }

    /**
     * On Phone Number Blocked
     * @param phoneNumberBlockedEntityList
     */
    @Override
    public void onPhoneNumberBlockedSuccessfully(List<PhoneNumberBlockedEntity> phoneNumberBlockedEntityList) {
        Preconditions.checkNotNull(phoneNumberBlockedEntityList, "Phone Number Blocked list can not be null");

        phoneNumberIsBlocked = true;

        phoneNumberIsBlockedTextView.setText(phoneNumberIsBlocked ? R.string.phone_number_blocked :
                R.string.phone_number_unlocked);

        showNoticeDialog(R.string.phone_number_blocked_successfully);

    }

    /**
     * On Phone Number Unlocked
     */
    @Override
    public void onPhoneNumberUnlockedSuccessfully() {

        phoneNumberIsBlocked = false;

        phoneNumberIsBlockedTextView.setText(phoneNumberIsBlocked ? R.string.phone_number_blocked :
                R.string.phone_number_unlocked);

        showNoticeDialog(R.string.phone_number_unlocked_successfully);

    }

    /**
     * On Phone Number Unlocked Error
     */
    @Override
    public void onPhoneNumberUnlockedError() {
        showNoticeDialog(R.string.phone_number_unlocked_error, false);
        switchBlockStatusWidget.setChecked(true, false);
    }

    /**
     * On Contact Successfully Disabled
     */
    @Override
    public void onContactSuccessfullyDisabled() {
        showNoticeDialog(getString(R.string.contact_disabled_successfully), true, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * on Error Disabling Contact
     */
    @Override
    public void onErrorDisablingContact() {
        showNoticeDialog(R.string.error_disabling_contact, false);
    }

    /**
     * On Checked Changed
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(!activityHandler.isConnectivityAvailable()) {

            showNoticeDialog(R.string.connectivity_not_available, false);
            switchBlockStatusWidget.setChecked(!isChecked, false);

        } else {

            if(phoneNumberList != null && !phoneNumberList.isEmpty()) {

                if(isChecked) {

                    showConfirmationDialog(R.string.block_phone_number_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            getPresenter().blockNumber(phoneNumberList);
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            switchBlockStatusWidget.setChecked(false, false);
                        }
                    });

                } else {
                    showConfirmationDialog(R.string.unlock_phone_number_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                        @Override
                        public void onAccepted(DialogFragment dialog) {
                            getPresenter().unlockNumber(phoneNumberList);
                        }

                        @Override
                        public void onRejected(DialogFragment dialog) {
                            switchBlockStatusWidget.setChecked(true, false);
                        }
                    });
                }

            } else {

                switchBlockStatusWidget.setChecked(!isChecked, false);

            }
        }

    }

    /**
     * Disable Contact
     */
    @OnClick(R.id.disableContact)
    protected void onDisableContact(){
        showConfirmationDialog(R.string.disable_contact_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                Preconditions.checkNotNull(contactId, "Contact id can not be null");
                Preconditions.checkState(!contactId.isEmpty(), "Contact id can not be empty");
                getPresenter().disableContact(contactId);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });
    }
}
