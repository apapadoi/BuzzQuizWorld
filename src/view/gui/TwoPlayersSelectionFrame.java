package view.gui;

import controller.FrontController;
import controller.requests.*;
import model.gamemodes.factories.TwoPlayersGamemodeFactory;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayersSelectionFrame extends JFrame implements UI {
    private final PlayFrame playFrame;
    private final JLabel backgroundImageLabel;
    private JButton backButton;
    private JButton confirmButton;
    private JComboBox roundSelectionBox;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private final String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};

    private void setComponentsPanel() {
        JPanel twoPlayersPanel = new JPanel();
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

        JPanel topComponentsPanel = new JPanel();
        topComponentsPanel.setLayout(new BoxLayout(topComponentsPanel,BoxLayout.X_AXIS));
        topComponentsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*UtilGUI.getScreenHeight()),(int)(0.052*UtilGUI.getScreenWidth()),
                0,(int)(0.052*UtilGUI.getScreenWidth())));
        topComponentsPanel.setOpaque(false);

        usernameField1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        usernameField2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        roundSelectionBox.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));

        usernameField1.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));
        usernameField2.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));
        roundSelectionBox.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));

        topComponentsPanel.add(usernameField1);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),0)));
        topComponentsPanel.add(roundSelectionBox);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),0)));
        topComponentsPanel.add(usernameField2);

        confirmButton= UtilGUI.getButtonInstance("Confirm");
        confirmButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        confirmButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));

        JPanel confirmButtonPanel = new JPanel();
        confirmButtonPanel.setLayout(new BoxLayout(confirmButtonPanel,BoxLayout.X_AXIS));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*UtilGUI.getScreenHeight()),(int)(0.416*UtilGUI.getScreenWidth()),
                (int)(0.416*UtilGUI.getScreenHeight()),(int)(0.416*UtilGUI.getScreenWidth())));
        confirmButtonPanel.setOpaque(false);
        confirmButtonPanel.add(confirmButton);

        JPanel backPanel = new JPanel();

        backButton= UtilGUI.getButtonInstance("Back");
        backButton.setPreferredSize(new Dimension(175,40));

        backButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));

        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.894*UtilGUI.getScreenWidth()),
                (int)(0.009*UtilGUI.getScreenHeight()),0));

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
        FrontController.getInstance().setView(this);
        this.setVisible(true);
    }

    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        usernames.add(usernameField1.getText());
        usernames.add(usernameField2.getText());
        return usernames;
    }

    @Override
    public int getNumOfRoundsChoice() {
        return Integer.parseInt((String)roundSelectionBox.getSelectedItem());
    }

    private void setUpButtonListeners() {
        backButton.addActionListener(e -> {
            playFrame.setVisible(true);
            TwoPlayersSelectionFrame.this.setVisible(false);
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
        roundSelectionBox.addActionListener(e -> roundSelectionBox.removeItem("Select rounds:"));
        confirmButton.addActionListener(e -> {
            if (!(usernameField1.getText().equals("") || usernameField1.getText().equals("Enter username:")
            || usernameField2.getText().equals("") || usernameField2.getText().equals("Enter username:")))
                    if (!(roundSelectionBox.getSelectedItem().equals("Select rounds:"))){
                        LoadingScreenFrame loadingScreenFrame=new LoadingScreenFrame();
                        FrontController.getInstance().dispatchRequest(new
                                SetGamemodeFactoryRequest(TwoPlayersGamemodeFactory.getInstance()));
                        TwoPlayersSelectionFrame.this.setVisible(false);
                        FrontController.getInstance().dispatchRequest(new LoadRequest());
                        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
                        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
                        FrontController.getInstance().setView(TwoPlayersFrame.getInstance());
                        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(
                                TwoPlayersFrame.getInstance()));
                        loadingScreenFrame.dispose();
                    }
        });
    }
}
