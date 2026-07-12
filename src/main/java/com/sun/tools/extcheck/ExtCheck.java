package com.sun.tools.extcheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import sun.net.www.ParseUtil;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/extcheck/ExtCheck.class */
public class ExtCheck {
    private static final boolean DEBUG = false;
    private String targetSpecTitle;
    private String targetSpecVersion;
    private String targetSpecVendor;
    private String targetImplTitle;
    private String targetImplVersion;
    private String targetImplVendor;
    private String targetsealed;
    private boolean verboseFlag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtCheck create(File file, boolean z) {
        return new ExtCheck(file, z);
    }

    private ExtCheck(File file, boolean z) {
        this.verboseFlag = z;
        investigateTarget(file);
    }

    private void investigateTarget(File file) {
        verboseMessage("Target file:" + file);
        Manifest manifest = null;
        try {
            URL fileToEncodedURL = ParseUtil.fileToEncodedURL(new File(file.getCanonicalPath()));
            if (fileToEncodedURL != null) {
                manifest = new JarLoader(fileToEncodedURL).getJarFile().getManifest();
            }
        } catch (MalformedURLException e) {
            error("Malformed URL ");
        } catch (IOException e2) {
            error("IO Exception ");
        }
        if (manifest == null) {
            error("No manifest available in " + file);
        }
        Attributes mainAttributes = manifest.getMainAttributes();
        if (mainAttributes != null) {
            this.targetSpecTitle = mainAttributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            this.targetSpecVersion = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            this.targetSpecVendor = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            this.targetImplTitle = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            this.targetImplVersion = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            this.targetImplVendor = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            this.targetsealed = mainAttributes.getValue(Attributes.Name.SEALED);
        } else {
            error("No attributes available in the manifest");
        }
        if (this.targetSpecTitle == null) {
            error("The target file does not have a specification title");
        }
        if (this.targetSpecVersion == null) {
            error("The target file does not have a specification version");
        }
        verboseMessage("Specification title:" + this.targetSpecTitle);
        verboseMessage("Specification version:" + this.targetSpecVersion);
        if (this.targetSpecVendor != null) {
            verboseMessage("Specification vendor:" + this.targetSpecVendor);
        }
        if (this.targetImplVersion != null) {
            verboseMessage("Implementation version:" + this.targetImplVersion);
        }
        if (this.targetImplVendor != null) {
            verboseMessage("Implementation vendor:" + this.targetImplVendor);
        }
        verboseMessage("");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkInstalledAgainstTarget() {
        File[] fileArr;
        String property = System.getProperty("java.ext.dirs");
        if (property != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property, File.pathSeparator);
            int countTokens = stringTokenizer.countTokens();
            fileArr = new File[countTokens];
            for (int i = 0; i < countTokens; i++) {
                fileArr[i] = new File(stringTokenizer.nextToken());
            }
        } else {
            fileArr = new File[0];
        }
        boolean z = true;
        for (int i2 = 0; i2 < fileArr.length; i2++) {
            String[] list = fileArr[i2].list();
            if (list != null) {
                for (String str : list) {
                    try {
                        URL fileToEncodedURL = ParseUtil.fileToEncodedURL(new File(new File(fileArr[i2], str).getCanonicalPath()));
                        if (fileToEncodedURL != null) {
                            z = z && checkURLRecursively(1, fileToEncodedURL);
                        }
                    } catch (MalformedURLException e) {
                        error("Malformed URL");
                    } catch (IOException e2) {
                        error("IO Exception");
                    }
                }
            }
        }
        if (z) {
            generalMessage("No conflicting installed jar found.");
        } else {
            generalMessage("Conflicting installed jar found.  Use -verbose for more information.");
        }
        return z;
    }

