package com.sun.tools.internal.xjc.runtime;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/internal/xjc/runtime/JAXBContextFactory.class */
public class JAXBContextFactory {
    private static final String DOT_OBJECT_FACTORY = ".ObjectFactory";
    private static final String IMPL_DOT_OBJECT_FACTORY = ".impl.ObjectFactory";

    public static JAXBContext createContext(Class[] classes, Map properties) throws JAXBException {
        Class[] r = new Class[classes.length];
        boolean modified = false;
        for (int i = 0; i < r.length; i++) {
            Class c = classes[i];
            String name = c.getName();
            if (name.endsWith(DOT_OBJECT_FACTORY) && !name.endsWith(IMPL_DOT_OBJECT_FACTORY)) {
                try {
                    c = getClassClassLoader(c).loadClass(name.substring(0, name.length() - DOT_OBJECT_FACTORY.length()) + IMPL_DOT_OBJECT_FACTORY);
                    modified = true;
                } catch (ClassNotFoundException e) {
                    throw new JAXBException(e);
                }
            }
            r[i] = c;
        }
        if (!modified) {
            throw new JAXBException("Unable to find a JAXB implementation to delegate");
        }
        return JAXBContext.newInstance(r, properties);
    }

    public static JAXBContext createContext(String contextPath, ClassLoader classLoader, Map properties) throws JAXBException {
        List<Class> classes = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(contextPath, CallSiteDescriptor.TOKEN_DELIMITER);
        while (tokens.hasMoreTokens()) {
            try {
                String pkg = tokens.nextToken();
                classes.add(classLoader.loadClass(pkg + IMPL_DOT_OBJECT_FACTORY));
            } catch (ClassNotFoundException e) {
                throw new JAXBException(e);
            }
        }
        return JAXBContext.newInstance((Class[]) classes.toArray(new Class[classes.size()]), properties);
    }

    private static ClassLoader getClassClassLoader(final Class c) {
        if (System.getSecurityManager() == null) {
            return c.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.tools.internal.xjc.runtime.JAXBContextFactory.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return c.getClassLoader();
            }
        });
    }
}
