package com.home.timetracker.core.routing;


import com.home.timetracker.core.AsSubscriber;
import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.ui.MainFrame;
import com.home.timetracker.ui.panel.*;

import java.awt.*;

public class RouteManager implements AsSubscriber{
    private MainFrame mainFrame;
    private ApplicationState prevState = ApplicationState.DASHBOARD;
    private ApplicationState currentState = ApplicationState.DASHBOARD;
    private PageJPanel<User> dashboardPanel;
    private PageJPanel<User> authenticationPanel;
    private PageJPanel<TrackerTask> taskInfoPanel;
    private PageJPanel<User> taskListPanel;
    private PageJPanel<TrackerTask> taskOperationsPanel;

    private User currentUser;

    public void start(){
        EventQueue.invokeLater(()-> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            this.dashboardPanel = new DashboardPanel();
            this.authenticationPanel = new AuthenticationPanel();
            this.taskInfoPanel = new TaskInfoPanel();
            this.taskListPanel = new TaskListPanel();
            this.taskOperationsPanel = new TaskOperationsPanel();

            this.authenticationPanel.init();
            mainFrame.setContentPanel(authenticationPanel);
            subscribe();
        });
    }

    @Override
    public void subscribe() {
        SubjectsStore.stateSubject.subscribe(this::changeState);
        SubjectsStore.logoutSubject.subscribe(state -> {
            this.currentUser = null;
            changeState(new ApplicationReducer<>(ApplicationState.AUTH,null));
        });
        SubjectsStore.backStateSubject.subscribe(state -> {
            this.changeState(new ApplicationReducer<>(this.prevState,this.currentUser));
        });
    }
    private void changeState(ApplicationReducer state){
        this.prevState = this.currentState;
        this.currentState = state.getState();
        switch (state.getState()){
            case DASHBOARD: {
                this.currentUser = (User) state.getPayload();
                this.dashboardPanel.setPayload(this.currentUser);
                this.mainFrame.setContentPanel(this.dashboardPanel);
                break;
            }
            case AUTH: {
                this.currentUser = null;
                this.authenticationPanel.setPayload(null);
                this.mainFrame.setContentPanel(this.authenticationPanel);
                break;
            }
            case TASKS_LIST:{
                this.taskListPanel.setPayload(this.currentUser);
                this.mainFrame.setContentPanel(this.taskListPanel);
                break;
            }
            case TASK_INFO: {
                this.taskInfoPanel.setPayload((TrackerTask) state.getPayload());
                this.mainFrame.setContentPanel(this.taskInfoPanel);
                break;
            }
            case TASK_OPERATIONS: {
                this.taskOperationsPanel.setPayload((TrackerTask) state.getPayload());
                this.mainFrame.setContentPanel(this.taskOperationsPanel);
                break;
            }
        }
    }
}
