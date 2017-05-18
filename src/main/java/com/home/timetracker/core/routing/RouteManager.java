package com.home.timetracker.core.routing;


import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.ui.MainFrame;

import java.awt.*;

public class RouteManager implements AsSubscriber{
    private MainFrame mainFrame;

    public void start(){
        EventQueue.invokeLater(()-> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);

//            this.dashboardPanel = new DashboardPanel();
//            this.resultAreaPanel = new ResultAreaPanel();
//            this.testAreaPanel = new TestAreaPanel();
//            this.createTestPanel = new CreateTestPanel();

//            mainFrame.setContentPanel(dashboardPanel);
            subscribe();
        });
    }

    @Override
    public void subscribe() {

    }
}
