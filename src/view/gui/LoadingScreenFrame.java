package view.gui;

import resources.utilResources.Image;
import javax.swing.*;

public class LoadingScreenFrame extends JFrame implements UI {
    public LoadingScreenFrame(){
        UtilGUI.setUpJFrameProperties(this);
        UtilGUI.setUpBackGround(this, Image.LOADING_SCREEN_PAGE_IMG);
        this.setVisible(true);
    }
}
