package view.gui;

import javafx.embed.swing.JFXPanel;
import model.player.Player;
import model.questions.Category;
import model.questions.Difficulty;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TwoPlayersGamemodeFrame extends JFrame {
    private JLabel backgroundImageLabel;
    private JPanel buttonsPanel;
    private JPanel leftSideIconsPanel;
    private JPanel rightSideIconsPanel;
    private static final int iconMultiplier = 30;
    private JLabel questionsImageLabel;
    private JPanel topPanel;
    private java.util.List<JLabel> topPanelLabels;
    private JPanel answersPanel;

    public TwoPlayersGamemodeFrame() {
        answersPanel = new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BorderLayout());
        answersPanel.setBorder(BorderFactory.createEmptyBorder(0,UtilGUI.getScreenWidth()*175/1368,
                UtilGUI.getScreenHeight()*50/768,0));
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setUpButtonsPanel();
        this.setUpLeftSideIcons();
        this.setUpRightSideIcons();
        this.setUpQuestionsImage();
        this.setUpDataPanel();
        this.setVisible(true);
    }

    private void setUpDataPanel() {
        this.topPanelLabels = new ArrayList<>();
        this.topPanel = new JPanel();
        this.topPanel.setOpaque(false);
        this.topPanel.setLayout(new BorderLayout());
        Player player1 = new Player("Pitbull",1500,350);
        Player player2 = new Player("z3",1000,500);
        JLabel timer = constructCustomJLabel("30 seconds");
        topPanelLabels.add(timer);
        JLabel questionText = constructCustomJLabel("<html>What is the name of the video game in the picture;</html>");
        topPanelLabels.add(questionText);
        JLabel roundId = constructCustomJLabel("Round : 3");
        topPanelLabels.add(roundId);
        JLabel username1 = constructCustomJLabel(player1.getUsername());
        topPanelLabels.add(username1);
        JLabel score1 = constructCustomJLabel(String.valueOf(player1.getScore()));
        topPanelLabels.add(score1);
        JLabel gamemode = constructCustomJLabel("Point Builder");
        topPanelLabels.add(gamemode);
        JLabel difficulty = constructCustomJLabel(Difficulty.Medium.toString());
        topPanelLabels.add(difficulty);
        JLabel category = constructCustomJLabel(Category.Technology.toString());
        topPanelLabels.add(category);
        JLabel username2 = constructCustomJLabel(player2.getUsername());
        topPanelLabels.add(username2);
        JLabel score2 = constructCustomJLabel(String.valueOf(player2.getScore()));
        topPanelLabels.add(score2);
        for(JLabel label:topPanelLabels)
            System.out.println(label.getText());
        JPanel firstHalfPanel = new JPanel();
        firstHalfPanel.setLayout(new GridLayout(1,3,0,20));
        firstHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*25/768,0,
                0,0));
        firstHalfPanel.setOpaque(false);
        firstHalfPanel.add(timer);
        firstHalfPanel.add(questionText);
        firstHalfPanel.add(roundId);
        this.topPanel.add(firstHalfPanel,BorderLayout.PAGE_START);

        JPanel secondHalfPanel = new JPanel();
        secondHalfPanel.setLayout(new GridLayout(2,7,0,20));
        secondHalfPanel.setOpaque(false);
        secondHalfPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*50/768,0,
                UtilGUI.getScreenHeight()*40/768,0));
        secondHalfPanel.add(constructCustomJLabel("Player 1 :"));
        secondHalfPanel.add(constructCustomJLabel("Score :"));
        secondHalfPanel.add(constructCustomJLabel("Gamemode :"));
        secondHalfPanel.add(constructCustomJLabel("Difficulty :"));
        secondHalfPanel.add(constructCustomJLabel("Category :"));
        secondHalfPanel.add(constructCustomJLabel("Score :"));
        secondHalfPanel.add(constructCustomJLabel("Player 2:"));
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

    private JLabel constructCustomJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(UtilGUI.getCustomFont().deriveFont((float)25.0));
        label.setForeground(Color.WHITE);

        return label;
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


        JLabel label = new JLabel("Cyberpunk");
        label.setForeground(Color.WHITE);
        label.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));

        label.setHorizontalAlignment(JLabel.CENTER);
        buttonsPanel.add(label);

        label = new JLabel("League Of Legends");
        label.setForeground(Color.WHITE);
        label.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));
        label.setHorizontalAlignment(JLabel.CENTER);
        buttonsPanel.add(label);

        label = new JLabel("Minecraft");
        label.setForeground(Color.WHITE);
        label.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        label.setHorizontalAlignment(JLabel.CENTER);
        buttonsPanel.add(label);

        label = new JLabel("Call Of Duty");
        label.setForeground(Color.WHITE);
        label.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        label.setHorizontalAlignment(JLabel.CENTER);
        buttonsPanel.add(label);

        this.backgroundImageLabel.add(answersPanel,BorderLayout.WEST);
        this.answersPanel.add(buttonsPanel,BorderLayout.CENTER);
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
        new JFXPanel();
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.TWO_PLAYERS_GAMEMODE_BACKGROUND_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    public static void main(String[] args) {
        new TwoPlayersGamemodeFrame();
    }
}
