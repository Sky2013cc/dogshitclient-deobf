package com.sun.xml.internal.rngom.parse;

import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.ast.builder.IncludedGrammar;
import com.sun.xml.internal.rngom.ast.builder.SchemaBuilder;
import com.sun.xml.internal.rngom.ast.builder.Scope;
import com.sun.xml.internal.rngom.ast.om.ParsedPattern;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/Parseable.class */
public interface Parseable {
    <P extends ParsedPattern> P parse(SchemaBuilder<?, P, ?, ?, ?, ?> schemaBuilder) throws BuildException, IllegalSchemaException;

    <P extends ParsedPattern> P parseInclude(String str, SchemaBuilder<?, P, ?, ?, ?, ?> schemaBuilder, IncludedGrammar<P, ?, ?, ?, ?> includedGrammar, String str2) throws BuildException, IllegalSchemaException;

    <P extends ParsedPattern> P parseExternal(String str, SchemaBuilder<?, P, ?, ?, ?, ?> schemaBuilder, Scope scope, String str2) throws BuildException, IllegalSchemaException;
}
