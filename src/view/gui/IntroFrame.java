package view.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import resources.images.Constants;
import resources.images.Image;
import resources.images.ImageFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class IntroFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private int screenWidth;
    private int screenHeight;
    private JPanel versionPanel;
    private JLabel versionLabel;
    private JLabel teamLabel;
    private JButton playButton;
    private JButton scoresButton;
    private JButton optionsButton;
    private JButton quitButton;
    private JPanel buttonsPanel;
    private JLabel logoImageLabel;
    private JPanel iconPanel;
    private JPanel optionQuitButtonsPanel;
    private ActionListener buttonSoundListener;

    public IntroFrame() {
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setUpVersionPanel();
        this.setUpIconPanel();
        this.setUpButtonsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpIconPanel() {
        iconPanel = new JPanel();
        iconPanel.setBackground(new Color(0,0,0,0));
        logoImageLabel = new JLabel();
        java.awt.Image resizedImage = ImageFactory.createImage(Image.INTRO_PAGE_LOGO).getImage().
                getScaledInstance((int)(0.186*this.screenWidth),(int)(0.362*this.screenHeight), java.awt.Image.SCALE_DEFAULT);
        this.logoImageLabel.setIcon(new ImageIcon(resizedImage));
        iconPanel.add(logoImageLabel,BorderLayout.CENTER);
        backgroundImageLabel.add(iconPanel,BorderLayout.PAGE_START);
    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(0,0,0,0));
        buttonsPanel.setLayout(new GridLayout(3,1,0,15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.362*this.screenWidth),(int)(0.333*this.screenHeight),(int)(0.362*this.screenWidth)));

        playButton = new JButton("Play");
        playButton.setFont(font);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        scoresButton = new JButton("High Scores");
        scoresButton.setFont(font);
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusPainted(false);

        optionsButton = new JButton("Options");
        optionsButton.setFont(font);
        quitButton = new JButton("Quit Game");
        quitButton.setFont(font);

        optionsButton.setPreferredSize(new Dimension((int)(0.127*this.screenWidth),(int)(0.041*this.screenHeight)));
        optionsButton.setBorderPainted(false);
        optionsButton.setFocusPainted(false);

        quitButton.setPreferredSize(new Dimension((int)(0.127*this.screenWidth),(int)(0.041*this.screenHeight)));
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);


        optionQuitButtonsPanel = new JPanel();
        optionQuitButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,13));
        optionQuitButtonsPanel.setBackground(new Color(0,0,0,0));
        optionQuitButtonsPanel.add(optionsButton);
        optionQuitButtonsPanel.add(quitButton);

        buttonsPanel.add(playButton);
        buttonsPanel.add(scoresButton);
        buttonsPanel.add(optionQuitButtonsPanel);

        backgroundImageLabel.add(buttonsPanel,BorderLayout.CENTER);
    }

    private void setUpVersionPanel() {
        this.versionPanel = new JPanel();
        versionPanel.setLayout(new BorderLayout());
        versionPanel.setBackground(new Color(0,0,0,0));

        versionLabel = new JLabel("Buzz! Quiz World 15.12.2020");
        versionLabel.setForeground(Color.WHITE);
        this.versionLabel.setFont(font);

        teamLabel = new JLabel("4 Hills Industry");
        teamLabel.setForeground(Color.WHITE);
        this.teamLabel.setFont(font);

        versionPanel.add(versionLabel,BorderLayout.LINE_START);
        versionPanel.add(teamLabel,BorderLayout.LINE_END);

        backgroundImageLabel.add(versionPanel,BorderLayout.PAGE_END);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(Image.INTRO_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(this.screenWidth,this.screenHeight, java.awt.Image.SCALE_DEFAULT);
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
        } catch (IOException|FontFormatException e) {
            //Handle exception
            font = new Font("Serif",Font.BOLD,12);
        }
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int)screenSize.getWidth();
        this.screenHeight = (int)screenSize.getHeight();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
        new JFXPanel();
        this.buttonSoundListener = e -> new MediaPlayer(new Media(new File(Constants.BUTTON_SOUND_URL).toURI().toString())).play();
    }

    private void setUpButtonListeners() {
        playButton.addActionListener(buttonSoundListener);
        optionsButton.addActionListener(buttonSoundListener);
        optionsButton.addActionListener(e -> new OptionsFrame(IntroFrame.this));
        scoresButton.addActionListener(buttonSoundListener);
        scoresButton.addActionListener(e -> new ScoresFrame(IntroFrame.this));
        quitButton.addActionListener(e -> System.exit(0));
    }

    public int getScreenWidth() { return this.screenWidth; }
    public int getScreenHeight() { return this.screenHeight; }
    public ActionListener getButtonSoundListener() { return this.buttonSoundListener; }
    public Font getFont() { return this.font; }

    public static void main(String[] args) {
        new IntroFrame();
    }
}
