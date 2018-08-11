package sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.AlertsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerAlertsComponent;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail.AlertDetailActivityFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * Alert Detail
 */
public class AlertDetailActivity extends SupportActivity<AlertDetailPresenter, IAlertDetailView>
        implements HasComponent<AlertsComponent>, IAlertDetailActivityHandler
        , IAlertDetailView  {

    public static String ALERT_ID_ARG = "ALERT_ID_ARG";

    private AlertsComponent alertsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String identity) {
        final Intent intent = new Intent(context, AlertDetailActivity.class);
        intent.putExtra(ALERT_ID_ARG, identity);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);

        if(savedInstanceState == null) {

            if(!getIntent().hasExtra(ALERT_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an alert identifier");

            final String alertId = getIntent().getStringExtra(ALERT_ID_ARG);
            addFragment(R.id.mainContainer, AlertDetailActivityFragment.newInstance(alertId));
        }

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
