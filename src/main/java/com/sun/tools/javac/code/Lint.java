package com.sun.tools.javac.code;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: target.jar:com/sun/tools/javac/code/Lint.class */
public class Lint {
    private final AugmentVisitor augmentor;
    private final EnumSet<LintCategory> values;
    private final EnumSet<LintCategory> suppressedValues;
    protected static final Context.Key<Lint> lintKey = new Context.Key<>();
    private static final Map<String, LintCategory> map = new ConcurrentHashMap(20);

    public static Lint instance(Context context) {
        Lint lint = (Lint) context.get(lintKey);
        if (lint == null) {
            lint = new Lint(context);
        }
        return lint;
    }

    public Lint augment(Attribute.Compound compound) {
        return this.augmentor.augment(this, compound);
    }

    public Lint augment(Symbol symbol) {
        Lint augment = this.augmentor.augment(this, symbol.getDeclarationAttributes());
        if (symbol.isDeprecated()) {
            if (augment == this) {
                augment = new Lint(this);
            }
            augment.values.remove(LintCategory.DEPRECATION);
            augment.suppressedValues.add(LintCategory.DEPRECATION);
        }
        return augment;
    }

    protected Lint(Context context) {
        Options instance = Options.instance(context);
        this.values = EnumSet.noneOf(LintCategory.class);
        for (Map.Entry<String, LintCategory> entry : map.entrySet()) {
            if (instance.lint(entry.getKey())) {
                this.values.add(entry.getValue());
            }
        }
        this.suppressedValues = EnumSet.noneOf(LintCategory.class);
        context.put((Context.Key<Context.Key<Lint>>) lintKey, (Context.Key<Lint>) this);
        this.augmentor = new AugmentVisitor(context);
    }

    protected Lint(Lint lint) {
        this.augmentor = lint.augmentor;
        this.values = lint.values.clone();
        this.suppressedValues = lint.suppressedValues.clone();
    }

    public String toString() {
        return "Lint:[values" + this.values + " suppressedValues" + this.suppressedValues + "]";
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Lint$LintCategory.class */
    public enum LintCategory {
        AUXILIARYCLASS("auxiliaryclass"),
        CAST("cast"),
        CLASSFILE("classfile"),
        DEPRECATION("deprecation"),
        DEP_ANN("dep-ann"),
        DIVZERO("divzero"),
        EMPTY("empty"),
        FALLTHROUGH("fallthrough"),
        FINALLY("finally"),
        OPTIONS("options"),
        OVERLOADS("overloads"),
        OVERRIDES("overrides"),
        PATH("path"),
        PROCESSING("processing"),
        RAW("rawtypes"),
        SERIAL("serial"),
        STATIC("static"),
        SUNAPI("sunapi", true),
        TRY("try"),
        UNCHECKED("unchecked"),
        VARARGS("varargs");

        public final String option;
        public final boolean hidden;

        LintCategory(String str) {
            this(str, false);
        }

        LintCategory(String str, boolean z) {
            this.option = str;
            this.hidden = z;
            Lint.map.put(str, this);
        }

        static LintCategory get(String str) {
            return (LintCategory) Lint.map.get(str);
        }
    }

    public boolean isEnabled(LintCategory lintCategory) {
        return this.values.contains(lintCategory);
    }

    public boolean isSuppressed(LintCategory lintCategory) {
        return this.suppressedValues.contains(lintCategory);
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Lint$AugmentVisitor.class */
    protected static class AugmentVisitor implements Attribute.Visitor {
        private final Context context;
        private Symtab syms;
        private Lint parent;
        private Lint lint;

        AugmentVisitor(Context context) {
            this.context = context;
        }

        Lint augment(Lint lint, Attribute.Compound compound) {
            initSyms();
            this.parent = lint;
            this.lint = null;
            compound.accept(this);
            return this.lint == null ? lint : this.lint;
        }

        Lint augment(Lint lint, List<Attribute.Compound> list) {
            initSyms();
            this.parent = lint;
            this.lint = null;
            Iterator<Attribute.Compound> it = list.iterator();
            while (it.hasNext()) {
                it.next().accept(this);
            }
            return this.lint == null ? lint : this.lint;
        }

        private void initSyms() {
            if (this.syms == null) {
                this.syms = Symtab.instance(this.context);
            }
        }

        private void suppress(LintCategory lintCategory) {
            if (this.lint == null) {
                this.lint = new Lint(this.parent);
            }
            this.lint.suppressedValues.add(lintCategory);
            this.lint.values.remove(lintCategory);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
            LintCategory lintCategory;
            if (constant.type.tsym == this.syms.stringType.tsym && (lintCategory = LintCategory.get((String) constant.value)) != null) {
                suppress(lintCategory);
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r2) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            if (compound.type.tsym == this.syms.suppressWarningsType.tsym) {
                List list = compound.values;
                while (true) {
                    List list2 = list;
                    if (list2.nonEmpty()) {
                        Pair pair = (Pair) list2.head;
                        if (((Symbol.MethodSymbol) pair.fst).name.toString().equals("value")) {
                            ((Attribute) pair.snd).accept(this);
                        }
                        list = list2.tail;
                    } else {
                        return;
                    }
                }
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            for (Attribute attribute : array.values) {
                attribute.accept(this);
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r2) {
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
        }
    }
}
