package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TaskPriority;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TaskType;
import com.home.timetracker.core.entity.task.TrackerTask;

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
                        null,
                        null,
                        null,
                        null,
                        8,0
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.CRITICAL,
                        TaskType.FEATURE,
                        TaskStatus.OPEN,
                        null,
                        null,
                        null,
                        null,
                        8,3
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.HIGH,
                        TaskType.BUG,
                        TaskStatus.OPEN,
                        null,
                        null,
                        null,
                        null,
                        8,6
                ),
                new TrackerTask(
                        "TaskTitle",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.OPEN,
                        null,
                        null,
                        null,
                        null,
                        8,5
                )
        }).collect(Collectors.toList()));
        return user;
    }
}
