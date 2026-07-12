package sun.tools.java;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import sun.tools.asm.Assembler;
import sun.tools.javac.SourceMember;
import sun.tools.tree.Context;
import sun.tools.tree.Expression;
import sun.tools.tree.Node;
import sun.tools.tree.Statement;
import sun.tools.tree.Vset;

/* loaded from: target.jar:sun/tools/java/MemberDefinition.class */
public class MemberDefinition implements Constants {
    protected long where;
    protected int modifiers;
    protected Type type;
    protected String documentation;
    protected IdentifierToken[] expIds;
    protected ClassDeclaration[] exp;
    protected Node value;
    protected ClassDefinition clazz;
    protected Identifier name;
    protected ClassDefinition innerClass;
    protected MemberDefinition nextMember;
    protected MemberDefinition nextMatch;
    protected MemberDefinition accessPeer;
    protected boolean superAccessMethod;
    private static Map proxyCache;
    static final int PUBLIC_ACCESS = 1;
    static final int PROTECTED_ACCESS = 2;
    static final int PACKAGE_ACCESS = 3;
    static final int PRIVATE_ACCESS = 4;

    public MemberDefinition(long j, ClassDefinition classDefinition, int i, Type type, Identifier identifier, IdentifierToken[] identifierTokenArr, Node node) {
        identifierTokenArr = identifierTokenArr == null ? new IdentifierToken[0] : identifierTokenArr;
        this.where = j;
        this.clazz = classDefinition;
        this.modifiers = i;
        this.type = type;
        this.name = identifier;
        this.expIds = identifierTokenArr;
        this.value = node;
    }

    public MemberDefinition(ClassDefinition classDefinition) {
        this(classDefinition.getWhere(), classDefinition.getOuterClass(), classDefinition.getModifiers(), classDefinition.getType(), classDefinition.getName().getFlatName().getName(), null, null);
        this.innerClass = classDefinition;
    }

    public static MemberDefinition makeProxyMember(MemberDefinition memberDefinition, ClassDefinition classDefinition, Environment environment) {
        if (proxyCache == null) {
            proxyCache = new HashMap();
        }
        String str = memberDefinition.toString() + "@" + classDefinition.toString();
        MemberDefinition memberDefinition2 = (MemberDefinition) proxyCache.get(str);
        if (memberDefinition2 != null) {
            return memberDefinition2;
        }
        MemberDefinition memberDefinition3 = new MemberDefinition(memberDefinition.getWhere(), classDefinition, memberDefinition.getModifiers(), memberDefinition.getType(), memberDefinition.getName(), memberDefinition.getExceptionIds(), null);
        memberDefinition3.exp = memberDefinition.getExceptions(environment);
        proxyCache.put(str, memberDefinition3);
        return memberDefinition3;
    }

    public final long getWhere() {
        return this.where;
    }

    public final ClassDeclaration getClassDeclaration() {
        return this.clazz.getClassDeclaration();
    }

    public void resolveTypeStructure(Environment environment) {
    }

    public ClassDeclaration getDefiningClassDeclaration() {
        return getClassDeclaration();
    }

    public final ClassDefinition getClassDefinition() {
        return this.clazz;
    }

    public final ClassDefinition getTopClass() {
        return this.clazz.getTopClass();
    }

    public final int getModifiers() {
        return this.modifiers;
    }

    public final void subModifiers(int i) {
        this.modifiers &= i ^ (-1);
    }

    public final void addModifiers(int i) {
        this.modifiers |= i;
    }

    public final Type getType() {
        return this.type;
    }

    public final Identifier getName() {
        return this.name;
    }

    public Vector getArguments() {
        if (isMethod()) {
            return new Vector();
        }
        return null;
    }

    public ClassDeclaration[] getExceptions(Environment environment) {
        if (this.expIds != null && this.exp == null) {
            if (this.expIds.length == 0) {
                this.exp = new ClassDeclaration[0];
            } else {
                throw new CompilerError("getExceptions " + this);
            }
        }
        return this.exp;
    }

    public final IdentifierToken[] getExceptionIds() {
        return this.expIds;
    }

    public ClassDefinition getInnerClass() {
        return this.innerClass;
    }

    public boolean isUplevelValue() {
        if (!isSynthetic() || !isVariable() || isStatic()) {
            return false;
        }
        String identifier = this.name.toString();
        return identifier.startsWith(Constants.prefixVal) || identifier.startsWith(Constants.prefixLoc) || identifier.startsWith(Constants.prefixThis);
    }

