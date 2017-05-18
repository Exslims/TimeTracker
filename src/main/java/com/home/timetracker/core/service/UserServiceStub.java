package com.home.timetracker.core.service;

import com.home.timetracker.core.entity.User;


public class UserServiceStub implements UserService{
    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }
}
