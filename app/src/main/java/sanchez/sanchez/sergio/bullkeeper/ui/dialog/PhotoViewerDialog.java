package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;

/**
 * Photo Viewer Dialog
 */
public class PhotoViewerDialog extends SupportDialogFragment {

    public static final String TAG = "PHOTO_VIEWER_DIALOG";

    private static final String PHOTO_URL_ARG = "PHOTO_URL_ARG";
    private static final String PHOTO_RES_ARG = "PHOTO_RES_ARG";

    private String photoUrl;

    @Inject
    protected Picasso picasso;

    /**
     * Image View
     */
    @BindView(R.id.imageView)
    protected ImageView imageView;

    /**
     * Photo Viewer Listener
     */
    private IPhotoViewerListener photoViewerListener;

    /**
     * Show Dialog
     * @param appCompatActivity
     * @param photoUrl
     */
    public static void show(final AppCompatActivity appCompatActivity,
                            final String photoUrl) {
        final PhotoViewerDialog photoViewerDialog = new PhotoViewerDialog();
        photoViewerDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);

        final Bundle args = new Bundle();
        args.putString(PHOTO_URL_ARG, photoUrl);
        photoViewerDialog.setArguments(args);

        photoViewerDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Show Dialog
     * @param appCompatActivity
     * @param photoRes
     */
    public static void show(final AppCompatActivity appCompatActivity,
                            final @DrawableRes int photoRes) {
        final PhotoViewerDialog photoViewerDialog = new PhotoViewerDialog();
        photoViewerDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);

        final Bundle args = new Bundle();
        args.putInt(PHOTO_RES_ARG, photoRes);
        photoViewerDialog.setArguments(args);

        photoViewerDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }


    /**
     * On Attach
     * @param context
     */
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            photoViewerListener = (IPhotoViewerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IPhotoViewerListener");
        }
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

        if (args != null) {

            if(args.containsKey(PHOTO_URL_ARG)) {

                photoUrl = args.getString(PHOTO_URL_ARG);

                if(photoUrl != null && !photoUrl.isEmpty()) {

                    if(photoUrl.matches("^(http|https|ftp)://.*$")) {

                        picasso.load(photoUrl)
                                .placeholder(R.drawable.user_default)
                                .error(R.drawable.user_default)
                                .noFade()
                                .into(imageView);

                    } else {

                        final File photoUrlFile = new File(photoUrl);

                        if(photoUrlFile.exists() && photoUrlFile.canRead()) {

                            picasso.load(photoUrlFile)
                                    .placeholder(R.drawable.user_default)
                                    .error(R.drawable.user_default)
                                    .noFade()
                                    .into(imageView);
                        }
                    }

                }

            } else if(args.containsKey(PHOTO_RES_ARG)) {

                @DrawableRes int photoRes = args.getInt(PHOTO_RES_ARG);
                imageView.setImageResource(photoRes);

            }

        }

    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.photo_viewer_dialog_layout;
    }

    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    /**
     * Change Photo
     */
    @OnClick(R.id.changePhoto)
    protected void onChangePhoto(){
        dismiss();
        if(photoViewerListener != null)
            photoViewerListener.onChangePhoto();
    }

    /**
     * Photo Viewer Listener
     */
    public interface IPhotoViewerListener {

        /**
         * On Change Photo
         */
        void onChangePhoto();

    }
}
