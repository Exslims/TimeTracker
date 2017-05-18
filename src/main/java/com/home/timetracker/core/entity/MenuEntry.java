package com.home.timetracker.core.entity;

import com.home.timetracker.core.MenuAction;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;

@Data
@AllArgsConstructor
public class MenuEntry {
    private String text;
    private MenuAction action;
    private ImageIcon imageIcon;
}
