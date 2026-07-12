package org.spongepowered.asm.service.mojang;

import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.service.IGlobalPropertyService;

/* loaded from: target.jar:org/spongepowered/asm/service/mojang/Blackboard.class */
public class Blackboard implements IGlobalPropertyService {
    @Override // org.spongepowered.asm.service.IGlobalPropertyService
    public final <T> T getProperty(String str) {
        return (T) Launch.blackboard.get(str);
    }

    @Override // org.spongepowered.asm.service.IGlobalPropertyService
    public final void setProperty(String key, Object value) {
        Launch.blackboard.put(key, value);
    }

    @Override // org.spongepowered.asm.service.IGlobalPropertyService
    public final <T> T getProperty(String str, T t) {
        T t2 = (T) Launch.blackboard.get(str);
        return t2 != null ? t2 : t;
    }

    @Override // org.spongepowered.asm.service.IGlobalPropertyService
    public final String getPropertyString(String key, String defaultValue) {
        Object value = Launch.blackboard.get(key);
        return value != null ? value.toString() : defaultValue;
    }
}
