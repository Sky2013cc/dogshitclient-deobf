package jdk.nashorn.internal.runtime.regexp.joni.ast;

import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/joni/ast/StringNode.class */
public final class StringNode extends Node implements StringType {
    private static final int NODE_STR_MARGIN = 16;
    private static final int NODE_STR_BUF_SIZE = 24;
    public static final StringNode EMPTY = new StringNode(null, Integer.MAX_VALUE, Integer.MAX_VALUE);
    public char[] chars;
    public int p;
    public int end;
    public int flag;

    public StringNode() {
        this.chars = new char[24];
    }

    public StringNode(char[] chars, int p, int end) {
        this.chars = chars;
        this.p = p;
        this.end = end;
        setShared();
    }

    public StringNode(char c) {
        this();
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = c;
    }

    public void ensure(int ahead) {
        int len = (this.end - this.p) + ahead;
        if (len >= this.chars.length) {
            char[] tmp = new char[len + 16];
            System.arraycopy(this.chars, this.p, tmp, 0, this.end - this.p);
            this.chars = tmp;
        }
    }

    private void modifyEnsure(int ahead) {
        if (isShared()) {
            int len = (this.end - this.p) + ahead;
            char[] tmp = new char[len + 16];
            System.arraycopy(this.chars, this.p, tmp, 0, this.end - this.p);
            this.chars = tmp;
            this.end -= this.p;
            this.p = 0;
            clearShared();
            return;
        }
        ensure(ahead);
    }

    @Override // jdk.nashorn.internal.runtime.regexp.joni.ast.Node
    public int getType() {
        return 0;
    }

    @Override // jdk.nashorn.internal.runtime.regexp.joni.ast.Node
    public String getName() {
        return "String";
    }

    @Override // jdk.nashorn.internal.runtime.regexp.joni.ast.Node
    public String toString(int level) {
        StringBuilder value = new StringBuilder();
        value.append("\n  bytes: '");
        for (int i = this.p; i < this.end; i++) {
            if (this.chars[i] >= ' ' && this.chars[i] < 127) {
                value.append(this.chars[i]);
            } else {
                value.append(String.format("[0x%04x]", Integer.valueOf(this.chars[i])));
            }
        }
        value.append(OperatorName.SHOW_TEXT_LINE);
        return value.toString();
    }

    public int length() {
        return this.end - this.p;
    }

    public StringNode splitLastChar() {
        int prev;
        StringNode n = null;
        if (this.end > this.p && (prev = EncodingHelper.prevCharHead(this.p, this.end)) != -1 && prev > this.p) {
            n = new StringNode(this.chars, prev, this.end);
            if (isRaw()) {
                n.setRaw();
            }
            this.end = prev;
        }
        return n;
    }

    public boolean canBeSplit() {
        return this.end > this.p && 1 < this.end - this.p;
    }

    public void set(char[] chars, int p, int end) {
        this.chars = chars;
        this.p = p;
        this.end = end;
        setShared();
    }

    public void cat(char[] cat, int catP, int catEnd) {
        int len = catEnd - catP;
        modifyEnsure(len);
        System.arraycopy(cat, catP, this.chars, this.end, len);
        this.end += len;
    }

    public void cat(char c) {
        modifyEnsure(1);
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = c;
    }

    public void catCode(int code) {
        cat((char) code);
    }

    public void clear() {
        if (this.chars.length > 24) {
            this.chars = new char[24];
        }
        this.flag = 0;
        this.end = 0;
        this.p = 0;
    }

    public void setRaw() {
        this.flag |= 1;
    }

    public void clearRaw() {
        this.flag &= -2;
    }

    public boolean isRaw() {
        return (this.flag & 1) != 0;
    }

    public void setAmbig() {
        this.flag |= 2;
    }

    public void clearAmbig() {
        this.flag &= -3;
    }

    public boolean isAmbig() {
        return (this.flag & 2) != 0;
    }

    public void setDontGetOptInfo() {
        this.flag |= 4;
    }

    public void clearDontGetOptInfo() {
        this.flag &= -5;
    }

    public boolean isDontGetOptInfo() {
        return (this.flag & 4) != 0;
    }

    public void setShared() {
        this.flag |= 8;
    }

    public void clearShared() {
        this.flag &= -9;
    }

    public boolean isShared() {
        return (this.flag & 8) != 0;
    }
}
