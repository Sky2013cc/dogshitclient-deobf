package com.sun.tools.javah;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javah.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javah/Gen.class */
public abstract class Gen {
    protected ProcessingEnvironment processingEnvironment;
    protected Types types;
    protected Elements elems;
    protected Mangle mangler;
    protected Util util;
    protected Set<TypeElement> classes;
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    protected JavaFileManager fileManager;
    protected JavaFileObject outFile;
    protected String lineSep = System.getProperty("line.separator");
    protected boolean force = false;

    protected abstract void write(OutputStream outputStream, TypeElement typeElement) throws Util.Exit;

    protected abstract String getIncludes();

    /* JADX INFO: Access modifiers changed from: protected */
    public Gen(Util util) {
        this.util = util;
    }

    public void setFileManager(JavaFileManager javaFileManager) {
        this.fileManager = javaFileManager;
    }

    public void setOutFile(JavaFileObject javaFileObject) {
        this.outFile = javaFileObject;
    }

    public void setClasses(Set<TypeElement> set) {
        this.classes = set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProcessingEnvironment(ProcessingEnvironment processingEnvironment) {
        this.processingEnvironment = processingEnvironment;
        this.elems = processingEnvironment.getElementUtils();
        this.types = processingEnvironment.getTypeUtils();
        this.mangler = new Mangle(this.elems, this.types);
    }

    public void setForce(boolean z) {
        this.force = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrintWriter wrapWriter(OutputStream outputStream) throws Util.Exit {
        try {
            return new PrintWriter((Writer) new OutputStreamWriter(outputStream, "ISO8859_1"), true);
        } catch (UnsupportedEncodingException e) {
            this.util.bug("encoding.iso8859_1.not.found");
            return null;
        }
    }

    public void run() throws IOException, ClassNotFoundException, Util.Exit {
        if (this.outFile != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            writeFileTop(byteArrayOutputStream);
            Iterator<TypeElement> it = this.classes.iterator();
            while (it.hasNext()) {
                write(byteArrayOutputStream, it.next());
            }
            writeIfChanged(byteArrayOutputStream.toByteArray(), this.outFile);
            return;
        }
        for (TypeElement typeElement : this.classes) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(8192);
            writeFileTop(byteArrayOutputStream2);
            write(byteArrayOutputStream2, typeElement);
            writeIfChanged(byteArrayOutputStream2.toByteArray(), getFileObject(typeElement.getQualifiedName()));
        }
    }

    private void writeIfChanged(byte[] bArr, FileObject fileObject) throws IOException {
        boolean z = false;
        String str = "[No need to update file ";
        if (this.force) {
            z = true;
            str = "[Forcefully writing file ";
        } else {
            try {
                if (!Arrays.equals(readBytes(fileObject.openInputStream()), bArr)) {
                    z = true;
                    str = "[Overwriting file ";
                }
            } catch (FileNotFoundException e) {
                z = true;
                str = "[Creating file ";
            }
        }
        if (this.util.verbose) {
            this.util.log(str + fileObject + "]");
        }
        if (z) {
            OutputStream openOutputStream = fileObject.openOutputStream();
            openOutputStream.write(bArr);
            openOutputStream.close();
        }
    }

    protected byte[] readBytes(InputStream inputStream) throws IOException {
        try {
            byte[] bArr = new byte[inputStream.available() + 1];
            int i = 0;
            while (true) {
                int read = inputStream.read(bArr, i, bArr.length - i);
                if (read != -1) {
                    i += read;
                    if (i == bArr.length) {
                        bArr = Arrays.copyOf(bArr, bArr.length * 2);
                    }
                } else {
                    byte[] copyOf = Arrays.copyOf(bArr, i);
                    inputStream.close();
                    return copyOf;
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String defineForStatic(TypeElement typeElement, VariableElement variableElement) throws Util.Exit {
        Object constantValue;
        CharSequence qualifiedName = typeElement.getQualifiedName();
        CharSequence simpleName = variableElement.getSimpleName();
        String mangle = this.mangler.mangle(qualifiedName, 1);
        String mangle2 = this.mangler.mangle(simpleName, 2);
        if (!variableElement.getModifiers().contains(Modifier.STATIC)) {
            this.util.bug("tried.to.define.non.static");
        }
        if (variableElement.getModifiers().contains(Modifier.FINAL) && (constantValue = variableElement.getConstantValue()) != null) {
            String str = null;
            if ((constantValue instanceof Integer) || (constantValue instanceof Byte) || (constantValue instanceof Short)) {
                str = constantValue.toString() + "L";
            } else if (constantValue instanceof Boolean) {
                str = ((Boolean) constantValue).booleanValue() ? "1L" : "0L";
            } else if (constantValue instanceof Character) {
                str = String.valueOf(((Character) constantValue).charValue() & 65535) + "L";
            } else if (constantValue instanceof Long) {
                if (isWindows) {
                    str = constantValue.toString() + "i64";
                } else {
                    str = constantValue.toString() + "LL";
                }
            } else if (constantValue instanceof Float) {
                float floatValue = ((Float) constantValue).floatValue();
                if (Float.isInfinite(floatValue)) {
                    str = (floatValue < 0.0f ? TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR : "") + "Inff";
                } else {
                    str = constantValue.toString() + "f";
                }
            } else if (constantValue instanceof Double) {
                double doubleValue = ((Double) constantValue).doubleValue();
                if (Double.isInfinite(doubleValue)) {
                    str = (doubleValue < 0.0d ? TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR : "") + "InfD";
                } else {
                    str = constantValue.toString();
                }
            }
            if (str != null) {
                return "#undef " + mangle + "_" + mangle2 + this.lineSep + "#define " + mangle + "_" + mangle2 + " " + str;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cppGuardBegin() {
        return "#ifdef __cplusplus" + this.lineSep + "extern \"C\" {" + this.lineSep + "#endif";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cppGuardEnd() {
        return "#ifdef __cplusplus" + this.lineSep + "}" + this.lineSep + "#endif";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String guardBegin(String str) {
        return "/* Header for class " + str + " */" + this.lineSep + this.lineSep + "#ifndef _Included_" + str + this.lineSep + "#define _Included_" + str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String guardEnd(String str) {
        return "#endif";
    }

    protected void writeFileTop(OutputStream outputStream) throws Util.Exit {
        wrapWriter(outputStream).println("/* DO NOT EDIT THIS FILE - it is machine generated */" + this.lineSep + getIncludes());
    }

    protected String baseFileName(CharSequence charSequence) {
        return this.mangler.mangle(charSequence, 1);
    }

    protected FileObject getFileObject(CharSequence charSequence) throws IOException {
        return this.fileManager.getFileForOutput(StandardLocation.SOURCE_OUTPUT, "", baseFileName(charSequence) + getFileSuffix(), (FileObject) null);
    }

    protected String getFileSuffix() {
        return ".h";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<VariableElement> getAllFields(TypeElement typeElement) {
        ArrayList arrayList = new ArrayList();
        Stack stack = new Stack();
        TypeElement typeElement2 = typeElement;
        while (true) {
            TypeElement typeElement3 = typeElement2;
            stack.push(typeElement3);
            TypeElement typeElement4 = (TypeElement) this.types.asElement(typeElement3.getSuperclass());
            if (typeElement4 == null) {
                break;
            }
            typeElement2 = typeElement4;
        }
        while (!stack.empty()) {
            arrayList.addAll(ElementFilter.fieldsIn(((TypeElement) stack.pop()).getEnclosedElements()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String signature(ExecutableElement executableElement) {
        StringBuilder sb = new StringBuilder(RuntimeConstants.SIG_METHOD);
        String str = "";
        for (VariableElement variableElement : executableElement.getParameters()) {
            sb.append(str);
            sb.append(this.types.erasure(variableElement.asType()).toString());
            str = DocLint.TAGS_SEPARATOR;
        }
        sb.append(RuntimeConstants.SIG_ENDMETHOD);
        return sb.toString();
    }
}
