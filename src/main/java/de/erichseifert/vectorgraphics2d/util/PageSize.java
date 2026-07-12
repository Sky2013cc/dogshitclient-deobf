package de.erichseifert.vectorgraphics2d.util;

import java.awt.geom.Rectangle2D;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/PageSize.class */
public class PageSize {
    public static final PageSize A3 = new PageSize(297.0d, 420.0d);
    public static final PageSize A4 = new PageSize(210.0d, 297.0d);
    public static final PageSize A5 = new PageSize(148.0d, 210.0d);
    public static final PageSize LETTER = new PageSize(21.59d, 27.94d);
    public static final PageSize LEGAL = new PageSize(21.59d, 35.56d);
    public static final PageSize TABLOID;
    public static final PageSize LEDGER;
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    static {
        PageSize pageSize = new PageSize(27.94d, 43.18d);
        TABLOID = pageSize;
        LEDGER = pageSize.getLandscape();
    }

    public PageSize(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    public PageSize(double d, double d2) {
        this(0.0d, 0.0d, d, d2);
    }

    public PageSize(Rectangle2D rectangle2D) {
        this(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }

    public PageSize getPortrait() {
        if (this.c <= this.d) {
            return this;
        }
        return new PageSize(this.a, this.b, this.d, this.c);
    }

    public PageSize getLandscape() {
        if (this.c >= this.d) {
            return this;
        }
        return new PageSize(this.a, this.b, this.d, this.c);
    }

    public double getX() {
        return this.a;
    }

    public double getY() {
        return this.b;
    }

    public double getWidth() {
        return this.c;
    }

    public double getHeight() {
        return this.d;
    }
}
