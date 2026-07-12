package com.formdev.flatlaf.util;

import java.awt.Component;
import java.awt.Container;

/* loaded from: target.jar:com/formdev/flatlaf/util/SwingUtils.class */
public class SwingUtils {
    public static <T extends Component> T getComponentByName(Container container, String str) {
        T t;
        for (Container container2 : container.getComponents()) {
            if (str.equals(container2.getName())) {
                return container2;
            }
            if ((container2 instanceof Container) && (t = (T) getComponentByName(container2, str)) != null) {
                return t;
            }
        }
        return null;
    }
}
