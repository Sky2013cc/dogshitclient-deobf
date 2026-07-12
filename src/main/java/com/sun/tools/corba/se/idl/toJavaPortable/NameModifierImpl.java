package com.sun.tools.corba.se.idl.toJavaPortable;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/toJavaPortable/NameModifierImpl.class */
public class NameModifierImpl implements NameModifier {
    private String prefix;
    private String suffix;

    public NameModifierImpl() {
        this.prefix = null;
        this.suffix = null;
    }

    public NameModifierImpl(String str, String str2) {
        this.prefix = str;
        this.suffix = str2;
    }

    public NameModifierImpl(String str) {
        int indexOf = str.indexOf(37);
        if (indexOf != str.lastIndexOf(37)) {
            throw new IllegalArgumentException(Util.getMessage("NameModifier.TooManyPercent"));
        }
        if (indexOf == -1) {
            throw new IllegalArgumentException(Util.getMessage("NameModifier.NoPercent"));
        }
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (!invalidChar(charAt, i == 0)) {
                i++;
            } else {
                throw new IllegalArgumentException(Util.getMessage("NameModifier.InvalidChar", new String(new char[]{charAt})));
            }
        }
        this.prefix = str.substring(0, indexOf);
        this.suffix = str.substring(indexOf + 1);
    }

    private boolean invalidChar(char c, boolean z) {
        if ('A' <= c && c <= 'Z') {
            return false;
        }
        if ('a' <= c && c <= 'z') {
            return false;
        }
        if ('0' <= c && c <= '9') {
            return z;
        }
        if (c == '%' || c == '$' || c == '_') {
            return false;
        }
        return true;
    }

    @Override // com.sun.tools.corba.se.idl.toJavaPortable.NameModifier
    public String makeName(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.prefix != null) {
            stringBuffer.append(this.prefix);
        }
        stringBuffer.append(str);
        if (this.suffix != null) {
            stringBuffer.append(this.suffix);
        }
        return stringBuffer.toString();
    }
}
