package com.sun.tools.javac.util;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Name;
import com.sun.xml.internal.xsom.XSFacet;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.slf4j.Marker;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/util/Names.class */
public class Names {
    public static final Context.Key<Names> namesKey = new Context.Key<>();
    public final Name.Table table;
    public final Name asterisk = fromString(Marker.ANY_MARKER);
    public final Name comma = fromString(DocLint.TAGS_SEPARATOR);
    public final Name empty = fromString("");
    public final Name hyphen = fromString(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR);
    public final Name one = fromString("1");
    public final Name period = fromString(sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR);
    public final Name semicolon = fromString(RuntimeConstants.SIG_ENDCLASS);
    public final Name slash = fromString(RuntimeConstants.SIG_PACKAGE);
    public final Name slashequals = fromString("/=");
    public final Name _class = fromString("class");
    public final Name _default = fromString("default");
    public final Name _super = fromString("super");
    public final Name _this = fromString("this");
    public final Name _name = fromString("name");
    public final Name addSuppressed = fromString("addSuppressed");
    public final Name any = fromString("<any>");
    public final Name append = fromString("append");
    public final Name clinit = fromString(org.spongepowered.asm.util.Constants.CLINIT);
    public final Name clone = fromString("clone");
    public final Name close = fromString("close");
    public final Name compareTo = fromString("compareTo");
    public final Name deserializeLambda = fromString("$deserializeLambda$");
    public final Name desiredAssertionStatus = fromString("desiredAssertionStatus");
    public final Name equals = fromString("equals");
    public final Name error = fromString("<error>");
    public final Name family = fromString("family");
    public final Name finalize = fromString("finalize");
    public final Name forName = fromString("forName");
    public final Name getClass = fromString("getClass");
    public final Name getClassLoader = fromString("getClassLoader");
    public final Name getComponentType = fromString("getComponentType");
    public final Name getDeclaringClass = fromString("getDeclaringClass");
    public final Name getMessage = fromString("getMessage");
    public final Name hasNext = fromString("hasNext");
    public final Name hashCode = fromString("hashCode");
    public final Name init = fromString(org.spongepowered.asm.util.Constants.CTOR);
    public final Name initCause = fromString("initCause");
    public final Name iterator = fromString("iterator");
    public final Name length = fromString(XSFacet.FACET_LENGTH);
    public final Name next = fromString("next");
    public final Name ordinal = fromString("ordinal");
    public final Name serialVersionUID = fromString(sun.rmi.rmic.iiop.Constants.SERIAL_VERSION_UID);
    public final Name toString = fromString("toString");
    public final Name value = fromString("value");
    public final Name valueOf = fromString("valueOf");
    public final Name values = fromString("values");
    public final Name dollarThis = fromString("$this");
    public final Name java_io_Serializable = fromString("java.io.Serializable");
    public final Name java_lang_AutoCloseable = fromString("java.lang.AutoCloseable");
    public final Name java_lang_Class = fromString("java.lang.Class");
    public final Name java_lang_Cloneable = fromString("java.lang.Cloneable");
    public final Name java_lang_Enum = fromString("java.lang.Enum");
    public final Name java_lang_Object = fromString("java.lang.Object");
    public final Name java_lang_invoke_MethodHandle = fromString("java.lang.invoke.MethodHandle");
    public final Name Array = fromString("Array");
    public final Name Bound = fromString("Bound");
    public final Name Method = fromString("Method");
    public final Name java_lang = fromString("java.lang");
    public final Name Annotation = fromString("Annotation");
    public final Name AnnotationDefault = fromString(Attribute.AnnotationDefault);
    public final Name BootstrapMethods = fromString(Attribute.BootstrapMethods);
    public final Name Bridge = fromString("Bridge");
    public final Name CharacterRangeTable = fromString(Attribute.CharacterRangeTable);
    public final Name Code = fromString("Code");
    public final Name CompilationID = fromString(Attribute.CompilationID);
    public final Name ConstantValue = fromString(Attribute.ConstantValue);
    public final Name Deprecated = fromString(Attribute.Deprecated);
    public final Name EnclosingMethod = fromString(Attribute.EnclosingMethod);
    public final Name Enum = fromString("Enum");
    public final Name Exceptions = fromString(Attribute.Exceptions);
    public final Name InnerClasses = fromString(Attribute.InnerClasses);
    public final Name LineNumberTable = fromString(Attribute.LineNumberTable);
    public final Name LocalVariableTable = fromString(Attribute.LocalVariableTable);
    public final Name LocalVariableTypeTable = fromString(Attribute.LocalVariableTypeTable);
    public final Name MethodParameters = fromString(Attribute.MethodParameters);
    public final Name RuntimeInvisibleAnnotations = fromString(Attribute.RuntimeInvisibleAnnotations);
    public final Name RuntimeInvisibleParameterAnnotations = fromString(Attribute.RuntimeInvisibleParameterAnnotations);
    public final Name RuntimeInvisibleTypeAnnotations = fromString(Attribute.RuntimeInvisibleTypeAnnotations);
    public final Name RuntimeVisibleAnnotations = fromString(Attribute.RuntimeVisibleAnnotations);
    public final Name RuntimeVisibleParameterAnnotations = fromString(Attribute.RuntimeVisibleParameterAnnotations);
    public final Name RuntimeVisibleTypeAnnotations = fromString(Attribute.RuntimeVisibleTypeAnnotations);
    public final Name Signature = fromString(Attribute.Signature);
    public final Name SourceFile = fromString(Attribute.SourceFile);
    public final Name SourceID = fromString(Attribute.SourceID);
    public final Name StackMap = fromString(Attribute.StackMap);
    public final Name StackMapTable = fromString(Attribute.StackMapTable);
    public final Name Synthetic = fromString(Attribute.Synthetic);
    public final Name Value = fromString("Value");
    public final Name Varargs = fromString("Varargs");
    public final Name ANNOTATION_TYPE = fromString("ANNOTATION_TYPE");
    public final Name CONSTRUCTOR = fromString("CONSTRUCTOR");
    public final Name FIELD = fromString("FIELD");
    public final Name LOCAL_VARIABLE = fromString("LOCAL_VARIABLE");
    public final Name METHOD = fromString("METHOD");
    public final Name PACKAGE = fromString("PACKAGE");
    public final Name PARAMETER = fromString("PARAMETER");
    public final Name TYPE = fromString("TYPE");
    public final Name TYPE_PARAMETER = fromString("TYPE_PARAMETER");
    public final Name TYPE_USE = fromString("TYPE_USE");
    public final Name CLASS = fromString("CLASS");
    public final Name RUNTIME = fromString("RUNTIME");
    public final Name SOURCE = fromString("SOURCE");
    public final Name T = fromString(PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
    public final Name deprecated = fromString("deprecated");
    public final Name ex = fromString("ex");
    public final Name package_info = fromString("package-info");
    public final Name lambda = fromString("lambda$");
    public final Name metafactory = fromString("metafactory");
    public final Name altMetafactory = fromString("altMetafactory");

    public static Names instance(Context context) {
        Names names = (Names) context.get(namesKey);
        if (names == null) {
            names = new Names(context);
            context.put((Context.Key<Context.Key<Names>>) namesKey, (Context.Key<Names>) names);
        }
        return names;
    }

    public Names(Context context) {
        this.table = createTable(Options.instance(context));
    }

    protected Name.Table createTable(Options options) {
        if (options.isSet("useUnsharedTable")) {
            return new UnsharedNameTable(this);
        }
        return new SharedNameTable(this);
    }

    public void dispose() {
        this.table.dispose();
    }

    public Name fromChars(char[] cArr, int i, int i2) {
        return this.table.fromChars(cArr, i, i2);
    }

    public Name fromString(String str) {
        return this.table.fromString(str);
    }

    public Name fromUtf(byte[] bArr) {
        return this.table.fromUtf(bArr);
    }

    public Name fromUtf(byte[] bArr, int i, int i2) {
        return this.table.fromUtf(bArr, i, i2);
    }
}
