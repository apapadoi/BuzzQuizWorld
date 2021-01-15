package controller.requests;

import controller.Dispatcher;
import view.gui.SelectionFrameUI;

import java.util.List;

/**
 * Represents a request for adding the username/s the player/s chose.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class AddUsernamesRequest extends Request{
    private final SelectionFrameUI selectionFrame;

    /**
     * Creates an {@code AddUsernamesRequest} which will use the provided {@code selectionFrame} as a source
     * for usernames of the players.
     * @param selectionFrame the frame that will provide the usernames as {@code SelectionFrameUI}
     */
    public AddUsernamesRequest(SelectionFrameUI selectionFrame) {
        this.selectionFrame = selectionFrame;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        List<String> usernames = selectionFrame.getUsernames();
        if(usernames==null)
            return;
        model.setUsernames(usernames);
    }
}
