package controller.requests;

import controller.Dispatcher;
import view.gui.UI;

/**
 * Represents a request for moving on to the next question inside a game session.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class NextQuestionRequest extends Request{
    private final UI gamemodeFrame;

    /**
     * Creates a {@code NextQuestionRequest} which will use the {@code UI gamemodeFrame} for invoking a
     * {@code PreQuestionRequest}
     * @param gamemodeFrame the {@code UI} object that will be passed to the {@code PreQuestionRequest}
     */
    public NextQuestionRequest(UI gamemodeFrame) {
        this.gamemodeFrame = gamemodeFrame;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        if(model.getPlayersAnswered().values().stream().distinct().count()>1)
            return;

        if(roundId==model.getNumOfRounds())
            return;

        gamemodeFrame.setVisible(false);
        dispatcher.dispatch(new PreQuestionRequest(gamemodeFrame));
    }
}
