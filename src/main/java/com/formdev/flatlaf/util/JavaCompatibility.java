package com.formdev.flatlaf.util;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import javax.swing.JComponent;

/* loaded from: target.jar:com/formdev/flatlaf/util/JavaCompatibility.class */
public class JavaCompatibility {
    private static MethodHandle drawStringUnderlineCharAtMethod;
    private static MethodHandle getClippedStringMethod;

    public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
        String str;
        Class[] clsArr;
        synchronized (JavaCompatibility.class) {
            if (drawStringUnderlineCharAtMethod == null) {
                try {
                    if (SystemInfo.isJava_9_orLater) {
                        str = "javax.swing.plaf.basic.BasicGraphicsUtils";
                    } else {
                        str = "sun.swing.SwingUtilities2";
                    }
                    Class<?> cls = Class.forName(str);
                    Class cls2 = Void.TYPE;
                    if (SystemInfo.isJava_9_orLater) {
                        clsArr = new Class[]{JComponent.class, Graphics2D.class, String.class, Integer.TYPE, Float.TYPE, Float.TYPE};
                    } else {
                        clsArr = new Class[]{JComponent.class, Graphics.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
                    }
                    MethodType mt = MethodType.methodType((Class<?>) cls2, (Class<?>[]) clsArr);
                    drawStringUnderlineCharAtMethod = MethodHandles.publicLookup().findStatic(cls, "drawStringUnderlineCharAt", mt);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            if (SystemInfo.isJava_9_orLater) {
                (void) drawStringUnderlineCharAtMethod.invoke(c, (Graphics2D) g, text, underlinedIndex, x, y);
            } else {
                (void) drawStringUnderlineCharAtMethod.invoke(c, g, text, underlinedIndex, x, y);
            }
        } finally {
            LoggingFacade.INSTANCE.logSevere(null, ex);
            RuntimeException runtimeException = new RuntimeException(ex);
        }
    }

    public static String getClippedString(JComponent c, FontMetrics fm, String string, int availTextWidth) {
        String str;
        String str2;
        synchronized (JavaCompatibility.class) {
            if (getClippedStringMethod == null) {
                try {
                    if (SystemInfo.isJava_9_orLater) {
                        str = "javax.swing.plaf.basic.BasicGraphicsUtils";
                    } else {
                        str = "sun.swing.SwingUtilities2";
                    }
                    Class<?> cls = Class.forName(str);
                    MethodType mt = MethodType.methodType(String.class, JComponent.class, FontMetrics.class, String.class, Integer.TYPE);
                    MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
                    if (SystemInfo.isJava_9_orLater) {
                        str2 = "getClippedString";
                    } else {
                        str2 = "clipStringIfNecessary";
                    }
                    getClippedStringMethod = publicLookup.findStatic(cls, str2, mt);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            return (String) getClippedStringMethod.invoke(c, fm, string, availTextWidth);
        } finally {
            LoggingFacade.INSTANCE.logSevere(null, ex);
            RuntimeException runtimeException = new RuntimeException(ex);
        }
    }
}
