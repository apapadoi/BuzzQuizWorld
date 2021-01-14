package controller.requests;

import controller.Dispatcher;
import model.Model;

/**
 * Represents an abstract request entity.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public abstract class Request {
    protected static int roundId;
    protected static int questionId;
    protected static final int questionsPerRound;
    protected final int playerIndex;
    protected static final Model model;

    static {
        roundId = 0;
        questionId = 0;
        questionsPerRound = 5;
        model = Model.getInstance();
    }

    /**
     * Initializes private attributes when a request does not need info about the player that caused the
     * request.
     */
    public Request() {
        this.playerIndex = -1;
    }

    /**
     * Initializes the private attributes using the {@code playerIndex} provided.
     * @param playerIndex the player's index that caused the request as {@code int}
     */
    public Request(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * Executes the actions associated with a request.
     * @param dispatcher the dispatcher that will dispatch the request as {@code Dispatcher}
     */
    public abstract void execute(Dispatcher dispatcher);
}
