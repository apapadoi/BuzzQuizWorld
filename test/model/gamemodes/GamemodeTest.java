package model.gamemodes;

import model.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamemodeTest {

    Gamemode test=new Gamemode("test",60) {
        @Override
        public String toString() {
            return null;
        }

        @Override
        public String getDescription() {
            return super.getDescription();
        }

        @Override
        public void actionIfCorrectAnswer(Model model) {

        }

        @Override
        public int getAvailableTime() {
            return super.getAvailableTime();
        }

        @Override
        public boolean hasPreQuestionFormat() {
            return super.hasPreQuestionFormat();
        }

        @Override
        public void actionIfWrongAnswer(Model model) {
            super.actionIfWrongAnswer(model);
        }
    };
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDescription() {
        assertEquals("test",test.getDescription());
        System.out.println("Get description");
    }

    @Test
    void getAvailableTime() {
        assertEquals(60,test.getAvailableTime());
    }
}