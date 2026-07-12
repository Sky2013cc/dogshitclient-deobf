package de.erichseifert.vectorgraphics2d;

import java.awt.RenderingHints;
import java.util.HashSet;
import java.util.Set;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/VectorHints.class */
public abstract class VectorHints {
    public static final Key KEY_EXPORT = new Key(0, "Vector export mode");
    public static final Object VALUE_EXPORT_READABILITY = new Value(KEY_EXPORT, 0, "Maximize readability for humans");
    public static final Object VALUE_EXPORT_QUALITY = new Value(KEY_EXPORT, 1, "Maximize render quality");
    public static final Object VALUE_EXPORT_SIZE = new Value(KEY_EXPORT, 2, "Minimize data size");
    public static final Key KEY_TEXT = new Key(1, "Text export mode");
    public static final Object VALUE_TEXT_DEFAULT = new Value(KEY_TEXT, 0, "Keep text");
    public static final Object VALUE_TEXT_VECTOR = new Value(KEY_TEXT, 1, "Convert text to vector shapes");

    protected VectorHints() {
        throw new UnsupportedOperationException();
    }

    /* loaded from: target.jar:de/erichseifert/vectorgraphics2d/VectorHints$Key.class */
    public static class Key extends RenderingHints.Key {
        private final String a;

        public Key(int i, String str) {
            super(i);
            this.a = str;
        }

        public int getIndex() {
            return intKey();
        }

        public boolean isCompatibleValue(Object obj) {
            return (obj instanceof Value) && ((Value) obj).isCompatibleKey(this);
        }

        public String toString() {
            return this.a;
        }
    }

    /* loaded from: target.jar:de/erichseifert/vectorgraphics2d/VectorHints$Value.class */
    public static class Value {
        private static final Set<String> a = new HashSet();
        private final Key b;
        private final int c;
        private final String d;

        private static synchronized void a(Value value) {
            String id = value.getId();
            if (a.contains(id)) {
                throw new ExceptionInInitializerError("Duplicate index: " + value.getIndex());
            }
            a.add(id);
        }

        public Value(Key key, int i, String str) {
            this.b = key;
            this.c = i;
            this.d = str;
            a(this);
        }

        public boolean isCompatibleKey(RenderingHints.Key key) {
            return this.b == key;
        }

        public int getIndex() {
            return this.c;
        }

        public String getId() {
            return this.b.getIndex() + CallSiteDescriptor.TOKEN_DELIMITER + getIndex();
        }

        public String toString() {
            return this.d;
        }
    }
}
