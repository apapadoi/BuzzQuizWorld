package view.gui;

import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FinishFrame extends JFrame{
    private final TwoPlayersGamemodeFrame gamemodeFrame;
    private JPanel textPanel;
    private final JPanel centralPanel;
    private JPanel buttonsPanel;

    public FinishFrame(TwoPlayersGamemodeFrame gamemodeFrame){
        this.gamemodeFrame = gamemodeFrame;
        this.setUndecorated(true);
        this.setUpJFrameProperties();
        setBackground(new Color(0,0,0,0));
        centralPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                final int R = 79;
                final int G = 45;
                final int B = 90;
                if (g instanceof Graphics2D) {
                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 195),
                                    0.0f, getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                   // //g.setColor(new Color(79, 45, 90, 215));
                    //g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        centralPanel.setLayout(new BorderLayout());
        this.setUpTextPanel();
        centralPanel.add(textPanel,BorderLayout.PAGE_START);
        this.setUpButtonsPanel();
        centralPanel.add(buttonsPanel,BorderLayout.PAGE_END);
        this.setContentPane(centralPanel);
    }

    private void setUpButtonsPanel() {
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setLayout(new GridLayout(2,1,0,30));
        this.buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,UtilGUI.getScreenWidth()*375/1130,
                UtilGUI.getScreenHeight()*125/527,UtilGUI.getScreenWidth()*380/1130));
        JButton button = new JButton("Respawn");
        button.setBackground(new Color(54,54,55,255));
        button.setForeground(new Color(156,156,156,255));
        button.setBorder(new LineBorder(Color.BLACK));
        button.setFont(UtilGUI.getCustomFont().deriveFont((float)25.0));
        button.setFocusPainted(false);
        this.buttonsPanel.add(button);
        button = new JButton("Title Screen");
        button.setBackground(new Color(54,54,55,255));
        button.setForeground(new Color(156,156,156,255));
        button.setBorder(new LineBorder(Color.BLACK));
        button.setFont(UtilGUI.getCustomFont().deriveFont((float)25.0));
        button.setFocusPainted(false);
        this.buttonsPanel.add(button);
    }

    private void setUpTextPanel() {
        this.textPanel = new JPanel();
        this.textPanel.setOpaque(false);
        this.textPanel.setLayout(new GridLayout(3,1,0,0));
        this.textPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*95/527,0,
                0,0));
        JLabel label = TwoPlayersGamemodeFrame.constructCustomJLabel("You died!",(float)70.0);
        textPanel.add(label);
        label = TwoPlayersGamemodeFrame.constructCustomJLabel("testUsername couldn't even score above 500 points",(float)25.0);
        textPanel.add(label);
        label = TwoPlayersGamemodeFrame.constructCustomJLabel("Score : 100",(float)25.0);
        textPanel.add(label);
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.gamemodeFrame.getSize());
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
    }
}
