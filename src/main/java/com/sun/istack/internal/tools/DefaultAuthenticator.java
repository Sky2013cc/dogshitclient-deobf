package com.sun.istack.internal.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.slf4j.Marker;
import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/istack/internal/tools/DefaultAuthenticator.class */
public class DefaultAuthenticator extends Authenticator {
    private static DefaultAuthenticator instance;
    private String proxyUser;
    private String proxyPasswd;
    private final List<AuthInfo> authInfo = new ArrayList();
    private static Authenticator systemAuthenticator = getCurrentAuthenticator();
    private static int counter = 0;

    /* loaded from: target.jar:com/sun/istack/internal/tools/DefaultAuthenticator$Receiver.class */
    public interface Receiver {
        void onParsingError(String str, Locator locator);

        void onError(Exception exc, Locator locator);
    }

    DefaultAuthenticator() {
        if (System.getProperty("http.proxyUser") != null) {
            this.proxyUser = System.getProperty("http.proxyUser");
        } else {
            this.proxyUser = System.getProperty("proxyUser");
        }
        if (System.getProperty("http.proxyPassword") != null) {
            this.proxyPasswd = System.getProperty("http.proxyPassword");
        } else {
            this.proxyPasswd = System.getProperty("proxyPassword");
        }
    }

    public static synchronized DefaultAuthenticator getAuthenticator() {
        if (instance == null) {
            instance = new DefaultAuthenticator();
            Authenticator.setDefault(instance);
        }
        counter++;
        return instance;
    }

    public static synchronized void reset() {
        counter--;
        if (instance != null && counter == 0) {
            Authenticator.setDefault(systemAuthenticator);
        }
    }

