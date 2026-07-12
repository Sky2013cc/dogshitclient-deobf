package com.formdev.flatlaf.icons;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatOptionPaneQuestionIcon.class */
public class FlatOptionPaneQuestionIcon extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneQuestionIcon() {
        super("OptionPane.icon.questionColor", "Actions.Blue");
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, 28.0f, 28.0f);
    }

    @Override // com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon
    protected Shape createInside() {
        Path2D.Float r0 = new Path2D.Float(1, 10);
        r0.moveTo(11.5d, 11.75d);
        r0.curveTo(11.75d, 9.5d, 13.75d, 8.0d, 16.0d, 8.0d);
        r0.curveTo(18.25d, 8.0d, 20.5d, 9.5d, 20.5d, 11.75d);
        r0.curveTo(20.5d, 14.75d, 16.0d, 15.5d, 16.0d, 19.0d);
        BasicStroke stroke = new BasicStroke(3.0f, 1, 0);
        Path2D.Float r02 = new Path2D.Float(0);
        r02.append(new Ellipse2D.Float(14.3f, 22.3f, 3.4f, 3.4f), false);
        r02.append(stroke.createStrokedShape(r0), false);
        return r02;
    }
}
