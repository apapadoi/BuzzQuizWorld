import controller.Controller;

/**
 * This class contains the main method that program starts from.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class BuzzApp {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Thread controllerThread = new Thread(controller);
        controllerThread.start();
    }
}
