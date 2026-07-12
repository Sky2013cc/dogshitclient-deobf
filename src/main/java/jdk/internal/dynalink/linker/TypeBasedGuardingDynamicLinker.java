package jdk.internal.dynalink.linker;

/* loaded from: target.jar:jdk/internal/dynalink/linker/TypeBasedGuardingDynamicLinker.class */
public interface TypeBasedGuardingDynamicLinker extends GuardingDynamicLinker {
    boolean canLinkType(Class<?> cls);
}
