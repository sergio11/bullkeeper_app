package sanchez.sanchez.sergio.bullkeeper.core.sounds.impl;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import timber.log.Timber;

/**
 * Sound Manager Impl
 */
public final class SoundManagerImpl implements ISoundManager {

    private final static int MAX_STREAMS = 2;
    private final static float DEFAULT_SOUND = 1.0f;

    private final Context context;

    /**
     * Sound Pool
     */
    private final SoundPool soundPool;

    /**
     * Sound Map
     */
    private final Map<Integer, Integer> soundMap;

    /**
     *
     * @param context
     */
    public SoundManagerImpl(Context context) {
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            final AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib)
                    .setMaxStreams(MAX_STREAMS).build();
        } else {

            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }


        soundMap = new HashMap<>();
        soundMap.put(DIALOG_CONFIRM_SOUND, soundPool.load(context, DIALOG_CONFIRM_SOUND, 1));
        soundMap.put(DIALOG_ERROR_SOUND, soundPool.load(context, DIALOG_ERROR_SOUND, 1));
        soundMap.put(DIALOG_SUCCESS_SOUND, soundPool.load(context, DIALOG_SUCCESS_SOUND, 1));
        soundMap.put(SEND_MESSAGE_SUCCESS, soundPool.load(context, SEND_MESSAGE_SUCCESS, 1));
    }

    /**
     * Play Sound
     * @param sound
     * @return
     */
    @Override
    public int playSound(int sound) {
        return playSound(sound, false);
    }

    /**
     * Play Sound
     * @param sound
     * @param loop
     * @return
     */
    @Override
    public int playSound(int sound, boolean loop) {
        int streamId = -1;
        if(!soundMap.isEmpty() && soundMap.containsKey(sound)) {
            Timber.d("SOUND: Play Sound");
            streamId = soundPool.play(soundMap.get(sound), DEFAULT_SOUND, DEFAULT_SOUND,
                    1, loop ? -1 : 0, 1f);
        }
        return streamId;
    }

    /**
     * Stop Sound
     * @param streamId
     * @return
     */
    @Override
    public void stopSound(int streamId) {
        if(streamId != -1)
            soundPool.stop(streamId);
    }
}
