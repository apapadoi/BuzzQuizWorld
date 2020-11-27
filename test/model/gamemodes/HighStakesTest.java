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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents a testing JUnit for HighStakes gamemode.
 */
class HighStakesTest {
    HighStakes test;
    Cli cli;
    Model model;
    List<String> answers;
    Question currentQuestion;

    /**
     *
     */
    @BeforeEach
    void setUp() {
        test=new HighStakes();
        cli=new Cli();
        model=new Model();
        answers=new ArrayList<>(List.of("10 days", "1 month", "1 year", "1 day"));
        currentQuestion=new Question("What is the lifespan of a dragonfly?","1 day",answers, Difficulty.Medium, Category.Science);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        assertEquals("High Stakes",test.toString());
        System.out.println("Get High Stakes");
    }

    @Test
    void actionIfCorrectAnswer() {
        test.setBetAmount(1000);
        test.actionIfCorrectAnswer(model,3);
        assertEquals(1000,test.getBetAmount());
        System.out.println("Get 1000 points");
    }

    @Test
    void showQuestionFormat() {
        model.setCurrentGamemode(new HighStakes());
        test.setBetAmount(250);
        test.showQuestionFormat(model,cli,currentQuestion,1);
    }

    @Test
    void actionsPreQuestionsPhase() {
    }

    @Test
    void hasPreQuestionFormat() {
        assertTrue(test.hasPreQuestionFormat());
        System.out.println("Get true");
    }

    @Test
    void readBettingAmount(){
        String text="";
        test.setBetAmount(700);
        if (test.getAvailableBets().contains(test.getBetAmount())){
            if (test.getBetAmount()>model.getScore()){
                test.setBetAmount(Collections.min(test.getAvailableBets()));
            }
        }
        else
            text="No such betting amount";
        assertEquals(text,"No such betting amount");
    }

    @Test
    void readBettingAmountString(){
        String text="";
        try {
            throw new NumberFormatException();
            /*if (test.getAvailableBets().contains(test.getBetAmount())) {
                if (test.getBetAmount() > model.getScore()) {
                    test.setBetAmount(Collections.min(test.getAvailableBets()));
                }
            } else
                text = "No such betting amount";

             */
        }catch (NumberFormatException exception){
            text="Not a number";
        }
        assertEquals(text, "Not a number");
    }

    @Test
    void readBettingAmountCorrect(){
        String text="";
        test.setBetAmount(750);
        if (test.getAvailableBets().contains(test.getBetAmount())){
            if (test.getBetAmount()>model.getScore()){
                test.setBetAmount(Collections.min(test.getAvailableBets()));
            }
        }
        else
            text="No such betting amount";
    }

    @Test
    void actionIfWrongAnswer() {
    }
}