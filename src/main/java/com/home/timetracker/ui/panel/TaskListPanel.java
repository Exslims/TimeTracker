package com.home.timetracker.ui.panel;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.panel.additional.TaskEntryPanel;
import com.home.timetracker.ui.util.VerticalScrollContainer;

import javax.swing.*;
import java.awt.*;


public class TaskListPanel extends PageJPanel<User> {
    @Override
    public void init() {
        JPanel taskGrid = this.componentsFactory.getGridJPanel(3,0,5,5);
        taskGrid.setBackground(AppThemeColor.BACKGROUND_DARK);
        VerticalScrollContainer verticalScrollContainer = new VerticalScrollContainer();
        verticalScrollContainer.setLayout(new BorderLayout());
        verticalScrollContainer.setBackground(AppThemeColor.BACKGROUND_DARK);
        verticalScrollContainer.add(taskGrid,BorderLayout.CENTER);
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(verticalScrollContainer,AppThemeColor.BACKGROUND_DARK);
        this.payload.getTasks().forEach(task -> {
            taskGrid.add(new TaskEntryPanel(task));
        });
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
