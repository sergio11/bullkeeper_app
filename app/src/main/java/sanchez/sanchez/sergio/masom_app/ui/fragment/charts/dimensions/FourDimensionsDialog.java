package sanchez.sanchez.sergio.masom_app.ui.fragment.charts.dimensions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportDialogFragment;

/**
 * Four Dimensions Dialog
 */
public final class FourDimensionsDialog extends SupportDialogFragment {

    public static final String TAG = "FOUR_DIMENSIONS_DIALOG";

    private static final String DIMENSION_IDX_ARG = "DIMENSION_IDX_ARG";
    private static final String DIMENSION_VALUE_ARG = "DIMENSION_VALUE_ARG";
    private static final String TOTAL_VALUE_ARG = "TOTAL_VALUE_ARG";

    public static final int VIOLENCE = 0;
    public static final int DRUGS = 1;
    public static final int ADULT = 2;
    public static final int BULLYING = 3;

    private int dimensionIdx = 0;
    private int dimensionValue = 0;
    private int totalValue = 0;

    /**
     * Dimension Title
     */
    @BindView(R.id.dimensionTitle)
    protected TextView dimensionTitle;

    /**
     * Dimension IMg
     */
    @BindView(R.id.dimensionImg)
    protected ImageView dimensionImg;

    /**
     * Separator
     */
    @BindView(R.id.separator)
    protected View separator;

    /**
     * Close Dialog
     */
    @BindView(R.id.closeDialog)
    protected Button closeDialog;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, int dimensionIdx, int value, int total) {
        final FourDimensionsDialog fourDimensionsDialogFragment = new FourDimensionsDialog();
        final Bundle args = new Bundle();
        args.putInt(DIMENSION_IDX_ARG, dimensionIdx);
        args.putInt(DIMENSION_VALUE_ARG, value);
        args.putInt(TOTAL_VALUE_ARG, total);
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
        return R.layout.four_dimensions_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() { }

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

        final Bundle args = getArguments();

        if(args != null) {
            dimensionIdx = args.getInt(DIMENSION_IDX_ARG);
            dimensionValue = args.getInt(DIMENSION_VALUE_ARG);
            totalValue = args.getInt(TOTAL_VALUE_ARG);
        }



        switch (dimensionIdx) {

            case VIOLENCE:

                dimensionTitle.setText(R.string.four_dimension_violence);
                dimensionTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.violence_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.violence_color));
                dimensionImg.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.comment_detail_violence));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.violence_button_state));

                break;

            case DRUGS:

                dimensionTitle.setText(R.string.four_dimension_drugs);
                dimensionTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.drugs_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.drugs_color));
                dimensionImg.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.comment_detail_drugs));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.drugs_button_state));

                break;

            case ADULT:

                dimensionTitle.setText(R.string.four_dimension_adult);
                dimensionTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.sex_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.sex_color));
                dimensionImg.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.comment_detail_sex));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.sex_button_state));

                break;

            case BULLYING:

                dimensionTitle.setText(R.string.four_dimension_bullying);
                dimensionTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.bullying_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.bullying_color));
                dimensionImg.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.comment_detail_bullying));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.bullying_button_state));

                break;

        }




    }
}
