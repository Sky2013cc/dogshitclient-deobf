package sun.rmi.rmic.iiop;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import sun.tools.java.ClassFile;
import sun.tools.java.ClassPath;

/* loaded from: target.jar:sun/rmi/rmic/iiop/ClassPathLoader.class */
public class ClassPathLoader extends ClassLoader {
    private ClassPath classPath;

    public ClassPathLoader(ClassPath classPath) {
        this.classPath = classPath;
    }

    @Override // java.lang.ClassLoader
    protected Class findClass(String str) throws ClassNotFoundException {
        byte[] loadClassData = loadClassData(str);
        return defineClass(str, loadClassData, 0, loadClassData.length);
    }

    private byte[] loadClassData(String str) throws ClassNotFoundException {
        ClassFile file = this.classPath.getFile(str.replace('.', File.separatorChar) + ".class");
        if (file != null) {
            IOException iOException = null;
            byte[] bArr = null;
            try {
                DataInputStream dataInputStream = new DataInputStream(file.getInputStream());
                bArr = new byte[(int) file.length()];
                try {
                    dataInputStream.readFully(bArr);
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                    }
                } catch (IOException e2) {
                    bArr = null;
                    iOException = e2;
                    try {
                        dataInputStream.close();
                    } catch (IOException e3) {
                    }
                } catch (Throwable th) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e4) {
                    }
                    throw th;
                }
            } catch (IOException e5) {
                iOException = e5;
            }
            if (bArr == null) {
                throw new ClassNotFoundException(str, iOException);
            }
            return bArr;
        }
        throw new ClassNotFoundException(str);
    }
}
