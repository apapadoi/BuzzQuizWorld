package view.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

/**
 * This class represents a feature that adds a dark background in all JComboBox components.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 12.1.2021
 */
public class DarkComboBoxUI  {
    private final BasicComboBoxUI basicComboBoxUI;

    /**
     * Default constructor.
     * @param comboBox instance of {@code JComboBox}
     */
    public DarkComboBoxUI(JComboBox comboBox) {
        basicComboBoxUI = new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    /**
                     * @see JComponent
                     */
                    @Override
                    public int getWidth() {
                        return 0;
                    }

                    /**
                     * @see JComponent
                     */
                    @Override
                    public void setFocusable(boolean focusable) {
                        super.setFocusable(false);
                    }
                };
            }

            /**
             * @see BasicComboBoxUI
             */
            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBox) {
                    /**
                     * @see BasicComboPopup
                     */
                    @Override
                    protected JScrollPane createScroller() {
                        JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scroller.getVerticalScrollBar().setUI(new NoArrowScrollBarUI(
                                Color.WHITE, 768 / 30).getBasicScrollBarUI());
                        scroller.getViewport().setOpaque(false);
                        scroller.setOpaque(false);
                        scroller.getVerticalScrollBar().setUnitIncrement(25);
                        scroller.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder());
                        scroller.getVerticalScrollBar().setBackground(new Color(54, 54, 55, 255));
                        scroller.getVerticalScrollBar().setForeground(Color.WHITE);
                        return scroller;
                    }
                };
            }
        };
    }

    /**
     * This method returns the basic JComboBoxUI.
     * @return JComboBox as {@code BasicComboBoxUI}
     */
    public BasicComboBoxUI getBasicComboBoxUI() {
        return this.basicComboBoxUI;
    }
}
