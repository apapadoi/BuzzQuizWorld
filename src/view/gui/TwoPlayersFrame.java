package view.gui;

import javax.swing.*;
import java.awt.*;

public class TwoPlayersFrame extends JFrame {
    private Font font;
    private JLabel backgroundImageLabel;
    private TwoPlayersSelectionFrame twoPlayersSelectionFrame;




    public TwoPlayersFrame(TwoPlayersSelectionFrame twoPlayersSelectionFrame){
        this.twoPlayersSelectionFrame=twoPlayersSelectionFrame;
        this.setVisible(true);
    }

    public int getScreenWidth(){
        return twoPlayersSelectionFrame.getWidth();
    }

    public int getScreenHeight(){
        return twoPlayersSelectionFrame.getHeight();
    }
}
