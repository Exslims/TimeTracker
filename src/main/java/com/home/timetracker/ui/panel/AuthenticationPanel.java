package com.home.timetracker.ui.panel;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.routing.ApplicationReducer;
import com.home.timetracker.core.routing.ApplicationState;
import com.home.timetracker.core.service.TimeTrackerDataServiceStub;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.util.UIUtils;

import javax.swing.*;
import java.awt.*;

public class AuthenticationPanel extends PageJPanel<User> {
    @Override
    public void init() {
        JPanel formPanel = this.componentsFactory.getSlideJPanel(new BorderLayout());
        JLabel appIcon = this.componentsFactory.getLabel("TimeTracker",26, AppThemeColor.MENU_BG_COLOR, TextStyle.BOLD);
        appIcon.setIcon(new ImageIcon(this.componentsFactory.getIcon("app/app_icon.png", 48)));
        appIcon.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        appIcon.setForeground(AppThemeColor.MENU_BG_COLOR);
        appIcon.setBackground(AppThemeColor.MENU_BG_COLOR);
        appIcon.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(appIcon,BorderLayout.PAGE_START);

        JTextField userNameField = this.componentsFactory.getTextField("User name");
        userNameField.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        formPanel.add(userNameField,BorderLayout.CENTER);
        JButton login = this.componentsFactory.getButton("Login", TextStyle.BOLD, 20f);
        login.setPreferredSize(new Dimension(200,40));
        login.setBackground(AppThemeColor.HEADER_PRIMARY_COLOR);
        login.setForeground(AppThemeColor.BACKGROUND);
        login.addActionListener(action -> {
            User userByName = TimeTrackerDataServiceStub.INSTANCE.getUserByName(userNameField.getText());
            if(userByName != null){
                SubjectsStore.loginSubject.onNext(userByName);
                SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.DASHBOARD,userByName));
            }
        });
        formPanel.add(login,BorderLayout.PAGE_END);
        JPanel panel = UIUtils.wrapToSlide(formPanel);
        panel.setBorder(BorderFactory.createEmptyBorder(220,200,230,200));
        this.add(panel,BorderLayout.CENTER);
    }
}
