package com.sun.tools.internal.jxc.ap;

import com.sun.tools.internal.jxc.api.JXC;
import com.sun.tools.internal.xjc.api.J2SJAXBModel;
import com.sun.tools.internal.xjc.api.Reference;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Marker;

@SupportedAnnotationTypes({Marker.ANY_MARKER})
/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/SchemaGenerator.class */
public class SchemaGenerator extends AbstractProcessor {
    private final Map<String, File> schemaLocations = new HashMap();
    private File episodeFile;

    public SchemaGenerator() {
    }

    public SchemaGenerator(Map<String, File> m) {
        this.schemaLocations.putAll(m);
    }

    public void setEpisodeFile(File episodeFile) {
        this.episodeFile = episodeFile;
    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ErrorReceiverImpl errorListener = new ErrorReceiverImpl(this.processingEnv);
        List<Reference> classes = new ArrayList<>();
        filterClass(classes, roundEnv.getRootElements());
        J2SJAXBModel model = JXC.createJavaCompiler().bind(classes, Collections.emptyMap(), null, this.processingEnv);
        if (model == null) {
            return false;
        }
        try {
            model.generateSchema(new SchemaOutputResolver() { // from class: com.sun.tools.internal.jxc.ap.SchemaGenerator.1
                public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                    OutputStream out;
                    File file;
                    if (SchemaGenerator.this.schemaLocations.containsKey(namespaceUri)) {
                        file = (File) SchemaGenerator.this.schemaLocations.get(namespaceUri);
                        if (file == null) {
                            return null;
                        }
                        out = new FileOutputStream(file);
                    } else {
                        File file2 = new File(suggestedFileName);
                        out = SchemaGenerator.this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", suggestedFileName, new Element[0]).openOutputStream();
                        file = file2.getAbsoluteFile();
                    }
                    StreamResult ss = new StreamResult(out);
                    SchemaGenerator.this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing " + file);
                    ss.setSystemId(file.toURL().toExternalForm());
                    return ss;
                }
            }, errorListener);
            if (this.episodeFile != null) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing " + this.episodeFile);
                model.generateEpisodeFile(new StreamResult(this.episodeFile));
            }
            return false;
        } catch (IOException e) {
            errorListener.error(e.getMessage(), e);
            return false;
        }
    }

    private void filterClass(List<Reference> classes, Collection<? extends Element> elements) {
        Iterator<? extends Element> it = elements.iterator();
        while (it.hasNext()) {
            TypeElement typeElement = (Element) it.next();
            if (typeElement.getKind().equals(ElementKind.CLASS) || typeElement.getKind().equals(ElementKind.ENUM)) {
                classes.add(new Reference(typeElement, this.processingEnv));
                filterClass(classes, ElementFilter.typesIn(typeElement.getEnclosedElements()));
            }
        }
    }

    public SourceVersion getSupportedSourceVersion() {
        if (SourceVersion.latest().compareTo(SourceVersion.RELEASE_6) > 0) {
            return SourceVersion.valueOf("RELEASE_7");
        }
        return SourceVersion.RELEASE_6;
    }
}
