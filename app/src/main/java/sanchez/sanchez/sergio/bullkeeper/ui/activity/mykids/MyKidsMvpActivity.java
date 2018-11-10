package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.crashlytics.android.answers.ContentViewEvent;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerMyKidsComponent;

/**
 * My Kids Activity
 */
public class MyKidsMvpActivity extends SupportMvpActivity<MyKidsActivityPresenter, IMyKidsActivityView>
        implements HasComponent<MyKidsComponent>, IMyKidsActivityHandler
        , IMyKidsActivityView {


    private MyKidsComponent myKidsComponent;

    private final String CONTENT_FULL_NAME = "MY_KIDS";
    private final String CONTENT_TYPE_NAME = "KIDS";

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, MyKidsMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        myKidsComponent = DaggerMyKidsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        myKidsComponent.inject(this);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_kids;
    }

    /**
     * On Create Content VIew Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsActivityPresenter providePresenter() {
        return myKidsComponent.myKidsActivityPresenter();
    }


    /**
     * Get Component
     * @return
     */
    @Override
    public MyKidsComponent getComponent() {
        return myKidsComponent;
    }

    /**
     * Navigate To My Kids Profile
     * @param identity
     */
    @Override
    public void navigateToMyKidsProfile(String identity) {
        navigatorImpl.navigateToMyKidsProfile(identity);
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(String identity) {
        navigatorImpl.navigateToComments(identity);
    }

    /**
     * Navigate To My Kid Detail
     * @param identity
     */
    @Override
    public void navigateToMyKidDetail(String identity) {
        navigatorImpl.navigateToMyKidsDetail(identity);
    }

    /**
     * Navigate To Kids Results
     * @param identity
     */
    @Override
    public void navigateToKidsResults(String identity) {
        navigatorImpl.navigateToKidsResultsActivity(identity);
    }

    /**
     * Navigate To Add Child
     */
    @Override
    public void navigateToAddChild() {
        navigatorImpl.navigateToAddKids();
    }

    /**
     * Navigate To Son Alerts
     * @param sonIdentity
     */
    @Override
    public void navigateToSonAlerts(String sonIdentity) {
        navigatorImpl.navigateToAlertList(sonIdentity);
    }

    /**
     * Navigate To Kid Realtions
     * @param identity
     */
    @Override
    public void navigateToRelations(String identity) {
        navigatorImpl.navigateToRelations(identity);
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_9;
    }
}
