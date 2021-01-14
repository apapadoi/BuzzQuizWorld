package controller.requests;

import controller.FrontController;
import model.Model;
import model.gamemodes.Gamemodable;
import model.gamemodes.HighStakes;
import model.gamemodes.factories.GamemodeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetGamemodeFactoryRequestTest {

    @Test
    void execute() {
        Model.getInstance().clearData();
        GamemodeFactory gamemodeFactory = new GamemodeFactory() {
            @Override
            public Gamemodable getRandomGamemode() {
                return new HighStakes();
            }

            @Override
            public void clearGamemodeData() {

            }
        };

        FrontController.getInstance().dispatchRequest(new SetGamemodeFactoryRequest(gamemodeFactory));
        assertEquals(gamemodeFactory, Model.getInstance().getGamemodeFactory());
    }
}