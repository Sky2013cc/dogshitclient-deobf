package org.knowm.xchart.style;

import org.knowm.xchart.style.theme.Theme;

/* loaded from: target.jar:org/knowm/xchart/style/BoxStyler.class */
public class BoxStyler extends AxesChartStyler {
    private BoxplotCalCulationMethod boxplotCalCulationMethod;

    /* loaded from: target.jar:org/knowm/xchart/style/BoxStyler$BoxplotCalCulationMethod.class */
    public enum BoxplotCalCulationMethod {
        N_PLUS_1,
        N_LESS_1,
        NP,
        N_LESS_1_PLUS_1
    }

    public BoxStyler() {
        setAllStyles();
        super.setAllStyles();
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        super.setAllStyles();
        this.boxplotCalCulationMethod = BoxplotCalCulationMethod.N_LESS_1_PLUS_1;
    }

    public BoxplotCalCulationMethod getBoxplotCalCulationMethod() {
        return this.boxplotCalCulationMethod;
    }

    public BoxStyler setBoxplotCalCulationMethod(BoxplotCalCulationMethod boxplotCalCulationMethod) {
        this.boxplotCalCulationMethod = boxplotCalCulationMethod;
        return this;
    }
}
