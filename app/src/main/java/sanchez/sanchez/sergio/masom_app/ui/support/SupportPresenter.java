package sanchez.sanchez.sergio.masom_app.ui.support;

import android.os.Bundle;

import net.grandcentrix.thirtyinch.TiPresenter;

import io.reactivex.disposables.CompositeDisposable;

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
}
