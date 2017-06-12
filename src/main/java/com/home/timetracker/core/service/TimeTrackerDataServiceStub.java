package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.PlotData;
import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.entity.task.TaskPriority;
import com.home.timetracker.core.entity.task.TaskStatus;
import com.home.timetracker.core.entity.task.TaskType;
import com.home.timetracker.core.entity.task.TrackerTask;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TimeTrackerDataServiceStub implements TimeTrackerDataService {
    private static class TimeTrackerDataServiceStubHolder {
        static final TimeTrackerDataServiceStub HOLDER_INSTANCE = new TimeTrackerDataServiceStub();
    }
    public static final TimeTrackerDataServiceStub INSTANCE = TimeTrackerDataServiceStubHolder.HOLDER_INSTANCE;
    private User currentUser; //stub data
    private List<TrackerTask> tasks;
    @Override
    public User getUserByName(String name) {
        User user = new User();
        this.currentUser = user;
        user.setUserName(name);
        this.tasks = getTasks();
        user.setTasks(this.getUserTasks(user));
        return user;
    }

    @Override
    public List<TrackerTask> getTasks() {
        return Arrays.stream(new TrackerTask[] {
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.LOW,
                        TaskType.BUG,
                        TaskStatus.OPEN,
                        currentUser,
                        currentUser,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,0,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.CRITICAL,
                        TaskType.FEATURE,
                        TaskStatus.OPEN,
                        currentUser,
                        null,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,3,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.HIGH,
                        TaskType.BUG,
                        TaskStatus.CLOSED,
                        currentUser,
                        currentUser,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,6,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        currentUser,
                        null,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        currentUser,
                        currentUser,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        currentUser,
                        currentUser,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.NORMAL,
                        TaskType.FEATURE,
                        TaskStatus.IN_PROGRESS,
                        currentUser,
                        null,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                ),
                new TrackerTask(
                        "TaskTitle",
                        "Task description",
                        TaskPriority.LOW,
                        TaskType.BUG,
                        TaskStatus.OPEN,
                        currentUser,
                        null,
                        LocalDate.now(),
                        LocalDate.now(),
                        8,5,
                        Arrays.stream(new PlotData[]{
                                new PlotData("Test1",5),
                                new PlotData("Test2",2),
                                new PlotData("Test3",6),
                        }).collect(Collectors.toList())
                )
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TrackerTask task) {
        this.tasks.add(task);
    }

    @Override
    public List<TrackerTask> getUserTasks(User user) {
        return this.tasks
                .stream()
                .filter(trackerTask -> (trackerTask.getAssignee() != null && trackerTask.getAssignee().equals(user)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackerTask> getUnassignedTasks() {
        return this.tasks
                .stream()
                .filter(trackerTask -> trackerTask.getAssignee() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackerTask> getTasksByPriority(TaskPriority priority) {
        return this.tasks
                .stream()
                .filter(trackerTask -> trackerTask.getPriority().equals(priority))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackerTask> getTasksByType(TaskType type) {
        return this.tasks
                .stream()
                .filter(trackerTask -> trackerTask.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrackerTask> getTasksByStatus(TaskStatus status) {
        return this.tasks
                .stream()
                .filter(trackerTask -> trackerTask.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
