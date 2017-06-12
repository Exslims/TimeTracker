package com.home.timetracker.ui.panel;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.task.TaskPriority;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TaskType;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.core.service.TimeTrackerDataServiceStub;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.TextStyle;
import com.home.timetracker.ui.util.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class TaskOperationsPanel extends PageJPanel<TrackerTask> {
    private DataBindListenersPool listenersPool;
    private boolean newTask = true;
    @Override
    public void init() {
        this.listenersPool = new DataBindListenersPool();
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        root.add(getHeaderPanel(),BorderLayout.PAGE_START);

        JPanel grid = this.componentsFactory.getGridJPanel(2, 0, 0, 5);
        grid.setBackground(AppThemeColor.BACKGROUND);
        grid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR),
                BorderFactory.createEmptyBorder(4,4,4,4)
        ));
        grid.add(this.componentsFactory.getLabel("Task title: "));
        JTextField titleField = this.componentsFactory.getTextField(this.payload.getTaskTitle());
        this.listenersPool.addListener(()->this.payload.setTaskTitle(titleField.getText()));
        grid.add(titleField);
        grid.add(this.componentsFactory.getLabel("Task description: "));
        JTextField descField = this.componentsFactory.getTextField(this.payload.getDescription());
        this.listenersPool.addListener(()->this.payload.setDescription(descField.getText()));
        grid.add(descField);
        grid.add(this.componentsFactory.getLabel("Task priority: "));
        JComboBox<String> priorityCB = this.componentsFactory.getComboBox(TaskPriority.class, this.payload.getPriority().toString());
        this.listenersPool.addListener(()->this.payload.setPriority(TaskPriority.valueOf(priorityCB.getSelectedItem().toString())));
        grid.add(priorityCB);
        grid.add(this.componentsFactory.getLabel("Task status: "));
        JComboBox<String> statusCB = this.componentsFactory.getComboBox(TaskStatus.class, this.payload.getStatus().toString());
        this.listenersPool.addListener(()->this.payload.setStatus(TaskStatus.valueOf(statusCB.getSelectedItem().toString())));
        grid.add(statusCB);
        grid.add(this.componentsFactory.getLabel("Task type: "));
        JComboBox<String> typeCB = this.componentsFactory.getComboBox(TaskType.class, this.payload.getType().toString());
        this.listenersPool.addListener(()->this.payload.setType(TaskType.valueOf(typeCB.getSelectedItem().toString())));
        grid.add(typeCB);
        grid.add(this.componentsFactory.getLabel("Estimated time(in hours): "));
        JTextField estimatedField = this.componentsFactory.getTextField(String.valueOf(this.payload.getEstimatedTime()));
        this.listenersPool.addListener(()->this.payload.setEstimatedTime(Integer.valueOf(estimatedField.getText())));
        grid.add(estimatedField);
        grid.add(this.componentsFactory.getLabel("Logged time(in hours): "));
        JTextField loggedField = this.componentsFactory.getTextField(String.valueOf(this.payload.getDueTime()));
        this.listenersPool.addListener(()->this.payload.setDueTime(Integer.valueOf(loggedField.getText())));
        grid.add(loggedField);

        root.add(grid,BorderLayout.CENTER);
        this.add(UIUtils.wrapToSlide(root), BorderLayout.PAGE_START);
    }
    private JPanel getHeaderPanel(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(2,2,5,2));
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        JButton cancelButton = this.componentsFactory.getButton("CANCEL", TextStyle.BOLD, 14f);
        cancelButton.addActionListener(action -> SubjectsStore.backStateSubject.onNext(true));
        JButton saveButton = this.componentsFactory.getButton("SAVE", TextStyle.BOLD, 14f);
        saveButton.addActionListener(action -> {
            this.listenersPool.save();
            if(newTask) {
                TimeTrackerDataServiceStub.INSTANCE.addTask(this.payload);
            }
            SubjectsStore.backStateSubject.onNext(true);
        });

        root.add(cancelButton,BorderLayout.LINE_START);
        root.add(saveButton,BorderLayout.LINE_END);
        return root;
    }

    @Override
    public void setPayload(TrackerTask payload) {
        this.payload = payload;
        if(this.payload != null){
            this.newTask = false;
        }else {
            this.payload = new TrackerTask();
        }
        this.removeAll();
        this.init();
        SubjectsStore.packSubject.onNext(true);
    }

    private interface DataBindListener {
        void onBind();
    }
    private class DataBindListenersPool {
        private List<DataBindListener> pool = new ArrayList<>();
        void addListener(DataBindListener listener) {
            this.pool.add(listener);
        }
        void save(){
           this.pool.forEach(DataBindListener::onBind);
        }
    }
}
