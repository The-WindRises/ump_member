package it.swiftelink.com.vcs_member.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import it.swiftelink.com.vcs_member.R;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/9/6 10:01
 */
public class MediaPlayerUtils {


    private static MediaPlayerUtils mediaPlayerUtils;

    private MediaPlayer mMediaPlayer;
    private CountDownTimer timer;

    //实例化SoundPool
    private MediaPlayerUtils() {
    }

    public static MediaPlayerUtils getInstance() {

        if (mediaPlayerUtils == null) {
            mediaPlayerUtils = new MediaPlayerUtils();

        }
        return mediaPlayerUtils;
    }


    public void init(Context context) {

        mMediaPlayer = MediaPlayer.create(context, R.raw.ling);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        if (timer == null) {
            timer = new CountDownTimer(6000 * 1000, 22000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
                        mMediaPlayer.start();
                    }

                }

                @Override
                public void onFinish() {
                    mMediaPlayer.stop();
                }
            };
        }

    }

    public void playSound() {

        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            timer.start();
        }


    }


    public void closeSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            timer.cancel();
            timer = null;
        }

    }

}
