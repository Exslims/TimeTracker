package com.home.timetracker.ui.panel.additional;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.MenuEntry;
import com.home.timetracker.core.routing.ApplicationReducer;
import com.home.timetracker.core.routing.ApplicationState;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.util.MenuListRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MenuPanel extends BaseJPanel {
    public MenuPanel() {
        super();
        this.setBackground(AppThemeColor.MENU_BG_COLOR);
        this.setPreferredSize(new Dimension(220,20));
        this.setBorder(BorderFactory.createMatteBorder(0,0,0,1,AppThemeColor.HEADER_BORDER_COLOR));
        this.createView();
    }

    @Override
    public void createView() {
        JList<MenuEntry> list = new JList<>(getEntries());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                list.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                list.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        list.setCellRenderer(new MenuListRenderer());
        list.setBackground(AppThemeColor.MENU_BG_COLOR);
        list.setSelectedIndex(0);
        list.addListSelectionListener(e ->
                list.getSelectedValue().getAction().onClick());

        JLabel appIcon = this.componentsFactory.getLabel("TimeTracker",20,AppThemeColor.MENU_FONT_COLOR, TextStyle.BOLD);
        appIcon.setIcon(new ImageIcon(this.componentsFactory.getIcon("app/app_icon.png", 36)));
        appIcon.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        appIcon.setBackground(AppThemeColor.MENU_BG_COLOR);
        this.add(list,BorderLayout.CENTER);
        this.add(appIcon,BorderLayout.PAGE_START);
    }
    @SuppressWarnings("all")
    private MenuEntry[] getEntries(){
        return new MenuEntry[] {
                new MenuEntry("Dashboard",()->{
                    SubjectsStore.stateSubject.onNext(new ApplicationReducer(ApplicationState.DASHBOARD,null));
                },new ImageIcon(this.componentsFactory.getIcon("app/dashboard_icon.png",26))),
                new MenuEntry("Tasks",()->{
                    SubjectsStore.stateSubject.onNext(new ApplicationReducer(ApplicationState.TASKS_LIST,null));
                },new ImageIcon(this.componentsFactory.getIcon("app/thickets_icon.png",26))),
                new MenuEntry("Statistic",()->{
                    SubjectsStore.stateSubject.onNext(new ApplicationReducer(ApplicationState.STATISTIC,null));
                },new ImageIcon(this.componentsFactory.getIcon("app/statistic_icon.png",26))),
                new MenuEntry("Account",()->{
                    SubjectsStore.stateSubject.onNext(new ApplicationReducer(ApplicationState.DASHBOARD,null));
                },new ImageIcon(this.componentsFactory.getIcon("app/account_icon.png",26)))
        };
    }
}
