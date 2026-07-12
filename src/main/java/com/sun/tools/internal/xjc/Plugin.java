package com.sun.tools.internal.xjc;

import com.sun.tools.internal.xjc.model.Model;
import com.sun.tools.internal.xjc.outline.Outline;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/Plugin.class */
public abstract class Plugin {
    public abstract String getOptionName();

    public abstract String getUsage();

    public abstract boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException;

    public int parseArgument(Options opt, String[] args, int i) throws BadCommandLineException, IOException {
        return 0;
    }

    public List<String> getCustomizationURIs() {
        return Collections.emptyList();
    }

    public boolean isCustomizationTagName(String nsUri, String localName) {
        return false;
    }

    public void onActivated(Options opts) throws BadCommandLineException {
    }

    public void postProcessModel(Model model, ErrorHandler errorHandler) {
    }
}
