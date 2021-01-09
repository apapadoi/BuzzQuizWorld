package view.gui;

import controller.FrontController;
import controller.requests.NextQuestionRequest;
import controller.requests.SetMaximumPlayersRequest;
import controller.requests.UpdateDataRequest;
import javafx.embed.swing.JFXPanel;
import model.player.Player;
import resources.utilResources.Image;
import resources.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayersFrame extends GameplayFrame {
    private static final TwoPlayersFrame instance = new TwoPlayersFrame();
    private final JLabel backgroundImageLabel;
    private static final int iconMultiplier = 30;
    private final JPanel answersPanel;
    private JLabel username1;
    private JLabel score1;
    private JLabel username2;
    private JLabel score2;
    private JLabel answer4;
    private JLabel answer1;
    private JLabel answer2;
    private JLabel answer3;

    public static TwoPlayersFrame getInstance() {
        return instance;
    }

    private TwoPlayersFrame() {
        // TODO remove this
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
        // TODO remove 0 index and add as a private attribute
        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1, 0));
    }

    private void setUpDataPanel() {
        // TODO remove topPanelLabels
        List<JLabel> topPanelLabels = new ArrayList<>();
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        topPanelLabels.add(timerLabel);

        topPanelLabels.add(questionTextLabel);

        topPanelLabels.add(roundLabel);

        username1 = UtilGUI.getLabelInstance("");
        topPanelLabels.add(username1);

        score1 = UtilGUI.getLabelInstance("");
        topPanelLabels.add(score1);

        topPanelLabels.add(gamemode);

        topPanelLabels.add(difficultyLabel);

        topPanelLabels.add(categoryLabel);

        username2 = UtilGUI.getLabelInstance("");
        topPanelLabels.add(username2);

        score2 = UtilGUI.getLabelInstance("");
        topPanelLabels.add(score2);

        JPanel firstHalfPanel = new JPanel();
        firstHalfPanel.setLayout(new GridLayout(1,3,0,20));
        firstHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*25/768,0,
                0,0));
        firstHalfPanel.setOpaque(false);
        firstHalfPanel.add(timerLabel);
        firstHalfPanel.add(questionTextLabel);
        firstHalfPanel.add(roundLabel);
        topPanel.add(firstHalfPanel,BorderLayout.PAGE_START);


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
        secondHalfPanel.add(difficultyLabel);
        secondHalfPanel.add(categoryLabel);
        secondHalfPanel.add(score2);
        secondHalfPanel.add(username2);
        topPanel.add(secondHalfPanel,BorderLayout.PAGE_END);
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
                FrontController.getInstance().dispatchRequest(new NextQuestionRequest(
                        TwoPlayersFrame.getInstance()));
            }
        });
    }

    @Override
    public UI getPreQuestionFrame() {
        return TwoPlayersBettingFrame.getInstance();
    }

    private void setUpQuestionsImage() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*50/768,
                UtilGUI.getScreenWidth()*175/1368,
                UtilGUI.getScreenHeight()*125/768,UtilGUI.getScreenWidth()*50/1368));
        panel.add(questionsImageLabel,JLabel.CENTER);
        this.backgroundImageLabel.add(panel,BorderLayout.EAST);
    }

    private void setUpRightSideIcons() {
        JPanel rightSideIconsPanel = new JPanel();
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
        JPanel leftSideIconsPanel = new JPanel();
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
        JPanel buttonsPanel = new JPanel();
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
    public void updateUsernames(List<Player> players) {
        username1.setText(players.get(0).getUsername());
        username2.setText(players.get(1).getUsername());
    }

    @Override
    public void updateAnswers(List<String> answers) {
        answer1.setText(answers.get(0));
        answer2.setText(answers.get(1));
        answer3.setText(answers.get(2));
        answer4.setText(answers.get(3));
    }

    @Override
    public void updateScores(List<Player> players) {
        this.score1.setText(String.valueOf(players.get(0).getScore()));
        this.score2.setText(String.valueOf(players.get(1).getScore()));
    }

    @Override
    public boolean hasMoreThanTwoPlayers() {
        return true;
    }
}
