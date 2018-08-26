package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * User Profile Presenter
 */
public final class MyKidsProfilePresenter extends TiPresenter<IMyKidsProfileView> {

    @Inject
    public MyKidsProfilePresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IMyKidsProfileView view) {
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
