package model.gamemodes;

import model.Model;
import model.questions.Question;
import view.cli.Cli;

/**
 * @author Tasos Papadopoulos
 * @version 23.11.2020
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
    public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {
        view.printCurrentGamemode(model.getCurrentGamemodeString());
        view.printCurrentPlayersUsername(model.getUsername());
        view.printPlayersScore(model.getScore());
        view.printRoundId(roundId);
        view.printSkipsAvailable(model.getSkipsAvailable());
        view.printQuestionsCategory(currentQuestion.getCategory());
        view.printQuestionsDifficulty(currentQuestion.getDifficulty());
        view.printAvailableTime(model.getAvailableTime());
        view.printQuestionsText(currentQuestion.getQuestionText());
        view.printQuestionsAnswers(currentQuestion.getAnswers());
        view.printStringWithoutLineSeparator("Choose your answer or type 'skip' if you want to skip the question!"+System.lineSeparator()+">");
    }

    @Override
    public void actionsPreQuestionsPhase(Model model, Cli view, Question currentQuestion) { }

    @Override
    public boolean hasPreQuestionFormat() { return false; }

    @Override
    public void actionIfWrongAnswer(Model model) { }
}
