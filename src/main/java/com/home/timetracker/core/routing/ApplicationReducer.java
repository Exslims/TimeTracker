package com.home.timetracker.core.routing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationReducer<T> {
    private ApplicationState state;


    private T payload;
}
