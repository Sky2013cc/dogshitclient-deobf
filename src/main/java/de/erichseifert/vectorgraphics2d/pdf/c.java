package de.erichseifert.vectorgraphics2d.pdf;

import de.erichseifert.vectorgraphics2d.util.FlateEncodeStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/c.class */
final class c extends OutputStream {
    private final ByteArrayOutputStream a = new ByteArrayOutputStream();
    private OutputStream b = this.a;
    private boolean c = true;

    public final byte[] a() {
        return this.a.toByteArray();
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        this.b.write(i);
        this.c = false;
    }

    public final boolean b() {
        return this.c;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        this.b.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.ReflectiveOperationException] */
    /* JADX WARN: Type inference failed for: r0v3, types: [de.erichseifert.vectorgraphics2d.pdf.c] */
    public final void a(Class<FlateEncodeStream> cls) {
        ?? r0 = this.c;
        if (r0 == 0) {
            throw new IllegalStateException("Cannot add filter after writing to payload.");
        }
        try {
            r0 = this;
            r0.b = cls.getConstructor(OutputStream.class).newInstance(this.b);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            r0.printStackTrace();
        }
    }
}
