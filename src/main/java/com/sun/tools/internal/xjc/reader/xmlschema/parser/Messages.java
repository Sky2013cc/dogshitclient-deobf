package com.sun.tools.internal.xjc.reader.xmlschema.parser;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/parser/Messages.class */
class Messages {
    static final String ERR_UNACKNOWLEDGED_CUSTOMIZATION = "CustomizationContextChecker.UnacknolwedgedCustomization";
    static final String WARN_INCORRECT_URI = "IncorrectNamespaceURIChecker.WarnIncorrectURI";
    static final String WARN_UNABLE_TO_CHECK_CORRECTNESS = "SchemaConstraintChecker.UnableToCheckCorrectness";

    Messages() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(String property, Object... args) {
        String text = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle").getString(property);
        return MessageFormat.format(text, args);
    }
}
