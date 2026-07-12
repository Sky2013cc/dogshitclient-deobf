package com.sun.tools.internal.ws.api.wsdl;

import com.sun.codemodel.internal.JClass;
import java.util.Map;

/* loaded from: target.jar:com/sun/tools/internal/ws/api/wsdl/TWSDLOperation.class */
public interface TWSDLOperation extends TWSDLExtensible {
    Map<String, JClass> getFaults();
}
