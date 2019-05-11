package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions;

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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;

/**
 * Four Dimensions Dialog
 */
public final class FourDimensionsDialog extends SupportDialogFragment {

    public static final String TAG = "FOUR_DIMENSIONS_DIALOG";

    private static final String DIMENSION_IDX_ARG = "DIMENSION_IDX_ARG";
    private static final String DIMENSION_VALUE_ARG = "DIMENSION_VALUE_ARG";
    private static final String TOTAL_VALUE_ARG = "TOTAL_VALUE_ARG";
    private static final String KID_IDENTITY_VALUE_ARG = "KID_IDENTITY_VALUE_ARG";

    /**
     * Dimensions Index
     */
    public static final int VIOLENCE = 0, DRUGS = 1, ADULT = 2, BULLYING = 3;

    /**
     * Dimension Idx
     */
    private int dimensionIdx = 0;

    /**
     * Dimension Value
     */
    private String dimensionValue;

    /**
     * Kid Identity Value
     */
    private String kidIdentityValue;

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
     * Content Detail Text View
     */
    @BindView(R.id.contentDetailText)
    protected TextView contentDetailTextView;

    /**
     * Show Comments Extracted Text View
     */
    @BindView(R.id.showCommentsExtractedTextView)
    protected TextView showCommentsExtractedTextView;

    /**
     * Show Comments Extracted Image View
     */
    @BindView(R.id.showCommentsExtractedImageView)
    protected ImageView showCommentsExtractedImageView;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, int dimensionIdx, final String dimensionValue,
                            final String kidIdentity) {
        final FourDimensionsDialog fourDimensionsDialogFragment = new FourDimensionsDialog();
        final Bundle args = new Bundle();
        args.putInt(DIMENSION_IDX_ARG, dimensionIdx);
        args.putString(DIMENSION_VALUE_ARG, dimensionValue);
        args.putString(KID_IDENTITY_VALUE_ARG, kidIdentity);
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
    protected void initializeInjector() { getApplicationComponent().inject(this); }

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

        if(args == null || !args.containsKey(DIMENSION_IDX_ARG) ||
                !args.containsKey(DIMENSION_VALUE_ARG))
            throw new IllegalArgumentException("You must provide Dimension Idx and Dimension Value");

        dimensionIdx = args.getInt(DIMENSION_IDX_ARG);
        dimensionValue = args.getString(DIMENSION_VALUE_ARG);
        // Kid Identity Value
        kidIdentityValue = args.getString(KID_IDENTITY_VALUE_ARG);

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
                contentDetailTextView.setText(String.format(getString(R.string.four_dimension_violence_content_text),
                        dimensionValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.violence_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.violence_arrow_circle_right_solid);

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
                contentDetailTextView.setText(String.format(getString(R.string.four_dimension_drugs_content_text),
                        dimensionValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.drugs_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.drugs_arrow_circle_right_solid);

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
                contentDetailTextView.setText(String.format(getString(R.string.four_dimension_adult_content_text),
                        dimensionValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.sex_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.sex_arrow_circle_right_solid);

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
                contentDetailTextView.setText(String.format(getString(R.string.four_dimension_bullying_content_text),
                        dimensionValue));
                showCommentsExtractedTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.bullying_color));
                showCommentsExtractedImageView.setImageResource(R.drawable.bullying_arrow_circle_right_solid);

                break;

        }
    }

    /**
     * On Show Comments Extracted
     */
    @OnClick(R.id.showCommentsExtracted)
    protected void onShowCommentsExtracted(){

        final DimensionCategoryEnum dimensionCategoryEnum;
        switch (dimensionIdx) {
            case VIOLENCE:
                dimensionCategoryEnum = DimensionCategoryEnum.VIOLENCE;
                break;
            case DRUGS:
                dimensionCategoryEnum = DimensionCategoryEnum.DRUGS;
                break;
            case ADULT:
                dimensionCategoryEnum = DimensionCategoryEnum.ADULT;
                break;
            default:
                dimensionCategoryEnum = DimensionCategoryEnum.BULLYING;
        }
        // Show Comments By Kid Identity and Social Media
        navigator.navigateToComments(getActivity(), kidIdentityValue, dimensionCategoryEnum);
    }
}
