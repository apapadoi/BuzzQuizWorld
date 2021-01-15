package view.gui;

import controller.FrontController;
import controller.requests.PlaySoundRequest;
import javafx.embed.swing.JFXPanel;
import view.gui.utilResources.Constants;
import view.gui.utilResources.Image;
import view.gui.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the starting frame of the game.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public class IntroFrame extends GUI {
    private final JLabel backgroundImageLabel;
    private JButton playButton;
    private JButton scoresButton;
    private JButton optionsButton;
    private JButton quitButton;

    /**
     * Default constructor.
     */
    public IntroFrame() {
        new JFXPanel();
        UtilGUI.setUpJFrameProperties(frame);
        this.backgroundImageLabel = UtilGUI.setUpBackGround(frame,Image.INTRO_PAGE_BACKGROUND_IMG);
        this.setUpVersionPanel();
        this.setUpIconPanel();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        FrontController.getInstance().setView(this);
        FrontController.getInstance().dispatchRequest(new PlaySoundRequest(Constants.MENU_SOUND_URL));
        frame.setVisible(true);
    }

    /**
     * This method creates the top panel and adds the game logo to it.
     */
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

    /**
     * This method creates the buttons panel and adds all available buttons to it.
     */
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

    /**
     * This method creates the versions panel where the version of the game and the name of the team will appear.
     */
    private void setUpVersionPanel() {
        JPanel versionPanel = new JPanel();
        versionPanel.setLayout(new BorderLayout());
        versionPanel.setBackground(new Color(0,0,0,0));

        JLabel versionLabel = new JLabel(" Buzz! Quiz World 15.1.2021");
        versionLabel.setForeground(Color.WHITE);
        versionLabel.setFont(UtilGUI.getCustomFont());

        JLabel teamLabel = new JLabel("Developed by 4 Hills Industry ");
        teamLabel.setForeground(Color.WHITE);
        teamLabel.setFont(UtilGUI.getCustomFont());

        versionPanel.add(versionLabel,BorderLayout.LINE_START);
        versionPanel.add(teamLabel,BorderLayout.LINE_END);

        backgroundImageLabel.add(versionPanel,BorderLayout.PAGE_END);
    }

    /**
     * This method sets button listeners for all available buttons.
     */
    private void setUpButtonListeners() {
        optionsButton.addActionListener(e -> new OptionsFrame(IntroFrame.this));
        scoresButton.addActionListener(e -> new ScoresFrame(IntroFrame.this));
        quitButton.addActionListener(e -> System.exit(0));
        playButton.addActionListener(e -> new PlayFrame(IntroFrame.this));
    }
}
