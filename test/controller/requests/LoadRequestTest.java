package controller.requests;

import controller.FrontController;
import model.Model;
import model.fileHandler.FileHandler;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.ImagedQuestion;
import model.questions.Question;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadRequestTest {

    @Test
    void execute1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        Model.getInstance().clearData();
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        Model.getInstance().setMaxPlayers(1);
        FrontController.getInstance().setFileHandler(fileHandler);
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
        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3,
                question4, question5));

        FrontController.getInstance().dispatchRequest(new LoadRequest());
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();
        int questionsWithSameAnswers =0;
        for(Question hardCodedQuestion:hardCodedQuestions) {
            boolean sameAnswers = false;
            for(Question fileHandlerQuestion:fileHandlerQuestions)
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
    }

    @Test
    void execute() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        Model.getInstance().clearData();
        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
        Model.getInstance().setMaxPlayers(1);
        FrontController.getInstance().setFileHandler(fileHandler);
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

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3,
                question4, question5));
        FrontController.getInstance().dispatchRequest(new LoadRequest());
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();

        int containedQuestions = 0;
        for(Question hardCodedQuestion:hardCodedQuestions)
            if(fileHandlerQuestions.contains(hardCodedQuestion))
                containedQuestions++;

        assertEquals(5, containedQuestions);
        assertEquals(5, hardCodedQuestions.size());
        assertEquals(5,fileHandlerQuestions.size());
    }
}