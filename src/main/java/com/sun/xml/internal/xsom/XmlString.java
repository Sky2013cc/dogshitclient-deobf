package com.sun.xml.internal.xsom;

import org.relaxng.datatype.ValidationContext;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XmlString.class */
public final class XmlString {
    public final String value;
    public final ValidationContext context;
    private static final ValidationContext NULL_CONTEXT = new ValidationContext() { // from class: com.sun.xml.internal.xsom.XmlString.1
        @Override // org.relaxng.datatype.ValidationContext
        public String resolveNamespacePrefix(String s) {
            if (s.length() == 0) {
                return "";
            }
            if (s.equals("xml")) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            return null;
        }

        @Override // org.relaxng.datatype.ValidationContext
        public String getBaseUri() {
            return null;
        }

        @Override // org.relaxng.datatype.ValidationContext
        public boolean isUnparsedEntity(String s) {
            return false;
        }

        @Override // org.relaxng.datatype.ValidationContext
        public boolean isNotation(String s) {
            return false;
        }
    };

    public XmlString(String value, ValidationContext context) {
        this.value = value;
        this.context = context;
        if (context == null) {
            throw new IllegalArgumentException();
        }
    }

    public XmlString(String value) {
        this(value, NULL_CONTEXT);
    }

    public final String resolvePrefix(String prefix) {
        return this.context.resolveNamespacePrefix(prefix);
    }

    public String toString() {
        return this.value;
    }
}
