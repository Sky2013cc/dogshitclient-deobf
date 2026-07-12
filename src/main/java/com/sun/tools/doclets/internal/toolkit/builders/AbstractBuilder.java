package com.sun.tools.doclets.internal.toolkit.builders;

import com.sun.tools.doclets.internal.toolkit.Configuration;
import com.sun.tools.doclets.internal.toolkit.Content;
import com.sun.tools.doclets.internal.toolkit.util.DocletAbortException;
import com.sun.tools.doclets.internal.toolkit.util.FatalError;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/builders/AbstractBuilder.class */
public abstract class AbstractBuilder {
    protected final Configuration configuration;
    protected final Set<String> containingPackagesSeen;
    protected final LayoutParser layoutParser;
    protected static final boolean DEBUG = false;

    public abstract String getName();

    public abstract void build() throws IOException;

    /* loaded from: target.jar:com/sun/tools/doclets/internal/toolkit/builders/AbstractBuilder$Context.class */
    public static class Context {
        final Configuration configuration;
        final Set<String> containingPackagesSeen;
        final LayoutParser layoutParser;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Context(Configuration configuration, Set<String> set, LayoutParser layoutParser) {
            this.configuration = configuration;
            this.containingPackagesSeen = set;
            this.layoutParser = layoutParser;
        }
    }

    public AbstractBuilder(Context context) {
        this.configuration = context.configuration;
        this.containingPackagesSeen = context.containingPackagesSeen;
        this.layoutParser = context.layoutParser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void build(XMLNode xMLNode, Content content) {
        String str = xMLNode.name;
        try {
            invokeMethod("build" + str, new Class[]{XMLNode.class, Content.class}, new Object[]{xMLNode, content});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            this.configuration.root.printError("Unknown element: " + str);
            throw new DocletAbortException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof FatalError) {
                throw ((FatalError) cause);
            }
            if (cause instanceof DocletAbortException) {
                throw ((DocletAbortException) cause);
            }
            throw new DocletAbortException(cause);
        } catch (Exception e3) {
            e3.printStackTrace();
            this.configuration.root.printError("Exception " + e3.getClass().getName() + " thrown while processing element: " + str);
            throw new DocletAbortException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void buildChildren(XMLNode xMLNode, Content content) {
        Iterator<XMLNode> it = xMLNode.children.iterator();
        while (it.hasNext()) {
            build(it.next(), content);
        }
    }

    protected void invokeMethod(String str, Class<?>[] clsArr, Object[] objArr) throws Exception {
        getClass().getMethod(str, clsArr).invoke(this, objArr);
    }
}
