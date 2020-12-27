package view.gui;

import javax.swing.*;

public class LoadingScreenFrame extends JFrame implements GUI{
    public LoadingScreenFrame(){
        UtilGUI.setUpJFrameProperties(this);
        UtilGUI.setUpBackGround(this, resources.images.Image.LOADING_SCREEN_PAGE_IMG);
        this.setVisible(true);
    }
}
