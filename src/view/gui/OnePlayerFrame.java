package view.gui;

import controller.ButtonSoundListener;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnePlayerFrame extends JFrame implements GUI{
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
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(50,0,30,0));
        questionsPanel.setOpaque(false);

        questionsLabel =new JLabel("<html>What is the most visited country of<br/>all four given?</html>",SwingConstants.CENTER);
        questionsLabel.setFont(UtilGUI.getCustomFont().deriveFont(100));
        questionsLabel.setForeground(Color.WHITE);

        roundLabel=new JLabel("Round 1");
        roundLabel.setFont(UtilGUI.getCustomFont());
        roundLabel.setForeground(Color.WHITE);

        roundPanel=new JPanel();
        roundPanel.setOpaque(false);
        roundPanel.setLayout(new BorderLayout());
        roundPanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,215));

        roundPanel.add(roundLabel);

        timerLabel=new JLabel("30 seconds");
        timerLabel.setFont(UtilGUI.getCustomFont());
        timerLabel.setForeground(Color.WHITE);

        timerPanel=new JPanel();
        timerPanel.setOpaque(false);
        timerLabel.setLayout(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,215,30,0));

        timerPanel.add(timerLabel,BorderLayout.CENTER);
        questionsPanel.add(timerPanel,BorderLayout.LINE_START);
        questionsPanel.add(questionsLabel,BorderLayout.CENTER);
        questionsPanel.add(roundPanel,BorderLayout.LINE_END);

        answersPanel=new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BoxLayout(answersPanel,BoxLayout.Y_AXIS));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(50,300,170,100));
        answersButton1= UtilGUI.getButtonInstance("Spain");
        answersButton2= UtilGUI.getButtonInstance("France");
        answersButton3= UtilGUI.getButtonInstance("Fiji");
        answersButton4= UtilGUI.getButtonInstance("Finland");


        answersButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton1.setPreferredSize(new Dimension(300,60));
        answersButton2.setPreferredSize(new Dimension(300,60));
        answersButton3.setPreferredSize(new Dimension(300,60));
        answersButton4.setPreferredSize(new Dimension(300,60));
        answersPanel.add(answersButton1);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton2);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton3);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton4);


        usernamePanel=new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel,BoxLayout.Y_AXIS));
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(0,20,40,0));
        usernamePanel.setOpaque(false);

        usernameLabel=new JLabel("Username: papster");
        usernameLabel.setFont(UtilGUI.getCustomFont());
        usernameLabel.setForeground(Color.WHITE);

        scoreLabel=new JLabel("Score: 1000");
        scoreLabel.setFont(UtilGUI.getCustomFont());
        scoreLabel.setForeground(Color.WHITE);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createRigidArea(new Dimension(0,15)));
        usernamePanel.add(scoreLabel);

        bottomPanel =new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        bottomPanel.setOpaque(false);

        exitButton= UtilGUI.getButtonInstance("Exit");
        exitButton.setPreferredSize(new Dimension(175,5));

        exitPanel=new JPanel();
        exitPanel.setLayout(new BorderLayout());
        exitPanel.setOpaque(false);
        exitPanel.setBorder(BorderFactory.createEmptyBorder(25,0,25,20));

        exitPanel.add(exitButton,BorderLayout.CENTER);

        leftPanel=new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(200,30,0,0));
        leftPanel.setOpaque(false);

        gamemodeLabel=new JLabel("Gamemode: PointBuilder");
        gamemodeLabel.setForeground(Color.WHITE);
        gamemodeLabel.setFont(UtilGUI.getCustomFont().deriveFont(35));

        categoryLabel=new JLabel("Category: Sports");
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(UtilGUI.getCustomFont());

        leftPanel.add(gamemodeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
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
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_PAGE_BACKGROUND_IMG);
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExitButtonFrame();
            }
        });
    }
    private void createExitButtonFrame(){
        JFrame exitFrame=new JFrame();
        exitFrame.setTitle("Exit");
        exitFrame.setSize(450,130);
        exitFrame.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exitFrame.setResizable(false);
        exitFrame.setLocationRelativeTo(null);
        JPanel exitPanel=new JPanel();
        JLabel exitLabel=new JLabel("Are you sure you want to exit?");
        JButton yesButton= UtilGUI.getButtonInstance("Yes");
        JButton noButton= UtilGUI.getButtonInstance("No");
        JPanel buttonsPanel=new JPanel();
        JPanel labelsPanel=new JPanel();

        exitLabel.setFont(UtilGUI.getCustomFont());
        exitLabel.setForeground(Color.BLACK);

        exitPanel.setLayout(new BorderLayout());

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(15,50,0,50));

        labelsPanel.setLayout(new BorderLayout());
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(0,70,0,0));
        labelsPanel.add(exitLabel,BorderLayout.CENTER);

        yesButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,yesButton.getMinimumSize().height));
        noButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,noButton.getMinimumSize().height));

        yesButton.setPreferredSize(new Dimension(5,5));
        noButton.setPreferredSize(new Dimension(5,5));
        buttonsPanel.add(yesButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30,0)));
        buttonsPanel.add(noButton);

        exitPanel.add(buttonsPanel,BorderLayout.CENTER);
        exitPanel.add(labelsPanel,BorderLayout.PAGE_START);

        exitFrame.add(exitPanel);
        exitFrame.setVisible(true);

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
}
