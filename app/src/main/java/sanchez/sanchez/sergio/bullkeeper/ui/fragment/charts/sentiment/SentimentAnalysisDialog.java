package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment;

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
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;

/**
 * Sentiment Analysis Dialog
 */
public final class SentimentAnalysisDialog extends SupportDialogFragment {

    public static final String TAG = "SENTIMENT_ANALYSIS_DIALOG";

    private static final String SENTIMENT_TYPE_ARG = "SENTIMENT_TYPE_ARG";
    private static final String SENTIMENT_VALUE_ARG = "SENTIMENT_VALUE_ARG";

    /**
     * State
     * ===============
     */


    /**
     * Sentiment Level Enum
     */
    private SentimentLevelEnum sentimentLevelEnum;

    /**
     * Sentiment Level Value
     */
    private String sentimentLevelValue;


    /**
     * Views
     * =================
     */

    /**
     * Sentiment Type Title Text View
     */
    @BindView(R.id.sentimentTypeTitle)
    protected TextView sentimentTypeTitleTextView;

    /**
     * Sentiment Type Image
     */
    @BindView(R.id.sentimentTypeImage)
    protected ImageView sentimentTypeImageView;

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
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final SentimentLevelEnum sentimentLevelEnum,
                            final String sentimentLevelValue) {
        final SentimentAnalysisDialog fourDimensionsDialogFragment = new SentimentAnalysisDialog();
        final Bundle args = new Bundle();
        args.putSerializable(SENTIMENT_TYPE_ARG, sentimentLevelEnum);
        args.putString(SENTIMENT_VALUE_ARG, sentimentLevelValue);
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
        return R.layout.sentiment_analysis_dialog;
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

        final Bundle args = getArguments();

        if(args == null || !args.containsKey(SENTIMENT_TYPE_ARG) ||
                !args.containsKey(SENTIMENT_VALUE_ARG))
            throw new IllegalArgumentException("You must provide Sentiment Type and Value");

        // Sentiment Type Enum
        sentimentLevelEnum = (SentimentLevelEnum) args.getSerializable(SENTIMENT_TYPE_ARG);
        // Sentiment Type Value
        sentimentLevelValue = args.getString(SENTIMENT_VALUE_ARG);

        switch (sentimentLevelEnum) {

            case POSITIVE:

                // Set Sentiment Image Type
                sentimentTypeImageView.setImageResource(R.drawable.positive_comments_solid);

                // Sentiment Title
                sentimentTypeTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.greenSuccess));
                sentimentTypeTitleTextView.setText(R.string.kid_results_positive_content);

                // Separator Color
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.greenSuccess));

                // Content Detail Text
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_sentiment_content_positive),
                        sentimentLevelValue));

                closeDialog.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.positive_results_button_state));

                break;

            case NEGATIVE:

                // Set Sentiment Image Type
                sentimentTypeImageView.setImageResource(R.drawable.negative_comments_solid);

                // Sentiment Title
                sentimentTypeTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.redDanger));
                sentimentTypeTitleTextView.setText(R.string.kid_results_negative_content);

                // Separator Color
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.redDanger));

                // Content Detail Text
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_sentiment_content_negative),
                        sentimentLevelValue));

                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.negative_results_button_state));

                break;

            case NEUTRO:

                // Set Sentiment Image Type
                sentimentTypeImageView.setImageResource(R.drawable.neutro_comments_solid);

                // Sentiment Title
                sentimentTypeTitleTextView.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.silver_color));
                sentimentTypeTitleTextView.setText(R.string.kid_results_neutral_content);

                // Separator Color
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.silver_color));

                // Content Detail Text
                contentDetailTextView.setText(String.format(getString(R.string.kid_results_sentiment_content_neutral),
                        sentimentLevelValue));

                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.neutral_results_button_state));

                break;
        }
    }
}
