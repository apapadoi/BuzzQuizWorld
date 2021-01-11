package view.gui;

import controller.FrontController;
import controller.requests.SetSoundSettingRequest;
import javafx.scene.layout.Border;
import model.Model;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the options frame where the player can change language and set fullscreen on and off.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 */
public class OptionsFrame extends GUI {
    private final IntroFrame introFrame;
    private JButton backButton;
    private final JLabel backgroundImageLabel;
    private JButton musicButton;
    private boolean hasMusic;
    private final String musicText;

    /**
     * Default constructor.
     * @param introFrame
     */
    public OptionsFrame(IntroFrame introFrame) {
        if(Model.getInstance().hasMusic())
            musicText="ON";
        else
            musicText="OFF";
        hasMusic = true;
        this.introFrame = introFrame;
        UtilGUI.setUpJFrameProperties(frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(frame, Image.OPTIONS_PAGE_BACKGROUND_IMG);
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setUpMusicButtonListener();
        this.setVisible(true);
        this.introFrame.setVisible(false);
    }

    /**
     * This method creates buttons panel (option button and language button).
     */
    private void setUpButtonsPanel() {
        this.musicButton = UtilGUI.getButtonInstance("Music : "+musicText);
        JPanel musicButtonPanel = new JPanel();
        musicButtonPanel.setOpaque(false);
        musicButtonPanel.setLayout(new BorderLayout());
        musicButtonPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/5,
                UtilGUI.getScreenWidth()/3,UtilGUI.getScreenHeight()/3,UtilGUI.getScreenWidth()/3));
        musicButtonPanel.add(musicButton,BorderLayout.CENTER);

        JPanel optionsTextPanel = new JPanel();
        optionsTextPanel.setLayout(new BorderLayout());
        optionsTextPanel.setOpaque(false);
        optionsTextPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()/5,
                UtilGUI.getScreenWidth()/3,0,UtilGUI.getScreenWidth()/3));
        JLabel optionsTextLabel=UtilGUI.getLabelInstance("Options");
        optionsTextPanel.add(optionsTextLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(musicButtonPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(optionsTextPanel,BorderLayout.PAGE_START);

        this.backgroundImageLabel.setBorder(BorderFactory.createEmptyBorder(0,0,
                UtilGUI.getScreenHeight()/25,UtilGUI.getScreenWidth()/25));

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(new Color(0,0,0,0));
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
            OptionsFrame.this.introFrame.setVisible(true);
            OptionsFrame.this.dispose();
        });
    }

    /**
     * This method sets fullScreen button listener.
     */
    private void setUpMusicButtonListener() {
        this.musicButton.addActionListener(e->{
            hasMusic = !hasMusic;
            if(hasMusic) {
                FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(true));
                musicButton.setText(musicButton.getText().replace("OFF","ON"));
            }
            else {
                FrontController.getInstance().dispatchRequest(new SetSoundSettingRequest(false));
                musicButton.setText(musicButton.getText().replace("ON","OFF"));
            }
        });
    }
}
