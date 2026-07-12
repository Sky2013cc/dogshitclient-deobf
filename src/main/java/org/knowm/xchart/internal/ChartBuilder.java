package org.knowm.xchart.internal;

import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.knowm.xchart.internal.ChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;

/* loaded from: target.jar:org/knowm/xchart/internal/ChartBuilder.class */
public abstract class ChartBuilder<T extends ChartBuilder<?, ?>, C extends Chart> {
    public int width = OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD;
    public int height = OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD;
    public String title = "";
    public Styler.ChartTheme chartTheme = Styler.ChartTheme.XChart;

    public abstract C build();

    /* JADX WARN: Multi-variable type inference failed */
    public T width(int width) {
        this.width = width;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T height(int height) {
        this.height = height;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T title(String title) {
        this.title = title;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T theme(Styler.ChartTheme chartTheme) {
        this.chartTheme = chartTheme;
        return this;
    }
}
