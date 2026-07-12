package org.knowm.xchart;

import java.util.List;
import org.knowm.xchart.internal.chartpart.RenderableSeries;
import org.knowm.xchart.internal.series.AxesChartSeriesCategory;
import org.knowm.xchart.internal.series.Series;

/* loaded from: target.jar:org/knowm/xchart/CategorySeries.class */
public class CategorySeries extends AxesChartSeriesCategory {
    private boolean isOverlapped;
    private CategorySeriesRenderStyle chartCategorySeriesRenderStyle;

    public CategorySeries(String name, List<?> xData, List<? extends Number> yData, List<? extends Number> errorBars, Series.DataType axisType) {
        super(name, xData, yData, errorBars, axisType);
        this.isOverlapped = false;
        this.chartCategorySeriesRenderStyle = null;
    }

    public CategorySeriesRenderStyle getChartCategorySeriesRenderStyle() {
        return this.chartCategorySeriesRenderStyle;
    }

    public CategorySeries setChartCategorySeriesRenderStyle(CategorySeriesRenderStyle categorySeriesRenderStyle) {
        this.chartCategorySeriesRenderStyle = categorySeriesRenderStyle;
        return this;
    }

    public boolean isOverlapped() {
        return this.isOverlapped;
    }

    public CategorySeries setOverlapped(boolean overlapped) {
        this.isOverlapped = overlapped;
        return this;
    }

    @Override // org.knowm.xchart.internal.series.Series
    public RenderableSeries.LegendRenderType getLegendRenderType() {
        return this.chartCategorySeriesRenderStyle.getLegendRenderType();
    }

    /* loaded from: target.jar:org/knowm/xchart/CategorySeries$CategorySeriesRenderStyle.class */
    public enum CategorySeriesRenderStyle implements RenderableSeries {
        Line(RenderableSeries.LegendRenderType.Line),
        Area(RenderableSeries.LegendRenderType.Line),
        Scatter(RenderableSeries.LegendRenderType.Scatter),
        SteppedBar(RenderableSeries.LegendRenderType.Box),
        Bar(RenderableSeries.LegendRenderType.BoxNoOutline),
        Stick(RenderableSeries.LegendRenderType.Line);

        private final RenderableSeries.LegendRenderType legendRenderType;

        CategorySeriesRenderStyle(RenderableSeries.LegendRenderType legendRenderType) {
            this.legendRenderType = legendRenderType;
        }

        @Override // org.knowm.xchart.internal.chartpart.RenderableSeries
        public RenderableSeries.LegendRenderType getLegendRenderType() {
            return this.legendRenderType;
        }
    }
}
