package sun.rmi.rmic.iiop;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

/* loaded from: target.jar:sun/rmi/rmic/iiop/DirectoryLoader.class */
public class DirectoryLoader extends ClassLoader {
    private Hashtable cache;
    private File root;

    public DirectoryLoader(File file) {
        this.cache = new Hashtable();
        if (file == null || !file.isDirectory()) {
            throw new IllegalArgumentException();
        }
        this.root = file;
    }

    private DirectoryLoader() {
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String str) throws ClassNotFoundException {
        return loadClass(str, true);
    }

    @Override // java.lang.ClassLoader
    public synchronized Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls = (Class) this.cache.get(str);
        if (cls == null) {
            try {
                cls = super.findSystemClass(str);
            } catch (ClassNotFoundException e) {
                byte[] classFileData = getClassFileData(str);
                if (classFileData == null) {
                    throw new ClassNotFoundException();
                }
                cls = defineClass(classFileData, 0, classFileData.length);
                if (cls == null) {
                    throw new ClassFormatError();
                }
                if (z) {
                    resolveClass(cls);
                }
                this.cache.put(str, cls);
            }
        }
        return cls;
    }

    private byte[] getClassFileData(String str) {
        byte[] bArr = null;
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File(this.root, str.replace('.', File.separatorChar) + ".class"));
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (ThreadDeath e) {
                        throw e;
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (ThreadDeath e2) {
                        throw e2;
                    } catch (Throwable th3) {
                    }
                }
                throw th2;
            }
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable th4) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (ThreadDeath e4) {
                    throw e4;
                } catch (Throwable th5) {
                }
            }
        }
        return bArr;
    }
}
