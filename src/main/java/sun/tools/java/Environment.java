package sun.tools.java;

import java.io.File;
import java.io.IOException;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StackType;

/* loaded from: target.jar:sun/tools/java/Environment.class */
public class Environment implements Constants {
    Environment env;
    String encoding;
    Object source;
    private static boolean debugging;
    private static boolean dependtrace;
    private static boolean dumpmodifiers;

    public Environment(Environment environment, Object obj) {
        if (environment != null && environment.env != null && environment.getClass() == getClass()) {
            environment = environment.env;
        }
        this.env = environment;
        this.source = obj;
    }

    public Environment() {
        this(null, null);
    }

    public boolean isExemptPackage(Identifier identifier) {
        return this.env.isExemptPackage(identifier);
    }

    public ClassDeclaration getClassDeclaration(Identifier identifier) {
        return this.env.getClassDeclaration(identifier);
    }

    public final ClassDefinition getClassDefinition(Identifier identifier) throws ClassNotFound {
        if (identifier.isInner()) {
            ClassDefinition classDefinition = getClassDefinition(identifier.getTopName());
            Identifier flatName = identifier.getFlatName();
            loop0: while (flatName.isQualified()) {
                flatName = flatName.getTail();
                Identifier head = flatName.getHead();
                String identifier2 = head.toString();
                if (identifier2.length() > 0 && Character.isDigit(identifier2.charAt(0))) {
                    ClassDefinition localClass = classDefinition.getLocalClass(identifier2);
                    if (localClass != null) {
                        classDefinition = localClass;
                    } else {
                        throw new ClassNotFound(Identifier.lookupInner(classDefinition.getName(), head));
                    }
                } else {
                    MemberDefinition firstMatch = classDefinition.getFirstMatch(head);
                    while (true) {
                        MemberDefinition memberDefinition = firstMatch;
                        if (memberDefinition == null) {
                            break loop0;
                        }
                        if (!memberDefinition.isInnerClass()) {
                            firstMatch = memberDefinition.getNextMatch();
                        } else {
                            classDefinition = memberDefinition.getInnerClass();
                            break;
                        }
                    }
                }
            }
            return classDefinition;
        }
        return getClassDeclaration(identifier).getClassDefinition(this);
    }

    public ClassDeclaration getClassDeclaration(Type type) {
        return getClassDeclaration(type.getClassName());
    }

    public final ClassDefinition getClassDefinition(Type type) throws ClassNotFound {
        return getClassDefinition(type.getClassName());
    }

    public boolean classExists(Identifier identifier) {
        return this.env.classExists(identifier);
    }

    public final boolean classExists(Type type) {
        return !type.isType(10) || classExists(type.getClassName());
    }

    public Package getPackage(Identifier identifier) throws IOException {
        return this.env.getPackage(identifier);
    }

    public void loadDefinition(ClassDeclaration classDeclaration) {
        this.env.loadDefinition(classDeclaration);
    }

    public final Object getSource() {
        return this.source;
    }

    public boolean resolve(long j, ClassDefinition classDefinition, Type type) {
        switch (type.getTypeCode()) {
            case 9:
                return resolve(j, classDefinition, type.getElementType());
            case 10:
                try {
                    Identifier className = type.getClassName();
                    if (!className.isQualified() && !className.isInner() && !classExists(className)) {
                        resolve(className);
                    }
                    ClassDefinition qualifiedClassDefinition = getQualifiedClassDefinition(j, className, classDefinition, false);
                    if (!classDefinition.canAccess(this, qualifiedClassDefinition.getClassDeclaration())) {
                        error(j, "cant.access.class", qualifiedClassDefinition);
                        return true;
                    }
                    qualifiedClassDefinition.noteUsedBy(classDefinition, j, this.env);
                    return true;
                } catch (AmbiguousClass e) {
                    error(j, "ambig.class", e.name1, e.name2);
                    return false;
                } catch (ClassNotFound e2) {
                    try {
                        if (e2.name.isInner() && getPackage(e2.name.getTopName()).exists()) {
                            this.env.error(j, "class.and.package", e2.name.getTopName());
                        }
                    } catch (IOException e3) {
                        this.env.error(j, "io.exception", "package check");
                    }
                    error(j, "class.not.found.no.context", e2.name);
                    return false;
                }
            case 11:
            default:
                return true;
            case 12:
                boolean resolve = resolve(j, classDefinition, type.getReturnType());
                Type[] argumentTypes = type.getArgumentTypes();
                int length = argumentTypes.length;
                while (true) {
                    int i = length;
                    length--;
                    if (i > 0) {
                        resolve &= resolve(j, classDefinition, argumentTypes[length]);
                    } else {
                        return resolve;
                    }
                }
        }
    }

