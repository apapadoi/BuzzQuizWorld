package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.round.Round;

public class PreQuestionRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();

        Round currentRound = model.getRound(Request.roundId);
        if(currentRound.hasPreQuestionFormat()) {
            // TODO THE PRE QUESTION ACTIONS
            System.out.println("Pre question request");
            currentRound.actionsPreQuestionsPhase(model);
        }

    }
}
