package com.sun.tools.javac.util;

import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic.class */
public class JCDiagnostic implements Diagnostic<JavaFileObject> {
    private final DiagnosticType type;
    private final DiagnosticSource source;
    private final DiagnosticPosition position;
    private final String key;
    protected final Object[] args;
    private final Set<DiagnosticFlag> flags;
    private final Lint.LintCategory lintCategory;
    private SourcePosition sourcePosition;
    private DiagnosticFormatter<JCDiagnostic> defaultFormatter;

    @Deprecated
    private static DiagnosticFormatter<JCDiagnostic> fragmentFormatter;

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$DiagnosticFlag.class */
    public enum DiagnosticFlag {
        MANDATORY,
        RESOLVE_ERROR,
        SYNTAX,
        RECOVERABLE,
        NON_DEFERRABLE,
        COMPRESSED
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$DiagnosticPosition.class */
    public interface DiagnosticPosition {
        JCTree getTree();

        int getStartPosition();

        int getPreferredPosition();

        int getEndPosition(EndPosTable endPosTable);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$Factory.class */
    public static class Factory {
        protected static final Context.Key<Factory> diagnosticFactoryKey = new Context.Key<>();
        DiagnosticFormatter<JCDiagnostic> formatter;
        final String prefix;
        final Set<DiagnosticFlag> defaultErrorFlags;

        public static Factory instance(Context context) {
            Factory factory = (Factory) context.get(diagnosticFactoryKey);
            if (factory == null) {
                factory = new Factory(context);
            }
            return factory;
        }

