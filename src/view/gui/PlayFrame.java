package view.gui;

import controller.ButtonSoundListener;
import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PlayFrame extends JFrame implements GUI{
    private IntroFrame introFrame;
    private JButton onePlayerButton;
    private JButton twoPlayersButton;
    private JPanel buttonsPanel;
    private JPanel backPanel;
    private JPanel playPanel;
    private JButton backButton;
    private JLabel backgroundImageLabel;
    private JLabel gamemodesLabel;
    private JPanel labelPanel;

    public PlayFrame(IntroFrame introFrame){
        this.introFrame=introFrame;
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.PLAY_PAGE_BACKGROUND_IMG);
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonsPanel() {
        buttonsPanel =new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1,2,(int)(0.338*UtilGUI.getScreenWidth()),0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.625*UtilGUI.getScreenHeight()),(int)(0.140*UtilGUI.getScreenWidth()),
                (int)(0.106*UtilGUI.getScreenHeight()),(int)(0.098*UtilGUI.getScreenWidth())));

        onePlayerButton= UtilGUI.getButtonInstance("One Player");
        twoPlayersButton= UtilGUI.getButtonInstance("Two Players");

        buttonsPanel.add(twoPlayersButton);
        buttonsPanel.add(onePlayerButton);

        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);


        backButton= UtilGUI.getButtonInstance("Back");
        backButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,0,(int)(0.013*UtilGUI.getScreenHeight()),(int)(0.007*UtilGUI.getScreenWidth())));

        playPanel=new JPanel();
        playPanel.setLayout(new BorderLayout());
        playPanel.setOpaque(false);

        labelPanel=new JPanel();
        labelPanel.setLayout(new GridLayout(1,1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.074*UtilGUI.getScreenHeight()),(int)(0.468*UtilGUI.getScreenWidth()),
                0,(int)(0.260*UtilGUI.getScreenWidth())));
        labelPanel.setOpaque(false);

        gamemodesLabel=UtilGUI.getLabelInstance("Select gamemode");
        labelPanel.add(gamemodesLabel);

        playPanel.add(labelPanel,BorderLayout.PAGE_START);
        playPanel.add(backPanel,BorderLayout.PAGE_END);
        playPanel.add(buttonsPanel,BorderLayout.CENTER);

        backgroundImageLabel.add(playPanel);
    }

    private void setUpButtonListeners(){
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnePlayerSelectionFrame onePlayerFrame=new OnePlayerSelectionFrame(PlayFrame.this);
                PlayFrame.this.setVisible(false);
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayersSelectionFrame twoPlayersFrame=new TwoPlayersSelectionFrame(PlayFrame.this);
                PlayFrame.this.setVisible(false);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introFrame.setVisible(true);
                PlayFrame.this.setVisible(false);
            }
        });
    }
}
