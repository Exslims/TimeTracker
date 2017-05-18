package com.home.timetracker.ui.panel.additional;

import com.home.timetracker.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;


public class MenuPanel extends BaseJPanel {
    public MenuPanel() {
        super();
        this.setBackground(AppThemeColor.MENU_BG_COLOR);
        this.setPreferredSize(new Dimension(220,20));
        this.setBorder(BorderFactory.createMatteBorder(0,0,0,1,AppThemeColor.HEADER_BORDER_COLOR));
    }

    @Override
    public void createView() {

    }
}
