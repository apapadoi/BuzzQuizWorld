package model.fileHandler;

import model.questions.Category;
import model.questions.Difficulty;
import model.questions.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a file handler which handles the files containing the data of the app.
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 * */
public class FileHandler {
    List<Question> questionList;
    Path dataPath;
    int questionsReturned;

    /**
     * Create a file handler using the given {@code List<Question>} and {@code Path} given.
     * @param dataPath the path of the file that contains the questions
     * @param questionList the List that will be stored the questions from the file
     */
    public FileHandler(List<Question> questionList,Path dataPath) {
        this.questionList = questionList;
        this.dataPath = dataPath;
        this.questionsReturned = 0;
    }

    /**
     * Reads the questions from the file with path the path that was given in the constructor of the class.
     * @throws IOException if the file was not found
     */
    public void readQuestions() throws IOException{
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
        }
    }

    /**
     * Returns 5 questions when invoked.
     * @return 5 new questions as {@code List<Question>}
     */
    public List<Question> getNextQuestions() {
        List<Question> nextQuestions = new ArrayList<>(5);
        int i;
        for(i=this.questionsReturned;i<questionsReturned+5;i++)
            nextQuestions.add(this.questionList.get(i));

        this.questionsReturned = i;
        Collections.shuffle(nextQuestions);
        return nextQuestions;
    }
}
