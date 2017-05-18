package com.home.timetracker.ui.panel;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.ui.panel.PageJPanel;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPanel extends PageJPanel {
    @Override
    protected void init() {
        JPanel formPanel = this.componentsFactory.getSlideJPanel(new BorderLayout());
    }
}
