package sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.AlertsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail.IAlertDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Alert Detail Activity Fragment
 */
public class AlertDetailActivityMvpFragment extends SupportMvpFragment<AlertDetailFragmentPresenter,
        IAlertDetailView, IAlertDetailActivityHandler, AlertsComponent> implements IAlertDetailView {

    public static String ALERT_ID_ARG = "TERMINAL_ID_ARG";
    public static String SON_ID_ARG = "KID_ID_ARG";

    /**
     * Alert Detail Background
     */
    @BindView(R.id.alertDetailBackground)
    protected ImageView alertDetailBackground;

    /**
     * Alert Title View
     */
    @BindView(R.id.alertTitle)
    protected TextView alertTitleView;

    /**
     * Son Full Name
     */
    @BindView(R.id.sonFullName)
    protected TextView sonFullNameView;


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

    @BindView(R.id.removeAlert)
    protected FloatingActionButton removeAlertButton;

    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;



    public AlertDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param alertId
     */
    public static AlertDetailActivityMvpFragment newInstance(final String alertId, final String sonId) {
        final AlertDetailActivityMvpFragment alertDetailActivityFragment =
                new AlertDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(ALERT_ID_ARG, alertId);
        args.putString(SON_ID_ARG, sonId);
        alertDetailActivityFragment.setArguments(args);
        return alertDetailActivityFragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * ON Alert Info Loaded
     * @param alertEntity
     */
    @Override
    public void onAlertInfoLoaded(final AlertEntity alertEntity) {

        if(appUtils.isValidString(alertEntity.getSon().getProfileImage()))
            picasso.load(alertEntity.getSon().getProfileImage())
                    .placeholder(R.drawable.kid_default_image)
                    .error(R.drawable.kid_default_image)
                    .into(alertDetailBackground);
        else
            alertDetailBackground.setImageResource(R.drawable.kid_default_image);

        // Alert Title
        alertTitleView.setText(alertEntity.getTitle());

        // Son Full Name
        sonFullNameView.setText(alertEntity.getSon().getFullName());

        // Alert Since
        alertSince.setText(alertEntity.getSince());

        // Alert Payload
        alertDetailMessage.setText(alertEntity.getPayload());

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


        // Alert Category
        switch (alertEntity.getCategory()) {

            case DEFAULT:
                // Alert Category Default
                alertDetailActions.setText(getString(R.string.alert_category_default));
                actionButton.setVisibility(View.GONE);
                break;

            case STATISTICS_KID:
                // Statistics Son Category
                alertDetailActions.setText(getString(R.string.alert_category_statistics_kid_desc));
                actionButton.setText(getString(R.string.alert_category_statistics_kid_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToKidStatistics(alertEntity.getSon().getIdentity());
                    }
                });

                break;

            case INFORMATION_KID:
                // Information Son Category
                alertDetailActions.setText(getString(R.string.alert_category_information_kid_desc));
                actionButton.setText(getString(R.string.alert_category_information_kid_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToEditKid(alertEntity.getSon().getIdentity());
                    }
                });

                break;

            case GENERAL_STATISTICS:
                // General Statistics
                alertDetailActions.setText(getString(R.string.alert_category_general_statistics_desc));
                actionButton.setText(getString(R.string.alert_category_general_statistics_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToGeneralStatistics();
                    }
                });
                break;

            case INFORMATION_EXTRACTION:
                // Information Extraction
                alertDetailActions.setText(getString(R.string.alert_category_information_extraction_desc));
                actionButton.setText(getString(R.string.alert_category_information_extraction_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToInformationExtraction(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case GEOFENCES:
                // Geofences
                alertDetailActions.setText(getString(R.string.alert_category_geofences_desc));
                actionButton.setText(getString(R.string.alert_category_geofences_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToGeofences(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case APPS_INSTALLED:
                // Apps Installed
                alertDetailActions.setText(getString(R.string.alert_category_apps_desc));
                actionButton.setText(getString(R.string.alert_category_apps_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToAppsInstalled(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case TERMINALS:
                // Terminals
                alertDetailActions.setText(getString(R.string.alert_category_terminals_desc));
                actionButton.setText(getString(R.string.alert_category_terminals_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToTerminals(alertEntity.getSon().getIdentity());
                    }
                });
                break;


            case KID_REQUEST:
                alertDetailActions.setText(getString(R.string.alert_category_kid_request_desc));
                actionButton.setText(getString(R.string.alert_category_kid_request_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToKidRequestList(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case CONTACTS:

                alertDetailActions.setText(getString(R.string.alert_category_contacts_desc));
                actionButton.setText(getString(R.string.alert_category_contacts_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToContacts(alertEntity.getSon().getIdentity());
                    }
                });

                break;


            case FUN_TIME:
                alertDetailActions.setText(getString(R.string.alert_category_fun_time_desc));
                actionButton.setText(getString(R.string.alert_category_fun_time_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToFunTime(alertEntity.getSon().getIdentity());
                    }
                });
                break;


            case PHONE_NUMBERS:
                alertDetailActions.setText(getString(R.string.alert_category_phone_numbers_desc));
                actionButton.setText(getString(R.string.alert_category_phone_numbers_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToPhoneNumbers(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case SCHEDULED_BLOCK:
                alertDetailActions.setText(getString(R.string.alert_category_scheduled_block_desc));
                actionButton.setText(getString(R.string.alert_category_scheduled_block_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToScheduledBlockList(alertEntity.getSon().getIdentity());
                    }
                });
                break;

            case KID_SUPERVISION_INVITATION:

                alertDetailActions.setText(getString(R.string.alert_category_kid_supervision_invitation_desc));
                actionButton.setText(getString(R.string.alert_category_kid_supervision_invitation_action_text));

                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityHandler.goToKidSupervisionInvitationList(
                                alertEntity.getSon().getIdentity());
                    }
                });

                break;

        }

    }

    /**
     * On Alert Not Founds
     */
    @Override
    public void onAlertNotFound() {

        showNoticeDialog(R.string.alert_not_found, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });

    }

    /**
     * On Alert Deleted
     */
    @Override
    public void onAlertDeleted() {
        showNoticeDialog(R.string.alert_removed_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
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
    protected void initializeInjector(AlertsComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AlertDetailFragmentPresenter providePresenter() {
        return component.alertDetailFragmentPresenter();
    }

    /**
     * On Remove Alert
     */
    @OnClick(R.id.removeAlert)
    protected void onRemoveAlert(){
        uiUtils.startBounceAnimationForView(removeAlertButton);
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            showConfirmationDialog(R.string.remove_alert_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
                @Override
                public void onAccepted(DialogFragment dialog) {

                    getPresenter().deleteAlert();

                }

                @Override
                public void onRejected(DialogFragment dialog) {

                }
            });
        }
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

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

}
