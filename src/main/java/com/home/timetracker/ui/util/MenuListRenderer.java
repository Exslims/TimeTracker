package com.home.timetracker.ui.util;


import com.home.timetracker.core.entity.MenuEntry;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.ComponentsFactory;
import com.home.timetracker.ui.TextStyle;

import javax.swing.*;
import java.awt.*;

public class MenuListRenderer extends JButton implements ListCellRenderer<MenuEntry> {
    private ComponentsFactory componentsFactory = new ComponentsFactory();
    @Override
    public Component getListCellRendererComponent(JList<? extends MenuEntry> list, MenuEntry value, int index, boolean isSelected, boolean cellHasFocus) {
        JButton button = this.componentsFactory.getButton(value.getText(), TextStyle.REGULAR,18f);
        button.setForeground(AppThemeColor.MENU_FONT_COLOR);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(AppThemeColor.MENU_BG_COLOR);
        button.setPreferredSize(new Dimension(220,50));
        button.setIcon(value.getImageIcon());
        if(isSelected){
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(0,14,0,0),
                    BorderFactory.createMatteBorder(0,0,0,4,AppThemeColor.DARK_PRIMARY_COLOR)));
            button.setBackground(AppThemeColor.MENU_SELECTED_COLOR);
        }else {
            button.setBorder(BorderFactory.createEmptyBorder(0,10,0,4));
        }
        button.addActionListener(action -> {
            value.getAction().onClick();
        });
        return button;
    }
}
