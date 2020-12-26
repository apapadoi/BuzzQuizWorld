package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OnePlayerFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel onePlayerPanel;
    private JPanel questionsPanel;
    private JLabel questionsLabel;
    private JLabel roundLabel;
    private JPanel roundPanel;
    private JLabel timerLabel;
    private JPanel timerPanel;
    private JPanel answersPanel;
    private JButton answersButton1;
    private JButton answersButton2;
    private JButton answersButton3;
    private JButton answersButton4;
    private JLabel usernameLabel;
    private JPanel usernamePanel;
    private JLabel scoreLabel;
    private JPanel bottomPanel;
    private JPanel exitPanel;
    private JButton exitButton;
    private JPanel leftPanel;
    private JLabel gamemodeLabel;
    private JLabel categoryLabel;


    private void setComponentsPanel() {
        onePlayerPanel=new JPanel();
        onePlayerPanel.setOpaque(false);
        onePlayerPanel.setLayout(new BorderLayout());

        questionsPanel=new JPanel();
        questionsPanel.setLayout(new BorderLayout());
        questionsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.046*onePlayerSelectionFrame.getScreenHeight()),0,
                (int)(0.027*onePlayerSelectionFrame.getScreenHeight()),0));
        questionsPanel.setOpaque(false);

        questionsLabel =new JLabel("<html>What is the most visited country of<br/>all four given?</html>",SwingConstants.CENTER);
        questionsLabel.setFont(font.deriveFont(100));
        questionsLabel.setForeground(Color.WHITE);

        roundLabel=new JLabel("Round 1");
        roundLabel.setFont(font);
        roundLabel.setForeground(Color.WHITE);

        roundPanel=new JPanel();
        roundPanel.setOpaque(false);
        roundPanel.setLayout(new BorderLayout());
        roundPanel.setBorder(BorderFactory.createEmptyBorder(0,0,(int)(0.027*onePlayerSelectionFrame.getScreenHeight()),
                (int)(0.111*onePlayerSelectionFrame.getScreenWidth())));

        roundPanel.add(roundLabel);

        timerLabel=new JLabel("30 seconds");
        timerLabel.setFont(font);
        timerLabel.setForeground(Color.WHITE);

        timerPanel=new JPanel();
        timerPanel.setOpaque(false);
        timerLabel.setLayout(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.111*onePlayerSelectionFrame.getScreenWidth()),
                (int)(0.027*onePlayerSelectionFrame.getScreenHeight()),0));

        timerPanel.add(timerLabel,BorderLayout.CENTER);
        questionsPanel.add(timerPanel,BorderLayout.LINE_START);
        questionsPanel.add(questionsLabel,BorderLayout.CENTER);
        questionsPanel.add(roundPanel,BorderLayout.LINE_END);

        answersPanel=new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BoxLayout(answersPanel,BoxLayout.Y_AXIS));
        answersPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.046*onePlayerSelectionFrame.getScreenHeight()),(int)(0.156*onePlayerSelectionFrame.getScreenWidth()),
                (int)(0.157*onePlayerSelectionFrame.getScreenHeight()),(int)(0.052*onePlayerSelectionFrame.getScreenWidth())));
        answersButton1=new JButton("Spain");
        answersButton1.setFont(font);
        answersButton2=new JButton("France");
        answersButton2.setFont(font);
        answersButton3=new JButton("Fiji");
        answersButton3.setFont(font);
        answersButton4=new JButton("Finland");
        answersButton4.setFont(font);


        answersButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton1.setPreferredSize(new Dimension((int)(0.156*onePlayerSelectionFrame.getScreenWidth()),(int)(0.055*onePlayerSelectionFrame.getScreenHeight())));
        answersButton2.setPreferredSize(new Dimension((int)(0.156*onePlayerSelectionFrame.getScreenWidth()),(int)(0.055*onePlayerSelectionFrame.getScreenHeight())));
        answersButton3.setPreferredSize(new Dimension((int)(0.156*onePlayerSelectionFrame.getScreenWidth()),(int)(0.055*onePlayerSelectionFrame.getScreenHeight())));
        answersButton4.setPreferredSize(new Dimension((int)(0.156*onePlayerSelectionFrame.getScreenWidth()),(int)(0.055*onePlayerSelectionFrame.getScreenHeight())));
        answersPanel.add(answersButton1);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*onePlayerSelectionFrame.getScreenHeight()))));
        answersPanel.add(answersButton2);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*onePlayerSelectionFrame.getScreenHeight()))));
        answersPanel.add(answersButton3);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*onePlayerSelectionFrame.getScreenHeight()))));
        answersPanel.add(answersButton4);


        usernamePanel=new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel,BoxLayout.Y_AXIS));
            usernamePanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.010*onePlayerSelectionFrame.getScreenWidth()),
                    (int)(0.037*onePlayerSelectionFrame.getScreenHeight()),0));
        usernamePanel.setOpaque(false);

        usernameLabel=new JLabel("Username: papster");
        usernameLabel.setFont(font);
        usernameLabel.setForeground(Color.WHITE);

        scoreLabel=new JLabel("Score: 1000");
        scoreLabel.setFont(font);
        scoreLabel.setForeground(Color.WHITE);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createRigidArea(new Dimension(0,(int)(0.013*onePlayerSelectionFrame.getScreenHeight()))));
        usernamePanel.add(scoreLabel);

        bottomPanel =new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.010*onePlayerSelectionFrame.getScreenWidth()),
                0,(int)(0.010*onePlayerSelectionFrame.getScreenWidth())));
        bottomPanel.setOpaque(false);

        exitButton=new JButton("Exit");
        exitButton.setFont(font);
        exitButton.setPreferredSize(new Dimension((int)(0.091*onePlayerSelectionFrame.getScreenWidth()),(int)(0.004*onePlayerSelectionFrame.getScreenHeight())));

        exitPanel=new JPanel();
        exitPanel.setLayout(new BorderLayout());
        exitPanel.setOpaque(false);
        exitPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.023*onePlayerSelectionFrame.getScreenHeight()),0,(int)(0.023*onePlayerSelectionFrame.getScreenHeight()),
                (int)(0.010*onePlayerSelectionFrame.getScreenWidth())));

        exitPanel.add(exitButton,BorderLayout.CENTER);

        leftPanel=new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*onePlayerSelectionFrame.getScreenHeight()),
                (int)(0.015*onePlayerSelectionFrame.getScreenWidth()),0,0));
        leftPanel.setOpaque(false);

        gamemodeLabel=new JLabel("Gamemode: PointBuilder");
        gamemodeLabel.setForeground(Color.WHITE);
        gamemodeLabel.setFont(font.deriveFont(35));

        categoryLabel=new JLabel("Category: Sports");
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(font);

        leftPanel.add(gamemodeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.037*onePlayerSelectionFrame.getScreenHeight()))));
        leftPanel.add(categoryLabel);


        bottomPanel.add(exitPanel,BorderLayout.LINE_END);
        bottomPanel.add(usernamePanel,BorderLayout.LINE_START);


        onePlayerPanel.add(leftPanel,BorderLayout.LINE_START);
        onePlayerPanel.add(bottomPanel,BorderLayout.PAGE_END);
        onePlayerPanel.add(questionsPanel,BorderLayout.PAGE_START);
        onePlayerPanel.add(answersPanel,BorderLayout.LINE_END);
        backgroundImageLabel.add(onePlayerPanel);
    }


    public OnePlayerFrame(OnePlayerSelectionFrame onePlayerSelectionFrame){
        this.onePlayerSelectionFrame=onePlayerSelectionFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
        exitButton.addActionListener(this.getButtonSoundListener());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExitButtonFrame();
            }
        });

        answersButton1.addActionListener(this.getButtonSoundListener());
        answersButton2.addActionListener(this.getButtonSoundListener());
        answersButton3.addActionListener(this.getButtonSoundListener());
        answersButton4.addActionListener(this.getButtonSoundListener());

    }
    private void createExitButtonFrame(){
        JFrame exitFrame=new JFrame();
        exitFrame.setTitle("Exit");
        exitFrame.setSize((int)(0.234*onePlayerSelectionFrame.getScreenWidth()),(int)(0.120*onePlayerSelectionFrame.getScreenHeight()));
        exitFrame.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exitFrame.setResizable(false);
        exitFrame.setLocationRelativeTo(null);
        JPanel exitPanel=new JPanel();
        JLabel exitLabel=new JLabel("Are you sure you want to exit?");
        JButton yesButton=new JButton("Yes");
        JButton noButton=new JButton("No");
        JPanel buttonsPanel=new JPanel();
        JPanel labelsPanel=new JPanel();

        yesButton.setFont(font);
        noButton.setFont(font);

        exitLabel.setFont(font);
        exitLabel.setForeground(Color.BLACK);

        exitPanel.setLayout(new BorderLayout());

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.013*onePlayerSelectionFrame.getScreenHeight()),
                (int)(0.026*onePlayerSelectionFrame.getScreenWidth()),0,(int)(0.026*onePlayerSelectionFrame.getScreenWidth())));

        labelsPanel.setLayout(new BorderLayout());
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.036*onePlayerSelectionFrame.getScreenWidth()),0,0));
        labelsPanel.add(exitLabel,BorderLayout.CENTER);

        yesButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,yesButton.getMinimumSize().height));
        noButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,noButton.getMinimumSize().height));

        yesButton.setPreferredSize(new Dimension((int)(0.002*onePlayerSelectionFrame.getScreenWidth()),(int)(0.004*onePlayerSelectionFrame.getScreenHeight())));
        noButton.setPreferredSize(new Dimension((int)(0.002*onePlayerSelectionFrame.getScreenWidth()),(int)(0.004*onePlayerSelectionFrame.getScreenHeight())));
        buttonsPanel.add(yesButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension((int)(0.015*onePlayerSelectionFrame.getScreenWidth()),0)));
        buttonsPanel.add(noButton);

        exitPanel.add(buttonsPanel,BorderLayout.CENTER);
        exitPanel.add(labelsPanel,BorderLayout.PAGE_START);


        exitFrame.add(exitPanel);
        exitFrame.setVisible(true);

        yesButton.addActionListener(this.getButtonSoundListener());
        noButton.addActionListener(this.getButtonSoundListener());

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IntroFrame introFrame=new IntroFrame();
                exitFrame.setVisible(false);
                OnePlayerFrame.this.setVisible(false);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame.setVisible(false);
            }
        });
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

    public int getScreenWidth(){
        return onePlayerSelectionFrame.getScreenWidth();
    }

    public int getScreenHeight(){
        return onePlayerSelectionFrame.getScreenHeight();
    }

    public ActionListener getButtonSoundListener() { return onePlayerSelectionFrame.getButtonSoundListener(); }

}
