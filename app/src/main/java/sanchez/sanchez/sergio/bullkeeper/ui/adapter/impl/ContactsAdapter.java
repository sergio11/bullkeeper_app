package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.ContactEntity;

/**
 * Contacts Adapter
 */
public final class ContactsAdapter extends SupportRecyclerViewAdapter<ContactEntity> {

    /**
     * @param context
     * @param data
     */
    public ContactsAdapter(final Context context, final ArrayList<ContactEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.contact_item_layout, viewGroup, false);
        return new ContactDetailViewHolder(view);
    }

    /**
     * Contact Detail View Holder
     */
    public class ContactDetailViewHolder
            extends SupportItemSwipedViewHolder<ContactEntity>{

        // Call Detail
        private ImageView contactPhotoImageView;
        private ImageView phoneNumberBlockedImageView;
        private TextView contactNameTextView, contactPhoneNumberTextView;


        /**
         * @param itemView
         */
        ContactDetailViewHolder(final View itemView) {
            super(itemView);
            contactPhotoImageView = itemView.findViewById(R.id.contactPhoto);
            phoneNumberBlockedImageView = itemView.findViewById(R.id.phoneNumberBlocked);
            contactNameTextView = itemView.findViewById(R.id.contactName);
            contactPhoneNumberTextView = itemView.findViewById(R.id.contactPhoneNumber);
        }

        /**
         * On Bind
         * @param contactEntity
         */
        @Override
        public void bind(final ContactEntity contactEntity) {
            super.bind(contactEntity);

            // Set Contact Photo
            if(contactEntity.getPhotoEncodedString() != null &&
                    !contactEntity.getPhotoEncodedString().isEmpty()) {
                byte[] decodedString = Base64
                        .decode(contactEntity.getPhotoEncodedString(),
                        Base64.DEFAULT);
                final Bitmap decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                contactPhotoImageView.setImageBitmap(decodedByte);
            } else {
                contactPhotoImageView.setImageResource(R.drawable.user_contact_default);
            }

            // Set Contact Name
            if(hasHighlightText())
                contactNameTextView.setText(getSpannableString(contactEntity.getName()));
            else
                contactNameTextView.setText(contactEntity.getName());

            // Set Phone Number
            contactPhoneNumberTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.contact_phonenumber), contactEntity.getPhoneNumber()));

            phoneNumberBlockedImageView.setImageResource(
                    !contactEntity.isBlocked() ? R.drawable.success_icon_solid :
                            R.drawable.close_circle_solid);
        }

    }
}
