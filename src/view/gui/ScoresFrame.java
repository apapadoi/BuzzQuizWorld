package view.gui;

import javafx.scene.layout.Border;
import model.player.Player;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScoresFrame extends JFrame{
    private JLabel backgroundImageLabel;
    private IntroFrame introFrame;
    private JPanel scoresPanel;
    private JLabel scoresTextLabel;
    private JPanel scoresTextPanel;
    private JPanel centralPanel;
    private JPanel backButtonPanel;
    private JButton backButton;
    private JPanel sortButtonsPanel;
    private JButton onePlayerSortButton;
    private JButton twoPlayersSortButton;
    private java.util.List<String> playerNames;
    private java.util.List<String> playerScoreOneGame;
    private java.util.List<String> playerScoreTwoGame;


    public ScoresFrame(IntroFrame introFrame) {
        this.playerNames = new ArrayList<>();
        this.playerScoreOneGame = new ArrayList<>();
        this.playerScoreTwoGame = new ArrayList<>();
        this.introFrame = introFrame;
        this.setUpJFrameProperties();
        this.loadData();
        this.setUpBackGround();
        this.setUpScoresTextPanel();
        this.setUpScoresPanel();
        this.setUpCentralPanel();
        this.setUpBackButton();
        this.setUpSortButtonsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }
    // TODO ADD ACTION LISTENERS TO SORT THE SCOREBOARD DEPENDING WHAT THE USER WANTS, BE CAREFUL THIS METHOD IS NOT
    // TODO INVOKED NOWHERE
    private void setUpSortButtonsListeners() {
        this.onePlayerSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void setUpSortButtonsPanel() {
        this.sortButtonsPanel = new JPanel();
        this.sortButtonsPanel.setOpaque(false);
        this.sortButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        this.onePlayerSortButton = new JButton("High Score");
        onePlayerSortButton.setFont(this.introFrame.getFont());
        onePlayerSortButton.setBorderPainted(false);
        onePlayerSortButton.setFocusPainted(false);
        this.onePlayerSortButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        this.twoPlayersSortButton = new JButton("1-1 Wins");
        twoPlayersSortButton.setFont(this.introFrame.getFont());
        twoPlayersSortButton.setBorderPainted(false);
        twoPlayersSortButton.setFocusPainted(false);
        this.twoPlayersSortButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));
        this.sortButtonsPanel.setBorder(BorderFactory.createEmptyBorder((int)(this.introFrame.getHeight()*0.04),
                (int)(this.introFrame.getWidth()*0.038), 0,0));
        this.sortButtonsPanel.add(onePlayerSortButton);
        this.sortButtonsPanel.add(twoPlayersSortButton);
        this.backgroundImageLabel.add(sortButtonsPanel, BorderLayout.LINE_START);
    }

    private void setUpCentralPanel() {
        this.centralPanel = new JPanel();
        this.centralPanel.setOpaque(false);
        this.centralPanel.setLayout(new BorderLayout());
        this.centralPanel.add(this.scoresTextPanel,BorderLayout.PAGE_START);
        this.centralPanel.add(this.scoresPanel,BorderLayout.CENTER);
        this.backgroundImageLabel.add(this.centralPanel,BorderLayout.CENTER);
    }

    private void setUpScoresTextPanel() {
        this.scoresTextPanel = new JPanel();
        this.scoresTextPanel.setLayout(new BorderLayout());
        this.scoresTextPanel.setOpaque(false);
        this.scoresTextPanel.setBorder(BorderFactory.createEmptyBorder(this.introFrame.getScreenHeight()/10,0,0,0));
        this.scoresTextLabel = new JLabel("Scoreboard");
        this.scoresTextLabel.setHorizontalAlignment(JLabel.CENTER);
        this.scoresTextLabel.setOpaque(false);
        this.scoresTextLabel.setFont(this.introFrame.getFont());
        this.scoresTextLabel.setForeground(Color.WHITE);
        this.scoresTextPanel.add(scoresTextLabel,BorderLayout.CENTER);
    }

    private void loadData() {
        playerNames.add("Papster");
        playerNames.add("tasos");
        playerNames.add("petros");
        playerNames.add("rafa");
        playerNames.add("thodwris");
        playerScoreOneGame.add("15");
        playerScoreOneGame.add("18");
        playerScoreOneGame.add("32");
        playerScoreOneGame.add("45");
        playerScoreOneGame.add("500");
        playerScoreTwoGame.add("2");
        playerScoreTwoGame.add("5");
        playerScoreTwoGame.add("8");
        playerScoreTwoGame.add("10");
        playerScoreTwoGame.add("15");

        playerNames.stream().sorted().forEach(playerNames::add);
        playerScoreOneGame.stream().sorted().forEach(playerScoreOneGame::add);
        playerScoreTwoGame.stream().sorted().forEach(playerScoreTwoGame::add);
    }

    private void setUpScoresPanel() {
        this.scoresPanel = new JPanel();
        this.scoresPanel.setOpaque(false);
        this.scoresPanel.setLayout(new GridLayout(playerNames.size()+1,3,0,0));

        JLabel usernamesText = new JLabel("Username");
        usernamesText.setHorizontalAlignment(JLabel.CENTER);
        usernamesText.setFont(this.introFrame.getFont());
        usernamesText.setForeground(Color.WHITE);

        JLabel highScoreText = new JLabel("High Score");
        highScoreText.setHorizontalAlignment(JLabel.CENTER);
        highScoreText.setFont(this.introFrame.getFont());
        highScoreText.setForeground(Color.WHITE);

        JLabel winsText = new JLabel("1-1 Wins");
        winsText.setHorizontalAlignment(JLabel.CENTER);
        winsText.setFont(this.introFrame.getFont());
        winsText.setForeground(Color.WHITE);

        this.scoresPanel.add(usernamesText);
        this.scoresPanel.add(highScoreText);
        this.scoresPanel.add(winsText);

        for(int i =0;i<playerNames.size();i++) {
            JLabel name = new JLabel(playerNames.get(i));
            name.setHorizontalAlignment(JLabel.CENTER);
            name.setFont(this.introFrame.getFont());
            name.setForeground(Color.WHITE);
            JLabel scoreOne = new JLabel(playerScoreOneGame.get(i));
            scoreOne.setHorizontalAlignment(JLabel.CENTER);
            scoreOne.setFont(this.introFrame.getFont());
            scoreOne.setForeground(Color.WHITE);
            JLabel scoreTwo = new JLabel(playerScoreTwoGame.get(i));
            scoreTwo.setHorizontalAlignment(JLabel.CENTER);
            scoreTwo.setFont(this.introFrame.getFont());
            scoreTwo.setForeground(Color.WHITE);

            this.scoresPanel.add(name);
            this.scoresPanel.add(scoreOne);
            this.scoresPanel.add(scoreTwo);
        }
    }

    private void setUpBackButton() {
        this.backButtonPanel = new JPanel();
        this.backButtonPanel.setLayout(new BorderLayout());
        this.backButtonPanel.setBackground(new Color(0,0,0,0));
        this.backgroundImageLabel.setBorder(BorderFactory.createEmptyBorder(0,0,
                this.introFrame.getScreenHeight()/25,this.introFrame.getScreenWidth()/25));

        this.backButton = new JButton("Back");
        backButton.setFont(this.introFrame.getFont());
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        this.backButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        this.backButtonPanel.add(this.backButton, BorderLayout.LINE_END);

        this.backgroundImageLabel.add(backButtonPanel,BorderLayout.PAGE_END);
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel, BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.SCORES_PAGE_BACKGROUND_IMG).
                getImage().getScaledInstance(this.introFrame.getScreenWidth(),this.introFrame.getScreenHeight(),
                java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners() {
        onePlayerSortButton.addActionListener(this.introFrame.getButtonSoundListener());
        twoPlayersSortButton.addActionListener(this.introFrame.getButtonSoundListener());
        backButton.addActionListener(this.introFrame.getButtonSoundListener());
    }
}
