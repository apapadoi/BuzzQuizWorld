package view.gui;

import controller.FrontController;
import controller.requests.LoadScoresRequest;
import model.player.Player;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the frame where the high scores will appear. The high scores can be sorted in one versus one wins and single player wins.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 */
public class ScoresFrame extends JFrame implements UI {
    private final JLabel backgroundImageLabel;
    private final IntroFrame introFrame;
    private JPanel scoresPanel;
    private JPanel scoresTextPanel;
    private JButton backButton;
    private JButton onePlayerSortButton;
    private JButton twoPlayersSortButton;
    private java.util.List<Player> players;
    private final java.util.List<JLabel> scoresLabels;

    /**
     * Default constructor
     * @param introFrame
     */
    public ScoresFrame(IntroFrame introFrame) {
        this.players = new ArrayList<>();
        this.scoresLabels = new ArrayList<>();
        this.introFrame = introFrame;
        UtilGUI.setUpJFrameProperties(this);
        FrontController.getInstance().dispatchRequest(new LoadScoresRequest(this));
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.SCORES_PAGE_BACKGROUND_IMG);
        this.setUpScoresTextPanel();
        this.setUpScoresPanel();
        this.setUpCentralPanel();
        this.setUpBackButton();
        this.setUpSortButtonsPanel();
        this.setUpButtonListeners();
        this.setUpSortButtonsListeners();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    /**
     * This method set listeners for the buttons to sort the high scores to 1v1 wins or single player wins.
     */
    private void setUpSortButtonsListeners() {
        this.onePlayerSortButton.addActionListener(e -> {
            players.sort((o1, o2) -> -1*Integer.compare(o1.getScore(),o2.getScore()));
            updatedScoreLabels();
        });
        this.twoPlayersSortButton.addActionListener(e -> {
            players.sort((o1, o2) -> -1*Integer.compare(o1.getWins(),o2.getWins()));
            updatedScoreLabels();
        });
    }

    /**
     * This method updates scores.
     */
    private void updatedScoreLabels() {
        for(int i = 0; i< scoresLabels.size()/3; i++) {
            scoresLabels.get(3*i).setText(players.get(i).getUsername());
            scoresLabels.get(3*i+1).setText(String.valueOf(players.get(i).getScore()));
            scoresLabels.get(3*i+2).setText(String.valueOf(players.get(i).getWins()));
        }
    }

    /**
     * This method creates the sorting buttons panel.
     */
    private void setUpSortButtonsPanel() {
        JPanel sortButtonsPanel = new JPanel();
        sortButtonsPanel.setOpaque(false);
        sortButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        this.onePlayerSortButton = UtilGUI.getButtonInstance("High Score");
        this.onePlayerSortButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        this.twoPlayersSortButton = UtilGUI.getButtonInstance("1-1 Wins");
        this.twoPlayersSortButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        sortButtonsPanel.setBorder(BorderFactory.createEmptyBorder((int)(this.introFrame.getHeight()*0.04),
                (int)(this.introFrame.getWidth()*0.038), 0,0));
        sortButtonsPanel.add(onePlayerSortButton);
        sortButtonsPanel.add(twoPlayersSortButton);
        this.backgroundImageLabel.add(sortButtonsPanel, BorderLayout.WEST);
    }

    /**
     * This method creates center panel for the frame.
     */
    private void setUpCentralPanel() {
        JPanel centralPanel = new JPanel();
        centralPanel.setOpaque(false);
        centralPanel.setLayout(new BorderLayout());
        centralPanel.add(this.scoresTextPanel,BorderLayout.PAGE_START);
        centralPanel.add(this.scoresPanel,BorderLayout.CENTER);
        this.backgroundImageLabel.add(centralPanel,BorderLayout.CENTER);
    }

    /**
     * This method creates the high scores text panel.
     */
    private void setUpScoresTextPanel() {
        this.scoresTextPanel = new JPanel();
        this.scoresTextPanel.setLayout(new BorderLayout());
        this.scoresTextPanel.setOpaque(false);
        this.scoresTextPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/10,0,0,0));
        JLabel scoresTextLabel = new JLabel("Scoreboard");
        scoresTextLabel.setHorizontalAlignment(JLabel.CENTER);
        scoresTextLabel.setOpaque(false);
        scoresTextLabel.setFont(UtilGUI.getCustomFont());
        scoresTextLabel.setForeground(Color.WHITE);
        this.scoresTextPanel.add(scoresTextLabel,BorderLayout.CENTER);
    }

    /**
     * @see UI
     * @param players
     */
    @Override
    public void updatePlayers(List<Player> players) {
        if(players==null)
            return;
        this.players = players;
        this.players.sort((o1, o2) -> -1*Integer.compare(o1.getScore(),o2.getScore()));
    }

    /**
     * This method creates scores panel.
     */
    private void setUpScoresPanel() {
        JLabel label;
        this.scoresPanel = new JPanel();
        this.scoresPanel.setOpaque(false);
        this.scoresPanel.setLayout(new GridLayout(players.size()+1,3,0,0));
        this.scoresPanel.add(UtilGUI.getLabelInstance("Username"));
        this.scoresPanel.add(UtilGUI.getLabelInstance("High Score"));
        this.scoresPanel.add(UtilGUI.getLabelInstance("1-1 Wins"));
        for(Player player : this.players) {
            label = UtilGUI.getLabelInstance(player.getUsername());
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
            label = UtilGUI.getLabelInstance(String.valueOf(player.getScore()));
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
            label = UtilGUI.getLabelInstance(String.valueOf(player.getWins()));
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
        }
    }

    /**
     * This method creates the back button (bottom panel) panel.
     */
    // TODO REMOVE THIS
    private void setUpBackButton() {
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(new Color(0,0,0,0));
        this.backgroundImageLabel.setBorder(BorderFactory.createEmptyBorder(0,0,
                UtilGUI.getScreenHeight()/25,UtilGUI.getScreenWidth()/25));

        this.backButton = UtilGUI.getButtonInstance("Back");
        this.backButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        backButtonPanel.add(this.backButton, BorderLayout.LINE_END);
        this.backgroundImageLabel.add(backButtonPanel,BorderLayout.PAGE_END);
    }

    /**
     * This method sets back button listener.
     */
    private void setUpButtonListeners() {
        backButton.addActionListener(e -> {
            ScoresFrame.this.introFrame.setVisible(true);
            ScoresFrame.this.dispose();
        });
    }
}
