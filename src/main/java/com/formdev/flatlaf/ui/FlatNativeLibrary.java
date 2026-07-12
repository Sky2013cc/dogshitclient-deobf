package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.NativeLibrary;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatNativeLibrary.class */
public class FlatNativeLibrary {
    private static boolean initialized;
    private static NativeLibrary nativeLibrary;

    private static native int getApiVersion();

    FlatNativeLibrary() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized boolean isLoaded(int apiVersion) {
        initialize(apiVersion);
        if (nativeLibrary != null) {
            return nativeLibrary.isLoaded();
        }
        return false;
    }

    private static void initialize(int apiVersion) {
        String classifier;
        String ext;
        if (initialized) {
            return;
        }
        initialized = true;
        if (!FlatSystemProperties.getBoolean(FlatSystemProperties.USE_NATIVE_LIBRARY, true)) {
            return;
        }
        if (SystemInfo.isWindows_10_orLater && (SystemInfo.isX86 || SystemInfo.isX86_64 || SystemInfo.isAARCH64)) {
            if (SystemInfo.isAARCH64) {
                classifier = "windows-arm64";
            } else if (SystemInfo.isX86_64) {
                classifier = "windows-x86_64";
            } else {
                classifier = "windows-x86";
            }
            ext = "dll";
        } else if (SystemInfo.isMacOS_10_14_Mojave_orLater && (SystemInfo.isAARCH64 || SystemInfo.isX86_64)) {
            classifier = SystemInfo.isAARCH64 ? "macos-arm64" : "macos-x86_64";
            ext = "dylib";
        } else if (SystemInfo.isLinux && SystemInfo.isX86_64) {
            classifier = "linux-x86_64";
            ext = "so";
            loadJAWT();
        } else {
            return;
        }
        NativeLibrary nativeLibrary2 = createNativeLibrary(classifier, ext);
        if (!nativeLibrary2.isLoaded()) {
            return;
        }
        try {
            int actualApiVersion = getApiVersion();
            if (actualApiVersion != apiVersion) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Wrong API version in native library (expected " + apiVersion + ", actual " + actualApiVersion + "). Ignoring native library.", null);
            } else {
                nativeLibrary = nativeLibrary2;
            }
        } catch (Throwable ex) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to get API version of native library. Ignoring native library.", ex);
        }
    }

    private static NativeLibrary createNativeLibrary(String classifier, String ext) {
        String libraryName = "flatlaf-" + classifier;
        String libraryPath = System.getProperty(FlatSystemProperties.NATIVE_LIBRARY_PATH);
        if (libraryPath != null) {
            if (Constants.ATTR_SYSTEM.equals(libraryPath)) {
                NativeLibrary library = new NativeLibrary(libraryName, true);
                if (library.isLoaded()) {
                    return library;
                }
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Native library '" + System.mapLibraryName(libraryName) + "' not found in java.library.path '" + System.getProperty("java.library.path") + "'. Using extracted native library instead.", null);
            } else {
                File libraryFile = new File(libraryPath, System.mapLibraryName(libraryName));
                if (libraryFile.exists()) {
                    return new NativeLibrary(libraryFile, true);
                }
                String libraryName2 = null;
                File jarFile = getJarFile();
                if (jarFile != null) {
                    libraryName2 = buildLibraryName(jarFile, classifier, ext);
                    File libraryFile2 = new File(libraryPath, libraryName2);
                    if (libraryFile2.exists()) {
                        return new NativeLibrary(libraryFile2, true);
                    }
                }
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Native library '" + libraryFile.getName() + (libraryName2 != null ? "' or '" + libraryName2 : "") + "' not found in '" + libraryFile.getParentFile().getAbsolutePath() + "'. Using extracted native library instead.", null);
            }
        }
        File libraryFile3 = findLibraryBesideJar(classifier, ext);
        if (libraryFile3 != null) {
            return new NativeLibrary(libraryFile3, true);
        }
        return new NativeLibrary("com/formdev/flatlaf/natives/" + libraryName, null, true);
    }

    private static File findLibraryBesideJar(String classifier, String ext) {
        File[] dirs;
        File jarFile = getJarFile();
        if (jarFile == null) {
            return null;
        }
        String libraryName = buildLibraryName(jarFile, classifier, ext);
        File jarDir = jarFile.getParentFile();
        File libraryFile = new File(jarDir, libraryName);
        if (libraryFile.isFile()) {
            return libraryFile;
        }
        if (jarDir.getName().equalsIgnoreCase("lib")) {
            File libraryFile2 = new File(jarDir.getParentFile(), "bin/" + libraryName);
            if (libraryFile2.isFile()) {
                return libraryFile2;
            }
        }
        String path = jarDir.getAbsolutePath().replace('\\', '/');
        if (path.contains("/.gradle/caches/")) {
            File versionDir = jarDir.getParentFile();
            if (libraryName.contains(versionDir.getName()) && (dirs = versionDir.listFiles()) != null) {
                for (File dir : dirs) {
                    File libraryFile3 = new File(dir, libraryName);
                    if (libraryFile3.isFile()) {
                        return libraryFile3;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private static File getJarFile() {
        try {
            CodeSource codeSource = FlatNativeLibrary.class.getProtectionDomain().getCodeSource();
            URL jarUrl = codeSource != null ? codeSource.getLocation() : null;
            if (jarUrl == null || !"file".equals(jarUrl.getProtocol())) {
                return null;
            }
            File jarFile = new File(jarUrl.toURI());
            if (!jarFile.isFile()) {
                return null;
            }
            return jarFile;
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
            return null;
        }
    }

    private static String buildLibraryName(File jarFile, String classifier, String ext) {
        String jarName = jarFile.getName();
        String jarBasename = StringUtils.removeTrailing(jarName.substring(0, jarName.lastIndexOf(46)), "-no-natives");
        return jarBasename + (jarBasename.contains("flatlaf") ? "" : "-flatlaf") + '-' + classifier + '.' + ext;
    }

    private static void loadJAWT() {
        try {
            System.loadLibrary("jawt");
        } catch (Exception ex) {
            LoggingFacade.INSTANCE.logSevere(ex.getMessage(), ex);
        } catch (UnsatisfiedLinkError ex2) {
            String message = ex2.getMessage();
            if (message == null || !message.contains("already loaded in another classloader")) {
                LoggingFacade.INSTANCE.logSevere(message, ex2);
            }
        }
    }
}
