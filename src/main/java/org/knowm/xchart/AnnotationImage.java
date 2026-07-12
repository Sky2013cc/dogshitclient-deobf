package org.knowm.xchart;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import org.knowm.xchart.internal.chartpart.Annotation;
import org.knowm.xchart.internal.chartpart.Chart;

/* loaded from: target.jar:org/knowm/xchart/AnnotationImage.class */
public class AnnotationImage extends Annotation {
    private BufferedImage image;
    protected double x;
    protected double y;

    public AnnotationImage(BufferedImage image, double x, double y, boolean isValueInScreenSpace) {
        super(isValueInScreenSpace);
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override // org.knowm.xchart.internal.chartpart.Annotation
    public void init(Chart chart) {
        super.init(chart);
    }

    @Override // org.knowm.xchart.internal.chartpart.ChartPart
    public void paint(Graphics2D g) {
        int xOffset;
        int yOffset;
        if (!this.isVisible) {
            return;
        }
        if (this.isValueInScreenSpace) {
            xOffset = ((int) this.x) - (this.image.getWidth() / 2);
            yOffset = (this.chart.getHeight() - ((int) this.y)) - (this.image.getWidth() / 2);
        } else {
            xOffset = ((int) (getXAxisScreenValue(this.x) + 0.5d)) - (this.image.getWidth() / 2);
            yOffset = ((int) (getYAxisScreenValue(this.y) + 0.5d)) - (this.image.getHeight() / 2);
        }
        g.drawImage(this.image, xOffset, yOffset, (ImageObserver) null);
        this.bounds = new Rectangle2D.Double(xOffset, yOffset, this.image.getWidth(), this.image.getHeight());
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
