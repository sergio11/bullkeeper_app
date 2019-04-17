package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;

/**
 * Summary My Kids Result Adapter
 */
public final class SummaryMyKidsResultAdapter extends SupportRecyclerViewAdapter<SummaryMyKidResultEntity>{

    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     */
    public SummaryMyKidsResultAdapter(Context context, ArrayList<SummaryMyKidResultEntity> data, final Picasso picasso) {
        super(context, data);
        this.picasso = picasso;
        // enable header
        hasHeader = false;
        hasFooter = false;

    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.summary_my_kids_result_item_layout, viewGroup, false);
        return new SummaryMyKidsResultViewHolder(context, view);
    }

    /**
     * Summary My Kids Result View Holder
     */
    public final class SummaryMyKidsResultViewHolder extends
            SupportItemViewHolder<SummaryMyKidResultEntity> {

        private Context context;

        private ImageView kidImageImageView, facebookIconImageView,
                instagramIconImageView, youtubeIconImageView;
        private TextView childNameTextView, schoolNameTextView, terminalsTextView,
                currentLocationTextView, totalCommentsAnalyzedTextView, violentCommentsTextView,
                adultContentCommentsTextView, drugsCommentsTextView, bullyingCommentsTextView,
                commentsNegativeSentimentTextView, commentsPositiveSentimentTextView,
                commentsNeutralSentimentTextView;


        /**
         * Summary My Kids Result View Holder
         * @param context
         * @param itemView
         */
        public SummaryMyKidsResultViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            kidImageImageView = itemView.findViewById(R.id.kidImage);
            childNameTextView = itemView.findViewById(R.id.childName);
            schoolNameTextView = itemView.findViewById(R.id.schoolName);
            terminalsTextView = itemView.findViewById(R.id.terminalsTextView);
            currentLocationTextView = itemView.findViewById(R.id.currentLocation);
            totalCommentsAnalyzedTextView = itemView.findViewById(R.id.totalCommentsAnalyzed);
            violentCommentsTextView = itemView.findViewById(R.id.violentComments);
            adultContentCommentsTextView = itemView.findViewById(R.id.adultContentComments);
            drugsCommentsTextView = itemView.findViewById(R.id.drugsComments);
            bullyingCommentsTextView = itemView.findViewById(R.id.bullyingComments);
            commentsNegativeSentimentTextView = itemView.findViewById(R.id.commentsNegativeSentiment);
            commentsNeutralSentimentTextView = itemView.findViewById(R.id.commentsNeutralSentiment);
            commentsPositiveSentimentTextView = itemView.findViewById(R.id.commentsPositiveSentiment);
            facebookIconImageView = itemView.findViewById(R.id.facebookIcon);
            instagramIconImageView = itemView.findViewById(R.id.instagramIcon);
            youtubeIconImageView = itemView.findViewById(R.id.youtubeIcon);
        }

        /**
         * On Bind
         * @param summaryMyKidResultEntity
         */
        @Override
        public void bind(SummaryMyKidResultEntity summaryMyKidResultEntity) {
            super.bind(summaryMyKidResultEntity);


            // Kid Name
            childNameTextView.setText(summaryMyKidResultEntity.getFirstName()
                +  " " + summaryMyKidResultEntity.getLastName());

            // Set School Name
            schoolNameTextView.setText(summaryMyKidResultEntity.getSchool().getName());

            // Check Terminals linked
            if(summaryMyKidResultEntity.getTotalDevices() > 0) {
                terminalsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.has_terminals_linked),
                        summaryMyKidResultEntity.getTotalDevices()));
            } else {
                terminalsTextView.setText(R.string.not_have_any_linked_devices);
            }

            if(summaryMyKidResultEntity.getProfileImage() != null &&
                    !summaryMyKidResultEntity.getProfileImage().isEmpty())
                // Set Child Image
                picasso.load(summaryMyKidResultEntity.getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(kidImageImageView);
            else
                kidImageImageView.setImageResource(R.drawable.kid_default_image);


            @DrawableRes int facebookIconRes = R.drawable.facebook_warning;
            @DrawableRes int instagramIconRes = R.drawable.instagram_warning;
            @DrawableRes int youtubeIconRes = R.drawable.google_plus_yellow;

            if(summaryMyKidResultEntity.getSocialMediaEntityList() != null) {
                for(final SocialMediaEntity socialMediaEntity: summaryMyKidResultEntity.getSocialMediaEntityList()) {
                    switch (socialMediaEntity.getType()) {
                        case FACEBOOK:

                            facebookIconRes = socialMediaEntity.hasInvalidToken() ?
                                    R.drawable.facebook_danger : R.drawable.facebook_success;
                            break;

                        case INSTAGRAM:

                            instagramIconRes = socialMediaEntity.hasInvalidToken() ?
                                    R.drawable.instagram_danger : R.drawable.instagram_success;

                            break;

                        case YOUTUBE:

                            youtubeIconRes = socialMediaEntity.hasInvalidToken() ?
                                    R.drawable.google_plus_danger : R.drawable.google_plus_success;

                            break;
                    }

                }
            }

            facebookIconImageView.setImageResource(facebookIconRes);
            instagramIconImageView.setImageResource(instagramIconRes);
            youtubeIconImageView.setImageResource(youtubeIconRes);


            // Total Comments Analyzed
            if(summaryMyKidResultEntity.getTotalCommentsAnalyzed() > 0 ){
                totalCommentsAnalyzedTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_total_comments),
                        summaryMyKidResultEntity.getTotalCommentsAnalyzed()));
            } else {
                totalCommentsAnalyzedTextView.setText(R.string.summary_my_kids_results_no_comments_analyzed);
            }

            // Violent Comments
            if(summaryMyKidResultEntity.getTotalViolentComments() > 0) {

                final String violentPercentage = Math.round((float)summaryMyKidResultEntity.getTotalViolentComments()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                violentCommentsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_violence_comments_percentage),
                        violentPercentage));
            } else {
                violentCommentsTextView.setText(R.string.summary_my_kids_results_no_violence_comments);
            }

            // Total Comments Adult Content
            if(summaryMyKidResultEntity.getTotalCommentsAdultContent() > 0) {

                final String adultContentPercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsAdultContent()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                adultContentCommentsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_adult_content_comments_percentage),
                        adultContentPercentage));
            } else {
                adultContentCommentsTextView.setText(R.string.summary_my_kids_results_no_adult_content_comments);
            }

            // Total Comments Bullying
            if(summaryMyKidResultEntity.getTotalCommentsBullying() > 0) {

                final String bullyingPercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsBullying()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                bullyingCommentsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_bullying_comments_percentage),
                        bullyingPercentage));
            } else {
                bullyingCommentsTextView.setText(R.string.summary_my_kids_results_no_bullying_comments);
            }

            // Total Comments Drugs
            if(summaryMyKidResultEntity.getTotalCommentsDrugs() > 0) {

                final String drugsPercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsDrugs()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                drugsCommentsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_drugs_comments_percentage),
                        drugsPercentage));
            } else {
                drugsCommentsTextView.setText(R.string.summary_my_kids_results_no_drugs_comments);
            }


            // Negative Sentiment
            if(summaryMyKidResultEntity.getTotalCommentsNegativeSentiment() > 0) {
                final String negativePercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsNegativeSentiment()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                commentsNegativeSentimentTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_negative_comments_percentage),
                        negativePercentage));
            } else {
                commentsNegativeSentimentTextView.setText(R.string.summary_my_kids_results_no_negative_comments);
            }


            // Positive Sentiment
            if(summaryMyKidResultEntity.getTotalCommentsPositiveSentiment() > 0) {
                final String positivePercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsPositiveSentiment()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                commentsPositiveSentimentTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_positive_comments_percentage),
                        positivePercentage));
            } else {
                commentsPositiveSentimentTextView.setText(R.string.summary_my_kids_results_no_positive_comments);
            }

            // Neutral Sentiment
            if(summaryMyKidResultEntity.getTotalCommentsNeutralSentiment() > 0) {
                final String neutralPercentage = Math.round((float)summaryMyKidResultEntity.getTotalCommentsNeutralSentiment()
                        / summaryMyKidResultEntity.getTotalCommentsAnalyzed() * 100) + "%";

                commentsNeutralSentimentTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.summary_my_kids_results_neutral_comments_percentage),
                        neutralPercentage));
            } else {
                commentsNeutralSentimentTextView.setText(R.string.summary_my_kids_results_no_neutral_comments);
            }

        }

    }

}
