package model.fileHandler;

import model.Model;
import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.ImagedQuestion;
import model.questions.Question;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void readQuestions1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
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

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3));
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();
        int containedQuestions = 0;
        for(Question question:hardCodedQuestions)
            if (fileHandlerQuestions.contains(question))
                containedQuestions++;

        assertEquals(3, containedQuestions);
    }

    @Test
    void readQuestions2() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Question question4 = new ImagedQuestion("Name the Hip Hop artist","Lex",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Easy"), Category.valueOf("HipHopArtists"), new ImageIcon("question_1_image.png"));
        Question question5 = new ImagedQuestion("Name the Hip Hop artist","Vlospa",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Medium"), Category.valueOf("HipHopArtists"), new ImageIcon("question_2_image.png"));

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question4,question5));
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();
        int containedQuestions = 0;
        for(Question question:hardCodedQuestions)
            if (fileHandlerQuestions.contains(question))
                containedQuestions++;

        assertEquals(2, containedQuestions);
    }

    @Test
    void readQuestions3() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
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

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1,question2,question3));
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();

        int questionsWithSameAnswers = 0;
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
        assertEquals(3, questionsWithSameAnswers);
        assertEquals((fileHandlerQuestions.size() - 2), hardCodedQuestions.size());
    }

    @Test
    void readQuestions4() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
        Question question4 = new ImagedQuestion("Name the Hip Hop artist","Lex",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Easy"), Category.valueOf("HipHopArtists"), new ImageIcon("question_1_image.png"));
        Question question5 = new ImagedQuestion("Name the Hip Hop artist","Vlospa",
                new ArrayList<>(List.of(
                        "Lex","Vlospa","Tzamal","Wang"
                )), Difficulty.valueOf("Medium"), Category.valueOf("HipHopArtists"), new ImageIcon("question_2_image.png"));

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question4,question5));
        List<Question> fileHandlerQuestions = fileHandler.getQuestionList();

        int questionsWithSameAnswers = 0;
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
        assertEquals(2, questionsWithSameAnswers);
        assertEquals((fileHandlerQuestions.size() - 3), hardCodedQuestions.size());
    }

    @Test
    void getNextQuestions1() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {fileHandler.readQuestions();}
        catch(IOException e) {
            System.out.println("IO EXCEPTION "+e.getMessage());
        }
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
        int containedQuestions = 0;
        List<Question> fileHandlerQuestions = fileHandler.getNextQuestions();
        for(Question hardCodedQuestion:hardCodedQuestions)
            if(fileHandlerQuestions.contains(hardCodedQuestion))
                containedQuestions++;

        assertEquals(5, containedQuestions);
        assertEquals(5, hardCodedQuestions.size());
        assertEquals(5,fileHandlerQuestions.size());
    }

    @Test
    void getNextQuestions2() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        try {
            fileHandler.readQuestions();
        } catch (IOException e) {
            System.out.println("IO EXCEPTION " + e.getMessage());
        }
        Question question1 = new Question("Which nut is used to make dynamite?", "Peanuts",
                new ArrayList<>(List.of(
                        "Peanuts", "Walnuts", "Pine nuts", "Almonds"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question2 = new Question("What was the first cold breakfast cereal invented in 1863?",
                "Granola", new ArrayList<>(List.of(
                "Granola", "Corn flakes", "Bran flakes", "Cheerios"
        )), Difficulty.valueOf("Hard"), Category.valueOf("Food"));
        Question question3 = new Question("Who invented Coca-Cola?", "John Pemberton",
                new ArrayList<>(List.of(
                        "Asa Griggs Candler", "Charles Elmer Hires", "John Matthews", "John Pemberton"
                )), Difficulty.valueOf("Medium"), Category.valueOf("Food"));
        Question question4 = new ImagedQuestion("Name the Hip Hop artist", "Lex",
                new ArrayList<>(List.of(
                        "Lex", "Vlospa", "Tzamal", "Wang"
                )), Difficulty.valueOf("Easy"), Category.valueOf("HipHopArtists"), new ImageIcon("question_1_image.png"));
        Question question5 = new ImagedQuestion("Name the Hip Hop artist", "Vlospa",
                new ArrayList<>(List.of(
                        "Lex", "Vlospa", "Tzamal", "Wang"
                )), Difficulty.valueOf("Medium"), Category.valueOf("HipHopArtists"), new ImageIcon("question_2_image.png"));

        List<Question> hardCodedQuestions = new ArrayList<>(List.of(question1, question2, question3, question4, question5));
        List<Question> fileHandlerQuestions = fileHandler.getNextQuestions();
        int questionsWithSameAnswers = 0;

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
    void savePlayers() {
        Model.getInstance().clearData();
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        fileHandler.setDb_file("test/resources/db/savePlayersTest.bin");
        Model.getInstance().setUsernames(new ArrayList<>());
        boolean savedSuccessfully = false;
        try {
            fileHandler.savePlayers();
            savedSuccessfully = true;
        }catch(IOException e) {
            savedSuccessfully = false;
        }
        List<Player> loadedPlayers;
        try {
            loadedPlayers = fileHandler.readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            loadedPlayers = null;
        }
        assertNotNull(loadedPlayers);
        assertTrue(savedSuccessfully);
        assertEquals(0,loadedPlayers.size());
    }

    @Test
    void readPlayers() {
        FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
                "/textQuestions/textQuestions.txt"), Paths.get("test/resources/data/questions/imagedQuestions" +
                "/imagedQuestions.txt"));
        fileHandler.setDb_file("test/resources/db/scores.bin");
        List<Player> loadedPlayers;
        try {
            loadedPlayers = fileHandler.readPlayers();
        }catch(IOException|ClassNotFoundException e) {
            loadedPlayers = null;
        }
        assertNotNull(loadedPlayers);
    }


}