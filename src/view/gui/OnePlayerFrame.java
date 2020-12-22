package view.gui;

import javafx.css.Size;
import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OnePlayerFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel onePlayerPanel;
    private JPanel questionsPanel;
    private JLabel questionsLabel;
    private JPanel answersPanel;
    private JButton answersButton1;
    private JButton answersButton2;
    private JButton answersButton3;
    private JButton answersButton4;


    private void setComponentsPanel() {
        onePlayerPanel=new JPanel();
        onePlayerPanel.setOpaque(false);
        onePlayerPanel.setLayout(new BorderLayout());


        questionsPanel=new JPanel();
        questionsPanel.setLayout(new GridBagLayout());
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

        questionsLabel =new JLabel("When did you die?");
        questionsLabel.setFont(font);
        questionsPanel.add(questionsLabel);

        answersPanel=new JPanel();
        answersPanel.setLayout(new GridLayout(4,1,200,30));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(25,100,100,100));

        answersButton1=new JButton("1995");
        answersButton1.setFont(font);
        answersButton2=new JButton("1998");
        answersButton2.setFont(font);
        answersButton3=new JButton("1975");
        answersButton3.setFont(font);
        answersButton4=new JButton("1996");
        answersButton4.setFont(font);

        answersPanel.add(answersButton1);
        answersPanel.add(answersButton2);
        answersPanel.add(answersButton3);
        answersPanel.add(answersButton4);



        onePlayerPanel.add(questionsPanel,BorderLayout.NORTH);
        onePlayerPanel.add(answersPanel,BorderLayout.EAST);
        backgroundImageLabel.add(onePlayerPanel);
    }


    public OnePlayerFrame(OnePlayerSelectionFrame onePlayerSelectionFrame){
        this.onePlayerSelectionFrame=onePlayerSelectionFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {

    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(onePlayerSelectionFrame.getScreenWidth(),onePlayerSelectionFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
        this.backgroundImageLabel.setIcon(new ImageIcon(resizedImage));
        this.backgroundImageLabel.setLayout(new BorderLayout());
    }

    private void setUpJFrameProperties() {
        // set properties of JFrame
        this.setTitle("Buzz! Quiz World Remastered");
        this.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
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

    public int getScreenWidth(){
        return onePlayerSelectionFrame.getScreenWidth();
    }

    public int getScreenHeight(){
        return onePlayerSelectionFrame.getScreenHeight();
    }
}
