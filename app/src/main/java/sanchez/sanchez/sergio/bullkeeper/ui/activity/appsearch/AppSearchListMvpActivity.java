package sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import butterknife.BindView;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.AppInstalledComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerAppInstalledComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.AppInstalledByTerminalAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * App Search List Activity
 */
public class AppSearchListMvpActivity extends SupportMvpSearchLCEActivity<AppSearchListPresenter, IAppSearchListView, AppInstalledByTerminalEntity>
        implements HasComponent<AppInstalledComponent>, IAppSearchListActivityHandler, IAppSearchListView {

    /**
     *
     */
    private final String CONTENT_FULL_NAME = "APP_SEARCH";
    private final String CONTENT_TYPE_NAME = "APPS";

    /**
     * Args
     */
    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * App Selected Arg
     */
    public static final String APP_SELECTED_ARG = "APP_SELECTED_ARG";

    /**
     * App Installed Component
     */
    private AppInstalledComponent appInstalledComponent;

    /**
     * State
     * ===================
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Views
     * =============
     */

    /**
     * App Search Header Title Text View
     */
    @BindView(R.id.searchHeaderTitle)
    protected TextView appSearchHeaderTitleTextView;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        final Intent intent = new Intent(context, AppSearchListMvpActivity.class);
        intent.putExtra(KID_IDENTITY_ARG, kid);
        return intent;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if (getIntent().getExtras() != null) {

            final Bundle extras = getIntent().getExtras();

            if (!extras.containsKey(KID_IDENTITY_ARG))
                throw new IllegalArgumentException("You must provide kid id");

            kid = extras.getString(KID_IDENTITY_ARG);

        } else {

            throw new IllegalArgumentException("You must provide args");
        }

    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.appInstalledComponent = DaggerAppInstalledComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.appInstalledComponent.inject(this);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        super.getArgs();
        final Bundle args = new Bundle();
        args.putString(AppSearchListPresenter.KID_IDENTITY_ARG, kid);
        return args;
    }



    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public AppSearchListPresenter providePresenter() {
        return appInstalledComponent.appSearchListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public AppInstalledComponent getComponent() {
        return appInstalledComponent;
    }


    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param appInstalledEntity
     */
    @Override
    public void onItemClick(final AppInstalledByTerminalEntity appInstalledEntity) {
        final Intent appSelectedIntentResult = new Intent();
        appSelectedIntentResult.putExtra(APP_SELECTED_ARG, appInstalledEntity);
        onResultOk(appSelectedIntentResult);
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_app_search_list;
    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AppInstalledByTerminalEntity> getAdapter() {
        final AppInstalledByTerminalAdapter appInstalledByTerminalAdapter =
                new AppInstalledByTerminalAdapter(this, new ArrayList<AppInstalledByTerminalEntity>());
        appInstalledByTerminalAdapter.setOnSupportRecyclerViewListener(this);
        return appInstalledByTerminalAdapter;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_4;
    }

    /**
     * Get Query Hint
     * @return
     */
    @Override
    protected int getQueryHint() {
        return R.string.search_apps_by_name_hint;
    }
}
