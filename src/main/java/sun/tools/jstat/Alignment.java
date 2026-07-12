package sun.tools.jstat;

import com.formdev.flatlaf.FlatClientProperties;
import java.util.HashMap;
import java.util.Set;

/* loaded from: target.jar:sun/tools/jstat/Alignment.class */
public abstract class Alignment {
    private static final String blanks = "                                                                                                                                                               ";
    private final String name;
    private final int value;
    private static int nextOrdinal = 0;
    private static HashMap<String, Alignment> map = new HashMap<>();
    public static final Alignment CENTER = new Alignment(FlatClientProperties.TABBED_PANE_ALIGN_CENTER) { // from class: sun.tools.jstat.Alignment.1
        @Override // sun.tools.jstat.Alignment
        protected String align(String str, int i) {
            int length = str.length();
            if (length >= i) {
                return str;
            }
            int i2 = i - length;
            int i3 = i2 / 2;
            int i4 = i2 % 2;
            if (i3 == 0) {
                return str + Alignment.blanks.substring(0, i4);
            }
            return Alignment.blanks.substring(0, i3) + str + Alignment.blanks.substring(0, i3 + i4);
        }
    };
    public static final Alignment LEFT = new Alignment("left") { // from class: sun.tools.jstat.Alignment.2
        @Override // sun.tools.jstat.Alignment
        protected String align(String str, int i) {
            int length = str.length();
            if (length >= i) {
                return str;
            }
            return str + Alignment.blanks.substring(0, i - length);
        }
    };
    public static final Alignment RIGHT = new Alignment("right") { // from class: sun.tools.jstat.Alignment.3
        @Override // sun.tools.jstat.Alignment
        protected String align(String str, int i) {
            int length = str.length();
            if (length >= i) {
                return str;
            }
            return Alignment.blanks.substring(0, i - length) + str;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String align(String str, int i);

    public static Alignment toAlignment(String str) {
        return map.get(str);
    }

    public static Set keySet() {
        return map.keySet();
    }

    public String toString() {
        return this.name;
    }

    private Alignment(String str) {
        int i = nextOrdinal;
        nextOrdinal = i + 1;
        this.value = i;
        this.name = str;
        map.put(str, this);
    }
}
