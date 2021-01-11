package controller.requests;

import controller.Dispatcher;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class PlaySoundRequest extends Request{
    private final String soundUrl;

    public PlaySoundRequest(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        if(!model.hasMusic())
            return;

        model.setMediaPlayer(new MediaPlayer(new Media(new File(soundUrl).toURI().toString())));
        model.getMediaPlayer().play();
    }
}
