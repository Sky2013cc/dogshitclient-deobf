package jdk.nashorn.internal.runtime.options;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclint.DocLint;
import java.util.Locale;
import java.util.TimeZone;
import jdk.nashorn.internal.runtime.QuotedStringTokenizer;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/options/OptionTemplate.class */
public final class OptionTemplate implements Comparable<OptionTemplate> {
    private final String resource;
    private final String key;
    private final boolean isHelp;
    private final boolean isXHelp;
    private String name;
    private String shortName;
    private String params;
    private String type;
    private String defaultValue;
    private String dependency;
    private String conflict;
    private boolean isUndocumented;
    private String description;
    private boolean valueNextArg;
    private static final int LINE_BREAK = 64;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OptionTemplate(String resource, String key, String value, boolean isHelp, boolean isXHelp) {
        this.resource = resource;
        this.key = key;
        this.isHelp = isHelp;
        this.isXHelp = isXHelp;
        parse(value);
    }

    public boolean isHelp() {
        return this.isHelp;
    }

    public boolean isXHelp() {
        return this.isXHelp;
    }

    public String getResource() {
        return this.resource;
    }

    public String getType() {
        return this.type;
    }

    public String getKey() {
        return this.key;
    }

    public String getDefaultValue() {
        String type = getType();
        boolean z = -1;
        switch (type.hashCode()) {
            case -2076227591:
                if (type.equals("timezone")) {
                    z = 2;
                    break;
                }
                break;
            case -1097462182:
                if (type.equals("locale")) {
                    z = 3;
                    break;
                }
                break;
            case 64711720:
                if (type.equals(Constants.IDL_BOOLEAN)) {
                    z = false;
                    break;
                }
                break;
            case 1958052158:
                if (type.equals("integer")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (this.defaultValue == null) {
                    this.defaultValue = com.sun.tools.internal.ws.wsdl.parser.Constants.FALSE;
                    break;
                }
                break;
            case true:
                if (this.defaultValue == null) {
                    this.defaultValue = PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES;
                    break;
                }
                break;
            case true:
                this.defaultValue = TimeZone.getDefault().getID();
                break;
            case true:
                this.defaultValue = Locale.getDefault().toLanguageTag();
                break;
        }
        return this.defaultValue;
    }

    public String getDependency() {
        return this.dependency;
    }

    public String getConflict() {
        return this.conflict;
    }

    public boolean isUndocumented() {
        return this.isUndocumented;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isValueNextArg() {
        return this.valueNextArg;
    }

    private static String strip(String value, char start, char end) {
        int len = value.length();
        if (len > 1 && value.charAt(0) == start && value.charAt(len - 1) == end) {
            return value.substring(1, len - 1);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0180 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x018c A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01a9 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c6 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01cf A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01d8 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01e7 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01f0 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f9 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0202 A[Catch: Exception -> 0x0244, TryCatch #0 {Exception -> 0x0244, blocks: (B:3:0x0005, B:4:0x0019, B:6:0x0020, B:7:0x004d, B:8:0x00a8, B:11:0x00b8, B:14:0x00c8, B:17:0x00d8, B:20:0x00e8, B:23:0x00f8, B:26:0x0108, B:29:0x0119, B:32:0x012a, B:35:0x013b, B:39:0x014b, B:40:0x0180, B:43:0x018c, B:48:0x0196, B:49:0x019f, B:45:0x01a0, B:51:0x01a9, B:56:0x01b3, B:57:0x01bc, B:53:0x01bd, B:58:0x01c6, B:60:0x01cf, B:62:0x01d8, B:64:0x01e7, B:66:0x01f0, B:68:0x01f9, B:70:0x0202, B:73:0x020e, B:74:0x0217, B:76:0x021b, B:78:0x0222, B:79:0x0228, B:81:0x022f, B:83:0x023b), top: B:2:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x020e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void parse(String origValue) {
        String value = origValue.trim();
        try {
            QuotedStringTokenizer keyValuePairs = new QuotedStringTokenizer(strip(value, '{', '}'), DocLint.TAGS_SEPARATOR);
            while (keyValuePairs.hasMoreTokens()) {
                String keyValue = keyValuePairs.nextToken();
                QuotedStringTokenizer st = new QuotedStringTokenizer(keyValue, "=");
                String keyToken = st.nextToken();
                String arg = st.nextToken();
                boolean z = -1;
                switch (keyToken.hashCode()) {
                    case -2103299560:
                        if (keyToken.equals("value_next_arg")) {
                            z = 9;
                        }
                        switch (z) {
                            case false:
                                this.isUndocumented = Boolean.parseBoolean(arg);
                                break;
                            case true:
                                if (!arg.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                                    throw new IllegalArgumentException(arg);
                                }
                                this.name = arg;
                                break;
                            case true:
                                if (!arg.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                                    throw new IllegalArgumentException(arg);
                                }
                                this.shortName = arg;
                                break;
                            case true:
                                this.description = arg;
                                break;
                            case true:
                                this.params = arg;
                                break;
                            case true:
                                this.type = arg.toLowerCase(Locale.ENGLISH);
                                break;
                            case true:
                                this.defaultValue = arg;
                                break;
                            case true:
                                this.dependency = arg;
                                break;
                            case true:
                                this.conflict = arg;
                                break;
                            case true:
                                this.valueNextArg = Boolean.parseBoolean(arg);
                                break;
                            default:
                                throw new IllegalArgumentException(keyToken);
                        }
                    case -995427962:
                        if (keyToken.equals("params")) {
                            z = 4;
                        }
                        switch (z) {
                        }
                        break;
                    case -580047918:
                        if (keyToken.equals("conflict")) {
                            z = 8;
                        }
                        switch (z) {
                        }
                        break;
                    case -26291381:
                        if (keyToken.equals("dependency")) {
                            z = 7;
                        }
                        switch (z) {
                        }
                        break;
                    case 3079825:
                        if (keyToken.equals("desc")) {
                            z = 3;
                        }
                        switch (z) {
                        }
                        break;
                    case 3373707:
                        if (keyToken.equals("name")) {
                            z = true;
                        }
                        switch (z) {
                        }
                        break;
                    case 3575610:
                        if (keyToken.equals(com.sun.tools.internal.ws.wsdl.parser.Constants.ATTR_TYPE)) {
                            z = 5;
                        }
                        switch (z) {
                        }
                        break;
                    case 572651336:
                        if (keyToken.equals("is_undocumented")) {
                            z = false;
                        }
                        switch (z) {
                        }
                        break;
                    case 1544803905:
                        if (keyToken.equals("default")) {
                            z = 6;
                        }
                        switch (z) {
                        }
                        break;
                    case 1565793390:
                        if (keyToken.equals("short_name")) {
                            z = 2;
                        }
                        switch (z) {
                        }
                        break;
                    default:
                        switch (z) {
                        }
                        break;
                }
            }
            if (this.type == null) {
                this.type = Constants.IDL_BOOLEAN;
            }
            if (this.params == null && Constants.IDL_BOOLEAN.equals(this.type)) {
                this.params = "[true|false]";
            }
            if (this.name == null && this.shortName == null) {
                throw new IllegalArgumentException(origValue);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(origValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean nameMatches(String aName) {
        return aName.equals(this.shortName) || aName.equals(this.name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\t');
        if (this.shortName != null) {
            sb.append(this.shortName);
            if (this.name != null) {
                sb.append(", ");
            }
        }
        if (this.name != null) {
            sb.append(this.name);
        }
        if (this.description != null) {
            int indent = sb.length();
            sb.append(' ');
            sb.append('(');
            int pos = 0;
            for (char c : this.description.toCharArray()) {
                sb.append(c);
                pos++;
                if (pos >= 64 && Character.isWhitespace(c)) {
                    pos = 0;
                    sb.append("\n\t");
                    for (int i = 0; i < indent; i++) {
                        sb.append(' ');
                    }
                }
            }
            sb.append(')');
        }
        if (this.params != null) {
            sb.append('\n');
            sb.append('\t');
            sb.append('\t');
            sb.append(Options.getMsg("nashorn.options.param", new String[0])).append(": ");
            sb.append(this.params);
            sb.append("   ");
            Object def = getDefaultValue();
            if (def != null) {
                sb.append(Options.getMsg("nashorn.options.default", new String[0])).append(": ");
                sb.append(getDefaultValue());
            }
        }
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(OptionTemplate o) {
        return getKey().compareTo(o.getKey());
    }
}
