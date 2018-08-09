package sanchez.sanchez.sergio.masom_app.ui.support;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import net.grandcentrix.thirtyinch.TiFragment;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.dialog.NoticeDialogFragment;

/**
 * Support Fragment
 */
public abstract class SupportFragment<P extends TiPresenter<V>, V extends TiView,
        H extends IBasicActivityHandler> extends TiFragment<P, V> implements  ISupportView {


    /**
     * Activity Handler
     */
    protected H activityHandler;

    /**
     * UnBinder
     */
    private Unbinder unbinder;

    @Nullable
    @BindView(R.id.appToolbarInclude)
    protected View appbarLayout;


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
    }

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getLayoutRes(), container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        if(appbarLayout != null) {

            AppBarLayoutIncluded appBarLayoutIncluded = new AppBarLayoutIncluded();
            ButterKnife.bind( appBarLayoutIncluded, appbarLayout );

            appBarLayoutIncluded.menuBars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activityHandler.showAppMenu();
                }
            });
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null)
            unbinder.unbind();
    }

    /**
     * Show Short Message
     * @param message
     */
    @Override
    public void showShortMessage(String message) {
        activityHandler.showShortMessage(message);
    }

    /**
     * Show Long Message
     * @param message
     */
    @Override
    public void showLongMessage(String message) {
        activityHandler.showLongMessage(message);
    }

    /**
     * Show Notice Dialog
     * @param title
     */
    @Override
    public void showNoticeDialog(String title) {
        activityHandler.showNoticeDialog(title);
    }

    /**
     * Show Notice Dialog
     * @param stringResId
     */
    @Override
    public void showNoticeDialog(Integer stringResId) {
        activityHandler.showNoticeDialog(stringResId);
    }

    /**
     * Show Progress Dialog
     * @param title
     */
    @Override
    public void showProgressDialog(String title) {
        activityHandler.showProgressDialog(title);
    }

    /**
     * Show Progress Dialog
     * @param stringResId
     */
    @Override
    public void showProgressDialog(Integer stringResId) {
        showProgressDialog(getString(stringResId));
    }

    /**
     * Hide Progress Dialog
     */
    @Override
    public void hideProgressDialog() {
        activityHandler.hideProgressDialog();
    }

    /**
     * Show Confirmation Dialog
     * @param title
     */
    @Override
    public void showConfirmationDialog(String title) {
        activityHandler.showConfirmationDialog(title);
    }

    /**
     * Show Confirmation Dialog
     * @param stringResId
     */
    @Override
    public void showConfirmationDialog(Integer stringResId) {
        showConfirmationDialog(getString(stringResId));
    }

    /**
     * Show Notice Dialog
     * @param title
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(String title, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        activityHandler.showNoticeDialog(title, noticeDialogListener);
    }

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(Integer stringResId, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        activityHandler.showNoticeDialog(stringResId, noticeDialogListener);
    }

    /**
     * Show Confirmation Dialog
     * @param title
     * @param confirmationDialogListener
     */
    @Override
    public void showConfirmationDialog(String title, ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener) {
        activityHandler.showConfirmationDialog(title, confirmationDialogListener);
    }

    /**
     * Show Confirmation Dialog
     * @param stringResId
     * @param confirmationDialogListener
     */
    @Override
    public void showConfirmationDialog(Integer stringResId, ConfirmationDialogFragment.ConfirmationDialogListener confirmationDialogListener) {
        activityHandler.showConfirmationDialog(stringResId, confirmationDialogListener);
    }

    /**
     * show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     */
    @Override
    public void showLongSimpleSnackbar(ViewGroup viewRoot, String description, String actionText, View.OnClickListener onClickListener) {
        activityHandler.showLongSimpleSnackbar(viewRoot, description, actionText, onClickListener);
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    /**
     * Get Layout Resource
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();


    /**
     * App Bar Layout Included
     */
    static class AppBarLayoutIncluded {
        @BindView( R.id.menuBars ) ImageButton menuBars;
    }

}
