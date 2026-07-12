package org.knowm.xchart;

import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/DialSeries.class */
public class DialSeries extends Series {
    private double value;
    private final String label;

    public DialSeries(String name, double value, String label) {
        super(name);
        this.value = value;
        this.label = label;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public String getLabel() {
        return this.label;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return null;
    }
}
