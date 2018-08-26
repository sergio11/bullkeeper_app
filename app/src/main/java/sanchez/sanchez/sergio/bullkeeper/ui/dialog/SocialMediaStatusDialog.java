package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

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
import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;

/**
 * Social Media Status Dialog
 */
public final class SocialMediaStatusDialog extends SupportDialogFragment {

    public static final String TAG = "SOCIAL_MEDIA_STATUS_DIALOG";

    private static final String SOCIAL_MEDIA_TYPE_ARG = "SOCIAL_MEDIA_TYPE_ARG";
    private static final String SOCIAL_MEDIA_STATUS_ARG = "SOCIAL_MEDIA_STATUS_ARG";

    private SocialMediaTypeEnum socialMediaEnum = SocialMediaTypeEnum.FACEBOOK;
    private SocialMediaStatusEnum socialMediaStatusEnum = SocialMediaStatusEnum.DISABLED;

    /**
     * Social Media Title
     */
    @BindView(R.id.socialMediaTitle)
    protected TextView socialMediaTitle;

    /**
     * Social Media Image
     */
    @BindView(R.id.socialMediaImage)
    protected ImageView socialMediaImage;

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
    public static void show(final AppCompatActivity appCompatActivity, final SocialMediaTypeEnum socialMediaTypeEnum,
                            final SocialMediaStatusEnum socialMediaStatusEnum) {
        final SocialMediaStatusDialog socialMediaStatusDialog = new SocialMediaStatusDialog();
        final Bundle args = new Bundle();
        args.putSerializable(SOCIAL_MEDIA_TYPE_ARG, socialMediaTypeEnum);
        args.putSerializable(SOCIAL_MEDIA_STATUS_ARG, socialMediaStatusEnum);
        socialMediaStatusDialog.setArguments(args);
        socialMediaStatusDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        socialMediaStatusDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.social_media_dialog_layout;
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
            this.socialMediaEnum = (SocialMediaTypeEnum)args.getSerializable(SOCIAL_MEDIA_TYPE_ARG);
            this.socialMediaStatusEnum = (SocialMediaStatusEnum)args.getSerializable(SOCIAL_MEDIA_STATUS_ARG);
        }

        switch (socialMediaEnum) {

            case FACEBOOK:

                socialMediaTitle.setText(R.string.facebook_brand_title);
                socialMediaTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.facebook_color));
                socialMediaImage.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.facebook_square_brands_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_facebook_button_state));

                break;

            case YOUTUBE:

                socialMediaTitle.setText(R.string.youtube_brand_title);
                socialMediaTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.youtube_color));
                socialMediaImage.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.youtube_brands_solid_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_youtube_button_state));

                break;

            case INSTAGRAM:

                socialMediaTitle.setText(R.string.instagram_brand_title);
                socialMediaTitle.setTextColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.instagram_color));
                socialMediaImage.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.instagram_brands_color));
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.social_media_instagram_button_state));

                break;


        }




    }
}
