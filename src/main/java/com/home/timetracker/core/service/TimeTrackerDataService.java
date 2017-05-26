package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TaskPriority;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TaskType;
import com.home.timetracker.core.entity.task.TrackerTask;

import java.util.List;

public interface TimeTrackerDataService {
    User getUserByName(String name);
    List<TrackerTask> getTasks();
    void addTask(TrackerTask task);
    List<TrackerTask> getUserTasks(User user);
    List<TrackerTask> getUnassignedTasks();
    List<TrackerTask> getTasksByPriority(TaskPriority priority);
    List<TrackerTask> getTasksByType(TaskType type);
    List<TrackerTask> getTasksByStatus(TaskStatus status);
}
