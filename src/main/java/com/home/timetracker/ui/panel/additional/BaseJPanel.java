package com.home.timetracker.ui.panel.additional;


import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

public abstract class BaseJPanel extends JPanel {
    protected ComponentsFactory componentsFactory = new ComponentsFactory();
    public BaseJPanel(){
        super(new BorderLayout());
        this.setBackground(AppThemeColor.BACKGROUND);
    }
    protected abstract void createView();
}
