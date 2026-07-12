package net.ccbluex.liquidbounce.injector;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/* loaded from: target.jar:net/ccbluex/liquidbounce/injector/RuntimeMixinTransformer.class */
public class RuntimeMixinTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className == null || classfileBuffer == null || !shouldTransform(className)) {
            return null;
        }
        try {
            if (className.contains("Minecraft") || className.contains("EntityPlayer")) {
                System.out.println("[Haze] Processing class for mixin: " + className);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean shouldTransform(String className) {
        return className.startsWith("net/minecraft") || className.startsWith("ave") || className.startsWith("blg") || className.startsWith("bsv") || className.startsWith("net/ccbluex/liquidbounce");
    }
}
