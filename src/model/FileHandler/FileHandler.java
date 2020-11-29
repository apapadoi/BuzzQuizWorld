package model.FileHandler;

import model.questions.Category;
import model.questions.Difficulty;
import model.questions.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a file handler which handles the data of the app.
 * @author Tasos Papadopoulos
 * @version 29.11.2020
 * */
public class FileHandler {
    List<Question> questionList;
    Path dataPath;
    int questionsReturned;

    public FileHandler() {
        this.questionList = new ArrayList<>();
        this.dataPath = Paths.get("data/questions/textQuestions/textQuestions.txt");
        this.questionsReturned = 0;
    }

    public void readQuestions() {
        try (BufferedReader reader = Files.newBufferedReader(dataPath, Charset.forName("UTF-8"))) {
            String line;
            while(( line = reader.readLine() ) != null) {
                String[] splitQuestion = line.split(";");
                Question currentQuestion = new Question();
                currentQuestion.setCategory(Category.valueOf(splitQuestion[0]));
                currentQuestion.setQuestionText(splitQuestion[1]);
                List<String> answers = new ArrayList<>( List.of(splitQuestion[2],
                                                                splitQuestion[3],
                                                                splitQuestion[4],
                                                                splitQuestion[5]));
                Collections.shuffle(answers);
                currentQuestion.setAnswers(answers);
                currentQuestion.setCorrectAnswer(splitQuestion[6]);
                currentQuestion.setDifficulty(Difficulty.valueOf(splitQuestion[7]));
                questionList.add(currentQuestion);
            }
            Collections.shuffle(this.questionList);
        } catch(IOException e) {
            System.out.println("Could not found questions file.");
        }
    }

    public List<Question> getNextQuestions() {
        List<Question> nextQuestions = new ArrayList<>(5);
        int i;
        for(i=this.questionsReturned;i<questionsReturned+5;i++)
            nextQuestions.add(this.questionList.get(i));

        this.questionsReturned = i;
        return nextQuestions;
    }
}
