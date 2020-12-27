package view.gui;

import resources.images.Image;
import javax.swing.*;

public class TwoPlayersBettingFrame extends JFrame implements GUI{

    public TwoPlayersBettingFrame(){
        UtilGUI.setUpJFrameProperties(this);
        UtilGUI.setUpBackGround(this, Image.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG);
        this.setComponentsPanel();
        this.setUpButtonListeners();
        this.setVisible(true);
    }

    private void setUpButtonListeners() {
    }

    private void setComponentsPanel() {
    }
}
