package view.gui;

import controller.ButtonSoundListener;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsFrame extends JFrame implements GUI{
    private JButton languageButton;
    private JButton fullscreenButton;
    private JButton backButton;
    private IntroFrame introFrame;
    private JLabel backgroundImageLabel;
    private String fullscreenText;
    private JPanel buttonsPanel;
    private JLabel optionsTextLabel;
    private JPanel backButtonPanel;
    private JPanel languageFullScreenPanel;
    private JPanel optionsTextPanel;
    private boolean fullScreened;

    public OptionsFrame(IntroFrame introFrame) {
        this.introFrame = introFrame;
        this.fullScreened = false;
        this.fullscreenText = " OFF";
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.OPTIONS_PAGE_BACKGROUND_IMG);
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setUpFullScreenListener();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1,1));

        this.optionsTextPanel = new JPanel();
        this.optionsTextPanel.setOpaque(false);
        this.optionsTextPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/10,0,0,0));
        this.optionsTextLabel=UtilGUI.getLabelInstance("Options");
        this.optionsTextPanel.add(optionsTextLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(optionsTextPanel,BorderLayout.PAGE_START);

        this.languageFullScreenPanel = new JPanel();
        this.languageFullScreenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12,15));
        this.languageFullScreenPanel.setOpaque(false);
        this.languageFullScreenPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/3,
                UtilGUI.getScreenWidth()/3,UtilGUI.getScreenHeight()/2,UtilGUI.getScreenWidth()/3));
        this.languageButton = UtilGUI.getButtonInstance("Language");
        java.awt.Image resizedEnglishFlag = ImageFactory.createImage(Image.ENGLISH_FLAG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/35,UtilGUI.getScreenHeight()/35, java.awt.Image.SCALE_DEFAULT);
        this.languageButton.setIcon(new ImageIcon(resizedEnglishFlag));
        this.languageButton.setPreferredSize(new Dimension(180,35));

        this.fullscreenButton = UtilGUI.getButtonInstance("FullScreen :"+this.fullscreenText);
        this.languageFullScreenPanel.add(languageButton);
        this.languageFullScreenPanel.add(fullscreenButton);
        buttonsPanel.add(languageFullScreenPanel);

        this.backButtonPanel = new JPanel();
        this.backButtonPanel.setLayout(new BorderLayout());
        this.backButtonPanel.setBackground(new Color(0,0,0,0));
        this.backgroundImageLabel.setBorder(BorderFactory.createEmptyBorder(0,0,
                UtilGUI.getScreenHeight()/25,UtilGUI.getScreenWidth()/25));

        this.backButton = UtilGUI.getButtonInstance("Back");
        this.backButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        this.backButtonPanel.add(this.backButton, BorderLayout.LINE_END);

        this.backgroundImageLabel.add(backButtonPanel,BorderLayout.PAGE_END);
        this.backgroundImageLabel.add(buttonsPanel,BorderLayout.CENTER);
        System.out.println(languageButton.getSize());
    }

    private void setUpButtonListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsFrame.this.introFrame.setVisible(true);
                OptionsFrame.this.dispose();
            }
        });
    }

    private void setUpFullScreenListener() {
        this.fullscreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullScreened = !fullScreened;
                if (fullScreened) {
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(OptionsFrame.this);
                    fullscreenButton.setText(fullscreenButton.getText().replace("OFF","ON"));
                }
                else {
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
                    OptionsFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    fullscreenButton.setText(fullscreenButton.getText().replace("ON","OFF"));
                }
            }
        });
    }
}
