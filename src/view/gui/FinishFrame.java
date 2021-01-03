package view.gui;

import controller.FrontController;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishFrame extends JFrame implements GUI{
    private final GUI gamemodeFrame;
    private JPanel textPanel;
    private JPanel buttonsPanel;
    private static final JButton respawnButton = UtilGUI.getButtonInstance("Respawn",(float)25.0);
    private static final JButton titleScreenButton = UtilGUI.getButtonInstance("Title Screen",(float)25.0);

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

    public FinishFrame(GUI gamemodeFrame){
        this.gamemodeFrame = gamemodeFrame;
        this.setUndecorated(true);
        this.setUpJFrameProperties();
        setBackground(new Color(0,0,0,0));
        JPanel centralPanel = new JPanel() {
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
                }
            }
        };
        centralPanel.setLayout(new BorderLayout());
        this.setUpTextPanel();
        centralPanel.add(textPanel,BorderLayout.PAGE_START);
        this.setUpButtonsPanel();
        centralPanel.add(buttonsPanel,BorderLayout.PAGE_END);
        this.setContentPane(centralPanel);
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonsPanel() {
        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setOpaque(false);
        this.buttonsPanel.setLayout(new GridLayout(2,1,0,30));
        this.buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,UtilGUI.getScreenWidth()*375/1130,
                UtilGUI.getScreenHeight()*125/527,UtilGUI.getScreenWidth()*380/1130));

        this.buttonsPanel.add(respawnButton);
        this.buttonsPanel.add(titleScreenButton);
    }

    private void setUpTextPanel() {
        this.textPanel = new JPanel();
        this.textPanel.setOpaque(false);
        this.textPanel.setLayout(new GridLayout(3,1,0,0));
        this.textPanel.setBorder(BorderFactory.createEmptyBorder(UtilGUI.getScreenHeight()*95/527,0,
                0,0));
        JLabel label = TwoPlayersGamemodeFrame.constructCustomJLabel("You died!",(float)70.0);
        textPanel.add(label);
        int playerScore = FrontController.getInstance().getModel().getScore(0);
        label = TwoPlayersGamemodeFrame.constructCustomJLabel(FrontController.getInstance().getModel().
                getUsername(0)+ " couldn't even score above " + (playerScore+500) + " points",(float)25.0);
        textPanel.add(label);
        label = TwoPlayersGamemodeFrame.constructCustomJLabel("Score : "+playerScore,(float)25.0);
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

    private void setUpButtonListeners() {
        respawnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayFrame(new IntroFrame());
                FinishFrame.this.gamemodeFrame.dispose();
                FinishFrame.this.dispose();
            }
        });

        titleScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IntroFrame();
                FinishFrame.this.gamemodeFrame.dispose();
                FinishFrame.this.dispose();
            }
        });
    }
}
