package controller.requests;

import controller.Dispatcher;

public class AddNumOfRoundsRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        dispatcher.getModel().setNumOfRoundsChoice( dispatcher.getView().getNumOfRoundsChoice(),
                dispatcher.getFileHandler());
    }
}
