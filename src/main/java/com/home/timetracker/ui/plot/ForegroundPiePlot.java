package com.home.timetracker.ui.plot;


import com.home.timetracker.ui.AppThemeColor;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ForegroundPiePlot extends JPanel{
    private final DataTable dataTable;
    private final PiePlot piePlot;

    public ForegroundPiePlot() {
        this.dataTable = new DataTable(Integer.class);
        this.piePlot = new PiePlot(dataTable);
        this.piePlot.setRadius(0.9);
        this.piePlot.setStart(90.0);
        this.piePlot.setInsets(new Insets2D.Double(20.0));
        PiePlot.PieSliceRenderer pieSliceRenderer = (PiePlot.PieSliceRenderer) piePlot.getPointRenderer(dataTable);
        pieSliceRenderer.setGap(0.2);
        pieSliceRenderer.setValueVisible(true);
        LinearGradient color = new LinearGradient(AppThemeColor.DARK_PRIMARY_COLOR,AppThemeColor.BACKGROUND);
        pieSliceRenderer.setColor(color);
        InteractivePanel panel = new InteractivePanel(piePlot);
        panel.setPannable(false);
        panel.setZoomable(false);
        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);

        for (int i = 0; i < 15; i++) {
            this.dataTable.add(new Random().nextInt(3));
        }
    }
}
