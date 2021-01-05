package model.gamemodes;

import model.Model;

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

    public Gamemode(String description) {
        this.description = description;
        this.availableTime = 0;
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

    @Override
    public void actionIfCorrectAnswer(Model model, int playerIndex) { }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfCorrectAnswer(Model model, int millis, int playerIndex) {
        this.actionIfCorrectAnswer(model, playerIndex);
    }

    /**
     * @see Gamemodable
     */
    @Override
    public int getAvailableTime() {
        return this.availableTime;
    }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionPreQuestionsPhase(Model model) { }

    /**
     * @see Gamemodable
     * @return default value for this implementation is false
     */
    @Override
    public boolean hasPreQuestionPhase() { return false; }

    /**
     * @see Gamemodable
     */
    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) { }

    @Override
    public boolean hasTimer() {
        return false;
    }

    @Override
    public int getMinBet() {
        return 0;
    }

    @Override
    public void setBetAmount(int amount, int playerIndex) {

    }

    @Override
    public void checkZeroScoreAndUpdate(Model model, int playerIndex) {

    }
}
