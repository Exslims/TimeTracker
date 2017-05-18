package com.home.timetracker.core.routing;


import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.MainFrame;
import com.home.timetracker.ui.panel.DashboardPanel;

import java.awt.*;

public class RouteManager implements AsSubscriber{
    private MainFrame mainFrame;

    private DashboardPanel dashboardPanel;

    public void start(){
        EventQueue.invokeLater(()-> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            this.dashboardPanel = new DashboardPanel();
//            this.resultAreaPanel = new ResultAreaPanel();
//            this.testAreaPanel = new TestAreaPanel();
//            this.createTestPanel = new CreateTestPanel();

            mainFrame.setContentPanel(dashboardPanel);
            subscribe();
        });
    }

    @Override
    public void subscribe() {
        SubjectsStore.stateSubject.subscribe(state -> {
            switch (state.getState()){
                case DASHBOARD: {
                    break;
                }
            }
        });
    }
}
