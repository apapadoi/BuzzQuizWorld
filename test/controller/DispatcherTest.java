package controller;

import controller.requests.Request;
import model.fileHandler.FileHandler;
import org.junit.jupiter.api.Test;
import view.gui.GUI;
import view.gui.UI;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {
    private final UI view = new GUI() {
        @Override
        public Dimension getSize() {
            return new Dimension(0,0);
        }
    };
    private boolean requestExecuted = true;
    private final FileHandler fileHandler = new FileHandler(new ArrayList<>(), Paths.get("test/resources/data/questions" +
            "/textQuestions/textQuestions.txt"),Paths.get("test/resources/data/questions/imagedQuestions" +
            "/imagedQuestions.txt"));
    @Test
    void dispatch() {
        Request request = new Request() {
            @Override
            public void execute(Dispatcher dispatcher) {
                DispatcherTest.this.requestExecuted = true;
            }
        };
        Dispatcher dispatcher = new Dispatcher(fileHandler);
        dispatcher.dispatch(request);
        assertTrue(requestExecuted);
    }

    @Test
    void setView() {
        Dispatcher dispatcher = new Dispatcher(fileHandler);
        dispatcher.setView(view);
        assertEquals(view, dispatcher.getView());
    }

    @Test
    void getView() {
        Dispatcher dispatcher = new Dispatcher(fileHandler);
        dispatcher.setView(view);
        assertEquals(view, dispatcher.getView());
    }

    @Test
    void getFileHandler() {
        Dispatcher dispatcher = new Dispatcher(fileHandler);
        assertEquals(fileHandler, dispatcher.getFileHandler());
    }
}