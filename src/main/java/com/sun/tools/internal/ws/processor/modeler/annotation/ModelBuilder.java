package com.sun.tools.internal.ws.processor.modeler.annotation;

import com.sun.tools.internal.ws.processor.modeler.ModelerException;
import com.sun.tools.internal.ws.wscompile.WsgenOptions;
import java.io.File;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/annotation/ModelBuilder.class */
public interface ModelBuilder {
    ProcessingEnvironment getProcessingEnvironment();

    String getOperationName(Name name);

    TypeMirror getHolderValueType(TypeMirror typeMirror);

    boolean checkAndSetProcessed(TypeElement typeElement);

    boolean isServiceException(TypeMirror typeMirror);

    boolean isRemote(TypeElement typeElement);

    boolean canOverWriteClass(String str);

    WsgenOptions getOptions();

    File getSourceDir();

    void log(String str);

    void processWarning(String str);

    void processError(String str);

    void processError(String str, Element element) throws ModelerException;
}
