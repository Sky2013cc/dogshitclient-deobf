package com.sun.tools.internal.jxc;

import com.sun.tools.internal.jxc.gen.config.NGCCRuntime;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/jxc/NGCCRuntimeEx.class */
public final class NGCCRuntimeEx extends NGCCRuntime {
    private final ErrorHandler errorHandler;

    public NGCCRuntimeEx(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public File getBaseDir(String baseDir) throws SAXException {
        File dir = new File(baseDir);
        if (dir.exists()) {
            return dir;
        }
        SAXParseException e = new SAXParseException(Messages.BASEDIR_DOESNT_EXIST.format(dir.getAbsolutePath()), getLocator());
        this.errorHandler.error(e);
        throw e;
    }

    public List<Pattern> getIncludePatterns(List<String> includeContent) {
        List<Pattern> includeRegexList = new ArrayList<>();
        for (String includes : includeContent) {
            String regex = convertToRegex(includes);
            Pattern pattern = Pattern.compile(regex);
            includeRegexList.add(pattern);
        }
        return includeRegexList;
    }

    public List getExcludePatterns(List<String> excludeContent) {
        List<Pattern> excludeRegexList = new ArrayList<>();
        for (String excludes : excludeContent) {
            String regex = convertToRegex(excludes);
            Pattern pattern = Pattern.compile(regex);
            excludeRegexList.add(pattern);
        }
        return excludeRegexList;
    }

    private String convertToRegex(String pattern) {
        StringBuilder regex = new StringBuilder();
        if (pattern.length() > 0) {
            int i = 0;
            while (true) {
                if (i >= pattern.length()) {
                    break;
                }
                char c = pattern.charAt(i);
                char nc = ' ';
                if (i + 1 != pattern.length()) {
                    nc = pattern.charAt(i + 1);
                }
                if (c == '.' && nc != '.') {
                    regex.append('\\');
                    regex.append('.');
                } else if (c == '.') {
                    continue;
                } else {
                    if (c == '*' && nc == '*') {
                        regex.append(".*");
                        break;
                    }
                    if (c == '*') {
                        regex.append("[^\\.]+");
                    } else if (c == '?') {
                        regex.append("[^\\.]");
                    } else {
                        regex.append(c);
                    }
                }
                i++;
            }
        }
        return regex.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.tools.internal.jxc.gen.config.NGCCRuntime
    public void unexpectedX(String token) throws SAXException {
        this.errorHandler.error(new SAXParseException(Messages.UNEXPECTED_NGCC_TOKEN.format(token, Integer.valueOf(getLocator().getLineNumber()), Integer.valueOf(getLocator().getColumnNumber())), getLocator()));
    }
}
