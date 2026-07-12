package sun.jvmstat.monitor;

import java.net.URI;
import java.net.URISyntaxException;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: target.jar:sun/jvmstat/monitor/VmIdentifier.class */
public class VmIdentifier {
    private URI uri;

    private URI canonicalize(String str) throws URISyntaxException {
        if (str == null) {
            return new URI("local://0@localhost");
        }
        URI uri = new URI(str);
        if (uri.isAbsolute()) {
            if (uri.isOpaque()) {
                uri = new URI(uri.getScheme(), "//" + uri.getSchemeSpecificPart(), uri.getFragment());
            }
        } else if (!str.startsWith("//")) {
            uri = uri.getFragment() == null ? new URI("//" + uri.getSchemeSpecificPart()) : new URI("//" + uri.getSchemeSpecificPart() + "#" + uri.getFragment());
        }
        return uri;
    }

    private void validate() throws URISyntaxException {
        String scheme = getScheme();
        if ((scheme == null || scheme.compareTo("file") != 0) && getLocalVmId() == -1) {
            throw new URISyntaxException(this.uri.toString(), "Local vmid required");
        }
    }

    public VmIdentifier(String str) throws URISyntaxException {
        URI canonicalize;
        try {
            canonicalize = canonicalize(str);
        } catch (URISyntaxException e) {
            if (str.startsWith("//")) {
                throw e;
            }
            canonicalize = canonicalize("//" + str);
        }
        this.uri = canonicalize;
        validate();
    }

    public VmIdentifier(URI uri) throws URISyntaxException {
        this.uri = uri;
        validate();
    }

    public HostIdentifier getHostIdentifier() throws URISyntaxException {
        StringBuffer stringBuffer = new StringBuffer();
        if (getScheme() != null) {
            stringBuffer.append(getScheme()).append(CallSiteDescriptor.TOKEN_DELIMITER);
        }
        stringBuffer.append("//").append(getHost());
        if (getPort() != -1) {
            stringBuffer.append(CallSiteDescriptor.TOKEN_DELIMITER).append(getPort());
        }
        if (getPath() != null) {
            stringBuffer.append(getPath());
        }
        return new HostIdentifier(stringBuffer.toString());
    }

    public String getScheme() {
        return this.uri.getScheme();
    }

    public String getSchemeSpecificPart() {
        return this.uri.getSchemeSpecificPart();
    }

    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    public String getHost() {
        return this.uri.getHost();
    }

    public int getPort() {
        return this.uri.getPort();
    }

    public String getAuthority() {
        return this.uri.getAuthority();
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

    public int getLocalVmId() {
        int i = -1;
        try {
            if (this.uri.getUserInfo() == null) {
                i = Integer.parseInt(this.uri.getAuthority());
            } else {
                i = Integer.parseInt(this.uri.getUserInfo());
            }
        } catch (NumberFormatException e) {
        }
        return i;
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
        if (!(obj instanceof VmIdentifier)) {
            return false;
        }
        return this.uri.equals(((VmIdentifier) obj).uri);
    }

    public String toString() {
        return this.uri.toString();
    }
}
