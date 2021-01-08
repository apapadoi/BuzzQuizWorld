package controller;

import controller.requests.Request;
import model.Model;
import model.fileHandler.FileHandler;
import view.gui.UI;

public class Dispatcher {
    private final Model model;
    private UI view;
    private final FileHandler fileHandler;

    public Dispatcher(Model model, FileHandler fileHandler) {
        this.model = model;
        this.fileHandler = fileHandler;
        view = null;
    }

    public void dispatch(Request request) {
        request.execute(this);
    }

    public void setView(UI view) {
        this.view = view;
    }
    public Model getModel() { return this.model; }
    public UI getView() { return this.view; }
    public FileHandler getFileHandler() { return this.fileHandler; }
}
