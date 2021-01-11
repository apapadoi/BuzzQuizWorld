package view.gui;

import controller.FrontController;
import controller.requests.SaveScoresRequest;
import controller.requests.ShowPlayerScoresRequest;
import model.player.Player;
import resources.utilResources.Image;
import resources.utilResources.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FinishFrame extends GUI {
    private final UI gamemodeFrame;
    private JPanel textPanel;
    private JPanel buttonsPanel;
    private static final JButton respawnButton = UtilGUI.getButtonInstance("Respawn",(float)25.0);
    private static final JButton titleScreenButton = UtilGUI.getButtonInstance("Title Screen",(float)25.0);

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

    public FinishFrame(UI gamemodeFrame){
        this.gamemodeFrame = gamemodeFrame;
        this.frame.setUndecorated(true);
        this.setUpJFrameProperties();
        this.frame.setBackground(new Color(0,0,0,0));
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
        this.frame.setContentPane(centralPanel);
        this.setUpButtonListeners();
        FrontController.getInstance().dispatchRequest(new SaveScoresRequest(gamemodeFrame));
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
        JLabel label = UtilGUI.getLabelInstance("You died!",(float)70.0);
        textPanel.add(label);

        FrontController.getInstance().dispatchRequest(new ShowPlayerScoresRequest(this));
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.frame.setTitle("Buzz! Quiz World Remastered");
        this.frame.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.gamemodeFrame.getSize());
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.frame.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners() {
        respawnButton.addActionListener(e -> {
            new PlayFrame(new IntroFrame());
            FinishFrame.this.gamemodeFrame.dispose();
            FinishFrame.this.dispose();
        });
        titleScreenButton.addActionListener(e -> {
            new IntroFrame();
            FinishFrame.this.gamemodeFrame.dispose();
            FinishFrame.this.dispose();
        });
    }

    @Override
    public void updateScores(List<Player> players) {
        for(Player player:players) {
            int playerScore = player.getScore();
            JLabel label = UtilGUI.getLabelInstance(player.getUsername() + " couldn't even score above "
                    + (playerScore + 500) + " points", (float) 25.0);
            textPanel.add(label);
            label = UtilGUI.getLabelInstance("Score : " + playerScore, (float) 25.0);
            textPanel.add(label);
        }
    }
}
