package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BettingFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private OnePlayerFrame onePlayerFrame;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel bettingPanel;
    private JLabel bettingLabel;
    private JPanel labelsPanel;
    private JPanel buttonsPanel;
    private JLabel amountAvailableLabel;
    private JLabel bettingPhaseLabel;
    private JPanel bettingPhasePanel;
    private JButton bettingAmountButton1;
    private JButton bettingAmountButton2;
    private JButton bettingAmountButton3;
    private JButton bettingAmountButton4;



    public BettingFrame(OnePlayerSelectionFrame onePlayerSelectionFrame){
        this.onePlayerSelectionFrame=onePlayerSelectionFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
    }

    private void setComponentsPanel() {
        bettingPanel=new JPanel();
        bettingPanel.setOpaque(false);
        bettingPanel.setLayout(new BorderLayout());

        labelsPanel=new JPanel();
        labelsPanel.setLayout(new GridLayout(2,1,0,30));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(25,800,0,0));

        bettingPhaseLabel=new JLabel("Betting Phase");
        bettingPhaseLabel.setFont(font);


        bettingPhasePanel=new JPanel();
        bettingPhasePanel.setLayout(new BorderLayout());

        bettingPhasePanel.add(bettingPhaseLabel);

        bettingLabel=new JLabel("Select your betting amount");
        bettingLabel.setFont(font);

        amountAvailableLabel=new JLabel("Your available amount is :" + "amount");
        amountAvailableLabel.setFont(font);

        labelsPanel.add(bettingPhaseLabel);
        labelsPanel.add(amountAvailableLabel);
        labelsPanel.add(bettingLabel);

        bettingPanel.add(bettingPhasePanel,BorderLayout.PAGE_START);
        bettingPanel.add(labelsPanel,BorderLayout.AFTER_LINE_ENDS);
        backgroundImageLabel.add(bettingPanel);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(onePlayerSelectionFrame.getScreenWidth(),onePlayerSelectionFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
