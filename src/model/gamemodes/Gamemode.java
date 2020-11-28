package model.gamemodes;

import model.Model;
import model.questions.Question;

/**
 * @author Tasos Papadopoulos
 * @version 28.11.2020
 * */
public abstract class Gamemode implements Gamemodable{
    protected final String description;
    protected final int availableTime;
    protected int skipsAvailable;

    public Gamemode(String description,int availableTime,int skipsAvailable) {
        this.description = description;
        this.availableTime = availableTime;
        this.skipsAvailable = skipsAvailable;
    }

    public abstract String toString();

    @Override
    public int getSkipsAvailable() {
        return this.skipsAvailable;
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    public void decreaseSkips() {
        (this.skipsAvailable)--;
    }

    public abstract void actionIfCorrectAnswer(Model model,int secondsTookToAnswer);

    @Override
    public int getAvailableTime() {
        return this.availableTime;
    }

    @Override
    public String getQuestionFormat(Model model,Question currentQuestion, int roundId) {
        StringBuilder questionFormat = new StringBuilder();
        questionFormat.append("Current Gamemode : ");
        questionFormat.append(model.getCurrentGamemodeString());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Username : ");
        questionFormat.append(model.getUsername());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Your score : ");
        questionFormat.append(model.getScore());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Current round : ");
        questionFormat.append(roundId);
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Available number of skips : ");
        questionFormat.append(model.getSkipsAvailable());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question's category : ");
        questionFormat.append(currentQuestion.getCategory());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question's difficulty : ");
        questionFormat.append(currentQuestion.getDifficulty());
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Available time : ");
        questionFormat.append(model.getAvailableTime());
        questionFormat.append(" seconds");
        questionFormat.append(System.lineSeparator());
        questionFormat.append("Question : ");
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

        questionFormat.append("Choose your answer or type 'skip' if you want to skip the question!");
        questionFormat.append(System.lineSeparator());
        questionFormat.append(">");

        return questionFormat.toString();
    }

    @Override
    public void actionsPreQuestionsPhase(Model model,Question currentQuestion) throws NumberFormatException,ArithmeticException{ }

    @Override
    public boolean hasPreQuestionFormat() { return false; }

    @Override
    public void actionIfWrongAnswer(Model model) { }

    @Override
    public String getPreQuestionAskMessage() {
        return null;
    }

    @Override
    public String getPreQuestionFormat(Model model, Question currentQuestion) { return null; }
}
