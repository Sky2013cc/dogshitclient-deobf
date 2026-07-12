package com.sun.codemodel.internal.fmt;

import com.sun.codemodel.internal.JResourceFile;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/codemodel/internal/fmt/JStaticFile.class */
public final class JStaticFile extends JResourceFile {
    private final ClassLoader classLoader;
    private final String resourceName;
    private final boolean isResource;

    public JStaticFile(String _resourceName) {
        this(_resourceName, !_resourceName.endsWith(Constants.SOURCE_FILE_EXTENSION));
    }

    public JStaticFile(String _resourceName, boolean isResource) {
        this(SecureLoader.getClassClassLoader(JStaticFile.class), _resourceName, isResource);
    }

    public JStaticFile(ClassLoader _classLoader, String _resourceName, boolean isResource) {
        super(_resourceName.substring(_resourceName.lastIndexOf(47) + 1));
        this.classLoader = _classLoader;
        this.resourceName = _resourceName;
        this.isResource = isResource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.codemodel.internal.JResourceFile
    public boolean isResource() {
        return this.isResource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.codemodel.internal.JResourceFile
    public void build(OutputStream os) throws IOException {
        DataInputStream dis = new DataInputStream(this.classLoader.getResourceAsStream(this.resourceName));
        byte[] buf = new byte[256];
        while (true) {
            int sz = dis.read(buf);
            if (sz > 0) {
                os.write(buf, 0, sz);
            } else {
                dis.close();
                return;
            }
        }
    }
}
