package model;

import javafx.scene.media.MediaPlayer;

/**
 * This class represents a custom media player for playing music in the game and storing the state of the
 * {@code MediaPlayer} object.
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 */
public class CustomMediaPlayer {
    private final MediaPlayer mediaPlayer;
    private boolean isPlaying;

    /**
     * Create a {@code CustomMediaPlayer} using the provided {@code MediaPlayer}
     * @param mediaPlayer a {@code MediaPlayer}  that will be used from the {@code CustomMediaPlayer} to play sounds
     */
    public CustomMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.isPlaying = false;
    }

    /**
     * When invoked calls the corresponding method of {@code MediaPlayer} class and stores the state of the
     * {@code MediaPlayer} object.<b> No checking is done on the {@code MediaPlayer} object </b>
     */
    public void play() {
        this.isPlaying = true;
        mediaPlayer.play();
    }

    /**
     * When invoked calls the corresponding method of {@code MediaPlayer} class and stores the state of the
     * {@code MediaPlayer} object.<b> No checking is done on the {@code MediaPlayer} object </b>
     */
    public void stop() {
        this.isPlaying = false;
        mediaPlayer.stop();
    }

    /**
     * Returns whether or not the {@code MediaPlayer} object is playing a sound.
     * @return whether or not the {@code MediaPlayer} object is playing a sound as {@code boolean}
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }

    /**
     * Sets a <b>"visual"</b> state in the {@code MediaPlayer} object for unit testing purposes.
     * @param playing the new state as {@code boolean} for the {@code MediaPlayer} object
     */
    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
