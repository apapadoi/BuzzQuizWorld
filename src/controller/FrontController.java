package controller;

import controller.requests.Request;
import model.Model;
import model.fileHandler.FileHandler;
import resources.images.Constants;
import view.gui.GUI;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FrontController{
    private static final Dispatcher dispatcher;
    private static final FrontController instance = new FrontController();

    static {
        dispatcher = new Dispatcher(new Model(), new FileHandler(new ArrayList<>(),
                Paths.get(Constants.QUESTIONS_FILE_URL)));
    }

    private FrontController() { }

    public void dispatchRequest(Request request) {
        dispatcher.dispatch(request);
    }

    public void setView(GUI view) {
        dispatcher.setView(view);
    }

    public static FrontController getInstance() {
        return instance;
    }
}
