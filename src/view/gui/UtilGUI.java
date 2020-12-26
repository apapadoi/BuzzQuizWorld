package view.gui;

import resources.images.Constants;
import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UtilGUI {
    private static Font font;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Constants.FONT_FILE_URL)).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont;
        } catch (IOException |FontFormatException e) {
            //Handle exception
            font = new Font("Serif",Font.BOLD,12);
        }
    }

    public static Font getCustomFont() {
        return font;
    }

    public static int getScreenWidth() {
        return (int)screenSize.getWidth();
    }

    public static int getScreenHeight() {
        return (int)screenSize.getHeight();
    }

    public static void setUpJFrameProperties(JFrame frame) {
        frame.setTitle("Buzz! Quiz World Remastered");
        frame.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        frame.setLayout(new BorderLayout());
    }
}
