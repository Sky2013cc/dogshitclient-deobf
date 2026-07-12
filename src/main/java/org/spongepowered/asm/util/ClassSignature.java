package org.spongepowered.asm.util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.signature.SignatureReader;
import org.spongepowered.asm.lib.signature.SignatureVisitor;
import org.spongepowered.asm.lib.signature.SignatureWriter;
import org.spongepowered.asm.lib.tree.ClassNode;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature.class */
public class ClassSignature {
    protected static final String OBJECT = "java/lang/Object";
    private final Map<TypeVar, TokenHandle> types = new LinkedHashMap();
    private Token superClass = new Token(OBJECT);
    private final List<Token> interfaces = new ArrayList();
    private final Deque<String> rawInterfaces = new LinkedList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$IToken.class */
    public interface IToken {
        public static final String WILDCARDS = "+-";

        String asType();

        String asBound();

        Token asToken();

        IToken setArray(boolean z);

        IToken setWildcard(char c);
    }

    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$Lazy.class */
    static class Lazy extends ClassSignature {
        private final String sig;
        private ClassSignature generated;

        Lazy(String sig) {
            this.sig = sig;
        }

        @Override // org.spongepowered.asm.util.ClassSignature
        public ClassSignature wake() {
            if (this.generated == null) {
                this.generated = ClassSignature.of(this.sig);
            }
            return this.generated;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$TypeVar.class */
    public static class TypeVar implements Comparable<TypeVar> {
        private final String originalName;
        private String currentName;

        TypeVar(String name) {
            this.originalName = name;
            this.currentName = name;
        }

        @Override // java.lang.Comparable
        public int compareTo(TypeVar other) {
            return this.currentName.compareTo(other.currentName);
        }

        public String toString() {
            return this.currentName;
        }

        String getOriginalName() {
            return this.originalName;
        }

        void rename(String name) {
            this.currentName = name;
        }

        public boolean matches(String originalName) {
            return this.originalName.equals(originalName);
        }

        public boolean equals(Object obj) {
            return this.currentName.equals(obj);
        }

        public int hashCode() {
            return this.currentName.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$Token.class */
    public static class Token implements IToken {
        static final String SYMBOLS = "+-*";
        private final boolean inner;
        private boolean array;
        private char symbol;
        private String type;
        private List<Token> classBound;
        private List<Token> ifaceBound;
        private List<IToken> signature;
        private List<IToken> suffix;
        private Token tail;

        Token() {
            this(false);
        }

        Token(String type) {
            this(type, false);
        }

        Token(char symbol) {
            this();
            this.symbol = symbol;
        }

        Token(boolean inner) {
            this(null, inner);
        }

        Token(String type, boolean inner) {
            this.symbol = (char) 0;
            this.inner = inner;
            this.type = type;
        }

        Token setSymbol(char symbol) {
            if (this.symbol == 0 && SYMBOLS.indexOf(symbol) > -1) {
                this.symbol = symbol;
            }
            return this;
        }

        Token setType(String type) {
            if (this.type == null) {
                this.type = type;
            }
            return this;
        }

        boolean hasClassBound() {
            return this.classBound != null;
        }

        boolean hasInterfaceBound() {
            return this.ifaceBound != null;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public IToken setArray(boolean array) {
            this.array |= array;
            return this;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public IToken setWildcard(char wildcard) {
            if (IToken.WILDCARDS.indexOf(wildcard) == -1) {
                return this;
            }
            return setSymbol(wildcard);
        }

        private List<Token> getClassBound() {
            if (this.classBound == null) {
                this.classBound = new ArrayList();
            }
            return this.classBound;
        }

        private List<Token> getIfaceBound() {
            if (this.ifaceBound == null) {
                this.ifaceBound = new ArrayList();
            }
            return this.ifaceBound;
        }

        private List<IToken> getSignature() {
            if (this.signature == null) {
                this.signature = new ArrayList();
            }
            return this.signature;
        }

        private List<IToken> getSuffix() {
            if (this.suffix == null) {
                this.suffix = new ArrayList();
            }
            return this.suffix;
        }

        IToken addTypeArgument(char symbol) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(symbol);
            }
            Token token = new Token(symbol);
            getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(String name) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(name);
            }
            Token token = new Token(name);
            getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(Token token) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(token);
            }
            getSignature().add(token);
            return token;
        }

        IToken addTypeArgument(TokenHandle token) {
            if (this.tail != null) {
                return this.tail.addTypeArgument(token);
            }
            TokenHandle handle = token.m4289clone();
            getSignature().add(handle);
            return handle;
        }

        Token addBound(String bound, boolean classBound) {
            if (classBound) {
                return addClassBound(bound);
            }
            return addInterfaceBound(bound);
        }

        Token addClassBound(String bound) {
            Token token = new Token(bound);
            getClassBound().add(token);
            return token;
        }

        Token addInterfaceBound(String bound) {
            Token token = new Token(bound);
            getIfaceBound().add(token);
            return token;
        }

        Token addInnerClass(String name) {
            this.tail = new Token(name, true);
            getSuffix().add(this.tail);
            return this.tail;
        }

        public String toString() {
            return asType();
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public String asBound() {
            StringBuilder sb = new StringBuilder();
            if (this.type != null) {
                sb.append(this.type);
            }
            if (this.classBound != null) {
                for (Token token : this.classBound) {
                    sb.append(token.asType());
                }
            }
            if (this.ifaceBound != null) {
                for (Token token2 : this.ifaceBound) {
                    sb.append(':').append(token2.asType());
                }
            }
            return sb.toString();
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public String asType() {
            return asType(false);
        }

        public String asType(boolean raw) {
            StringBuilder sb = new StringBuilder();
            if (this.array) {
                sb.append('[');
            }
            if (this.symbol != 0) {
                sb.append(this.symbol);
            }
            if (this.type == null) {
                return sb.toString();
            }
            if (!this.inner) {
                sb.append('L');
            }
            sb.append(this.type);
            if (!raw) {
                if (this.signature != null) {
                    sb.append('<');
                    for (IToken token : this.signature) {
                        sb.append(token.asType());
                    }
                    sb.append('>');
                }
                if (this.suffix != null) {
                    for (IToken token2 : this.suffix) {
                        sb.append('.').append(token2.asType());
                    }
                }
            }
            if (!this.inner) {
                sb.append(';');
            }
            return sb.toString();
        }

        boolean isRaw() {
            return this.signature == null;
        }

        String getClassType() {
            return this.type != null ? this.type : ClassSignature.OBJECT;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public Token asToken() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$TokenHandle.class */
    public class TokenHandle implements IToken {
        final Token token;
        boolean array;
        char wildcard;

        TokenHandle(ClassSignature this$0) {
            this(new Token());
        }

        TokenHandle(Token token) {
            this.token = token;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public IToken setArray(boolean array) {
            this.array |= array;
            return this;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public IToken setWildcard(char wildcard) {
            if (IToken.WILDCARDS.indexOf(wildcard) > -1) {
                this.wildcard = wildcard;
            }
            return this;
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public String asBound() {
            return this.token.asBound();
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public String asType() {
            StringBuilder sb = new StringBuilder();
            if (this.wildcard > 0) {
                sb.append(this.wildcard);
            }
            if (this.array) {
                sb.append('[');
            }
            return sb.append(ClassSignature.this.getTypeVar(this)).toString();
        }

        @Override // org.spongepowered.asm.util.ClassSignature.IToken
        public Token asToken() {
            return this.token;
        }

        public String toString() {
            return this.token.toString();
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public TokenHandle m4289clone() {
            return new TokenHandle(this.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser.class */
    public class SignatureParser extends SignatureVisitor {
        private FormalParamElement param;

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$SignatureElement.class */
        abstract class SignatureElement extends SignatureVisitor {
            public SignatureElement() {
                super(Opcodes.ASM5);
            }
        }

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$TokenElement.class */
        abstract class TokenElement extends SignatureElement {
            protected Token token;
            private boolean array;

            TokenElement() {
                super();
            }

            public Token getToken() {
                if (this.token == null) {
                    this.token = new Token();
                }
                return this.token;
            }

            protected void setArray() {
                this.array = true;
            }

            private boolean getArray() {
                boolean array = this.array;
                this.array = false;
                return array;
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitClassType(String name) {
                getToken().setType(name);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitClassBound() {
                getToken();
                return new BoundElement(this, true);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitInterfaceBound() {
                getToken();
                return new BoundElement(this, false);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitInnerClassType(String name) {
                this.token.addInnerClass(name);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitArrayType() {
                setArray();
                return this;
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitTypeArgument(char wildcard) {
                return new TypeArgElement(this, wildcard);
            }

            Token addTypeArgument() {
                return this.token.addTypeArgument('*').asToken();
            }

            IToken addTypeArgument(char symbol) {
                return this.token.addTypeArgument(symbol).setArray(getArray());
            }

            IToken addTypeArgument(String name) {
                return this.token.addTypeArgument(name).setArray(getArray());
            }

            IToken addTypeArgument(Token token) {
                return this.token.addTypeArgument(token).setArray(getArray());
            }

            IToken addTypeArgument(TokenHandle token) {
                return this.token.addTypeArgument(token).setArray(getArray());
            }
        }

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$FormalParamElement.class */
        class FormalParamElement extends TokenElement {
            private final TokenHandle handle;

            FormalParamElement(String param) {
                super();
                this.handle = ClassSignature.this.getType(param);
                this.token = this.handle.asToken();
            }
        }

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$TypeArgElement.class */
        class TypeArgElement extends TokenElement {
            private final TokenElement type;
            private final char wildcard;

            TypeArgElement(TokenElement type, char wildcard) {
                super();
                this.type = type;
                this.wildcard = wildcard;
            }

            @Override // org.spongepowered.asm.util.ClassSignature.SignatureParser.TokenElement, org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitArrayType() {
                this.type.setArray();
                return this;
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitBaseType(char descriptor) {
                this.token = this.type.addTypeArgument(descriptor).asToken();
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitTypeVariable(String name) {
                TokenHandle token = ClassSignature.this.getType(name);
                this.token = this.type.addTypeArgument(token).setWildcard(this.wildcard).asToken();
            }

            @Override // org.spongepowered.asm.util.ClassSignature.SignatureParser.TokenElement, org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitClassType(String name) {
                this.token = this.type.addTypeArgument(name).setWildcard(this.wildcard).asToken();
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitTypeArgument() {
                this.token.addTypeArgument('*');
            }

            @Override // org.spongepowered.asm.util.ClassSignature.SignatureParser.TokenElement, org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitTypeArgument(char wildcard) {
                return new TypeArgElement(this, wildcard);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitEnd() {
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$BoundElement.class */
        public class BoundElement extends TokenElement {
            private final TokenElement type;
            private final boolean classBound;

            BoundElement(TokenElement type, boolean classBound) {
                super();
                this.type = type;
                this.classBound = classBound;
            }

            @Override // org.spongepowered.asm.util.ClassSignature.SignatureParser.TokenElement, org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitClassType(String name) {
                this.token = this.type.token.addBound(name, this.classBound);
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitTypeArgument() {
                this.token.addTypeArgument('*');
            }

            @Override // org.spongepowered.asm.util.ClassSignature.SignatureParser.TokenElement, org.spongepowered.asm.lib.signature.SignatureVisitor
            public SignatureVisitor visitTypeArgument(char wildcard) {
                return new TypeArgElement(this, wildcard);
            }
        }

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$SuperClassElement.class */
        class SuperClassElement extends TokenElement {
            SuperClassElement() {
                super();
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitEnd() {
                ClassSignature.this.setSuperClass(this.token);
            }
        }

        /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureParser$InterfaceElement.class */
        class InterfaceElement extends TokenElement {
            InterfaceElement() {
                super();
            }

            @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
            public void visitEnd() {
                ClassSignature.this.addInterface(this.token);
            }
        }

        SignatureParser() {
            super(Opcodes.ASM5);
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
        public void visitFormalTypeParameter(String name) {
            this.param = new FormalParamElement(name);
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
        public SignatureVisitor visitClassBound() {
            return this.param.visitClassBound();
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
        public SignatureVisitor visitInterfaceBound() {
            return this.param.visitInterfaceBound();
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
        public SignatureVisitor visitSuperclass() {
            return new SuperClassElement();
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureVisitor
        public SignatureVisitor visitInterface() {
            return new InterfaceElement();
        }
    }

    /* loaded from: target.jar:org/spongepowered/asm/util/ClassSignature$SignatureRemapper.class */
    class SignatureRemapper extends SignatureWriter {
        private final Set<String> localTypeVars = new HashSet();

        SignatureRemapper() {
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureWriter, org.spongepowered.asm.lib.signature.SignatureVisitor
        public void visitFormalTypeParameter(String name) {
            this.localTypeVars.add(name);
            super.visitFormalTypeParameter(name);
        }

        @Override // org.spongepowered.asm.lib.signature.SignatureWriter, org.spongepowered.asm.lib.signature.SignatureVisitor
        public void visitTypeVariable(String name) {
            TypeVar typeVar;
            if (!this.localTypeVars.contains(name) && (typeVar = ClassSignature.this.getTypeVar(name)) != null) {
                super.visitTypeVariable(typeVar.toString());
            } else {
                super.visitTypeVariable(name);
            }
        }
    }

    ClassSignature() {
    }

    private ClassSignature read(String signature) {
        if (signature != null) {
            try {
                new SignatureReader(signature).accept(new SignatureParser());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    protected TypeVar getTypeVar(String varName) {
        for (TypeVar typeVar : this.types.keySet()) {
            if (typeVar.matches(varName)) {
                return typeVar;
            }
        }
        return null;
    }

    protected TokenHandle getType(String varName) {
        for (TypeVar typeVar : this.types.keySet()) {
            if (typeVar.matches(varName)) {
                return this.types.get(typeVar);
            }
        }
        TokenHandle handle = new TokenHandle(this);
        this.types.put(new TypeVar(varName), handle);
        return handle;
    }

    protected String getTypeVar(TokenHandle handle) {
        for (Map.Entry<TypeVar, TokenHandle> type : this.types.entrySet()) {
            TypeVar typeVar = type.getKey();
            TokenHandle typeHandle = type.getValue();
            if (handle == typeHandle || handle.asToken() == typeHandle.asToken()) {
                return PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE + typeVar + RuntimeConstants.SIG_ENDCLASS;
            }
        }
        return handle.token.asType();
    }

    protected void addTypeVar(TypeVar typeVar, TokenHandle handle) throws IllegalArgumentException {
        if (this.types.containsKey(typeVar)) {
            throw new IllegalArgumentException("TypeVar " + typeVar + " is already present on " + this);
        }
        this.types.put(typeVar, handle);
    }

    protected void setSuperClass(Token superClass) {
        this.superClass = superClass;
    }

    public String getSuperClass() {
        return this.superClass.asType(true);
    }

    protected void addInterface(Token iface) {
        if (!iface.isRaw()) {
            String raw = iface.asType(true);
            ListIterator<Token> iter = this.interfaces.listIterator();
            while (iter.hasNext()) {
                Token intrface = iter.next();
                if (intrface.isRaw() && intrface.asType(true).equals(raw)) {
                    iter.set(iface);
                    return;
                }
            }
        }
        this.interfaces.add(iface);
    }

    public void addInterface(String iface) {
        this.rawInterfaces.add(iface);
    }

    protected void addRawInterface(String iface) {
        Token token = new Token(iface);
        String raw = token.asType(true);
        for (Token intrface : this.interfaces) {
            if (intrface.asType(true).equals(raw)) {
                return;
            }
        }
        this.interfaces.add(token);
    }

    public void merge(ClassSignature other) {
        try {
            Set<String> typeVars = new HashSet<>();
            for (TypeVar typeVar : this.types.keySet()) {
                typeVars.add(typeVar.toString());
            }
            other.conform(typeVars);
            for (Map.Entry<TypeVar, TokenHandle> type : other.types.entrySet()) {
                addTypeVar(type.getKey(), type.getValue());
            }
            for (Token iface : other.interfaces) {
                addInterface(iface);
            }
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    private void conform(Set<String> typeVars) {
        for (TypeVar typeVar : this.types.keySet()) {
            String name = findUniqueName(typeVar.getOriginalName(), typeVars);
            typeVar.rename(name);
            typeVars.add(name);
        }
    }

    private String findUniqueName(String typeVar, Set<String> typeVars) {
        String name;
        if (!typeVars.contains(typeVar)) {
            return typeVar;
        }
        if (typeVar.length() == 1 && (name = findOffsetName(typeVar.charAt(0), typeVars)) != null) {
            return name;
        }
        String name2 = findOffsetName('T', typeVars, "", typeVar);
        if (name2 != null) {
            return name2;
        }
        String name3 = findOffsetName('T', typeVars, typeVar, "");
        if (name3 != null) {
            return name3;
        }
        String name4 = findOffsetName('T', typeVars, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, typeVar);
        if (name4 != null) {
            return name4;
        }
        String name5 = findOffsetName('T', typeVars, "", typeVar + "Type");
        if (name5 != null) {
            return name5;
        }
        throw new IllegalStateException("Failed to conform type var: " + typeVar);
    }

    private String findOffsetName(char c, Set<String> typeVars) {
        return findOffsetName(c, typeVars, "", "");
    }

    private String findOffsetName(char c, Set<String> typeVars, String prefix, String suffix) {
        String name = String.format("%s%s%s", prefix, Character.valueOf(c), suffix);
        if (!typeVars.contains(name)) {
            return name;
        }
        if (c > '@' && c < '[') {
            int i = c - '@';
            while (true) {
                int s = i;
                if (s + 65 != c) {
                    String name2 = String.format("%s%s%s", prefix, Character.valueOf((char) (s + 65)), suffix);
                    if (typeVars.contains(name2)) {
                        i = (s + 1) % 26;
                    } else {
                        return name2;
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public SignatureVisitor getRemapper() {
        return new SignatureRemapper();
    }

    public String toString() {
        while (this.rawInterfaces.size() > 0) {
            addRawInterface(this.rawInterfaces.remove());
        }
        StringBuilder sb = new StringBuilder();
        if (this.types.size() > 0) {
            boolean valid = false;
            StringBuilder types = new StringBuilder();
            for (Map.Entry<TypeVar, TokenHandle> type : this.types.entrySet()) {
                String bound = type.getValue().asBound();
                if (!bound.isEmpty()) {
                    types.append(type.getKey()).append(':').append(bound);
                    valid = true;
                }
            }
            if (valid) {
                sb.append('<').append((CharSequence) types).append('>');
            }
        }
        sb.append(this.superClass.asType());
        for (Token iface : this.interfaces) {
            sb.append(iface.asType());
        }
        return sb.toString();
    }

    public ClassSignature wake() {
        return this;
    }

    public static ClassSignature of(String signature) {
        return new ClassSignature().read(signature);
    }

    public static ClassSignature of(ClassNode classNode) {
        if (classNode.signature != null) {
            return of(classNode.signature);
        }
        return generate(classNode);
    }

    public static ClassSignature ofLazy(ClassNode classNode) {
        if (classNode.signature != null) {
            return new Lazy(classNode.signature);
        }
        return generate(classNode);
    }

    private static ClassSignature generate(ClassNode classNode) {
        ClassSignature generated = new ClassSignature();
        generated.setSuperClass(new Token(classNode.superName != null ? classNode.superName : OBJECT));
        for (String iface : classNode.interfaces) {
            generated.addInterface(new Token(iface));
        }
        return generated;
    }
}
