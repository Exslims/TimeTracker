package com.home.timetracker.core;

import com.home.timetracker.core.entity.User;
import com.home.timetracker.core.routing.ApplicationReducer;
import rx.subjects.PublishSubject;


public class SubjectsStore {
    public static final PublishSubject<ApplicationReducer> stateSubject = PublishSubject.create();
    public static final PublishSubject<Boolean> packSubject = PublishSubject.create();
    public static final PublishSubject<Boolean> openMenuSubject = PublishSubject.create();
    public static final PublishSubject<User> loginSubject = PublishSubject.create();
    public static final PublishSubject<Boolean> logoutSubject = PublishSubject.create();
}
