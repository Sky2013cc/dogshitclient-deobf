package com.formdev.flatlaf.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/formdev/flatlaf/util/NativeLibrary.class */
public class NativeLibrary {
    private static final String DELETE_SUFFIX = ".delete";
    private static boolean deletedTemporary;
    private final boolean loaded;

    public NativeLibrary(String libraryName, ClassLoader classLoader, boolean supported) {
        boolean z;
        if (supported) {
            z = loadLibraryFromJar(libraryName, classLoader);
        } else {
            z = false;
        }
        this.loaded = z;
    }

    public NativeLibrary(File libraryFile, boolean supported) {
        boolean z;
        if (supported) {
            z = loadLibraryFromFile(libraryFile);
        } else {
            z = false;
        }
        this.loaded = z;
    }

    public NativeLibrary(String libraryName, boolean supported) {
        boolean z;
        if (supported) {
            z = loadLibraryFromSystem(libraryName);
        } else {
            z = false;
        }
        this.loaded = z;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    private static boolean loadLibraryFromJar(String libraryName, ClassLoader classLoader) {
        URL resource;
        String libraryName2 = decorateLibraryName(libraryName);
        if (classLoader != null) {
            resource = classLoader.getResource(libraryName2);
        } else {
            resource = NativeLibrary.class.getResource(RuntimeConstants.SIG_PACKAGE + libraryName2);
        }
        URL libraryUrl = resource;
        if (libraryUrl == null) {
            LoggingFacade.INSTANCE.logSevere("Library '" + libraryName2 + "' not found", null);
            return false;
        }
        File tempFile = null;
        try {
            if ("file".equals(libraryUrl.getProtocol())) {
                String binPath = libraryUrl.getPath();
                String srcPath = binPath.replace("flatlaf-core/bin/main/", "flatlaf-core/src/main/resources/");
                File libraryFile = new File(srcPath);
                if (!libraryFile.isFile()) {
                    libraryFile = new File(binPath);
                }
                if (libraryFile.isFile()) {
                    System.load(libraryFile.getCanonicalPath());
                    return true;
                }
            }
            Path tempPath = createTempFile(libraryName2);
            tempFile = tempPath.toFile();
            InputStream in = libraryUrl.openStream();
            try {
                Files.copy(in, tempPath, StandardCopyOption.REPLACE_EXISTING);
                if (in != null) {
                    in.close();
                }
                System.load(tempFile.getCanonicalPath());
                deleteOrMarkForDeletion(tempFile);
                return true;
            } finally {
            }
        } catch (Throwable ex) {
            LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
            if (tempFile != null) {
                deleteOrMarkForDeletion(tempFile);
                return false;
            }
            return false;
        }
    }

    private boolean loadLibraryFromFile(File libraryFile) {
        try {
            System.load(libraryFile.getAbsolutePath());
            return true;
        } catch (Throwable ex) {
            LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
            return false;
        }
    }

    private boolean loadLibraryFromSystem(String libraryName) {
        try {
            System.loadLibrary(libraryName);
            return true;
        } catch (Throwable ex) {
            String message = ex.getMessage();
            if ((ex instanceof UnsatisfiedLinkError) && message != null && message.contains("java.library.path")) {
                return false;
            }
            LoggingFacade.INSTANCE.logSevere(message, ex);
            return false;
        }
    }

    private static String decorateLibraryName(String libraryName) {
        int sep = libraryName.lastIndexOf(47);
        if (sep >= 0) {
            return libraryName.substring(0, sep + 1) + System.mapLibraryName(libraryName.substring(sep + 1));
        }
        return System.mapLibraryName(libraryName);
    }

    private static Path createTempFile(String libraryName) throws IOException {
        int sep = libraryName.lastIndexOf(47);
        String name = sep >= 0 ? libraryName.substring(sep + 1) : libraryName;
        int dot = name.lastIndexOf(46);
        String prefix = (dot >= 0 ? name.substring(0, dot) : name) + '-';
        String suffix = dot >= 0 ? name.substring(dot) : "";
        Path tempDir = getTempDir();
        long nanoTime = System.nanoTime();
        int i = 0;
        while (true) {
            String s = prefix + Long.toUnsignedString(nanoTime) + i + suffix;
            try {
                return Files.createFile(tempDir.resolve(s), new FileAttribute[0]);
            } catch (FileAlreadyExistsException e) {
                i++;
            }
        }
    }

    private static Path getTempDir() throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        if (SystemInfo.isWindows) {
            tmpdir = tmpdir + "\\flatlaf.temp";
        }
        Path tempDir = Paths.get(tmpdir, new String[0]);
        Files.createDirectories(tempDir, new FileAttribute[0]);
        if (SystemInfo.isWindows) {
            deleteTemporaryFiles(tempDir);
        }
        return tempDir;
    }

    private static void deleteTemporaryFiles(Path tempDir) {
        if (deletedTemporary) {
            return;
        }
        deletedTemporary = true;
        File[] markerFiles = tempDir.toFile().listFiles((dir, name) -> {
            return name.endsWith(DELETE_SUFFIX);
        });
        if (markerFiles == null) {
            return;
        }
        for (File markerFile : markerFiles) {
            File toDeleteFile = new File(markerFile.getParent(), StringUtils.removeTrailing(markerFile.getName(), DELETE_SUFFIX));
            if (!toDeleteFile.exists() || toDeleteFile.delete()) {
                markerFile.delete();
            }
        }
    }

    private static void deleteOrMarkForDeletion(File file) {
        if (file.delete()) {
            return;
        }
        try {
            File markFile = new File(file.getParent(), file.getName() + DELETE_SUFFIX);
            markFile.createNewFile();
        } catch (IOException e) {
        }
    }
}
