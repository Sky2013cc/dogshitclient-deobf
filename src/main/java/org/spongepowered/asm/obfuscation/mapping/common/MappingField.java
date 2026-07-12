package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

/* loaded from: target.jar:org/spongepowered/asm/obfuscation/mapping/common/MappingField.class */
public class MappingField implements IMapping<MappingField> {
    private final String owner;
    private final String name;
    private final String desc;

    public MappingField(String owner, String name) {
        this(owner, name, null);
    }

    public MappingField(String owner, String name, String desc) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public IMapping.Type getType() {
        return IMapping.Type.FIELD;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String getName() {
        return this.name;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public final String getSimpleName() {
        return this.name;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public final String getOwner() {
        return this.owner;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public final String getDesc() {
        return this.desc;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingField getSuper() {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingField move(String newOwner) {
        return new MappingField(newOwner, getName(), getDesc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingField remap(String newName) {
        return new MappingField(getOwner(), newName, getDesc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingField transform(String newDesc) {
        return new MappingField(getOwner(), getName(), newDesc);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public MappingField copy() {
        return new MappingField(getOwner(), getName(), getDesc());
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{toString()});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MappingField) {
            return Objects.equal(toString(), ((MappingField) obj).toString());
        }
        return false;
    }

    @Override // org.spongepowered.asm.obfuscation.mapping.IMapping
    public String serialise() {
        return toString();
    }

    public String toString() {
        return String.format("L%s;%s:%s", getOwner(), getName(), Strings.nullToEmpty(getDesc()));
    }
}
