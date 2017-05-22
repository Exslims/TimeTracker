package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TaskPriority;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TaskType;
import com.home.timetracker.core.entity.task.TrackerTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;


public class UserServiceStub implements UserService{
    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setUserName(name);
        user.setTasks(Arrays.stream(new TrackerTask[] {
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.LOW,
                        TaskType.BUG,
                        TaskStatus.OPEN,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,0
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.CRITICAL,
                        TaskType.FEATURE,
                        TaskStatus.OPEN,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,3
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.HIGH,
                        TaskType.BUG,
                        TaskStatus.CLOSED,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,6
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.LOW,
                        TaskType.BUG,
                        TaskStatus.OPEN,
                        user,
                        user,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5
                )
        }).collect(Collectors.toList()));
        return user;
    }
}
