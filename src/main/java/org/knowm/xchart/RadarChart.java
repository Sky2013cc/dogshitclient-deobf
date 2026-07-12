package org.knowm.xchart;

import java.awt.Graphics2D;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.chartpart.Legend_Marker;
import org.knowm.xchart.internal.chartpart.Plot_Radar;
import org.knowm.xchart.internal.style.SeriesColorMarkerLineStyle;
import org.knowm.xchart.internal.style.SeriesColorMarkerLineStyleCycler;
import org.knowm.xchart.style.RadarStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/RadarChart.class */
public class RadarChart extends Chart<RadarStyler, RadarSeries> {
    private String[] radiiLabels;

    public RadarChart(int width, int height) {
        super(width, height, new RadarStyler());
        this.plot = new Plot_Radar(this);
        this.legend = new Legend_Marker(this);
    }

    public RadarChart(int width, int height, Theme theme) {
        this(width, height);
        ((RadarStyler) this.styler).setTheme(theme);
    }

    public RadarChart(int width, int height, Styler.ChartTheme chartTheme) {
        this(width, height, chartTheme.newInstance(chartTheme));
    }

    public RadarChart(RadarChartBuilder radarChartBuilder) {
        this(radarChartBuilder.width, radarChartBuilder.height, radarChartBuilder.chartTheme);
        setTitle(radarChartBuilder.title);
    }

    public String[] getRadiiLabels() {
        return this.radiiLabels;
    }

    public void setRadiiLabels(String[] radiiLabels) {
        this.radiiLabels = radiiLabels;
    }

    public RadarSeries addSeries(String seriesName, double[] values) {
        return addSeries(seriesName, values, null);
    }

    public RadarSeries addSeries(String seriesName, double[] values, String[] tooltipOverrides) {
        sanityCheck(seriesName, values, tooltipOverrides);
        RadarSeries series = new RadarSeries(seriesName, values, tooltipOverrides);
        this.seriesMap.put(seriesName, series);
        return series;
    }

    private void sanityCheck(String seriesName, double[] values, String[] annotations) {
        if (this.radiiLabels == null) {
            throw new IllegalArgumentException("Variable labels cannot be null!!!");
        }
        if (this.seriesMap.containsKey(seriesName)) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< has already been used. Use unique names for each series!!!");
        }
        if (values == null) {
            throw new IllegalArgumentException("Values data cannot be null!!!");
        }
        if (values.length < this.radiiLabels.length) {
            throw new IllegalArgumentException("Too few values!!!");
        }
        for (double d : values) {
            if (d < 0.0d || d > 1.0d) {
                throw new IllegalArgumentException("Values must be in [0, 1] range!!!");
            }
        }
        if (annotations != null && annotations.length < this.radiiLabels.length) {
            throw new IllegalArgumentException("Too few tool tips!!!");
        }
    }

    @Override // org.knowm.xchart.internal.chartpart.Chart
    public void paint(Graphics2D g, int width, int height) {
        setWidth(width);
        setHeight(height);
        setSeriesStyles();
        paintBackground(g);
        this.plot.paint(g);
        this.chartTitle.paint(g);
        this.legend.paint(g);
        this.annotations.forEach(x -> {
            x.paint(g);
        });
    }

    private void setSeriesStyles() {
        SeriesColorMarkerLineStyleCycler seriesColorMarkerLineStyleCycler = new SeriesColorMarkerLineStyleCycler(getStyler().getSeriesColors(), getStyler().getSeriesMarkers(), getStyler().getSeriesLines());
        for (RadarSeries series : getSeriesMap().values()) {
            SeriesColorMarkerLineStyle seriesColorMarkerLineStyle = seriesColorMarkerLineStyleCycler.getNextSeriesColorMarkerLineStyle();
            if (series.getLineStyle() == null) {
                series.setLineStyle(seriesColorMarkerLineStyle.getStroke());
            }
            if (series.getLineColor() == null) {
                series.setLineColor(seriesColorMarkerLineStyle.getColor());
            }
            if (series.getFillColor() == null) {
                series.setFillColor(seriesColorMarkerLineStyle.getColor());
            }
            if (series.getMarker() == null) {
                series.setMarker(seriesColorMarkerLineStyle.getMarker());
            }
            if (series.getMarkerColor() == null) {
                series.setMarkerColor(seriesColorMarkerLineStyle.getColor());
            }
        }
    }
}
