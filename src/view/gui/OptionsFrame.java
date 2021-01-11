package view.gui;

import resources.utilResources.Image;
import resources.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the options frame where the player can change language and set fullscreen on and off.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 */
public class OptionsFrame extends GUI {
    private JButton languageButton;
    private JButton fullscreenButton;
    private final IntroFrame introFrame;
    private JButton backButton;
    private final JLabel backgroundImageLabel;
    private String fullscreenText;
    private boolean fullScreened;

    /**
     * Default constructor.
     * @param introFrame
     */
    public OptionsFrame(IntroFrame introFrame) {
        this.introFrame = introFrame;
        this.fullScreened = false;
        this.fullscreenText = " OFF";
        UtilGUI.setUpJFrameProperties(frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(frame, Image.OPTIONS_PAGE_BACKGROUND_IMG);
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setUpFullScreenListener();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    /**
     * This method creates buttons panel (option button and language button).
     */
    private void setUpButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1,1));

        JPanel optionsTextPanel = new JPanel();
        optionsTextPanel.setOpaque(false);
        optionsTextPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/10,0,0,0));
        JLabel optionsTextLabel=UtilGUI.getLabelInstance("Options");
        optionsTextPanel.add(optionsTextLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(optionsTextPanel,BorderLayout.PAGE_START);

        JPanel languageFullScreenPanel = new JPanel();
        languageFullScreenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12,15));
        languageFullScreenPanel.setOpaque(false);
        languageFullScreenPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/3,
                UtilGUI.getScreenWidth()/3,UtilGUI.getScreenHeight()/2,UtilGUI.getScreenWidth()/3));
        this.languageButton = UtilGUI.getButtonInstance("Language");
        java.awt.Image resizedEnglishFlag = ImageFactory.createImage(Image.ENGLISH_FLAG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/35,UtilGUI.getScreenHeight()/35, java.awt.Image.SCALE_DEFAULT);
        this.languageButton.setIcon(new ImageIcon(resizedEnglishFlag));
        this.languageButton.setPreferredSize(new Dimension(180,35));

        this.fullscreenButton = UtilGUI.getButtonInstance("FullScreen :"+this.fullscreenText);
        languageFullScreenPanel.add(languageButton);
        languageFullScreenPanel.add(fullscreenButton);
        buttonsPanel.add(languageFullScreenPanel);

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
        this.backgroundImageLabel.add(buttonsPanel,BorderLayout.CENTER);
        System.out.println(languageButton.getSize());
    }

    /**
     * This method sets back button listener.
     */
    private void setUpButtonListeners() {
        backButton.addActionListener(e -> {
            OptionsFrame.this.introFrame.setVisible(true);
            OptionsFrame.this.dispose();
        });
    }

    /**
     * This method sets fullScreen button listener.
     */
    private void setUpFullScreenListener() {
        this.fullscreenButton.addActionListener(e -> {
            fullScreened = !fullScreened;
            if (fullScreened) {
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().
                        setFullScreenWindow(OptionsFrame.this.frame);
                fullscreenButton.setText(fullscreenButton.getText().replace("OFF","ON"));
            }
            else {
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
                OptionsFrame.this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                fullscreenButton.setText(fullscreenButton.getText().replace("ON","OFF"));
            }
        });
    }
}
