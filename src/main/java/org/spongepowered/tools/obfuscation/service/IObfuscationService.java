package org.spongepowered.tools.obfuscation.service;

import java.util.Collection;
import java.util.Set;

/* loaded from: target.jar:org/spongepowered/tools/obfuscation/service/IObfuscationService.class */
public interface IObfuscationService {
    Set<String> getSupportedOptions();

    Collection<ObfuscationTypeDescriptor> getObfuscationTypes();
}
