package com.home.timetracker.ui;


import com.home.timetracker.ui.util.CircleProgressBarUI;
import com.home.timetracker.ui.util.ScrollUI;
import com.home.timetracker.ui.util.VerticalScrollContainer;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ComponentsFactory {
    private Font REGULAR_FONT;
    private Font BOLD_FONT;

    public ComponentsFactory() {
        try {
            this.BOLD_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/Roboto-Bold.ttf"));
            this.REGULAR_FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("font/Roboto-Light.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(AppThemeColor.PRIMARY_TEXT);
        label.setFont(REGULAR_FONT.deriveFont(16f));
        return label;
    }
    public JLabel getLabel(String text, int align) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(align);
        label.setForeground(AppThemeColor.PRIMARY_TEXT);
        label.setFont(REGULAR_FONT.deriveFont(16f));
        return label;
    }
    public JLabel getLabel(String text, float size, Color foreground) {
        JLabel label = new JLabel(text);
        label.setForeground(foreground);
        label.setFont(REGULAR_FONT.deriveFont(size));
        return label;
    }
    public JTextField getTextField(String text){
        JTextField textField = new JTextField(text);
        textField.setForeground(AppThemeColor.PRIMARY_TEXT);
        textField.setFont(REGULAR_FONT.deriveFont(16f));
        return textField;
    }
    public JLabel getIconLabel(String iconPath, int iconSize) {
        JLabel label = new JLabel("");
        label.setBorder(null);
        label.setForeground(AppThemeColor.PRIMARY_COLOR);
        label.setFont(BOLD_FONT.deriveFont(24f));
        label.setBackground(AppThemeColor.BACKGROUND);
        label.setVerticalAlignment(SwingConstants.CENTER);
        BufferedImage icon = null;
        try {
            BufferedImage buttonIcon = ImageIO.read(getClass().getClassLoader().getResource(iconPath));
            icon = Scalr.resize(buttonIcon, iconSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(icon != null){
            label.setIcon(new ImageIcon(icon));
        }
        return label;
    }
    public JButton getIconButton(String iconPath, int iconSize, String text) {
        JButton button = new JButton(""){
            @Override
            protected void paintBorder(Graphics g) {
                if(!this.getModel().isPressed()) {
                    super.paintBorder(g);
                }
            }
        };
        button.setBorder(null);
        button.setForeground(AppThemeColor.PRIMARY_COLOR);
        button.setFont(BOLD_FONT.deriveFont(24f));
        button.setText(text);
        button.setBackground(AppThemeColor.BACKGROUND);
        button.setFocusPainted(false);
        button.addChangeListener(e->{
            if(!button.getModel().isPressed()){
                button.setBackground(button.getBackground());
            }
        });
        button.setVerticalAlignment(SwingConstants.CENTER);
        BufferedImage icon = getIcon(iconPath,iconSize);
        if(icon != null){
            button.setIcon(new ImageIcon(icon));
        }
        return button;
    }
    public JButton getButton(String text) {
        JButton button = new JButton(text){
            @Override
            protected void paintBorder(Graphics g) {
                if(!this.getModel().isPressed()) {
                    super.paintBorder(g);
                }
            }
        };
        button.setBorder(BorderFactory.createLineBorder(AppThemeColor.DIVIDER_COLOR));
        button.setForeground(AppThemeColor.PRIMARY_COLOR);
        button.setFont(BOLD_FONT.deriveFont(20f));
        button.setBackground(AppThemeColor.BACKGROUND);
        button.setPreferredSize(new Dimension(100,30));
        button.setFocusPainted(false);
        button.addChangeListener(e->{
            if(!button.getModel().isPressed()){
                button.setBackground(button.getBackground());
            }
        });
        button.setVerticalAlignment(SwingConstants.CENTER);
        return button;
    }
    public JPanel getJPanel(LayoutManager layoutManager){
        JPanel panel = new JPanel(layoutManager);
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JPanel getJPanel(LayoutManager layoutManager,Color background){
        JPanel panel = new JPanel(layoutManager);
        panel.setBackground(background);
        return panel;
    }
    public JPanel getVerticalJPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JPanel getGridJPanel(int col,int row){
        JPanel panel = new JPanel(new GridLayout(row,col,0,5));
        panel.setBackground(AppThemeColor.BACKGROUND);
        return panel;
    }
    public JScrollPane getScrollPane(VerticalScrollContainer container){
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        scrollPane.setBackground(AppThemeColor.BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        container.getParent().setBackground(AppThemeColor.BACKGROUND);
        JScrollBar vBar = scrollPane.getVerticalScrollBar();
        vBar.setBackground(AppThemeColor.BACKGROUND);
        vBar.setUI(new ScrollUI());
        vBar.setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        vBar.setUnitIncrement(3);
        vBar.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        return scrollPane;
    }
    public JProgressBar getCircleProgressBar(int value, float fontSize){
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximum(100);
        progressBar.setValue(value);
        progressBar.setBorder(null);
        progressBar.setUI(new CircleProgressBarUI());
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(false);
        progressBar.setFont(REGULAR_FONT.deriveFont(fontSize));
        progressBar.setBackground(AppThemeColor.BACKGROUND);
        return progressBar;
    }
    @SuppressWarnings("all")
    public BufferedImage getIcon(String path, int size){
        BufferedImage icon = null;
        try {
            BufferedImage buttonIcon = ImageIO.read(getClass().getClassLoader().getResource(path));
            icon = Scalr.resize(buttonIcon, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return icon;
    }
}