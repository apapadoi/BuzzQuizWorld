package controller.requests;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CustomMediaPlayer;
import model.Model;
import org.junit.jupiter.api.Test;
import view.gui.utilResources.Constants;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StopSoundRequestTest {
    @Test
    void execute() {
        new JFXPanel();
        CustomMediaPlayer customMediaPlayer = new CustomMediaPlayer(
                new MediaPlayer(new Media(new File(Constants.MENU_SOUND_URL).toURI().toString())));
        FrontController.getInstance().dispatchRequest(new PlaySoundRequest(customMediaPlayer));
        assertTrue(Model.getInstance().getMediaPlayer().isPlaying());
        FrontController.getInstance().dispatchRequest(new StopSoundRequest());
        assertFalse(Model.getInstance().getMediaPlayer().isPlaying());
    }
}