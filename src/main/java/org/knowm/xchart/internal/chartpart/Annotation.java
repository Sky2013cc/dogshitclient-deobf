package org.knowm.xchart.internal.chartpart;

import java.awt.geom.Rectangle2D;
import org.knowm.xchart.style.Styler;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/Annotation.class */
public abstract class Annotation implements ChartPart {
    protected boolean isVisible = true;
    protected boolean isValueInScreenSpace;
    protected Chart chart;
    protected Styler styler;
    protected Rectangle2D bounds;

    public Annotation(boolean isValueInScreenSpace) {
        this.isValueInScreenSpace = isValueInScreenSpace;
    }

    public void init(Chart chart) {
        this.chart = chart;
        this.styler = chart.getStyler();
    }

    @Override // org.knowm.xchart.internal.chartpart.ChartPart
    public Rectangle2D getBounds() {
        return this.bounds;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getXAxisScreenValue(double chartSpaceValue) {
        return (int) this.chart.getXAxis().getScreenValue(chartSpaceValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getYAxisScreenValue(double chartSpaceValue) {
        return (int) this.chart.getYAxis().getScreenValue(chartSpaceValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getXAxisScreenValueForMax() {
        return (int) this.chart.getPlot().plotSurface.getBounds().getMaxX();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getXAxisScreenValueForMin() {
        return (int) this.chart.getPlot().plotSurface.getBounds().getMinX();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getYAxisScreenValueForMax() {
        return (int) this.chart.getPlot().plotSurface.getBounds().getMaxY();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getYAxisScreenValueForMin() {
        return (int) this.chart.getPlot().plotSurface.getBounds().getMinY();
    }
}
