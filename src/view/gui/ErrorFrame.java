package view.gui;

import view.gui.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the frame that will appear if any error occurs while the game is loading the questions.
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public class ErrorFrame extends GUI{
    private JPanel topPanel;
    private JButton exitButton;
    private JPanel exitPanel;
    private final JPanel errorPanel;
    private final JLabel backgroundImageLabel;

    /**
     * Default constructor.
     */
    public ErrorFrame() {
        UtilGUI.setUpJFrameProperties(frame);
        backgroundImageLabel = UtilGUI.setUpBackGround(frame, Image.ERROR_FRAME_BACKGROUND_IMG);
        errorPanel = new JPanel();
        errorPanel.setLayout(new BorderLayout());
        errorPanel.setOpaque(false);
        this.setUpTopPanel();
        this.setUpExitPanel();
        this.connectPanels();
        this.setUpButtonListeners();
        Thread errorThread = new Thread(() -> {
            try {
                Thread.sleep(550);
            }catch(InterruptedException exception) {

            }
            ErrorFrame.this.setVisible(true);
        });
        errorThread.start();
   }

    /**
     * This method creates the top panel for the frame.
     */
    private void setUpTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.416*UtilGUI.getScreenHeight()), 0, 0, 0));
        topPanel.setOpaque(false);

        JLabel errorText = UtilGUI.getLabelInstance(
                "There was an error with the file system. Please press exit and try again.", 30);

        topPanel.add(errorText, BorderLayout.CENTER);
    }

    /**
     * This method creates the exit panel for the frame.
     */
    private void setUpExitPanel() {
        exitPanel = new JPanel();
        exitPanel.setLayout(new BorderLayout());
        exitPanel.setOpaque(false);

        exitButton = UtilGUI.getButtonInstance("Exit");
        exitButton.setPreferredSize(new Dimension((int) (0.091 * UtilGUI.getScreenWidth()), (int) (0.037 * UtilGUI.getScreenHeight())));
        exitPanel.add(exitButton, BorderLayout.LINE_END);
        exitPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (0.013 * UtilGUI.getScreenHeight()), (int) (0.007 * UtilGUI.getScreenWidth())));
    }

    /**
     * This method connects all available panels.
     */
    private void connectPanels() {
        errorPanel.add(topPanel, BorderLayout.NORTH);
        errorPanel.add(exitPanel, BorderLayout.PAGE_END);
        backgroundImageLabel.add(errorPanel);
    }

    /**
     * This method sets button listeners.
     */
    private void setUpButtonListeners() {
        exitButton.addActionListener(e -> System.exit(-1));
    }
}