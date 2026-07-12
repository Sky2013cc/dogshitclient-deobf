package org.apache.fontbox.util.autodetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/apache/fontbox/util/autodetect/WindowsFontDirFinder.class */
public class WindowsFontDirFinder implements FontDirFinder {
    private static final Log LOG = LogFactory.getLog(WindowsFontDirFinder.class);

    private String getWinDir(String osName) throws IOException {
        Process process;
        Runtime runtime = Runtime.getRuntime();
        if (osName.startsWith("Windows 9")) {
            process = runtime.exec("command.com /c echo %windir%");
        } else {
            process = runtime.exec("cmd.exe /c echo %windir%");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.ISO_8859_1));
        Throwable th = null;
        try {
            try {
                String readLine = bufferedReader.readLine();
                if (bufferedReader != null) {
                    if (0 != 0) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        bufferedReader.close();
                    }
                }
                return readLine;
            } finally {
            }
        } catch (Throwable th3) {
            if (bufferedReader != null) {
                if (th != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    bufferedReader.close();
                }
            }
            throw th3;
        }
    }

    @Override // org.apache.fontbox.util.autodetect.FontDirFinder
    public List<File> find() {
        List<File> fontDirList = new ArrayList<>();
        String windir = null;
        try {
            windir = System.getProperty("env.windir");
        } catch (SecurityException e) {
            LOG.debug("Couldn't get Windows font directories - ignoring", e);
        }
        String osName = System.getProperty("os.name");
        if (windir == null) {
            try {
                windir = getWinDir(osName);
            } catch (IOException | SecurityException e2) {
                LOG.debug("Couldn't get Windows font directories - ignoring", e2);
            }
        }
        if (windir != null && windir.length() > 2) {
            if (windir.endsWith(RuntimeConstants.SIG_PACKAGE)) {
                windir = windir.substring(0, windir.length() - 1);
            }
            File osFontsDir = new File(windir + File.separator + "FONTS");
            if (osFontsDir.exists() && osFontsDir.canRead()) {
                fontDirList.add(osFontsDir);
            }
            File psFontsDir = new File(windir.substring(0, 2) + File.separator + "PSFONTS");
            if (psFontsDir.exists() && psFontsDir.canRead()) {
                fontDirList.add(psFontsDir);
            }
        } else {
            String windowsDirName = osName.endsWith("NT") ? "WINNT" : "WINDOWS";
            char c = 'C';
            while (true) {
                char driveLetter = c;
                if (driveLetter > 'E') {
                    break;
                }
                File osFontsDir2 = new File(driveLetter + CallSiteDescriptor.TOKEN_DELIMITER + File.separator + windowsDirName + File.separator + "FONTS");
                try {
                    if (osFontsDir2.exists() && osFontsDir2.canRead()) {
                        fontDirList.add(osFontsDir2);
                        break;
                    }
                } catch (SecurityException e3) {
                    LOG.debug("Couldn't get Windows font directories - ignoring", e3);
                }
                c = (char) (driveLetter + 1);
            }
            char c2 = 'C';
            while (true) {
                char driveLetter2 = c2;
                if (driveLetter2 <= 'E') {
                    File psFontsDir2 = new File(driveLetter2 + CallSiteDescriptor.TOKEN_DELIMITER + File.separator + "PSFONTS");
                    try {
                        if (psFontsDir2.exists() && psFontsDir2.canRead()) {
                            fontDirList.add(psFontsDir2);
                        }
                    } catch (SecurityException e4) {
                        LOG.debug("Couldn't get Windows font directories - ignoring", e4);
                    }
                    c2 = (char) (driveLetter2 + 1);
                }
            }
        }
        try {
            String localAppData = System.getenv("LOCALAPPDATA");
            if (localAppData != null && !localAppData.isEmpty()) {
                File localFontDir = new File(localAppData + File.separator + "Microsoft" + File.separator + "Windows" + File.separator + "Fonts");
                if (localFontDir.exists() && localFontDir.canRead()) {
                    fontDirList.add(localFontDir);
                }
            }
        } catch (SecurityException e5) {
            LOG.debug("Couldn't get LOCALAPPDATA directory - ignoring", e5);
        }
        return fontDirList;
    }
}
