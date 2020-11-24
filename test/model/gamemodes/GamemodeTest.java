package model.gamemodes;

import model.Model;
import model.questions.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.cli.Cli;

import static org.junit.jupiter.api.Assertions.*;

class GamemodeTest {

    Gamemode test=new Gamemode("test",60,5) {
        @Override
        public String toString() {
            return null;
        }

        @Override
        public int getSkipsAvailable() {
            return super.getSkipsAvailable();
        }

        @Override
        public String getDescription() {
            return super.getDescription();
        }

        @Override
        public void decreaseSkips() {
            super.decreaseSkips();
        }

        @Override
        public void actionIfCorrectAnswer(Model model, int secondsTookToAnswer) {

        }

        @Override
        public int getAvailableTime() {
            return super.getAvailableTime();
        }

        @Override
        public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {
            super.showQuestionFormat(model, view, currentQuestion, roundId);
        }

        @Override
        public void actionsPreQuestionsPhase(Model model, Cli view, Question currentQuestion) {
            super.actionsPreQuestionsPhase(model, view, currentQuestion);
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
    void getSkipsAvailable() {
        assertEquals(5, test.getSkipsAvailable());
        System.out.println("Get skips available");
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