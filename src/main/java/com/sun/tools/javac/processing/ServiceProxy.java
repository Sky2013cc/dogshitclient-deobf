package com.sun.tools.javac.processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:com/sun/tools/javac/processing/ServiceProxy.class */
class ServiceProxy {
    private static final String prefix = "META-INF/services/";

    ServiceProxy() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/processing/ServiceProxy$ServiceConfigurationError.class */
    public static class ServiceConfigurationError extends Error {
        static final long serialVersionUID = 7732091036771098303L;

        ServiceConfigurationError(String str) {
            super(str);
        }
    }

    private static void fail(Class<?> cls, String str) throws ServiceConfigurationError {
        throw new ServiceConfigurationError(cls.getName() + ": " + str);
    }

    private static void fail(Class<?> cls, URL url, int i, String str) throws ServiceConfigurationError {
        fail(cls, url + CallSiteDescriptor.TOKEN_DELIMITER + i + ": " + str);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x021e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x026d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean parse(Class<?> cls, URL url) throws ServiceConfigurationError {
        String trim;
        int length;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            try {
                inputStream = url.openStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                do {
                    String readLine = bufferedReader.readLine();
                    String str = readLine;
                    if (readLine == null) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                fail(cls, ": " + e);
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e2) {
                                fail(cls, ": " + e2);
                                return false;
                            }
                        }
                        return false;
                    }
                    int indexOf = str.indexOf(35);
                    if (indexOf >= 0) {
                        str = str.substring(0, indexOf);
                    }
                    trim = str.trim();
                    length = trim.length();
                } while (length == 0);
                if (trim.indexOf(32) >= 0 || trim.indexOf(9) >= 0) {
                    fail(cls, url, 1, "Illegal configuration-file syntax");
                }
                int codePointAt = trim.codePointAt(0);
                if (!Character.isJavaIdentifierStart(codePointAt)) {
                    fail(cls, url, 1, "Illegal provider-class name: " + trim);
                }
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = trim.codePointAt(charCount);
                    if (!Character.isJavaIdentifierPart(codePointAt2) && codePointAt2 != 46) {
                        fail(cls, url, 1, "Illegal provider-class name: " + trim);
                    }
                    charCount += Character.charCount(codePointAt2);
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        fail(cls, ": " + e3);
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        fail(cls, ": " + e4);
                    }
                }
                return true;
            } catch (Throwable th) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                        fail(cls, ": " + e5);
                        if (inputStream != null) {
                        }
                        throw th;
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        fail(cls, ": " + e6);
                        throw th;
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e8) {
                    fail(cls, ": " + e8);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e9) {
                            fail(cls, ": " + e9);
                            return false;
                        }
                    }
                    return false;
                }
            }
            if (inputStream != null) {
            }
            return false;
        } catch (IOException e10) {
            fail(cls, ": " + e10);
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e11) {
                    fail(cls, ": " + e11);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e12) {
                            fail(cls, ": " + e12);
                            return false;
                        }
                    }
                    return false;
                }
            }
            if (inputStream != null) {
            }
            return false;
        }
    }

    public static boolean hasService(Class<?> cls, URL[] urlArr) throws ServiceConfigurationError {
        for (URL url : urlArr) {
            if (parse(cls, new URL(url, prefix + cls.getName()))) {
                return true;
            }
        }
        return false;
    }
}
