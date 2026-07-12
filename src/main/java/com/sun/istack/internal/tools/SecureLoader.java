package com.sun.istack.internal.tools;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: target.jar:com/sun/istack/internal/tools/SecureLoader.class */
class SecureLoader {
    SecureLoader() {
    }

    static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.istack.internal.tools.SecureLoader.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }

    static ClassLoader getClassClassLoader(final Class c) {
        if (System.getSecurityManager() == null) {
            return c.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.istack.internal.tools.SecureLoader.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return c.getClassLoader();
            }
        });
    }

    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.istack.internal.tools.SecureLoader.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }

    static ClassLoader getParentClassLoader(final ClassLoader cl) {
        if (System.getSecurityManager() == null) {
            return cl.getParent();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.istack.internal.tools.SecureLoader.4
            @Override // java.security.PrivilegedAction
            public Object run() {
                return cl.getParent();
            }
        });
    }

    static void setContextClassLoader(final ClassLoader cl) {
        if (System.getSecurityManager() == null) {
            Thread.currentThread().setContextClassLoader(cl);
        } else {
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.sun.istack.internal.tools.SecureLoader.5
                @Override // java.security.PrivilegedAction
                public Object run() {
                    Thread.currentThread().setContextClassLoader(cl);
                    return null;
                }
            });
        }
    }
}
