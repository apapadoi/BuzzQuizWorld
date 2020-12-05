import controller.Controller;
import model.fileHandler.FileHandler;
import model.Model;
import view.cli.Cli;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class contains the main method that program starts from.
 *
 * @author Tasos Papadopoulos
 * @version 17.11.2020
 */
public class BuzzApp {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(),new Cli(),new FileHandler(new ArrayList<>(),
                Paths.get("data/questions/textQuestions/textQuestions.txt")));
        Thread controllerThread = new Thread(controller);
        controllerThread.start();
    }
}
