package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadingScreenFrame extends JFrame implements GUI{
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private TwoPlayersSelectionFrame twoPlayersSelectionFrame;
    private JLabel backgroundImageLabel;

    public LoadingScreenFrame(OnePlayerSelectionFrame onePlayerFrame) {
        this.onePlayerSelectionFrame =onePlayerFrame;
        this.setUpJFrameProperties();
        this.setUpBackgroundOnePlayer();
        this.setVisible(true);
    }

    public LoadingScreenFrame(TwoPlayersSelectionFrame twoPlayersFrame){
        this.twoPlayersSelectionFrame =twoPlayersFrame;
        this.setUpJFrameProperties();
        this.setUpBackgroundTwoPlayers();
        this.setVisible(true);
    }

    private void setUpBackgroundTwoPlayers() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.LOADING_SCREEN_PAGE_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(), UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpBackgroundOnePlayer() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.LOADING_SCREEN_PAGE_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(), UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
}
