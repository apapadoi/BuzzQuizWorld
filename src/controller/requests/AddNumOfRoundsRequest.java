package controller.requests;

import controller.Dispatcher;
import view.gui.SelectionFrameUI;

/**
 * Represents a request for adding the number of rounds the player/s chose.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class AddNumOfRoundsRequest extends Request{
    private final SelectionFrameUI selectionFrame;

    /**
     * Creates an {@code AddNumOfRoundsRequest} which will use the provided {@code selectionFrame} as a source
     * for number of rounds.
     * @param selectionFrame the frame that will provide the number of rounds choice as {@code SelectionFrameUI}
     */
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
