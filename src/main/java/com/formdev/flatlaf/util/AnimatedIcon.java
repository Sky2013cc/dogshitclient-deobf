package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.Animator;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;

/* loaded from: target.jar:com/formdev/flatlaf/util/AnimatedIcon.class */
public interface AnimatedIcon extends Icon {
    void paintIconAnimated(Component component, Graphics graphics, int i, int i2, float f);

    float getValue(Component component);

    default void paintIcon(Component c, Graphics g, int x, int y) {
        AnimationSupport.paintIcon(this, c, g, x, y);
    }

    default boolean isAnimationEnabled() {
        return true;
    }

    default int getAnimationDuration() {
        return 150;
    }

    default int getAnimationResolution() {
        return 10;
    }

    default Animator.Interpolator getAnimationInterpolator() {
        return CubicBezierEasing.STANDARD_EASING;
    }

    default Object getClientPropertyKey() {
        return getClass();
    }

    /* loaded from: target.jar:com/formdev/flatlaf/util/AnimatedIcon$AnimationSupport.class */
    public static class AnimationSupport {
        private float startValue;
        private float targetValue;
        private float animatedValue;
        private float fraction;
        private Animator animator;
        private int x;
        private int y;

        public static void paintIcon(AnimatedIcon icon, Component c, Graphics g, int x, int y) {
            if (!isAnimationEnabled(icon, c)) {
                paintIconImpl(icon, c, g, x, y, null);
                return;
            }
            JComponent jc = (JComponent) c;
            Object key = icon.getClientPropertyKey();
            AnimationSupport as = (AnimationSupport) jc.getClientProperty(key);
            if (as == null) {
                as = new AnimationSupport();
                float value = icon.getValue(c);
                as.animatedValue = value;
                as.targetValue = value;
                as.startValue = value;
                as.x = x;
                as.y = y;
                jc.putClientProperty(key, as);
            } else {
                float value2 = icon.getValue(c);
                if (value2 != as.targetValue) {
                    if (as.animator == null) {
                        as.animator = new Animator(icon.getAnimationDuration(), fraction -> {
                            if (!c.isDisplayable()) {
                                as.animator.stop();
                                return;
                            }
                            as.animatedValue = as.startValue + ((as.targetValue - as.startValue) * fraction);
                            as.fraction = fraction;
                            c.repaint(as.x, as.y, icon.getIconWidth(), icon.getIconHeight());
                        }, () -> {
                            float f = as.targetValue;
                            as.animatedValue = f;
                            as.startValue = f;
                            as.animator = null;
                        });
                    }
                    if (as.animator.isRunning()) {
                        as.animator.cancel();
                        int duration2 = (int) (icon.getAnimationDuration() * as.fraction);
                        if (duration2 > 0) {
                            as.animator.setDuration(duration2);
                        }
                        as.startValue = as.animatedValue;
                    } else {
                        as.animator.setDuration(icon.getAnimationDuration());
                        as.animator.setResolution(icon.getAnimationResolution());
                        as.animator.setInterpolator(icon.getAnimationInterpolator());
                        as.animatedValue = as.startValue;
                    }
                    as.targetValue = value2;
                    as.animator.start();
                }
                as.x = x;
                as.y = y;
            }
            paintIconImpl(icon, c, g, x, y, as);
        }

        private static void paintIconImpl(AnimatedIcon icon, Component c, Graphics g, int x, int y, AnimationSupport as) {
            float value = as != null ? as.animatedValue : icon.getValue(c);
            icon.paintIconAnimated(c, g, x, y, value);
        }

        private static boolean isAnimationEnabled(AnimatedIcon icon, Component c) {
            return Animator.useAnimation() && icon.isAnimationEnabled() && (c instanceof JComponent);
        }

        public static void saveIconLocation(AnimatedIcon icon, Component c, int x, int y) {
            AnimationSupport as;
            if (isAnimationEnabled(icon, c) && (as = (AnimationSupport) ((JComponent) c).getClientProperty(icon.getClientPropertyKey())) != null) {
                as.x = x;
                as.y = y;
            }
        }
    }
}
