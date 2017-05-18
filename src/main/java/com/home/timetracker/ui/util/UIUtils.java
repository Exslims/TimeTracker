package com.home.timetracker.ui.util;



import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    private static ComponentsFactory componentsFactory = new ComponentsFactory();
    public static JPanel wrapToSlide(JComponent panel){
        JPanel wrapper = componentsFactory.getJPanel(new BorderLayout());
        wrapper.setBackground(AppThemeColor.BACKGROUND_DARK);
        wrapper.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        wrapper.add(panel,BorderLayout.CENTER);
        return wrapper;
    }
}