        protected Factory(Context context) {
            this(JavacMessages.instance(context), "compiler");
            context.put((Context.Key<Context.Key<Factory>>) diagnosticFactoryKey, (Context.Key<Factory>) this);
            final Options instance = Options.instance(context);
            initOptions(instance);
            instance.addListener(new Runnable() { // from class: com.sun.tools.javac.util.JCDiagnostic.Factory.1
                @Override // java.lang.Runnable
                public void run() {
                    Factory.this.initOptions(instance);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initOptions(Options options) {
            if (options.isSet("onlySyntaxErrorsUnrecoverable")) {
                this.defaultErrorFlags.add(DiagnosticFlag.RECOVERABLE);
            }
        }

        public Factory(JavacMessages javacMessages, String str) {
            this.prefix = str;
            this.formatter = new BasicDiagnosticFormatter(javacMessages);
            this.defaultErrorFlags = EnumSet.of(DiagnosticFlag.MANDATORY);
        }

        public JCDiagnostic error(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.ERROR, null, this.defaultErrorFlags, diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic mandatoryWarning(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.WARNING, null, EnumSet.of(DiagnosticFlag.MANDATORY), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic mandatoryWarning(Lint.LintCategory lintCategory, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.WARNING, lintCategory, EnumSet.of(DiagnosticFlag.MANDATORY), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic warning(Lint.LintCategory lintCategory, String str, Object... objArr) {
            return create(DiagnosticType.WARNING, lintCategory, EnumSet.noneOf(DiagnosticFlag.class), null, null, str, objArr);
        }

        public JCDiagnostic warning(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.WARNING, null, EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic warning(Lint.LintCategory lintCategory, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.WARNING, lintCategory, EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic mandatoryNote(DiagnosticSource diagnosticSource, String str, Object... objArr) {
            return create(DiagnosticType.NOTE, null, EnumSet.of(DiagnosticFlag.MANDATORY), diagnosticSource, null, str, objArr);
        }

        public JCDiagnostic note(String str, Object... objArr) {
            return create(DiagnosticType.NOTE, null, EnumSet.noneOf(DiagnosticFlag.class), null, null, str, objArr);
        }

        public JCDiagnostic note(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(DiagnosticType.NOTE, null, EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic fragment(String str, Object... objArr) {
            return create(DiagnosticType.FRAGMENT, null, EnumSet.noneOf(DiagnosticFlag.class), null, null, str, objArr);
        }

        public JCDiagnostic create(DiagnosticType diagnosticType, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(diagnosticType, null, EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, str, objArr);
        }

        public JCDiagnostic create(DiagnosticType diagnosticType, Lint.LintCategory lintCategory, Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return new JCDiagnostic(this.formatter, diagnosticType, lintCategory, set, diagnosticSource, diagnosticPosition, qualify(diagnosticType, str), objArr);
        }

        protected String qualify(DiagnosticType diagnosticType, String str) {
            return this.prefix + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR + diagnosticType.key + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR + str;
        }
    }

    @Deprecated
    public static JCDiagnostic fragment(String str, Object... objArr) {
        return new JCDiagnostic(getFragmentFormatter(), DiagnosticType.FRAGMENT, null, EnumSet.noneOf(DiagnosticFlag.class), null, null, "compiler." + DiagnosticType.FRAGMENT.key + sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR + str, objArr);
    }

    @Deprecated
    public static DiagnosticFormatter<JCDiagnostic> getFragmentFormatter() {
        if (fragmentFormatter == null) {
            fragmentFormatter = new BasicDiagnosticFormatter(JavacMessages.getDefaultMessages());
        }
        return fragmentFormatter;
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$DiagnosticType.class */
    public enum DiagnosticType {
        FRAGMENT("misc"),
        NOTE("note"),
        WARNING("warn"),
        ERROR("err");

        final String key;

        DiagnosticType(String str) {
            this.key = str;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$SimpleDiagnosticPosition.class */
    public static class SimpleDiagnosticPosition implements DiagnosticPosition {
        private final int pos;

        public SimpleDiagnosticPosition(int i) {
            this.pos = i;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public JCTree getTree() {
            return null;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getStartPosition() {
            return this.pos;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getPreferredPosition() {
            return this.pos;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getEndPosition(EndPosTable endPosTable) {
            return this.pos;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$SourcePosition.class */
    class SourcePosition {
        private final int line;
        private final int column;

        SourcePosition() {
            int preferredPosition = JCDiagnostic.this.position == null ? -1 : JCDiagnostic.this.position.getPreferredPosition();
            if (preferredPosition == -1 || JCDiagnostic.this.source == null) {
                this.column = -1;
                this.line = -1;
            } else {
                this.line = JCDiagnostic.this.source.getLineNumber(preferredPosition);
                this.column = JCDiagnostic.this.source.getColumnNumber(preferredPosition, true);
            }
        }

        public int getLineNumber() {
            return this.line;
        }

        public int getColumnNumber() {
            return this.column;
        }
    }

    protected JCDiagnostic(DiagnosticFormatter<JCDiagnostic> diagnosticFormatter, DiagnosticType diagnosticType, Lint.LintCategory lintCategory, Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
        if (diagnosticSource == null && diagnosticPosition != null && diagnosticPosition.getPreferredPosition() != -1) {
            throw new IllegalArgumentException();
        }
        this.defaultFormatter = diagnosticFormatter;
        this.type = diagnosticType;
        this.lintCategory = lintCategory;
        this.flags = set;
        this.source = diagnosticSource;
        this.position = diagnosticPosition;
        this.key = str;
        this.args = objArr;
    }

    public DiagnosticType getType() {
        return this.type;
    }

    public List<JCDiagnostic> getSubdiagnostics() {
        return List.nil();
    }

    public boolean isMultiline() {
        return false;
    }

    public boolean isMandatory() {
        return this.flags.contains(DiagnosticFlag.MANDATORY);
    }

    public boolean hasLintCategory() {
        return this.lintCategory != null;
    }

    public Lint.LintCategory getLintCategory() {
        return this.lintCategory;
    }

    @Override // 
    /* renamed from: getSource, reason: merged with bridge method [inline-methods] */
    public JavaFileObject mo615getSource() {
        if (this.source == null) {
            return null;
        }
        return this.source.getFile();
    }

    public DiagnosticSource getDiagnosticSource() {
        return this.source;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getIntStartPosition() {
        if (this.position == null) {
            return -1;
        }
        return this.position.getStartPosition();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getIntPosition() {
        if (this.position == null) {
            return -1;
        }
        return this.position.getPreferredPosition();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getIntEndPosition() {
        if (this.position == null) {
            return -1;
        }
        return this.position.getEndPosition(this.source.getEndPosTable());
    }

    public long getStartPosition() {
        return getIntStartPosition();
    }

    public long getPosition() {
        return getIntPosition();
    }

    public long getEndPosition() {
        return getIntEndPosition();
    }

    public DiagnosticPosition getDiagnosticPosition() {
        return this.position;
    }

    public long getLineNumber() {
        if (this.sourcePosition == null) {
            this.sourcePosition = new SourcePosition();
        }
        return this.sourcePosition.getLineNumber();
    }

    public long getColumnNumber() {
        if (this.sourcePosition == null) {
            this.sourcePosition = new SourcePosition();
        }
        return this.sourcePosition.getColumnNumber();
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getPrefix() {
        return getPrefix(this.type);
    }

    public String getPrefix(DiagnosticType diagnosticType) {
        return this.defaultFormatter.formatKind(this, Locale.getDefault());
    }

    public String toString() {
        return this.defaultFormatter.format(this, Locale.getDefault());
    }

    public Diagnostic.Kind getKind() {
        switch (this.type) {
            case NOTE:
                return Diagnostic.Kind.NOTE;
            case WARNING:
                return this.flags.contains(DiagnosticFlag.MANDATORY) ? Diagnostic.Kind.MANDATORY_WARNING : Diagnostic.Kind.WARNING;
            case ERROR:
                return Diagnostic.Kind.ERROR;
            default:
                return Diagnostic.Kind.OTHER;
        }
    }

    public String getCode() {
        return this.key;
    }

    public String getMessage(Locale locale) {
        return this.defaultFormatter.formatMessage(this, locale);
    }

    public void setFlag(DiagnosticFlag diagnosticFlag) {
        this.flags.add(diagnosticFlag);
        if (this.type == DiagnosticType.ERROR) {
            switch (diagnosticFlag) {
                case SYNTAX:
                    this.flags.remove(DiagnosticFlag.RECOVERABLE);
                    return;
                case RESOLVE_ERROR:
                    this.flags.add(DiagnosticFlag.RECOVERABLE);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean isFlagSet(DiagnosticFlag diagnosticFlag) {
        return this.flags.contains(diagnosticFlag);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/JCDiagnostic$MultilineDiagnostic.class */
    public static class MultilineDiagnostic extends JCDiagnostic {
        private final List<JCDiagnostic> subdiagnostics;

        @Override // com.sun.tools.javac.util.JCDiagnostic
        /* renamed from: getSource */
        public /* bridge */ /* synthetic */ Object mo615getSource() {
            return super.mo615getSource();
        }

        public MultilineDiagnostic(JCDiagnostic jCDiagnostic, List<JCDiagnostic> list) {
            super(jCDiagnostic.defaultFormatter, jCDiagnostic.getType(), jCDiagnostic.getLintCategory(), jCDiagnostic.flags, jCDiagnostic.getDiagnosticSource(), jCDiagnostic.position, jCDiagnostic.getCode(), jCDiagnostic.getArgs());
            this.subdiagnostics = list;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic
        public List<JCDiagnostic> getSubdiagnostics() {
            return this.subdiagnostics;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic
        public boolean isMultiline() {
            return true;
        }
    }
}
