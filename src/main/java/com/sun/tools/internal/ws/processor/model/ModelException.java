package com.sun.tools.internal.ws.processor.model;

import com.sun.istack.internal.localization.Localizable;
import com.sun.tools.internal.ws.processor.ProcessorException;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/ModelException.class */
public class ModelException extends ProcessorException {
    public ModelException(String key, Object... args) {
        super(key, args);
    }

    public ModelException(Throwable throwable) {
        super(throwable);
    }

    public ModelException(Localizable arg) {
        super("model.nestedModelError", arg);
    }

    @Override // com.sun.tools.internal.ws.processor.ProcessorException
    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.model";
    }
}
