package com.sun.tools.javap;

import com.sun.tools.classfile.AttributeException;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.DescriptorException;
import java.io.PrintWriter;

/* loaded from: target.jar:com/sun/tools/javap/BasicWriter.class */
public class BasicWriter {
    private String[] spaces = new String[80];
    private LineWriter lineWriter;
    private PrintWriter out;
    protected Messages messages;

    /* JADX INFO: Access modifiers changed from: protected */
    public BasicWriter(Context context) {
        this.lineWriter = LineWriter.instance(context);
        this.out = (PrintWriter) context.get(PrintWriter.class);
        this.messages = (Messages) context.get(Messages.class);
        if (this.messages == null) {
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void print(String str) {
        this.lineWriter.print(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void print(Object obj) {
        this.lineWriter.print(obj == null ? null : obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void println() {
        this.lineWriter.println();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void println(String str) {
        this.lineWriter.print(str);
        this.lineWriter.println();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void println(Object obj) {
        this.lineWriter.print(obj == null ? null : obj.toString());
        this.lineWriter.println();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void indent(int i) {
        this.lineWriter.indent(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void tab() {
        this.lineWriter.tab();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPendingNewline(boolean z) {
        this.lineWriter.pendingNewline = z;
    }

    protected String report(AttributeException attributeException) {
        this.out.println("Error: " + attributeException.getMessage());
        return "???";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String report(ConstantPoolException constantPoolException) {
        this.out.println("Error: " + constantPoolException.getMessage());
        return "???";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String report(DescriptorException descriptorException) {
        this.out.println("Error: " + descriptorException.getMessage());
        return "???";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String report(String str) {
        this.out.println("Error: " + str);
        return "???";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String space(int i) {
        if (i < this.spaces.length && this.spaces[i] != null) {
            return this.spaces[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        String sb2 = sb.toString();
        if (i < this.spaces.length) {
            this.spaces[i] = sb2;
        }
        return sb2;
    }

    /* loaded from: target.jar:com/sun/tools/javap/BasicWriter$LineWriter.class */
    private static class LineWriter {
        private final PrintWriter out;
        private final StringBuilder buffer;
        private int indentCount;
        private final int indentWidth;
        private final int tabColumn;
        private boolean pendingNewline;
        private int pendingSpaces;

        static LineWriter instance(Context context) {
            LineWriter lineWriter = (LineWriter) context.get(LineWriter.class);
            if (lineWriter == null) {
                lineWriter = new LineWriter(context);
            }
            return lineWriter;
        }

        protected LineWriter(Context context) {
            context.put(LineWriter.class, this);
            Options instance = Options.instance(context);
            this.indentWidth = instance.indentWidth;
            this.tabColumn = instance.tabColumn;
            this.out = (PrintWriter) context.get(PrintWriter.class);
            this.buffer = new StringBuilder();
        }

        protected void print(String str) {
            if (this.pendingNewline) {
                println();
                this.pendingNewline = false;
            }
            if (str == null) {
                str = "null";
            }
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                switch (charAt) {
                    case '\n':
                        println();
                        break;
                    case ' ':
                        this.pendingSpaces++;
                        break;
                    default:
                        if (this.buffer.length() == 0) {
                            indent();
                        }
                        if (this.pendingSpaces > 0) {
                            for (int i2 = 0; i2 < this.pendingSpaces; i2++) {
                                this.buffer.append(' ');
                            }
                            this.pendingSpaces = 0;
                        }
                        this.buffer.append(charAt);
                        break;
                }
            }
        }

        protected void println() {
            this.pendingSpaces = 0;
            this.out.println(this.buffer);
            this.buffer.setLength(0);
        }

        protected void indent(int i) {
            this.indentCount += i;
        }

        protected void tab() {
            int i = (this.indentCount * this.indentWidth) + this.tabColumn;
            this.pendingSpaces += i <= this.buffer.length() ? 1 : i - this.buffer.length();
        }

        private void indent() {
            this.pendingSpaces += this.indentCount * this.indentWidth;
        }
    }
}
