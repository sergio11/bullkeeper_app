package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.crashlytics.android.answers.ContentViewEvent;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.AlertsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerAlertsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail.AlertDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;

/**
 * Alert Detail
 */
public class AlertDetailMvpActivity extends SupportMvpActivity<AlertDetailPresenter, IAlertDetailView>
        implements HasComponent<AlertsComponent>, IAlertDetailActivityHandler
        , IAlertDetailView  {

    private final String CONTENT_FULL_NAME = "ALERT_DETAIL";
    private final String CONTENT_TYPE_NAME = "ALERTS";

    public static String ALERT_ID_ARG = "ALERT_ID_ARG";
    public static String SON_ID_ARG = "SON_ID_ARG";

    private AlertsComponent alertsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String alertId, final String sonId) {
        final Intent intent = new Intent(context, AlertDetailMvpActivity.class);
        intent.putExtra(ALERT_ID_ARG, alertId);
        intent.putExtra(SON_ID_ARG, sonId);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        alertsComponent = DaggerAlertsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        alertsComponent.inject(this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_alert_detail;
    }


    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {


            if(!getIntent().hasExtra(ALERT_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an alert identifier");

            if (!getIntent().hasExtra(SON_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an son identifier");

            final String alertId = getIntent().getStringExtra(ALERT_ID_ARG);
            final String sonId = getIntent().getStringExtra(SON_ID_ARG);
            addFragment(R.id.mainContainer,
                    AlertDetailActivityMvpFragment.newInstance(alertId, sonId), false);
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
    public AlertDetailPresenter providePresenter() {
        return alertsComponent.alertDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public AlertsComponent getComponent() {
        return alertsComponent;
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
