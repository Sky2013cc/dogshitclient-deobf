package com.sun.tools.corba.se.idl;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/GenFileStream.class */
public class GenFileStream extends PrintWriter {
    private CharArrayWriter charArrayWriter;
    private static CharArrayWriter tmpCharArrayWriter;
    private String name;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GenFileStream(String str) {
        super(r1);
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        tmpCharArrayWriter = charArrayWriter;
        this.charArrayWriter = tmpCharArrayWriter;
        this.name = str;
    }

    @Override // java.io.PrintWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        File file = new File(this.name);
        try {
        } catch (IOException e) {
            System.err.println(Util.getMessage("GenFileStream.1", new String[]{this.name, e.toString()}));
        }
        if (checkError()) {
            throw new IOException();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(this.charArrayWriter.toCharArray());
        fileWriter.close();
        super.close();
    }

    public String name() {
        return this.name;
    }
}
