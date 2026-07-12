package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.util.Collections;
import java.util.Map;

/* loaded from: target.jar:jdk/internal/dynalink/beans/BeanIntrospector.class */
class BeanIntrospector extends FacetIntrospector {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BeanIntrospector(Class<?> clazz) {
        super(clazz, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.internal.dynalink.beans.FacetIntrospector
    public Map<String, MethodHandle> getInnerClassGetters() {
        return Collections.emptyMap();
    }

    @Override // jdk.internal.dynalink.beans.FacetIntrospector
    MethodHandle editMethodHandle(MethodHandle mh) {
        return mh;
    }
}
