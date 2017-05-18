package com.home.timetracker.ui.util;

import com.home.timetracker.ui.AppThemeColor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollUI extends BasicScrollBarUI {
    private final Dimension d = new Dimension();
    private Color scrollColor;

    public ScrollUI(Color color) {
        this.scrollColor = color;
    }
    public ScrollUI() {
        this.scrollColor = AppThemeColor.PRIMARY_COLOR;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = null;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled()) {
            return;
        } else {
            color = scrollColor;
        }
        g2.setPaint(color);
        g2.fillRect(r.x, r.y, r.width, r.height);
        g2.setPaint(AppThemeColor.BACKGROUND_DARK);
        g2.drawRect(r.x, r.y, r.width, r.height);
        g2.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}
