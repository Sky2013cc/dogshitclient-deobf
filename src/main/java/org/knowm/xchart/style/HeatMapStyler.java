package org.knowm.xchart.style;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Function;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/style/HeatMapStyler.class */
public class HeatMapStyler extends AxesChartStyler {
    private boolean isPiecewise;
    private boolean isPiecewiseRanged = true;
    private int splitNumber;
    private static final Color[] DEFAULT_RANGE_COLORS = {new Color(255, 255, 255), new Color(0, 255, 255)};
    private Color[] rangeColors;
    private boolean isDrawBorder;
    private boolean showValue;
    private Font valueFont;
    private Color valueFontColor;
    double min;
    double max;
    private int gradientColorColumnWeight;
    private int gradientColorColumnHeight;
    private String heatMapValueDecimalPattern;
    private Function<Double, String> heatMapDecimalValueFormatter;

    public void setTheme(Theme theme) {
        this.theme = theme;
        setAllStyles();
    }

    @Override // org.knowm.xchart.style.AxesChartStyler, org.knowm.xchart.style.Styler
    public void setAllStyles() {
        super.setAllStyles();
        this.rangeColors = new Color[3];
        this.rangeColors[0] = new Color(255, 165, 0);
        this.rangeColors[1] = new Color(255, 69, 0);
        this.rangeColors[2] = new Color(139, 0, 0);
        this.splitNumber = 5;
        this.valueFont = new Font("SansSerif", 0, 16);
        this.valueFontColor = ChartColor.BLACK.getColor();
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
        this.gradientColorColumnWeight = 30;
        this.gradientColorColumnHeight = 200;
    }

    @Override // org.knowm.xchart.style.Styler
    public HeatMapStyler setLegendPosition(Styler.LegendPosition legendPosition) {
        if (!Styler.LegendPosition.OutsideE.equals(legendPosition) && !Styler.LegendPosition.OutsideS.equals(legendPosition)) {
            throw new IllegalArgumentException("HeatMapStyler LegendPosition must be OutsideE or OutsideS!!!");
        }
        super.setLegendPosition(legendPosition);
        return this;
    }

    public boolean isPiecewise() {
        return this.isPiecewise;
    }

    public HeatMapStyler setPiecewise(boolean isPiecewise) {
        this.isPiecewise = isPiecewise;
        return this;
    }

    public int getSplitNumber() {
        return this.splitNumber;
    }

    public HeatMapStyler setSplitNumber(int splitNumber) {
        if (splitNumber > 0) {
            this.splitNumber = splitNumber;
        } else {
            this.splitNumber = 1;
        }
        return this;
    }

    public Color[] getRangeColors() {
        return this.rangeColors;
    }

    public HeatMapStyler setRangeColors(Color[] rangeColors) {
        if (rangeColors != null && rangeColors.length > 0) {
            if (rangeColors.length == 1) {
                this.rangeColors = new Color[2];
                this.rangeColors[0] = rangeColors[0];
                this.rangeColors[1] = rangeColors[0];
            }
            this.rangeColors = rangeColors;
        } else {
            this.rangeColors = DEFAULT_RANGE_COLORS;
        }
        return this;
    }

    public boolean isDrawBorder() {
        return this.isDrawBorder;
    }

    public HeatMapStyler setDrawBorder(boolean isDrawBorder) {
        this.isDrawBorder = isDrawBorder;
        return this;
    }

    public boolean isShowValue() {
        return this.showValue;
    }

    public HeatMapStyler setShowValue(boolean showValue) {
        this.showValue = showValue;
        return this;
    }

    public Font getValueFont() {
        return this.valueFont;
    }

    public HeatMapStyler setValueFont(Font valueFont) {
        this.valueFont = valueFont;
        return this;
    }

    public Color getValueFontColor() {
        return this.valueFontColor;
    }

    public HeatMapStyler setValueFontColor(Color valueFontColor) {
        this.valueFontColor = valueFontColor;
        return this;
    }

    public double getMin() {
        return this.min;
    }

    public HeatMapStyler setMin(double min) {
        this.min = min;
        return this;
    }

    public double getMax() {
        return this.max;
    }

    public HeatMapStyler setMax(double max) {
        this.max = max;
        return this;
    }

    public int getGradientColorColumnWeight() {
        return this.gradientColorColumnWeight;
    }

    public HeatMapStyler setGradientColorColumnWeight(int gradientColorColumnWeight) {
        this.gradientColorColumnWeight = Math.max(gradientColorColumnWeight, 10);
        return this;
    }

    public int getGradientColorColumnHeight() {
        return this.gradientColorColumnHeight;
    }

    public HeatMapStyler setGradientColorColumnHeight(int gradientColorColumnHeight) {
        this.gradientColorColumnHeight = Math.max(gradientColorColumnHeight, 100);
        return this;
    }

    public String getHeatMapValueDecimalPattern() {
        return this.heatMapValueDecimalPattern;
    }

    public HeatMapStyler setHeatMapValueDecimalPattern(String heatMapValueDecimalPattern) {
        this.heatMapValueDecimalPattern = heatMapValueDecimalPattern;
        return this;
    }

    public Function<Double, String> getHeatMapDecimalValueFormatter() {
        return this.heatMapDecimalValueFormatter;
    }

    public HeatMapStyler setHeatMapDecimalValueFormatter(Function<Double, String> heatMapDecimalValueFormatter) {
        this.heatMapDecimalValueFormatter = heatMapDecimalValueFormatter;
        return this;
    }

    public boolean isPiecewiseRanged() {
        return this.isPiecewiseRanged;
    }

    public HeatMapStyler setPiecewiseRanged(boolean piecewiseRanged) {
        if (piecewiseRanged) {
            setPiecewise(true);
        }
        this.isPiecewiseRanged = piecewiseRanged;
        return this;
    }
}
