package com.sun.xml.internal.rngom.binary.visitor;

import com.sun.xml.internal.rngom.binary.Pattern;
import com.sun.xml.internal.rngom.nc.NameClass;
import org.relaxng.datatype.Datatype;

/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/visitor/PatternVisitor.class */
public interface PatternVisitor {
    void visitEmpty();

    void visitNotAllowed();

    void visitError();

    void visitAfter(Pattern pattern, Pattern pattern2);

    void visitGroup(Pattern pattern, Pattern pattern2);

    void visitInterleave(Pattern pattern, Pattern pattern2);

    void visitChoice(Pattern pattern, Pattern pattern2);

    void visitOneOrMore(Pattern pattern);

    void visitElement(NameClass nameClass, Pattern pattern);

    void visitAttribute(NameClass nameClass, Pattern pattern);

    void visitData(Datatype datatype);

    void visitDataExcept(Datatype datatype, Pattern pattern);

    void visitValue(Datatype datatype, Object obj);

    void visitText();

    void visitList(Pattern pattern);
}
