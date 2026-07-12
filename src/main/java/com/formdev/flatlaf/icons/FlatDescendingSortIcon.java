package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/* loaded from: target.jar:com/formdev/flatlaf/icons/FlatDescendingSortIcon.class */
public class FlatDescendingSortIcon extends FlatAscendingSortIcon {
    @Override // com.formdev.flatlaf.icons.FlatAscendingSortIcon
    protected void paintArrow(Component c, Graphics2D g, boolean chevron) {
        if (chevron) {
            Path2D path = FlatUIUtils.createPath(false, 1.0d, 0.0d, 5.0d, 4.0d, 9.0d, 0.0d);
            g.setStroke(new BasicStroke(1.0f));
            g.draw(path);
            return;
        }
        g.fill(FlatUIUtils.createPath(0.5d, 0.0d, 5.0d, 5.0d, 9.5d, 0.0d));
    }
}
