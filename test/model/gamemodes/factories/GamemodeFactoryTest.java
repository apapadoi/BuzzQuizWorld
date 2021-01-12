package model.gamemodes.factories;

import model.Model;
import model.gamemodes.Gamemodable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamemodeFactoryTest {
    private final Gamemodable customGamemode = new Gamemodable() {
        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public int getAvailableTime() {
            return 0;
        }

        @Override
        public void actionIfCorrectAnswer(Model model, int playerIndex) {

        }

        @Override
        public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {

        }

        @Override
        public boolean hasPreQuestionPhase() {
            return false;
        }

        @Override
        public void actionIfWrongAnswer(Model model, int playerIndex) {

        }

        @Override
        public boolean hasTimer() {
            return false;
        }

        @Override
        public int getMinBet() {
            return 0;
        }

        @Override
        public void setBetAmount(int amount, int playerIndex) {

        }

        @Override
        public void checkZeroScoreAndUpdate(Model model, int playerIndex) {

        }
    };
    private final GamemodeFactory gamemodeFactory = new GamemodeFactory() {
        @Override
        public Gamemodable getRandomGamemode() {
            return customGamemode;
        }

        @Override
        public void clearGamemodeData() {
        }
    };

    @Test
    void getRandomGamemode() {
        Gamemodable currentGamemode = gamemodeFactory.getRandomGamemode();
        assertEquals(customGamemode, currentGamemode);
    }
}