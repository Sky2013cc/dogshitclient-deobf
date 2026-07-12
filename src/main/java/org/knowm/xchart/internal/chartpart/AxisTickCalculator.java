package org.knowm.xchart.internal.chartpart;

import java.text.Format;
import java.util.List;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/AxisTickCalculator.class */
public interface AxisTickCalculator {
    List<Double> getTickLocations();

    List<String> getTickLabels();

    Format getAxisFormat();
}
