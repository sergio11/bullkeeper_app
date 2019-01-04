package sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.AddPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.DeleteAllPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.DeletePhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.GetPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Phone Numbers Blocked
 */
public final class PhoneNumbersBlockedListPresenter
        extends SupportLCEPresenter<IPhoneNumbersBlockedListView> {

    /**
     * Args
     */
    public final static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public final static String KID_ID_ARG = "KID_ID_ARG";


    /**
     * Get Phone Numbers Blocked Interact
     */
    private final GetPhoneNumbersBlockedInteract getPhoneNumbersBlockedInteract;

    /**
     * Delete All Phone Numbers Blocked Interact
     */
    private final DeleteAllPhoneNumbersBlockedInteract deleteAllPhoneNumbersBlockedInteract;

    /**
     * Delete Phone Numbers Blocked Interact
     */
    private final DeletePhoneNumbersBlockedInteract deletePhoneNumbersBlockedInteract;

    /**
     * Add Phone Numbers Blocked Interact
     */
    private final AddPhoneNumbersBlockedInteract addPhoneNumbersBlockedInteract;

    /**
     *
     * @param getPhoneNumbersBlockedInteract
     * @param deleteAllPhoneNumbersBlockedInteract
     * @param deletePhoneNumbersBlockedInteract
     */
    @Inject
    public PhoneNumbersBlockedListPresenter(
            final GetPhoneNumbersBlockedInteract getPhoneNumbersBlockedInteract,
            final DeleteAllPhoneNumbersBlockedInteract deleteAllPhoneNumbersBlockedInteract,
            final DeletePhoneNumbersBlockedInteract deletePhoneNumbersBlockedInteract,
            final AddPhoneNumbersBlockedInteract addPhoneNumbersBlockedInteract) {
        this.getPhoneNumbersBlockedInteract = getPhoneNumbersBlockedInteract;
        this.deleteAllPhoneNumbersBlockedInteract = deleteAllPhoneNumbersBlockedInteract;
        this.deletePhoneNumbersBlockedInteract = deletePhoneNumbersBlockedInteract;
        this.addPhoneNumbersBlockedInteract = addPhoneNumbersBlockedInteract;
    }

    /**
     *
     */
    @Override
    public void loadData() {
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(), "Kid id can not be empty");
        Preconditions.checkState(!args.getString(TERMINAL_ID_ARG, "").isEmpty(), "Terminal id can not be empty");

        final String kid = args.getString(KID_ID_ARG);
        final String terminal = args.getString(TERMINAL_ID_ARG);

        getPhoneNumbersBlockedInteract.execute(
                new GetPhoneNumbersBlockedObservable(GetPhoneNumbersBlockedInteract
                        .GetPhoneNumbersBlockedListApiErrors.class),
                GetPhoneNumbersBlockedInteract.Params.create(kid, terminal));
    }

    /**
     * Load Data
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }


    /**
     * Delete All Phone Numbers Blocked
     */
    public void deleteAllPhoneNumbersBlocked(){
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(), "Kid id can not be empty");
        Preconditions.checkState(!args.getString(TERMINAL_ID_ARG, "").isEmpty(), "Terminal id can not be empty");

        final String kid = args.getString(KID_ID_ARG);
        final String terminal = args.getString(TERMINAL_ID_ARG);

        // Delete All Phone Numbers Blocked
        deleteAllPhoneNumbersBlockedInteract
                .execute(new DeleteAllPhoneNumbersBlockedObservable(),
                        DeleteAllPhoneNumbersBlockedInteract.Params.create(kid, terminal));
    }


    /**
     * Delete Phone Number Blocked
     * @param phoneNumber
     */
    public void deletePhoneNumberBlocked(final String phoneNumber) {
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(), "Kid id can not be empty");
        Preconditions.checkState(!args.getString(TERMINAL_ID_ARG, "").isEmpty(), "Terminal id can not be empty");
        Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
        Preconditions.checkState(!phoneNumber.isEmpty(), "Phone Number can not be empty");

        final String kid = args.getString(KID_ID_ARG);
        final String terminal = args.getString(TERMINAL_ID_ARG);

        deletePhoneNumbersBlockedInteract.execute(new DeletePhoneNumberBlockedObservable(),
                DeletePhoneNumbersBlockedInteract.Params.create(kid, terminal, phoneNumber));
    }

    /**
     * Add Phone Number To Blocked
     * @param phoneNumber
     */
    public void addPhoneNumberToBlocked(final String phoneNumber) {
        Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
        Preconditions.checkState(!phoneNumber.isEmpty(), "Phone number can not be empty");

        final String kid = args.getString(KID_ID_ARG);
        final String terminal = args.getString(TERMINAL_ID_ARG);

        addPhoneNumbersBlockedInteract.execute(new AddPhoneNumberBlockedObservable(),
                AddPhoneNumbersBlockedInteract.Params.create(kid, terminal, phoneNumber));

    }


    /**
     * Get Phone Numbers blocked Observable
     */
    public class GetPhoneNumbersBlockedObservable extends CommandCallBackWrapper<List<PhoneNumberBlockedEntity>,
            GetPhoneNumbersBlockedInteract.GetPhoneNumbersBlockedListApiErrors.IGetPhoneNumbersListApiErrorsVisitor,
            GetPhoneNumbersBlockedInteract.GetPhoneNumbersBlockedListApiErrors>
            implements GetPhoneNumbersBlockedInteract.GetPhoneNumbersBlockedListApiErrors
                    .IGetPhoneNumbersListApiErrorsVisitor {

        /**
         *
         * @param apiErrors
         */
        public GetPhoneNumbersBlockedObservable(Class<GetPhoneNumbersBlockedInteract.GetPhoneNumbersBlockedListApiErrors> apiErrors) {
            super(apiErrors);
        }

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(List<PhoneNumberBlockedEntity> response) {
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDataLoaded(response);
            }

        }

        /**
         * Visit No Phone Numbers Blocked Found
         * @param apiErrorsVisitor
         */
        @Override
        public void visitNoPhoneNumbersBlockedFound(GetPhoneNumbersBlockedInteract.GetPhoneNumbersBlockedListApiErrors.IGetPhoneNumbersListApiErrorsVisitor apiErrorsVisitor) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNoDataFound();
            }
        }
    }


    /**
     * Delete All Phone Numbers Blocked
     */
    public class DeleteAllPhoneNumbersBlockedObservable extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onAllPhoneNumbersDeleted();
            }
        }
    }

    /**
     * Delete Phone Number Blocked
     */
    public class DeletePhoneNumberBlockedObservable
            extends BasicCommandCallBackWrapper<String> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(String response) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberDeleted();
            }
        }
    }

    /**
     * Delete Phone Number Blocked
     */
    public class AddPhoneNumberBlockedObservable
            extends BasicCommandCallBackWrapper<PhoneNumberBlockedEntity> {

        /**
         * On Success
         */
        @Override
        protected void onSuccess(PhoneNumberBlockedEntity phoneNumberBlockedEntity) {
            if (isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onPhoneNumberAdded(phoneNumberBlockedEntity);
            }
        }
    }
}
