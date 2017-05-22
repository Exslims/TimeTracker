package com.home.timetracker.core.entity.task;

import com.home.timetracker.core.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackerTask {
    private String taskTitle;

    private TaskPriority priority;
    private TaskType type;
    private TaskStatus status;

    private User reporter;
    private User assignee;

    private LocalDateTime created;
    private LocalDateTime due;

    private int estimatedTime;
    private int dueTime;

}
