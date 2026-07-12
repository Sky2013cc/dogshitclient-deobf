package com.formdev.flatlaf.icons;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatOptionPaneInformationIcon.class */
public class FlatOptionPaneInformationIcon extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneInformationIcon() {
        super("OptionPane.icon.informationColor", "Actions.Blue");
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, 28.0f, 28.0f);
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createInside() {
        Path2D.Float r0 = new Path2D.Float(0);
        r0.append(new RoundRectangle2D.Float(14.0f, 13.0f, 4.0f, 12.0f, 4.0f, 4.0f), false);
        r0.append(new Ellipse2D.Float(14.0f, 7.0f, 4.0f, 4.0f), false);
        return r0;
    }
}
