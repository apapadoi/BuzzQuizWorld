package controller.requests;

import controller.Dispatcher;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CustomMediaPlayer;
import java.io.File;

/**
 * Represents a request for playing a sound.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class PlaySoundRequest extends Request{
    private final CustomMediaPlayer mediaPlayer;

    /**
     * Creates a {@code PlaySoundRequest} which will play the sound stored in {@code soundUrl} provided using
     * a {@code CustomMediaPlayer} object.
     * @param soundUrl the sound that will be played.
     */
    public PlaySoundRequest(String soundUrl) {
        mediaPlayer = new CustomMediaPlayer(
                new MediaPlayer(new Media(new File(soundUrl).toURI().toString())));
    }

    /**
     * Creates a {@code PlaySoundRequest} using the {@code CustomMediaPlayer} provided.
     * @param mediaPlayer the {@code CustomMediaPlayer} that will be used for playing a sound as
     * {@code CustomMediaPlayer}
     */
    public PlaySoundRequest(CustomMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        if(!model.hasMusic())
            return;

        model.setMediaPlayer(mediaPlayer);
        model.getMediaPlayer().play();
    }
}
