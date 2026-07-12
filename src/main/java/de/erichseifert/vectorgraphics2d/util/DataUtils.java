package de.erichseifert.vectorgraphics2d.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/DataUtils.class */
public abstract class DataUtils {
    private static final DecimalFormat a = new DecimalFormat(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    private static final DecimalFormat b = new DecimalFormat(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES, DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    static {
        a.setMaximumFractionDigits(15);
        b.setMaximumFractionDigits(6);
    }

    DataUtils() {
        throw new UnsupportedOperationException();
    }

    public static <K, V> Map<K, V> map(K[] kArr, V[] vArr) {
        if (kArr.length != vArr.length) {
            throw new IllegalArgumentException("Cannot create a Map: The number of keys and values differs.");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(kArr.length);
        for (int i = 0; i < kArr.length; i++) {
            linkedHashMap.put(kArr[i], vArr[i]);
        }
        return linkedHashMap;
    }

    public static String join(String str, List<?> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(list.size() * 3);
        int i = 0;
        for (Object obj : list) {
            if (str.length() > 0) {
                int i2 = i;
                i++;
                if (i2 > 0) {
                    sb.append(str);
                }
            }
            sb.append(format(obj));
        }
        return sb.toString();
    }

    public static String join(String str, Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        return join(str, (List<?>) Arrays.asList(objArr));
    }

    public static String join(String str, double[] dArr) {
        if (dArr == null || dArr.length == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double d : dArr) {
            arrayList.add(Double.valueOf(d));
        }
        return join(str, arrayList);
    }

    public static String join(String str, float[] fArr) {
        if (fArr == null || fArr.length == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return join(str, arrayList);
    }

    public static int max(int... iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("No values provided: Cannot determine maximum value.");
        }
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static void transfer(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String format(Number number) {
        String obj;
        if (number instanceof Double) {
            obj = a.format(number.doubleValue());
        } else if (number instanceof Float) {
            obj = b.format(number.floatValue());
        } else {
            obj = number.toString();
        }
        return obj;
    }

    public static String format(Object obj) {
        if (obj instanceof Number) {
            return format((Number) obj);
        }
        return obj.toString();
    }

    public static List<Float> asList(float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr != null ? fArr.length : 0);
        if (fArr != null) {
            for (float f : fArr) {
                arrayList.add(Float.valueOf(f));
            }
        }
        return arrayList;
    }

    public static List<Double> asList(double[] dArr) {
        ArrayList arrayList = new ArrayList(dArr != null ? dArr.length : 0);
        if (dArr != null) {
            for (double d : dArr) {
                arrayList.add(Double.valueOf(d));
            }
        }
        return arrayList;
    }

    public static String stripTrailing(String str, String str2) {
        return str.replaceAll(RuntimeConstants.SIG_METHOD + Pattern.quote(str2) + ")+$", "");
    }
}
