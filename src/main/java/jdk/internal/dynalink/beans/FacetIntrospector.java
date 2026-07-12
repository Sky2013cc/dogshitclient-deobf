package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import jdk.internal.dynalink.support.Lookup;

/* loaded from: target.jar:jdk/internal/dynalink/beans/FacetIntrospector.class */
abstract class FacetIntrospector {
    private final Class<?> clazz;
    private final boolean instance;
    private final boolean isRestricted;
    protected final AccessibleMembersLookup membersLookup;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map<String, MethodHandle> getInnerClassGetters();

    abstract MethodHandle editMethodHandle(MethodHandle methodHandle);

    /* JADX INFO: Access modifiers changed from: package-private */
    public FacetIntrospector(Class<?> clazz, boolean instance) {
        this.clazz = clazz;
        this.instance = instance;
        this.isRestricted = CheckRestrictedPackage.isRestrictedClass(clazz);
        this.membersLookup = new AccessibleMembersLookup(clazz, instance);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<Field> getFields() {
        if (this.isRestricted) {
            return Collections.emptySet();
        }
        Field[] fields = this.clazz.getFields();
        Collection<Field> cfields = new ArrayList<>(fields.length);
        for (Field field : fields) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if ((!isStatic || this.clazz == field.getDeclaringClass()) && this.instance != isStatic && isAccessible(field)) {
                cfields.add(field);
            }
        }
        return cfields;
    }

    boolean isAccessible(Member m) {
        Class<?> declaring = m.getDeclaringClass();
        return declaring == this.clazz || !CheckRestrictedPackage.isRestrictedClass(declaring);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<Method> getMethods() {
        return this.membersLookup.getMethods();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodHandle unreflectGetter(Field field) {
        return editMethodHandle(Lookup.PUBLIC.unreflectGetter(field));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodHandle unreflectSetter(Field field) {
        return editMethodHandle(Lookup.PUBLIC.unreflectSetter(field));
    }
}
