package com.sun.tools.corba.se.idl;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InvalidCharacter.class */
public class InvalidCharacter extends IOException {
    private String message;

    public InvalidCharacter(String str, String str2, int i, int i2, char c) {
        this.message = null;
        String str3 = "^";
        if (i2 > 1) {
            byte[] bArr = new byte[i2 - 1];
            for (int i3 = 0; i3 < i2 - 1; i3++) {
                bArr[i3] = 32;
            }
            str3 = new String(bArr) + str3;
        }
        this.message = Util.getMessage("InvalidCharacter.1", new String[]{str, Integer.toString(i), "" + c, Integer.toString(c), str2, str3});
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
