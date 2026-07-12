package org.knowm.xchart;

import java.awt.Graphics2D;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.chartpart.Legend_Pie;
import org.knowm.xchart.internal.chartpart.Plot_Dial;
import org.knowm.xchart.style.DialStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/DialChart.class */
public class DialChart extends Chart<DialStyler, DialSeries> {
    public DialChart(int width, int height) {
        super(width, height, new DialStyler());
        this.plot = new Plot_Dial(this);
        this.legend = new Legend_Pie(this);
    }

    public DialChart(int width, int height, Theme theme) {
        this(width, height);
        ((DialStyler) this.styler).setTheme(theme);
    }

    public DialChart(int width, int height, Styler.ChartTheme chartTheme) {
        this(width, height, chartTheme.newInstance(chartTheme));
    }

    public DialChart(DialChartBuilder chartBuilder) {
        this(chartBuilder.width, chartBuilder.height, chartBuilder.chartTheme);
        setTitle(chartBuilder.title);
    }

    public DialSeries addSeries(String seriesName, double value) {
        return addSeries(seriesName, value, null);
    }

    public DialSeries addSeries(String seriesName, double value, String label) {
        sanityCheck(seriesName, value);
        DialSeries series = new DialSeries(seriesName, value, label);
        this.seriesMap.clear();
        this.seriesMap.put(seriesName, series);
        return series;
    }

    private void sanityCheck(String seriesName, double value) {
        if (this.seriesMap.containsKey(seriesName)) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< has already been used. Use unique names for each series!!!");
        }
        if (value < 0.0d || value > 1.0d) {
            throw new IllegalArgumentException("Value must be in [0, 1] range!!!");
        }
    }

    @Override // org.knowm.xchart.internal.chartpart.Chart
    public void paint(Graphics2D g, int width, int height) {
        setWidth(width);
        setHeight(height);
        paintBackground(g);
        this.plot.paint(g);
        this.chartTitle.paint(g);
        this.annotations.forEach(x -> {
            x.paint(g);
        });
    }
}
