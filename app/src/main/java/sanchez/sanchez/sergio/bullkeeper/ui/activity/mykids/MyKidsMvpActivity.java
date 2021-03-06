package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
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
     * Activity
     */
    @Inject
    protected Activity activity;

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
        navigatorImpl.navigateToMyKidsProfile(activity, identity);
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(String identity) {
        navigatorImpl.navigateToComments(activity, identity);
    }

    /**
     *
     * @param identity
     */
    @Override
    public void navigateToMyKidDetail(final String identity) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        navigatorImpl.navigateToMyKidsDetail(activity, identity);
    }


    /**
     * Navigate To Kids Results
     * @param identity
     */
    @Override
    public void navigateToKidsResults(String identity) {
        navigatorImpl.navigateToKidsResultsActivity(activity, identity);
    }

    /**
     * Navigate To Add Child
     */
    @Override
    public void navigateToAddChild() {
        navigatorImpl.navigateToAddKids(activity);
    }

    /**
     * Navigate To Son Alerts
     * @param kid
     */
    @Override
    public void navigateToKidAlerts(String kid) {
        navigatorImpl.navigateToAlertList(activity, kid);
    }


    /**
     * Navigate To Invitations
     */
    @Override
    public void navigateToInvitations() {
        navigatorImpl.navigateToInvitations(this);
    }

    /**
     * Navigate To Conversation List
     */
    @Override
    public void navigateToConversationMessagesList(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        final String currentUserId = preferencesRepositoryImpl.getPrefCurrentUserIdentity();
        navigatorImpl.navigateToConversationMessageList(this, currentUserId, kid);
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
