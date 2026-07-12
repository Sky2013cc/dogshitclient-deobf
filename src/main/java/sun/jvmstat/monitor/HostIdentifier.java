package sun.jvmstat.monitor;

import java.net.URI;
import java.net.URISyntaxException;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: target.jar:sun/jvmstat/monitor/HostIdentifier.class */
public class HostIdentifier {
    private URI uri;

    private URI canonicalize(String str) throws URISyntaxException {
        URI uri;
        if (str == null || str.compareTo("localhost") == 0) {
            return new URI("//localhost");
        }
        URI uri2 = new URI(str);
        if (uri2.isAbsolute()) {
            if (uri2.isOpaque()) {
                String scheme = uri2.getScheme();
                String schemeSpecificPart = uri2.getSchemeSpecificPart();
                String fragment = uri2.getFragment();
                if (str.lastIndexOf(CallSiteDescriptor.TOKEN_DELIMITER) != str.indexOf(CallSiteDescriptor.TOKEN_DELIMITER)) {
                    if (fragment == null) {
                        uri = new URI(scheme + "://" + schemeSpecificPart);
                    } else {
                        uri = new URI(scheme + "://" + schemeSpecificPart + "#" + fragment);
                    }
                    return uri;
                }
                return new URI("//" + str);
            }
            return uri2;
        }
        if (uri2.getSchemeSpecificPart().startsWith("//")) {
            return uri2;
        }
        return new URI("//" + str);
    }

    public HostIdentifier(String str) throws URISyntaxException {
        this.uri = canonicalize(str);
    }

    public HostIdentifier(String str, String str2, String str3, String str4, String str5) throws URISyntaxException {
        this.uri = new URI(str, str2, str3, str4, str5);
    }

    public HostIdentifier(VmIdentifier vmIdentifier) {
        StringBuilder sb = new StringBuilder();
        String scheme = vmIdentifier.getScheme();
        String host = vmIdentifier.getHost();
        String authority = vmIdentifier.getAuthority();
        if (scheme != null && scheme.compareTo("file") == 0) {
            try {
                this.uri = new URI("file://localhost");
                return;
            } catch (URISyntaxException e) {
                return;
            }
        }
        if (host != null && host.compareTo(authority) == 0) {
            host = null;
        }
        if (scheme == null) {
            if (host == null) {
                scheme = "local";
            } else {
                scheme = "rmi";
            }
        }
        sb.append(scheme).append("://");
        if (host == null) {
            sb.append("localhost");
        } else {
            sb.append(host);
        }
        int port = vmIdentifier.getPort();
        if (port != -1) {
            sb.append(CallSiteDescriptor.TOKEN_DELIMITER).append(port);
        }
        String path = vmIdentifier.getPath();
        if (path != null && path.length() != 0) {
            sb.append(path);
        }
        String query = vmIdentifier.getQuery();
        if (query != null) {
            sb.append("?").append(query);
        }
        String fragment = vmIdentifier.getFragment();
        if (fragment != null) {
            sb.append("#").append(fragment);
        }
        try {
            this.uri = new URI(sb.toString());
        } catch (URISyntaxException e2) {
            throw new RuntimeException("Internal Error", e2);
        }
    }

    public VmIdentifier resolve(VmIdentifier vmIdentifier) throws URISyntaxException, MonitorException {
        String scheme = vmIdentifier.getScheme();
        String host = vmIdentifier.getHost();
        String authority = vmIdentifier.getAuthority();
        if (scheme != null && scheme.compareTo("file") == 0) {
            return vmIdentifier;
        }
        if (host != null && host.compareTo(authority) == 0) {
            host = null;
        }
        if (scheme == null) {
            scheme = getScheme();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(scheme).append("://");
        String userInfo = vmIdentifier.getUserInfo();
        if (userInfo != null) {
            stringBuffer.append(userInfo);
        } else {
            stringBuffer.append(vmIdentifier.getAuthority());
        }
        if (host == null) {
            host = getHost();
        }
        stringBuffer.append("@").append(host);
        int port = vmIdentifier.getPort();
        if (port == -1) {
            port = getPort();
        }
        if (port != -1) {
            stringBuffer.append(CallSiteDescriptor.TOKEN_DELIMITER).append(port);
        }
        String path = vmIdentifier.getPath();
        if (path == null || path.length() == 0) {
            path = getPath();
        }
        if (path != null && path.length() > 0) {
            stringBuffer.append(path);
        }
        String query = vmIdentifier.getQuery();
        if (query == null) {
            query = getQuery();
        }
        if (query != null) {
            stringBuffer.append("?").append(query);
        }
        String fragment = vmIdentifier.getFragment();
        if (fragment == null) {
            fragment = getFragment();
        }
        if (fragment != null) {
            stringBuffer.append("#").append(fragment);
        }
        return new VmIdentifier(stringBuffer.toString());
    }

    public String getScheme() {
        if (this.uri.isAbsolute()) {
            return this.uri.getScheme();
        }
        return null;
    }

    public String getSchemeSpecificPart() {
        return this.uri.getSchemeSpecificPart();
    }

    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    public String getHost() {
        return this.uri.getHost() == null ? "localhost" : this.uri.getHost();
    }

    public int getPort() {
        return this.uri.getPort();
    }

    public String getPath() {
        return this.uri.getPath();
    }

    public String getQuery() {
        return this.uri.getQuery();
    }

    public String getFragment() {
        return this.uri.getFragment();
    }

    public String getMode() {
        String query = getQuery();
        if (query != null) {
            String[] split = query.split("\\+");
            for (int i = 0; i < split.length; i++) {
                if (split[i].startsWith("mode=")) {
                    return split[i].substring(split[i].indexOf(61) + 1);
                }
            }
            return PDPageLabelRange.STYLE_ROMAN_LOWER;
        }
        return PDPageLabelRange.STYLE_ROMAN_LOWER;
    }

    public URI getURI() {
        return this.uri;
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HostIdentifier)) {
            return false;
        }
        return this.uri.equals(((HostIdentifier) obj).uri);
    }

    public String toString() {
        return this.uri.toString();
    }
}
