package view.gui;

import controller.FrontController;
import javafx.embed.swing.JFXPanel;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;

public class IntroFrame extends JFrame implements GUI{
    private final JLabel backgroundImageLabel;
    private JButton playButton;
    private JButton scoresButton;
    private JButton optionsButton;
    private JButton quitButton;

    public IntroFrame() {
        new JFXPanel();
        UtilGUI.setUpJFrameProperties(this);
        this.backgroundImageLabel = UtilGUI.setUpBackGround(this,Image.INTRO_PAGE_BACKGROUND_IMG);
        this.setUpVersionPanel();
        this.setUpIconPanel();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        FrontController.getInstance().setView(this);
        this.setVisible(true);
    }

    private void setUpIconPanel() {
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(0,0,0,0));
        JLabel logoImageLabel = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(Image.INTRO_PAGE_LOGO).getImage().
                getScaledInstance((int)(0.186*UtilGUI.getScreenWidth()),(int)(0.362*UtilGUI.getScreenHeight()), java.awt.Image.SCALE_DEFAULT);
        logoImageLabel.setIcon(new ImageIcon(resizedImage));
        iconPanel.add(logoImageLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(iconPanel,BorderLayout.PAGE_START);
    }

    private void setUpButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(0,0,0,0));
        buttonsPanel.setLayout(new GridLayout(3,1,0,15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.362*UtilGUI.getScreenWidth()),
                (int)(0.333*UtilGUI.getScreenHeight()),(int)(0.362*UtilGUI.getScreenWidth())));

        playButton = UtilGUI.getButtonInstance("Play");
        scoresButton = UtilGUI.getButtonInstance("High Scores");
        optionsButton = UtilGUI.getButtonInstance("Options");
        quitButton = UtilGUI.getButtonInstance("Quit Game");

        optionsButton.setPreferredSize(new Dimension((int)(0.127*UtilGUI.getScreenWidth()),(int)(0.041*UtilGUI.getScreenHeight())));
        quitButton.setPreferredSize(new Dimension((int)(0.127*UtilGUI.getScreenWidth()),(int)(0.041*UtilGUI.getScreenHeight())));

        JPanel optionQuitButtonsPanel = new JPanel();
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
        JPanel versionPanel = new JPanel();
        versionPanel.setLayout(new BorderLayout());
        versionPanel.setBackground(new Color(0,0,0,0));

        JLabel versionLabel = new JLabel("Buzz! Quiz World 15.12.2020");
        versionLabel.setForeground(Color.WHITE);
        versionLabel.setFont(UtilGUI.getCustomFont());

        JLabel teamLabel = new JLabel("4 Hills Industry");
        teamLabel.setForeground(Color.WHITE);
        teamLabel.setFont(UtilGUI.getCustomFont());

        versionPanel.add(versionLabel,BorderLayout.LINE_START);
        versionPanel.add(teamLabel,BorderLayout.LINE_END);

        backgroundImageLabel.add(versionPanel,BorderLayout.PAGE_END);
    }

    private void setUpButtonListeners() {
        optionsButton.addActionListener(e -> new OptionsFrame(IntroFrame.this));
        scoresButton.addActionListener(e -> new ScoresFrame(IntroFrame.this));
        quitButton.addActionListener(e -> System.exit(0));
        playButton.addActionListener(e -> {
            new PlayFrame(IntroFrame.this);
        });
        quitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new IntroFrame();
    }
}
