package com.sun.tools.internal.ws.processor.modeler;

import com.sun.istack.internal.localization.Localizable;
import com.sun.tools.internal.ws.processor.ProcessorException;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/ModelerException.class */
public class ModelerException extends ProcessorException {
    public ModelerException(String key) {
        super(key);
    }

    public ModelerException(String key, Object... args) {
        super(key, args);
    }

    public ModelerException(Throwable throwable) {
        super(throwable);
    }

    public ModelerException(Localizable arg) {
        super("modeler.nestedModelError", arg);
    }

    @Override // com.sun.tools.internal.ws.processor.ProcessorException
    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.modeler";
    }
}
