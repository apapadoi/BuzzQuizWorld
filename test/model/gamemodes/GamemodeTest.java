package model.gamemodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamemodeTest {

    @Test
    void getDescription() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description") {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertEquals("Mock Gamemode Description", gamemode.getDescription());
    }

    @Test
    void testToString() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description") {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertEquals("Mock Gamemode toString()",gamemode.toString());
    }

    @Test
    void getAvailableTime() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description",15) {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertEquals(15, gamemode.getAvailableTime());
    }

    @Test
    void hasPreQuestionPhase() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description",15) {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertFalse(gamemode.hasPreQuestionPhase());
    }

    @Test
    void hasTimer() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description",15) {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertFalse(gamemode.hasTimer());
    }

    @Test
    void getMinBet() {
        Gamemode gamemode = new Gamemode("Mock Gamemode Description",15) {
            @Override
            public String toString() {
                return "Mock Gamemode toString()";
            }
        };
        assertEquals(0, gamemode.getMinBet());
    }
}