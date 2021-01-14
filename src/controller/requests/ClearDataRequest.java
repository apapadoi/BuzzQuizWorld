package controller.requests;

import controller.Dispatcher;

/**
 * Represents a request for clearing the data from a previous game session.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class ClearDataRequest extends Request{
    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.clearData();
        Request.roundId = 0;
        Request.questionId = 0;
        model.getGamemodeFactory().clearGamemodeData();
    }
}
