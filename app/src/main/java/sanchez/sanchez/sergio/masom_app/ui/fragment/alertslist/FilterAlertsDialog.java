package sanchez.sanchez.sergio.masom_app.ui.fragment.alertslist;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.AlertsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerAlertsComponent;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertlist.IAlertListActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportDialogFragment;

/**
 * Filter Alerts Dialog
 */
public final class FilterAlertsDialog extends SupportDialogFragment {

    public static final String TAG = "FILTER_ALERTS_DIALOG";

    /**
     * Alerts Component
     */
    private AlertsComponent alertsComponent;

    /**
     * Alerts List Activity Handler
     */
    private IAlertListActivityHandler alertListActivityHandler;


    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity) {
        final FilterAlertsDialog menuDialogFragment = new FilterAlertsDialog();
        menuDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        menuDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            alertListActivityHandler = (IAlertListActivityHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IAlertListActivityHandler");
        }
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.filter_alerts_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.alertsComponent = DaggerAlertsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build();
        this.alertsComponent.inject(this);
    }

    /**
     * On ApplyChanges
     */
    @OnClick(R.id.applyChanges)
    protected void onApplyChanges(){
        alertListActivityHandler.onApplyChanges();
        this.dismiss();
    }
}
