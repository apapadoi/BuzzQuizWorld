package view.gui;

import controller.FrontController;
import controller.requests.SetBetAmountRequest;
import model.questions.Category;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TwoPlayersBettingFrame extends JFrame implements GUI{
    private static final TwoPlayersBettingFrame instance = new TwoPlayersBettingFrame();
    private final JPanel bettingPanel;
    private JButton confirmButton;
    private ButtonGroup rightButtonGroup;
    private ButtonGroup leftButtonGroup;
    private JLabel categoryLabel;

    public static TwoPlayersBettingFrame getInstance() {
        return instance;
    }

    private TwoPlayersBettingFrame(){
        bettingPanel=new JPanel();
        bettingPanel.setLayout(new BorderLayout());
        bettingPanel.setOpaque(false);
        UtilGUI.setUpJFrameProperties(this);
        JLabel backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
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

    private void setUpTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.05*UtilGUI.getScreenHeight()),
                (int)(0.37*UtilGUI.getScreenWidth()),0, (int)(0.39*UtilGUI.getScreenWidth())));

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

        topPanel.add(top);
        topPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.046*UtilGUI.getScreenHeight()))));
        topPanel.add(center);
        topPanel.add(Box.createRigidArea(new Dimension(0,(int)(0.055*UtilGUI.getScreenHeight()))));
        topPanel.add(bottom);

        bettingPanel.add(topPanel,BorderLayout.NORTH);
    }

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

    private void setUpButtonListeners(){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> betsSelected = new ArrayList<>(2);
                betsSelected.add(Integer.parseInt(leftButtonGroup.getSelection().getActionCommand()));
                betsSelected.add(Integer.parseInt(rightButtonGroup.getSelection().getActionCommand()));
                FrontController.getInstance().dispatchRequest(new SetBetAmountRequest(betsSelected));
                TwoPlayersFrame.getInstance().setVisible(true);
                TwoPlayersBettingFrame.this.dispose();
            }
        });
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryLabel.setText("Category : "+category.toString());
    }
}
