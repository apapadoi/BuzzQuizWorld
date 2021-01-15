package view.gui;

import view.gui.utilResources.Constants;
import view.gui.utilResources.Image;
import view.gui.utilResources.ImageFactory;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the general settings,setters and getters that need to be set in all frames.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
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

    /**
     * This method returns the custom font
     * @return custom font as {@code Font}
     */
    public static Font getCustomFont() {
        return font;
    }

    /**
     * This method returns the screen width.
     * @return screen width as {@code int}
     */
    public static int getScreenWidth() {
        return (int)screenSize.getWidth();
    }

    /**
     * This method returns the screen height.
     * @return screen height as {@code int}
     */
    public static int getScreenHeight() {
        return (int)screenSize.getHeight();
    }

    /**
     * This method sets all necessary settings for the frame.
     * @param frame instance of {@code JFrame}
     */
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

    /**
     * This method sets the background image in the main label.
     * @param frame instance of {@code JFrame}
     * @param image instance of {@code Image}
     * @return the label background as {@code JLabel}
     */
    public static JLabel setUpBackGround(JFrame frame,Image image) {
        JLabel label = new JLabel();
        frame.add(label,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(image).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        label.setLayout(new BorderLayout());
        return label;
    }

    /**
     * This method constructs a custom themed button.
     * @param buttonText instance of {@code String}
     * @return constructed button as {@code JButton}
     */
    public static JButton getButtonInstance(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setFont(UtilGUI.getCustomFont());
        button.setFocusPainted(false);
        button.setBackground(new Color(54,54,55,255));
        button.setForeground(Color.WHITE);
        button.setBorder(new LineBorder(Color.BLACK));
        button.addActionListener(ButtonSoundListener.getInstance());
        button.setMultiClickThreshhold(250L);
        return button;
    }

    /**
     * This method constructs a custom themed button.
     * @param buttonText instance of {@code String}
     * @param fontSize instance of {@code float}
     * @return constructed button as {@code JButton}
     */
    public static JButton getButtonInstance(String buttonText,float fontSize) {
        JButton button = getButtonInstance(buttonText);
        button.setFont(UtilGUI.getCustomFont().deriveFont(fontSize));
        return button;
    }

    /**
     * This method constructs a custom themed label.
     * @param labelText instance of {@code String}
     * @return constructed label as {@code JLabel}
     */
    public static JLabel getLabelInstance(String labelText) {
        JLabel label=new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(UtilGUI.getCustomFont());
        label.setForeground(Color.WHITE);

        return label;
    }

    /**
     * This method constructs a custom themed label.
     * @param labelText instance of {@code String}
     * @param fontSize instance of {@code float}
     * @return constructed label as {@code JLabel}
     */
    public static JLabel getLabelInstance(String labelText,float fontSize) {
        JLabel label=getLabelInstance(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(UtilGUI.getCustomFont().deriveFont(fontSize));
        label.setForeground(Color.WHITE);

        return label;
    }

    /**
     * This method constructs a general back button panel.
     * @param backButton instance of {@code JButton}
     * @return back button panel as {@code JPanel}
     */
    public static JPanel getBackPanel(JButton backButton){
        JPanel backPanel=new JPanel();
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.894*UtilGUI.getScreenWidth()),
                (int)(0.009*UtilGUI.getScreenHeight()),0));

        return backPanel;
    }
}
