package sanchez.sanchez.sergio.bullkeeper.core.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.grandcentrix.thirtyinch.TiFragment;
import net.grandcentrix.thirtyinch.TiPresenter;
import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ActivityComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Support Fragment
 */
public abstract class SupportMvpFragment<P extends TiPresenter<V>, V extends ISupportView,
        H extends IBasicActivityHandler, C extends ActivityComponent> extends TiFragment<P, V> implements  ISupportView {

    /**
     * Component Class
     */
    protected Class<C> componentClass;

    /**
     * Component
     */
    protected C component;

    /**
     * Activity Handler
     */
    protected H activityHandler;

    /**
     * UnBinder
     */
    private Unbinder unbinder;

    /**
     * Optional App Bar Layout
     */
    @Nullable
    @BindView(R.id.appToolbarInclude)
    protected View appbarLayout;

    /**
     * Preferences Manager
     */
    @Inject
    protected IPreferenceRepository preferencesRepositoryImpl;

    /**
     * App Utils
     */
    @Inject
    protected IAppUtils appUtils;


    /**
     * On Attach
     * @param context
     */
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
     * On Create
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Create Component
        createComponent();
        // Initialize component
        initializeInjector(component);
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


    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        if(appbarLayout != null) {

            final SupportToolbarApp supportToolbarApp = new SupportToolbarApp(getToolbarType(), appbarLayout, getAppIconMode());
            supportToolbarApp.bind(activityHandler);
            activityHandler.setSupportToolbarApp(supportToolbarApp);
        }

    }

    /**
     * On Save Instance State
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    /**
     * On View State Restored
     * @param savedInstanceState
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
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
     * Show Short Message
     * @param message
     */
    @Override
    public void showShortMessage(String message) {
        activityHandler.showShortMessage(message);
    }

    /**
     * Show Short Message
     * @param messageResId
     */
    @Override
    public void showShortMessage(@StringRes int messageResId) {
        activityHandler.showShortMessage(messageResId);
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
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred);
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred);
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
     * Show Notice Dialog
     * @param title
     * @param isSuccess
     */
    @Override
    public void showNoticeDialog(String title, boolean isSuccess) {
        activityHandler.showNoticeDialog(title, isSuccess);
    }

    /**
     * Show Notice Dialog
     * @param stringResId
     * @param isSuccess
     */
    @Override
    public void showNoticeDialog(Integer stringResId, boolean isSuccess) {
        activityHandler.showNoticeDialog(stringResId, isSuccess);
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
     * show Long Simple Snackbar
     * @param viewRoot
     * @param description
     * @param actionText
     * @param onClickListener
     */
    @Override
    public void showLongSimpleSnackbar(ViewGroup viewRoot, String description, String actionText, View.OnClickListener onClickListener,
                                       final Snackbar.Callback snackbarCallback) {
        activityHandler.showLongSimpleSnackbar(viewRoot, description, actionText, onClickListener, snackbarCallback);
    }

    /**
     *
     * @param view
     * @param width
     * @param height
     */
    public void setDimensions(final View view, int width, int height) {
        activityHandler.setDimensions(view, width, height);
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
     * Create Component
     */
    private void createComponent(){

        this.componentClass = (Class<C>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[3];

        this.component = componentClass
                .cast(((HasComponent<C>) getActivity())
                        .getComponent());
    }


    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector(final C component);


    /**
     * Toolbar Type
     * @return
     */
    protected int getToolbarType(){
        return SupportToolbarApp.INFORMATIVE_TOOLBAR;
    }

    /**
     * Get App Icon Mode
     * @return
     */
    protected int getAppIconMode() { return SupportToolbarApp.ENABLE_GO_TO_HOME; }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return null;
    }
}
