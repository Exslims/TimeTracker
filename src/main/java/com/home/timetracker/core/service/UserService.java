package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.User;

public interface UserService {
    User getUserByName(String name);
    void addUser(User user);

}
