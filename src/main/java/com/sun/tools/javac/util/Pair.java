package com.sun.tools.javac.util;

import com.sun.tools.doclint.DocLint;
import java.util.Objects;

/* loaded from: target.jar:com/sun/tools/javac/util/Pair.class */
public class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A a, B b) {
        this.fst = a;
        this.snd = b;
    }

    public String toString() {
        return "Pair[" + this.fst + DocLint.TAGS_SEPARATOR + this.snd + "]";
    }

    public boolean equals(Object obj) {
        return (obj instanceof Pair) && Objects.equals(this.fst, ((Pair) obj).fst) && Objects.equals(this.snd, ((Pair) obj).snd);
    }

    public int hashCode() {
        if (this.fst != null) {
            return this.snd == null ? this.fst.hashCode() + 2 : (this.fst.hashCode() * 17) + this.snd.hashCode();
        }
        if (this.snd == null) {
            return 0;
        }
        return this.snd.hashCode() + 1;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }
}
