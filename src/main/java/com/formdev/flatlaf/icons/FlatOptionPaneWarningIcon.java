package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatOptionPaneWarningIcon.class */
public class FlatOptionPaneWarningIcon extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneWarningIcon() {
        super("OptionPane.icon.warningColor", "Actions.Yellow");
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createOutside() {
        return FlatUIUtils.createRoundTrianglePath(16.0f, 0.0f, 32.0f, 28.0f, 0.0f, 28.0f, 4.0f);
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createInside() {
        Path2D.Float r0 = new Path2D.Float(0);
        r0.append(new RoundRectangle2D.Float(14.0f, 8.0f, 4.0f, 11.0f, 4.0f, 4.0f), false);
        r0.append(new Ellipse2D.Float(14.0f, 21.0f, 4.0f, 4.0f), false);
        return r0;
    }
}
