package com.sun.xml.internal.rngom.ast.builder;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import com.sun.xml.internal.rngom.ast.om.Location;
import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/GrammarSection.class */
public interface GrammarSection<P extends ParsedPattern, E extends ParsedElementAnnotation, L extends Location, A extends Annotations<E, L, CL>, CL extends CommentList<L>> {
    public static final Combine COMBINE_CHOICE = new Combine("choice");
    public static final Combine COMBINE_INTERLEAVE = new Combine("interleave");
    public static final String START = "��#start��";

    void define(String str, Combine combine, P p, L l, A a) throws BuildException;

    void topLevelAnnotation(E e) throws BuildException;

    void topLevelComment(CL cl) throws BuildException;

    Div<P, E, L, A, CL> makeDiv();

    Include<P, E, L, A, CL> makeInclude();

    /* loaded from: target.jar:com/sun/xml/internal/rngom/ast/builder/GrammarSection$Combine.class */
    public static final class Combine {
        private final String name;

        private Combine(String name) {
            this.name = name;
        }

        public final String toString() {
            return this.name;
        }
    }
}
