package controller.requests;

import controller.Dispatcher;

public class SetSoundSettingRequest extends Request{
    private final boolean hasMusic;

    public SetSoundSettingRequest(boolean hasMusic) {
        this.hasMusic = hasMusic;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        model.setHasMusic(hasMusic);
        if(!hasMusic)
            model.getMediaPlayer().stop();
        else
            model.getMediaPlayer().play();
    }
}
