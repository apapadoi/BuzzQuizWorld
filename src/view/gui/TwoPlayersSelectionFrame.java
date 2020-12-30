package view.gui;

import controller.ButtonSoundListener;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TwoPlayersSelectionFrame extends JFrame implements GUI{
    private PlayFrame playFrame;
    private JLabel backgroundImageLabel;
    private JPanel twoPlayersPanel;
    private JPanel backPanel;
    private JButton backButton;
    private JPanel confirmButtonPanel;
    private JButton confirmButton;
    private JPanel topComponentsPanel;
    private JComboBox roundSelectionBox;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};

    private void setComponentsPanel() {
        twoPlayersPanel=new JPanel();
        twoPlayersPanel.setLayout(new BorderLayout());
        twoPlayersPanel.setOpaque(false);

        usernameField1=new JTextField("Enter username:");
        usernameField1.setFont(UtilGUI.getCustomFont());
        usernameField1.setHorizontalAlignment(JTextField.CENTER);
        usernameField2=new JTextField("Enter username:");
        usernameField2.setFont(UtilGUI.getCustomFont());
        usernameField2.setHorizontalAlignment(JTextField.CENTER);
        roundSelectionBox=new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(UtilGUI.getCustomFont());

        topComponentsPanel=new JPanel();
        topComponentsPanel.setLayout(new BoxLayout(topComponentsPanel,BoxLayout.X_AXIS));
        topComponentsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*playFrame.getScreenHeight()),(int)(0.052*playFrame.getScreenWidth()),
                0,(int)(0.052*playFrame.getScreenWidth())));
        topComponentsPanel.setOpaque(false);

        usernameField1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        usernameField2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        roundSelectionBox.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));

        usernameField1.setPreferredSize(new Dimension((int)(0.052*playFrame.getScreenWidth()),(int)(0.046*playFrame.getScreenHeight())));
        usernameField2.setPreferredSize(new Dimension((int)(0.052*playFrame.getScreenWidth()),(int)(0.046*playFrame.getScreenHeight())));
        roundSelectionBox.setPreferredSize(new Dimension((int)(0.052*playFrame.getScreenWidth()),(int)(0.046*playFrame.getScreenHeight())));

        topComponentsPanel.add(usernameField1);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*playFrame.getScreenWidth()),0)));
        topComponentsPanel.add(roundSelectionBox);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*playFrame.getScreenWidth()),0)));
        topComponentsPanel.add(usernameField2);

        confirmButton= UtilGUI.getButtonInstance("Confirm");
        confirmButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        confirmButton.setPreferredSize(new Dimension((int)(0.091*playFrame.getScreenWidth()),(int)(0.046*playFrame.getScreenHeight())));

        confirmButtonPanel=new JPanel();
        confirmButtonPanel.setLayout(new BoxLayout(confirmButtonPanel,BoxLayout.X_AXIS));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*playFrame.getScreenHeight()),(int)(0.416*playFrame.getScreenWidth()),
                (int)(0.416*playFrame.getScreenHeight()),(int)(0.416*playFrame.getScreenWidth())));
        confirmButtonPanel.setOpaque(false);
        confirmButtonPanel.add(confirmButton);

        backPanel=new JPanel();

        backButton= UtilGUI.getButtonInstance("Back");
        backButton.setPreferredSize(new Dimension(175,40));

        backButton.setPreferredSize(new Dimension((int)(0.091*playFrame.getScreenWidth()),(int)(0.037*playFrame.getScreenHeight())));

        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.894*playFrame.getScreenWidth()),
                (int)(0.009*playFrame.getScreenHeight()),0));


        twoPlayersPanel.add(topComponentsPanel,BorderLayout.PAGE_START);
        twoPlayersPanel.add(backPanel,BorderLayout.PAGE_END);
        twoPlayersPanel.add(confirmButtonPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(twoPlayersPanel);
    }

    public TwoPlayersSelectionFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG);
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(true);
                TwoPlayersSelectionFrame.this.setVisible(false);
            }
        });

        usernameField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField1.getText().equals("Enter username:"))
                    usernameField1.setText("");
            }
        });

        backgroundImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField1.getText().equals(""))
                    usernameField1.setText("Enter username:");
                if (usernameField2.getText().equals(""))
                    usernameField2.setText("Enter username:");
            }
        });

        usernameField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField2.getText().equals("Enter username:"))
                    usernameField2.setText("");

            }
        });

        roundSelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundSelectionBox.removeItem("Select rounds:");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(usernameField1.getText().equals("") || usernameField1.getText().equals("Enter username:")
                || usernameField2.getText().equals("") || usernameField2.getText().equals("Enter username:")))
                        if (!(roundSelectionBox.getSelectedItem().equals("Select rounds:"))){
                            LoadingScreenFrame loadingScreenFrame=new LoadingScreenFrame();
                            TwoPlayersSelectionFrame.this.setVisible(false);
                            Timer timer=new Timer();
                            TimerTask timerTask=new TimerTask() {
                                @Override
                                public void run() {
                                    loadingScreenFrame.setVisible(false);
                                    //TwoPlayersGamemodeFrame twoPlayersGamemodeFrame=new TwoPlayersGamemodeFrame();
                                    TwoPlayersBettingFrame twoPlayersBettingFrame=new TwoPlayersBettingFrame(TwoPlayersSelectionFrame.this);
                                }
                            };
                            timer.schedule(timerTask,1000);
                        }
            }
        });
    }
}
