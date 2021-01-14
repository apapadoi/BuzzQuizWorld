package controller;

import controller.requests.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Front Controller class.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
class FrontControllerTest {
    private boolean requestExecuted = false;

    /**
     * Tests whether or not the Front Controller dispatches a request using a Dispatcher.
     */
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