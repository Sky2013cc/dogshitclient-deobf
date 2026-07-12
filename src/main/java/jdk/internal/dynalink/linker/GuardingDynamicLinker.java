package jdk.internal.dynalink.linker;

/* loaded from: target.jar:jdk/internal/dynalink/linker/GuardingDynamicLinker.class */
public interface GuardingDynamicLinker {
    GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception;
}
