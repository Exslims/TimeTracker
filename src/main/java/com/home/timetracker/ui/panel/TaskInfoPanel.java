package com.home.timetracker.ui.panel;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.task.TrackerTask;
import com.home.timetracker.core.routing.ApplicationReducer;
import com.home.timetracker.core.routing.ApplicationState;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.plot.ForegroundDataPlot;
import com.home.timetracker.ui.plot.ForegroundPiePlot;
import com.home.timetracker.ui.util.UIUtils;
import com.home.timetracker.ui.util.VerticalScrollContainer;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class TaskInfoPanel extends PageJPanel<TrackerTask> {
    @Override
    public void init() {
        this.initHeaderPanel();

        JPanel taskInfoPanel = this.componentsFactory.getSlideJPanel(new BorderLayout());
        taskInfoPanel.add(this.componentsFactory.getLabel(this.payload.getTaskTitle(),18f,AppThemeColor.HEADER_BUTTONS_COLOR),BorderLayout.PAGE_START);

        ForegroundDataPlot foregroundDataPlot = new ForegroundDataPlot();
        taskInfoPanel.add(foregroundDataPlot,BorderLayout.PAGE_END);

        this.add(UIUtils.wrapToSlide(taskInfoPanel),BorderLayout.CENTER);
        this.add(UIUtils.wrapToSlide(getTaskConstraintsPanel()),BorderLayout.LINE_END);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                char[] wdTitle = new char[512];
                WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
                User32.INSTANCE.GetWindowText(hwnd,wdTitle,512);
                foregroundDataPlot.add(Native.toString(wdTitle));
            }
        }, 0, 1000);
    }
    private JScrollPane getTaskConstraintsPanel(){
        VerticalScrollContainer container = new VerticalScrollContainer();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.setBackground(AppThemeColor.BACKGROUND_DARK);
        JScrollPane scrollPane = this.componentsFactory.getScrollPane(container, AppThemeColor.BACKGROUND_DARK);
        scrollPane.setBorder(null);
        JPanel taskConstraintsPanel = this.componentsFactory.getSlideJPanel(new GridLayout(0,2,5,5));
        taskConstraintsPanel.add(this.componentsFactory.getLabel("Status:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getLabel(this.payload.getStatus().toString().replace("_"," "),14f,AppThemeColor.HEADER_BUTTONS_COLOR));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Type:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getLabel(this.payload.getType().toString().replace("_"," "),14f,AppThemeColor.HEADER_BUTTONS_COLOR));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Priority:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getPriorityLabel(this.payload.getPriority(),22));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Reporter:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getIconLabel("app/account_icon2.png",22,this.payload.getReporter().getUserName(),14f,AppThemeColor.HEADER_BUTTONS_COLOR));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Assigner:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getIconLabel("app/account_icon2.png",22,this.payload.getAssignee().getUserName(),14f,AppThemeColor.HEADER_BUTTONS_COLOR));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Created:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getLabel(this.payload.getCreated().toString(),14f,AppThemeColor.HEADER_BUTTONS_COLOR));

        taskConstraintsPanel.add(this.componentsFactory.getLabel("Due:",14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.add(this.componentsFactory.getLabel(this.payload.getDue().toString(),14f,AppThemeColor.HEADER_BUTTONS_COLOR));
        taskConstraintsPanel.setPreferredSize(new Dimension(220,170));
        container.add(taskConstraintsPanel);

        JPanel workPanel = this.componentsFactory.getSlideJPanel(new GridLayout(2,1,5,5));
        JPanel estimatedPanel = this.componentsFactory.getJPanel(new BorderLayout());
        estimatedPanel.add(this.componentsFactory.getProgressBar(this.payload.getEstimatedTime(),this.payload.getEstimatedTime()),BorderLayout.CENTER);
        estimatedPanel.add(this.componentsFactory.getLabel(this.payload.getEstimatedTime() + "h",13f,AppThemeColor.HEADER_BUTTONS_COLOR),BorderLayout.LINE_END);
        JPanel duePanel = this.componentsFactory.getJPanel(new BorderLayout());
        duePanel.add(this.componentsFactory.getProgressBar(this.payload.getEstimatedTime(),this.payload.getDueTime()),BorderLayout.CENTER);
        duePanel.add(this.componentsFactory.getLabel(this.payload.getDueTime() + "h",13f,AppThemeColor.HEADER_BUTTONS_COLOR),BorderLayout.LINE_END);
        workPanel.add(estimatedPanel);
        workPanel.add(duePanel);
        container.add(UIUtils.wrapToVerticalSlide(workPanel));
        return scrollPane;
    }
    private void initHeaderPanel(){
        JPanel root = this.componentsFactory.getJPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(2,5,0,5));
        root.setBackground(AppThemeColor.BACKGROUND_DARK);
        JButton backButton = this.componentsFactory.getIconButton("app/forse_back.png", 30, AppThemeColor.BACKGROUND_DARK);
        backButton.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.TASKS_LIST,null));
        });
        JButton editButton = this.componentsFactory.getIconButton("app/task_edit.png", 24, AppThemeColor.BACKGROUND_DARK);
        editButton.addActionListener(action -> {
            SubjectsStore.stateSubject.onNext(new ApplicationReducer<>(ApplicationState.TASK_OPERATIONS,this.payload));
        });
        root.add(backButton,BorderLayout.LINE_START);
        root.add(editButton,BorderLayout.LINE_END);
        this.add(root,BorderLayout.PAGE_START);
    }
}
