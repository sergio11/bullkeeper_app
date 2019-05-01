package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;

/**
 * Place Suggestions Adapter
 */
public final class PlaceSuggestionsAdapter extends SupportRecyclerViewAdapter<PlaceSuggestionEntity>{

    /**
     *
     * @param context
     * @param data
     */
    public PlaceSuggestionsAdapter(Context context, ArrayList<PlaceSuggestionEntity> data) {
        super(context, data);
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
        View view = inflater.inflate(R.layout.place_suggestion_item_layout, viewGroup, false);
        return new PlaceViewHolder(view);
    }



    /**
     * Place View Holder
     */
    public final class PlaceViewHolder extends
            SupportItemSwipedViewHolder<PlaceSuggestionEntity> {

        private TextView primaryText, secondaryText;

        /**
         * Place View Holder
         * @param itemView
         */
        public PlaceViewHolder(final View itemView) {
            super(itemView);
            this.primaryText = itemView.findViewById(R.id.primaryText);
            this.secondaryText = itemView.findViewById(R.id.secondaryText);
        }

        /**
         * On Bind
         * @param place
         */
        @Override
        public void bind(final PlaceSuggestionEntity place) {
            super.bind(place);

            if(hasHighlightText())
                primaryText.setText(getSpannableString(place.getTitle()));
            else
                primaryText.setText(place.getHighlightedTitle());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                secondaryText.setText(Html.fromHtml(place.getVicinity(),
                        Html.FROM_HTML_MODE_COMPACT));
            } else {
                secondaryText.setText(Html.fromHtml(place.getVicinity()));
            }
        }

    }

}
