package sanchez.sanchez.sergio.masom_app.ui.support;

import android.os.Bundle;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import sanchez.sanchez.sergio.data.models.response.APIResponse;
import sanchez.sanchez.sergio.data.utils.CallbackWrapper;
import timber.log.Timber;

/**
 * Support Presenter
 * @param <T>
 */
public abstract class SupportPresenter<T extends ISupportView> extends TiPresenter<T> {

    protected CompositeDisposable compositeDisposable;

    public SupportPresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
        releaseSubscription();


    }

    /**
     * Release Subscriptions
     */
    private void releaseSubscription(){
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    public void init(){}

    public void init(Bundle args){}

    /**
     * Command CallBack Wrapper
     * @param <T>
     */
    public abstract class CommandCallBackWrapper<T, V extends ISupportVisitor,
            E extends Enum<E> & ISupportVisitable<V>>
            extends CallbackWrapper<T> {

        private final Set<E> apiErrorsValues;

        public CommandCallBackWrapper(Class<E> apiErrors)
        {
            apiErrorsValues= EnumSet.allOf(apiErrors);
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
            if(isViewAttached() && getView() != null)
                getView().onNetworkError();
        }

        /**
         * On Other Exception
         * @param ex
         */
        @Override
        protected void onOtherException(Throwable ex) {
            Timber.e("On Other Error -> %s", ex.getMessage());
            if(isViewAttached() && getView() != null)
                getView().onOtherException();
        }

        @Override
        protected void onApiException(APIResponse response) {
            if (response != null) {
                Timber.e("On Api Exception -> %s - %s", response.getCode(), response.getCodeName());
                final Iterator<E> apiErrorsIte = apiErrorsValues.iterator();
                while (apiErrorsIte.hasNext()) {
                    final E apiError = apiErrorsIte.next();
                    if(apiError.name().equalsIgnoreCase(response.getCodeName())) {
                        apiError.accept((V)this);
                        break;
                    }
                }
            }
        }

        /**
         * On Success
         * @param response
         */
        protected abstract void onSuccess(final T response);
    }

    /**
     * Support Visitable
     */
    public interface ISupportVisitable<T extends ISupportVisitor> {
        /**
         * Accept
         * @param visitor
         */
        void accept(T visitor);
    }

    /**
     * Support Visitor
     */
    public interface ISupportVisitor{}

}
