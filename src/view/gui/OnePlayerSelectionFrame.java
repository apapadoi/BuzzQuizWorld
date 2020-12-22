package view.gui;

import resources.images.ImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class OnePlayerSelectionFrame extends JFrame {
    private Font font;
    private PlayFrame playFrame;
    private JLabel backgroundImageLabel;
    private JPanel onePlayerPanel;
    private JPanel backPanel;
    private JButton backButton;
    private JPanel componentsPanel;
    private JButton confirmButton;
    private JComboBox roundSelectionBox;
    private JTextField usernameField;
    private String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};


    public OnePlayerSelectionFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setComponentsPanel() {
        onePlayerPanel=new JPanel();
        onePlayerPanel.setLayout(new BorderLayout());
        onePlayerPanel.setOpaque(false);

        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);

        backButton=new JButton("Back");
        backButton.setFont(font);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension((int)(0.091*playFrame.getScreenWidth()),(int)(0.037*playFrame.getScreenHeight())));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.260*playFrame.getScreenWidth()),(int)(0.013*playFrame.getScreenHeight()),
                (int)(0.007*playFrame.getScreenWidth())));


        componentsPanel=new JPanel();
        componentsPanel.setLayout(new GridLayout(3,1,0,(int)(0.231*playFrame.getScreenHeight())));
        componentsPanel.setOpaque(false);
        componentsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.138*playFrame.getScreenHeight()),(int)(0.078*playFrame.getScreenWidth()),
                (int)(0.138*playFrame.getScreenHeight()),(int)(0.781*playFrame.getScreenWidth())));

        usernameField =new JTextField("Enter username:");
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setFont(font);

        roundSelectionBox =new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(font);

        confirmButton=new JButton("Confirm");
        confirmButton.setFont(font);
        confirmButton.setBorderPainted(false);
        confirmButton.setFocusPainted(false);

        componentsPanel.add(usernameField);
        componentsPanel.add(roundSelectionBox);
        componentsPanel.add(confirmButton);

        onePlayerPanel.add(backPanel,BorderLayout.PAGE_END);
        onePlayerPanel.add(componentsPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(onePlayerPanel);
    }

    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(playFrame.getScreenWidth(),playFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
        this.setIconImage(ImageFactory.createImage(resources.images.Image.APP_ICON).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // only this for full size but not full screen
        //this.setUndecorated(true); //add this for full screen
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ignored){}
        this.setLayout(new BorderLayout());
    }

    private void setUpButtonListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(true);
                OnePlayerSelectionFrame.this.setVisible(false);
            }
        });

       usernameField.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               if (usernameField.getText().equals("Enter username:"))
                   usernameField.setText("");
           }
       });

       backgroundImageLabel.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               super.mouseClicked(e);
               if (usernameField.getText().equals(""))
                   usernameField.setText("Enter username:");
           }
       });

       confirmButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (!(usernameField.getText().equals("") || usernameField.getText().equals("Enter username:")))
                   if (!(roundSelectionBox.getSelectedItem().equals("Select rounds:"))){
                       LoadingScreenFrame loadingScreenFrame=new LoadingScreenFrame(OnePlayerSelectionFrame.this);
                       OnePlayerSelectionFrame.this.setVisible(false);
                       Timer timer=new Timer();
                       TimerTask timerTask=new TimerTask() {
                           @Override
                           public void run() {
                               loadingScreenFrame.setVisible(false);
                               OnePlayerSelectionFrame.this.setVisible(true);
                           }
                       };
                       timer.schedule(timerTask,5000);
                   }

           }
       });

       roundSelectionBox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               roundSelectionBox.removeItem("Select rounds:");
           }
       });
    }

    public int getScreenWidth(){
        return playFrame.getScreenWidth();
    }

    public int getScreenHeight(){
        return playFrame.getScreenHeight();
    }
}
