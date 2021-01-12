package view.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * This class represents a feature that adds a scroll bar.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
class NoArrowScrollBarUI {
    private final BasicScrollBarUI basicScrollBarUI;

    /**
     * Default constructor.
     * @param buttonColor instance of {@code Color}
     * @param arcValue instance of {@code int}
     */
    public NoArrowScrollBarUI(Color buttonColor,int arcValue) {
        basicScrollBarUI = new BasicScrollBarUI() {

            protected JButton createZeroButton() {
                JButton button = new JButton("");
                Dimension zeroDim = new Dimension(0, 0);
                button.setPreferredSize(zeroDim);
                button.setMinimumSize(zeroDim);
                button.setMaximumSize(zeroDim);
                return button;
            }

            /**
             * @see BasicScrollBarUI
             */
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            /**
             * @see BasicScrollBarUI
             */
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            /**
             * @see BasicScrollBarUI
             */
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            }

            /**
             * @see BasicScrollBarUI
             */
            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D)g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(Color.WHITE);
                g2.fillRoundRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,
                        thumbBounds.height,arcValue,arcValue);
                g2.setPaint(buttonColor);
                g2.drawRoundRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,
                        thumbBounds.height,arcValue,arcValue);
                g2.dispose();
            }
        };
    }

    /**
     * This method returns the scroll bar.
     * @return scroll bar as {@code BasicScrollBarUI}
     */
    public BasicScrollBarUI getBasicScrollBarUI() {
        return basicScrollBarUI;
    }
}