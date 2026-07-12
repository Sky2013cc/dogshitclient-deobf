package org.knowm.xchart.internal.chartpart;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/RenderableSeries.class */
public interface RenderableSeries {

    /* loaded from: target.jar:org/knowm/xchart/internal/chartpart/RenderableSeries$LegendRenderType.class */
    public enum LegendRenderType {
        Line,
        Scatter,
        Box,
        BoxNoOutline
    }

    LegendRenderType getLegendRenderType();
}
