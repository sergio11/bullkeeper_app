package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidresultssettings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.crashlytics.android.answers.ContentViewEvent;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidresultssettings.KidResultsSettingsActivityFragment;


/**
 * Kid Results Settings Activity
 */
public class KidResultsSettingsMvpActivity extends SupportMvpActivity<KidResultsSettingsActivityPresenter, IKidResultsSettingsView>
        implements HasComponent<SettingsComponent>, IKidResultsSettingsActivityHandler
        , IKidResultsSettingsView {

    private final String CONTENT_FULL_NAME = "KID_RESULTS_SETTINGS";
    private final String CONTENT_TYPE_NAME = "KID_RESULTS";

    /**
     * Settings Component
     */
    private SettingsComponent settingsComponent;

    /**
     * Kid Results Settings Activity Fragment
     */
    protected KidResultsSettingsActivityFragment kidResultsSettingsActivityFragment;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, KidResultsSettingsMvpActivity.class);
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.settingsComponent = DaggerSettingsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.settingsComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidResultsSettingsActivityPresenter providePresenter() {
        return settingsComponent.kidResultsSettingsActivityPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public SettingsComponent getComponent() {
        return settingsComponent;
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_settings;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        kidResultsSettingsActivityFragment = new KidResultsSettingsActivityFragment();
        if (savedInstanceState == null)
            addFragment(R.id.mainContainer, kidResultsSettingsActivityFragment,
                    false);
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
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {
        super.hasPendingChanges();
        return kidResultsSettingsActivityFragment.hasPendingChanges();
    }

    /**
     * On Save Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        super.onSavedPendingChanges();
        kidResultsSettingsActivityFragment.onSavedPendingChanges();
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        super.onDiscardPendingChanges();
        kidResultsSettingsActivityFragment.onDiscardPendingChanges();
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }
}
