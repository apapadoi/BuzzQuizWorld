package view.gui;

import controller.FrontController;
import controller.requests.NextQuestionRequest;
import controller.requests.SetMaximumPlayersRequest;
import controller.requests.UpdateDataRequest;
import model.player.Player;
import resources.utilResources.Image;
import resources.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the frame for two players gameplay.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 */
public class TwoPlayersFrame extends GameplayFrame {
    private static final TwoPlayersFrame instance = new TwoPlayersFrame();
    private final JLabel backgroundImageLabel;
    private static final int iconMultiplier = 30;
    private JPanel answersPanel;
    private JLabel username1;
    private JLabel score1;
    private JLabel username2;
    private JLabel score2;
    private List<JLabel> answersList;
    private static final HashMap<Integer, List<Integer>> keyEventListHashMap;

    static {
        keyEventListHashMap = new HashMap<>();
        Integer[] keyEventsCodes = {KeyEvent.VK_W,KeyEvent.VK_A,KeyEvent.VK_S,KeyEvent.VK_D,
                                    KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT};
        for(int i=0;i<keyEventsCodes.length;i++) {
            keyEventListHashMap.put(keyEventsCodes[i], new ArrayList<>(List.of(
                    (i>3) ? 1 : 0,
                    i%4
            )));
        }
    }

    public static TwoPlayersFrame getInstance() {
        return instance;
    }

    /**
     * Default constructor.
     */
    private TwoPlayersFrame() {
        UtilGUI.setUpJFrameProperties(frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(frame, Image.TWO_PLAYERS_GAMEMODE_BACKGROUND_IMG);
        FrontController.getInstance().setView(this);
        this.setUpAnswersPanel();
        this.setUpLeftSideIcons();
        this.setUpRightSideIcons();
        this.setUpQuestionsImageLabel();
        this.setUpDataPanel();
        this.setUpKeyListeners();

        FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
        FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                -1, 0));
    }

    private void setUpDataPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(getFirstHalfPanel(),BorderLayout.PAGE_START);
        topPanel.add(getSecondHalfPanel(),BorderLayout.PAGE_END);
        this.backgroundImageLabel.add(topPanel,BorderLayout.PAGE_START);
    }

    private void setUpKeyListeners() {
        TwoPlayersFrame.this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int countWhenPress = count;
                FrontController.getInstance().dispatchRequest(new UpdateDataRequest(
                        keyEventListHashMap.get(e.getKeyCode()).get(0),
                        keyEventListHashMap.get(e.getKeyCode()).get(1),
                        countWhenPress));
                FrontController.getInstance().dispatchRequest(new NextQuestionRequest(
                        TwoPlayersFrame.getInstance()));
            }
        });
    }

    private JPanel getSecondHalfPanel() {
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
        return secondHalfPanel;
    }

    private JPanel getFirstHalfPanel() {
        username1 = UtilGUI.getLabelInstance("");
        score1 = UtilGUI.getLabelInstance("");
        username2 = UtilGUI.getLabelInstance("");
        score2 = UtilGUI.getLabelInstance("");
        JPanel firstHalfPanel = new JPanel();
        firstHalfPanel.setLayout(new GridLayout(1,3,0,20));
        firstHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*25/768,0,
                0,0));
        firstHalfPanel.setOpaque(false);
        firstHalfPanel.add(timerLabel);
        firstHalfPanel.add(questionTextLabel);
        firstHalfPanel.add(roundLabel);
        return firstHalfPanel;

    }

    private void setUpAnswersPanel() {
        answersPanel = new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BorderLayout());
        answersPanel.setBorder(BorderFactory.createEmptyBorder(0,UtilGUI.getScreenWidth()*175/1368,
                UtilGUI.getScreenHeight()*50/768,0));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4,1,15,15));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,0));
        answersList = new ArrayList<>();
        for(int i=0;i<4;i++) {
            answersList.add(UtilGUI.getLabelInstance("",(float)30.0));
            buttonsPanel.add(answersList.get(i));
        }
        this.backgroundImageLabel.add(answersPanel,BorderLayout.WEST);
        this.answersPanel.add(buttonsPanel,BorderLayout.CENTER);
    }

    private void setUpQuestionsImageLabel() {
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
        Image[] key_images = {Image.UP_KEY_IMG, Image.LEFT_KEY_IMG, Image.DOWN_KEY_IMG, Image.RIGHT_KEY_IMG};
        JPanel rightSideIconsPanel = new JPanel();
        rightSideIconsPanel.setOpaque(false);
        rightSideIconsPanel.setLayout(new GridLayout(4,1,0,0));
        rightSideIconsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                UtilGUI.getScreenWidth()*30/1368,0,0));
        JLabel label;
        for(Image image:key_images) {
            label = new JLabel();
            label.setIcon(new ImageIcon(getResizedImage(image)));
            rightSideIconsPanel.add(label);
        }
        this.answersPanel.add(rightSideIconsPanel,BorderLayout.EAST);
    }

    private void setUpLeftSideIcons() {
        Image[] key_images = {Image.W_KEY_IMG, Image.A_KEY_IMG, Image.S_KEY_IMG, Image.D_KEY_IMG};
        JPanel leftSideIconsPanel = new JPanel();
        leftSideIconsPanel.setOpaque(false);
        leftSideIconsPanel.setLayout(new GridLayout(4,1,0,0));
        leftSideIconsPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,UtilGUI.getScreenWidth()*30/1368));
        JLabel label;
        for(Image image:key_images) {
            label = new JLabel();
            label.setIcon(new ImageIcon(getResizedImage(image)));
            leftSideIconsPanel.add(label);
        }
        this.answersPanel.add(leftSideIconsPanel,BorderLayout.WEST);
    }

    private java.awt.Image getResizedImage(Image image) {
        return ImageFactory.createImage(image).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,
                        UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
    }

    @Override
    public void updateUsernames(List<Player> players) {
        username1.setText(players.get(0).getUsername());
        username2.setText(players.get(1).getUsername());
    }

    @Override
    public void updateAnswers(List<String> answers) {
        for(int i=0;i<answersList.size();i++)
            answersList.get(i).setText(answers.get(i));
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

    @Override
    public UI getPreQuestionFrame() {
        return TwoPlayersBettingFrame.getInstance();
    }
}
