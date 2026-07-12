package com.sun.tools.example.debug.tty;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/example/debug/tty/SourceMapper.class */
public class SourceMapper {
    private final String[] dirs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceMapper(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.endsWith(".jar") && !str.endsWith(".zip")) {
                arrayList.add(str);
            }
        }
        this.dirs = (String[]) arrayList.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceMapper(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, File.pathSeparator);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!nextToken.endsWith(".jar") && !nextToken.endsWith(".zip")) {
                arrayList.add(nextToken);
            }
        }
        this.dirs = (String[]) arrayList.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSourcePath() {
        if (this.dirs.length >= 1) {
            StringBuffer stringBuffer = new StringBuffer(this.dirs[0]);
            for (int i = 0 + 1; i < this.dirs.length; i++) {
                stringBuffer.append(File.pathSeparator);
                stringBuffer.append(this.dirs[i]);
            }
            return stringBuffer.toString();
        }
        return "";
    }

    File sourceFile(Location location) {
        try {
            String sourceName = location.sourceName();
            String name = location.declaringType().name();
            int lastIndexOf = name.lastIndexOf(46);
            String str = (lastIndexOf >= 0 ? name.substring(0, lastIndexOf + 1) : "").replace('.', File.separatorChar) + sourceName;
            for (int i = 0; i < this.dirs.length; i++) {
                File file = new File(this.dirs[i], str);
                if (file.exists()) {
                    return file;
                }
            }
            return null;
        } catch (AbsentInformationException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BufferedReader sourceReader(Location location) {
        File sourceFile = sourceFile(location);
        if (sourceFile == null) {
            return null;
        }
        try {
            return new BufferedReader(new FileReader(sourceFile));
        } catch (IOException e) {
            return null;
        }
    }
}
