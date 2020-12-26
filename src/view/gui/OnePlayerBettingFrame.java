package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OnePlayerBettingFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private OnePlayerFrame onePlayerFrame;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel bettingPanel;
    private JLabel bettingLabel;
    private JPanel labelsPanel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JLabel amountAvailableLabel;
    private JLabel bettingPhaseLabel;
    private JPanel bettingPhasePanel;
    private JButton bettingAmountButton1;
    private JButton bettingAmountButton2;
    private JButton bettingAmountButton3;
    private JButton bettingAmountButton4;



    public OnePlayerBettingFrame(OnePlayerSelectionFrame onePlayerSelectionFrame){
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
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);

        bettingPhasePanel=new JPanel();
        bettingPhasePanel.setOpaque(false);
        bettingPhasePanel.setLayout(new BorderLayout());
        bettingPhasePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        bettingPhaseLabel=new JLabel("             High Stakes");
        bettingPhaseLabel.setFont(font);
        bettingPhaseLabel.setForeground(Color.WHITE);

        bettingLabel=new JLabel("Select your betting amount");
        bettingLabel.setFont(font);
        bettingLabel.setForeground(Color.WHITE);

        labelsPanel=new JPanel();
        labelsPanel.setOpaque(false);
        labelsPanel.setLayout(new BoxLayout(labelsPanel,BoxLayout.Y_AXIS));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(7,800,0,0));

        labelsPanel.add(bettingPhaseLabel);
        labelsPanel.add(Box.createRigidArea(new Dimension(0,70)));
        labelsPanel.add(bettingLabel);

        bettingPhasePanel.add(labelsPanel,BorderLayout.CENTER);


        centerPanel=new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(150,0,0,220));
        centerPanel.setOpaque(false);
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,200,530,0));
        buttonsPanel.setOpaque(false);
        bettingAmountButton1=new JButton("250");
        bettingAmountButton1.setFont(font);
        bettingAmountButton2=new JButton("500");
        bettingAmountButton2.setFont(font);
        bettingAmountButton3=new JButton("750");
        bettingAmountButton3.setFont(font);
        bettingAmountButton4=new JButton("1000");
        bettingAmountButton4.setFont(font);

        amountAvailableLabel=new JLabel("Amounts Available");
        amountAvailableLabel.setForeground(Color.WHITE);
        amountAvailableLabel.setFont(font);

        bettingAmountButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton1.setPreferredSize(new Dimension(100,60));
        bettingAmountButton2.setPreferredSize(new Dimension(100,60));
        bettingAmountButton3.setPreferredSize(new Dimension(100,60));
        bettingAmountButton4.setPreferredSize(new Dimension(100,60));
        buttonsPanel.add(bettingAmountButton1);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton2);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton3);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton4);

        centerPanel.add(amountAvailableLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,130)));
        centerPanel.add(buttonsPanel);

        bettingPanel.add(centerPanel,BorderLayout.CENTER);
        bettingPanel.add(bettingPhasePanel,BorderLayout.PAGE_START);
        backgroundImageLabel.add(bettingPanel);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG).getImage().
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
