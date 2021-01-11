package view.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class DarkComboBoxUI  {
    private final BasicComboBoxUI basicComboBoxUI;

    public DarkComboBoxUI(JComboBox comboBox) {
        basicComboBoxUI = new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }

                    @Override
                    public void setFocusable(boolean focusable) {
                        super.setFocusable(false);
                    }
                };
            }

            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBox) {
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

    public BasicComboBoxUI getBasicComboBoxUI() {
        return this.basicComboBoxUI;
    }
}
