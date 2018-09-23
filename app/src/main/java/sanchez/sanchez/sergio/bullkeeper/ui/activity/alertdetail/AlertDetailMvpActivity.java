package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

}
