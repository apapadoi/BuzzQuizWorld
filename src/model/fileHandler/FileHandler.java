package model.fileHandler;

import model.Model;
import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;
import model.questions.ImagedQuestion;
import model.questions.Question;
import view.gui.utilResources.Constants;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a file handler which handles the files containing the data of the app.
 * @author Tasos Papadopoulos
 * @version 13.1.2021
 * */
public class FileHandler {
    private String db_file = Constants.DB_FILE_URL;
    private final List<Question> questionList;
    private final Path textDataPath;
    private int questionsReturned;
    private final Path imagedDataPath;
    private boolean loadedQuestions;

    /**
     * Create a file handler using the given {@code List<Question>} and {@code Paths} given for
     * the text and imaged questions.
     * @param textDataPath the path of the file that contains the text questions
     * @param imagedDataPath the path of the file that contains the imaged questions
     * @param questionList the List that will be stored the questions from the file
     */
    public FileHandler(List<Question> questionList,Path textDataPath, Path imagedDataPath) {
        this.loadedQuestions = false;
        this.questionList = questionList;
        this.textDataPath = textDataPath;
        this.questionsReturned = 0;
        this.imagedDataPath = imagedDataPath;
    }

    /**
     * Set the path of the file containing the scores of the players that have played the game.
     * @param db_file the path of the file as {@code String}
     */
    public void setDb_file(String db_file) {
        this.db_file = db_file;
    }

    /**
     * Reads the questions from the files that were given in the constructor of the class.
     * @throws IOException if the file was not found
     */
    public void readQuestions() throws IOException{
        if(loadedQuestions) {
            Collections.shuffle(this.questionList);
            questionsReturned = 0;
            return;
        }
        loadedQuestions = true;
        this.readTextQuestions();
        this.readImagedQuestions();
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

    /**
     * Updates the specified db file with the player/players of the current game
     * @throws IOException if the file was not found or another IO problem happened
     */
    public void savePlayers() throws IOException {
        List<Player> currentPlayers = new ArrayList<>(Model.getInstance().getPlayers());
        List<Player> previousPlayers = null;
        try {
            previousPlayers = this.readPlayers();
        } catch(IOException|ClassNotFoundException e) {
            // TODO ADD ERROR FRAME REQUEST
            System.exit(-5);
        }

        if(previousPlayers.size()==0) {
            previousPlayers.addAll(currentPlayers);
        }
        else {
            for(Player currentPlayer : currentPlayers) {
                boolean found = false;
                for(Player previousPlayer : previousPlayers) {
                    if(currentPlayer.getUsername().equals(previousPlayer.getUsername())) {
                        int previousScore = previousPlayer.getScore();
                        int currentScore = currentPlayer.getScore();
                        int previousWins = previousPlayer.getWins();
                        int currentWins = currentPlayer.getWins();
                        previousPlayer.setScore(
                                Math.max(previousScore, currentScore)
                        );
                        previousPlayer.setWins(
                                currentWins > 0 ? previousWins + currentWins : previousWins
                        );
                        found = true;
                    }
                }
                if(!found) {
                    previousPlayers.add(currentPlayer);
                }
            }
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.db_file))) {
            out.writeObject(previousPlayers);
        }
    }

    /**
     * Reads the {@code Player} objects from the specified db_file.
     * @return The {@code Player} objects as {@code List<Player>}
     * @throws IOException if the file was not found or another IO problem happened
     * @throws ClassNotFoundException if the {@code Player} class was not found
     */
    public List<Player> readPlayers() throws IOException, ClassNotFoundException{
        List<Player> players=null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.db_file))) {
            Object object = in.readObject();
            if(object instanceof List) {
                players = (List<Player>)object;
            }
        }
        return players;
    }

    /**
     * Reads the text questions from the file that was given in the constructor of the class.
     * @throws IOException if the file was not found or another IO problem happened
     */
    private void readTextQuestions() throws IOException{
        try (BufferedReader reader = Files.newBufferedReader(textDataPath, StandardCharsets.UTF_8)) {
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
     * Reads the imaged questions from the file that was given in the constructor of the class.
     * @throws IOException if the file was not found or another IO problem happened
     */
    private void readImagedQuestions() throws IOException{
        try(BufferedReader reader = Files.newBufferedReader(imagedDataPath, StandardCharsets.UTF_8)) {
            String line;
            while(( line = reader.readLine() ) != null) {
                String [] splitQuestion = line.split(";");
                ImagedQuestion currentQuestion = new ImagedQuestion("src/resources/images/"+splitQuestion[8]);
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
     * Returns the question list that contains the questions were loaded.
     * @return the question list as {@code List<Question>}
     */
    public List<Question> getQuestionList() {
        return questionList;
    }
}
