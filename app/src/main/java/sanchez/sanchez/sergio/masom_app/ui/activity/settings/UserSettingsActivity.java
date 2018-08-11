package sanchez.sanchez.sergio.masom_app.ui.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerSettingsComponent;
import sanchez.sanchez.sergio.masom_app.di.components.SettingsComponent;
import sanchez.sanchez.sergio.masom_app.ui.fragment.settings.UserSettingsActivityFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

/**
 * User Settings Activity
 */
public class UserSettingsActivity extends SupportActivity<UserSettingsActivityPresenter, IUserSettingsView>
        implements HasComponent<SettingsComponent>, IUserSettingsActivityHandler
        , IUserSettingsView {


    /**
     * Settings Component
     */
    private SettingsComponent settingsComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, UserSettingsActivity.class);
    }

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_settings);
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
            addFragment(R.id.mainContainer, new UserSettingsActivityFragment());
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
    public UserSettingsActivityPresenter providePresenter() {
        return settingsComponent.userSettingsActivityPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public SettingsComponent getComponent() {
        return settingsComponent;
    }
}
