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
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setUpFullScreenListener();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        this.setLayout(new BorderLayout());
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.OPTIONS_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight()+40, java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1,1));

        this.optionsTextPanel = new JPanel();
        this.optionsTextPanel.setOpaque(false);
        this.optionsTextPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/10,0,0,0));
        this.optionsTextLabel = new JLabel("Options");
        this.optionsTextLabel.setFont(UtilGUI.getCustomFont());
        this.optionsTextLabel.setForeground(Color.WHITE);
        this.optionsTextPanel.add(optionsTextLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(optionsTextPanel,BorderLayout.PAGE_START);


        this.languageFullScreenPanel = new JPanel();
        this.languageFullScreenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 12,15));
        this.languageFullScreenPanel.setOpaque(false);
        this.languageFullScreenPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/3,
                UtilGUI.getScreenWidth()/3,UtilGUI.getScreenHeight()/2,UtilGUI.getScreenWidth()/3));
        this.languageButton = new JButton("Language");
        java.awt.Image resizedEnglishFlag = ImageFactory.createImage(Image.ENGLISH_FLAG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/35,UtilGUI.getScreenHeight()/35, java.awt.Image.SCALE_DEFAULT);
        this.languageButton.setIcon(new ImageIcon(resizedEnglishFlag));
        this.languageButton.setPreferredSize(new Dimension(180,35));
        languageButton.setFont(UtilGUI.getCustomFont());
        languageButton.setBorderPainted(false);
        languageButton.setFocusPainted(false);
        this.fullscreenButton = new JButton("FullScreen :"+this.fullscreenText);
        fullscreenButton.setFont(UtilGUI.getCustomFont());
        fullscreenButton.setBorderPainted(false);
        fullscreenButton.setFocusPainted(false);
        this.languageFullScreenPanel.add(languageButton);
        this.languageFullScreenPanel.add(fullscreenButton);
        buttonsPanel.add(languageFullScreenPanel);


        this.backButtonPanel = new JPanel();
        this.backButtonPanel.setLayout(new BorderLayout());
        this.backButtonPanel.setBackground(new Color(0,0,0,0));
        this.backgroundImageLabel.setBorder(BorderFactory.createEmptyBorder(0,0,
                UtilGUI.getScreenHeight()/25,UtilGUI.getScreenWidth()/25));

        this.backButton = new JButton("Back");
        backButton.setFont(UtilGUI.getCustomFont());
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        this.backButton.setPreferredSize(new Dimension( (int)(this.introFrame.getWidth()*0.135),
                (int)(this.introFrame.getHeight()*0.04) ));

        this.backButtonPanel.add(this.backButton, BorderLayout.LINE_END);

        this.backgroundImageLabel.add(backButtonPanel,BorderLayout.PAGE_END);
        this.backgroundImageLabel.add(buttonsPanel,BorderLayout.CENTER);
        System.out.println(languageButton.getSize());
    }

    private void setUpButtonListeners() {
        languageButton.addActionListener(ButtonSoundListener.getInstance());
        fullscreenButton.addActionListener(ButtonSoundListener.getInstance());
        backButton.addActionListener(ButtonSoundListener.getInstance());
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
