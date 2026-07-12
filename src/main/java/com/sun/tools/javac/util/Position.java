package com.sun.tools.javac.util;

import java.util.BitSet;

/* loaded from: target.jar:com/sun/tools/javac/util/Position.class */
public class Position {
    public static final int NOPOS = -1;
    public static final int FIRSTPOS = 0;
    public static final int FIRSTLINE = 1;
    public static final int FIRSTCOLUMN = 1;
    public static final int LINESHIFT = 10;
    public static final int MAXCOLUMN = 1023;
    public static final int MAXLINE = 4194303;
    public static final int MAXPOS = Integer.MAX_VALUE;

    /* loaded from: target.jar:com/sun/tools/javac/util/Position$LineMap.class */
    public interface LineMap extends com.sun.source.tree.LineMap {
        int getStartPosition(int i);

        int getPosition(int i, int i2);

        int getLineNumber(int i);

        int getColumnNumber(int i);
    }

    private Position() {
    }

    public static LineMap makeLineMap(char[] cArr, int i, boolean z) {
        LineMapImpl lineTabMapImpl = z ? new LineTabMapImpl(i) : new LineMapImpl();
        lineTabMapImpl.build(cArr, i);
        return lineTabMapImpl;
    }

    public static int encodePosition(int i, int i2) {
        if (i < 1) {
            throw new IllegalArgumentException("line must be greater than 0");
        }
        if (i2 < 1) {
            throw new IllegalArgumentException("column must be greater than 0");
        }
        if (i > 4194303 || i2 > 1023) {
            return -1;
        }
        return (i << 10) + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/util/Position$LineMapImpl.class */
    public static class LineMapImpl implements LineMap {
        protected int[] startPosition;
        private int lastPosition = 0;
        private int lastLine = 1;

        protected LineMapImpl() {
        }

        protected void build(char[] cArr, int i) {
            int i2 = 0;
            int i3 = 0;
            int[] iArr = new int[i];
            while (i3 < i) {
                int i4 = i2;
                i2++;
                iArr[i4] = i3;
                do {
                    char c = cArr[i3];
                    if (c == '\r' || c == '\n') {
                        if (c == '\r' && i3 + 1 < i && cArr[i3 + 1] == '\n') {
                            i3 += 2;
                        } else {
                            i3++;
                        }
                    } else {
                        if (c == '\t') {
                            setTabPosition(i3);
                        }
                        i3++;
                    }
                } while (i3 < i);
            }
            this.startPosition = new int[i2];
            System.arraycopy(iArr, 0, this.startPosition, 0, i2);
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getStartPosition(int i) {
            return this.startPosition[i - 1];
        }

        @Override // com.sun.source.tree.LineMap
        public long getStartPosition(long j) {
            return getStartPosition(longToInt(j));
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getPosition(int i, int i2) {
            return (this.startPosition[i - 1] + i2) - 1;
        }

        @Override // com.sun.source.tree.LineMap
        public long getPosition(long j, long j2) {
            return getPosition(longToInt(j), longToInt(j2));
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getLineNumber(int i) {
            if (i == this.lastPosition) {
                return this.lastLine;
            }
            this.lastPosition = i;
            int i2 = 0;
            int length = this.startPosition.length - 1;
            while (i2 <= length) {
                int i3 = (i2 + length) >> 1;
                int i4 = this.startPosition[i3];
                if (i4 < i) {
                    i2 = i3 + 1;
                } else if (i4 > i) {
                    length = i3 - 1;
                } else {
                    this.lastLine = i3 + 1;
                    return this.lastLine;
                }
            }
            this.lastLine = i2;
            return this.lastLine;
        }

        @Override // com.sun.source.tree.LineMap
        public long getLineNumber(long j) {
            return getLineNumber(longToInt(j));
        }

        @Override // com.sun.tools.javac.util.Position.LineMap
        public int getColumnNumber(int i) {
            return (i - this.startPosition[getLineNumber(i) - 1]) + 1;
        }

        @Override // com.sun.source.tree.LineMap
        public long getColumnNumber(long j) {
            return getColumnNumber(longToInt(j));
        }

        private static int longToInt(long j) {
            int i = (int) j;
            if (i != j) {
                throw new IndexOutOfBoundsException();
            }
            return i;
        }

        protected void setTabPosition(int i) {
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/Position$LineTabMapImpl.class */
    public static class LineTabMapImpl extends LineMapImpl {
        private BitSet tabMap;

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getColumnNumber(long j) {
            return super.getColumnNumber(j);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getLineNumber(long j) {
            return super.getLineNumber(j);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public /* bridge */ /* synthetic */ int getLineNumber(int i) {
            return super.getLineNumber(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getPosition(long j, long j2) {
            return super.getPosition(j, j2);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.source.tree.LineMap
        public /* bridge */ /* synthetic */ long getStartPosition(long j) {
            return super.getStartPosition(j);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public /* bridge */ /* synthetic */ int getStartPosition(int i) {
            return super.getStartPosition(i);
        }

        public LineTabMapImpl(int i) {
            this.tabMap = new BitSet(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl
        protected void setTabPosition(int i) {
            this.tabMap.set(i);
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public int getColumnNumber(int i) {
            int i2 = 0;
            for (int i3 = this.startPosition[getLineNumber(i) - 1]; i3 < i; i3++) {
                if (this.tabMap.get(i3)) {
                    i2 = ((i2 / 8) * 8) + 8;
                } else {
                    i2++;
                }
            }
            return i2 + 1;
        }

        @Override // com.sun.tools.javac.util.Position.LineMapImpl, com.sun.tools.javac.util.Position.LineMap
        public int getPosition(int i, int i2) {
            int i3 = this.startPosition[i - 1];
            int i4 = i2 - 1;
            int i5 = 0;
            while (i5 < i4) {
                i3++;
                if (this.tabMap.get(i3)) {
                    i5 = ((i5 / 8) * 8) + 8;
                } else {
                    i5++;
                }
            }
            return i3;
        }
    }
}