    @Override // java.net.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        if (getRequestorType() == Authenticator.RequestorType.PROXY && this.proxyUser != null && this.proxyPasswd != null) {
            return new PasswordAuthentication(this.proxyUser, this.proxyPasswd.toCharArray());
        }
        for (AuthInfo auth : this.authInfo) {
            if (auth.matchingHost(getRequestingURL())) {
                return new PasswordAuthentication(auth.getUser(), auth.getPassword().toCharArray());
            }
        }
        return null;
    }

    public void setProxyAuth(String proxyAuth) {
        if (proxyAuth == null) {
            this.proxyUser = null;
            this.proxyPasswd = null;
            return;
        }
        int i = proxyAuth.indexOf(58);
        if (i < 0) {
            this.proxyUser = proxyAuth;
            this.proxyPasswd = "";
        } else if (i == 0) {
            this.proxyUser = "";
            this.proxyPasswd = proxyAuth.substring(1);
        } else {
            this.proxyUser = proxyAuth.substring(0, i);
            this.proxyPasswd = proxyAuth.substring(i + 1);
        }
    }

    public void setAuth(File f, Receiver l) {
        Receiver listener = l == null ? new DefaultRImpl() : l;
        BufferedReader in = null;
        FileInputStream fi = null;
        InputStreamReader is = null;
        try {
            LocatorImpl locator = new LocatorImpl();
            locator.setSystemId(f.getAbsolutePath());
            try {
                fi = new FileInputStream(f);
                is = new InputStreamReader(fi, "UTF-8");
                in = new BufferedReader(is);
                try {
                    int lineno = 1;
                    locator.setSystemId(f.getCanonicalPath());
                    while (true) {
                        String text = in.readLine();
                        if (text == null) {
                            break;
                        }
                        int i = lineno;
                        lineno++;
                        locator.setLineNumber(i);
                        if (!"".equals(text.trim()) && !text.startsWith("#")) {
                            try {
                                AuthInfo ai = parseLine(text);
                                this.authInfo.add(ai);
                            } catch (Exception e) {
                                listener.onParsingError(text, locator);
                            }
                        }
                    }
                } catch (IOException e2) {
                    listener.onError(e2, locator);
                    Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, e2.getMessage(), (Throwable) e2);
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
                        return;
                    }
                }
                if (is != null) {
                    is.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (FileNotFoundException e3) {
                listener.onError(e3, locator);
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex2) {
                        Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex2);
                        return;
                    }
                }
                if (is != null) {
                    is.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (UnsupportedEncodingException e4) {
                listener.onError(e4, locator);
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex3) {
                        Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex3);
                        return;
                    }
                }
                if (is != null) {
                    is.close();
                }
                if (fi != null) {
                    fi.close();
                }
            }
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex4) {
                    Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex4);
                    throw th;
                }
            }
            if (is != null) {
                is.close();
            }
            if (fi != null) {
                fi.close();
            }
            throw th;
        }
    }

    private AuthInfo parseLine(String text) throws Exception {
        URL url;
        int i;
        try {
            url = new URL(text);
        } catch (MalformedURLException e) {
            int i2 = text.indexOf(58, text.indexOf(58) + 1) + 1;
            int j = text.lastIndexOf(64);
            String encodedUrl = text.substring(0, i2) + URLEncoder.encode(text.substring(i2, j), "UTF-8") + text.substring(j);
            url = new URL(encodedUrl);
        }
        String authinfo = url.getUserInfo();
        if (authinfo != null && (i = authinfo.indexOf(58)) >= 0) {
            String user = authinfo.substring(0, i);
            String password = authinfo.substring(i + 1);
            return new AuthInfo(new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile()), user, URLDecoder.decode(password, "UTF-8"));
        }
        throw new Exception();
    }

    static Authenticator getCurrentAuthenticator() {
        final Field f = getTheAuthenticator();
        if (f == null) {
            return null;
        }
        try {
            AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: com.sun.istack.internal.tools.DefaultAuthenticator.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                public Void run() {
                    f.setAccessible(true);
                    return null;
                }
            });
            return (Authenticator) f.get(null);
        } catch (Exception e) {
            return null;
        } finally {
            AccessController.doPrivileged(new PrivilegedAction<Void>() { // from class: com.sun.istack.internal.tools.DefaultAuthenticator.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                public Void run() {
                    f.setAccessible(false);
                    return null;
                }
            });
        }
    }

    private static Field getTheAuthenticator() {
        try {
            return Authenticator.class.getDeclaredField("theAuthenticator");
        } catch (Exception e) {
            return null;
        }
    }

    /* loaded from: target.jar:com/sun/istack/internal/tools/DefaultAuthenticator$DefaultRImpl.class */
    private static class DefaultRImpl implements Receiver {
        private DefaultRImpl() {
        }

        @Override // com.sun.istack.internal.tools.DefaultAuthenticator.Receiver
        public void onParsingError(String line, Locator loc) {
            System.err.println(getLocationString(loc) + ": " + line);
        }

        @Override // com.sun.istack.internal.tools.DefaultAuthenticator.Receiver
        public void onError(Exception e, Locator loc) {
            System.err.println(getLocationString(loc) + ": " + e.getMessage());
            Logger.getLogger(DefaultAuthenticator.class.getName()).log(Level.SEVERE, e.getMessage(), (Throwable) e);
        }

        private String getLocationString(Locator l) {
            return RuntimeConstants.SIG_ARRAY + l.getSystemId() + "#" + l.getLineNumber() + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/istack/internal/tools/DefaultAuthenticator$AuthInfo.class */
    public static final class AuthInfo {
        private final String user;
        private final String password;
        private final Pattern urlPattern;

        public AuthInfo(URL url, String user, String password) {
            String u = url.toExternalForm().replaceFirst("\\?", "\\\\?");
            this.urlPattern = Pattern.compile(u.replace(Marker.ANY_MARKER, ".*"), 2);
            this.user = user;
            this.password = password;
        }

        public String getUser() {
            return this.user;
        }

        public String getPassword() {
            return this.password;
        }

        public boolean matchingHost(URL requestingURL) {
            return this.urlPattern.matcher(requestingURL.toExternalForm()).matches();
        }
    }
}
