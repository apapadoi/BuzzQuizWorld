package controller.requests;

import controller.FrontController;
import model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetMaximumPlayersRequestTest {

    @Test
    void execute() {
        Model.getInstance().clearData();

        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(3));
        assertEquals(3, Model.getInstance().getMaxPlayers());
    }
}