package view.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.gui.utilResources.Constants;
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
    private final MediaPlayer mediaPlayer =
            new MediaPlayer(new Media(new File(Constants.BUTTON_SOUND_URL).toURI().toString()));

    /**
     * Default constructor
     */
    private ButtonSoundListener() { }

    /**
     * Invoked when a button associated with this type of action listener is pressed. Plays the button sound using a
     * {@code MediaPlayer} object.
     * @param e the action event associated with the action performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mediaPlayer.play();
    }

    /**
     * Returns the unique {@code ButtonSoundListener} instance.
     * @return the unique button sound listener as {@code ButtonSoundListener}
     */
    public static ButtonSoundListener getInstance() { return instance; }
}
