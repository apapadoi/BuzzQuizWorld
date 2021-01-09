package controller.requests;

import controller.Dispatcher;

public class AddNumOfRoundsRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setNumOfRoundsChoice(dispatcher.getView().getNumOfRoundsChoice(),
                dispatcher.getFileHandler());
    }
}
