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

public class TwoPlayersSelectionFrame extends JFrame {
    private PlayFrame playFrame;
    private Font font;
    private JLabel backgroundImageLabel;
    private JPanel twoPlayersPanel;
    private JPanel backPanel;
    private JButton backButton;
    private JPanel confirmButtonPanel;
    private JButton confirmButton;
    private JPanel topComponentsPanel;
    private JComboBox roundSelectionBox;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};


    private void setComponentsPanel() {
        twoPlayersPanel=new JPanel();
        twoPlayersPanel.setLayout(new BorderLayout());
        twoPlayersPanel.setOpaque(false);

        usernameField1=new JTextField("Enter username:");
        usernameField1.setFont(font);
        usernameField1.setHorizontalAlignment(JTextField.CENTER);
        usernameField2=new JTextField("Enter username:");
        usernameField2.setFont(font);
        usernameField2.setHorizontalAlignment(JTextField.CENTER);
        roundSelectionBox=new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(font);

        topComponentsPanel=new JPanel();
        topComponentsPanel.setLayout(new BoxLayout(topComponentsPanel,BoxLayout.X_AXIS));
        topComponentsPanel.setBorder(BorderFactory.createEmptyBorder(200,100,0,100));
        topComponentsPanel.setOpaque(false);

        usernameField1.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        usernameField2.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        roundSelectionBox.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));

        usernameField1.setPreferredSize(new Dimension(100,50));
        usernameField2.setPreferredSize(new Dimension(100,50));
        roundSelectionBox.setPreferredSize(new Dimension(100,50));

        topComponentsPanel.add(usernameField1);
        topComponentsPanel.add(Box.createRigidArea(new Dimension(300,0)));
        topComponentsPanel.add(roundSelectionBox);
        topComponentsPanel.add(Box.createRigidArea(new Dimension(300,0)));
        topComponentsPanel.add(usernameField2);

        confirmButton=new JButton("Confirm");
        confirmButton.setFont(font);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorderPainted(false);

        confirmButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        confirmButton.setPreferredSize(new Dimension(175,50));

        confirmButtonPanel=new JPanel();
        confirmButtonPanel.setLayout(new BoxLayout(confirmButtonPanel,BoxLayout.X_AXIS));
        confirmButtonPanel.setBorder(BorderFactory.createEmptyBorder(200,800,450,800));
        confirmButtonPanel.setOpaque(false);
        confirmButtonPanel.add(confirmButton);

        backPanel=new JPanel();
        backButton=new JButton("Back");
        backButton.setFont(font);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(175,40));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,1717,10,0));


        twoPlayersPanel.add(topComponentsPanel,BorderLayout.PAGE_START);
        twoPlayersPanel.add(backPanel,BorderLayout.PAGE_END);
        twoPlayersPanel.add(confirmButtonPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(twoPlayersPanel);
    }

    public TwoPlayersSelectionFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
        this.loadFont();
        this.setUpJFrameProperties();
        this.setUpBackGround();
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
        backButton.addActionListener(this.getButtonSoundListener());
        confirmButton.addActionListener(this.getButtonSoundListener());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(true);
                TwoPlayersSelectionFrame.this.setVisible(false);
            }
        });

        usernameField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField1.getText().equals("Enter username:"))
                    usernameField1.setText("");
            }
        });

        backgroundImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField1.getText().equals(""))
                    usernameField1.setText("Enter username:");
                if (usernameField2.getText().equals(""))
                    usernameField2.setText("Enter username:");
            }
        });

        usernameField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField2.getText().equals("Enter username:"))
                    usernameField2.setText("");

            }
        });

        roundSelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundSelectionBox.removeItem("Select rounds:");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(usernameField1.getText().equals("") || usernameField1.getText().equals("Enter username:")
                || usernameField2.getText().equals("") || usernameField2.getText().equals("Enter username:")))
                        if (!(roundSelectionBox.getSelectedItem().equals("Select rounds:"))){
                            LoadingScreenFrame loadingScreenFrame=new LoadingScreenFrame(TwoPlayersSelectionFrame.this);
                            TwoPlayersSelectionFrame.this.setVisible(false);
                            Timer timer=new Timer();
                            TimerTask timerTask=new TimerTask() {
                                @Override
                                public void run() {
                                    loadingScreenFrame.setVisible(false);
                                    TwoPlayersFrame twoPlayersFrame=new TwoPlayersFrame(TwoPlayersSelectionFrame.this);
                                }
                            };
                            timer.schedule(timerTask,1000);
                        }
            }
        });
    }
    private void setUpBackGround() {
        this.backgroundImageLabel = new JLabel();
        this.add(backgroundImageLabel,BorderLayout.CENTER);
        java.awt.Image resizedImage = ImageFactory.createImage(resources.images.Image.ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG).getImage().
                getScaledInstance(playFrame.getScreenWidth(),playFrame.getScreenHeight(), java.awt.Image.SCALE_DEFAULT);
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
        return playFrame.getWidth();
    }

    public int getScreenHeight(){
        return playFrame.getHeight();
    }

    public ActionListener getButtonSoundListener() { return playFrame.getButtonSoundListener(); }
}
