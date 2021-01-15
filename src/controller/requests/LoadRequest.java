package controller.requests;

import controller.Dispatcher;
import java.io.IOException;

/**
 * Represents a request for loading the questions for the next game session.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class LoadRequest extends Request{
    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        try {
            dispatcher.getFileHandler().readQuestions();
        } catch (IOException exception) {
            // TODO new ErrorFrame
            System.exit(-5);
        }
    }
}