    private boolean checkURLRecursively(int i, URL url) throws IOException {
        Attributes mainAttributes;
        verboseMessage("Comparing with " + url);
        JarLoader jarLoader = new JarLoader(url);
        Manifest manifest = jarLoader.getJarFile().getManifest();
        if (manifest != null && (mainAttributes = manifest.getMainAttributes()) != null) {
            String value = mainAttributes.getValue(Attributes.Name.SPECIFICATION_TITLE);
            String value2 = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
            mainAttributes.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            String value3 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            String value4 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            String value5 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            mainAttributes.getValue(Attributes.Name.SEALED);
            if (value != null && value.equals(this.targetSpecTitle) && value2 != null && (value2.equals(this.targetSpecVersion) || isNotOlderThan(value2, this.targetSpecVersion))) {
                verboseMessage("");
                verboseMessage("CONFLICT DETECTED ");
                verboseMessage("Conflicting file:" + url);
                verboseMessage("Installed Version:" + value2);
                if (value3 != null) {
                    verboseMessage("Implementation Title:" + value3);
                }
                if (value4 != null) {
                    verboseMessage("Implementation Version:" + value4);
                }
                if (value5 != null) {
                    verboseMessage("Implementation Vendor:" + value5);
                    return false;
                }
                return false;
            }
        }
        boolean z = true;
        URL[] classPath = jarLoader.getClassPath();
        if (classPath != null) {
            for (URL url2 : classPath) {
                if (url != null) {
                    z = checkURLRecursively(i + 1, url2) && z;
                }
            }
        }
        return z;
    }

    private boolean isNotOlderThan(String str, String str2) throws NumberFormatException {
        int i;
        int i2;
        if (str == null || str.length() < 1) {
            throw new NumberFormatException("Empty version string");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str2, Constants.NAME_SEPARATOR, true);
        StringTokenizer stringTokenizer2 = new StringTokenizer(str, Constants.NAME_SEPARATOR, true);
        while (true) {
            if (stringTokenizer.hasMoreTokens() || stringTokenizer2.hasMoreTokens()) {
                if (stringTokenizer.hasMoreTokens()) {
                    i = Integer.parseInt(stringTokenizer.nextToken());
                } else {
                    i = 0;
                }
                if (stringTokenizer2.hasMoreTokens()) {
                    i2 = Integer.parseInt(stringTokenizer2.nextToken());
                } else {
                    i2 = 0;
                }
                if (i2 < i) {
                    return false;
                }
                if (i2 > i) {
                    return true;
                }
                if (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                }
                if (stringTokenizer2.hasMoreTokens()) {
                    stringTokenizer2.nextToken();
                }
            } else {
                return true;
            }
        }
    }

    void verboseMessage(String str) {
        if (this.verboseFlag) {
            System.err.println(str);
        }
    }

    void generalMessage(String str) {
        System.err.println(str);
    }

    static void error(String str) throws RuntimeException {
        throw new RuntimeException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/extcheck/ExtCheck$JarLoader.class */
    public static class JarLoader {
        private final URL base;
        private JarFile jar;
        private URL csu;

        JarLoader(URL url) {
            String str = url + "!/";
            URL url2 = null;
            try {
                url2 = new URL("jar", "", str);
                this.jar = findJarFile(url);
                this.csu = url;
            } catch (MalformedURLException e) {
                ExtCheck.error("Malformed url " + str);
            } catch (IOException e2) {
                ExtCheck.error("IO Exception occurred");
            }
            this.base = url2;
        }

        URL getBaseURL() {
            return this.base;
        }

        JarFile getJarFile() {
            return this.jar;
        }

        private JarFile findJarFile(URL url) throws IOException {
            if ("file".equals(url.getProtocol())) {
                String replace = url.getFile().replace('/', File.separatorChar);
                if (!new File(replace).exists()) {
                    throw new FileNotFoundException(replace);
                }
                return new JarFile(replace);
            }
            return ((JarURLConnection) getBaseURL().openConnection()).getJarFile();
        }

        URL[] getClassPath() throws IOException {
            Attributes mainAttributes;
            String value;
            Manifest manifest = this.jar.getManifest();
            if (manifest != null && (mainAttributes = manifest.getMainAttributes()) != null && (value = mainAttributes.getValue(Attributes.Name.CLASS_PATH)) != null) {
                return parseClassPath(this.csu, value);
            }
            return null;
        }

        private URL[] parseClassPath(URL url, String str) throws MalformedURLException {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            URL[] urlArr = new URL[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                urlArr[i] = new URL(url, stringTokenizer.nextToken());
                i++;
            }
            return urlArr;
        }
    }
}
