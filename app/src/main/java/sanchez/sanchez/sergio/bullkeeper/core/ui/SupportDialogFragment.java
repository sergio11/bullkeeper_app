package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;

/**
 * Support Dialog Fragment
 */
public abstract class SupportDialogFragment extends DialogFragment {

    public final static String TITLE_ARG = "DIALOG_TITLE";

    private Unbinder unbinder;

    protected String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            onPrepareArgs(getArguments());
        }
    }

    /**
     * On Prepare Args
     * @param args
     */
    protected void onPrepareArgs(final Bundle args) {
        title = args.getString(TITLE_ARG, getString(R.string.default_notice_dialog_title));
    }

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutRes(), container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * On Resume
     */
    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

    /**
     * Hide Keyboard
     */
    public void hideKeyboard() {
        if(getContext() != null && getView() != null) {
            final InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }

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
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return AndroidApplication.getInstance().getApplicationComponent();
    }

    /**
     * Get Layout Res
     * @return
     */
    protected abstract int getLayoutRes();


    /**
     * Initialize Injector
     */
    protected abstract void initializeInjector();


}
