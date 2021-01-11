package controller.requests;

import controller.Dispatcher;

public class StopSoundRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        model.getMediaPlayer().stop();
    }
}
