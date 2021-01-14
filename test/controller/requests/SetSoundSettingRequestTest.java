package controller.requests;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.CustomMediaPlayer;
import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.factories.GamemodeFactory;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class SetSoundSettingRequestTest {

    @Test
    void execute() {
        new JFXPanel();
        Model.getInstance().setGamemodeFactory(new GamemodeFactory() {
            @Override
            public Gamemodable getRandomGamemode() {
                return null;
            }

            @Override
            public void clearGamemodeData() {

            }
        });
        Model.getInstance().setMediaPlayer(new CustomMediaPlayer(
                new MediaPlayer(new Media(new File("test/resources/sounds/test.mp3").toURI().toString()))));
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(false));
        assertFalse(Model.getInstance().hasMusic());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(true));
        assertTrue(Model.getInstance().hasMusic());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(false));
        assertFalse(Model.getInstance().hasMusic());
    }

    @Test
    void execute1() {
        new JFXPanel();
        Model.getInstance().setGamemodeFactory(new GamemodeFactory() {
            @Override
            public Gamemodable getRandomGamemode() {
                return null;
            }

            @Override
            public void clearGamemodeData() {

            }
        });
        Model.getInstance().setMediaPlayer(new CustomMediaPlayer(
                new MediaPlayer(new Media(new File("test/resources/sounds/test.mp3").toURI().toString()))));
        FrontController.getInstance().dispatchRequest(new ClearDataRequest());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(true));
        assertTrue(Model.getInstance().hasMusic());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(false));
        assertFalse(Model.getInstance().hasMusic());
        FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(true));
        assertTrue(Model.getInstance().hasMusic());
    }
}