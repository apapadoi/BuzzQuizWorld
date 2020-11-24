package model.gamemodes;

import model.Model;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.cli.Cli;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HighStakesTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        HighStakes test=new HighStakes();
        assertEquals("High Stakes",test.toString());
        System.out.println("Get High Stakes");
    }

    @Test
    void actionIfCorrectAnswer() {
        HighStakes test=new HighStakes();
        Model model=new Model();
        test.betAmount=1000;
        test.actionIfCorrectAnswer(model,3);
        assertEquals(1000,test.betAmount);
        System.out.println("Get 1000 points");
    }

    @Test
    void showQuestionFormat() {
        HighStakes test=new HighStakes();
        Model model=new Model();
        Cli cli=new Cli();
        List<String> answers=new ArrayList<>(
                List.of("10 days",
                        "1 month",
                        "1 year",
                        "1 day"));
        Question currentQuestion=new Question("What is the lifespan of a dragonfly?","1 day",answers, Difficulty.Medium, Category.Science);
        test.betAmount=250;
        test.showQuestionFormat(model,cli,currentQuestion,1);
    }

    @Test
    void actionsPreQuestionsPhase() {
    }

    @Test
    void hasPreQuestionFormat() {
    }

    @Test
    void actionIfWrongAnswer() {
    }
}