    public boolean resolveByName(long j, ClassDefinition classDefinition, Identifier identifier) {
        return resolveByName(j, classDefinition, identifier, false);
    }

    public boolean resolveExtendsByName(long j, ClassDefinition classDefinition, Identifier identifier) {
        return resolveByName(j, classDefinition, identifier, true);
    }

    private boolean resolveByName(long j, ClassDefinition classDefinition, Identifier identifier, boolean z) {
        try {
            if (!identifier.isQualified() && !identifier.isInner() && !classExists(identifier)) {
                resolve(identifier);
            }
            ClassDefinition qualifiedClassDefinition = getQualifiedClassDefinition(j, identifier, classDefinition, z);
            ClassDeclaration classDeclaration = qualifiedClassDefinition.getClassDeclaration();
            if (z || !classDefinition.canAccess(this, classDeclaration)) {
                if (!z || !classDefinition.extendsCanAccess(this, classDeclaration)) {
                    error(j, "cant.access.class", qualifiedClassDefinition);
                    return true;
                }
                return true;
            }
            return true;
        } catch (AmbiguousClass e) {
            error(j, "ambig.class", e.name1, e.name2);
            return false;
        } catch (ClassNotFound e2) {
            try {
                if (e2.name.isInner() && getPackage(e2.name.getTopName()).exists()) {
                    this.env.error(j, "class.and.package", e2.name.getTopName());
                }
            } catch (IOException e3) {
                this.env.error(j, "io.exception", "package check");
            }
            error(j, "class.not.found", e2.name, "type name");
            return false;
        }
    }

    public final ClassDefinition getQualifiedClassDefinition(long j, Identifier identifier, ClassDefinition classDefinition, boolean z) throws ClassNotFound {
        if (identifier.isInner()) {
            ClassDefinition classDefinition2 = getClassDefinition(identifier.getTopName());
            Identifier flatName = identifier.getFlatName();
            loop0: while (flatName.isQualified()) {
                flatName = flatName.getTail();
                Identifier head = flatName.getHead();
                String identifier2 = head.toString();
                if (identifier2.length() > 0 && Character.isDigit(identifier2.charAt(0))) {
                    ClassDefinition localClass = classDefinition2.getLocalClass(identifier2);
                    if (localClass != null) {
                        classDefinition2 = localClass;
                    } else {
                        throw new ClassNotFound(Identifier.lookupInner(classDefinition2.getName(), head));
                    }
                } else {
                    MemberDefinition firstMatch = classDefinition2.getFirstMatch(head);
                    while (true) {
                        MemberDefinition memberDefinition = firstMatch;
                        if (memberDefinition == null) {
                            break loop0;
                        }
                        if (!memberDefinition.isInnerClass()) {
                            firstMatch = memberDefinition.getNextMatch();
                        } else {
                            ClassDeclaration classDeclaration = classDefinition2.getClassDeclaration();
                            classDefinition2 = memberDefinition.getInnerClass();
                            ClassDeclaration classDeclaration2 = classDefinition2.getClassDeclaration();
                            if ((!z && !classDefinition.canAccess(this.env, classDeclaration2)) || (z && !classDefinition.extendsCanAccess(this.env, classDeclaration2))) {
                                this.env.error(j, "no.type.access", head, classDeclaration, classDefinition);
                            }
                        }
                    }
                    throw new ClassNotFound(Identifier.lookupInner(classDefinition2.getName(), head));
                }
            }
            return classDefinition2;
        }
        return getClassDeclaration(identifier).getClassDefinition(this);
    }

