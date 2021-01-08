package controller;

import controller.requests.Request;
import model.fileHandler.FileHandler;
import view.gui.UI;

public class Dispatcher {
    private UI view;
    private final FileHandler fileHandler;

    public Dispatcher(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        view = null;
    }

    public void dispatch(Request request) {
        request.execute(this);
    }

    public void setView(UI view) {
        this.view = view;
    }
    public UI getView() { return this.view; }
    public FileHandler getFileHandler() { return this.fileHandler; }
}
