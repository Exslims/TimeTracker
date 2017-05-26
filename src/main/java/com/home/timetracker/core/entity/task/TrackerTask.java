package com.home.timetracker.core.entity.task;

import com.home.timetracker.core.entity.PlotData;
import com.home.timetracker.core.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackerTask {
    private String taskTitle;

    private TaskPriority priority = TaskPriority.NORMAL;
    private TaskType type = TaskType.BUG;
    private TaskStatus status = TaskStatus.OPEN;

    private User reporter;
    private User assignee;

    private LocalDate created = LocalDate.now();
    private LocalDate due = LocalDate.now();

    private int estimatedTime;
    private int dueTime;

    private List<PlotData> plotData = new ArrayList<>();
}
