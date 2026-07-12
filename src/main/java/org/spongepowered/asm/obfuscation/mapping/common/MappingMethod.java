package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.util.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/spongepowered/asm/obfuscation/mapping/common/MappingMethod.class */
public class MappingMethod implements IMapping<MappingMethod> {
    private final String owner;
    private final String name;
    private final String desc;

    public MappingMethod(String fullyQualifiedName, String desc) {
        this(getOwnerFromName(fullyQualifiedName), getBaseName(fullyQualifiedName), desc);
    }

    public MappingMethod(String owner, String simpleName, String desc) {
        this.owner = owner;
        this.name = simpleName;
        this.desc = desc;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public IMapping.Type getType() {
        return IMapping.Type.METHOD;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String getName() {
        if (this.name == null) {
            return null;
        }
        return (this.owner != null ? this.owner + RuntimeConstants.SIG_PACKAGE : "") + this.name;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String getSimpleName() {
        return this.name;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String getOwner() {
        return this.owner;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String getDesc() {
        return this.desc;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingMethod getSuper() {
        return null;
    }

    public boolean isConstructor() {
        return Constants.CTOR.equals(this.name);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingMethod move(String newOwner) {
        return new MappingMethod(newOwner, getSimpleName(), getDesc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingMethod remap(String newName) {
        return new MappingMethod(getOwner(), newName, getDesc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingMethod transform(String newDesc) {
        return new MappingMethod(getOwner(), getSimpleName(), newDesc);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingMethod copy() {
        return new MappingMethod(getOwner(), getSimpleName(), getDesc());
    }

    public MappingMethod addPrefix(String prefix) {
        String simpleName = getSimpleName();
        if (simpleName == null || simpleName.startsWith(prefix)) {
            return this;
        }
        return new MappingMethod(getOwner(), prefix + simpleName, getDesc());
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{getName(), getDesc()});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MappingMethod) && Objects.equal(this.name, ((MappingMethod) obj).name) && Objects.equal(this.desc, ((MappingMethod) obj).desc);
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String serialise() {
        return toString();
    }

    public String toString() {
        String desc = getDesc();
        Object[] objArr = new Object[3];
        objArr[0] = getName();
        objArr[1] = desc != null ? " " : "";
        objArr[2] = desc != null ? desc : "";
        return String.format("%s%s%s", objArr);
    }

    private static String getBaseName(String name) {
        if (name == null) {
            return null;
        }
        int pos = name.lastIndexOf(47);
        return pos > -1 ? name.substring(pos + 1) : name;
    }

    private static String getOwnerFromName(String name) {
        int pos;
        if (name != null && (pos = name.lastIndexOf(47)) > -1) {
            return name.substring(0, pos);
        }
        return null;
    }
}
