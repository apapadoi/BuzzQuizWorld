package view.gui;

import controller.FrontController;
import controller.requests.SetBetAmountRequest;
import model.player.Player;
import model.questions.Category;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the betting phase frame (while in High Stakes gamemode) for two player.
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 12.1.2021
 */
public class TwoPlayersBettingFrame extends GUI {
    private static final TwoPlayersBettingFrame instance = new TwoPlayersBettingFrame();
    private final JPanel bettingPanel;
    private JButton confirmButton;
    private ButtonGroup rightButtonGroup;
    private ButtonGroup leftButtonGroup;
    private JLabel categoryLabel;
    private JLabel player1Data;
    private JLabel player2Data;

    public static TwoPlayersBettingFrame getInstance() {
        return instance;
    }

    /**
     * Default constructor.
     */
    private TwoPlayersBettingFrame(){
        bettingPanel=new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);
        UtilGUI.setUpJFrameProperties(frame);
        JLabel backgroundImageLabel = UtilGUI.setUpBackGround(frame,
                Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
        this.setUpTopPanel();
        this.setUpAmountPanel();
        this.setUpRightSideIcons();
        this.setUpLeftSideIcons();
        this.setUpBottomPanel();
        this.setUpButtonListeners();
        backgroundImageLabel.add(bettingPanel);
        this.setVisible(true);
    }

