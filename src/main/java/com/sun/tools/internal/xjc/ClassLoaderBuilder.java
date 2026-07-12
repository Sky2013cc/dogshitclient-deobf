package com.sun.tools.internal.xjc;

import com.sun.istack.internal.tools.MaskingClassLoader;
import com.sun.istack.internal.tools.ParallelWorldClassLoader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBContext;

/* loaded from: target.jar:com/sun/tools/internal/xjc/ClassLoaderBuilder.class */
class ClassLoaderBuilder {
    private static String[] maskedPackages = {"com.sun.tools.", "com.sun.codemodel.internal.", "com.sun.relaxng.", "com.sun.xml.internal.xsom.", "com.sun.xml.internal.bind."};
    private static String[] toolPackages = {"com.sun.tools.", "com.sun.codemodel.internal.", "com.sun.relaxng.", "com.sun.xml.internal.xsom."};
    public static final boolean noHack = Boolean.getBoolean(XJCFacade.class.getName() + ".nohack");

    ClassLoaderBuilder() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader createProtectiveClassLoader(ClassLoader cl, String v) throws ClassNotFoundException, MalformedURLException {
        if (noHack) {
            return cl;
        }
        boolean mustang = false;
        if (SecureLoader.getClassClassLoader(JAXBContext.class) == null) {
            mustang = true;
            List<String> mask = new ArrayList<>(Arrays.asList(maskedPackages));
            mask.add("javax.xml.bind.");
            ClassLoader cl2 = new MaskingClassLoader(cl, mask);
            URL apiUrl = cl2.getResource("javax/xml/bind/JAXBPermission.class");
            if (apiUrl == null) {
                throw new ClassNotFoundException("There's no JAXB 2.2 API in the classpath");
            }
            cl = new URLClassLoader(new URL[]{ParallelWorldClassLoader.toJarUrl(apiUrl)}, cl2);
        }
        if ("1.0".equals(v)) {
            if (!mustang) {
                cl = new MaskingClassLoader(cl, toolPackages);
            }
            cl = new ParallelWorldClassLoader(cl, "1.0/");
        } else if (mustang) {
            cl = new ParallelWorldClassLoader(cl, "");
        }
        return cl;
    }
}
