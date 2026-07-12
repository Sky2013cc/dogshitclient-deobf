package com.sun.tools.jdi;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/jdi/Packet.class */
public class Packet {
    public static final short NoFlags = 0;
    public static final short Reply = 128;
    public static final short ReplyNoError = 0;
    static int uID = 1;
    static final byte[] nullData = new byte[0];
    short cmdSet;
    short cmd;
    short errorCode;
    volatile boolean replied = false;
    int id = uniqID();
    short flags = 0;
    byte[] data = nullData;

    public byte[] toByteArray() {
        int length = this.data.length + 11;
        byte[] bArr = new byte[length];
        bArr[0] = (byte) ((length >>> 24) & 255);
        bArr[1] = (byte) ((length >>> 16) & 255);
        bArr[2] = (byte) ((length >>> 8) & 255);
        bArr[3] = (byte) ((length >>> 0) & 255);
        bArr[4] = (byte) ((this.id >>> 24) & 255);
        bArr[5] = (byte) ((this.id >>> 16) & 255);
        bArr[6] = (byte) ((this.id >>> 8) & 255);
        bArr[7] = (byte) ((this.id >>> 0) & 255);
        bArr[8] = (byte) this.flags;
        if ((this.flags & 128) == 0) {
            bArr[9] = (byte) this.cmdSet;
            bArr[10] = (byte) this.cmd;
        } else {
            bArr[9] = (byte) ((this.errorCode >>> 8) & 255);
            bArr[10] = (byte) ((this.errorCode >>> 0) & 255);
        }
        if (this.data.length > 0) {
            System.arraycopy(this.data, 0, bArr, 11, this.data.length);
        }
        return bArr;
    }

    public static Packet fromByteArray(byte[] bArr) throws IOException {
        if (bArr.length < 11) {
            throw new IOException("packet is insufficient size");
        }
        if ((((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | ((bArr[3] & 255) << 0)) != bArr.length) {
            throw new IOException("length size mis-match");
        }
        int i = bArr[4] & 255;
        int i2 = bArr[5] & 255;
        int i3 = bArr[6] & 255;
        int i4 = bArr[7] & 255;
        Packet packet = new Packet();
        packet.id = (i << 24) | (i2 << 16) | (i3 << 8) | (i4 << 0);
        packet.flags = (short) (bArr[8] & 255);
        if ((packet.flags & 128) == 0) {
            packet.cmdSet = (short) (bArr[9] & 255);
            packet.cmd = (short) (bArr[10] & 255);
        } else {
            packet.errorCode = (short) ((((short) (bArr[9] & 255)) << 8) + (((short) (bArr[10] & 255)) << 0));
        }
        packet.data = new byte[bArr.length - 11];
        System.arraycopy(bArr, 11, packet.data, 0, packet.data.length);
        return packet;
    }

    private static synchronized int uniqID() {
        int i = uID;
        uID = i + 1;
        return i;
    }
}
