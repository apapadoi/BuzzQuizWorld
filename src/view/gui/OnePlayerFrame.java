package view.gui;

import javafx.css.Size;
import resources.images.ImageFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OnePlayerFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private OnePlayerSelectionFrame onePlayerSelectionFrame;
    private JPanel onePlayerPanel;
    private JPanel questionsPanel;
    private JLabel questionsLabel;
    private JLabel roundLabel;
    private JPanel roundPanel;
    private JLabel timerLabel;
    private JPanel timerPanel;
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
        questionsPanel.setLayout(new BorderLayout());
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(50,0,30,0));
        questionsPanel.setOpaque(false);

        questionsLabel =new JLabel("<html>What is the most visited country of<br/>all four given?</html>",SwingConstants.CENTER);
        questionsLabel.setFont(font);
        questionsLabel.setForeground(Color.WHITE);

        roundLabel=new JLabel("Round 1");
        roundLabel.setFont(font);
        roundLabel.setForeground(Color.WHITE);

        roundPanel=new JPanel();
        roundPanel.setOpaque(false);
        roundPanel.setLayout(new BorderLayout());
        roundPanel.setBorder(BorderFactory.createEmptyBorder(20,0,30,215));

        roundPanel.add(roundLabel);

        timerLabel=new JLabel("30 seconds");
        timerLabel.setFont(font);
        timerLabel.setForeground(Color.WHITE);

        timerPanel=new JPanel();
        timerPanel.setOpaque(false);
        timerLabel.setLayout(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(20,215,30,0));

        timerPanel.add(timerLabel,BorderLayout.CENTER);
        questionsPanel.add(timerPanel,BorderLayout.LINE_START);
        questionsPanel.add(questionsLabel,BorderLayout.CENTER);
        questionsPanel.add(roundPanel,BorderLayout.LINE_END);

        answersPanel=new JPanel();
        answersPanel.setOpaque(false);
        answersPanel.setLayout(new BoxLayout(answersPanel,BoxLayout.Y_AXIS));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(50,300,170,100));
        answersButton1=new JButton("Spain");
        answersButton1.setFont(font);
        answersButton2=new JButton("France");
        answersButton2.setFont(font);
        answersButton3=new JButton("Fiji");
        answersButton3.setFont(font);
        answersButton4=new JButton("Finland");
        answersButton4.setFont(font);


        answersButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        answersButton1.setPreferredSize(new Dimension(300,60));
        answersButton2.setPreferredSize(new Dimension(300,60));
        answersButton3.setPreferredSize(new Dimension(300,60));
        answersButton4.setPreferredSize(new Dimension(300,60));
        answersPanel.add(answersButton1);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton2);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton3);
        answersPanel.add(Box.createRigidArea(new Dimension(0,100)));
        answersPanel.add(answersButton4);



        onePlayerPanel.add(questionsPanel,BorderLayout.PAGE_START);
        onePlayerPanel.add(answersPanel,BorderLayout.LINE_END);
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
