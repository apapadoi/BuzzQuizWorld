package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadingScreenFrame extends JFrame {
    private OnePlayerSelectionFrame onePlayerFrame;
    private TwoPlayersSelectionFrame twoPlayersFrame;
    private JLabel backgroundImageLabel;
    private Font font;


    public LoadingScreenFrame(OnePlayerSelectionFrame onePlayerFrame) {
        this.onePlayerFrame=onePlayerFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setVisible(true);
    }

    public LoadingScreenFrame(TwoPlayersSelectionFrame twoPlayersFrame){
        this.twoPlayersFrame=twoPlayersFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setVisible(true);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.LOADING_SCREEN_PAGE_IMG).getImage().
                getScaledInstance(onePlayerFrame.getScreenWidth(),onePlayerFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
    }

    private void loadFont() {
        // create the custom font
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/Minecraft.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont;
        } catch (IOException |FontFormatException e) {
            //Handle exception
            font = new Font("Serif",Font.BOLD,12);
        }
    }


}
