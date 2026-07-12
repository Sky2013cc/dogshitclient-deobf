package org.knowm.xchart.style.markers;

/* loaded from: target.jar:org/knowm/xchart/style/markers/SeriesMarkers.class */
public interface SeriesMarkers {
    public static final Marker NONE = new None();
    public static final Marker CIRCLE = new Circle();
    public static final Marker DIAMOND = new Diamond();
    public static final Marker SQUARE = new Square();
    public static final Marker TRIANGLE_DOWN = new TriangleDown();
    public static final Marker TRIANGLE_UP = new TriangleUp();
    public static final Marker CROSS = new Cross();
    public static final Marker PLUS = new Plus();
    public static final Marker TRAPEZOID = new Trapezoid();
    public static final Marker OVAL = new Oval();
    public static final Marker RECTANGLE = new Rectangle();

    Marker[] getSeriesMarkers();
}
