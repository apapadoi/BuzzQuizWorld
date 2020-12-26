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
        this.setUpJFrameProperties();
        this.setUpBackGround();
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

        onePlayerButton=new JButton("One Player");
        onePlayerButton.setFont(UtilGUI.getCustomFont());
        onePlayerButton.setBorderPainted(false);
        onePlayerButton.setFocusPainted(false);

        twoPlayersButton=new JButton("Two Players");
        twoPlayersButton.setFont(UtilGUI.getCustomFont());
        twoPlayersButton.setBorderPainted(false);
        twoPlayersButton.setFocusPainted(false);

        buttonsPanel.add(twoPlayersButton);
        buttonsPanel.add(onePlayerButton);

        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);


        backButton=new JButton("Back");
        backButton.setFont(UtilGUI.getCustomFont());
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
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

        gamemodesLabel=new JLabel("Select Gamemode");
        gamemodesLabel.setFont(UtilGUI.getCustomFont());
        gamemodesLabel.setForeground(Color.WHITE);
        labelPanel.add(gamemodesLabel);

        playPanel.add(labelPanel,BorderLayout.PAGE_START);
        playPanel.add(backPanel,BorderLayout.PAGE_END);
        playPanel.add(buttonsPanel,BorderLayout.CENTER);

        backgroundImageLabel.add(playPanel);
    }


    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.PLAY_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth(),UtilGUI.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners(){
        onePlayerButton.addActionListener(ButtonSoundListener.getInstance());
        twoPlayersButton.addActionListener(ButtonSoundListener.getInstance());
        backButton.addActionListener(ButtonSoundListener.getInstance());
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
