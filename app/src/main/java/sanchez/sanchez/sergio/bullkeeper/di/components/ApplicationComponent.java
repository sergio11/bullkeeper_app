package sanchez.sanchez.sergio.bullkeeper.di.components;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.sse.ISseEventHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.SearchSchoolLocationDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AddPhoneNumberBlockedDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ProgressDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats.AppStatsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile.ChildAlertsDetailDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.services.RemoteNotificationService;
import sanchez.sanchez.sergio.bullkeeper.core.utils.ScreenManager;
import sanchez.sanchez.sergio.bullkeeper.core.utils.UiUtils;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ApiModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ApplicationModule;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.splash.SplashScreenActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.menu.MenuDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.domain.utils.IAuthTokenAware;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = { ApplicationModule.class, ApiModule.class })
public interface ApplicationComponent {

    /**
     * Inject into Splash Screen Activity
     * @param splashScreenActivity
     */
    void inject(final SplashScreenActivity splashScreenActivity);

    /**
     * Menu Dialog Fragment
     * @param menuDialogFragment
     */
    void inject(MenuDialogFragment menuDialogFragment);

    /**
     * Inject Photo View Dialog
     * @param photoViewerDialog
     */
    void inject(final PhotoViewerDialog photoViewerDialog);

    /**
     * Inject Search School Location Dialog
     * @param searchSchoolLocationDialog
     */
    void inject(final SearchSchoolLocationDialog searchSchoolLocationDialog);

    /**
     * Remote Notification Service
     * @param remoteNotificationService
     */
    void inject(final RemoteNotificationService remoteNotificationService);

    /**
     * Inject into Comments Extracted By Social Media Dialog
     * @param commentsExtractedBySocialMediaDialog
     */
    void inject(final CommentsExtractedBySocialMediaDialog commentsExtractedBySocialMediaDialog);

    /**
     * Inject into System Alerts Dialog
     * @param systemAlertsDialog
     */
    void inject(final SystemAlertsDialog systemAlertsDialog);

    /**
     * Inject into Child Alerts Detail Dialog
     * @param childAlertsDetailDialog
     */
    void inject(final ChildAlertsDetailDialog childAlertsDetailDialog);

    /**
     * Inject into Confirmation Dialog Fragment
     * @param confirmationDialogFragment
     */
    void inject(final ConfirmationDialogFragment confirmationDialogFragment);

    /**
     * Inject into Notice Dialog Fragment
     * @param noticeDialogFragment
     */
    void inject(final NoticeDialogFragment noticeDialogFragment);

    /**
     * Inject into Progress Dialog Fragment
     * @param progressDialogFragment
     */
    void inject(final ProgressDialogFragment progressDialogFragment);

    /**
     * Inject into Add Phone Number Blocked Dialog
     * @param addPhoneNumberBlockedDialogFragment
     */
    void inject(final AddPhoneNumberBlockedDialogFragment addPhoneNumberBlockedDialogFragment);

    /**
     * Inject into App Stats Dialog
     * @param appStatsDialog
     */
    void inject(final AppStatsDialog appStatsDialog);


    //Exposed to sub-graphs.
    Context context();
    IThreadExecutor threadExecutor();
    IPostExecutionThread postExecutionThread();
    INavigator navigator();
    ILocalSystemNotification localSystemNotification();
    //IRemoteSystemNotification remoteSystemNotification();
    INotificationHelper notificationHelper();
    IPreferenceRepository preferencesManager();
    Retrofit retrofit();
    IAppUtils appUtils();
    IAuthTokenAware authTokenAware();
    ApiEndPointsHelper apiEndPointsHelper();
    Picasso picasso();
    ScreenManager screenManager();
    UiUtils uiUtils();
    ISoundManager soundManager();
    ISseEventHandler sseEventHandler();
}
