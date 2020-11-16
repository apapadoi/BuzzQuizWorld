package model.gamemodes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the OnePlayerGamemodes enum.
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
class OnePlayerGamemodesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAvailableGamemodes() {
        for(String currentGamemode : OnePlayerGamemodes.getAvailableGamemodes()) {
            System.out.println(currentGamemode);
        }
    }
}