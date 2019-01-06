package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * User Settings Activity Presenter
 */
public final class UserSettingsActivityPresenter
        extends TiPresenter<IUserSettingsView> {

    @Inject
    public UserSettingsActivityPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IUserSettingsView view) {
        super.onAttachView(view);
    }
}
