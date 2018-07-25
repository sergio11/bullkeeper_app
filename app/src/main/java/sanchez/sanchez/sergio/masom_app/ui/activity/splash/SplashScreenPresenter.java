package sanchez.sanchez.sergio.masom_app.ui.activity.splash;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

import sanchez.sanchez.sergio.masom_app.navigation.INavigator;

/**
 * Home Presenter
 */
public final class SplashScreenPresenter extends TiPresenter<ISplashScreenView> {

    private INavigator navigator;

    @Inject
    public SplashScreenPresenter(final INavigator navigator) {
        super();
        this.navigator = navigator;
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final ISplashScreenView view) {
        super.onAttachView(view);

        view.showLongMessage("Hello World!!!");
        // Navigation to Intro
        navigator.navigateToIntro();

    }
}
