package sanchez.sanchez.sergio.masom_app.navigation.impl;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IntroActivity;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class NavigatorImpl implements INavigator {

    private final Context context;

    @Inject
    public NavigatorImpl(final Context context) {
        this.context = context;
    }

    /**
     * Navigate To Intro
     */
    @Override
    public void navigateToIntro() {
        Intent intentToLaunch = IntroActivity.getCallingIntent(context);
        context.startActivity(intentToLaunch);
    }

    /**
     * Navigate To Home
     */
    @Override
    public void navigateToHome() {

    }
}
