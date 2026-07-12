package com.sun.tools.javac.api;

import java.util.Locale;
import java.util.Set;
import javax.tools.Diagnostic;

/* loaded from: target.jar:com/sun/tools/javac/api/DiagnosticFormatter.class */
public interface DiagnosticFormatter<D extends Diagnostic<?>> {

    /* loaded from: target.jar:com/sun/tools/javac/api/DiagnosticFormatter$Configuration.class */
    public interface Configuration {

        /* loaded from: target.jar:com/sun/tools/javac/api/DiagnosticFormatter$Configuration$DiagnosticPart.class */
        public enum DiagnosticPart {
            SUMMARY,
            DETAILS,
            SOURCE,
            SUBDIAGNOSTICS,
            JLS
        }

        /* loaded from: target.jar:com/sun/tools/javac/api/DiagnosticFormatter$Configuration$MultilineLimit.class */
        public enum MultilineLimit {
            DEPTH,
            LENGTH
        }

        void setVisible(Set<DiagnosticPart> set);

        Set<DiagnosticPart> getVisible();

        void setMultilineLimit(MultilineLimit multilineLimit, int i);

        int getMultilineLimit(MultilineLimit multilineLimit);
    }

    /* loaded from: target.jar:com/sun/tools/javac/api/DiagnosticFormatter$PositionKind.class */
    public enum PositionKind {
        START,
        END,
        LINE,
        COLUMN,
        OFFSET
    }

    boolean displaySource(D d);

    String format(D d, Locale locale);

    String formatMessage(D d, Locale locale);

    String formatKind(D d, Locale locale);

    String formatSource(D d, boolean z, Locale locale);

    String formatPosition(D d, PositionKind positionKind, Locale locale);

    Configuration getConfiguration();
}
