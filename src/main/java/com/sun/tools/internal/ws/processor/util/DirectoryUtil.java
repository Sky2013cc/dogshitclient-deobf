package com.sun.tools.internal.ws.processor.util;

import com.sun.tools.internal.ws.processor.generator.GeneratorException;
import com.sun.tools.internal.ws.util.ClassNameInfo;
import java.io.File;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/util/DirectoryUtil.class */
public class DirectoryUtil {
    public static File getOutputDirectoryFor(String theClass, File rootDir) throws GeneratorException {
        File outputDir;
        String packagePath = null;
        String packageName = ClassNameInfo.getQualifier(theClass);
        if (packageName != null && packageName.length() > 0) {
            packagePath = packageName.replace('.', File.separatorChar);
        }
        if (rootDir != null) {
            if (packagePath != null) {
                outputDir = new File(rootDir, packagePath);
                ensureDirectory(outputDir);
            } else {
                outputDir = rootDir;
            }
        } else {
            String workingDirPath = System.getProperty("user.dir");
            File workingDir = new File(workingDirPath);
            if (packagePath == null) {
                outputDir = workingDir;
            } else {
                outputDir = new File(workingDir, packagePath);
                ensureDirectory(outputDir);
            }
        }
        return outputDir;
    }

    public static String getRelativePathfromCommonBase(File file, File base) throws IOException {
        String basePath = base.getCanonicalPath();
        String filePath = file.getCanonicalPath();
        return filePath.substring(basePath.length());
    }

    private static void ensureDirectory(File dir) throws GeneratorException {
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created || !dir.exists()) {
                throw new GeneratorException("generator.cannot.create.dir", dir.getAbsolutePath());
            }
        }
    }
}
