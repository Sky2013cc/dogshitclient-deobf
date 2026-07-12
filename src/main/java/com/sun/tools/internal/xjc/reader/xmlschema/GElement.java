package com.sun.tools.internal.xjc.reader.xmlschema;

import com.sun.tools.internal.xjc.reader.gbind.Element;
import com.sun.xml.internal.xsom.XSParticle;
import java.util.HashSet;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/GElement.class */
abstract class GElement extends Element {
    final Set<XSParticle> particles = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getPropertyNameSeed();
}
