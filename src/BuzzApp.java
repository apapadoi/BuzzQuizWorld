import view.gui.IntroFrame;

import javax.swing.*;

/**
 * This class contains the main method that program starts from.
 *
 * @author Tasos Papadopoulos
 * @author Thodwris Myridhs
 * @version 14.1.2021
 */
public class BuzzApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IntroFrame());
    }
}
