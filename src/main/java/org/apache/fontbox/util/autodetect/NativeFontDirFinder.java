package org.apache.fontbox.util.autodetect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/fontbox/util/autodetect/NativeFontDirFinder.class */
public abstract class NativeFontDirFinder implements FontDirFinder {
    private static final Log LOG = LogFactory.getLog(NativeFontDirFinder.class);

    protected abstract String[] getSearchableDirectories();

    @Override // org.apache.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        List<File> fontDirList = new ArrayList<>();
        String[] searchableDirectories = getSearchableDirectories();
        if (searchableDirectories != null) {
            for (String searchableDirectorie : searchableDirectories) {
                File fontDir = new File(searchableDirectorie);
                try {
                    if (fontDir.exists() && fontDir.canRead()) {
                        fontDirList.add(fontDir);
                    }
                } catch (SecurityException e) {
                    LOG.debug("Couldn't get native font directories - ignoring", e);
                }
            }
        }
        return fontDirList;
    }
}
