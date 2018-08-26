package sanchez.sanchez.sergio.bullkeeper.ui.fragment.question;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.ApplicationComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;
import timber.log.Timber;

/**
 * Question App Dialog
 */
public final class QuestionAppDialog extends SupportDialogFragment
implements YouTubePlayer.OnInitializedListener{

    public static final String TAG = "QUESTION_APP_DIALOG";


    /**
     * Application Component
     */
    private ApplicationComponent applicationComponent;

    /**
     * Basic Activity Handler
     */
    private IBasicActivityHandler basicActivityHandler;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    //youtube player to play video when new video selected
    private YouTubePlayer youTubePlayer;


    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity) {
        final QuestionAppDialog menuDialogFragment = new QuestionAppDialog();
        menuDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        menuDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * On Attach
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            basicActivityHandler = (IBasicActivityHandler) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.question_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.applicationComponent = AndroidApplication.getInstance().getApplicationComponent();
        this.applicationComponent.inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                youTubePlayer.cueVideo(getString(R.string.youtube_video_cue));
            }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Timber.e("Youtube Player View initialization failed");
    }
}
