package org.apache.fontbox.afm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: target.jar:org/apache/fontbox/afm/Composite.class */
public class Composite {
    private final String name;
    private final List<CompositePart> parts = new ArrayList();

    public Composite(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addPart(CompositePart part) {
        this.parts.add(part);
    }

    public List<CompositePart> getParts() {
        return Collections.unmodifiableList(this.parts);
    }
}
