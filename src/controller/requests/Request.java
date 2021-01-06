package controller.requests;

import controller.Dispatcher;

public abstract class Request {
    protected static int roundId;
    protected static int questionId;
    protected static final int questionsPerRound;
    protected final int playerIndex;

    static {
        roundId = 0;
        questionId = 0;
        questionsPerRound = 5;
    }

    public Request() {
        this.playerIndex = -1;
    }

    public Request(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public abstract void execute(Dispatcher dispatcher);
}
