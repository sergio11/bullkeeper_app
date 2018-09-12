package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.os.Bundle;
import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.EnumSet;
import java.util.Set;
import io.reactivex.disposables.CompositeDisposable;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.utils.CallbackWrapper;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;
import timber.log.Timber;

/**
 * Support Presenter
 * @param <T>
 */
public abstract class SupportPresenter<T extends ISupportView> extends TiPresenter<T> {

    protected CompositeDisposable compositeDisposable;

    protected Bundle args;

    public SupportPresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull T view) {
        super.onAttachView(view);
        Timber.d("On Attach View");
        args = view.getArgs();
        if(args != null && !args.isEmpty()) {
            onInit(args);
        } else {
            onInit();
        }
    }

    /**
     * On Detach View
     */
    @Override
    protected void onDetachView() {
        super.onDetachView();
        Timber.d("On Detach View");
        releaseSubscription();
    }

    /**
     * Release Subscriptions
     */
    private void releaseSubscription(){
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    /**
     * Notify Unexpected exception
     */
    protected void notifyUnexpectedException(){
        if (isViewAttached() && getView() != null) {
            getView().hideProgressDialog();
            getView().onOtherException();
        }
    }

    /**
     * On Init
     */
    protected void onInit(){}

    /**
     * On Init
     * @param args
     */
    protected void onInit(Bundle args){}

    /**
     * Command CallBack Wrapper
     * @param <T>
     */
    public abstract class CommandCallBackWrapper<T, V extends ISupportVisitor,
            E extends Enum<E> & ISupportVisitable<V>>
            extends CallbackWrapper<T> implements CommonApiErrors.ICommonApiErrorVisitor {

        private final Set<E> apiErrorsValues;
        private final Set<CommonApiErrors> commonApiErrors;

        public CommandCallBackWrapper(Class<E> apiErrors)
        {
            apiErrorsValues= EnumSet.allOf(apiErrors);
            commonApiErrors = EnumSet.allOf(CommonApiErrors.class);
        }

        /**
         * Find Common Api Error From Code Name
         * @param codeName
         * @return
         */
        private CommonApiErrors findCommonApiErrorFromCodeName(final String codeName) {

            CommonApiErrors commonApiError = null;

            for(final CommonApiErrors ce: commonApiErrors) {
                if (ce.name().equalsIgnoreCase(codeName)) {
                    commonApiError = ce;
                    break;
                }
            }

            return commonApiError;

        }

        /**
         * Find Api Error From Code Name
         * @param codeName
         * @return
         */
        private E findApiErrorFromCodeName(final String codeName) {

            E apiErrorFounded = null;

            for (E apiError : apiErrorsValues) {
                if (apiError.name().equalsIgnoreCase(codeName)) {
                    apiErrorFounded = apiError;
                    break;
                }
            }

            return apiErrorFounded;
        }

        /**
         * On Next
         * @param t
         */
        @Override
        public void onNext(T t) {
            super.onNext(t);
            onSuccess(t);
        }

        @Override
        protected void onNetworkError() {
            Timber.e("On Network Error");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNetworkError();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            Timber.e("On Other Error -> %s", ex.getMessage());
            notifyUnexpectedException();
        }

        @Override
        protected void onApiException(APIResponse response) {
            if (response != null) {
                Timber.e("On Api Exception -> %s - %s", response.getCode(), response.getCodeName());

                final CommonApiErrors commonApiError = findCommonApiErrorFromCodeName(response.getCodeName());

                if(commonApiError != null) {

                    commonApiError.accept(this, response.getData());

                } else {

                    final E apiError = findApiErrorFromCodeName(response.getCodeName());

                    if(apiError != null) {

                        apiError.accept((V)this, response.getData());

                    } else {

                        Timber.e("No Api Code Name Founded");
                        notifyUnexpectedException();

                    }

                }

            } else {

                Timber.e("Response is null");
                notifyUnexpectedException();
            }
        }

        /**
         * Visit Generic Error
         * @param errors
         */
        @Override
        public void visitGenericError(CommonApiErrors errors) {
            notifyUnexpectedException();
        }

        /**
         * Visit Message Not Readable
         * @param errors
         */
        @Override
        public void visitMessageNotReadable(CommonApiErrors errors) {
            notifyUnexpectedException();
        }

        /**
         * On Success
         * @param response
         */
        protected abstract void onSuccess(final T response);

    }

    /**
     * Basic Command Callback Wrapper
     * @param <T>
     */
    public abstract class BasicCommandCallBackWrapper<T>
            extends CallbackWrapper<T> implements CommonApiErrors.ICommonApiErrorVisitor {

        private final Set<CommonApiErrors> commonApiErrors;

        public BasicCommandCallBackWrapper()
        {
            commonApiErrors = EnumSet.allOf(CommonApiErrors.class);
        }

        /**
         * Find Common Api Error From Code Name
         * @param codeName
         * @return
         */
        private CommonApiErrors findCommonApiErrorFromCodeName(final String codeName) {

            CommonApiErrors commonApiError = null;

            for(final CommonApiErrors ce: commonApiErrors) {
                if (ce.name().equalsIgnoreCase(codeName)) {
                    commonApiError = ce;
                    break;
                }
            }

            return commonApiError;

        }


        /**
         * On Next
         * @param t
         */
        @Override
        public void onNext(T t) {
            super.onNext(t);
            onSuccess(t);
        }

        @Override
        protected void onNetworkError() {
            Timber.e("On Network Error");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onNetworkError();
            }
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            Timber.e("On Other Error -> %s", ex.getMessage());
            notifyUnexpectedException();
        }

        @Override
        protected void onApiException(APIResponse response) {
            if (response != null) {
                Timber.e("On Api Exception -> %s - %s", response.getCode(), response.getCodeName());

                final CommonApiErrors commonApiError = findCommonApiErrorFromCodeName(response.getCodeName());

                if(commonApiError != null) {

                    commonApiError.accept(this, response.getData());

                } else {

                    Timber.e("No Api Code Name Founded");
                    notifyUnexpectedException();

                }

            } else {

                Timber.e("Response is null");
                notifyUnexpectedException();
            }
        }

        /**
         * Visit Generic Error
         * @param errors
         */
        @Override
        public void visitGenericError(CommonApiErrors errors) {
            notifyUnexpectedException();
        }

        /**
         * Visit Message Not Readable
         * @param errors
         */
        @Override
        public void visitMessageNotReadable(CommonApiErrors errors) {
            notifyUnexpectedException();
        }

        /**
         * On Success
         * @param response
         */
        protected abstract void onSuccess(final T response);

    }


    /**
     * Signup Api Errors
     */
    public enum CommonApiErrors implements ISupportVisitable<CommonApiErrors.ICommonApiErrorVisitor> {

        /**
         * Generic Error
         */
        GENERIC_ERROR() {
            @Override
            public <E> void accept(ICommonApiErrorVisitor visitor, E data) {
                visitor.visitGenericError(this);
            }
        },
        /**
         * Message Not Readable
         */
        MESSAGE_NOT_READABLE(){
            @Override
            public <E> void accept(ICommonApiErrorVisitor visitor, E data) {
                visitor.visitMessageNotReadable(this);
            }
        };

        /**
         * Signup Api Error Visitor
         */
        public interface ICommonApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Generic Error
             * @param errors
             */
            void visitGenericError(final CommonApiErrors errors);

            /**
             * Visit Message Not Readable
             * @param errors
             */
            void visitMessageNotReadable(final CommonApiErrors errors);
        }

    }






}
