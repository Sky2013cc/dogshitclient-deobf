package org.knowm.xchart.internal.chartpart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.knowm.xchart.internal.series.AxesChartSeries;
import org.knowm.xchart.internal.series.AxesChartSeriesCategory;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.BoxStyler;

/* loaded from: target.jar:org/knowm/xchart/internal/chartpart/BoxPlotDataCalculator.class */
public class BoxPlotDataCalculator<ST extends AxesChartStyler, S extends AxesChartSeries> {
    public List<BoxPlotData> calculate(Map<String, S> seriesMap, ST boxPlotStyler) {
        List<BoxPlotData> boxPlotDataList = new ArrayList<>();
        for (S series : seriesMap.values()) {
            if (series.isEnabled()) {
                Collection<? extends Number> yData = ((AxesChartSeriesCategory) series).getYData();
                List<Double> data = new ArrayList<>();
                for (Number next : yData) {
                    if (next != null) {
                        data.add(Double.valueOf(next.doubleValue()));
                    }
                }
                Collections.sort(data);
                BoxPlotData boxPlotData = calculate(data, (List<Double>) boxPlotStyler);
                boxPlotDataList.add(boxPlotData);
            }
        }
        return boxPlotDataList;
    }

    private BoxPlotData calculate(List<Double> data, ST boxPlotStyler) {
        BoxPlotData boxPlotData = new BoxPlotData();
        int n = data.size();
        BoxStyler.BoxplotCalCulationMethod boxplotCalCulationMethod = ((BoxStyler) boxPlotStyler).getBoxplotCalCulationMethod();
        double q1P = 0.0d;
        double q2P = 0.0d;
        double q3P = 0.0d;
        if (BoxStyler.BoxplotCalCulationMethod.N_PLUS_1.equals(boxplotCalCulationMethod)) {
            q1P = (n + 1) / 4.0d;
            q2P = (2 * (n + 1)) / 4.0d;
            q3P = (3 * (n + 1)) / 4.0d;
        } else if (BoxStyler.BoxplotCalCulationMethod.N_LESS_1.equals(boxplotCalCulationMethod)) {
            q1P = (n - 1) / 4.0d;
            q2P = (2 * (n - 1)) / 4.0d;
            q3P = (3 * (n - 1)) / 4.0d;
        } else if (BoxStyler.BoxplotCalCulationMethod.NP.equals(boxplotCalCulationMethod)) {
            q1P = n / 4.0d;
            q2P = (2 * n) / 4.0d;
            q3P = (3 * n) / 4.0d;
        } else if (BoxStyler.BoxplotCalCulationMethod.N_LESS_1_PLUS_1.equals(boxplotCalCulationMethod)) {
            q1P = ((n - 1) / 4.0d) + 1.0d;
            q2P = ((2 * (n - 1)) / 4.0d) + 1.0d;
            q3P = ((3 * (n - 1)) / 4.0d) + 1.0d;
        }
        boxPlotData.q1 = getQuartile(data, q1P, boxplotCalCulationMethod);
        boxPlotData.median = getQuartile(data, q2P, boxplotCalCulationMethod);
        boxPlotData.q3 = getQuartile(data, q3P, boxplotCalCulationMethod);
        double irq = boxPlotData.q3 - boxPlotData.q1;
        boxPlotData.lower = boxPlotData.q1 - (1.5d * irq);
        if (boxPlotData.lower < data.get(0).doubleValue()) {
            boxPlotData.lower = data.get(0).doubleValue();
        }
        boxPlotData.upper = boxPlotData.q3 + (1.5d * irq);
        if (boxPlotData.upper > data.get(data.size() - 1).doubleValue()) {
            boxPlotData.upper = data.get(data.size() - 1).doubleValue();
        }
        return boxPlotData;
    }

    private static double getQuartile(List<Double> data, double qiP, BoxStyler.BoxplotCalCulationMethod boxplotCalCulationMethod) {
        double qi;
        int previousItem = (int) Math.floor(qiP);
        int previousItem_index = previousItem == 0 ? 0 : previousItem - 1;
        int nextItem = (int) Math.ceil(qiP);
        int nextItem_index = data.size() == 1 ? 0 : nextItem - 1;
        if (BoxStyler.BoxplotCalCulationMethod.NP == boxplotCalCulationMethod) {
            if (previousItem == nextItem) {
                qi = (data.get(previousItem_index).doubleValue() + data.get(nextItem_index).doubleValue()) / 2.0d;
            } else {
                qi = data.get(nextItem_index).doubleValue();
            }
        } else if (previousItem == nextItem) {
            qi = data.get(previousItem_index).doubleValue();
        } else {
            qi = (data.get(previousItem_index).doubleValue() * (nextItem - qiP)) + (data.get(nextItem_index).doubleValue() * (qiP - previousItem));
        }
        return qi;
    }
}
