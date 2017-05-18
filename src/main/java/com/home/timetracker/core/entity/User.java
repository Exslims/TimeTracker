package com.home.timetracker.core.entity;

import com.home.timetracker.core.entity.task.TrackerTask;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String userName;
    private List<TrackerTask> tasks = new ArrayList<>();
}
