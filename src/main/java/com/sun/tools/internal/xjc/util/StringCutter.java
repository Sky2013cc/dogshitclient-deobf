package com.sun.tools.internal.xjc.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/StringCutter.class */
public final class StringCutter {
    private final String original;
    private String s;
    private boolean ignoreWhitespace;

    public StringCutter(String s, boolean ignoreWhitespace) {
        this.original = s;
        this.s = s;
        this.ignoreWhitespace = ignoreWhitespace;
    }

    public void skip(String regexp) throws ParseException {
        next(regexp);
    }

    public String next(String regexp) throws ParseException {
        trim();
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(this.s);
        if (m.lookingAt()) {
            String r = m.group();
            this.s = this.s.substring(r.length());
            trim();
            return r;
        }
        throw error();
    }

    private ParseException error() {
        return new ParseException(this.original, this.original.length() - this.s.length());
    }

    public String until(String regexp) throws ParseException {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(this.s);
        if (m.find()) {
            String r = this.s.substring(0, m.start());
            this.s = this.s.substring(m.start());
            if (this.ignoreWhitespace) {
                r = r.trim();
            }
            return r;
        }
        String r2 = this.s;
        this.s = "";
        return r2;
    }

    public char peek() {
        return this.s.charAt(0);
    }

    private void trim() {
        if (this.ignoreWhitespace) {
            this.s = this.s.trim();
        }
    }

    public int length() {
        return this.s.length();
    }
}
