package de.erichseifert.vectorgraphics2d;

import de.erichseifert.vectorgraphics2d.util.GraphicsUtils;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Rectangle2D;
import java.util.Map;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/GraphicsState.class */
public class GraphicsState implements Cloneable {
    public static final Color DEFAULT_BACKGROUND = Color.BLACK;
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final Shape DEFAULT_CLIP = null;
    public static final Composite DEFAULT_COMPOSITE = AlphaComposite.SrcOver;
    public static final Font DEFAULT_FONT = Font.decode((String) null);
    public static final Color DEFAULT_PAINT = DEFAULT_COLOR;
    public static final Stroke DEFAULT_STROKE = new BasicStroke();
    public static final AffineTransform DEFAULT_TRANSFORM = new AffineTransform();
    public static final Color DEFAULT_XOR_MODE = Color.BLACK;
    private RenderingHints a = new RenderingHints((Map) null);
    private Color b = DEFAULT_BACKGROUND;
    private Color c = DEFAULT_COLOR;
    private Shape d = DEFAULT_CLIP;
    private Composite e = DEFAULT_COMPOSITE;
    private Font f = DEFAULT_FONT;
    private Paint g = DEFAULT_PAINT;
    private Stroke h = DEFAULT_STROKE;
    private AffineTransform i = new AffineTransform(DEFAULT_TRANSFORM);
    private Color j = DEFAULT_XOR_MODE;

    public Object clone() throws CloneNotSupportedException {
        GraphicsState graphicsState = (GraphicsState) super.clone();
        graphicsState.a = (RenderingHints) this.a.clone();
        graphicsState.d = GraphicsUtils.clone(this.d);
        graphicsState.i = new AffineTransform(this.i);
        return graphicsState;
    }

    private static Shape a(Shape shape, AffineTransform affineTransform) {
        if (shape == null) {
            return null;
        }
        if (affineTransform == null || affineTransform.isIdentity()) {
            return GraphicsUtils.clone(shape);
        }
        boolean z = shape instanceof Rectangle2D;
        boolean z2 = (affineTransform.getType() & 48) == 0;
        if (z && z2) {
            Rectangle2D rectangle2D = (Rectangle2D) shape;
            double[] dArr = {rectangle2D.getMinX(), rectangle2D.getMinY(), rectangle2D.getMaxX(), rectangle2D.getMaxY()};
            affineTransform.transform(dArr, 0, dArr, 0, 2);
            Rectangle2D.Double r0 = new Rectangle2D.Double();
            r0.setFrameFromDiagonal(dArr[0], dArr[1], dArr[2], dArr[3]);
            return r0;
        }
        return affineTransform.createTransformedShape(shape);
    }

    private static Shape b(Shape shape, AffineTransform affineTransform) {
        if (shape == null) {
            return null;
        }
        try {
            return a(shape, affineTransform.createInverse());
        } catch (NoninvertibleTransformException unused) {
            return null;
        }
    }

    public Shape transformShape(Shape shape) {
        return a(shape, this.i);
    }

    public Shape untransformShape(Shape shape) {
        return b(shape, this.i);
    }

    public RenderingHints getHints() {
        return this.a;
    }

    public Color getBackground() {
        return this.b;
    }

    public void setBackground(Color color) {
        this.b = color;
    }

    public Color getColor() {
        return this.c;
    }

    public void setColor(Color color) {
        this.c = color;
    }

    public Shape getClip() {
        return untransformShape(this.d);
    }

    public void setClip(Shape shape) {
        this.d = transformShape(shape);
    }

    public Composite getComposite() {
        return this.e;
    }

    public void setComposite(Composite composite) {
        this.e = composite;
    }

    public Font getFont() {
        return this.f;
    }

    public void setFont(Font font) {
        this.f = font;
    }

    public Paint getPaint() {
        return this.g;
    }

    public void setPaint(Paint paint) {
        this.g = paint;
    }

    public Stroke getStroke() {
        return this.h;
    }

    public void setStroke(Stroke stroke) {
        this.h = stroke;
    }

    public AffineTransform getTransform() {
        return new AffineTransform(this.i);
    }

    public void setTransform(AffineTransform affineTransform) {
        this.i.setTransform(affineTransform);
    }

    public Color getXorMode() {
        return this.j;
    }

    public void setXorMode(Color color) {
        this.j = color;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphicsState)) {
            return false;
        }
        GraphicsState graphicsState = (GraphicsState) obj;
        if (!this.a.equals(graphicsState.a) || !this.b.equals(graphicsState.b) || !this.c.equals(graphicsState.c) || !this.e.equals(graphicsState.e) || !this.f.equals(graphicsState.f) || !this.g.equals(graphicsState.g) || !this.h.equals(graphicsState.h) || !this.i.equals(graphicsState.i) || !this.j.equals(graphicsState.j)) {
            return false;
        }
        if ((this.d == null || graphicsState.d == null) && this.d != graphicsState.d) {
            return false;
        }
        return this.d == null || this.d.equals(graphicsState.d);
    }

    public boolean isDefault() {
        return this.a.isEmpty() && this.b.equals(DEFAULT_BACKGROUND) && this.c.equals(DEFAULT_COLOR) && this.e.equals(DEFAULT_COMPOSITE) && this.f.equals(DEFAULT_FONT) && this.g.equals(DEFAULT_PAINT) && this.h.equals(DEFAULT_STROKE) && this.i.equals(DEFAULT_TRANSFORM) && this.j.equals(DEFAULT_XOR_MODE) && this.d == DEFAULT_CLIP;
    }
}
