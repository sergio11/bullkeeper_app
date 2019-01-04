package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.KidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.IKidRequestDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Kid Request Activity Fragment
 */
public class KidRequestDetailActivityMvpFragment extends SupportMvpFragment<KidRequestDetailFragmentPresenter,
        IKidRequestDetailView, IKidRequestDetailActivityHandler, KidRequestComponent>
        implements IKidRequestDetailView {

    /**
     * Args
     */
    public static String CHILD_ID_ARG = "KID_ID_ARG";
    public static String ID_ARG = "KID_ID_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Dependencies
     * ===============
     */


    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * State
     * =============
     */

    /**
     * Child ID
     */
    @State
    protected String childId;

    /**
     * ID
     */
    @State
    protected String id;


    public KidRequestDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param kid
     * @param id
     */
    public static KidRequestDetailActivityMvpFragment newInstance(final String kid, final String id) {
        final KidRequestDetailActivityMvpFragment kidRequestDetailActivityFragment =
                new KidRequestDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(CHILD_ID_ARG, kid);
        args.putString(ID_ARG, id);
        kidRequestDetailActivityFragment.setArguments(args);
        return kidRequestDetailActivityFragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(CHILD_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(CHILD_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        childId = getArgs().getString(CHILD_ID_ARG);

        // Get Id
        if(!getArgs().containsKey(ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(ID_ARG)))
            throw new IllegalArgumentException("You must provide a id");

        id = getArgs().getString(ID_ARG);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_request_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(KidRequestComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidRequestDetailFragmentPresenter providePresenter() {
        return component.kidRequestDetailFragmentPresenter();
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Kid Request Loaded
     * @param kidRequestEntity
     */
    @Override
    public void onKidRequestLoaded(final KidRequestEntity kidRequestEntity) {
        Preconditions.checkNotNull(kidRequestEntity, "Kid Request can not be null");
    }

    /**
     * On Kid Request Deleted
     */
    @Override
    public void onKidRequestDeleted() {
        Timber.d("On Kid Request Deleted");
    }

    /**
     * On Kid Request Not Found
     */
    @Override
    public void onKidRequestNotFound() {
        activityHandler.showNoticeDialog(R.string.kid_request_not_found, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }
}
