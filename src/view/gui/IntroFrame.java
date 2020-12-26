package view.gui;

import controller.ButtonSoundListener;
import javafx.embed.swing.JFXPanel;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroFrame extends JFrame implements GUI{
    private JLabel backgroundImageLabel;
    private JPanel versionPanel;
    private JLabel versionLabel;
    private JLabel teamLabel;
    private JButton playButton;
    private JButton scoresButton;
    private JButton optionsButton;
    private JButton quitButton;
    private JPanel buttonsPanel;
    private JLabel logoImageLabel;
    private JPanel iconPanel;
    private JPanel optionQuitButtonsPanel;

    public IntroFrame() {
        new JFXPanel();
        UtilGUI.setUpJFrameProperties(this);
        this.setUpBackGround();
        this.setUpVersionPanel();
        this.setUpIconPanel();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpIconPanel() {
        iconPanel = new JPanel();
        iconPanel.setBackground(new Color(0,0,0,0));
        logoImageLabel = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(Image.INTRO_PAGE_LOGO).getImage().
                getScaledInstance((int)(0.186*UtilGUI.getScreenWidth()),(int)(0.362*UtilGUI.getScreenHeight()), java.awt.Image.SCALE_DEFAULT);
        this.logoImageLabel.setIcon(new ImageIcon(resizedImage));
        iconPanel.add(logoImageLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(iconPanel,BorderLayout.PAGE_START);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(0,0,0,0));
        buttonsPanel.setLayout(new GridLayout(3,1,0,15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.362*UtilGUI.getScreenWidth()),
                (int)(0.333*UtilGUI.getScreenHeight()),(int)(0.362*UtilGUI.getScreenWidth())));

        playButton = new JButton("Play");
        playButton.setFont(UtilGUI.getCustomFont());
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        scoresButton = new JButton("High Scores");
        scoresButton.setFont(UtilGUI.getCustomFont());
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusPainted(false);

        optionsButton = new JButton("Options");
        optionsButton.setFont(UtilGUI.getCustomFont());
        quitButton = new JButton("Quit Game");
        quitButton.setFont(UtilGUI.getCustomFont());

        optionsButton.setPreferredSize(new Dimension((int)(0.127*UtilGUI.getScreenWidth()),(int)(0.041*UtilGUI.getScreenHeight())));
        optionsButton.setBorderPainted(false);
        optionsButton.setFocusPainted(false);

        quitButton.setPreferredSize(new Dimension((int)(0.127*UtilGUI.getScreenWidth()),(int)(0.041*UtilGUI.getScreenHeight())));
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);


        optionQuitButtonsPanel = new JPanel();
        optionQuitButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,13));
        optionQuitButtonsPanel.setBackground(new Color(0,0,0,0));
        optionQuitButtonsPanel.add(optionsButton);
        optionQuitButtonsPanel.add(quitButton);

        buttonsPanel.add(playButton);
        buttonsPanel.add(scoresButton);
        buttonsPanel.add(optionQuitButtonsPanel);

        backgroundImageLabel.add(buttonsPanel,BorderLayout.CENTER);
    }

    private void setUpVersionPanel() {
        this.versionPanel = new JPanel();
        versionPanel.setLayout(new BorderLayout());
        versionPanel.setBackground(new Color(0,0,0,0));

        versionLabel = new JLabel("Buzz! Quiz World 15.12.2020");
        versionLabel.setForeground(Color.WHITE);
        this.versionLabel.setFont(UtilGUI.getCustomFont());

        teamLabel = new JLabel("4 Hills Industry");
        teamLabel.setForeground(Color.WHITE);
        this.teamLabel.setFont(UtilGUI.getCustomFont());

        versionPanel.add(versionLabel,BorderLayout.LINE_START);
        versionPanel.add(teamLabel,BorderLayout.LINE_END);

        backgroundImageLabel.add(versionPanel,BorderLayout.PAGE_END);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.INTRO_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners() {
        playButton.addActionListener(ButtonSoundListener.getInstance());
        optionsButton.addActionListener(ButtonSoundListener.getInstance());
        optionsButton.addActionListener(e -> new OptionsFrame(IntroFrame.this));
        scoresButton.addActionListener(ButtonSoundListener.getInstance());
        scoresButton.addActionListener(e -> new ScoresFrame(IntroFrame.this));
        quitButton.addActionListener(e -> System.exit(0));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayFrame playFrame=new PlayFrame(IntroFrame.this);
                IntroFrame.this.setVisible(false);
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new IntroFrame();
    }
}
