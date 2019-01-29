package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.AddPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.DeletePhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Contact Detail Presenter
 */
public final class ContactDetailFragmentPresenter extends SupportPresenter<IContactDetailView> {

    /**
     * Get Contact Detail Interact
     */
    private final GetContactDetailInteract getContactDetailInteract;

    /**
     * Add Phone Numbers Blocked Interact
     */
    private final AddPhoneNumbersBlockedInteract addPhoneNumbersBlockedInteract;

    /**
     * Delete Phone Numbers Blocked Interact
     */
    private final DeletePhoneNumbersBlockedInteract deletePhoneNumbersBlockedInteract;

    /**
     * Contact Detail Presenter
     */
    @Inject
    public ContactDetailFragmentPresenter(
            final GetContactDetailInteract getContactDetailInteract,
            final AddPhoneNumbersBlockedInteract addPhoneNumbersBlockedInteract,
            final DeletePhoneNumbersBlockedInteract deletePhoneNumbersBlockedInteract){
        this.getContactDetailInteract = getContactDetailInteract;
        this.addPhoneNumbersBlockedInteract = addPhoneNumbersBlockedInteract;
        this.deletePhoneNumbersBlockedInteract = deletePhoneNumbersBlockedInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String childId = args.getString(ContactDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(ContactDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String contactId = args.getString(ContactDetailActivityMvpFragment.CONTACT_ID_ARG);

        getContactDetailInteract.execute(new GetContactDetailObserver(),
                GetContactDetailInteract.Params.create(childId, terminalId, contactId));

    }

    /**
     * Block Number
     */
    public void blockNumber(final String phoneNumber) {
        Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
        Preconditions.checkState(!phoneNumber.isEmpty(), "Phone number can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String childId = args.getString(ContactDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(ContactDetailActivityMvpFragment.TERMINAL_ID_ARG);

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, "ES");

            final String prefix = "+".concat(String.valueOf(phoneNumberProto.getCountryCode()));
            final String nationalNumber = String.valueOf(phoneNumberProto.getNationalNumber());
            final String number = prefix.concat(nationalNumber);

            addPhoneNumbersBlockedInteract.execute(new AddPhoneNumberObserver(),
                    AddPhoneNumbersBlockedInteract.Params.create(childId, terminalId, prefix,
                            nationalNumber, number));

        } catch (NumberParseException e) {
            addPhoneNumbersBlockedInteract.execute(new AddPhoneNumberObserver(),
                    AddPhoneNumbersBlockedInteract.Params.create(childId, terminalId, phoneNumber));
        }

    }

    /**
     * Unlock Number
     */
    public void unlockNumber(final String phoneNumber){
        Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
        Preconditions.checkState(!phoneNumber.isEmpty(), "Phone number can not be empty");

        if (isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String childId = args.getString(ContactDetailActivityMvpFragment.CHILD_ID_ARG);
        final String terminalId = args.getString(ContactDetailActivityMvpFragment.TERMINAL_ID_ARG);

        deletePhoneNumbersBlockedInteract.execute(new DeletePhoneNumberObserver(),
                DeletePhoneNumbersBlockedInteract.Params.create(childId, terminalId,
                        phoneNumber));

    }

    /**
     * Get Contact Detail Observer
     */
    public class GetContactDetailObserver extends BasicCommandCallBackWrapper<ContactEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(ContactEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onContactDetailLoaded(response);
            }

        }
    }

    /**
     * Add Phone Number Block Observer
     */
    public class AddPhoneNumberObserver extends BasicCommandCallBackWrapper<PhoneNumberBlockedEntity> {

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberBlockedError();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberBlockedError();
            }
        }

        /**
         * On Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberBlockedError();
            }
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(PhoneNumberBlockedEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberBlockedSuccessfully(response);
            }

        }
    }

    /**
     * Delete Phone Number Block Observer
     */
    public class DeletePhoneNumberObserver extends BasicCommandCallBackWrapper<String> {

        /**
         * On Network Error
         */
        @Override
        protected void onNetworkError() {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberUnlockedError();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberUnlockedError();
            }
        }

        /**
         * On Api Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberUnlockedError();
            }
        }


        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberUnlockedSuccessfully();
            }

        }
    }

}
