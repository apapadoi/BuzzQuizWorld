package view.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UtilGUI {
    private static Font font;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static Font getCustomFont() {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/Minecraft.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont;
        } catch (IOException |FontFormatException e) {
            //Handle exception
            font = new Font("Serif",Font.BOLD,12);
        }

        return font;
    }

    public static int getScreenWidth() {
        return (int)screenSize.getWidth();
    }

    public static int getScreenHeight() {
        return (int)screenSize.getHeight();
    }
}
