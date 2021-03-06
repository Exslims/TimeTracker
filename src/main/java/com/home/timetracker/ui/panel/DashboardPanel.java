package com.home.timetracker.ui.panel;


import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.core.service.TimeTrackerDataServiceStub;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.panel.additional.TaskEntryPanel;
import com.home.timetracker.ui.util.UIUtils;
import com.home.timetracker.ui.util.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends PageJPanel<User> {
    @Override
    public void init() {
        JPanel root = this.componentsFactory.getGridJPanel(2, 1, 0, 0);
        List<TrackerTask> unassignedTasks = TimeTrackerDataServiceStub.INSTANCE.getUnassignedTasks();
        List<TrackerTask> assignedTasks = TimeTrackerDataServiceStub.INSTANCE.getUserTasks(this.payload);
        root.add(getTaskListPanel("Unassigned tasks",unassignedTasks));
        root.add(getTaskListPanel("Assigned to me tasks",assignedTasks));
        this.add(getTasksInfoPanel(),BorderLayout.PAGE_START);
        this.add(root,BorderLayout.CENTER);
    }
    private JPanel getTasksInfoPanel(){
        JPanel taskStatusPanel = this.componentsFactory.getGridJPanel(4, 1, 5, 0);
        taskStatusPanel.add(getMinimizedTaskPanel(
                TimeTrackerDataServiceStub.INSTANCE.getTasksByStatus(TaskStatus.OPEN).size(),
                "OPEN"
        ));
        taskStatusPanel.add(getMinimizedTaskPanel(
                TimeTrackerDataServiceStub.INSTANCE.getTasksByStatus(TaskStatus.IN_PROGRESS).size(),
                "IN PROGRESS"
        ));
        taskStatusPanel.add(getMinimizedTaskPanel(
                TimeTrackerDataServiceStub.INSTANCE.getTasksByStatus(TaskStatus.ON_HOLD).size(),
                "ON HOLD"
        ));
        taskStatusPanel.add(getMinimizedTaskPanel(
                TimeTrackerDataServiceStub.INSTANCE.getTasksByStatus(TaskStatus.CLOSED).size(),
                "CLOSED"
        ));
        taskStatusPanel.setBackground(AppThemeColor.BACKGROUND_DARK);
        return taskStatusPanel;
    }
    private JPanel getMinimizedTaskPanel(int taskCount, String title){
        JPanel root = this.componentsFactory.getSlideJPanel(new FlowLayout(FlowLayout.CENTER,15,0));
        root.add(this.componentsFactory.getLabel(String.valueOf(taskCount),36f,AppThemeColor.HEADER_BUTTONS_COLOR,TextStyle.BOLD,SwingConstants.RIGHT));
        root.add(this.componentsFactory.getLabel(title,18f,AppThemeColor.HEADER_BUTTONS_COLOR, TextStyle.BOLD,SwingConstants.LEFT));
        return UIUtils.wrapToSlide(root);
    }
    private JPanel getTaskListPanel(String title, List<TrackerTask> tasks){
        JPanel root = this.componentsFactory.getSlideJPanel(new BorderLayout());
        root.add(this.componentsFactory.getLabel(title,14f, AppThemeColor.HEADER_BUTTONS_COLOR),BorderLayout.PAGE_START);

        VerticalScrollContainer container = new VerticalScrollContainer();
        container.setBackground(AppThemeColor.BACKGROUND);
        container.setLayout(new GridLayout(tasks.size(),1,0,5));
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(container, AppThemeColor.BACKGROUND);

        tasks.forEach(trackerTask -> {
            container.add(new TaskEntryPanel(trackerTask));
        });
        root.add(scrollPane,BorderLayout.CENTER);
        return UIUtils.wrapToSlide(root);
    }
}
