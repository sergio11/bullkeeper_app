package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;
import timber.log.Timber;

/**
 * App Help Dialog
 */
public final class AppHelpDialog extends SupportDialogFragment
    implements YouTubePlayer.OnInitializedListener{

    public static final String TAG = "EXPLANATION_DIALOG";

    public static final String EXPLANATION_TITLE_ARG = "EXPLANATION_TITLE_ARG";
    public static final String EXPLANATION_CUE_VIDEO_ARG = "EXPLANATION_CUE_VIDEO_ARG";

    @BindView(R.id.explanationTitle)
    protected TextView explanationTitleTextView;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    //youtube player to play video when new video selected
    private YouTubePlayer youTubePlayer;

    private String explanationTitle;
    private String cueVideo;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final String title, final String cueVideo) {
        final AppHelpDialog menuDialogFragment = new AppHelpDialog();
        menuDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);

        final Bundle args = new Bundle();
        args.putString(EXPLANATION_TITLE_ARG, title);
        args.putString(EXPLANATION_CUE_VIDEO_ARG, cueVideo);

        menuDialogFragment.setArguments(args);
        menuDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }



    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.explanation_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() { }


    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if(args == null || !args.containsKey(EXPLANATION_TITLE_ARG) ||
                !args.containsKey(EXPLANATION_CUE_VIDEO_ARG)) {
            throw new IllegalArgumentException("You must specify the title and video");
        }

        explanationTitle = args.getString(EXPLANATION_TITLE_ARG);
        cueVideo = args.getString(EXPLANATION_CUE_VIDEO_ARG);

        explanationTitleTextView.setText(explanationTitle);

        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.youtubePlayerContainer, youTubePlayerFragment)
                .commit();

        youTubePlayerFragment.initialize(getString(R.string.youtube_client_key), this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(youTubePlayer != null)
            youTubePlayer.release();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        if (!wasRestored) {
                youTubePlayer = youTubePlayer;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                youTubePlayer.cueVideo(cueVideo);
            }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Timber.e("Youtube Player View initialization failed");
    }
}
