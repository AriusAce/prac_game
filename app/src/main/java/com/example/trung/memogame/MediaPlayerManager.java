package com.example.trung.memogame;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerManager {
    private MediaPlayer mediaPlayer;
    private Context context;
    private int pausePoint = 0;

    public MediaPlayerManager(Context context) {
        this.context = context;
    }

    public void create(int id, boolean loop) {
        mediaPlayer = MediaPlayer.create(context, id);
        mediaPlayer.setLooping(loop);
    }

    public void play() {
        mediaPlayer.start();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
        pausePoint = mediaPlayer.getCurrentPosition();
    }

    public void resume() {
        mediaPlayer.seekTo(pausePoint);
        mediaPlayer.start();
    }
}
