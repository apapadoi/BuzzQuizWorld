package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TwoPlayersFrame extends JFrame {
    private PlayFrame playFrame;
    private Font font;
    private JLabel logoImageLabel;
    private int screenWidth;
    private int screenHeight;
    private JLabel backgroundImageLabel;
    private IntroFrame introFrame;
    private JPanel twoPlayersPanel;
    private JPanel backPanel;
    private JButton backButton;
    private JPanel componentsPanel1;
    private JPanel componentsPanel2;
    private JButton confirmButton;
    private JComboBox roundSelectionBox;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};



    public TwoPlayersFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
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
        twoPlayersPanel=new JPanel();
        twoPlayersPanel.setLayout(new BorderLayout());
        twoPlayersPanel.setOpaque(false);

        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);

        backButton=new JButton("Back");
        backButton.setFont(font);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(175,40));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,500,15,15));

        componentsPanel1=new JPanel();
        componentsPanel1.setLayout(new GridLayout(1,3,250,0));
        componentsPanel1.setOpaque(false);
        componentsPanel1.setBorder(BorderFactory.createEmptyBorder(450,150,450,150));

        usernameField1=new JTextField("Enter username:");
        usernameField1.setHorizontalAlignment(JTextField.CENTER);
        usernameField1.setFont(font);

        usernameField2=new JTextField("Enter username:");
        usernameField2.setHorizontalAlignment(JTextField.CENTER);
        usernameField2.setFont(font);

        roundSelectionBox=new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(font);

        confirmButton=new JButton("Confirm");
        confirmButton.setFont(font);
        confirmButton.setBorderPainted(false);
        confirmButton.setFocusPainted(false);

        componentsPanel1.add(usernameField1);
        componentsPanel1.add(roundSelectionBox);
        componentsPanel1.add(usernameField2);


        twoPlayersPanel.add(componentsPanel1,BorderLayout.CENTER);
        twoPlayersPanel.add(backPanel,BorderLayout.PAGE_END);
        backgroundImageLabel.add(twoPlayersPanel);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(playFrame.getScreenWidth(),playFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
