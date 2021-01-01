package controller.requests;

import controller.Dispatcher;
import java.io.IOException;

public class LoadRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        try {
            dispatcher.getFileHandler().readQuestions();
        } catch (IOException exception) {
            // T0DO new ErrorFrame
            System.exit(-5);
        }
    }
}
