package controller.requests;

import controller.Dispatcher;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CustomMediaPlayer;
import model.player.Player;

import java.io.File;

public class PlaySoundRequest extends Request{
    private final CustomMediaPlayer mediaPlayer;

    public PlaySoundRequest(String soundUrl) {
        mediaPlayer = new CustomMediaPlayer(
                new MediaPlayer(new Media(new File(soundUrl).toURI().toString())));
    }

    public PlaySoundRequest(CustomMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        if(!model.hasMusic())
            return;

        model.setMediaPlayer(mediaPlayer);
        model.getMediaPlayer().play();
    }
}
