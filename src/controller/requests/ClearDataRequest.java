package controller.requests;

import controller.Dispatcher;

public class ClearDataRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        model.clearData();
        Request.roundId = 0;
        Request.questionId = 0;
        model.getGamemodeFactory().clearGamemodeData();
    }
}
