package com.home.timetracker.core.service;


import com.home.timetracker.core.entity.User;
import lombok.Getter;
import lombok.Setter;

public class TrackerStorage {
    private UserService userService = new UserServiceStub();
    @Getter
    private User currentUser;

    public boolean tryAuth(String userName){
        User userByName = this.userService.getUserByName(userName);
        if(userByName != null){
            this.currentUser = userByName;
            return true;
        }
        return false;
    }
}
