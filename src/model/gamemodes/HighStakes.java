package model.gamemodes;

import model.Model;
import model.questions.Question;
import view.cli.Cli;

public class HighStakes implements Gamemodable {
    final String description;

    public HighStakes() {
        this.description = "At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n";
    }

    @Override
    public String toString() {
        return "High Stakes";
    }

    @Override
    public int getSkipsAvailable() {
        return 0;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void decreaseSkips() {

    }

    @Override
    public void actionIfCorrectAnswer(Model model) {

    }

    @Override
    public int getAvailableTime() {
        return 0;
    }

    @Override
    public void showQuestionFormat(Model model, Cli view, Question currentQuestion, int roundId) {

    }

    @Override
    public boolean actionWhenAnswered(String choice, Question currentQuestion, int secondsTookToAnswer, Cli view, Model model) throws NumberFormatException {
        return false;
    }

    @Override
    public void showPreQuestionFormat(Model model, Cli view) {

    }

    @Override
    public boolean hasPreQuestionFormat() {
        return true;
    }


}
