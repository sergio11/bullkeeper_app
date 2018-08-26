package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * My Kids Detail Presenter
 */
public final class MyKidsDetailPresenter extends TiPresenter<IMyKidsDetailView> {

    @Inject
    public MyKidsDetailPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IMyKidsDetailView view) {
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