    public Type resolveNames(ClassDefinition classDefinition, Type type, boolean z) {
        Identifier resolveName;
        dtEvent("Environment.resolveNames: " + classDefinition + ", " + type);
        switch (type.getTypeCode()) {
            case 9:
                type = Type.tArray(resolveNames(classDefinition, type.getElementType(), z));
                break;
            case 10:
                Identifier className = type.getClassName();
                if (z) {
                    resolveName = resolvePackageQualifiedName(className);
                } else {
                    resolveName = classDefinition.resolveName(this, className);
                }
                if (className != resolveName) {
                    type = Type.tClass(resolveName);
                    break;
                }
                break;
            case 12:
                Type returnType = type.getReturnType();
                Type resolveNames = resolveNames(classDefinition, returnType, z);
                Type[] argumentTypes = type.getArgumentTypes();
                Type[] typeArr = new Type[argumentTypes.length];
                boolean z2 = returnType != resolveNames;
                int length = argumentTypes.length;
                while (true) {
                    int i = length;
                    length--;
                    if (i > 0) {
                        Type type2 = argumentTypes[length];
                        Type resolveNames2 = resolveNames(classDefinition, type2, z);
                        typeArr[length] = resolveNames2;
                        if (type2 != resolveNames2) {
                            z2 = true;
                        }
                    } else if (z2) {
                        type = Type.tMethod(resolveNames, typeArr);
                        break;
                    }
                }
                break;
        }
        return type;
    }

    public Identifier resolveName(Identifier identifier) {
        if (identifier.isQualified()) {
            Identifier resolveName = resolveName(identifier.getHead());
            if (resolveName.hasAmbigPrefix()) {
                return resolveName;
            }
            if (!classExists(resolveName)) {
                return resolvePackageQualifiedName(identifier);
            }
            try {
                return getClassDefinition(resolveName).resolveInnerClass(this, identifier.getTail());
            } catch (ClassNotFound e) {
                return Identifier.lookupInner(resolveName, identifier.getTail());
            }
        }
        try {
            return resolve(identifier);
        } catch (AmbiguousClass e2) {
            if (identifier.hasAmbigPrefix()) {
                return identifier;
            }
            return identifier.addAmbigPrefix();
        } catch (ClassNotFound e3) {
            Imports imports = getImports();
            if (imports != null) {
                return imports.forceResolve(this, identifier);
            }
            return identifier;
        }
    }

    public final Identifier resolvePackageQualifiedName(Identifier identifier) {
        Identifier identifier2 = null;
        while (true) {
            if (classExists(identifier)) {
                break;
            }
            if (!identifier.isQualified()) {
                identifier = identifier2 == null ? identifier : Identifier.lookup(identifier, identifier2);
                identifier2 = null;
            } else {
                Identifier name = identifier.getName();
                identifier2 = identifier2 == null ? name : Identifier.lookup(name, identifier2);
                identifier = identifier.getQualifier();
            }
        }
        if (identifier2 != null) {
            identifier = Identifier.lookupInner(identifier, identifier2);
        }
        return identifier;
    }

    public Identifier resolve(Identifier identifier) throws ClassNotFound {
        return this.env == null ? identifier : this.env.resolve(identifier);
    }

    public Imports getImports() {
        if (this.env == null) {
            return null;
        }
        return this.env.getImports();
    }

    public ClassDefinition makeClassDefinition(Environment environment, long j, IdentifierToken identifierToken, String str, int i, IdentifierToken identifierToken2, IdentifierToken[] identifierTokenArr, ClassDefinition classDefinition) {
        if (this.env == null) {
            return null;
        }
        return this.env.makeClassDefinition(environment, j, identifierToken, str, i, identifierToken2, identifierTokenArr, classDefinition);
    }

