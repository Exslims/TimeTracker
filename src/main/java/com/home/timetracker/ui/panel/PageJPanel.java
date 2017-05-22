package com.home.timetracker.ui.panel;

import com.home.timetracker.core.SubjectsStore;
import com.home.timetracker.ui.AppThemeColor;
import com.home.timetracker.ui.ComponentsFactory;

import javax.swing.*;
import java.awt.*;

public abstract class PageJPanel<T> extends JPanel {
    protected T payload;
    protected ComponentsFactory componentsFactory = new ComponentsFactory();
    protected PageJPanel(){
        super(new BorderLayout());
        this.setBackground(AppThemeColor.BACKGROUND_DARK);
    }
    public abstract void init();
    public void setPayload(T payload) {
        this.payload = payload;
        if(this.payload != null) {
            this.removeAll();
            this.init();
        }
        SubjectsStore.packSubject.onNext(true);
    }
}
