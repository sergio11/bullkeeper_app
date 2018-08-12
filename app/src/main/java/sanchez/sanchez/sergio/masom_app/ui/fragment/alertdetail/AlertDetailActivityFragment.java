package sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import static sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Alert Detail Activity Fragment
 */
public class AlertDetailActivityFragment extends SupportFragment<AlertDetailFragmentPresenter,
        IAlertDetailView, IAlertDetailActivityHandler> implements IAlertDetailView {

    public static String ALERT_ID_ARG = "ALERT_ID_ARG";

    protected AlertsComponent alertsComponent;

    /**
     * Alert Detail Background
     */
    @BindView(R.id.alertDetailBackground)
    protected ImageView alertDetailBackground;


    /**
     * Alert Detail Message Title
     */
    @BindView(R.id.alertDetailMessageTitle)
    protected TextView alertDetailMessageTitle;

    /**
     * Alert Detail Message Title Separator
     */
    @BindView(R.id.alertDetailMessageTitleSeparator)
    protected View alertDetailMessageTitleSeparator;

    /**
     * Alert Detail Message
     */
    @BindView(R.id.alertDetailMessage)
    protected TextView alertDetailMessage;

    /**
     * Alert Detail Required Actions Title
     */
    @BindView(R.id.alertDetailRequiredActionsTitle)
    protected TextView alertDetailRequiredActionsTitle;

    /**
     * Alert Detail Required Actions Title Separator
     */
    @BindView(R.id.alertDetailRequiredActionsTitleSeparator)
    protected View alertDetailRequiredActionsTitleSeparator;

    /**
     * Alert Detail Actions
     */
    @BindView(R.id.alertDetailActions)
    protected TextView alertDetailActions;

    /**
     * Action Button
     */
    @BindView(R.id.actionButton)
    protected Button actionButton;

    /**
     * Alert Category Image
     */
    @BindView(R.id.alertCategoryImage)
    protected ImageView alertCategoryImage;

    /**
     * Alert Category Text
     */
    @BindView(R.id.alertCategoryText)
    protected TextView alertCategoryText;

    /**
     * Alert Since
     */
    @BindView(R.id.alertSince)
    protected TextView alertSince;

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

        switch (alertEntity.getLevel()) {

            case SUCCESS:

                int successColor = ContextCompat.getColor(appContext, R.color.greenSuccess);

                // Alert Category Image
                alertCategoryImage.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.success_icon_solid));

                // Alert Category Text
                alertCategoryText.setTextColor(successColor);
                alertCategoryText.setText(R.string.alert_category_success_text);

                // Alert Detail Message Title
                alertDetailMessageTitle.setTextColor(successColor);
                alertDetailMessageTitleSeparator.setBackgroundColor(successColor);

                // Alert Detail Require Actions
                alertDetailRequiredActionsTitle.setTextColor(successColor);
                alertDetailRequiredActionsTitleSeparator.setBackgroundColor(successColor);


                //Action Button
                actionButton.setTextColor(successColor);
                actionButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_alt_circle_right_regular_green_52, 0);

                break;

            case DANGER:

                int dangerColor = ContextCompat.getColor(appContext, R.color.redDanger);

                // Alert Category Image
                alertCategoryImage.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.danger_icon_solid));

                // Alert Category Text
                alertCategoryText.setTextColor(dangerColor);
                alertCategoryText.setText(R.string.alert_category_danger_text);

                // Alert Detail Require Actions
                alertDetailRequiredActionsTitle.setTextColor(dangerColor);
                alertDetailRequiredActionsTitleSeparator.setBackgroundColor(dangerColor);

                // Alert Detail Message Title
                alertDetailMessageTitle.setTextColor(dangerColor);
                alertDetailMessageTitleSeparator.setBackgroundColor(dangerColor);

                // Action Button
                actionButton.setTextColor(dangerColor);
                actionButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_alt_circle_right_regular_red_52, 0);

                break;

            case INFO:

                int infoColor = ContextCompat.getColor(appContext, R.color.cyanBrilliant);

                // Alert Category Image
                alertCategoryImage.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.info_icon_solid));

                // Alert Category Text
                alertCategoryText.setTextColor(infoColor);
                alertCategoryText.setText(R.string.alert_category_danger_text);

                // Alert Detail Message Title
                alertDetailMessageTitle.setTextColor(infoColor);
                alertDetailMessageTitleSeparator.setBackgroundColor(infoColor);

                // Alert Detail Require Actions
                alertDetailRequiredActionsTitle.setTextColor(infoColor);
                alertDetailRequiredActionsTitleSeparator.setBackgroundColor(infoColor);

                // Action Button
                actionButton.setTextColor(ContextCompat.getColor(appContext, R.color.cyanBrilliant));
                actionButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_alt_circle_right_regular_52, 0);

                break;

            case WARNING:

                int warningColor = ContextCompat.getColor(appContext, R.color.yellowWarning);

                // Alert Category Image
                alertCategoryImage.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.warning_icon_solid));

                // Alert Category Text
                alertCategoryText.setTextColor(warningColor);
                alertCategoryText.setText(R.string.alert_category_warning_text);

                // Alert Detail Message Title
                alertDetailMessageTitle.setTextColor(warningColor);
                alertDetailMessageTitleSeparator.setBackgroundColor(warningColor);

                // Alert Detail Require Actions
                alertDetailRequiredActionsTitle.setTextColor(warningColor);
                alertDetailRequiredActionsTitleSeparator.setBackgroundColor(warningColor);

                // Action Button
                actionButton.setTextColor(ContextCompat.getColor(appContext, R.color.yellowWarning));
                actionButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_alt_circle_right_regular_yellow_52, 0);

                break;

            default:

                int defaultColor = ContextCompat.getColor(appContext, R.color.cyanBrilliant);

                // Alert Category Image
                alertCategoryImage.setImageDrawable(ContextCompat.getDrawable(appContext, R.drawable.info_icon_solid));

                // Alert Category Text
                alertCategoryText.setTextColor(defaultColor);
                alertCategoryText.setText(R.string.alert_category_danger_text);

                // Alert Detail Message Title
                alertDetailMessageTitle.setTextColor(defaultColor);
                alertDetailMessageTitleSeparator.setBackgroundColor(defaultColor);

                // Alert Detail Require Actions
                alertDetailRequiredActionsTitle.setTextColor(defaultColor);
                alertDetailRequiredActionsTitleSeparator.setBackgroundColor(defaultColor);

                // Action Button
                actionButton.setTextColor(ContextCompat.getColor(appContext, R.color.cyanBrilliant));
                actionButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_alt_circle_right_regular_52, 0);

                break;

        }

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


    /**
     * On Action Button
     */
    @OnClick(R.id.actionButton)
    protected void onActionButton(){}

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return TOOLBAR_WITH_MENU;
    }
}
