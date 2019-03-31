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
    @RawRes int SOS_ALARM_SOUND = R.raw.sos_alarm;
    @RawRes int PICK_ME_UP_SOUND = R.raw.pick_me_up_sound;

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
