package sanchez.sanchez.sergio.masom_app.ui.support;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import sanchez.sanchez.sergio.masom_app.di.HasComponent;

/**
 * Support Fragment
 */
public abstract class SupportFragment extends Fragment {

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
