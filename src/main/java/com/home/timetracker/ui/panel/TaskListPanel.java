package com.home.timetracker.ui.panel;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.core.routing.ApplicationReducer;
import com.home.timetracker.core.routing.ApplicationState;
import com.home.timetracker.core.service.TimeTrackerDataServiceStub;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.panel.additional.TaskEntryPanel;
import com.home.timetracker.ui.util.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;


public class TaskListPanel extends PageJPanel<User> {
    private JPanel taskGrid;
    private TaskFilter filter = TaskFilter.ALL;
    @Override
    public void init() {
        taskGrid = this.componentsFactory.getGridJPanel(3,0,5,5);
        taskGrid.setBackground(AppThemeColor.BACKGROUND_DARK);
        VerticalScrollContainer taskContainer = new VerticalScrollContainer();
        taskContainer.setLayout(new BorderLayout());
        taskContainer.setBackground(AppThemeColor.BACKGROUND_DARK);
        taskContainer.add(taskGrid,BorderLayout.CENTER);
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(taskContainer,AppThemeColor.BACKGROUND_DARK);
        this.fillContainer();
        this.add(getHeaderPanel(), BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
    }
    private void fillContainer(){
        this.taskGrid.removeAll();
        switch (this.filter){
            case ALL:{
                TimeTrackerDataServiceStub.INSTANCE.getTasks().forEach(task -> {
                    taskGrid.add(new TaskEntryPanel(task));
                });
                break;
            }
            case ASSIGNED_TO_ME: {
                this.payload.getTasks().forEach(task -> {
                    taskGrid.add(new TaskEntryPanel(task));
                });
            }
        }
    }
    public JPanel getHeaderPanel(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(2,2,5,2));
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        JButton addTaskButton = this.componentsFactory.getIconButton("app/add_task.png", 30, AppThemeColor.BACKGROUND_DARK);
        addTaskButton.addActionListener(action -> SubjectsStore.stateSubject.onNext(
                new ApplicationReducer<>(ApplicationState.TASK_OPERATIONS, null)));
        JComboBox<String> filterComboBox = this.componentsFactory.getComboBox(TaskFilter.class, filter.toString());
        filterComboBox.addActionListener(action -> {
            this.filter = TaskFilter.valueOf(filterComboBox.getSelectedItem().toString());
            this.fillContainer();
            SubjectsStore.packSubject.onNext(true);
        });
        root.add(filterComboBox,BorderLayout.LINE_START);
        root.add(addTaskButton,BorderLayout.LINE_END);
        return root;
    }

    private enum TaskFilter {
        ALL, ASSIGNED_TO_ME
    }
}
