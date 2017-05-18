package com.home.timetracker.ui;


import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.panel.HasView;
import com.home.timetracker.ui.panel.additional.MenuPanel;
import com.home.timetracker.ui.panel.additional.navigation.NavigationHeaderPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements AsSubscriber,HasView {
    private JPanel currentPanel;
    private MenuPanel menuPanel;
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
            this.remove(currentPanel);
        }
        this.add(panel,BorderLayout.CENTER);
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
        this.add(new NavigationHeaderPanel(),BorderLayout.PAGE_START);
        this.menuPanel = new MenuPanel();
        this.menuPanel.setVisible(false);
        this.add(menuPanel,BorderLayout.LINE_START);
    }
}
