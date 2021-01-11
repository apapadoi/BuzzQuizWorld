package view.gui;

import javax.swing.*;
import java.awt.*;

public abstract class GUI implements UI{
    protected final JFrame frame;

    public GUI() {
        this.frame = new JFrame();
    }

    @Override
    public Dimension getSize() {
        return this.frame.getSize();
    }

    @Override
    public void setVisible(boolean b) {
        this.frame.setVisible(b);
    }

    @Override
    public void dispose() {
        this.frame.dispose();
    }

    @Override
    public int getWidth() {
        return this.frame.getWidth();
    }

    @Override
    public int getHeight() {
        return this.frame.getHeight();
    }
}
