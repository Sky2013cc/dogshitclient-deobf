package org.spongepowered.asm.lib.util;

import java.util.Map;
import org.spongepowered.asm.lib.Label;

/* loaded from: target.jar:org/spongepowered/asm/lib/util/ASMifiable.class */
public interface ASMifiable {
    void asmify(StringBuffer stringBuffer, String str, Map<Label, String> map);
}
