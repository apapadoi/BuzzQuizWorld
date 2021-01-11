package controller;

import controller.requests.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrontControllerTest {
    private boolean requestExecuted = false;

    @Test
    void dispatchRequest() {
        Request request = new Request() {
            @Override
            public void execute(Dispatcher dispatcher) {
                FrontControllerTest.this.requestExecuted = true;
            }
        };
        FrontController.getInstance().dispatchRequest(request);
        assertTrue(requestExecuted);
    }
}