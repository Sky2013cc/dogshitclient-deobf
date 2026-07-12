package com.sun.codemodel.internal.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* loaded from: target.jar:com/sun/codemodel/internal/util/EncoderFactory.class */
public class EncoderFactory {
    public static CharsetEncoder createEncoder(String encodin) {
        Charset cs = Charset.forName(System.getProperty("file.encoding"));
        CharsetEncoder encoder = cs.newEncoder();
        if (cs.getClass().getName().equals("sun.nio.cs.MS1252")) {
            try {
                return (CharsetEncoder) Class.forName("com.sun.codemodel.internal.util.MS1252Encoder").getConstructor(Charset.class).newInstance(cs);
            } catch (Throwable th) {
                return encoder;
            }
        }
        return encoder;
    }
}
