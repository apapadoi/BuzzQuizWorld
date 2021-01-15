package view.gui;

import controller.FrontController;
import controller.requests.SetBetAmountRequest;
import model.player.Player;
import model.questions.Category;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the betting phase frame (while in High Stakes gamemode) for one player.
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 12.1.2021
 */
public class OnePlayerBettingFrame extends GUI {
    private static final OnePlayerBettingFrame instance = new OnePlayerBettingFrame();
    private final JLabel backgroundImageLabel;
    private JButton bettingAmountButton1;
    private JButton bettingAmountButton2;
    private JButton bettingAmountButton3;
    private JButton bettingAmountButton4;
    private JLabel categoryLabel;
    private JLabel scoreLabel;
    private JLabel gamemodeLabel;
    private final JPanel bettingPanel;
    private JPanel bettingPhasePanel;
    private JPanel buttonsPanel;
    private JLabel amountAvailableLabel;
    private JPanel centerPanel;
    private JPanel labelsPanel;
    public static OnePlayerBettingFrame getInstance() {
        return instance;
    }

    /**
     * Default constructor.
     */
    private OnePlayerBettingFrame(){
        UtilGUI.setUpJFrameProperties(frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(frame,
                Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
        bettingPanel = new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);
        this.setUpTopPanel();
        this.setUpCenterPanel();
        this.connectPanels();
        this.setUpButtonListeners();
    }

    /**
     * This method creates the top panel for betting frame.
     */
    private void setUpTopPanel(){
        bettingPhasePanel = new JPanel();
        bettingPhasePanel.setOpaque(false);
        bettingPhasePanel.setLayout(new BorderLayout());
        bettingPhasePanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        this.setUpTopPanelData();

        bettingPhasePanel.add(labelsPanel,BorderLayout.CENTER);
    }

    /**
     * This method constructs all necessary components for top panel.
     */
    private void setUpTopPanelData(){
        gamemodeLabel = UtilGUI.getLabelInstance("");
        // TODO ADD PRE QUESTION MESSAGE TO GAMEMODABLE
        JLabel bettingLabel = UtilGUI.getLabelInstance("Select your betting amount");

        scoreLabel = UtilGUI.getLabelInstance("");
        categoryLabel = UtilGUI.getLabelInstance("");

        labelsPanel = new JPanel();
        labelsPanel.setOpaque(false);
        labelsPanel.setLayout(new BoxLayout(labelsPanel,BoxLayout.Y_AXIS));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.1*UtilGUI.getScreenHeight()),(int)(0.416*UtilGUI.getScreenWidth()),
                0,0));

        labelsPanel.add(gamemodeLabel);
        labelsPanel.add(bettingLabel);
        labelsPanel.add(scoreLabel);
        labelsPanel.add(categoryLabel);
    }

    /**
     * This method creates the center panel for the frame.
     */
    private void setUpCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.138*UtilGUI.getScreenHeight()),0,
                0,(int)(0.114*UtilGUI.getScreenWidth())));
        centerPanel.setOpaque(false);

        this.setUpButtonsPanel();

        centerPanel.add(amountAvailableLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.120*UtilGUI.getScreenHeight()))));
        centerPanel.add(buttonsPanel);
    }

    /**
     * This method creates the buttons panel to be adjusted in center panel.
     */
    private void setUpButtonsPanel(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.104*UtilGUI.getScreenWidth()),
                (int)(0.490*UtilGUI.getScreenHeight()),0));
        buttonsPanel.setOpaque(false);

        this.setUpButtonPanelData();

        buttonsPanel.add(bettingAmountButton1);
        buttonsPanel.add(Box.createRigidArea(new Dimension((int)(0.026*UtilGUI.getScreenWidth()),0)));
        buttonsPanel.add(bettingAmountButton2);
        buttonsPanel.add(Box.createRigidArea(new Dimension((int)(0.026*UtilGUI.getScreenWidth()),0)));
        buttonsPanel.add(bettingAmountButton3);
        buttonsPanel.add(Box.createRigidArea(new Dimension((int)(0.026*UtilGUI.getScreenWidth()),0)));
        buttonsPanel.add(bettingAmountButton4);
    }

    /**
     * This method constructs all necessary components for the button panel.
     */
    private void setUpButtonPanelData(){
        bettingAmountButton1= UtilGUI.getButtonInstance("250");
        bettingAmountButton2= UtilGUI.getButtonInstance("500");
        bettingAmountButton3= UtilGUI.getButtonInstance("750");
        bettingAmountButton4= UtilGUI.getButtonInstance("1000");

        amountAvailableLabel = UtilGUI.getLabelInstance("Amounts Available");

        bettingAmountButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        bettingAmountButton1.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        bettingAmountButton2.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        bettingAmountButton3.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
        bettingAmountButton4.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.055*UtilGUI.getScreenHeight())));
    }

    /**
     * This method connects all available panels.
     */
    private void connectPanels(){
        bettingPanel.add(centerPanel,BorderLayout.CENTER);
        bettingPanel.add(bettingPhasePanel,BorderLayout.PAGE_START);
        backgroundImageLabel.add(bettingPanel);
    }

    /**
     * This method sets button listeners for all available betting buttons.
     */
    // TODO SET ACTION COMMANDS IN ALL BUTTONS
    private void setUpButtonListeners() {
        ActionListener listener = e -> {
            List<Integer> betsSelected = new ArrayList<>();
            betsSelected.add(Integer.parseInt(e.getActionCommand()));
            FrontController.getInstance().dispatchRequest(new SetBetAmountRequest(betsSelected));
            OnePlayerFrame.getInstance().setVisible(true);
            OnePlayerBettingFrame.this.dispose();
        };

        this.bettingAmountButton1.addActionListener(listener);
        this.bettingAmountButton2.addActionListener(listener);
        this.bettingAmountButton3.addActionListener(listener);
        this.bettingAmountButton4.addActionListener(listener);
    }

    /**
     * @see UI
     */
    @Override
    public void updateCategory(Category category) {
        this.categoryLabel.setText("Category : "+category.toString());
    }

    /**
     * @see UI
     */
    @Override
    public void updatePlayerData(List<Player> players) {
        instance.scoreLabel.setText("Score : "+ players.get(0).getScore());
    }

    /**
     * @see UI
     */
    @Override
    public void updateGamemode(String gamemodeName) {
        instance.gamemodeLabel.setText(gamemodeName);
    }
}
