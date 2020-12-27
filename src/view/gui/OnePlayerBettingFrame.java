package view.gui;

import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static resources.images.Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG;

public class OnePlayerBettingFrame extends JFrame implements GUI{
    private JLabel backgroundImageLabel;
    private OnePlayerFrame onePlayerFrame;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel bettingPanel;
    private JLabel bettingLabel;
    private JPanel labelsPanel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JLabel amountAvailableLabel;
    private JLabel bettingPhaseLabel;
    private JPanel bettingPhasePanel;
    private JButton bettingAmountButton1;
    private JButton bettingAmountButton2;
    private JButton bettingAmountButton3;
    private JButton bettingAmountButton4;

    public OnePlayerBettingFrame(OnePlayerSelectionFrame onePlayerSelectionFrame){
        this.onePlayerSelectionFrame=onePlayerSelectionFrame;
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
    }

    private void setComponentsPanel() {
        bettingPanel=new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);

        bettingPhasePanel=new JPanel();
        bettingPhasePanel.setOpaque(false);
        bettingPhasePanel.setLayout(new BorderLayout());
        bettingPhasePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        bettingPhaseLabel=new JLabel("             High Stakes");
        bettingPhaseLabel.setFont(UtilGUI.getCustomFont());
        bettingPhaseLabel.setForeground(Color.WHITE);

        bettingLabel=new JLabel("Select your betting amount");
        bettingLabel.setFont(UtilGUI.getCustomFont());
        bettingLabel.setForeground(Color.WHITE);

        labelsPanel=new JPanel();
        labelsPanel.setOpaque(false);
        labelsPanel.setLayout(new BoxLayout(labelsPanel,BoxLayout.Y_AXIS));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(7,800,0,0));

        labelsPanel.add(bettingPhaseLabel);
        labelsPanel.add(Box.createRigidArea(new Dimension(0,70)));
        labelsPanel.add(bettingLabel);

        bettingPhasePanel.add(labelsPanel,BorderLayout.CENTER);

        centerPanel=new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(150,0,0,220));
        centerPanel.setOpaque(false);
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,200,530,0));
        buttonsPanel.setOpaque(false);
        bettingAmountButton1= UtilGUI.getButtonInstance("250");
        bettingAmountButton2= UtilGUI.getButtonInstance("500");
        bettingAmountButton3= UtilGUI.getButtonInstance("750");
        bettingAmountButton4= UtilGUI.getButtonInstance("1000");

        amountAvailableLabel=new JLabel("Amounts Available");
        amountAvailableLabel.setForeground(Color.WHITE);
        amountAvailableLabel.setFont(UtilGUI.getCustomFont());

        bettingAmountButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton1.setPreferredSize(new Dimension(100,60));
        bettingAmountButton2.setPreferredSize(new Dimension(100,60));
        bettingAmountButton3.setPreferredSize(new Dimension(100,60));
        bettingAmountButton4.setPreferredSize(new Dimension(100,60));
        buttonsPanel.add(bettingAmountButton1);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton2);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton3);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,0)));
        buttonsPanel.add(bettingAmountButton4);

        centerPanel.add(amountAvailableLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,130)));
        centerPanel.add(buttonsPanel);

        bettingPanel.add(centerPanel,BorderLayout.CENTER);
        bettingPanel.add(bettingPhasePanel,BorderLayout.PAGE_START);
        backgroundImageLabel.add(bettingPanel);
    }
}
