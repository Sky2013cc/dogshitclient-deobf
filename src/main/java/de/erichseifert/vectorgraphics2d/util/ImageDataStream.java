package de.erichseifert.vectorgraphics2d.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/ImageDataStream.class */
public class ImageDataStream extends InputStream {
    private final BufferedImage a;
    private final int b;
    private final int c;
    private final Interleaving d;
    private final Raster e;
    private final boolean f;
    private final Queue<Integer> g;
    private final int[] h;
    private final int[] i;
    private int j = -1;
    private int k = 0;

    /* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/ImageDataStream$Interleaving.class */
    public enum Interleaving {
        SAMPLE,
        ROW,
        WITHOUT_ALPHA,
        ALPHA_ONLY
    }

    public ImageDataStream(BufferedImage bufferedImage, Interleaving interleaving) {
        this.a = bufferedImage;
        this.d = interleaving;
        this.b = bufferedImage.getWidth();
        this.c = bufferedImage.getHeight();
        WritableRaster alphaRaster = bufferedImage.getAlphaRaster();
        if (interleaving == Interleaving.ALPHA_ONLY) {
            this.e = alphaRaster;
        } else {
            this.e = bufferedImage.getRaster();
        }
        this.f = alphaRaster == null;
        this.g = new LinkedList();
        this.h = new int[this.e.getNumBands()];
        this.i = this.e.getSampleModel().getSampleSize();
    }

    public BufferedImage getImage() {
        return this.a;
    }

    public Interleaving getInterleaving() {
        return this.d;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        boolean z;
        if (!this.g.isEmpty()) {
            return this.g.poll().intValue();
        }
        if (this.d == Interleaving.SAMPLE || this.d == Interleaving.WITHOUT_ALPHA) {
            this.j++;
            if (this.j >= this.b) {
                this.j = 0;
                this.k++;
            }
        }
        if (this.j >= 0 && this.j < this.b && this.k >= 0 && this.k < this.c) {
            this.e.getPixel(this.j, this.k, this.h);
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return -1;
        }
        int length = this.h.length;
        if (this.d == Interleaving.WITHOUT_ALPHA || this.d == Interleaving.ALPHA_ONLY) {
            if (this.d == Interleaving.WITHOUT_ALPHA && !this.f) {
                length--;
            }
            for (int i = 0; i < length; i++) {
                a(i);
            }
        } else if (this.f) {
            for (int i2 = 0; i2 < length; i2++) {
                a(i2);
            }
        } else {
            for (int i3 = 0; i3 < length; i3++) {
                if (i3 == 0) {
                    a(length - 1);
                } else {
                    a(i3 - 1);
                }
            }
        }
        if (!this.g.isEmpty()) {
            return this.g.poll().intValue();
        }
        return -1;
    }

    private void a(int i) {
        if (this.i[i] < 8) {
            this.g.offer(Integer.valueOf(this.h[i] & 255));
            return;
        }
        for (int i2 = (this.i[i] / 8) - 1; i2 >= 0; i2--) {
            this.g.offer(Integer.valueOf((this.h[i] >> (i2 << 3)) & 255));
        }
    }
}
