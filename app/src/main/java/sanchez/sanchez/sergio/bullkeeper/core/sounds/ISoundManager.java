package sanchez.sanchez.sergio.bullkeeper.core.sounds;

import android.support.annotation.RawRes;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Sound Manager
 */
public interface ISoundManager {

    /**
     * Sounds
     */
    @RawRes int DIALOG_CONFIRM_SOUND = R.raw.dialog_confirm_sound;
    @RawRes int DIALOG_ERROR_SOUND = R.raw.dialog_error_sound;
    @RawRes int DIALOG_SUCCESS_SOUND = R.raw.dialog_success_sound;
    @RawRes int SEND_MESSAGE_SUCCESS = R.raw.send_message;

    /**
     * Play Sound
     */
    int playSound(@RawRes int sound);

    /**
     * Play Sound
     */
    int playSound(@RawRes int sound, boolean loop);

    /**
     * Stop Sound
     */
    void stopSound(int streamId);
}
