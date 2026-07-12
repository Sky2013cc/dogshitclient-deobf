package com.sun.javadoc;

/* loaded from: target.jar:com/sun/javadoc/ClassDoc.class */
public interface ClassDoc extends ProgramElementDoc, Type {
    boolean isAbstract();

    boolean isSerializable();

    boolean isExternalizable();

    MethodDoc[] serializationMethods();

    FieldDoc[] serializableFields();

    boolean definesSerializableFields();

    ClassDoc superclass();

    Type superclassType();

    boolean subclassOf(ClassDoc classDoc);

    ClassDoc[] interfaces();

    Type[] interfaceTypes();

    TypeVariable[] typeParameters();

    ParamTag[] typeParamTags();

    FieldDoc[] fields();

    FieldDoc[] fields(boolean z);

    FieldDoc[] enumConstants();

    MethodDoc[] methods();

    MethodDoc[] methods(boolean z);

    ConstructorDoc[] constructors();

    ConstructorDoc[] constructors(boolean z);

    ClassDoc[] innerClasses();

    ClassDoc[] innerClasses(boolean z);

    ClassDoc findClass(String str);

    @Deprecated
    ClassDoc[] importedClasses();

    @Deprecated
    PackageDoc[] importedPackages();
}