    public MemberDefinition makeMemberDefinition(Environment environment, long j, ClassDefinition classDefinition, String str, int i, Type type, Identifier identifier, IdentifierToken[] identifierTokenArr, IdentifierToken[] identifierTokenArr2, Object obj) {
        if (this.env == null) {
            return null;
        }
        return this.env.makeMemberDefinition(environment, j, classDefinition, str, i, type, identifier, identifierTokenArr, identifierTokenArr2, obj);
    }

    public boolean isApplicable(MemberDefinition memberDefinition, Type[] typeArr) throws ClassNotFound {
        Type type = memberDefinition.getType();
        if (!type.isType(12)) {
            return false;
        }
        Type[] argumentTypes = type.getArgumentTypes();
        if (typeArr.length != argumentTypes.length) {
            return false;
        }
        int length = typeArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (isMoreSpecific(typeArr[length], argumentTypes[length]));
        return false;
    }

    public boolean isMoreSpecific(MemberDefinition memberDefinition, MemberDefinition memberDefinition2) throws ClassNotFound {
        return isMoreSpecific(memberDefinition.getClassDeclaration().getType(), memberDefinition2.getClassDeclaration().getType()) && isApplicable(memberDefinition2, memberDefinition.getType().getArgumentTypes());
    }

    public boolean isMoreSpecific(Type type, Type type2) throws ClassNotFound {
        return implicitCast(type, type2);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0010. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00dd A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e1 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean implicitCast(Type type, Type type2) throws ClassNotFound {
        if (type == type2) {
            return true;
        }
        int typeCode = type2.getTypeCode();
        switch (type.getTypeCode()) {
            case 1:
                if (typeCode == 3) {
                    return true;
                }
            case 2:
            case 3:
                if (typeCode == 4) {
                    return true;
                }
            case 4:
                if (typeCode == 5) {
                    return true;
                }
            case 5:
                if (typeCode == 6) {
                    return true;
                }
            case 6:
                return typeCode == 7;
            case 7:
            default:
                return false;
            case 8:
                return type2.inMask(1792);
            case 9:
                if (!type2.isType(9)) {
                    return type2 == Type.tObject || type2 == Type.tCloneable || type2 == Type.tSerializable;
                }
                do {
                    type = type.getElementType();
                    type2 = type2.getElementType();
                    if (type.isType(9)) {
                    }
                    if (type.inMask(StackType.STOP_BT) || !type2.inMask(StackType.STOP_BT)) {
                        return type.getTypeCode() != type2.getTypeCode();
                    }
                    return isMoreSpecific(type, type2);
                } while (type2.isType(9));
                if (type.inMask(StackType.STOP_BT)) {
                }
                if (type.getTypeCode() != type2.getTypeCode()) {
                }
                break;
            case 10:
                if (typeCode == 10) {
                    return getClassDefinition(type2).implementedBy(this, getClassDefinition(type).getClassDeclaration());
                }
                return false;
        }
    }

    public boolean explicitCast(Type type, Type type2) throws ClassNotFound {
        Type type3;
        if (implicitCast(type, type2)) {
            return true;
        }
        if (type.inMask(254)) {
            return type2.inMask(254);
        }
        if (type.isType(10) && type2.isType(10)) {
            ClassDefinition classDefinition = getClassDefinition(type);
            ClassDefinition classDefinition2 = getClassDefinition(type2);
            if (classDefinition2.isFinal()) {
                return classDefinition.implementedBy(this, classDefinition2.getClassDeclaration());
            }
            if (classDefinition.isFinal()) {
                return classDefinition2.implementedBy(this, classDefinition.getClassDeclaration());
            }
            if (classDefinition2.isInterface() && classDefinition.isInterface()) {
                return classDefinition2.couldImplement(classDefinition);
            }
            return classDefinition2.isInterface() || classDefinition.isInterface() || classDefinition.superClassOf(this, classDefinition2.getClassDeclaration());
        }
        if (type2.isType(9)) {
            if (!type.isType(9)) {
                if (type == Type.tObject || type == Type.tCloneable || type == Type.tSerializable) {
                    return true;
                }
                return false;
            }
            Type elementType = type.getElementType();
            Type elementType2 = type2.getElementType();
            while (true) {
                type3 = elementType2;
                if (elementType.getTypeCode() != 9 || type3.getTypeCode() != 9) {
                    break;
                }
                elementType = elementType.getElementType();
                elementType2 = type3.getElementType();
            }
            if (elementType.inMask(StackType.STOP_BT) && type3.inMask(StackType.STOP_BT)) {
                return explicitCast(elementType, type3);
            }
            return false;
        }
        return false;
    }

    public int getFlags() {
        return this.env.getFlags();
    }

    public final boolean debug_lines() {
        return (getFlags() & 4096) != 0;
    }

    public final boolean debug_vars() {
        return (getFlags() & 8192) != 0;
    }

    public final boolean debug_source() {
        return (getFlags() & 262144) != 0;
    }

    public final boolean opt() {
        return (getFlags() & 16384) != 0;
    }

    public final boolean opt_interclass() {
        return (getFlags() & 32768) != 0;
    }

    public final boolean verbose() {
        return (getFlags() & 1) != 0;
    }

    public final boolean dump() {
        return (getFlags() & 2) != 0;
    }

    public final boolean warnings() {
        return (getFlags() & 4) != 0;
    }

    public final boolean dependencies() {
        return (getFlags() & 32) != 0;
    }

    public final boolean print_dependencies() {
        return (getFlags() & 1024) != 0;
    }

    public final boolean deprecation() {
        return (getFlags() & 512) != 0;
    }

    public final boolean version12() {
        return (getFlags() & 2048) != 0;
    }

    public final boolean strictdefault() {
        return (getFlags() & 131072) != 0;
    }

    public void shutdown() {
        if (this.env != null) {
            this.env.shutdown();
        }
    }

    public void error(Object obj, long j, String str, Object obj2, Object obj3, Object obj4) {
        this.env.error(obj, j, str, obj2, obj3, obj4);
    }

    public final void error(long j, String str, Object obj, Object obj2, Object obj3) {
        error(this.source, j, str, obj, obj2, obj3);
    }

    public final void error(long j, String str, Object obj, Object obj2) {
        error(this.source, j, str, obj, obj2, null);
    }

    public final void error(long j, String str, Object obj) {
        error(this.source, j, str, obj, null, null);
    }

    public final void error(long j, String str) {
        error(this.source, j, str, null, null, null);
    }

    public void output(String str) {
        this.env.output(str);
    }

    static {
        debugging = System.getProperty("javac.debug") != null;
        dependtrace = System.getProperty("javac.trace.depend") != null;
        dumpmodifiers = System.getProperty("javac.dump.modifiers") != null;
    }

    public static void debugOutput(Object obj) {
        if (debugging) {
            System.out.println(obj.toString());
        }
    }

    public void setCharacterEncoding(String str) {
        this.encoding = str;
    }

    public String getCharacterEncoding() {
        return this.encoding;
    }

    public short getMajorVersion() {
        if (this.env == null) {
            return (short) 45;
        }
        return this.env.getMajorVersion();
    }

    public short getMinorVersion() {
        if (this.env == null) {
            return (short) 3;
        }
        return this.env.getMinorVersion();
    }

    public final boolean coverage() {
        return (getFlags() & 64) != 0;
    }

    public final boolean covdata() {
        return (getFlags() & 128) != 0;
    }

    public File getcovFile() {
        return this.env.getcovFile();
    }

    public void dtEnter(String str) {
        if (dependtrace) {
            System.out.println(">>> " + str);
        }
    }

    public void dtExit(String str) {
        if (dependtrace) {
            System.out.println("<<< " + str);
        }
    }

    public void dtEvent(String str) {
        if (dependtrace) {
            System.out.println(str);
        }
    }

    public boolean dumpModifiers() {
        return dumpmodifiers;
    }
}
