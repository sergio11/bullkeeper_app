package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Support Dialog
 */
public abstract class SupportDialog extends DialogFragment {

    public final static String TITLE_ARG = "DIALOG_TITLE";


    protected String title;

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

}
