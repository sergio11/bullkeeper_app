package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.utils.PreferencesManager;

/**
 * Support Preference Fragment
 */
public abstract class SupportPreferenceFragment<H> extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener {

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
    protected PreferencesManager preferencesManager;

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


}
