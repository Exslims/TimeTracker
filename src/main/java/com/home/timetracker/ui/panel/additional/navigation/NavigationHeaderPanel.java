package com.home.timetracker.ui.panel.additional.navigation;

import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.ComponentsFactory;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.panel.HasView;

import javax.swing.*;
import java.awt.*;


public class NavigationHeaderPanel extends JPanel implements HasView,AsSubscriber{
    private ComponentsFactory componentsFactory = new ComponentsFactory();
    public NavigationHeaderPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, AppThemeColor.HEADER_BORDER_COLOR));
        this.setPreferredSize(new Dimension(300,40));
        this.setBackground(AppThemeColor.HEADER_BG_COLOR);
        this.createView();
        this.subscribe();
    }

    @Override
    public void createView() {
        JPanel leftBar = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));
        leftBar.setBackground(AppThemeColor.HEADER_BG_COLOR);
        JButton openMenuButton = this.componentsFactory.getIconButton("app/header_menu.png", 28, AppThemeColor.HEADER_BG_COLOR);
        openMenuButton.addActionListener(action ->
                SubjectsStore.openMenuSubject.onNext(true));
        leftBar.add(openMenuButton);

        JPanel userBar = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.RIGHT));
        userBar.setBackground(AppThemeColor.HEADER_BG_COLOR);
        JPanel userProfile = this.componentsFactory.getJPanel(new FlowLayout(FlowLayout.LEFT));
        userProfile.setBackground(AppThemeColor.HEADER_BG_COLOR);
        userProfile.setBorder(BorderFactory.createEmptyBorder(-3,20,0,20));
        userProfile.add(this.componentsFactory.getIconButton("app/user_avatar.png",28,AppThemeColor.HEADER_BG_COLOR));
        userProfile.add(this.componentsFactory.getLabel("Exslims",20,AppThemeColor.HEADER_BUTTONS_COLOR, TextStyle.MEDIUM));
        userBar.add(this.componentsFactory.getIconButton("app/notification_new.png",28,AppThemeColor.HEADER_BG_COLOR));
        userBar.add(userProfile);
        userBar.add(this.componentsFactory.getIconButton("app/user_settings.png",28,AppThemeColor.HEADER_BG_COLOR));

        this.add(leftBar,BorderLayout.LINE_START);
        this.add(userBar,BorderLayout.LINE_END);
    }

    @Override
    public void subscribe() {

    }
}
