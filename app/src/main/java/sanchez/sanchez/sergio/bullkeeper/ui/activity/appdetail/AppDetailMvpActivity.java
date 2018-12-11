package sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.AppInstalledComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerAppInstalledComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.appdetail.AppInstalledDetailActivityMvpFragment;


/**
 * App Detail
 */
public class AppDetailMvpActivity extends SupportMvpActivity<AppDetailPresenter, IAppDetailView>
        implements HasComponent<AppInstalledComponent>
        , IAppDetailView, IAppDetailActivityHandler {

    private final String CONTENT_FULL_NAME = "APP_DETAIL";
    private final String CONTENT_TYPE_NAME = "APPS_INSTALLED";

    /**
     * Args
     */
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String APP_ID_ARG = "APP_ID_ARG";

    /**
     * App Installed Component
     */
    private AppInstalledComponent appInstalledComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid,
                                          final String terminal, final String app) {
        final Intent intent = new Intent(context, AppDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        intent.putExtra(APP_ID_ARG, app);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        appInstalledComponent = DaggerAppInstalledComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        appInstalledComponent.inject(this);

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

            if(!getIntent().hasExtra(APP_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an app identifier");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String app = getIntent().getStringExtra(APP_ID_ARG);

            addFragment(R.id.mainContainer,
                    AppInstalledDetailActivityMvpFragment.newInstance(kid, terminal, app), false);
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
    public AppDetailPresenter providePresenter() {
        return appInstalledComponent.appDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public AppInstalledComponent getComponent() {
        return appInstalledComponent;
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
