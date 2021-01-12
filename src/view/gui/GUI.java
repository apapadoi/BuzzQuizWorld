package view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that contains several default implementations for methods of the {@code UI} interface.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public abstract class GUI implements UI{
    protected final JFrame frame;

    /**
     * Default constructor
     */
    public GUI() {
        this.frame = new JFrame();
    }

    /**
     * @see UI
     */
    @Override
    public Dimension getSize() {
        return this.frame.getSize();
    }

    /**
     * @see UI
     */
    @Override
    public void setVisible(boolean b) {
        this.frame.setVisible(b);
    }

    /**
     * @see UI
     */
    @Override
    public void dispose() {
        this.frame.dispose();
    }

    /**
     * @see UI
     */
    @Override
    public int getWidth() {
        return this.frame.getWidth();
    }

    /**
     * @see UI
     */
    @Override
    public int getHeight() {
        return this.frame.getHeight();
    }
}
