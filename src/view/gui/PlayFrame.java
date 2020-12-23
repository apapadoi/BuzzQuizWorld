package view.gui;

import resources.images.Image;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PlayFrame extends JFrame {
    private Font font;
    private IntroFrame introFrame;
    private JButton onePlayerButton;
    private JButton twoPlayersButton;
    private JPanel buttonsPanel;
    private JPanel backPanel;
    private JPanel playPanel;
    private JButton backButton;
    private JLabel backgroundImageLabel;
    private JLabel gamemodesLabel;
    private JPanel labelPanel;


    public PlayFrame(IntroFrame introFrame){
        this.introFrame=introFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonsPanel() {
        buttonsPanel =new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1,2,650,0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(740,270,50,190));

        onePlayerButton=new JButton("One Player");
        onePlayerButton.setFont(font);
        onePlayerButton.setBorderPainted(false);
        onePlayerButton.setFocusPainted(false);

        twoPlayersButton=new JButton("Two Players");
        twoPlayersButton.setFont(font);
        twoPlayersButton.setBorderPainted(false);
        twoPlayersButton.setFocusPainted(false);

        buttonsPanel.add(twoPlayersButton);
        buttonsPanel.add(onePlayerButton);

        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);


        backButton=new JButton("Back");
        backButton.setFont(font);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(175,40));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,0,15,15));

        playPanel=new JPanel();
        playPanel.setLayout(new BorderLayout());
        playPanel.setOpaque(false);

        labelPanel=new JPanel();
        labelPanel.setLayout(new GridLayout(1,1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(80,900,0,500));
        labelPanel.setOpaque(false);

        gamemodesLabel=new JLabel("Select Gamemode");
        gamemodesLabel.setFont(font);
        gamemodesLabel.setForeground(Color.WHITE);
        labelPanel.add(gamemodesLabel);

        playPanel.add(labelPanel,BorderLayout.PAGE_START);
        playPanel.add(backPanel,BorderLayout.PAGE_END);
        playPanel.add(buttonsPanel,BorderLayout.CENTER);

        backgroundImageLabel.add(playPanel);
    }


    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.PLAY_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(introFrame.getScreenWidth(),introFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void loadFont() {
        // create the custom font
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/Minecraft.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            font = customFont;
        } catch (IOException |FontFormatException e) {
            //Handle exception
            font = new Font("Serif",Font.BOLD,12);
        }
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners(){
        onePlayerButton.addActionListener(this.getButtonSoundListener());
        twoPlayersButton.addActionListener(this.getButtonSoundListener());
        backButton.addActionListener(this.getButtonSoundListener());
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnePlayerSelectionFrame onePlayerFrame=new OnePlayerSelectionFrame(PlayFrame.this);
                PlayFrame.this.setVisible(false);
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayersSelectionFrame twoPlayersFrame=new TwoPlayersSelectionFrame(PlayFrame.this);
                PlayFrame.this.setVisible(false);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introFrame.setVisible(true);
                PlayFrame.this.setVisible(false);
            }
        });
    }

    public int getScreenWidth(){
        return introFrame.getScreenWidth();
    }

    public int getScreenHeight(){
        return introFrame.getScreenHeight();
    }

    public ActionListener getButtonSoundListener() { return introFrame.getButtonSoundListener(); }
}
