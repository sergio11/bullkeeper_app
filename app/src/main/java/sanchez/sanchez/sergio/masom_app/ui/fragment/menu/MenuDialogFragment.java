package sanchez.sanchez.sergio.masom_app.ui.fragment.menu;

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
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportDialogFragment;
import timber.log.Timber;

/**
 * Menu Dialog Fragment
 */
public final class MenuDialogFragment extends SupportDialogFragment
    implements AdapterView.OnItemClickListener {

    private final static int PROFILE_ITEM_POSITION = 0;
    private final static int SETTINGS_ITEM_POSITION = 1;
    private final static int HOW_ITS_WORK_POSITION = 2;

    public static final String TAG = "MENU_DIALOG_FRAGMENT";

    @BindView(R.id.appMenuItems)
    protected ListView appMenuItems;

    protected ApplicationComponent applicationComponent;

    @Inject
    protected INavigator navigator;

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
                break;

            case SETTINGS_ITEM_POSITION:
                // Navigate to User Settings
                navigator.navigateToUserSettings();
                break;

            case HOW_ITS_WORK_POSITION:
                //Show App Tutorial
                navigator.navigateToAppTutorial();
                break;

        }



        final String item = (String)adapterView.getItemAtPosition(position);
        Timber.d("Item -> %s", item);
    }
}
