package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.app.Service;

import java.util.EnumSet;
import java.util.Set;

import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.utils.CallbackWrapper;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;
import timber.log.Timber;

/**
 * Support Service
 */
public abstract class SupportService extends Service {

    @Override
    public void onCreate() {
        initializeInjector();
        super.onCreate();
    }

    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
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

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            Timber.e("On Other Error -> %s", ex.getMessage());
            onUnexpectedException();
        }

        /**
         * On API Exception
         * @param response
         */
        @Override
        protected void onApiException(APIResponse response) {
            if (response != null) {
                Timber.e("On Api Exception -> %s - %s", response.getCode(), response.getCodeName());

                final CommonApiErrors commonApiError = findCommonApiErrorFromCodeName(response.getCodeName());

                if(commonApiError != null) {
                    commonApiError.accept(this, response.getData());
                } else {
                    Timber.e("No Api Code Name Founded");
                    onUnexpectedException();
                }
            } else {
                Timber.e("Response is null");
                onUnexpectedException();
            }
        }

        /**
         * Visit Generic Error
         * @param errors
         */
        @Override
        public void visitGenericError(CommonApiErrors errors) {
            onUnexpectedException();
        }

        /**
         * Visit Message Not Readable
         * @param errors
         */
        @Override
        public void visitMessageNotReadable(CommonApiErrors errors) {
            onUnexpectedException();
        }

        /**
         * On Success
         * @param response
         */
        protected abstract void onSuccess(final T response);

        /**
         * On Unexpected Exception
         */
        protected abstract void onUnexpectedException();

    }

    /**
     * Common Api Errors
     */
    public enum CommonApiErrors implements ISupportVisitable<CommonApiErrors.ICommonApiErrorVisitor> {

        /**
         * Generic Error
         */
        GENERIC_ERROR() {
            @Override
            public <E> void accept(CommonApiErrors.ICommonApiErrorVisitor visitor, E data) {
                visitor.visitGenericError(this);
            }
        },
        /**
         * Message Not Readable
         */
        MESSAGE_NOT_READABLE(){
            @Override
            public <E> void accept(CommonApiErrors.ICommonApiErrorVisitor visitor, E data) {
                visitor.visitMessageNotReadable(this);
            }
        };

        /**
         * Common Api Error Visitor
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
