package io.hamed.callrecorder;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rm.com.audiowave.AudioWaveView;
import rm.com.audiowave.OnProgressListener;

public class MediaPlayerWave extends MediaPlayer implements MediaPlayer.OnCompletionListener {

    private static final long DELAY_MILLIS = 200;

    private Handler myHandler = new Handler();
    private String filePath;
    private Context context;
    private AudioWaveView waveView;
    private IOnChangeListener listener;

    private long minutes;
    private long seconds;
    private double finalTime;
    private double finalSeconds;
    private double finalMinutes;

    public MediaPlayerWave(Context context, String filePath) {
        this.filePath = filePath;
        this.context = context;
    }

    public MediaPlayerWave(Context context) {
        this.filePath = filePath;
    }

    public void create() {
        try {
            setDataSource(context, Uri.parse(filePath));
            prepare();
            setFinalTime(getDuration());
            setFinalMinutes(TimeUnit.MILLISECONDS.toMinutes((long) finalTime));
            setFinalSeconds(TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)));
            setOnCompletionListener(this);
            myHandler.postDelayed(UpdateSongTime, DELAY_MILLIS);
            if (waveView != null) {
                byte[] data = IOUtil.readFile(filePath);
                setAudioWaveViewHandling(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void setAudioWaveViewHandling(byte[] data) {
        waveView.setRawData(data);
        waveView.setOnProgressListener(new OnProgressListener() {
            @Override
            public void onStartTracking(float progress) {

            }

            @Override
            public void onStopTracking(float progress) {
                int totalDuration = getDuration();
                double val = ((double) progress) / ((double) 100);
                int currentDuration = (int) (val * totalDuration);
                seekTo(currentDuration);
                if (!isPlaying())
                    start();
            }

            @Override
            public void onProgressChanged(float progress, boolean byUser) {

            }
        });
    }

    public void setWaveView(AudioWaveView waveView) {
        this.waveView = waveView;
    }

    public void setOnChangeListener(IOnChangeListener i) {
        this.listener = i;
    }

    private long getMinutes() {
        return minutes;
    }

    private void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public double getFinalTime() {
        return finalTime;
    }

    private void setFinalTime(double finalTime) {
        this.finalTime = finalTime;
    }

    public double getFinalSeconds() {
        return finalSeconds;
    }

    private void setFinalSeconds(double finalSeconds) {
        this.finalSeconds = finalSeconds;
    }

    public double getFinalMinutes() {
        return finalMinutes;
    }

    private void setFinalMinutes(double finalMinutes) {
        this.finalMinutes = finalMinutes;
    }

    @Override
    public void start() throws IllegalStateException {
        if (listener != null) {
            listener.onStartPlayer();
        }
        super.start();
    }

    @Override
    public void pause() throws IllegalStateException {
        if (listener != null) {
            listener.onPausePlayer();
        }
        super.pause();
    }

    @Override
    public void stop() throws IllegalStateException {
        if (listener != null) {
            listener.onStopPlayer();
        }
        super.stop();
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            if (isPlaying()) {
                int currentPosition = getCurrentPosition();
                int totalDuration = 100;
                double val = ((double) currentPosition) / ((double) getDuration());
                int currentProgress = (int) (val * totalDuration);
                double startTime = getCurrentPosition();
                setMinutes(TimeUnit.MILLISECONDS.toMinutes((long) startTime));
                setSeconds(TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)));

                if (waveView != null)
                    waveView.setProgress(currentProgress);

                if (listener != null)
                    listener.onUpdatePlayer(getMinutes(), getSeconds());

                Log.i(Constants.TAG, "run: " + currentPosition);
            }
            myHandler.postDelayed(this, DELAY_MILLIS);
        }
    };

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (listener != null) {
            listener.onUpdatePlayer(0, 0);
            listener.onFinishPlayer();
        }
        if (waveView != null) {
            waveView.setProgress(0);
        }
        stop();
        try {
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        stop();
        myHandler.removeCallbacks(UpdateSongTime);
    }

    public interface IOnChangeListener {

        void onStartPlayer();

        void onPausePlayer();

        void onStopPlayer();

        void onUpdatePlayer(long minutes, long seconds);

        void onFinishPlayer();
    }

}
