package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * My Kids Home Adapter
 */
public final class MyKidsStatusAdapter extends SupportRecyclerViewAdapter<SonEntity>{

    private OnMyKidsListener listener;
    private final Picasso picasso;

    private final static Integer MIN_KIDS_COUNT = 3;

    /**
     *
     * @param context
     * @param data
     */
    public MyKidsStatusAdapter(Context context, ArrayList<SonEntity> data, final Picasso picasso) {
        super(context, data, MIN_KIDS_COUNT);
        this.picasso = picasso;
    }


    /**
     * Set Listener
     * @param listener
     */
    public void setOnMyKidsListenerListener(OnMyKidsListener listener) {
        this.listener = listener;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.my_kids_status_item_layout, viewGroup, false);
        return new MyKidsViewHolder(view);
    }

    /**
     * On Create Item Default View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemDefaultViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.my_kids_status_item_default_layout, viewGroup, false);
        return new AddKidsDefaultViewHolder(view);
    }


    /**
     * My Kids View Holder
     */
    public class MyKidsViewHolder
            extends SupportItemViewHolder<SonEntity> {

        private CircleImageView childImage;
        private TextView childName, alertCount;


        MyKidsViewHolder(View itemView) {
            super(itemView);

            childImage = itemView.findViewById(R.id.childImage);
            childName = itemView.findViewById(R.id.childName);
            alertCount = itemView.findViewById(R.id.alertCount);
        }

        /**
         * On Bind
         * @param sonEntity
         */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void bind(final SonEntity sonEntity){
            super.bind(sonEntity);


            if(sonEntity.getProfileImage() != null &&
                    !sonEntity.getProfileImage().isEmpty())
                // Set Child Image
                picasso.load(sonEntity.getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(childImage);
            else
                childImage.setImageResource(R.drawable.kid_default_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onDetailActionClicked(getItemByAdapterPosition(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(listener != null) {

                        AlertLevelEnum alertLevelEnum = null;
                        int alertLevelValue = 0;

                        final AlertLevelEnum[] alertLevelEnumsPriority = new AlertLevelEnum[]{
                                AlertLevelEnum.DANGER, AlertLevelEnum.WARNING, AlertLevelEnum.SUCCESS,
                                AlertLevelEnum.INFO
                        };

                        for (final AlertLevelEnum alertLevel : alertLevelEnumsPriority) {
                            if (sonEntity.getAlertStatistics().containsKey(alertLevel)) {
                                alertLevelEnum = alertLevel;
                                alertLevelValue = sonEntity.getAlertStatistics().get(alertLevel);
                                break;
                            }
                        }

                        if(alertLevelEnum != null)
                            listener.onShowAlertsDetail(alertLevelEnum,
                                    String.valueOf(alertLevelValue), sonEntity.getIdentity());

                        return true;

                    } else {
                        return false;
                    }
                }
            });


            if(sonEntity.getAlertStatistics().containsKey(AlertLevelEnum.DANGER)) {
                childImage.setBorderColor(ContextCompat.getColor(context, R.color.redDanger));
                alertCount.setText(String.valueOf(sonEntity.getAlertStatistics()
                        .get(AlertLevelEnum.DANGER)));
                alertCount.setVisibility(View.VISIBLE);
                alertCount.setBackground(ContextCompat.getDrawable(context, R.drawable.background_alert_count_danger));
            } else if(sonEntity.getAlertStatistics().containsKey(AlertLevelEnum.WARNING)) {
                childImage.setBorderColor(ContextCompat.getColor(context, R.color.yellowWarning));
                alertCount.setText(String.valueOf(sonEntity.getAlertStatistics()
                        .get(AlertLevelEnum.WARNING)));
                alertCount.setVisibility(View.VISIBLE);
                alertCount.setBackground(ContextCompat.getDrawable(context, R.drawable.background_alert_count_warning));
            } else if(sonEntity.getAlertStatistics().containsKey(AlertLevelEnum.SUCCESS)) {
                childImage.setBorderColor(ContextCompat.getColor(context, R.color.greenSuccess));
                alertCount.setText(String.valueOf(sonEntity.getAlertStatistics()
                        .get(AlertLevelEnum.SUCCESS)));
                alertCount.setVisibility(View.VISIBLE);
                alertCount.setBackground(ContextCompat.getDrawable(context, R.drawable.background_alert_count_success));
            } else if(sonEntity.getAlertStatistics().containsKey(AlertLevelEnum.INFO)) {
                childImage.setBorderColor(ContextCompat.getColor(context, R.color.cyanBrilliant));
                alertCount.setText(String.valueOf(sonEntity.getAlertStatistics()
                        .get(AlertLevelEnum.INFO)));
                alertCount.setVisibility(View.VISIBLE);
                alertCount.setBackground(ContextCompat.getDrawable(context, R.drawable.background_alert_count_info));
            } else {
                childImage.setBorderColor(ContextCompat.getColor(context, R.color.cyanBrilliant));
                alertCount.setText("");
                alertCount.setVisibility(View.GONE);
            }

            childName.setText(sonEntity.getFullName());
        }
    }

    /**
     * On My Kids Listener
     */
    public interface OnMyKidsListener {

        /**
         * On Detail Action Clicked
         * @param sonEntity
         */
        void onDetailActionClicked(final SonEntity sonEntity);

        /**
         * On Default Item Clicked
         */
        void onDefaultItemClicked();

        /**
         * On Show Alerts Detail
         * @param alertLevelEnum
         * @param alertValue
         * @param childId
         */
        void onShowAlertsDetail(final AlertLevelEnum alertLevelEnum, final String alertValue,
                                final String childId);
    }

    /**
     * Add Kids Default View Holder
     */
    public class AddKidsDefaultViewHolder extends RecyclerView.ViewHolder {

        public AddKidsDefaultViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onDefaultItemClicked();
                }
            });
        }
    }
}
