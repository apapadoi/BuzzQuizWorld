package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.gamemodes.factories.GamemodeFactory;

public class SetGamemodeFactoryRequest extends Request{
    private final GamemodeFactory gamemodeFactory;

    public SetGamemodeFactoryRequest(GamemodeFactory gamemodeFactory) {
        this.gamemodeFactory = gamemodeFactory;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model.getInstance().setGamemodeFactory(gamemodeFactory);
    }
}
