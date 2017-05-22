package com.home.timetracker.ui.plot;


import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.panel.additional.BaseJPanel;
import com.sun.deploy.util.StringUtils;
import de.erichseifert.gral.plots.colors.LinearGradient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ForegroundDataPlot extends BaseJPanel{
    private LinearGradient gradient = new LinearGradient(AppThemeColor.DARK_PRIMARY_COLOR, AppThemeColor.BACKGROUND);
    private List<PlotData> entries = new ArrayList<>();
    public ForegroundDataPlot() {
        this.createView();
    }

    public void add(String windowName){
        List<PlotData> collect = this.entries.stream()
                .filter(entry -> entry.getTitle().equals(windowName))
                .collect(Collectors.toList());
        if(collect.isEmpty()){
            this.entries.add(new PlotData(windowName,1));
        }else {
            collect.get(0).increment();
        }
        this.removeAll();
        this.createView();
    }

    @Override
    public void createView() {
        this.add(this.componentsFactory.getLabel("Time tracking",14f,AppThemeColor.HEADER_BUTTONS_COLOR),BorderLayout.PAGE_START);
        if(this.entries.size() > 0) {
            List<PlotData> sorted = new ArrayList<>();
            this.entries.stream().sorted(PlotData::compareTo)
                    .forEachOrdered(sorted::add);

            JPanel progressPanel = this.componentsFactory.getGridJPanel(1, 0, 0, 5);
            int maxValue = sorted.get(0).value;
            sorted.forEach(entry -> {
                progressPanel.add(this.componentsFactory.getProgressBar(maxValue, entry.getValue(),
                        AppThemeColor.HEADER_BUTTONS_COLOR,entry.getTitle()));
            });

            this.add(progressPanel,BorderLayout.CENTER);
            SubjectsStore.packSubject.onNext(true);
        }
    }

    @Data
    @EqualsAndHashCode
    @ToString
    @AllArgsConstructor
    private class PlotData implements Comparable<PlotData>{
        private String title;
        private int value;

        void increment(){
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
}
