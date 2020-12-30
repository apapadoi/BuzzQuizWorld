package controller;

import model.Model;
import model.fileHandler.FileHandler;
import view.gui.GUI;
import view.gui.IntroFrame;

public class UpdatedController implements Runnable{
    private final Model model;
    private final GUI view;
    private final FileHandler fileHandler;

    public UpdatedController(Model model, GUI view, FileHandler fileHandler) {
        this.view = view;
        this.model = model;
        this.fileHandler = fileHandler;
    }

    @Override
    public void run() {
        new IntroFrame();
    }
}
