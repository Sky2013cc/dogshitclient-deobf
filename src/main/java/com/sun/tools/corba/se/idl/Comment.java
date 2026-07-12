package com.sun.tools.corba.se.idl;

import java.io.PrintWriter;
import org.slf4j.Marker;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/Comment.class */
public class Comment {
    static final int UNKNOWN = -1;
    static final int JAVA_DOC = 0;
    static final int C_BLOCK = 1;
    static final int CPP_LINE = 2;
    private static String _eol = System.getProperty("line.separator");
    private String _text;
    private int _style;

    Comment() {
        this._text = new String("");
        this._style = -1;
        this._text = new String("");
        this._style = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Comment(String str) {
        this._text = new String("");
        this._style = -1;
        this._text = str;
        this._style = style(this._text);
    }

    public void text(String str) {
        this._text = str;
        this._style = style(this._text);
    }

    public String text() {
        return this._text;
    }

    private int style(String str) {
        if (str == null) {
            return -1;
        }
        if (str.startsWith("/**") && str.endsWith("*/")) {
            return 0;
        }
        if (str.startsWith("/*") && str.endsWith("*/")) {
            return 1;
        }
        if (str.startsWith("//")) {
            return 2;
        }
        return -1;
    }

    public void write() {
        System.out.println(this._text);
    }

    public void generate(String str, PrintWriter printWriter) {
        if (this._text == null || printWriter == null) {
            return;
        }
        if (str == null) {
            str = new String("");
        }
        switch (this._style) {
            case 0:
                print(str, printWriter);
                return;
            case 1:
                print(str, printWriter);
                return;
            case 2:
                print(str, printWriter);
                return;
            default:
                return;
        }
    }

    private void print(String str, PrintWriter printWriter) {
        String str2 = this._text.trim() + _eol;
        int i = 0;
        int indexOf = str2.indexOf(_eol);
        int length = str2.length() - 1;
        printWriter.println();
        while (i < length) {
            printWriter.println(str + str2.substring(i, indexOf));
            i = indexOf + _eol.length();
            indexOf = i + str2.substring(i).indexOf(_eol);
        }
    }

    private void printJavaDoc(String str, PrintWriter printWriter) {
        String str2 = this._text.substring(3, this._text.length() - 2).trim() + _eol;
        int i = 0;
        int indexOf = str2.indexOf(_eol);
        int length = str2.length() - 1;
        printWriter.println(_eol + str + "/**");
        while (i < length) {
            String trim = str2.substring(i, indexOf).trim();
            if (trim.startsWith(Marker.ANY_MARKER)) {
                printWriter.println(str + " * " + trim.substring(1, trim.length()).trim());
            } else {
                printWriter.println(str + " * " + trim);
            }
            i = indexOf + _eol.length();
            indexOf = i + str2.substring(i).indexOf(_eol);
        }
        printWriter.println(str + " */");
    }

    private void printCBlock(String str, PrintWriter printWriter) {
        String str2 = this._text.substring(2, this._text.length() - 2).trim() + _eol;
        int i = 0;
        int indexOf = str2.indexOf(_eol);
        int length = str2.length() - 1;
        printWriter.println(str + "/*");
        while (i < length) {
            String trim = str2.substring(i, indexOf).trim();
            if (trim.startsWith(Marker.ANY_MARKER)) {
                printWriter.println(str + " * " + trim.substring(1, trim.length()).trim());
            } else {
                printWriter.println(str + " * " + trim);
            }
            i = indexOf + _eol.length();
            indexOf = i + str2.substring(i).indexOf(_eol);
        }
        printWriter.println(str + " */");
    }

    private void printCppLine(String str, PrintWriter printWriter) {
        printWriter.println(str + "//");
        printWriter.println(str + "// " + this._text.substring(2).trim());
        printWriter.println(str + "//");
    }
}
