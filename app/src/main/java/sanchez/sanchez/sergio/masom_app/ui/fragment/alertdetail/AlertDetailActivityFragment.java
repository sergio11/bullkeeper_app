package sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.AlertsComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.IAlertDetailActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Alert Detail Activity Fragment
 */
public class AlertDetailActivityFragment extends SupportFragment<AlertDetailFragmentPresenter,
        IAlertDetailView, IAlertDetailActivityHandler> implements IAlertDetailView {

    public static String ALERT_ID_ARG = "ALERT_ID_ARG";

    protected AlertsComponent alertsComponent;

    @BindView(R.id.alertDetailBackground)
    protected ImageView alertDetailBackground;

    @Inject
    protected Context appContext;

    public AlertDetailActivityFragment() { }

    /**
     * New Instance
     * @param alertId
     */
    public static AlertDetailActivityFragment newInstance(final String alertId) {
        final AlertDetailActivityFragment alertDetailActivityFragment =
                new AlertDetailActivityFragment();
        final Bundle args = new Bundle();
        args.putString(ALERT_ID_ARG, alertId);
        alertDetailActivityFragment.setArguments(args);
        return alertDetailActivityFragment;
    }

    /**
     * ON Alert Info Loaded
     * @param alertEntity
     */
    @Override
    public void onAlertInfoLoaded(AlertEntity alertEntity) {

        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .into(alertDetailBackground);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_alert_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        alertsComponent = AlertsComponent.class
                .cast(((HasComponent<AlertsComponent>) getActivity())
                        .getComponent());

        alertsComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AlertDetailFragmentPresenter providePresenter() {
        return alertsComponent.alertDetailFragmentPresenter();
    }

    /**
     * On Remove Alert
     */
    @OnClick(R.id.removeAlert)
    protected void onRemoveAlert(){

        showConfirmationDialog(R.string.remove_alert_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {

                showNoticeDialog(R.string.alert_removed_successfully, new NoticeDialogFragment.NoticeDialogListener() {
                    @Override
                    public void onAccepted(DialogFragment dialog) {
                        activityHandler.closeActivity();
                    }
                });

            }

            @Override
            public void onRejected(DialogFragment dialog) {

            }
        });

    }
}
