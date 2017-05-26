package com.home.timetracker.ui.plot;


import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.core.entity.PlotData;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.panel.additional.BaseJPanel;
import de.erichseifert.gral.plots.colors.LinearGradient;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ForegroundDataPlot extends BaseJPanel{
    private LinearGradient gradient = new LinearGradient(AppThemeColor.HEADER_BUTTONS_COLOR, AppThemeColor.BACKGROUND);
    private List<PlotData> entries = new ArrayList<>();
    public ForegroundDataPlot(List<PlotData> plotData) {
        this.entries = plotData;
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
            final List<PlotData> limited = sorted.stream().limit(8).collect(Collectors.toList());
            JPanel progressPanel = this.componentsFactory.getGridJPanel(1, 8, 0, 5);
            int maxValue = limited.get(0).getValue();
            limited.forEach(entry ->
                    progressPanel.add(this.componentsFactory.getProgressBar(maxValue, entry.getValue(),
                    (Color) gradient.get(limited.indexOf(entry)/10f),entry.getTitle())));

            this.add(progressPanel,BorderLayout.CENTER);
            SubjectsStore.packSubject.onNext(true);
        }
    }
}
