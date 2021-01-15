package view.gui;

import controller.FrontController;
import controller.requests.*;
import model.gamemodes.factories.TwoPlayersGamemodeFactory;
import view.gui.utilResources.Constants;
import view.gui.utilResources.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the frame when the players are going to type their usernames and choose how many rounds they want to play.
 * @author Thodwrhs Myridis
 * @author Tasos Papadopoulos
 * @version 12.1.2021
 */
public class TwoPlayersSelectionFrame extends GUI implements SelectionFrameUI{
    private final PlayFrame playFrame;
    private final JLabel backgroundImageLabel;
    private JButton backButton;
    private JButton confirmButton;
    private JComboBox roundSelectionBox;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private final String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};
    private final JPanel twoPlayersPanel;
    private JPanel topComponentsPanel;
    private JPanel backPanel;
    private JPanel confirmButtonPanel;

    /**
     * Default constructor.
     * @param playFrame instance of {@code PlayFrame} class.
     */
    public TwoPlayersSelectionFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
        UtilGUI.setUpJFrameProperties(this.frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(this.frame, Image.ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG);
        twoPlayersPanel=new JPanel();
        twoPlayersPanel.setLayout(new BorderLayout());
        twoPlayersPanel.setOpaque(false);
        this.setUpTopPanel();
        this.setUpCenterPanel();
        this.setUpBottomPanel();
        this.connectPanels();
        this.setUpButtonListeners();
        FrontController.getInstance().setView(this);
        this.setVisible(true);
    }

    /**
     * This method creates the top panel for the frame.
     */
    private void setUpTopPanel(){
        topComponentsPanel=new JPanel();
        topComponentsPanel.setLayout(new BoxLayout(topComponentsPanel,BoxLayout.X_AXIS));
        topComponentsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*UtilGUI.getScreenHeight()),(int)(0.052*UtilGUI.getScreenWidth()),
                0,(int)(0.052*UtilGUI.getScreenWidth())));
        topComponentsPanel.setOpaque(false);

        this.setUpTopComponents();

        topComponentsPanel.add(usernameField1);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),0)));
        topComponentsPanel.add(roundSelectionBox);
        topComponentsPanel.add(Box.createRigidArea(new Dimension((int)(0.156*UtilGUI.getScreenWidth()),0)));
        topComponentsPanel.add(usernameField2);
    }

    /**
     * This method constructs necessary components for the top panel.
     */
    private void setUpTopComponents(){
        usernameField1=new JTextField("Enter username:");
        usernameField1.setFont(UtilGUI.getCustomFont());
        usernameField1.setHorizontalAlignment(JTextField.CENTER);
        usernameField1.setBackground(new Color(54,54,55,255));
        usernameField1.setForeground(Color.WHITE);
        usernameField1.setBorder(new LineBorder(Color.BLACK));
        usernameField2=new JTextField("Enter username:");
        usernameField2.setFont(UtilGUI.getCustomFont());
        usernameField2.setHorizontalAlignment(JTextField.CENTER);
        usernameField2.setBackground(new Color(54,54,55,255));
        usernameField2.setForeground(Color.WHITE);
        usernameField2.setBorder(new LineBorder(Color.BLACK));
        roundSelectionBox=new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(UtilGUI.getCustomFont());
        roundSelectionBox.setBackground(new Color(54,54,55,255));
        roundSelectionBox.setForeground(Color.WHITE);
        roundSelectionBox.setUI(new DarkComboBoxUI(roundSelectionBox).getBasicComboBoxUI());
        roundSelectionBox.setBorder(new LineBorder(Color.BLACK));
        usernameField1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        usernameField2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        roundSelectionBox.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));

        usernameField1.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));
        usernameField2.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));
        roundSelectionBox.setPreferredSize(new Dimension((int)(0.052*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));
    }

    /**
     * This method creates the center panel for the frame.
     */
    private void setUpCenterPanel(){
        confirmButton= UtilGUI.getButtonInstance("Confirm");
        confirmButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        confirmButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.046*UtilGUI.getScreenHeight())));

        confirmButtonPanel = new JPanel();
        confirmButtonPanel.setLayout(new BoxLayout(confirmButtonPanel,BoxLayout.X_AXIS));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.185*UtilGUI.getScreenHeight()),(int)(0.416*UtilGUI.getScreenWidth()),
                (int)(0.400*UtilGUI.getScreenHeight()),(int)(0.416*UtilGUI.getScreenWidth())));
        confirmButtonPanel.setOpaque(false);
        confirmButtonPanel.add(confirmButton);
    }

    /**
     * This method creates the bottom panel for the frame.
     */
    private void setUpBottomPanel(){
        backButton= UtilGUI.getButtonInstance("Back");
        backButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));
        backPanel= UtilGUI.getBackPanel(backButton);
    }

    /**
     * This method connects all available panels.
     */
    private void connectPanels() {
        twoPlayersPanel.add(topComponentsPanel,BorderLayout.PAGE_START);
        twoPlayersPanel.add(backPanel,BorderLayout.PAGE_END);
        twoPlayersPanel.add(confirmButtonPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(twoPlayersPanel);
    }

    /**
     * @see UI
     */
    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        usernames.add(usernameField1.getText());
        usernames.add(usernameField2.getText());
        return usernames;
    }

    /**
     * @see UI
     */
    @Override
    public int getNumOfRoundsChoice() {
        return Integer.parseInt((String)roundSelectionBox.getSelectedItem());
    }

    /**
     * This method sets all button listeners.
     */
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
            if (usernameField1.getText().equals(usernameField2.getText()))
                return;
            if (usernameField1.getText().equals(""))
                return;
            if (usernameField2.getText().equals(""))
                    return;
            if (usernameField1.getText().equals("Enter username:"))
                return;
            if (usernameField2.getText().equals("Enter username:"))
                return;
            if (roundSelectionBox.getSelectedItem().equals("Select rounds:"))
                return;
            FrontController.getInstance().dispatchRequest(new
                    SetGamemodeFactoryRequest(TwoPlayersGamemodeFactory.getInstance()));
            FrontController.getInstance().dispatchRequest(new LoadRequest());
            FrontController.getInstance().dispatchRequest(new SetMaximumPlayersRequest(2));
            FrontController.getInstance().dispatchRequest(new ClearDataRequest());
            FrontController.getInstance().dispatchRequest(
                    new AddUsernamesRequest(TwoPlayersSelectionFrame.this));
            FrontController.getInstance().dispatchRequest(
                    new AddNumOfRoundsRequest(TwoPlayersSelectionFrame.this));
            FrontController.getInstance().setView(TwoPlayersFrame.getInstance());
            FrontController.getInstance().dispatchRequest(new UpdateDataRequest(-1,
                    -1,0));
            FrontController.getInstance().dispatchRequest(new PreQuestionRequest(
                    TwoPlayersFrame.getInstance()));
            FrontController.getInstance().dispatchRequest(new StopSoundRequest());
            TwoPlayersSelectionFrame.this.setVisible(false);
            FrontController.getInstance().dispatchRequest(new PlaySoundRequest(Constants.GAMEPLAY_SOUND_URL));
        });
    }
}
