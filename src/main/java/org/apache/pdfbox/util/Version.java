package org.apache.pdfbox.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/pdfbox/util/Version.class */
public final class Version {
    private static final Log LOG = LogFactory.getLog(Version.class);
    private static final String PDFBOX_VERSION_PROPERTIES = "/org/apache/pdfbox/resources/version.properties";

    private Version() {
    }

    public static String getVersion() {
        try {
            InputStream resourceAsStream = Version.class.getResourceAsStream(PDFBOX_VERSION_PROPERTIES);
            Throwable th = null;
            try {
                InputStream is = new BufferedInputStream(resourceAsStream);
                Throwable th2 = null;
                try {
                    try {
                        Properties properties = new Properties();
                        properties.load(is);
                        String property = properties.getProperty("pdfbox.version", null);
                        if (is != null) {
                            if (0 != 0) {
                                try {
                                    is.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            } else {
                                is.close();
                            }
                        }
                        return property;
                    } finally {
                    }
                } catch (Throwable th4) {
                    if (is != null) {
                        if (th2 != null) {
                            try {
                                is.close();
                            } catch (Throwable th5) {
                                th2.addSuppressed(th5);
                            }
                        } else {
                            is.close();
                        }
                    }
                    throw th4;
                }
            } finally {
                if (resourceAsStream != null) {
                    if (0 != 0) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th6) {
                            th.addSuppressed(th6);
                        }
                    } else {
                        resourceAsStream.close();
                    }
                }
            }
        } catch (IOException io) {
            LOG.debug("Unable to read version from properties - returning null", io);
            return null;
        }
    }
}
