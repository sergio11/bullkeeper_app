package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerKidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.KidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail.KidRequestDetailActivityMvpFragment;

/**
 * Kid Request Detail Mvp Activity
 */
public class KidRequestDetailMvpActivity extends SupportMvpActivity<KidRequestDetailPresenter, IKidRequestDetailView>
        implements HasComponent<KidRequestComponent>
        , IKidRequestDetailView, IKidRequestDetailActivityHandler {

    /**
     *
     */
    private final String CONTENT_FULL_NAME = "KID_REQUEST_DETAIL";
    private final String CONTENT_TYPE_NAME = "KID_REQUEST";

    /**
     * Args
     */
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String ID_ARG = "ID_ARG";
    public static String PHONE_NUMBER_ARG = "PHONE_NUMBER_ARG";

    /**
     * Kid Request Component
     */
    private KidRequestComponent kidRequestComponent;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Activity context, final String kid, final String identity) {
        final Intent intent = new Intent(context, KidRequestDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(ID_ARG, identity);
        return intent;
    }

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid, final String identity) {
        final Intent intent = new Intent(context, KidRequestDetailMvpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(ID_ARG, identity);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        kidRequestComponent = DaggerKidRequestComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        kidRequestComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {

            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an kid identifier");

            final String kid = getIntent().getStringExtra(KID_ID_ARG);

            if (!getIntent().hasExtra(ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify a identifier");

            final String id = getIntent().getStringExtra(ID_ARG);

            addFragment(R.id.mainContainer,
                    KidRequestDetailActivityMvpFragment.newInstance(kid, id),
                    false);
        }
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent()
                .putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidRequestDetailPresenter providePresenter() {
        return kidRequestComponent.kidRequestDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public KidRequestComponent getComponent() {
        return kidRequestComponent;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_5;
    }

    /**
     * Navigate To Conversation Message List
     * @param kid
     */
    @Override
    public void navigateToConversationMessagesList(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        final String currentUserId =
                preferencesRepositoryImpl.getPrefCurrentUserIdentity();
        navigatorImpl.navigateToConversationMessageList(this, currentUserId, kid);
    }

    /**
     * Make Phone Call
     * @param phoneNumber
     */
    @Override
    public void makePhoneCall(final String phoneNumber) {
        Preconditions.checkNotNull(phoneNumber, "Phone Number can not be null");
        Preconditions.checkState(!phoneNumber.isEmpty(), "Phone Number can not be empty");

        if(permissionManager.shouldAskPermission(Manifest.permission.CALL_PHONE)) {
            final Bundle callbackArgs = new Bundle();
            callbackArgs.putString(PHONE_NUMBER_ARG, phoneNumber);
            permissionManager.checkSinglePermission(Manifest.permission.CALL_PHONE,
                    getString(R.string.call_phone_permission_reason), callbackArgs);
        }else
            navigatorImpl.startPhoneCall(this, phoneNumber);

    }

    /**
     *
     * @param permission
     * @param callbackArgs
     */
    @Override
    public void onSinglePermissionGranted(String permission, Bundle callbackArgs) {
        super.onSinglePermissionGranted(permission, callbackArgs);

        if(permission.equalsIgnoreCase(Manifest.permission.CALL_PHONE) &&
                callbackArgs.containsKey(PHONE_NUMBER_ARG)) {
            final String phoneNumber = callbackArgs.getString(PHONE_NUMBER_ARG);
            navigatorImpl.startPhoneCall(this, phoneNumber);
        }
    }
}
