package view.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

class NoArrowScrollBarUI {
    private final BasicScrollBarUI basicScrollBarUI;

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

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            }

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

    public BasicScrollBarUI getBasicScrollBarUI() {
        return basicScrollBarUI;
    }
}