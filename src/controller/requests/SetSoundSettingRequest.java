package controller.requests;

import controller.Dispatcher;

/**
 * Represents a request for setting whether or not the game has music.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class SetSoundSettingRequest extends Request{
    private final boolean hasMusic;

    /**
     * Create a {@code SetSoundSettingRequest} which will use the {@code hasMusic} option provided.
     * @param hasMusic the new option for whether or not the game has music.
     */
    public SetSoundSettingRequest(boolean hasMusic) {
        this.hasMusic = hasMusic;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setHasMusic(hasMusic);
        if(!hasMusic)
            model.getMediaPlayer().stop();
        else
            model.getMediaPlayer().play();
    }
}
