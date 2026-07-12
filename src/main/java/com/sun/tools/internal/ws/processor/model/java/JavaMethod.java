package com.sun.tools.internal.ws.processor.model.java;

import com.sun.tools.internal.ws.processor.model.Parameter;
import com.sun.tools.internal.ws.resources.ModelMessages;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import com.sun.tools.internal.ws.wscompile.WsimportOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/java/JavaMethod.class */
public class JavaMethod {
    private final ErrorReceiver errorReceiver;
    private final String name;
    private final WsimportOptions options;
    private final List<JavaParameter> parameters = new ArrayList();
    private final List<String> exceptions = new ArrayList();
    private JavaType returnType = null;

    public JavaMethod(String name, WsimportOptions options, ErrorReceiver receiver) {
        this.name = name;
        this.errorReceiver = receiver;
        this.options = options;
    }

    public String getName() {
        return this.name;
    }

    public JavaType getReturnType() {
        return this.returnType;
    }

    public void setReturnType(JavaType returnType) {
        this.returnType = returnType;
    }

    private boolean hasParameter(String paramName) {
        for (JavaParameter parameter : this.parameters) {
            if (paramName.equals(parameter.getName())) {
                return true;
            }
        }
        return false;
    }

    private Parameter getParameter(String paramName) {
        for (JavaParameter parameter : this.parameters) {
            if (paramName.equals(parameter.getName())) {
                return parameter.getParameter();
            }
        }
        return null;
    }

    public void addParameter(JavaParameter param) {
        if (hasParameter(param.getName())) {
            if (this.options.isExtensionMode()) {
                param.setName(getUniqueName(param.getName()));
            } else {
                Parameter duplicParam = getParameter(param.getName());
                if (param.getParameter().isEmbedded()) {
                    this.errorReceiver.error(param.getParameter().getLocator(), ModelMessages.MODEL_PARAMETER_NOTUNIQUE_WRAPPER(param.getName(), param.getParameter().getEntityName()));
                    this.errorReceiver.error(duplicParam.getLocator(), ModelMessages.MODEL_PARAMETER_NOTUNIQUE_WRAPPER(param.getName(), duplicParam.getEntityName()));
                    return;
                } else {
                    this.errorReceiver.error(param.getParameter().getLocator(), ModelMessages.MODEL_PARAMETER_NOTUNIQUE(param.getName(), param.getParameter().getEntityName()));
                    this.errorReceiver.error(duplicParam.getLocator(), ModelMessages.MODEL_PARAMETER_NOTUNIQUE(param.getName(), duplicParam.getEntityName()));
                    return;
                }
            }
        }
        this.parameters.add(param);
    }

    public List<JavaParameter> getParametersList() {
        return this.parameters;
    }

    public void addException(String exception) {
        if (!this.exceptions.contains(exception)) {
            this.exceptions.add(exception);
        }
    }

    public Iterator<String> getExceptions() {
        return this.exceptions.iterator();
    }

    private String getUniqueName(String param) {
        int parmNum = 0;
        while (hasParameter(param)) {
            int i = parmNum;
            parmNum++;
            param = param + Integer.toString(i);
        }
        return param;
    }
}
