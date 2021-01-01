package controller.requests;

import controller.Dispatcher;

public abstract class Request {
    protected static int roundId;
    protected static int questionId;
    protected static final int questionsPerRound;

    static {
        roundId = 0;
        questionId = 0;
        questionsPerRound = 5;
    }

    public abstract void execute(Dispatcher dispatcher);
}
