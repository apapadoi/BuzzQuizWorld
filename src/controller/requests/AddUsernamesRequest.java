package controller.requests;

import controller.Dispatcher;
import model.Model;
import java.util.List;

public class AddUsernamesRequest extends Request{
    @Override
    public void execute(Dispatcher dispatcher) {
        List<String> usernames = dispatcher.getView().getUsernames();
        if(usernames==null)
            return;
        Model.getInstance().setUsernames(usernames);
    }
}
