package controller.requests;

import controller.Dispatcher;

/**
 * Represents a request for adding the number of rounds the player/s chose.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class AddNumOfRoundsRequest extends Request{
    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setNumOfRoundsChoice(dispatcher.getView().getNumOfRoundsChoice(),
                dispatcher.getFileHandler());
    }
}
