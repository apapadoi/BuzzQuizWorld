package controller.requests;

import controller.Dispatcher;

/**
 * Represents a request for stop playing a sound.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class StopSoundRequest extends Request{
    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.getMediaPlayer().stop();
    }
}
