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
    private SelectionFrameUI selectionFrame;

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
