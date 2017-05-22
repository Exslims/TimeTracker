package com.home.timetracker.ui;


import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.panel.AuthenticationPanel;
import com.home.timetracker.ui.panel.HasView;
import com.home.timetracker.ui.panel.additional.MenuPanel;
import com.home.timetracker.ui.panel.additional.navigation.NavigationHeaderPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements AsSubscriber,HasView {
    private JPanel currentPanel;
    private MenuPanel menuPanel;
    private NavigationHeaderPanel headerPanel;
    private JPanel root;
    public MainFrame() throws HeadlessException {
        super("Time tracker");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(AppThemeColor.BACKGROUND_DARK);
        this.setPreferredSize(new Dimension(1000,700));
        this.setMinimumSize(new Dimension(1000,700));
        this.createView();
        this.subscribe();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void setContentPanel(JPanel panel){
        if(currentPanel != null){
            this.root.remove(currentPanel);
        }
        if(panel instanceof AuthenticationPanel){
            this.menuPanel.setVisible(false);
            this.headerPanel.setVisible(false);
        }else {
            this.headerPanel.setVisible(true);
        }
        this.root.add(panel,BorderLayout.CENTER);
        this.currentPanel = panel;
        this.pack();
        this.repaint();
    }

    @Override
    public void subscribe() {
        SubjectsStore.packSubject.subscribe(state -> {
            this.pack();
            this.repaint();
        });
        SubjectsStore.openMenuSubject.subscribe(state -> {
            if(this.menuPanel.isVisible()){
                this.menuPanel.setVisible(false);
            }else {
                this.menuPanel.setVisible(true);
            }
            this.pack();
            this.repaint();
        });
    }

    @Override
    public void createView() {
        this.root = new JPanel(new BorderLayout());
        headerPanel = new NavigationHeaderPanel();
        this.root.add(headerPanel,BorderLayout.PAGE_START);
        this.menuPanel = new MenuPanel();
        this.menuPanel.setVisible(false);
        this.add(menuPanel,BorderLayout.LINE_START);
        this.add(root,BorderLayout.CENTER);
    }
}
