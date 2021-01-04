package view.gui;

import controller.ButtonSoundListener;
import resources.images.Constants;
import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UtilGUI {
    private static Font font;
    private static final Dimension screenSize;

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
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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

    public static JLabel setUpBackGround(JFrame frame,Image image) {
        JLabel label = new JLabel();
        frame.add(label,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(image).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        label.setLayout(new BorderLayout());
        return label;
    }

    public static JButton getButtonInstance(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(UtilGUI.getCustomFont());
        button.setFocusPainted(false);
        button.setBackground(new Color(54,54,55,255));
        button.setForeground(new Color(156,156,156,255));
        button.setBorder(new LineBorder(Color.BLACK));
        button.addActionListener(ButtonSoundListener.getInstance());
        button.setMultiClickThreshhold(2000L);
        return button;
    }

    public static JButton getButtonInstance(String buttonText,float fontSize) {
        JButton button = getButtonInstance(buttonText);
        button.setFont(UtilGUI.getCustomFont().deriveFont(fontSize));
        return button;
    }

    public static JLabel getLabelInstance(String labelText) {
        JLabel label=new JLabel(labelText);
        label.setFont(UtilGUI.getCustomFont());
        label.setForeground(Color.WHITE);

        return label;
    }

    public static JLabel getLabelInstance(String labelText,float fontSize) {
        JLabel label=getLabelInstance(labelText);
        label.setFont(UtilGUI.getCustomFont().deriveFont(fontSize));

        return label;
    }
}
