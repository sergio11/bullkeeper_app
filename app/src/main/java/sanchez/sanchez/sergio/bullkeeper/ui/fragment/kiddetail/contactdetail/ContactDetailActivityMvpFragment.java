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
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
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
    protected String phoneNumber;

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

        // Set Phone Number
        phoneNumberTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.contact_phonenumber), contactEntity.getPhoneNumber()));

        phoneNumber = contactEntity.getPhoneNumber();

        // Set Phone Number is blocked
        phoneNumberIsBlockedTextView.setText(contactEntity.isBlocked() ? R.string.phone_number_blocked :
                R.string.phone_number_unlocked);

        switchBlockStatusWidget.setEnabled(true);
        switchBlockStatusWidget.setChecked(contactEntity.isBlocked());

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
     * @param phoneNumberBlockedEntity
     */
    @Override
    public void onPhoneNumberBlockedSuccessfully(PhoneNumberBlockedEntity phoneNumberBlockedEntity) {
        Preconditions.checkNotNull(phoneNumberBlockedEntity, "Phone Number Blocked can not be null");

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
        showNoticeDialog(R.string.phone_number_unlocked_error);
        switchBlockStatusWidget.setChecked(true, false);
    }

    /**
     * On Checked Changed
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(appUtils.isValidString(phoneNumber)) {

            if(isChecked) {

                showConfirmationDialog(R.string.block_phone_number_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        getPresenter().blockNumber(phoneNumber);
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
                        getPresenter().unlockNumber(phoneNumber);
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
