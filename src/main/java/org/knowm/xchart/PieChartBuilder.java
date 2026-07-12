package org.knowm.xchart;

import org.knowm.xchart.internal.ChartBuilder;

/* loaded from: target.jar:org/knowm/xchart/PieChartBuilder.class */
public class PieChartBuilder extends ChartBuilder<PieChartBuilder, PieChart> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.knowm.xchart.internal.ChartBuilder
    public PieChart build() {
        return new PieChart(this);
    }
}
