package view.gui;


import resources.utilResources.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrame extends JFrame implements UI{
    private JPanel topPanel;
    private JLabel errorText;
    private JButton exitButton;
    private JPanel exitPanel;
    private JPanel errorPanel;
    private JLabel backgroundImageLabel;


    public ErrorFrame() {
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ERROR_FRAME_BACKGROUND_IMG);
        errorPanel = new JPanel();
        errorPanel.setLayout(new BorderLayout());
        errorPanel.setOpaque(false);
        this.setUpTopPanel();
        this.setUpExitPanel();
        this.connectPanels();
        this.setVisible(true);
    }

    private void setUpTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.416*UtilGUI.getScreenHeight()), 0, 0, 0));
        topPanel.setOpaque(false);

        errorText = UtilGUI.getLabelInstance("There was an error while loading the questions. Please press exit and try again.", 30);

        topPanel.add(errorText, BorderLayout.CENTER);
    }

    private void setUpExitPanel() {
        exitPanel = new JPanel();
        exitPanel.setLayout(new BorderLayout());
        exitPanel.setOpaque(false);

        exitButton = UtilGUI.getButtonInstance("Exit");
        exitButton.setPreferredSize(new Dimension((int) (0.091 * UtilGUI.getScreenWidth()), (int) (0.037 * UtilGUI.getScreenHeight())));
        exitPanel.add(exitButton, BorderLayout.LINE_END);
        exitPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (0.013 * UtilGUI.getScreenHeight()), (int) (0.007 * UtilGUI.getScreenWidth())));
    }


    private void connectPanels() {
        errorPanel.add(topPanel, BorderLayout.NORTH);
        errorPanel.add(exitPanel, BorderLayout.PAGE_END);
        backgroundImageLabel.add(errorPanel);
    }

    private void setUpButtonListeners() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Exit application
            }
        });
    }
}