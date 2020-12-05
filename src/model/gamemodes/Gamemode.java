package model.gamemodes;

import model.Model;
import model.questions.Question;

/**
 * Abstract class containing several default implementations for methods of the {@code Gamemodable} interface.
 * @author Tasos Papadopoulos
 * @version 5.12.2020
 * */
public abstract class Gamemode implements Gamemodable{
    protected final String description;
    protected final int availableTime;

    /**
     * Create a gamemode using the given description and available time for each question for this gamemode
     * Used for subclasses because object with dynamic type of this class can not be created.
     * @param description the description of this gamemode
     * @param availableTime the available time in seconds for each question for this gamemode
     */
    public Gamemode(String description,int availableTime) {
        this.description = description;
        this.availableTime = availableTime;
    }

    /**
     * @see Gamemodable
     */
    public abstract String toString();

    /**
     * @see Gamemodable
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @see Gamemodable
     */
    public abstract void actionIfCorrectAnswer(Model model);

    /**
     * @see Gamemodable
     */
    @Override
    public int getAvailableTime() {
        return this.availableTime;
    }

    /**
     * @see Gamemodable
     * @return default value of this implementation is showing the current gamemode,the player's username, the player's score,the id of the current round,
     * the question's category and difficulty, the available time in seconds, the question itself, the possible answers and then an ask message to the user
     */
    @Override
    public String getQuestionFormat(Model model,Question currentQuestion, int roundId) {
        StringBuilder questionFormat = new StringBuilder();
        questionFormat.append("Current Gamemode : ");
        questionFormat.append(model.getRound(roundId).getGamemodeString());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Username : ");
        questionFormat.append(model.getUsername());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Your score : ");
        questionFormat.append(model.getScore());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Current round : ");
        questionFormat.append(roundId+1);
        questionFormat.append(" of ");
        questionFormat.append(model.getNumOfRounds());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question's category : ");
        questionFormat.append(currentQuestion.getCategory());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question's difficulty : ");
        questionFormat.append(currentQuestion.getDifficulty());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Available time : ");
        questionFormat.append(model.getRound(roundId).getAvailableTime());
        questionFormat.append(" seconds");
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question ");
        questionFormat.append(model.getRound(roundId).getQuestions().indexOf(currentQuestion)+1);
        questionFormat.append(" of ");
        questionFormat.append(model.getRound(roundId).getQuestions().size());
        questionFormat.append(" : ");
        questionFormat.append(currentQuestion.getQuestionText());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Answers : ");
        questionFormat.append(System.lineSeparator());
        questionFormat.append("1. ");
        questionFormat.append(currentQuestion.getAnswers().get(0));
        questionFormat.append(System.lineSeparator());
        questionFormat.append("2. ");
        questionFormat.append(currentQuestion.getAnswers().get(1));
        questionFormat.append(System.lineSeparator());
        questionFormat.append("3. ");
        questionFormat.append(currentQuestion.getAnswers().get(2));
        questionFormat.append(System.lineSeparator());
        questionFormat.append("4. ");
        questionFormat.append(currentQuestion.getAnswers().get(3));
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Choose your answer![1-4]");
        questionFormat.append(System.lineSeparator());
        questionFormat.append(">");

        return questionFormat.toString();
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionsPreQuestionsPhase(Model model) throws NumberFormatException,ArithmeticException{ }

    /**
     * @see Gamemodable
     * @return default value for this implementation is false
     */
    @Override
    public boolean hasPreQuestionFormat() { return false; }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfWrongAnswer(Model model) { }

    /**
     * @see Gamemodable
     * @return default value for this implementation is an empty string
     */
    @Override
    public String getPreQuestionAskMessage() {
        return "";
    }

    /**
     * @see Gamemodable
     * @return default value for this implementation is an empty string
     */
    @Override
    public String getPreQuestionFormat(Model model, Question currentQuestion) { return ""; }
}
