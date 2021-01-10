package model.round;

import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.Gamemodable;
import model.gamemodes.factories.GamemodeFactory;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.ImagedQuestion;
import model.questions.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private static FileHandler fileHandler;
    private Gamemodable customGamemode = new Gamemodable() {
        @Override
        public String getDescription() {
            return "Mock Gamemode Description";
        }

        @Override
        public int getAvailableTime() {
            return 1500;
        }

        @Override
        public void actionIfCorrectAnswer(Model model, int playerIndex) {
            model.getPlayers().get(playerIndex).addScore(4500);
        }

        @Override
        public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {
            this.actionIfCorrectAnswer(model, playerIndex);
        }

        @Override
        public void actionPreQuestionsPhase(Model model) {

        }

        @Override
        public boolean hasPreQuestionPhase() {
            return true;
        }

        @Override
        public void actionIfWrongAnswer(Model model, int playerIndex) {
            model.getPlayers().get(playerIndex).addScore(-3000);
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

        @Override
        public String toString() {
            return "Mock Gamemode toString()";
        }
    };
    private final GamemodeFactory customGamemodeFactoryWithPreQuestionFormat = new GamemodeFactory() {
        @Override
        public Gamemodable getRandomGamemode() {
            return RoundTest.this.customGamemode;
        }
    };

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Model.getInstance().setGamemodeFactory(customGamemodeFactoryWithPreQuestionFormat);
        Model.getInstance().setNumOfRoundsChoice(1, fileHandler);
        Model.getInstance().setUsernames(new ArrayList<>(List.of("testUsername")));
        Model.getInstance().setMaxPlayers(1);
    }

    @Test
    void getQuestions1() {
        Question question1 = new Question("Which nut is used to make dynamite?","Peanuts",
                new ArrayList<>(List.of(
                        "Peanuts","Walnuts","Pine nuts","Almonds"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question2 = new Question("What was the first cold breakfast cereal invented in 1863?",
                "Granola",new ArrayList<>(List.of(
                "Granola","Corn flakes","Bran flakes","Cheerios"
        )), Difficulty.valueOf("Hard"), Category.valueOf("Food"));
        Question question3 = new Question("Who invented Coca-Cola?","John Pemberton",
                new ArrayList<>(List.of(
                        "Asa Griggs Candler","Charles Elmer Hires","John Matthews","John Pemberton"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question4 = new ImagedQuestion("Name the Hip Hop artist","Lex",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Easy"), Category.valueOf("HipHopArtists"), new ImageIcon("question_1_image.png"));
        Question question5 = new ImagedQuestion("Name the Hip Hop artist","Vlospa",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Medium"), Category.valueOf("HipHopArtists"), new ImageIcon("question_2_image.png"));

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3,question4,question5));
        Round round = Model.getInstance().getRound(0);
        List<Question> roundQuestions = round.getQuestions();

        int questionsContained = 0;
        for(Question hardCodedQuestion:hardCodedQuestions)
            if(roundQuestions.contains(hardCodedQuestion))
                questionsContained++;

        assertEquals(5, questionsContained);
    }

    @Test
    void getQuestions2() {
        Question question1 = new Question("Which nut is used to make dynamite?","Peanuts",
                new ArrayList<>(List.of(
                        "Peanuts","Walnuts","Pine nuts","Almonds"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question2 = new Question("What was the first cold breakfast cereal invented in 1863?",
                "Granola",new ArrayList<>(List.of(
                "Granola","Corn flakes","Bran flakes","Cheerios"
        )), Difficulty.valueOf("Hard"), Category.valueOf("Food"));
        Question question3 = new Question("Who invented Coca-Cola?","John Pemberton",
                new ArrayList<>(List.of(
                        "Asa Griggs Candler","Charles Elmer Hires","John Matthews","John Pemberton"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question4 = new ImagedQuestion("Name the Hip Hop artist","Lex",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Easy"), Category.valueOf("HipHopArtists"), new ImageIcon("question_1_image.png"));
        Question question5 = new ImagedQuestion("Name the Hip Hop artist","Vlospa",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Medium"), Category.valueOf("HipHopArtists"), new ImageIcon("question_2_image.png"));

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3,question4,question5));
        Round round = Model.getInstance().getRound(0);
        List<Question> roundQuestions = round.getQuestions();

        int questionsWithSameAnswers = 0;

        for(Question hardCodedQuestion:hardCodedQuestions) {
            boolean sameAnswers = false;
            for(Question fileHandlerQuestion:roundQuestions)
                if(fileHandlerQuestion.equals(hardCodedQuestion)) {
                    int countOfSameAnswers = 0;
                    for(String hardcodedAnswers:hardCodedQuestion.getAnswers()) {
                        if(fileHandlerQuestion.getAnswers().contains(hardcodedAnswers))
                            countOfSameAnswers++;
                    }
                    if(countOfSameAnswers==4)
                        sameAnswers = true;
                    break;
                }

            if(sameAnswers)
                questionsWithSameAnswers++;
        }
        assertEquals(5, questionsWithSameAnswers);
        assertEquals(5, roundQuestions.size());
    }

    @Test
    void getGamemodeDescription() {
        Round round = Model.getInstance().getRound(0);
        assertEquals("Mock Gamemode Description", round.getGamemodeDescription());
    }

    @Test
    void getGamemodeString() {
        Round round = Model.getInstance().getRound(0);
        assertEquals("Mock Gamemode toString()", round.getGamemodeString());
    }

    @Test
    void getAvailableTime() {
        Round round = Model.getInstance().getRound(0);
        assertEquals(1500, round.getAvailableTime());
    }

    @Test
    void hasPreQuestionFormat() {
        Round round = Model.getInstance().getRound(0);
        assertTrue(round.hasPreQuestionFormat());
    }

    @Test
    void getGamemode() {
        Round round = Model.getInstance().getRound(0);
        assertEquals(customGamemode, round.getGamemode());
    }

    @Test
    void actionIfCorrectAnswer() {
        Round round = Model.getInstance().getRound(0);
        round.actionIfCorrectAnswer(0);
        assertEquals(4500, Model.getInstance().getScore(0));
        round.actionIfCorrectAnswer(0);
        assertEquals(9000, Model.getInstance().getScore(0));
    }

    @Test
    void actionIfWrongAnswer() {
        Round round = Model.getInstance().getRound(0);
        round.actionIfWrongAnswer(0);
        assertEquals(6000, Model.getInstance().getScore(0));
        round.actionIfCorrectAnswer(0);
        assertEquals(10500, Model.getInstance().getScore(0));
        round.actionIfCorrectAnswer(0);
        assertEquals(15000, Model.getInstance().getScore(0));
    }
}