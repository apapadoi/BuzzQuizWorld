package view.gui;

import model.player.Player;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoresFrame extends JFrame{
    private JLabel backgroundImageLabel;
    private final IntroFrame introFrame;
    private JPanel scoresPanel;
    private JLabel scoresTextLabel;
    private JPanel scoresTextPanel;
    private JPanel centralPanel;
    private JPanel backButtonPanel;
    private JButton backButton;
    private JPanel sortButtonsPanel;
    private JButton onePlayerSortButton;
    private JButton twoPlayersSortButton;
    private java.util.List<Player> players;
    private java.util.List<JLabel> scoresLabels;

    public ScoresFrame(IntroFrame introFrame) {
        this.players = new ArrayList<>();
        this.scoresLabels = new ArrayList<>();
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
        this.setUpSortButtonsListeners();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    private void setUpSortButtonsListeners() {
        this.onePlayerSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.sort(new Comparator<Player>() {
                    @Override
                    public int compare(Player o1, Player o2) {
                        return -1*Integer.compare(o1.getScore(),o2.getScore());
                    }
                });
                updatedScoreLabels();
            }
        });

        this.twoPlayersSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.sort(new Comparator<Player>() {
                    @Override
                    public int compare(Player o1, Player o2) {
                        return -1*Integer.compare(o1.getWins(),o2.getWins());
                    }
                });
                updatedScoreLabels();
            }
        });

    }

    private void updatedScoreLabels() {
        for(int i = 0; i< scoresLabels.size()/3; i++) {
            scoresLabels.get(3*i).setText(players.get(i).getUsername());
            scoresLabels.get(3*i+1).setText(String.valueOf(players.get(i).getScore()));
            scoresLabels.get(3*i+2).setText(String.valueOf(players.get(i).getWins()));
        }
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
        this.backgroundImageLabel.add(sortButtonsPanel, BorderLayout.WEST);
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
        this.players.add(new Player("Papster",500,2));
        this.players.add(new Player("tasos",45,10));
        this.players.add(new Player("petros",32,15));
        this.players.add(new Player("rafa",18,5));
        this.players.add(new Player("thodwris",0,8));
        this.players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return -1*Integer.compare(o1.getScore(),o2.getScore());
            }
        });
    }

    private void setUpScoresPanel() {
        JLabel label;
        this.scoresPanel = new JPanel();
        this.scoresPanel.setOpaque(false);
        this.scoresPanel.setLayout(new GridLayout(players.size()+1,3,0,0));
        this.scoresPanel.add(constructCustomJLabel("Username"));
        this.scoresPanel.add(constructCustomJLabel("High Score"));
        this.scoresPanel.add(constructCustomJLabel("1-1 Wins"));
        for(Player player : this.players) {
            label = constructCustomJLabel(player.getUsername());
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
            label = constructCustomJLabel(String.valueOf(player.getScore()));
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
            label = constructCustomJLabel(String.valueOf(player.getWins()));
            this.scoresPanel.add(label);
            this.scoresLabels.add(label);
        }
    }

    private JLabel constructCustomJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(this.introFrame.getFont());
        label.setForeground(Color.WHITE);

        return label;
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoresFrame.this.introFrame.setVisible(true);
                ScoresFrame.this.dispose();
            }
        });
    }
}
