package view.gui;

import controller.FrontController;
import controller.requests.PreQuestionRequest;
import controller.requests.UpdateDataRequest;
import javafx.embed.swing.JFXPanel;
import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayersFrame extends JFrame implements GUI{
    private static final TwoPlayersFrame instance = new TwoPlayersFrame();
    private JLabel backgroundImageLabel;
    private JPanel buttonsPanel;
    private JPanel leftSideIconsPanel;
    private JPanel rightSideIconsPanel;
    private static final int iconMultiplier = 30;
    private JLabel questionsImageLabel;
    private JPanel topPanel;
    private java.util.List<JLabel> topPanelLabels;
    private JPanel answersPanel;
    private JLabel timerLabel;
    private JLabel questionTextLabel;
    private JLabel roundIdLabel;
    private JLabel username1;
    private JLabel score1;
    private JLabel gamemode;
    private JLabel difficulty;
    private JLabel category;
    private JLabel username2;
    private JLabel score2;
    private JLabel answer4;
    private JLabel answer1;
    private JLabel answer2;
    private JLabel answer3;
    private boolean hasTimer;
    private final Timer timer;
    private int count = 10000;

    public static TwoPlayersFrame getInstance() {
        return instance;
    }

    private TwoPlayersFrame() {
        new JFXPanel();
        answersPanel = new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BorderLayout());
        answersPanel.setBorder(BorderFactory.createEmptyBorder(0,UtilGUI.getScreenWidth()*175/1368,
                UtilGUI.getScreenHeight()*50/768,0));
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.TWO_PLAYERS_GAMEMODE_BACKGROUND_IMG);

        FrontController.getInstance().setView(this);
        this.setUpButtonsPanel();
        this.setUpLeftSideIcons();
        this.setUpRightSideIcons();
        this.setUpQuestionsImage();
        this.setUpDataPanel();
        this.setUpKeyListeners();
        timer =  new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count<=0) {
                    ((Timer) e.getSource()).stop();
                } else {
                    count -= 100;
                }
                if(hasTimer)
                    TwoPlayersFrame.this.timerLabel.setText(Integer.toString(count));
                else
                    TwoPlayersFrame.this.timerLabel.setText("");
            }
        });
        // TODO remove 0 index and add as a private attribute
        UpdateDataRequest.setMaxPlayers(2);
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1, -1, 0));
    }

    private void setUpDataPanel() {
        this.topPanelLabels = new ArrayList<>();
        this.topPanel = new JPanel();
        this.topPanel.setOpaque(false);
        this.topPanel.setLayout(new BorderLayout());

        this.timerLabel = UtilGUI.getLabelInstance("");
        topPanelLabels.add(timerLabel);

        questionTextLabel = UtilGUI.getLabelInstance("");
        topPanelLabels.add(questionTextLabel);

        roundIdLabel = UtilGUI.getLabelInstance("");
        topPanelLabels.add(roundIdLabel);

        username1 = UtilGUI.getLabelInstance(FrontController.getInstance().getModel().getUsername(0));
        topPanelLabels.add(username1);

        score1 = UtilGUI.getLabelInstance(String.valueOf(FrontController.getInstance().getModel().getScore(0)));
        topPanelLabels.add(score1);

        gamemode = UtilGUI.getLabelInstance("");
        topPanelLabels.add(gamemode);

        difficulty = UtilGUI.getLabelInstance("");
        topPanelLabels.add(difficulty);

        category = UtilGUI.getLabelInstance("");
        topPanelLabels.add(category);

        username2 = UtilGUI.getLabelInstance(FrontController.getInstance().getModel().getUsername(1));
        topPanelLabels.add(username2);

        score2 = UtilGUI.getLabelInstance(String.valueOf(FrontController.getInstance().getModel().getScore(1)));
        topPanelLabels.add(score2);

        JPanel firstHalfPanel = new JPanel();
        firstHalfPanel.setLayout(new GridLayout(1,3,0,20));
        firstHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*25/768,0,
                0,0));
        firstHalfPanel.setOpaque(false);
        firstHalfPanel.add(timerLabel);
        firstHalfPanel.add(questionTextLabel);
        firstHalfPanel.add(roundIdLabel);
        this.topPanel.add(firstHalfPanel,BorderLayout.PAGE_START);


        JPanel secondHalfPanel = new JPanel();
        secondHalfPanel.setLayout(new GridLayout(2,7,0,20));
        secondHalfPanel.setOpaque(false);
        secondHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*50/768,0,
                UtilGUI.getScreenHeight()*40/768,0));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Player 1 :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Score :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Gamemode :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Difficulty :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Category :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Score :"));
        secondHalfPanel.add(UtilGUI.getLabelInstance("Player 2:"));
        secondHalfPanel.add(username1);
        secondHalfPanel.add(score1);
        secondHalfPanel.add(gamemode);
        secondHalfPanel.add(difficulty);
        secondHalfPanel.add(category);
        secondHalfPanel.add(score2);
        secondHalfPanel.add(username2);
        this.topPanel.add(secondHalfPanel,BorderLayout.PAGE_END);
        this.backgroundImageLabel.add(topPanel,BorderLayout.PAGE_START);
    }

    private void setUpKeyListeners() {
        TwoPlayersFrame.this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int countWhenPress = count;
                int keyCode = e.getKeyCode();
                int playerIndex = -1;
                int answerChosenIndex = -1;
                switch(keyCode) {
                    case KeyEvent.VK_W: // player 0 answer 1
                        System.out.println("Player 1 press w ");
                        playerIndex = 0;
                        answerChosenIndex = 0;
                        break;
                    case KeyEvent.VK_A: // player 0 answer 2
                        System.out.println("Player 1 press a ");
                        playerIndex = 0;
                        answerChosenIndex = 1;
                        break;
                    case KeyEvent.VK_S: // player 0 answer 3
                        System.out.println("Player 1 press s ");
                        playerIndex = 0;
                        answerChosenIndex = 2;
                        break;
                    case KeyEvent.VK_D: // player 0 answer 4
                        System.out.println("Player 1 press d ");
                        playerIndex = 0;
                        answerChosenIndex = 3;
                        break;
                    case KeyEvent.VK_UP: // player 1 answer 1
                        System.out.println("player 2 press up key");
                        playerIndex = 1;
                        answerChosenIndex = 0;
                        break;
                    case KeyEvent.VK_LEFT: // player 1 answer 2
                        System.out.println("player 2 press left key");
                        playerIndex = 1;
                        answerChosenIndex = 1;
                        break;
                    case KeyEvent.VK_DOWN: // player 1 answer 3
                        System.out.println("player 2 press down key");
                        playerIndex = 1;
                        answerChosenIndex = 2;
                        break;
                    case KeyEvent.VK_RIGHT: // player 1 answer 4
                        System.out.println("player 2 press right key");
                        playerIndex = 1;
                        answerChosenIndex = 3;
                }
                FrontController.getInstance().dispatchRequest(new UpdateDataRequest(playerIndex,
                            answerChosenIndex, countWhenPress));
                if(UpdateDataRequest.allAnswered()) {
                    TwoPlayersFrame.instance.setVisible(false);
                    FrontController.getInstance().dispatchRequest(new PreQuestionRequest(
                            TwoPlayersFrame.this));
                }
            }
        });
    }

    @Override
    public GUI getPreQuestionFrame() {
        return (GUI)TwoPlayersBettingFrame.getInstance();
    }

    private void setUpQuestionsImage() {
        this.questionsImageLabel = new JLabel();
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*50/768,UtilGUI.getScreenWidth()*175/1368,
                UtilGUI.getScreenHeight()*125/768,UtilGUI.getScreenWidth()*50/1368));
        java.awt.Image resizedImage = ImageFactory.createImage(Image.QUESTION_IMG_TEST_IMG).getImage().
                getScaledInstance((int)(UtilGUI.getScreenWidth()*550/1368),UtilGUI.getScreenHeight()*300/768,java.awt.Image.SCALE_DEFAULT);
        this.questionsImageLabel.setIcon(new ImageIcon(resizedImage));
        panel.add(questionsImageLabel,JLabel.CENTER);
        this.backgroundImageLabel.add(panel,BorderLayout.EAST);
    }

    private void setUpRightSideIcons() {
        rightSideIconsPanel = new JPanel();
        rightSideIconsPanel.setOpaque(false);
        rightSideIconsPanel.setLayout(new GridLayout(4,1,0,0));
        rightSideIconsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                UtilGUI.getScreenWidth()*30/1368,0,0));

        JLabel label = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(Image.UP_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        rightSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.LEFT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        rightSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.DOWN_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        rightSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.RIGHT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        rightSideIconsPanel.add(label);

        this.answersPanel.add(rightSideIconsPanel,BorderLayout.EAST);
    }

    private void setUpLeftSideIcons() {
        leftSideIconsPanel = new JPanel();
        leftSideIconsPanel.setOpaque(false);
        leftSideIconsPanel.setLayout(new GridLayout(4,1,0,0));

       leftSideIconsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,UtilGUI.getScreenWidth()*30/1368));
        JLabel label = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(Image.W_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        leftSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.A_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        leftSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.S_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        leftSideIconsPanel.add(label);

        label = new JLabel();
        resizedImage = ImageFactory.createImage(Image.D_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(resizedImage));
        leftSideIconsPanel.add(label);

        this.answersPanel.add(leftSideIconsPanel,BorderLayout.WEST);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4,1,15,15));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,0));

        answer1 = UtilGUI.getLabelInstance("",(float)30.0);
        buttonsPanel.add(answer1);

        answer2 = UtilGUI.getLabelInstance("",(float)30.0);
        buttonsPanel.add(answer2);

        answer3 = UtilGUI.getLabelInstance("",(float)30.0);
        buttonsPanel.add(answer3);

        answer4 = UtilGUI.getLabelInstance("",(float)30.0);
        buttonsPanel.add(answer4);

        this.backgroundImageLabel.add(answersPanel,BorderLayout.WEST);
        this.answersPanel.add(buttonsPanel,BorderLayout.CENTER);
    }

    @Override
    public void updateAnswers(List<String> answers) {
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
    }

    @Override
    public void updateScore(List<Player> players) {
        this.score1.setText(String.valueOf(players.get(0).getScore()));
        this.score2.setText(String.valueOf(players.get(1).getScore()));
    }

    @Override
    public void updateGamemode(String gamemodeName) {
        this.gamemode.setText(gamemodeName);
    }

    @Override
    public void updateQuestion(String question) {
        this.questionTextLabel.setText(question);
    }

    @Override
    public void updateCategory(Category category) {
        this.category.setText(category.toString());
    }

    @Override
    public void updateRoundId(String id) {
        this.roundIdLabel.setText("Round : " + id);
    }

    @Override
    public void updateTimerLabel(String text) {
        this.timerLabel.setText(text);
    }

    @Override
    public void setHasTimer(boolean b) {
        this.hasTimer = b;
    }

    @Override
    public void restartCount() {
        this.count = 10000;
    }

    @Override
    public void stopTimer() {
        this.timer.stop();
    }

    @Override
    public void startTimer() {
        this.timer.start();
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
