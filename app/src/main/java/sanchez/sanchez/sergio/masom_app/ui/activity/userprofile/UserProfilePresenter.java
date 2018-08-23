package sanchez.sanchez.sergio.masom_app.ui.activity.userprofile;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

import sanchez.sanchez.sergio.masom_app.R;

/**
 * User Profile Presenter
 */
public final class UserProfilePresenter extends TiPresenter<IUserProfileView> {

    @Inject
    public UserProfilePresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IUserProfileView view) {
        super.onAttachView(view);
        view.showLongMessage("Hello World!!!");
    }

    /**
     * Update Profile
     * @param name
     * @param surname
     * @param birthday
     * @param email
     * @param tfno
     */
    public void updateProfile(final String name, String surname, String birthday,
                              final String email, final String tfno) {

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.signup_in_progress);

        if (isViewAttached() && getView() != null) {
            getView().hideProgressDialog();
            getView().showShortMessage("Profile Updated!!!");
        }

    }

}
