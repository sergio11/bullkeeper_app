package sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSmsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SmsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail.AppInstalledDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail.SmsDetailActivityMvpFragment;


/**
 * Sms Detail
 */
public class SmsDetailMvpActivity extends SupportMvpActivity<SmsDetailPresenter, ISmsDetailView>
        implements HasComponent<SmsComponent>
        , ISmsDetailView, ISmsDetailActivityHandler {

    private final String CONTENT_FULL_NAME = "SMS_DETAILS";
    private final String CONTENT_TYPE_NAME = "SMS";

    /**
     * Args
     */
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String SMS_ID_ARG = "SMS_ID_ARG";

    /**
     * SMS Component
     */
    private SmsComponent smsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid,
                                          final String terminal, final String sms) {
        final Intent intent = new Intent(context, SmsDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        intent.putExtra(SMS_ID_ARG, sms);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        smsComponent = DaggerSmsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        smsComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }


    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {

            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an son identifier");

            if(!getIntent().hasExtra(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an terminal identifier");

            if(!getIntent().hasExtra(SMS_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an sms identifier");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String sms = getIntent().getStringExtra(SMS_ID_ARG);

            addFragment(R.id.mainContainer,
                    SmsDetailActivityMvpFragment.newInstance(kid, terminal, sms), false);
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
    public SmsDetailPresenter providePresenter() {
        return smsComponent.smsDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public SmsComponent getComponent() {
        return smsComponent;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_5;
    }

}
