package org.knowm.xchart;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.knowm.xchart.internal.chartpart.AxisPair;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.internal.chartpart.Legend_HeatMap;
import org.knowm.xchart.internal.chartpart.Plot_HeatMap;
import org.knowm.xchart.style.HeatMapStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/HeatMapChart.class */
public class HeatMapChart extends Chart<HeatMapStyler, HeatMapSeries> {
    private HeatMapSeries heatMapSeries;

    public HeatMapChart(int width, int height) {
        super(width, height, new HeatMapStyler());
        this.axisPair = new AxisPair(this);
        this.plot = new Plot_HeatMap(this);
        this.legend = new Legend_HeatMap(this);
    }

    public HeatMapChart(int width, int height, Theme theme) {
        this(width, height);
        ((HeatMapStyler) this.styler).setTheme(theme);
    }

    public HeatMapChart(int width, int height, Styler.ChartTheme chartTheme) {
        this(width, height, chartTheme.newInstance(chartTheme));
    }

    public HeatMapChart(HeatMapChartBuilder heatMapChartBuilder) {
        this(heatMapChartBuilder.width, heatMapChartBuilder.height, heatMapChartBuilder.chartTheme);
        setTitle(heatMapChartBuilder.title);
        setXAxisTitle(heatMapChartBuilder.xAxisTitle);
        setYAxisTitle(heatMapChartBuilder.yAxisTitle);
    }

    public HeatMapSeries addSeries(String seriesName, int[] xData, int[] yData, int[][] heatData) {
        return addSeries(seriesName, arrayToList(xData), arrayToList(yData), arrayToList(heatData));
    }

    public HeatMapSeries addSeries(String seriesName, List<?> xData, List<?> yData, List<Number[]> heatData) {
        if (this.heatMapSeries != null) {
            throw new RuntimeException("HeatMapSeries can only be added once!!!");
        }
        sanityCheck(xData, yData, heatData);
        this.heatMapSeries = new HeatMapSeries(seriesName, xData, yData, heatData);
        this.seriesMap.put(seriesName, this.heatMapSeries);
        return this.heatMapSeries;
    }

    public HeatMapSeries updateSeries(String seriesName, int[] xData, int[] yData, int[][] heatData) {
        return updateSeries(seriesName, arrayToList(xData), arrayToList(yData), arrayToList(heatData));
    }

    public HeatMapSeries updateSeries(String seriesName, List<?> xData, List<?> yData, List<Number[]> heatData) {
        Map<String, HeatMapSeries> seriesMap = getSeriesMap();
        HeatMapSeries series = seriesMap.get(seriesName);
        if (series == null) {
            throw new IllegalArgumentException("Series name >" + seriesName + "< not found!!!");
        }
        series.replaceData(xData, yData, heatData);
        return series;
    }

    public HeatMapSeries getHeatMapSeries() {
        return this.heatMapSeries;
    }

    @Override // org.knowm.xchart.internal.chartpart.Chart
    public void paint(Graphics2D g, int width, int height) {
        if (this.heatMapSeries == null) {
            return;
        }
        setWidth(width);
        setHeight(height);
        prepareForPaint();
        paintBackground(g);
        this.axisPair.paint(g);
        this.plot.paint(g);
        this.chartTitle.paint(g);
        this.legend.paint(g);
        this.annotations.forEach(x -> {
            x.paint(g);
        });
    }

    private List<Integer> arrayToList(int[] data) {
        List<Integer> list = new ArrayList<>();
        for (int datum : data) {
            list.add(Integer.valueOf(datum));
        }
        return list;
    }

    private List<Number[]> arrayToList(int[][] heatData) {
        List<Number[]> list = new ArrayList<>();
        for (int i = 0; i < heatData.length; i++) {
            int[] array = heatData[i];
            for (int j = 0; j < array.length; j++) {
                Number[] numbers = {Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(heatData[i][j])};
                list.add(numbers);
            }
        }
        return list;
    }

    private void sanityCheck(List<?> xData, List<?> yData, List<Number[]> heatData) {
        if (xData == null) {
            throw new IllegalArgumentException("X-Axis data cannot be null!!!");
        }
        if (xData.size() == 0) {
            throw new IllegalArgumentException("X-Axis data cannot be empty!!!");
        }
        if (yData == null) {
            throw new IllegalArgumentException("Y-Axis data cannot be null!!!");
        }
        if (yData.size() == 0) {
            throw new IllegalArgumentException("Y-Axis data cannot be empty!!!");
        }
        if (heatData == null) {
            throw new IllegalArgumentException("Heat data cannot be null!!!");
        }
        if (heatData.size() == 0) {
            throw new IllegalArgumentException("Heat data cannot be empty!!!");
        }
        for (Number[] numbers : heatData) {
            if (numbers != null) {
                if (numbers.length != 3) {
                    throw new IllegalArgumentException("Heat data column length is not equal to 3!!!");
                }
                if (numbers[0] == null || numbers[1] == null || numbers[2] == null) {
                    throw new IllegalArgumentException("All values in the heat data column cannot be null!!!");
                }
                if (numbers[0].intValue() < 0 || numbers[1].intValue() < 0) {
                    throw new IllegalArgumentException("numbers[0] and numbers[1] cannot be less than 0!!!");
                }
            }
        }
    }

    private void prepareForPaint() {
        if (((HeatMapStyler) this.styler).getMin() != Double.MIN_VALUE) {
            this.heatMapSeries.setMin(((HeatMapStyler) this.styler).getMin());
        }
        if (((HeatMapStyler) this.styler).getMax() != Double.MAX_VALUE) {
            this.heatMapSeries.setMax(((HeatMapStyler) this.styler).getMax());
        }
    }
}
