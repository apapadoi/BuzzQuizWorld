package controller;

import controller.requests.Request;
import model.fileHandler.FileHandler;
import resources.utilResources.Constants;
import view.gui.UI;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the controller for dispatching requests. Singleton Design Pattern is used.
 * @author Tasos Papapdopoulos
 * @version 14.1.2021
 */
public class FrontController{
    private static final Dispatcher dispatcher;
    private static final FrontController instance = new FrontController();

    static {
        dispatcher = new Dispatcher(new FileHandler(new ArrayList<>(),
                Paths.get(Constants.QUESTIONS_FILE_URL), Paths.get(Constants.IMAGED_QUESTIONS_FILE_URL)));
    }

    /**
     * Default constructor.
     */
    private FrontController() { }

    /**
     * Dispatches the provided request.
     * @param request the request that will be dispatched as {@code Request}
     */
    public void dispatchRequest(Request request) {
        dispatcher.dispatch(request);
    }

    /**
     * Setter for the view component that the dispatcher uses.
     * @param view the view component that will be used from the dispatcher as {@code UI}
     */
    public void setView(UI view) {
        dispatcher.setView(view);
    }

    /**
     * Returns the unique {@code FrontController} instance
     * @return the front controller instance as {@code FrontController}
     */
    public static FrontController getInstance() {
        return instance;
    }

    /**
     * Setter for the file handler that the dispatcher uses.
     * @param fileHandler the file handler that will be used from the dispatcher as {@code FileHandler}
     */
    public void setFileHandler(FileHandler fileHandler) {
        dispatcher.setFileHandler(fileHandler);
    }
}
