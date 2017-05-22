package com.home.timetracker.ui.panel.additional;


import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.core.routing.ApplicationReducer;
import com.home.timetracker.core.routing.ApplicationState;
import com.home.timetracker.ui.AppThemeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TaskEntryPanel extends BaseJPanel {
    private TrackerTask trackerTask;

    public TaskEntryPanel(TrackerTask trackerTask) {
        this.trackerTask = trackerTask;
        this.createView();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppThemeColor.HEADER_BUTTONS_COLOR),
                        BorderFactory.createEmptyBorder(4,4,4,4)
                ));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                        BorderFactory.createEmptyBorder(4,4,4,4)
                ));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.TASK_INFO,trackerTask));
            }
        });
    }

    @Override
    public void createView() {
        this.setPreferredSize(new Dimension(250,90));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        JPanel infoPanel = this.componentsFactory.getGridJPanel(2, 1, 0, 0);
        infoPanel.add(this.componentsFactory.getLabel(this.trackerTask.getType().toString(),13f,AppThemeColor.HEADER_BUTTONS_COLOR));
        JLabel statusLabel = this.componentsFactory.getLabel(this.trackerTask.getStatus().toString().replace("_"," "), 13f, AppThemeColor.HEADER_BUTTONS_COLOR);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        infoPanel.add(statusLabel);
        this.add(infoPanel,BorderLayout.PAGE_START);
        this.add(this.componentsFactory.getLabel(this.trackerTask.getTaskTitle(),16f, AppThemeColor.MENU_BG_COLOR),BorderLayout.CENTER);
        this.add(this.componentsFactory.getPriorityLabel(this.trackerTask.getPriority(),20),BorderLayout.LINE_END);

        JPanel progressBarsPanel = this.componentsFactory.getGridJPanel(1, 2, 0, 5);
        progressBarsPanel.add(this.componentsFactory.getProgressBar(this.trackerTask.getEstimatedTime(),this.trackerTask.getEstimatedTime()));
        progressBarsPanel.add(this.componentsFactory.getProgressBar(this.trackerTask.getEstimatedTime(),this.trackerTask.getDueTime()));
        this.add(progressBarsPanel,BorderLayout.PAGE_END);
    }
}