    public boolean isAccessMethod() {
        return isSynthetic() && isMethod() && this.accessPeer != null;
    }

    public MemberDefinition getAccessMethodTarget() {
        if (isAccessMethod()) {
            MemberDefinition memberDefinition = this.accessPeer;
            while (true) {
                MemberDefinition memberDefinition2 = memberDefinition;
                if (memberDefinition2 != null) {
                    if (memberDefinition2.isAccessMethod()) {
                        memberDefinition = memberDefinition2.accessPeer;
                    } else {
                        return memberDefinition2;
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public void setAccessMethodTarget(MemberDefinition memberDefinition) {
        if (getAccessMethodTarget() != memberDefinition) {
            if (this.accessPeer != null || memberDefinition.accessPeer != null) {
                throw new CompilerError("accessPeer");
            }
            this.accessPeer = memberDefinition;
        }
    }

    public MemberDefinition getAccessUpdateMember() {
        if (isAccessMethod()) {
            MemberDefinition memberDefinition = this.accessPeer;
            while (true) {
                MemberDefinition memberDefinition2 = memberDefinition;
                if (memberDefinition2 != null) {
                    if (!memberDefinition2.isAccessMethod()) {
                        memberDefinition = memberDefinition2.accessPeer;
                    } else {
                        return memberDefinition2;
                    }
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public void setAccessUpdateMember(MemberDefinition memberDefinition) {
        if (getAccessUpdateMember() != memberDefinition) {
            if (!isAccessMethod() || memberDefinition.getAccessMethodTarget() != getAccessMethodTarget()) {
                throw new CompilerError("accessPeer");
            }
            memberDefinition.accessPeer = this.accessPeer;
            this.accessPeer = memberDefinition;
        }
    }

    public final boolean isSuperAccessMethod() {
        return this.superAccessMethod;
    }

    public final void setIsSuperAccessMethod(boolean z) {
        this.superAccessMethod = z;
    }

    public final boolean isBlankFinal() {
        return isFinal() && !isSynthetic() && getValue() == null;
    }

    public boolean isNeverNull() {
        return isUplevelValue() && !this.name.toString().startsWith(Constants.prefixVal);
    }

    public Node getValue(Environment environment) throws ClassNotFound {
        return this.value;
    }

    public final Node getValue() {
        return this.value;
    }

    public final void setValue(Node node) {
        this.value = node;
    }

    public Object getInitialValue() {
        return null;
    }

    public final MemberDefinition getNextMember() {
        return this.nextMember;
    }

    public final MemberDefinition getNextMatch() {
        return this.nextMatch;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public void check(Environment environment) throws ClassNotFound {
    }

    public Vset check(Environment environment, Context context, Vset vset) throws ClassNotFound {
        return vset;
    }

    public void code(Environment environment, Assembler assembler) throws ClassNotFound {
        throw new CompilerError("code");
    }

    public void codeInit(Environment environment, Context context, Assembler assembler) throws ClassNotFound {
        throw new CompilerError("codeInit");
    }

    public boolean reportDeprecated(Environment environment) {
        return isDeprecated() || this.clazz.reportDeprecated(environment);
    }

    public final boolean canReach(Environment environment, MemberDefinition memberDefinition) {
        if (memberDefinition.isLocal() || !memberDefinition.isVariable()) {
            return true;
        }
        if ((!isVariable() && !isInitializer()) || !getClassDeclaration().equals(memberDefinition.getClassDeclaration()) || isStatic() != memberDefinition.isStatic()) {
            return true;
        }
        do {
            MemberDefinition nextMember = memberDefinition.getNextMember();
            memberDefinition = nextMember;
            if (nextMember == null) {
                break;
            }
        } while (memberDefinition != this);
        return memberDefinition != null;
    }

    private int getAccessLevel() {
        if (isPublic()) {
            return 1;
        }
        if (isProtected()) {
            return 2;
        }
        if (isPackagePrivate()) {
            return 3;
        }
        if (isPrivate()) {
            return 4;
        }
        throw new CompilerError("getAccessLevel()");
    }

    private void reportError(Environment environment, String str, ClassDeclaration classDeclaration, MemberDefinition memberDefinition) {
        if (classDeclaration == null) {
            environment.error(getWhere(), str, this, getClassDeclaration(), memberDefinition.getClassDeclaration());
        } else {
            environment.error(classDeclaration.getClassDefinition().getWhere(), str, this, getClassDeclaration(), memberDefinition.getClassDeclaration());
        }
    }

    public boolean sameReturnType(MemberDefinition memberDefinition) {
        if (isMethod() && memberDefinition.isMethod()) {
            return getType().getReturnType() == memberDefinition.getType().getReturnType();
        }
        throw new CompilerError("sameReturnType: not method");
    }

    public boolean checkOverride(Environment environment, MemberDefinition memberDefinition) {
        return checkOverride(environment, memberDefinition, null);
    }

    private boolean checkOverride(Environment environment, MemberDefinition memberDefinition, ClassDeclaration classDeclaration) {
        boolean z = true;
        if (!isMethod()) {
            throw new CompilerError("checkOverride(), expected method");
        }
        if (isSynthetic()) {
            if (memberDefinition.isFinal() || memberDefinition.isConstructor() || memberDefinition.isStatic() || isStatic()) {
            }
            return true;
        }
        if (getName() != memberDefinition.getName() || !getType().equalArguments(memberDefinition.getType())) {
            throw new CompilerError("checkOverride(), signature mismatch");
        }
        if (memberDefinition.isStatic() && !isStatic()) {
            reportError(environment, "override.static.with.instance", classDeclaration, memberDefinition);
            z = false;
        }
        if (!memberDefinition.isStatic() && isStatic()) {
            reportError(environment, "hide.instance.with.static", classDeclaration, memberDefinition);
            z = false;
        }
        if (memberDefinition.isFinal()) {
            reportError(environment, "override.final.method", classDeclaration, memberDefinition);
            z = false;
        }
        if (memberDefinition.reportDeprecated(environment) && !isDeprecated() && (this instanceof SourceMember)) {
            reportError(environment, "warn.override.is.deprecated", classDeclaration, memberDefinition);
        }
        if (getAccessLevel() > memberDefinition.getAccessLevel()) {
            reportError(environment, "override.more.restrictive", classDeclaration, memberDefinition);
            z = false;
        }
        if (!sameReturnType(memberDefinition)) {
        }
        if (!exceptionsFit(environment, memberDefinition)) {
            reportError(environment, "override.incompatible.exceptions", classDeclaration, memberDefinition);
            z = false;
        }
        return z;
    }

    public boolean checkMeet(Environment environment, MemberDefinition memberDefinition, ClassDeclaration classDeclaration) {
        if (!isMethod()) {
            throw new CompilerError("checkMeet(), expected method");
        }
        if (!isAbstract() && !memberDefinition.isAbstract()) {
            throw new CompilerError("checkMeet(), no abstract method");
        }
        if (!isAbstract()) {
            return checkOverride(environment, memberDefinition, classDeclaration);
        }
        if (!memberDefinition.isAbstract()) {
            return memberDefinition.checkOverride(environment, this, classDeclaration);
        }
        if (getName() != memberDefinition.getName() || !getType().equalArguments(memberDefinition.getType())) {
            throw new CompilerError("checkMeet(), signature mismatch");
        }
        if (!sameReturnType(memberDefinition)) {
            environment.error(classDeclaration.getClassDefinition().getWhere(), "meet.different.return", this, getClassDeclaration(), memberDefinition.getClassDeclaration());
            return false;
        }
        return true;
    }

    public boolean couldOverride(Environment environment, MemberDefinition memberDefinition) {
        if (!isMethod()) {
            throw new CompilerError("coulcOverride(), expected method");
        }
        if (!memberDefinition.isAbstract() || getAccessLevel() > memberDefinition.getAccessLevel() || !exceptionsFit(environment, memberDefinition)) {
            return false;
        }
        return true;
    }

    private boolean exceptionsFit(Environment environment, MemberDefinition memberDefinition) {
        ClassDeclaration[] exceptions = getExceptions(environment);
        ClassDeclaration[] exceptions2 = memberDefinition.getExceptions(environment);
        int i = 0;
        while (i < exceptions.length) {
            try {
                ClassDefinition classDefinition = exceptions[i].getClassDefinition(environment);
                int i2 = 0;
                while (true) {
                    if (i2 < exceptions2.length) {
                        if (classDefinition.subClassOf(environment, exceptions2[i2])) {
                            break;
                        }
                        i2++;
                    } else if (!classDefinition.subClassOf(environment, environment.getClassDeclaration(idJavaLangError)) && !classDefinition.subClassOf(environment, environment.getClassDeclaration(idJavaLangRuntimeException))) {
                        return false;
                    }
                }
            } catch (ClassNotFound e) {
                environment.error(getWhere(), "class.not.found", e.name, memberDefinition.getClassDeclaration());
            }
        }
        return true;
        i++;
    }

    public final boolean isPublic() {
        return (this.modifiers & 1) != 0;
    }

    public final boolean isPrivate() {
        return (this.modifiers & 2) != 0;
    }

    public final boolean isProtected() {
        return (this.modifiers & 4) != 0;
    }

    public final boolean isPackagePrivate() {
        return (this.modifiers & 7) == 0;
    }

    public final boolean isFinal() {
        return (this.modifiers & 16) != 0;
    }

    public final boolean isStatic() {
        return (this.modifiers & 8) != 0;
    }

    public final boolean isSynchronized() {
        return (this.modifiers & 32) != 0;
    }

    public final boolean isAbstract() {
        return (this.modifiers & 1024) != 0;
    }

    public final boolean isNative() {
        return (this.modifiers & 256) != 0;
    }

    public final boolean isVolatile() {
        return (this.modifiers & 64) != 0;
    }

    public final boolean isTransient() {
        return (this.modifiers & 128) != 0;
    }

    public final boolean isMethod() {
        return this.type.isType(12);
    }

    public final boolean isVariable() {
        return !this.type.isType(12) && this.innerClass == null;
    }

    public final boolean isSynthetic() {
        return (this.modifiers & 524288) != 0;
    }

    public final boolean isDeprecated() {
        return (this.modifiers & 262144) != 0;
    }

    public final boolean isStrict() {
        return (this.modifiers & 2097152) != 0;
    }

    public final boolean isInnerClass() {
        return this.innerClass != null;
    }

    public final boolean isInitializer() {
        return getName().equals(idClassInit);
    }

    public final boolean isConstructor() {
        return getName().equals(idInit);
    }

    public boolean isLocal() {
        return false;
    }

    public boolean isInlineable(Environment environment, boolean z) throws ClassNotFound {
        return ((!isStatic() && !isPrivate() && !isFinal() && !isConstructor() && !z) || isSynchronized() || isNative()) ? false : true;
    }

    public boolean isConstant() {
        if (isFinal() && isVariable() && this.value != null) {
            try {
                this.modifiers &= -17;
                return ((Expression) this.value).isConstant();
            } finally {
                this.modifiers |= 16;
            }
        }
        return false;
    }

    public String toString() {
        Identifier name = getClassDefinition().getName();
        if (isInitializer()) {
            return isStatic() ? "static {}" : "instance {}";
        }
        if (isConstructor()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append('(');
            Type[] argumentTypes = getType().getArgumentTypes();
            for (int i = 0; i < argumentTypes.length; i++) {
                if (i > 0) {
                    stringBuffer.append(',');
                }
                stringBuffer.append(argumentTypes[i].toString());
            }
            stringBuffer.append(')');
            return stringBuffer.toString();
        }
        if (isInnerClass()) {
            return getInnerClass().toString();
        }
        return this.type.typeString(getName().toString());
    }

    public void print(PrintStream printStream) {
        if (isPublic()) {
            printStream.print("public ");
        }
        if (isPrivate()) {
            printStream.print("private ");
        }
        if (isProtected()) {
            printStream.print("protected ");
        }
        if (isFinal()) {
            printStream.print("final ");
        }
        if (isStatic()) {
            printStream.print("static ");
        }
        if (isSynchronized()) {
            printStream.print("synchronized ");
        }
        if (isAbstract()) {
            printStream.print("abstract ");
        }
        if (isNative()) {
            printStream.print("native ");
        }
        if (isVolatile()) {
            printStream.print("volatile ");
        }
        if (isTransient()) {
            printStream.print("transient ");
        }
        printStream.println(toString() + RuntimeConstants.SIG_ENDCLASS);
    }

    public void cleanup(Environment environment) {
        this.documentation = null;
        if (!isMethod() || this.value == null) {
            if (isVariable()) {
                if (isPrivate() || !isFinal() || this.type.isType(9)) {
                    this.value = null;
                    return;
                }
                return;
            }
            return;
        }
        int i = 0;
        if (isPrivate() || isInitializer()) {
            this.value = Statement.empty;
        } else {
            int costInline = ((Statement) this.value).costInline(Statement.MAXINLINECOST, null, null);
            i = costInline;
            if (costInline >= Statement.MAXINLINECOST) {
                this.value = Statement.empty;
            } else {
                try {
                    if (!isInlineable(null, true)) {
                        this.value = Statement.empty;
                    }
                } catch (ClassNotFound e) {
                }
            }
        }
        if (this.value != Statement.empty && environment.dump()) {
            environment.output("[after cleanup of " + getName() + ", " + i + " expression cost units remain]");
        }
    }
}
