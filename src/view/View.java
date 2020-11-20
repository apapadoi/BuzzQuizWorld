package view;

import model.questions.Category;
import model.questions.Difficulty;

import java.util.List;

public interface View {
    void printIntroPage(String version);

    void printNumOfPlayersAvailablePage();

    void printAvailableGamemodeChoices(List<String> availableGamemodes);

    void printSingleTextWithoutLineSeparator(String text);

    void printNumberFormatExceptionMessage();

    void printLoadingScreen(String currentGamemodeString, String currentGamemodeDescription);

    void clearScreen();

    void printCurrentGamemode(String currentGamemode);

    void printUsernameChoiceText();

    void printCurrentPlayersUsername(String username);

    void printPlayersScore(int score);

    void printRoundId(int id);

    void printSkipsAvailable(int skips);

    void printQuestionsCategory(Category category);

    void printQuestionsDifficulty(Difficulty difficulty);

    void printAvailableTime(int availableTime);

    void printQuestionsText(String questionsText);

    void printQuestionsAnswers(List<String> answers);

    void printChooseAnswerText();

    void printNotValidAnswerChoiceText();

    void printOutOfSkipsMessage();

    void printFinishPage(String username, int score);

    void printTimeEndedMessage();

    void printBettingPhaseAmount();

    void printNoSuchBettingAmount();

    void printBetDoneAutomatically(int score);

    void printPlayersBet(int bet);
}
