package model;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resources.utilResources.Constants;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CustomMediaPlayerTest {
    @BeforeEach
    void setUp() {
        new JFXPanel();
    }

    @Test
    void play() {
        CustomMediaPlayer customMediaPlayer = new CustomMediaPlayer(new MediaPlayer(new Media(
                new File(Constants.MENU_SOUND_URL).toURI().toString()
        )));
        customMediaPlayer.play();
        assertTrue(customMediaPlayer.isPlaying());
    }

    @Test
    void stop() {
        CustomMediaPlayer customMediaPlayer = new CustomMediaPlayer(new MediaPlayer(new Media(
                new File(Constants.MENU_SOUND_URL).toURI().toString()
        )));
        customMediaPlayer.play();
        assertTrue(customMediaPlayer.isPlaying());
        customMediaPlayer.stop();
        assertFalse(customMediaPlayer.isPlaying());
    }

    @Test
    void isPlaying() {
        CustomMediaPlayer customMediaPlayer = new CustomMediaPlayer(new MediaPlayer(new Media(
                new File(Constants.MENU_SOUND_URL).toURI().toString()
        )));
        assertFalse(customMediaPlayer.isPlaying());
        customMediaPlayer.play();
        assertTrue(customMediaPlayer.isPlaying());
        customMediaPlayer.stop();
        assertFalse(customMediaPlayer.isPlaying());
    }


    @Test
    void setPlaying() {
        CustomMediaPlayer customMediaPlayer = new CustomMediaPlayer(new MediaPlayer(new Media(
                new File(Constants.MENU_SOUND_URL).toURI().toString()
        )));
        customMediaPlayer.setPlaying(true);
        assertTrue(customMediaPlayer.isPlaying());
        customMediaPlayer.setPlaying(false);
        assertFalse(customMediaPlayer.isPlaying());
    }
}