package de.erichseifert.vectorgraphics2d.pdf;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/e.class */
final class e implements PDFObject {
    private final String a;
    private final String b;

    public e(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public static String b() {
        return "Font";
    }

    public static String c() {
        return "TrueType";
    }

    public final String d() {
        return this.b;
    }
}
