package com.sun.tools.hat.internal.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/* loaded from: target.jar:com/sun/tools/hat/internal/model/ReachableExcludesImpl.class */
public class ReachableExcludesImpl implements ReachableExcludes {
    private File excludesFile;
    private long lastModified;
    private Hashtable methods;

    public ReachableExcludesImpl(File file) {
        this.excludesFile = file;
        readFile();
    }

    private void readFileIfNeeded() {
        if (this.excludesFile.lastModified() != this.lastModified) {
            synchronized (this) {
                if (this.excludesFile.lastModified() != this.lastModified) {
                    readFile();
                }
            }
        }
    }

    private void readFile() {
        long lastModified = this.excludesFile.lastModified();
        Hashtable hashtable = new Hashtable();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.excludesFile)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    hashtable.put(readLine, readLine);
                } else {
                    this.lastModified = lastModified;
                    this.methods = hashtable;
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + this.excludesFile + ":  " + e);
        }
    }

    @Override // com.sun.tools.hat.internal.model.ReachableExcludes
    public boolean isExcluded(String str) {
        readFileIfNeeded();
        return this.methods.get(str) != null;
    }
}
