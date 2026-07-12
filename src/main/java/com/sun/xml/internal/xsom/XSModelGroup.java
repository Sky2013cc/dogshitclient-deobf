package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSModelGroup.class */
public interface XSModelGroup extends XSComponent, XSTerm, Iterable<XSParticle> {
    public static final Compositor ALL = Compositor.ALL;
    public static final Compositor SEQUENCE = Compositor.SEQUENCE;
    public static final Compositor CHOICE = Compositor.CHOICE;

    Compositor getCompositor();

    XSParticle getChild(int i);

    int getSize();

    XSParticle[] getChildren();

    /* loaded from: target.jar:com/sun/xml/internal/xsom/XSModelGroup$Compositor.class */
    public enum Compositor {
        ALL("all"),
        CHOICE("choice"),
        SEQUENCE("sequence");

        private final String value;

        Compositor(String _value) {
            this.value = _value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}
