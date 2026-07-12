package org.knowm.xchart;

import java.awt.Graphics2D;
import java.util.Map;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.chartpart.Legend_Pie;
import org.knowm.xchart.internal.chartpart.Plot_Pie;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.internal.style.SeriesColorMarkerLineStyle;
import org.knowm.xchart.internal.style.SeriesColorMarkerLineStyleCycler;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/PieChart.class */
public class PieChart extends Chart<PieStyler, PieSeries> {
    public PieChart(int width, int height) {
        super(width, height, new PieStyler());
        this.plot = new Plot_Pie(this);
        this.legend = new Legend_Pie(this);
    }

    public PieChart(int width, int height, Theme theme) {
        this(width, height);
        ((PieStyler) this.styler).setTheme(theme);
    }

    public PieChart(int width, int height, Styler.ChartTheme chartTheme) {
        this(width, height, chartTheme.newInstance(chartTheme));
    }

    public PieChart(PieChartBuilder chartBuilder) {
        this(chartBuilder.width, chartBuilder.height, chartBuilder.chartTheme);
        setTitle(chartBuilder.title);
    }

    public PieSeries addSeries(String seriesName, Number value) {
        PieSeries series = new PieSeries(seriesName, value);
        if (this.seriesMap.containsKey(seriesName)) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< has already been used. Use unique names for each series!!!");
        }
        this.seriesMap.put(seriesName, series);
        return series;
    }

    public PieSeries updatePieSeries(String seriesName, Number value) {
        Map<String, PieSeries> seriesMap = getSeriesMap();
        PieSeries series = seriesMap.get(seriesName);
        if (series == null) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< not found!!!");
        }
        series.replaceData(value);
        return series;
    }

    @Override // org.knowm.xchart.internal.chartpart.Chart
    public void paint(Graphics2D g, int width, int height) {
        setWidth(width);
        setHeight(height);
        for (PieSeries seriesPie : getSeriesMap().values()) {
            PieSeries.PieSeriesRenderStyle seriesType = seriesPie.getChartPieSeriesRenderStyle();
            if (seriesType == null) {
                seriesPie.setChartPieSeriesRenderStyle(getStyler().getDefaultSeriesRenderStyle());
            }
        }
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
        for (Series series : getSeriesMap().values()) {
            SeriesColorMarkerLineStyle seriesColorMarkerLineStyle = seriesColorMarkerLineStyleCycler.getNextSeriesColorMarkerLineStyle();
            if (series.getFillColor() == null) {
                series.setFillColor(seriesColorMarkerLineStyle.getColor());
            }
        }
    }
}
