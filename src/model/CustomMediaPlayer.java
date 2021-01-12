package model;

import javafx.scene.media.MediaPlayer;

public class CustomMediaPlayer {
    private final MediaPlayer mediaPlayer;
    private boolean isPlaying;

    public CustomMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.isPlaying = false;
    }

    public void play() {
        this.isPlaying = true;
        mediaPlayer.play();
    }

    public void stop() {
        this.isPlaying = false;
        mediaPlayer.stop();
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
