package org.spongepowered.asm.lib;

/* loaded from: target.jar:org/spongepowered/asm/lib/Label.class */
public class Label {
    static final int DEBUG = 1;
    static final int RESOLVED = 2;
    static final int RESIZED = 4;
    static final int PUSHED = 8;
    static final int TARGET = 16;
    static final int STORE = 32;
    static final int REACHABLE = 64;
    static final int JSR = 128;
    static final int RET = 256;
    static final int SUBROUTINE = 512;
    static final int VISITED = 1024;
    static final int VISITED2 = 2048;
    public Object info;
    int status;
    int line;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int inputStackTop;
    int outputStackMax;
    Frame frame;
    Label successor;
    Edge successors;
    Label next;

    public int getOffset() {
        if ((this.status & 2) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(MethodWriter owner, ByteVector out, int source, boolean wideOffset) {
        if ((this.status & 2) == 0) {
            if (wideOffset) {
                addReference((-1) - source, out.length);
                out.putInt(-1);
                return;
            } else {
                addReference(source, out.length);
                out.putShort(-1);
                return;
            }
        }
        if (wideOffset) {
            out.putInt(this.position - source);
        } else {
            out.putShort(this.position - source);
        }
    }

    private void addReference(int sourcePosition, int referencePosition) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            int[] a = new int[this.srcAndRefPositions.length + 6];
            System.arraycopy(this.srcAndRefPositions, 0, a, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = a;
        }
        int[] iArr = this.srcAndRefPositions;
        int i = this.referenceCount;
        this.referenceCount = i + 1;
        iArr[i] = sourcePosition;
        int[] iArr2 = this.srcAndRefPositions;
        int i2 = this.referenceCount;
        this.referenceCount = i2 + 1;
        iArr2[i2] = referencePosition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean resolve(MethodWriter owner, int position, byte[] data) {
        boolean needUpdate = false;
        this.status |= 2;
        this.position = position;
        int i = 0;
        while (i < this.referenceCount) {
            int i2 = i;
            int i3 = i + 1;
            int source = this.srcAndRefPositions[i2];
            i = i3 + 1;
            int reference = this.srcAndRefPositions[i3];
            if (source >= 0) {
                int offset = position - source;
                if (offset < -32768 || offset > 32767) {
                    int opcode = data[reference - 1] & 255;
                    if (opcode <= 168) {
                        data[reference - 1] = (byte) (opcode + 49);
                    } else {
                        data[reference - 1] = (byte) (opcode + 20);
                    }
                    needUpdate = true;
                }
                data[reference] = (byte) (offset >>> 8);
                data[reference + 1] = (byte) offset;
            } else {
                int offset2 = position + source + 1;
                int reference2 = reference + 1;
                data[reference] = (byte) (offset2 >>> 24);
                int reference3 = reference2 + 1;
                data[reference2] = (byte) (offset2 >>> 16);
                data[reference3] = (byte) (offset2 >>> 8);
                data[reference3 + 1] = (byte) offset2;
            }
        }
        return needUpdate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Label getFirst() {
        return this.frame == null ? this : this.frame.owner;
    }

    boolean inSubroutine(long id) {
        return ((this.status & 1024) == 0 || (this.srcAndRefPositions[(int) (id >>> 32)] & ((int) id)) == 0) ? false : true;
    }

    boolean inSameSubroutine(Label block) {
        if ((this.status & 1024) == 0 || (block.status & 1024) == 0) {
            return false;
        }
        for (int i = 0; i < this.srcAndRefPositions.length; i++) {
            if ((this.srcAndRefPositions[i] & block.srcAndRefPositions[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    void addToSubroutine(long id, int nbSubroutines) {
        if ((this.status & 1024) == 0) {
            this.status |= 1024;
            this.srcAndRefPositions = new int[(nbSubroutines / 32) + 1];
        }
        int[] iArr = this.srcAndRefPositions;
        int i = (int) (id >>> 32);
        iArr[i] = iArr[i] | ((int) id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitSubroutine(Label JSR2, long id, int nbSubroutines) {
        Edge edge;
        Edge e;
        Label stack = this;
        while (stack != null) {
            Label l = stack;
            stack = l.next;
            l.next = null;
            if (JSR2 != null) {
                if ((l.status & 2048) == 0) {
                    l.status |= 2048;
                    if ((l.status & 256) != 0 && !l.inSameSubroutine(JSR2)) {
                        Edge e2 = new Edge();
                        e2.info = l.inputStackTop;
                        e2.successor = JSR2.successors.successor;
                        e2.next = l.successors;
                        l.successors = e2;
                    }
                    edge = l.successors;
                    while (true) {
                        e = edge;
                        if (e == null) {
                            if (((l.status & 128) == 0 || e != l.successors.next) && e.successor.next == null) {
                                e.successor.next = stack;
                                stack = e.successor;
                            }
                            edge = e.next;
                        }
                    }
                }
            } else if (!l.inSubroutine(id)) {
                l.addToSubroutine(id, nbSubroutines);
                edge = l.successors;
                while (true) {
                    e = edge;
                    if (e == null) {
                        break;
                    }
                    edge = e.next;
                }
            }
        }
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}
