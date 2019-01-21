package sanchez.sanchez.sergio.bullkeeper.ui.activity.dayscheduleddetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerFunTimeComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.FunTimeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail.DayScheduledDetailActivityMvpFragment;

/**
 * Day Scheduled Mvp Activity
 */
public class DayScheduledMvpActivity extends SupportMvpActivity<DayScheduledPresenter, IDayScheduledDetailView>
        implements HasComponent<FunTimeComponent>
        , IDayScheduledDetailView, IDayScheduledActivityHandler {

    private final String CONTENT_FULL_NAME = "FUN_TIME_DAY_SCHEDULED_DETAIL";
    private final String CONTENT_TYPE_NAME = "FUN_TIME";

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String DAY_SCHEDULED_ARG = "DAY_SCHEDULED_ARG";
    public static String FUN_TIME_ENABLED_ARG = "FUN_TIME_ENABLED_ARG";

    /**
     * Fun Time Component
     */
    private FunTimeComponent funTimeComponent;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @param terminalId
     * @param day
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid
            , final String terminalId, final String day, final boolean isFunTimeEnabled) {
        final Intent intent = new Intent(context, DayScheduledMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminalId);
        intent.putExtra(DAY_SCHEDULED_ARG, day);
        intent.putExtra(FUN_TIME_ENABLED_ARG, isFunTimeEnabled);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        funTimeComponent = DaggerFunTimeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        funTimeComponent.inject(this);

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


            if(!getIntent().hasExtra(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an terminal identifier");

            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an kid identifier");

            if (!getIntent().hasExtra(DAY_SCHEDULED_ARG))
                throw new IllegalArgumentException("It is necessary to specify an day scheduled identifier");

            if (!getIntent().hasExtra(FUN_TIME_ENABLED_ARG))
                throw new IllegalArgumentException("It is necessary to specify a fun time status");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String day = getIntent().getStringExtra(DAY_SCHEDULED_ARG);
            final boolean isFunTimeEnabled = getIntent()
                    .getBooleanExtra(FUN_TIME_ENABLED_ARG, false);

            addFragment(R.id.mainContainer,
                    DayScheduledDetailActivityMvpFragment
                            .newInstance(terminal, kid, day, isFunTimeEnabled), false);
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
    public DayScheduledPresenter providePresenter() {
        return funTimeComponent.dayScheduledPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public FunTimeComponent getComponent() {
        return funTimeComponent;
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
