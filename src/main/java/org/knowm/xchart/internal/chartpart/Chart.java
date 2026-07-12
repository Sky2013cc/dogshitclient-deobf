package org.knowm.xchart.internal.chartpart;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import org.knowm.xchart.internal.series.Series;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.Styler;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/Chart.class */
public abstract class Chart<ST extends Styler, S extends Series> {
    protected final ST styler;
    protected AxisPair axisPair;
    protected Plot_<ST, S> plot;
    protected Legend_<ST, S> legend;
    private int width;
    private int height;
    protected final Map<String, S> seriesMap = new LinkedHashMap();
    protected final ArrayList<ChartPart> annotations = new ArrayList<>();
    private String title = "";
    private String xAxisTitle = "";
    private String yAxisTitle = "";
    private final Map<Integer, String> yAxisGroupTitleMap = new HashMap();
    protected final ChartTitle<ST, S> chartTitle = new ChartTitle<>(this);

    public abstract void paint(Graphics2D graphics2D, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public Chart(int width, int height, ST styler) {
        this.width = width;
        this.height = height;
        this.styler = styler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paintBackground(Graphics2D g) {
        Object obj;
        RenderingHints.Key key = RenderingHints.KEY_ANTIALIASING;
        if (this.styler.getAntiAlias()) {
            obj = RenderingHints.VALUE_ANTIALIAS_ON;
        } else {
            obj = RenderingHints.VALUE_ANTIALIAS_OFF;
        }
        g.setRenderingHint(key, obj);
        g.setColor(this.styler.getChartBackgroundColor());
        g.fill(new Rectangle2D.Double(0.0d, 0.0d, getWidth(), getHeight()));
    }

    public ST getStyler() {
        return this.styler;
    }

    public S removeSeries(String seriesName) {
        return this.seriesMap.remove(seriesName);
    }

    public int getWidth() {
        return this.width;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXAxisTitle() {
        return this.xAxisTitle;
    }

    public void setXAxisTitle(String xAxisTitle) {
        this.xAxisTitle = xAxisTitle;
    }

    public String getYAxisTitle() {
        return this.yAxisTitle;
    }

    public void setYAxisTitle(String yAxisTitle) {
        this.yAxisTitle = yAxisTitle;
    }

    public String getYAxisGroupTitle(int yAxisGroup) {
        String title = this.yAxisGroupTitleMap.get(Integer.valueOf(yAxisGroup));
        if (title == null) {
            return this.yAxisTitle;
        }
        return title;
    }

    public void setYAxisGroupTitle(int yAxisGroup, String yAxisTitle) {
        this.yAxisGroupTitleMap.put(Integer.valueOf(yAxisGroup), yAxisTitle);
    }

    public void addAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
        annotation.init(this);
    }

    public void setCustomXAxisTickLabelsFormatter(Function<Double, String> customFormattingFunction) {
        AxesChartStyler axesChartStyler = (AxesChartStyler) this.styler;
        axesChartStyler.setxAxisTickLabelsFormattingFunction(customFormattingFunction);
    }

    public void setCustomYAxisTickLabelsFormatter(Function<Double, String> customFormattingFunction) {
        AxesChartStyler axesChartStyler = (AxesChartStyler) this.styler;
        axesChartStyler.setyAxisTickLabelsFormattingFunction(customFormattingFunction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChartTitle<ST, S> getChartTitle() {
        return this.chartTitle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Legend_<ST, S> getLegend() {
        return this.legend;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Plot_<ST, S> getPlot() {
        return this.plot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Axis getXAxis() {
        return this.axisPair.getXAxis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Axis getYAxis() {
        return this.axisPair.getYAxis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Axis getYAxis(int yIndex) {
        return this.axisPair.getYAxis(yIndex);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AxisPair getAxisPair() {
        return this.axisPair;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Format getXAxisFormat() {
        return this.axisPair.getXAxis().getAxisTickCalculator().getAxisFormat();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Format getYAxisFormat() {
        return this.axisPair.getYAxis().getAxisTickCalculator().getAxisFormat();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Format getYAxisFormat(String yAxisDecimalPattern) {
        Format format;
        if (yAxisDecimalPattern != null) {
            format = new DecimalFormat(yAxisDecimalPattern);
        } else {
            format = this.axisPair.getYAxis().getAxisTickCalculator().getAxisFormat();
        }
        return format;
    }

    public double getChartXFromCoordinate(int screenX) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getXAxis().getChartValue(screenX);
    }

    public double getChartYFromCoordinate(int screenY) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getYAxis().getChartValue(screenY);
    }

    public double getChartYFromCoordinate(int screenY, int yIndex) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getYAxis(yIndex).getChartValue(screenY);
    }

    public double getScreenXFromChart(double xValue) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getXAxis().getScreenValue(xValue);
    }

    public double getScreenYFromChart(double yValue) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getYAxis().getScreenValue(yValue);
    }

    public double getScreenYFromChart(double yValue, int yIndex) {
        if (this.axisPair == null) {
            return Double.NaN;
        }
        return this.axisPair.getYAxis(yIndex).getScreenValue(yValue);
    }

    public double getYAxisLeftWidth() {
        Rectangle2D.Double bounds = getAxisPair().getLeftYAxisBounds();
        return bounds.width + bounds.x;
    }

    public Map<String, S> getSeriesMap() {
        return this.seriesMap;
    }
}
