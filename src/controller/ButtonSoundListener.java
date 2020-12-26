package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import resources.images.Constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Represents an action listener that handles the sound when a button is pressed. Singleton Design Pattern is used.
 * @author Tasos Papadopoulos
 * @version 26.12.2020
 */
public class ButtonSoundListener implements ActionListener {
    private static final ButtonSoundListener instance = new ButtonSoundListener();

    private ButtonSoundListener() { }

    @Override
    public void actionPerformed(ActionEvent e) {
        new MediaPlayer(new Media(new File(Constants.BUTTON_SOUND_URL).toURI().toString())).play();
    }

    public static ButtonSoundListener getInstance() { return instance; }
}
