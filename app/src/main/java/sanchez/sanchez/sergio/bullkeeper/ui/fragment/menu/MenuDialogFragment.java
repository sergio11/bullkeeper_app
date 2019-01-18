package sanchez.sanchez.sergio.bullkeeper.ui.fragment.menu;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.fernandocejas.arrow.collections.Lists;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.events.impl.LogoutEvent;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Menu Dialog Fragment
 */
public final class MenuDialogFragment extends SupportDialogFragment
    implements AdapterView.OnItemClickListener {

    private final static int PROFILE_ITEM_POSITION = 0;
    private final static int SETTINGS_ITEM_POSITION = 1;
    private final static int HOW_ITS_WORK_POSITION = 2;
    private final static int CLOSE_SESSION_POSITION = 3;
    private final static int TERMS_OF_SERVICE_POSITION = 4;
    private final static int PRIVACY_POLICY_POSITION = 5;

    public static final String TAG = "MENU_DIALOG_FRAGMENT";

    @BindView(R.id.appMenuItems)
    protected ListView appMenuItems;

    protected ApplicationComponent applicationComponent;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Preference Manager
     */
    @Inject
    protected IPreferenceRepository preferencesRepositoryImpl;

    /**
     * Local System Notification
     */
    @Inject
    protected ILocalSystemNotification localSystemNotification;

    /**
     * Show
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity) {
        final MenuDialogFragment menuDialogFragment = new MenuDialogFragment();
        menuDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        menuDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Resources resources = getResources();
        final List<String> appMenuItemsList = Lists.newArrayList(resources.getStringArray(R.array.app_menu_items));

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.app_menu_item_layout, appMenuItemsList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if (convertView == null)
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_menu_item_layout, parent, false);

                final TextView appMenuItemText = convertView.findViewById(R.id.menuItemText);

                appMenuItemText.setText(this.getItem(position));

                return convertView;
            }

        };

        appMenuItems.setAdapter(arrayAdapter);
        appMenuItems.setOnItemClickListener(this);
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.app_menu_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.applicationComponent = getApplicationComponent();
        applicationComponent.inject(this);
    }

    /**
     * On Item Click Listener
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Timber.d("Position: %d - Id -> %d", position, id);

        switch (position) {

            case PROFILE_ITEM_POSITION:
                // Navigate to User Profile Screen
                navigator.navigateToUserProfile(getActivity());
                break;

            case SETTINGS_ITEM_POSITION:
                // Navigate to User Settings
                navigator.navigateToUserSettings(getActivity());
                break;

            case HOW_ITS_WORK_POSITION:
                //Show App Tutorial
                navigator.navigateToAppTutorial(getActivity());
                break;

            case CLOSE_SESSION_POSITION:
                // Close Session
                localSystemNotification.sendNotification(new LogoutEvent(preferencesRepositoryImpl.getPrefCurrentUserIdentity()));
                preferencesRepositoryImpl.setAuthToken(IPreferenceRepository.AUTH_TOKEN_DEFAULT_VALUE);
                preferencesRepositoryImpl.setPrefCurrentUserIdentity(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE);
                navigator.navigateToIntro(getActivity(), true);
                break;

            case TERMS_OF_SERVICE_POSITION:
                navigator.showLegalContentActivity(getActivity(), LegalContentActivity.LegalTypeEnum.TERMS_OF_SERVICE);
                break;

            case PRIVACY_POLICY_POSITION:
                navigator.showLegalContentActivity(getActivity(), LegalContentActivity.LegalTypeEnum.PRIVACY_POLICY);
                break;

        }

        final String item = (String)adapterView.getItemAtPosition(position);
        Timber.d("Item -> %s", item);
    }
}
