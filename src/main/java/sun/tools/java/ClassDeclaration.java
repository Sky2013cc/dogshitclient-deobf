package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/ClassDeclaration.class */
public final class ClassDeclaration implements Constants {
    int status;
    Type type;
    ClassDefinition definition;
    private boolean found = false;

    public ClassDeclaration(Identifier identifier) {
        this.type = Type.tClass(identifier);
    }

    public int getStatus() {
        return this.status;
    }

    public Identifier getName() {
        return this.type.getClassName();
    }

    public Type getType() {
        return this.type;
    }

    public boolean isDefined() {
        switch (this.status) {
            case 2:
            case 4:
            case 5:
            case 6:
                return true;
            case 3:
            default:
                return false;
        }
    }

    public ClassDefinition getClassDefinition() {
        return this.definition;
    }

    public ClassDefinition getClassDefinition(Environment environment) throws ClassNotFound {
        environment.dtEvent("getClassDefinition: " + getName() + ", status " + getStatus());
        if (this.found) {
            return this.definition;
        }
        while (true) {
            switch (this.status) {
                case 0:
                case 1:
                case 3:
                    environment.loadDefinition(this);
                case 2:
                case 4:
                    if (!this.definition.isInsideLocal()) {
                        this.definition.basicCheck(environment);
                    }
                    this.found = true;
                    return this.definition;
                case 5:
                case 6:
                    this.found = true;
                    return this.definition;
                default:
                    throw new ClassNotFound(getName());
            }
        }
    }

    public ClassDefinition getClassDefinitionNoCheck(Environment environment) throws ClassNotFound {
        environment.dtEvent("getClassDefinition: " + getName() + ", status " + getStatus());
        while (true) {
            switch (this.status) {
                case 0:
                case 1:
                case 3:
                    environment.loadDefinition(this);
                case 2:
                case 4:
                case 5:
                case 6:
                    return this.definition;
                default:
                    throw new ClassNotFound(getName());
            }
        }
    }

    public void setDefinition(ClassDefinition classDefinition, int i) {
        if (classDefinition != null && !getName().equals(classDefinition.getName())) {
            throw new CompilerError("setDefinition: name mismatch: " + this + ", " + classDefinition);
        }
        this.definition = classDefinition;
        this.status = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ClassDeclaration) {
            return this.type.equals(((ClassDeclaration) obj).type);
        }
        return false;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        String identifier = getName().toString();
        String str = "type ";
        String str2 = getName().isInner() ? "nested " : "";
        if (getClassDefinition() != null) {
            if (getClassDefinition().isInterface()) {
                str = "interface ";
            } else {
                str = "class ";
            }
            if (!getClassDefinition().isTopLevel()) {
                str2 = "inner ";
                if (getClassDefinition().isLocal()) {
                    str2 = "local ";
                    if (!getClassDefinition().isAnonymous()) {
                        identifier = getClassDefinition().getLocalName() + " (" + identifier + RuntimeConstants.SIG_ENDMETHOD;
                    }
                }
            }
        }
        return str2 + str + identifier;
    }
}
