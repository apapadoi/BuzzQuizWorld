package view.gui;

import com.sun.webkit.network.Util;
import controller.FrontController;
import controller.requests.PreQuestionRequest;
import controller.requests.UpdateDataRequest;
import model.Model;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import model.player.Player;
import model.questions.Category;
import resources.utilResources.Image;
import resources.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class OnePlayerFrame extends JFrame implements GUI{
    private JLabel backgroundImageLabel;
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
    private static final OnePlayerFrame instance;
    private final Timer timer;
    private int count = 10000;
    private boolean hasTimer = false;
    private JLabel difficultyLabel;

    static {
        instance = new OnePlayerFrame();
    }
    private void setUpQuestionsPanel(){
        questionsPanel=new JPanel();
        questionsPanel.setLayout(new BorderLayout());
        questionsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.046*UtilGUI.getScreenHeight()),0,
                (int)(0.027*UtilGUI.getScreenHeight()),0));
        questionsPanel.setOpaque(false);

        this.setUpQuestionsPanelData();

        questionsPanel.add(timerPanel,BorderLayout.LINE_START);
        questionsPanel.add(questionsLabel,BorderLayout.CENTER);
        questionsPanel.add(roundPanel,BorderLayout.LINE_END);
    }

    private void setUpQuestionsPanelData(){
        questionsLabel =new JLabel("",SwingConstants.CENTER);
        questionsLabel.setFont(UtilGUI.getCustomFont().deriveFont(100));
        questionsLabel.setForeground(Color.WHITE);

        roundLabel=new JLabel("");
        roundLabel.setFont(UtilGUI.getCustomFont());
        roundLabel.setForeground(Color.WHITE);

        roundPanel=new JPanel();
        roundPanel.setOpaque(false);
        roundPanel.setLayout(new BorderLayout());
        roundPanel.setBorder(BorderFactory.createEmptyBorder(0,0,(int)(0.027*UtilGUI.getScreenHeight()),
                (int)(0.111*UtilGUI.getScreenWidth())));

        roundPanel.add(roundLabel);

        timerLabel=new JLabel("");
        timerLabel.setFont(UtilGUI.getCustomFont());
        timerLabel.setForeground(Color.WHITE);

        timerPanel=new JPanel();
        timerPanel.setOpaque(false);
        timerLabel.setLayout(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.111*UtilGUI.getScreenWidth()),
                (int)(0.027*UtilGUI.getScreenHeight()),0));

        timerPanel.add(timerLabel,BorderLayout.CENTER);
    }

    private void setUpAnswersPanel(){
        answersPanel=new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BoxLayout(answersPanel,BoxLayout.Y_AXIS));
        answersPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.046*UtilGUI.getScreenHeight()),(int)(0.156*UtilGUI.getScreenWidth()),
                (int)(0.157*UtilGUI.getScreenHeight()),(int)(0.052*UtilGUI.getScreenWidth())));

        this.setUpAnswersPanelData();

        answersPanel.add(answersButton1);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*UtilGUI.getScreenHeight()))));
        answersPanel.add(answersButton2);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*UtilGUI.getScreenHeight()))));
        answersPanel.add(answersButton3);
        answersPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.092*UtilGUI.getScreenHeight()))));
        answersPanel.add(answersButton4);
    }

    private void setUpAnswersPanelData(){
        answersButton1= UtilGUI.getButtonInstance("");
        answersButton2= UtilGUI.getButtonInstance("");
        answersButton3= UtilGUI.getButtonInstance("");
        answersButton4= UtilGUI.getButtonInstance("");

        answersButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));

        answersButton1.setPreferredSize(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        answersButton2.setPreferredSize(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        answersButton3.setPreferredSize(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        answersButton4.setPreferredSize(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
    }

    private void setUpLeftPanel(){
        leftPanel=new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*UtilGUI.getScreenHeight()),
                (int)(0.015*UtilGUI.getScreenWidth()),0,0));
        leftPanel.setOpaque(false);

        gamemodeLabel=new JLabel();
        gamemodeLabel.setForeground(Color.WHITE);
        gamemodeLabel.setFont(UtilGUI.getCustomFont().deriveFont(35));

        categoryLabel=new JLabel();
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setFont(UtilGUI.getCustomFont());

        difficultyLabel=new JLabel();
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setFont(UtilGUI.getCustomFont());

        leftPanel.add(gamemodeLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.037*UtilGUI.getScreenHeight()))));
        leftPanel.add(categoryLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.037*UtilGUI.getScreenHeight()))));
        leftPanel.add(difficultyLabel);
    }

    private void setUpBottomPanel(){
        bottomPanel =new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.010*UtilGUI.getScreenWidth()),
                0,(int)(0.010*UtilGUI.getScreenWidth())));
        bottomPanel.setOpaque(false);

        this.setUpBottomPanelData();

        bottomPanel.add(exitPanel,BorderLayout.LINE_END);
        bottomPanel.add(usernamePanel,BorderLayout.LINE_START);
    }

    private void setUpBottomPanelData(){
        usernamePanel=new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel,BoxLayout.Y_AXIS));
        usernamePanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.010*UtilGUI.getScreenWidth()),
                (int)(0.037*UtilGUI.getScreenHeight()),0));
        usernamePanel.setOpaque(false);

        usernameLabel=new JLabel();
        usernameLabel.setFont(UtilGUI.getCustomFont());
        usernameLabel.setForeground(Color.WHITE);

        scoreLabel=new JLabel();
        scoreLabel.setFont(UtilGUI.getCustomFont());
        scoreLabel.setForeground(Color.WHITE);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createRigidArea(new Dimension(0,(int)(0.013*UtilGUI.getScreenHeight()))));
        usernamePanel.add(scoreLabel);

        exitButton= UtilGUI.getButtonInstance("Exit");
        exitButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.004*UtilGUI.getScreenHeight())));


        exitPanel=new JPanel();
        exitPanel.setLayout(new BorderLayout());
        exitPanel.setOpaque(false);
        exitPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.023*UtilGUI.getScreenHeight()),0,(int)(0.023*UtilGUI.getScreenHeight()),
                (int)(0.010*UtilGUI.getScreenWidth())));

        exitPanel.add(exitButton,BorderLayout.CENTER);
    }
    private void connectPanels() {
        onePlayerPanel.add(leftPanel,BorderLayout.LINE_START);
        onePlayerPanel.add(bottomPanel,BorderLayout.PAGE_END);
        onePlayerPanel.add(questionsPanel,BorderLayout.PAGE_START);
        onePlayerPanel.add(answersPanel,BorderLayout.LINE_END);
        backgroundImageLabel.add(onePlayerPanel);
    }

    private OnePlayerFrame(){
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_PAGE_BACKGROUND_IMG);
        onePlayerPanel=new JPanel();
        onePlayerPanel.setOpaque(false);
        onePlayerPanel.setLayout(new BorderLayout());
        this.setUpQuestionsPanel();
        this.setUpAnswersPanel();
        this.setUpLeftPanel();
        this.setUpBottomPanel();
        this.connectPanels();
        this.setUpButtonListeners();
        FrontController.getInstance().setView(this);
        timer =  new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count<=0) {
                    ((Timer) e.getSource()).stop();
                } else {
                    count -= 100;
                }
                if(hasTimer)
                    OnePlayerFrame.this.timerLabel.setText((count/1000.0)+" seconds");
                else
                    OnePlayerFrame.this.timerLabel.setText("");
            }
        });
        // TODO add set max players to one selection frame
        Model.getInstance().setMaxPlayers(1);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1, -1, 0));
    }

    public static OnePlayerFrame getInstance() {
        return instance;
    }

    private void setUpButtonListeners() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createExitButtonFrame();
            }
        });

        ActionListener updateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnePlayerFrame.this.timer.stop();
                int answerIndex = -1;
                JButton buttonPressed = (JButton)e.getSource();
                if(buttonPressed.equals(answersButton1))
                    answerIndex = 0;
                else if(buttonPressed.equals(answersButton2))
                    answerIndex = 1;
                else if(buttonPressed.equals(answersButton3))
                    answerIndex = 2;
                else
                    answerIndex = 3;
                OnePlayerFrame.this.setVisible(false);
                FrontController.getInstance().dispatchRequest(new UpdateDataRequest(0, answerIndex,
                        count));
                FrontController.getInstance().dispatchRequest(new PreQuestionRequest(
                        OnePlayerFrame.this));
            }
        };
        answersButton1.addActionListener(updateListener);
        answersButton2.addActionListener(updateListener);
        answersButton3.addActionListener(updateListener);
        answersButton4.addActionListener(updateListener);
    }

    @Override
    public GUI getPreQuestionFrame() {
        return (GUI)OnePlayerBettingFrame.getInstance();
    }

    private void createExitButtonFrame(){
        JFrame exitFrame=new JFrame();
        exitFrame.setTitle("Exit");
        exitFrame.setSize((int)(0.234*UtilGUI.getScreenWidth()),(int)(0.120*UtilGUI.getScreenHeight()));
        exitFrame.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        exitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exitFrame.setResizable(false);
        exitFrame.setLocationRelativeTo(null);
        JPanel exitPanel=new JPanel();
        JLabel exitLabel=UtilGUI.getLabelInstance("Are you sure you want to exit?");
        exitLabel.setForeground(Color.BLACK);
        JButton yesButton= UtilGUI.getButtonInstance("Yes");
        JButton noButton= UtilGUI.getButtonInstance("No");
        JPanel buttonsPanel=new JPanel();
        JPanel labelsPanel=new JPanel();

        exitPanel.setLayout(new BorderLayout());

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.013*UtilGUI.getScreenHeight()),
                (int)(0.026*UtilGUI.getScreenWidth()),0,(int)(0.026*UtilGUI.getScreenWidth())));

        labelsPanel.setLayout(new BorderLayout());
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.036*UtilGUI.getScreenWidth()),0,0));
        labelsPanel.add(exitLabel,BorderLayout.CENTER);

        yesButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,yesButton.getMinimumSize().height));
        noButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,noButton.getMinimumSize().height));

        yesButton.setPreferredSize(new Dimension((int)(0.002*UtilGUI.getScreenWidth()),(int)(0.004*UtilGUI.getScreenHeight())));
        noButton.setPreferredSize(new Dimension((int)(0.002*UtilGUI.getScreenWidth()),(int)(0.004*UtilGUI.getScreenHeight())));
        buttonsPanel.add(yesButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension((int)(0.015*UtilGUI.getScreenWidth()),0)));
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

    @Override
    public void updateAnswers(List<String> answers) {
        answersButton1.setText(answers.get(0));
        answersButton2.setText(answers.get(1));
        answersButton3.setText(answers.get(2));
        answersButton4.setText(answers.get(3));
    }

    @Override
    public void updateScore(List<Player> players) {
        scoreLabel.setText("Score : "+String.valueOf(players.get(0).getScore()));
    }

    @Override
    public void updateGamemode(String gamemodeName) {
        gamemodeLabel.setText("Gamemode : "+gamemodeName);
    }

    @Override
    public void updateQuestion(String question) {
        StringBuilder string = new StringBuilder("<html>");
        string.append(question);
        string.append("</html>");
        questionsLabel.setText(string.toString());
    }

    @Override
    public void updateCategory(Category category) {
        categoryLabel.setText("Category : "+category.toString());
    }

    @Override
    public void updateRoundId(String id) {
        roundLabel.setText("Round : "+id);
    }

    @Override
    public void setHasTimer(boolean b) { this.hasTimer = b; }

    @Override
    public void restartCount() { this.count = 10000; }

    @Override
    public void stopTimer() { this.timer.stop(); }

    @Override
    public void startTimer() { this.timer.start(); }

    @Override
    public boolean hasMoreThanTwoPlayers() {
        return false;
    }
}