    /**
     * This method creates amount panel for the frames.
     */
    private void setUpAmountPanel() {
        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new GridLayout(4,1,(int)(0.007*UtilGUI.getScreenWidth()),
                (int)(0.013*UtilGUI.getScreenHeight())));
        amountPanel.setOpaque(false);
        amountPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,0));


        JLabel betAmount1 = new JLabel("250");
        betAmount1.setForeground(Color.WHITE);
        betAmount1.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));

        betAmount1.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount1);

        JLabel betAmount2 = new JLabel("500");
        betAmount2.setForeground(Color.WHITE);
        betAmount2.setFont(UtilGUI.getCustomFont().deriveFont((float) 30.0));
        betAmount2.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount2);

        JLabel betAmount3 = new JLabel("750");
        betAmount3.setForeground(Color.WHITE);
        betAmount3.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount3.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount3);

        JLabel betAmount4 = new JLabel("1000");
        betAmount4.setForeground(Color.WHITE);
        betAmount4.setFont(UtilGUI.getCustomFont().deriveFont((float)30.0));
        betAmount4.setHorizontalAlignment(JLabel.CENTER);
        amountPanel.add(betAmount4);

        bettingPanel.add(amountPanel,BorderLayout.CENTER);
    }

    /**
     * This method creates right side icons (buttons) for the second player.
     */
    private void setUpRightSideIcons() {
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridLayout(4,1,0,0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0,
                0,0,(int)(0.364*UtilGUI.getScreenWidth())));

        JRadioButton amount250=new JRadioButton("");
        amount250.setActionCommand("250");
        amount250.setSelected(true);
        JRadioButton amount500=new JRadioButton("");
        amount500.setActionCommand("500");
        JRadioButton amount750=new JRadioButton("");
        amount750.setActionCommand("750");
        JRadioButton amount1000=new JRadioButton("");
        amount1000.setActionCommand("1000");

        rightButtonGroup=new ButtonGroup();
        amount250.setOpaque(false);
        amount500.setOpaque(false);
        amount750.setOpaque(false);
        amount1000.setOpaque(false);
        rightButtonGroup.add(amount250);
        rightButtonGroup.add(amount500);
        rightButtonGroup.add(amount750);
        rightButtonGroup.add(amount1000);
        rightPanel.add(amount250);
        rightPanel.add(amount500);
        rightPanel.add(amount750);
        rightPanel.add(amount1000);

        this.bettingPanel.add(rightPanel,BorderLayout.EAST);
    }

    /**
     * This method creates left side icons (buttons) for the first player.
     */
    private void setUpLeftSideIcons() {
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridLayout(4,1,0,0));

        leftPanel.setBorder(BorderFactory.createEmptyBorder(0,
                (int)(0.364*UtilGUI.getScreenWidth()),0,0));

        JRadioButton amount250=new JRadioButton("");
        amount250.setActionCommand("250");
        amount250.setSelected(true);
        JRadioButton amount500=new JRadioButton("");
        amount500.setActionCommand("500");
        JRadioButton amount750=new JRadioButton("");
        amount750.setActionCommand("750");
        JRadioButton amount1000=new JRadioButton("");
        amount1000.setActionCommand("1000");

        leftButtonGroup =new ButtonGroup();

        amount250.setOpaque(false);
        amount500.setOpaque(false);
        amount750.setOpaque(false);
        amount1000.setOpaque(false);

        leftButtonGroup.add(amount250);
        leftButtonGroup.add(amount500);
        leftButtonGroup.add(amount750);
        leftButtonGroup.add(amount1000);

        leftPanel.add(amount250);
        leftPanel.add(amount500);
        leftPanel.add(amount750);
        leftPanel.add(amount1000);

        this.bettingPanel.add(leftPanel,BorderLayout.WEST);
    }

    /**
     * This method creates top panel for the frame.
     */
    private void setUpTopPanel() {
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(0,150,0,150));
        topPanel.setOpaque(false);


        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setLayout(new BoxLayout(topCenterPanel,BoxLayout.Y_AXIS));
        topCenterPanel.setOpaque(false);
        topCenterPanel.setBorder(BorderFactory.createEmptyBorder());

        categoryLabel =UtilGUI.getLabelInstance("");

        JLabel chooseAmountLabel = UtilGUI.getLabelInstance("Select your betting amount");

        JLabel amountLabel = UtilGUI.getLabelInstance("Amounts available");

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

        top.add(categoryLabel,BorderLayout.CENTER);
        center.add(chooseAmountLabel,BorderLayout.CENTER);
        bottom.add(amountLabel,BorderLayout.CENTER);

        topCenterPanel.add(top);
        topCenterPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.046*UtilGUI.getScreenHeight()))));
        topCenterPanel.add(center);
        topCenterPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.055*UtilGUI.getScreenHeight()))));
        topCenterPanel.add(bottom);

        JPanel topLeftPanel=new JPanel();
        topLeftPanel.setLayout(new BorderLayout());
        topLeftPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        topLeftPanel.setOpaque(false);

        JPanel topRightPanel=new JPanel();
        topRightPanel.setLayout(new BorderLayout());
        topRightPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        topRightPanel.setOpaque(false);

        player1Data =UtilGUI.getLabelInstance("");
        player2Data =UtilGUI.getLabelInstance("");
        topLeftPanel.add(player1Data);
        topRightPanel.add(player2Data);

        topPanel.add(topLeftPanel,BorderLayout.WEST);
        topPanel.add(topCenterPanel,BorderLayout.CENTER);
        topPanel.add(topRightPanel,BorderLayout.EAST);

        bettingPanel.add(topPanel,BorderLayout.NORTH);
    }

    /**
     * This method creates bottom panel for the frame.
     */
    private void setUpBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,(int)(0.092*UtilGUI.getScreenHeight()),(int)(0.052*UtilGUI.getScreenWidth())));
        bottomPanel.setOpaque(false);
        confirmButton=UtilGUI.getButtonInstance("Confirm");
        confirmButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));

        bottomPanel.add(confirmButton,BorderLayout.LINE_END);

        bettingPanel.add(bottomPanel,BorderLayout.PAGE_END);
    }

    /**
     * This method sets button listeners.
     */
    private void setUpButtonListeners(){
        confirmButton.addActionListener(e -> {
            List<Integer> betsSelected = new ArrayList<>(2);
            betsSelected.add(Integer.parseInt(leftButtonGroup.getSelection().getActionCommand()));
            betsSelected.add(Integer.parseInt(rightButtonGroup.getSelection().getActionCommand()));
            FrontController.getInstance().dispatchRequest(new SetBetAmountRequest(betsSelected));
            TwoPlayersFrame.getInstance().setVisible(true);
            TwoPlayersBettingFrame.this.dispose();
        });
    }

    /**
     * @see UI
     */
    @Override
    public void updateCategory(Category category) {
        this.categoryLabel.setText("Category : "+category.toString());
    }

    // TODO remove this

    /**
     * @see UI
     */
    @Override
    public void updatePlayerData(List<Player> players) {
        this.player1Data.setText("<html>"+players.get(0).getUsername()+"<br>"+players.get(0).getScore()+"</html>");
        this.player2Data.setText("<html>"+players.get(1).getUsername()+"<br>"+players.get(1).getScore()+"</html>");
    }
}
