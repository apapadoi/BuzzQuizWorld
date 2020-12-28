package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TwoPlayersBettingFrame extends JFrame {
    private JLabel backgroundImageLabel;
    private Font font;
    private TwoPlayersSelectionFrame twoPlayersSelectionFrame;
    private static final int iconMultiplier = 30;
    private JLabel wKey;
    private JLabel aKey;
    private JLabel sKey;
    private JLabel dKey;
    private JLabel upKey;
    private JLabel downKey;
    private JLabel leftKey;
    private JLabel rightKey;
    private JLabel betAmount1;
    private JLabel betAmount2;
    private JLabel betAmount3;
    private JLabel betAmount4;
    private JPanel bettingPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel amountPanel;
    private JPanel topPanel;
    private JLabel chooseAmountLabel;
    private JLabel amountLabel;
    private JLabel highStakesLabel;


    public TwoPlayersBettingFrame(TwoPlayersSelectionFrame twoPlayersSelectionFrame){
        this.twoPlayersSelectionFrame=twoPlayersSelectionFrame;
        bettingPanel=new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setUpTopPanel();
        this.setUpAmountPanel();
        this.setUpRightSideIcons();
        this.setUpLeftSideIcons();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpAmountPanel() {
        amountPanel = new JPanel();
        amountPanel.setLayout(new GridLayout(4,1,15,15));
        amountPanel.setOpaque(false);
        amountPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,0));


        betAmount1 = new JLabel("250");
        betAmount1.setForeground(Color.WHITE);
        betAmount1.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));

        betAmount1.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount1);

        betAmount2 = new JLabel("500");
        betAmount2.setForeground(Color.WHITE);
        betAmount2.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));
        betAmount2.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount2);

        betAmount3 = new JLabel("750");
        betAmount3.setForeground(Color.WHITE);
        betAmount3.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount3.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount3);

        betAmount4 = new JLabel("1000");
        betAmount4.setForeground(Color.WHITE);
        betAmount4.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount4.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount4);

        bettingPanel.add(amountPanel,BorderLayout.CENTER);
    }

    private void setUpRightSideIcons() {
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridLayout(4,1,0,0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,700));

        upKey = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.UP_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        upKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(upKey);

        leftKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.LEFT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        leftKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(leftKey);

        downKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.DOWN_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        downKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(downKey);

        rightKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.RIGHT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        rightKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(rightKey);

        this.bettingPanel.add(rightPanel,BorderLayout.EAST);
    }

    private void setUpLeftSideIcons() {
        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridLayout(4,1,0,0));

        leftPanel.setBorder(BorderFactory.createEmptyBorder(0,
                700,0,0));
        wKey = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.W_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        wKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(wKey);

        aKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.A_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        aKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(aKey);

        sKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.S_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        sKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(sKey);

        dKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.D_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        dKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(dKey);

        this.bettingPanel.add(leftPanel,BorderLayout.WEST);
    }

    private void setUpTopPanel() {
        topPanel=new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0,800,0,0));

        highStakesLabel=new JLabel("High Stakes");
        highStakesLabel.setForeground(Color.WHITE);
        highStakesLabel.setFont(font);

        chooseAmountLabel=new JLabel("Select your betting amount");
        chooseAmountLabel.setForeground(Color.WHITE);
        chooseAmountLabel.setFont(font);

        amountLabel=new JLabel("Amounts available");
        amountLabel.setFont(font);
        amountLabel.setForeground(Color.WHITE);

        JPanel top=new JPanel();
        JPanel center=new JPanel();
        JPanel bottom=new JPanel();

        top.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());

        top.setBorder(BorderFactory.createEmptyBorder(10,100,0,0));
        top.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        center.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(0,70,0,0));
        bottom.setOpaque(false);

        top.add(highStakesLabel,BorderLayout.CENTER);
        center.add(chooseAmountLabel,BorderLayout.CENTER);
        bottom.add(amountLabel,BorderLayout.CENTER);

        topPanel.add(top);
        topPanel.add(Box.createRigidArea(new Dimension(0,50)));
        topPanel.add(center);
        topPanel.add(Box.createRigidArea(new Dimension(0,60)));
        topPanel.add(bottom);

        bettingPanel.add(topPanel,BorderLayout.NORTH);
        backgroundImageLabel.add(bettingPanel);
    }

    private void setUpButtonListeners() {
    }
    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(twoPlayersSelectionFrame.getScreenWidth(),twoPlayersSelectionFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
