package com.home.timetracker.ui.plot;


import com.home.timetracker.ui.AppThemeColor;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.EnumeratedData;
import de.erichseifert.gral.data.statistics.Histogram;
import de.erichseifert.gral.data.statistics.Histogram1D;
import de.erichseifert.gral.data.statistics.Statistics;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Orientation;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HistogramPlot extends JPanel{
    private static final int SAMPLE_COUNT = 1000;
    public HistogramPlot() {
        this.setLayout(new BorderLayout());
        Random random = new Random();
        DataTable data = new DataTable(Double.class);
        for (int i = 0; i < SAMPLE_COUNT; i++) {
            data.add(random.nextGaussian());
        }

        // Create histogram from data
        Histogram histogram = new Histogram1D(data, Orientation.VERTICAL,
                new Number[] {-4.0, -3.2, -2.4, -1.6, -0.8, 0.0, 0.8, 1.6, 2.4, 3.6, 4.0});
        // Create a second dimension (x axis) for plotting
        DataSource histogram2d = new EnumeratedData(histogram, (-4.0 + -3.2)/2.0, 0.8);

        // Create new bar plot
        BarPlot plot = new BarPlot(histogram2d);

        // Format plot
        plot.setInsets(new Insets2D.Double(20.0, 65.0, 50.0, 40.0));
        plot.getTitle().setText(
                String.format("Distribution of %d random samples", data.getRowCount()));
        plot.setBarWidth(0.78);

        // Format x axis
        plot.getAxisRenderer(BarPlot.AXIS_X).setTickAlignment(0.0);
        plot.getAxisRenderer(BarPlot.AXIS_X).setTickSpacing(0.8);
        plot.getAxisRenderer(BarPlot.AXIS_X).setMinorTicksVisible(false);
        // Format y axis
        plot.getAxis(BarPlot.AXIS_Y).setRange(0.0,
                MathUtils.ceil(histogram.getStatistics().get(Statistics.MAX)*1.1, 25.0));
        plot.getAxisRenderer(BarPlot.AXIS_Y).setTickAlignment(0.0);
        plot.getAxisRenderer(BarPlot.AXIS_Y).setMinorTicksVisible(false);
        plot.getAxisRenderer(BarPlot.AXIS_Y).setIntersection(-4.4);

        // Format bars
        PointRenderer barRenderer = plot.getPointRenderers(histogram2d).get(0);
        barRenderer.setColor(GraphicsUtils.deriveWithAlpha(AppThemeColor.DARK_PRIMARY_COLOR, 128));
        barRenderer.setValueVisible(true);

        // Add plot to Swing component
        InteractivePanel panel = new InteractivePanel(plot);
        panel.setPannable(false);
        panel.setZoomable(false);
        add(panel,BorderLayout.CENTER);
    }
}
