package view.gui;

import javafx.scene.control.RadioButton;
import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TwoPlayersBettingFrame extends JFrame {
    private JLabel backgroundImageLabel;
    private TwoPlayersSelectionFrame twoPlayersSelectionFrame;
    private static final int iconMultiplier = 30;
    private JLabel wKey;
    private JLabel aKey;
    private JLabel sKey;
    private JLabel dKey;
    private JLabel upKey;
    private JLabel downKey;
    private JLabel leftKey;
    private JLabel rightKey;
    private JLabel betAmount1;
    private JLabel betAmount2;
    private JLabel betAmount3;
    private JLabel betAmount4;
    private JPanel bettingPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel amountPanel;
    private JPanel topPanel;
    private JLabel chooseAmountLabel;
    private JLabel amountLabel;
    private JLabel highStakesLabel;
    private JPanel bottomPanel;
    private JButton confirmButton;


    public TwoPlayersBettingFrame(TwoPlayersSelectionFrame twoPlayersSelectionFrame){
        this.twoPlayersSelectionFrame=twoPlayersSelectionFrame;
        bettingPanel=new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);
        UtilGUI.setUpJFrameProperties(this);
        this.backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
        this.setUpTopPanel();
        this.setUpAmountPanel();
        this.setUpRightSideIcons();
        this.setUpLeftSideIcons();
        this.setUpBottomPanel();
        this.setUpButtonListeners();
        backgroundImageLabel.add(bettingPanel);
        this.setVisible(true);
    }

    private void setUpAmountPanel() {
        amountPanel = new JPanel();
        amountPanel.setLayout(new GridLayout(4,1,(int)(0.007*UtilGUI.getScreenWidth()),
                (int)(0.013*UtilGUI.getScreenHeight())));
        amountPanel.setOpaque(false);
        amountPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,0));


        betAmount1 = new JLabel("250");
        betAmount1.setForeground(Color.WHITE);
        betAmount1.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));

        betAmount1.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount1);

        betAmount2 = new JLabel("500");
        betAmount2.setForeground(Color.WHITE);
        betAmount2.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));
        betAmount2.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount2);

        betAmount3 = new JLabel("750");
        betAmount3.setForeground(Color.WHITE);
        betAmount3.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount3.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount3);

        betAmount4 = new JLabel("1000");
        betAmount4.setForeground(Color.WHITE);
        betAmount4.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount4.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount4);

        bettingPanel.add(amountPanel,BorderLayout.CENTER);
    }

    private void setUpRightSideIcons() {
        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridLayout(4,1,0,0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,(int)(0.364*UtilGUI.getScreenWidth())));

        /*
        upKey = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.UP_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        upKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(upKey);

        leftKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.LEFT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        leftKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(leftKey);

        downKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.DOWN_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        downKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(downKey);

        rightKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.RIGHT_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        rightKey.setIcon(new ImageIcon(resizedImage));
        rightPanel.add(rightKey);
        */

        JRadioButton amount250=new JRadioButton("");
        amount250.setSelected(true);
        JRadioButton amount500=new JRadioButton("");
        JRadioButton amount750=new JRadioButton("");
        JRadioButton amount1000=new JRadioButton("");

        ButtonGroup rightGroup=new ButtonGroup();


        amount250.setOpaque(false);
        amount500.setOpaque(false);
        amount750.setOpaque(false);
        amount1000.setOpaque(false);

        rightGroup.add(amount250);
        rightGroup.add(amount500);
        rightGroup.add(amount750);
        rightGroup.add(amount1000);

        rightPanel.add(amount250);
        rightPanel.add(amount500);
        rightPanel.add(amount750);
        rightPanel.add(amount1000);

        this.bettingPanel.add(rightPanel,BorderLayout.EAST);
    }

    private void setUpLeftSideIcons() {
        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridLayout(4,1,0,0));

        leftPanel.setBorder(BorderFactory.createEmptyBorder(0,
                (int)(0.364*UtilGUI.getScreenWidth()),0,0));
       /* wKey = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.W_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        wKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(wKey);

        aKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.A_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        aKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(aKey);

        sKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.S_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        sKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(sKey);

        dKey = new JLabel();
        resizedImage = ImageFactory.createImage(resources.images.Image.D_KEY_IMG).getImage().
                getScaledInstance(UtilGUI.getScreenWidth()/iconMultiplier,UtilGUI.getScreenHeight()/iconMultiplier, java.awt.Image.SCALE_DEFAULT);
        dKey.setIcon(new ImageIcon(resizedImage));
        leftPanel.add(dKey);
        */
        JRadioButton amount250=new JRadioButton("");
        amount250.setSelected(true);
        JRadioButton amount500=new JRadioButton("");
        JRadioButton amount750=new JRadioButton("");
        JRadioButton amount1000=new JRadioButton("");

        ButtonGroup leftGroup=new ButtonGroup();


        amount250.setOpaque(false);
        amount500.setOpaque(false);
        amount750.setOpaque(false);
        amount1000.setOpaque(false);

        leftGroup.add(amount250);
        leftGroup.add(amount500);
        leftGroup.add(amount750);
        leftGroup.add(amount1000);

        leftPanel.add(amount250);
        leftPanel.add(amount500);
        leftPanel.add(amount750);
        leftPanel.add(amount1000);

        this.bettingPanel.add(leftPanel,BorderLayout.WEST);
    }

    private void setUpTopPanel() {
        topPanel=new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.416*UtilGUI.getScreenWidth()),0,0));

        highStakesLabel=UtilGUI.getLabelInstance("High Stakes");

        chooseAmountLabel=UtilGUI.getLabelInstance("Select your betting amount");

        amountLabel=UtilGUI.getLabelInstance("Amounts available");

        JPanel top=new JPanel();
        JPanel center=new JPanel();
        JPanel bottom=new JPanel();

        top.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());

        top.setBorder(BorderFactory.createEmptyBorder((int)(0.009*UtilGUI.getScreenHeight()),
                (int)(0.052*UtilGUI.getScreenWidth()),0,0));
        top.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.015*UtilGUI.getScreenWidth()),0,0));
        center.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.036*UtilGUI.getScreenWidth()),0,0));
        bottom.setOpaque(false);

        top.add(highStakesLabel,BorderLayout.CENTER);
        center.add(chooseAmountLabel,BorderLayout.CENTER);
        bottom.add(amountLabel,BorderLayout.CENTER);

        topPanel.add(top);
        topPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.046*UtilGUI.getScreenHeight()))));
        topPanel.add(center);
        topPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.055*UtilGUI.getScreenHeight()))));
        topPanel.add(bottom);

        bettingPanel.add(topPanel,BorderLayout.NORTH);
    }

    private void setUpBottomPanel() {
        bottomPanel=new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,(int)(0.092*UtilGUI.getScreenHeight()),(int)(0.052*UtilGUI.getScreenWidth())));
        bottomPanel.setOpaque(false);
        confirmButton=UtilGUI.getButtonInstance("Confirm");
        confirmButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));

        bottomPanel.add(confirmButton,BorderLayout.LINE_END);

        bettingPanel.add(bottomPanel,BorderLayout.PAGE_END);
    }

    private void setUpButtonListeners(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayersGamemodeFrame twoPlayersGamemodeFrame=new TwoPlayersGamemodeFrame();
                TwoPlayersBettingFrame.this.setVisible(false);
            }
        });
    }
}
