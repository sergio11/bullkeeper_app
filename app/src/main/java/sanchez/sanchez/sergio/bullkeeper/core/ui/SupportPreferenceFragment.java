package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

/**
 * Support Preference Fragment
 */
public abstract class SupportPreferenceFragment<H> extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener, IDataManagement {

    /**
     * Activity Handler
     */
    protected H activityHandler;

    /**
     * UnBinder
     */
    private Unbinder unbinder;

    /**
     * Preferences Manager
     */
    @Inject
    protected IPreferenceRepository preferencesRepositoryImpl;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            activityHandler = (H) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IIntroActivityHandler");
        }
    }

    /**
     * On Create Preferences
     * @param savedInstanceState
     * @param rootKey
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(getPreferencesLayout());

    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeInjector();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    /**
     * On Destroy View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null)
            unbinder.unbind();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    /**
     * Get Preferences Layout
     * @return
     */
    @XmlRes
    protected abstract int getPreferencesLayout();


    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();

    /**
     * Config Switch Preference Compat Value
     * @param key
     * @param isEnabled
     */
    protected boolean configSwitchPreferenceCompatValue(final String key, final boolean isEnabled) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);
        switchPreferenceCompatPreference.setOnPreferenceChangeListener(this);
        switchPreferenceCompatPreference.setEnabled(isEnabled);
        return switchPreferenceCompatPreference.isChecked();
    }

    /**
     * Show Switch Preference Compat
     * @param key
     */
    protected void showSwitchPreferenceCompat(final String key) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);
        switchPreferenceCompatPreference.setVisible(true);
    }

    /**
     * Hide Switch Preference Compat
     * @param key
     */
    protected void hideSwitchPreferenceCompat(final String key) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);
        switchPreferenceCompatPreference.setVisible(false);
    }

    /**
     * Set Switch Preference Compat Checked
     * @param key
     * @param isChecked
     */
    protected void setSwitchPreferenceCompatChecked(final String key, boolean isChecked) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);
        switchPreferenceCompatPreference.setChecked(isChecked);

    }


    /**
     * Get Switch Preference Compat Value
     * @param key
     * @return
     */
    protected boolean getSwitchPrefenceCompatValue(final String key) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);
        switchPreferenceCompatPreference.setOnPreferenceChangeListener(this);
        return switchPreferenceCompatPreference.isChecked();
    }


    /**
     * Toogle Switch Preference Compat
     * @param key
     */
    protected void toggleSwitchPreferenceCompat(final String key, boolean isEnabled) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");

        final SwitchPreferenceCompat switchPreferenceCompatPreference = (SwitchPreferenceCompat) findPreference(key);

        if(isEnabled) {
            switchPreferenceCompatPreference.setChecked(true);
            switchPreferenceCompatPreference.setEnabled(false);
        } else {
            switchPreferenceCompatPreference.setEnabled(true);
        }
    }


    /**
     * Has List Preference this value
     * @param key
     * @param value
     * @return
     */
    protected boolean hasListPreferenceThisValue(final String key, final String value) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");
        Preconditions.checkNotNull(value, "value can not be null");
        Preconditions.checkState(!value.isEmpty(), "value can not be empty string");

        final ListPreference listPreference = (ListPreference) findPreference(key);
        return listPreference.getValue().equals(value);
    }

    /**
     * Switch Preference Compat Is It In This State
     * @param key
     * @param state
     * @return
     */
    protected boolean switchPreferenceCompatIsItInThisState(final String key, boolean state) {
        Preconditions.checkNotNull(key, "Key can not be null");
        Preconditions.checkState(!key.isEmpty(), "Key can not be empty string");

        final SwitchPreferenceCompat switchPreferenceCompat =
                (SwitchPreferenceCompat) findPreference(key);

        return state == switchPreferenceCompat.isChecked();

    }


}
