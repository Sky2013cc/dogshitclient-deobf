package org.spongepowered.asm.service;

/* loaded from: target.jar:org/spongepowered/asm/service/IMixinServiceBootstrap.class */
public interface IMixinServiceBootstrap {
    String getName();

    String getServiceClassName();

    void bootstrap();
}
