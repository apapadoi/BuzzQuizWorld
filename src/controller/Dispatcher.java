package controller;

import controller.requests.Request;
import model.fileHandler.FileHandler;
import view.gui.UI;

/**
 * Represents the dispatcher for dispatching requests.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class Dispatcher {
    private UI view;
    private FileHandler fileHandler;

    /**
     * Constructs a dispatcher with the file handler provided.
     * @param fileHandler the file handler that will be used for dispatching requests as {@code FileHandler}
     */
    public Dispatcher(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        view = null;
    }

    /**
     * Dispatches the provided request.
     * @param request the request that will be dispatched as {@code Request}
     */
    public void dispatch(Request request) {
        request.execute(this);
    }

    /**
     * Setter for the view component that is used from the dispatcher.
     * @param view the view component that will be used from the dispatcher as {@code UI}
     */
    public void setView(UI view) {
        this.view = view;
    }

    /**
     * Returns the view component.
     * @return the view component as {@code UI}
     */
    public UI getView() { return this.view; }

    /**
     * Returns the file handler that is used for dispatching requests.
     * @return the file handler as {@code FileHandler}
     */
    public FileHandler getFileHandler() { return this.fileHandler; }

    /**
     * Setter for the file handler that is used for dispatching requests.
     * @param fileHandler the new file handler that will be used from the dispatcher as {@code FileHandler}
     */
    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
}
