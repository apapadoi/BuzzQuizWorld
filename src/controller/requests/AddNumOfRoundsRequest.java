package controller.requests;

import controller.Dispatcher;
import view.gui.SelectionFrameUI;

/**
 * Represents a request for adding the number of rounds the player/s chose.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class AddNumOfRoundsRequest extends Request{
    private SelectionFrameUI selectionFrame;

    public AddNumOfRoundsRequest(SelectionFrameUI selectionFrame) {
        this.selectionFrame = selectionFrame;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setNumOfRoundsChoice(selectionFrame.getNumOfRoundsChoice(),
                dispatcher.getFileHandler());
    }
}
