package view.gui;

import controller.FrontController;
import controller.requests.AddNumOfRoundsRequest;
import controller.requests.AddUsernamesRequest;
import controller.requests.LoadRequest;
import controller.requests.PreQuestionRequest;
import model.Model;
import model.gamemodes.factories.OnePlayerGamemodeFactory;
import resources.utilResources.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class OnePlayerSelectionFrame extends JFrame implements GUI{
    private PlayFrame playFrame;
    private JLabel backgroundImageLabel;
    private JPanel onePlayerSelectionPanel;
    private JPanel backPanel;
    private JButton backButton;
    private JPanel componentsPanel;
    private JButton confirmButton;
    private JComboBox roundSelectionBox;
    private JTextField usernameField;
    private String[] roundsList={"Select rounds:","1","2","3","4","5","6","7","8","9","10"};

    public OnePlayerSelectionFrame(PlayFrame playFrame){
        this.playFrame=playFrame;
        UtilGUI.setUpJFrameProperties(this);
        backgroundImageLabel = UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG);
        onePlayerSelectionPanel =new JPanel();
        onePlayerSelectionPanel.setLayout(new BorderLayout());
        onePlayerSelectionPanel.setOpaque(false);
        this.setUpLeftPanel();
        this.setUpBackPanel();
        this.connectPanels();
        this.setUpButtonListeners();
        FrontController.getInstance().setView(this);
        this.setVisible(true);
    }

    private void setUpLeftPanel(){
        componentsPanel=new JPanel();
        componentsPanel.setLayout(new GridLayout(3,1,0,(int)(0.231*UtilGUI.getScreenHeight())));
        componentsPanel.setOpaque(false);
        componentsPanel.setBorder(BorderFactory.createEmptyBorder((int)(0.138*UtilGUI.getScreenHeight()),(int)(0.078*UtilGUI.getScreenWidth()),
                (int)(0.138*UtilGUI.getScreenHeight()),(int)(0.781*UtilGUI.getScreenWidth())));

        this.setUpLeftComponents();

        componentsPanel.add(usernameField);
        componentsPanel.add(roundSelectionBox);
        componentsPanel.add(confirmButton);
    }

    private void setUpLeftComponents(){
        usernameField =new JTextField("Enter username:");
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setFont(UtilGUI.getCustomFont());

        roundSelectionBox =new JComboBox(roundsList);
        roundSelectionBox.setAlignmentX(2);
        roundSelectionBox.setSelectedIndex(0);
        roundSelectionBox.setFont(UtilGUI.getCustomFont());

        confirmButton= UtilGUI.getButtonInstance("Confirm");
    }

    private void setUpBackPanel(){
        backPanel=new JPanel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setOpaque(false);

        backButton= UtilGUI.getButtonInstance("Back");
        backButton.setPreferredSize(new Dimension((int)(0.091*UtilGUI.getScreenWidth()),(int)(0.037*UtilGUI.getScreenHeight())));
        backPanel.add(backButton,BorderLayout.LINE_END);
        backPanel.setBorder(BorderFactory.createEmptyBorder(0,(int)(0.260*UtilGUI.getScreenWidth()),(int)(0.013*UtilGUI.getScreenHeight()),
                (int)(0.007*UtilGUI.getScreenWidth())));
    }
    private void connectPanels() {
        onePlayerSelectionPanel.add(backPanel,BorderLayout.PAGE_END);
        onePlayerSelectionPanel.add(componentsPanel,BorderLayout.CENTER);
        backgroundImageLabel.add(onePlayerSelectionPanel);
    }

    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        usernames.add(usernameField.getText());
        return usernames;
    }

    @Override
    public int getNumOfRoundsChoice() {
        return Integer.parseInt((String)roundSelectionBox.getSelectedItem());
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

       // TODO add focus lost listener
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
                        LoadingScreenFrame loadingScreenFrame=  new LoadingScreenFrame();
                        Model.getInstance().setGamemodeFactory(OnePlayerGamemodeFactory.getInstance());
                        OnePlayerSelectionFrame.this.setVisible(false);
                        FrontController.getInstance().dispatchRequest(new LoadRequest());
                        FrontController.getInstance().dispatchRequest(new AddUsernamesRequest());
                        FrontController.getInstance().dispatchRequest(new AddNumOfRoundsRequest());
                        FrontController.getInstance().setView(OnePlayerFrame.getInstance());
                        FrontController.getInstance().dispatchRequest(new PreQuestionRequest(
                                OnePlayerFrame.getInstance()));
                        loadingScreenFrame.dispose();
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
}
