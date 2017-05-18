package com.home.timetracker.core;

import com.home.timetracker.core.routing.ApplicationReducer;
import rx.subjects.PublishSubject;


public class SubjectsStore {
    public static final PublishSubject<ApplicationReducer> stateSubject = PublishSubject.create();
    public static final PublishSubject<Boolean> packSubject = PublishSubject.create();
}
