package com.home.timetracker.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class PlotData implements Comparable<PlotData>{
    private String title;
    private int value;

    public void increment(){
        this.value++;
    }

    @Override
    public int compareTo(PlotData o) {
        if(value > o.value){
            return -1;
        }else if(value < o.value){
            return 1;
        }
        return 0;
    }
}
