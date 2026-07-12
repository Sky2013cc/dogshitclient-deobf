package jdk.nashorn.internal.ir;

import jdk.nashorn.internal.codegen.types.Type;

/* loaded from: target.jar:jdk/nashorn/internal/ir/Optimistic.class */
public interface Optimistic {
    int getProgramPoint();

    Optimistic setProgramPoint(int i);

    boolean canBeOptimistic();

    Type getMostOptimisticType();

    Type getMostPessimisticType();

    Optimistic setType(Type type);
}
