package sanchez.sanchez.sergio.bullkeeper.ui.fragment.apprules;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;

/**
 * App Rules Info Dialog
 */
public final class AppRulesInfoDialog extends SupportDialogFragment {

    public static final String TAG = "APP_RULES_INFO_DIALOG";

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity) {
        final AppRulesInfoDialog fourDimensionsDialogFragment = new AppRulesInfoDialog();
        final Bundle args = new Bundle();
        fourDimensionsDialogFragment.setArguments(args);
        fourDimensionsDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        fourDimensionsDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.app_rules_info_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {}

    /**
     * On Close Dialog
     */
    @OnClick(R.id.closeDialog)
    protected void onCloseDialog(){
        this.dismiss();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
