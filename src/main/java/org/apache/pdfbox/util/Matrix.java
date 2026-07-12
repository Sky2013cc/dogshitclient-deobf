package org.apache.pdfbox.util;

import com.sun.tools.doclint.DocLint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:org/apache/pdfbox/util/Matrix.class */
public final class Matrix implements Cloneable {
    public static final int SIZE = 9;
    private float[] single;

    public Matrix() {
        this.single = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    private Matrix(float[] src) {
        this.single = src;
    }

    private Matrix(COSArray array) {
        this.single = new float[9];
        this.single[0] = ((COSNumber) array.getObject(0)).floatValue();
        this.single[1] = ((COSNumber) array.getObject(1)).floatValue();
        this.single[3] = ((COSNumber) array.getObject(2)).floatValue();
        this.single[4] = ((COSNumber) array.getObject(3)).floatValue();
        this.single[6] = ((COSNumber) array.getObject(4)).floatValue();
        this.single[7] = ((COSNumber) array.getObject(5)).floatValue();
        this.single[8] = 1.0f;
    }

    public Matrix(float a, float b, float c, float d, float e, float f) {
        this.single = new float[9];
        this.single[0] = a;
        this.single[1] = b;
        this.single[3] = c;
        this.single[4] = d;
        this.single[6] = e;
        this.single[7] = f;
        this.single[8] = 1.0f;
    }

    public Matrix(AffineTransform at) {
        this.single = new float[9];
        this.single[0] = (float) at.getScaleX();
        this.single[1] = (float) at.getShearY();
        this.single[3] = (float) at.getShearX();
        this.single[4] = (float) at.getScaleY();
        this.single[6] = (float) at.getTranslateX();
        this.single[7] = (float) at.getTranslateY();
        this.single[8] = 1.0f;
    }

    public static Matrix createMatrix(COSBase base) {
        if (!(base instanceof COSArray)) {
            return new Matrix();
        }
        COSArray array = (COSArray) base;
        if (array.size() < 6) {
            return new Matrix();
        }
        for (int i = 0; i < 6; i++) {
            if (!(array.getObject(i) instanceof COSNumber)) {
                return new Matrix();
            }
        }
        return new Matrix(array);
    }

    public AffineTransform createAffineTransform() {
        return new AffineTransform(this.single[0], this.single[1], this.single[3], this.single[4], this.single[6], this.single[7]);
    }

    public float getValue(int row, int column) {
        return this.single[(row * 3) + column];
    }

    public void setValue(int row, int column, float value) {
        this.single[(row * 3) + column] = value;
    }

    public float[][] getValues() {
        float[][] retval = new float[3][3];
        retval[0][0] = this.single[0];
        retval[0][1] = this.single[1];
        retval[0][2] = this.single[2];
        retval[1][0] = this.single[3];
        retval[1][1] = this.single[4];
        retval[1][2] = this.single[5];
        retval[2][0] = this.single[6];
        retval[2][1] = this.single[7];
        retval[2][2] = this.single[8];
        return retval;
    }

    public void concatenate(Matrix matrix) {
        this.single = checkFloatValues(multiplyArrays(matrix.single, this.single));
    }

    public void translate(Vector vector) {
        translate(vector.getX(), vector.getY());
    }

    public void translate(float tx, float ty) {
        float[] fArr = this.single;
        fArr[6] = fArr[6] + (tx * this.single[0]) + (ty * this.single[3]);
        float[] fArr2 = this.single;
        fArr2[7] = fArr2[7] + (tx * this.single[1]) + (ty * this.single[4]);
        float[] fArr3 = this.single;
        fArr3[8] = fArr3[8] + (tx * this.single[2]) + (ty * this.single[5]);
        checkFloatValues(this.single);
    }

    public void scale(float sx, float sy) {
        float[] fArr = this.single;
        fArr[0] = fArr[0] * sx;
        float[] fArr2 = this.single;
        fArr2[1] = fArr2[1] * sx;
        float[] fArr3 = this.single;
        fArr3[2] = fArr3[2] * sx;
        float[] fArr4 = this.single;
        fArr4[3] = fArr4[3] * sy;
        float[] fArr5 = this.single;
        fArr5[4] = fArr5[4] * sy;
        float[] fArr6 = this.single;
        fArr6[5] = fArr6[5] * sy;
        checkFloatValues(this.single);
    }

    public void rotate(double theta) {
        concatenate(getRotateInstance(theta, 0.0f, 0.0f));
    }

    public Matrix multiply(Matrix other) {
        return new Matrix(checkFloatValues(multiplyArrays(this.single, other.single)));
    }

    private float[] checkFloatValues(float[] values) {
        if (!Float.isFinite(values[0]) || !Float.isFinite(values[1]) || !Float.isFinite(values[2]) || !Float.isFinite(values[3]) || !Float.isFinite(values[4]) || !Float.isFinite(values[5]) || !Float.isFinite(values[6]) || !Float.isFinite(values[7]) || !Float.isFinite(values[8])) {
            throw new IllegalArgumentException("Multiplying two matrices produces illegal values");
        }
        return values;
    }

    private float[] multiplyArrays(float[] a, float[] b) {
        float[] c = {(a[0] * b[0]) + (a[1] * b[3]) + (a[2] * b[6]), (a[0] * b[1]) + (a[1] * b[4]) + (a[2] * b[7]), (a[0] * b[2]) + (a[1] * b[5]) + (a[2] * b[8]), (a[3] * b[0]) + (a[4] * b[3]) + (a[5] * b[6]), (a[3] * b[1]) + (a[4] * b[4]) + (a[5] * b[7]), (a[3] * b[2]) + (a[4] * b[5]) + (a[5] * b[8]), (a[6] * b[0]) + (a[7] * b[3]) + (a[8] * b[6]), (a[6] * b[1]) + (a[7] * b[4]) + (a[8] * b[7]), (a[6] * b[2]) + (a[7] * b[5]) + (a[8] * b[8])};
        return c;
    }

    public void transform(Point2D point) {
        float x = (float) point.getX();
        float y = (float) point.getY();
        float a = this.single[0];
        float b = this.single[1];
        float c = this.single[3];
        float d = this.single[4];
        float e = this.single[6];
        float f = this.single[7];
        point.setLocation((x * a) + (y * c) + e, (x * b) + (y * d) + f);
    }

    public Point2D.Float transformPoint(float x, float y) {
        float a = this.single[0];
        float b = this.single[1];
        float c = this.single[3];
        float d = this.single[4];
        float e = this.single[6];
        float f = this.single[7];
        return new Point2D.Float((x * a) + (y * c) + e, (x * b) + (y * d) + f);
    }

    public Vector transform(Vector vector) {
        float a = this.single[0];
        float b = this.single[1];
        float c = this.single[3];
        float d = this.single[4];
        float e = this.single[6];
        float f = this.single[7];
        float x = vector.getX();
        float y = vector.getY();
        return new Vector((x * a) + (y * c) + e, (x * b) + (y * d) + f);
    }

    public static Matrix getScaleInstance(float x, float y) {
        return new Matrix(x, 0.0f, 0.0f, y, 0.0f, 0.0f);
    }

    public static Matrix getTranslateInstance(float x, float y) {
        return new Matrix(1.0f, 0.0f, 0.0f, 1.0f, x, y);
    }

    public static Matrix getRotateInstance(double theta, float tx, float ty) {
        float cosTheta = (float) Math.cos(theta);
        float sinTheta = (float) Math.sin(theta);
        return new Matrix(cosTheta, sinTheta, -sinTheta, cosTheta, tx, ty);
    }

    public static Matrix concatenate(Matrix a, Matrix b) {
        return b.multiply(a);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Matrix m4140clone() {
        return new Matrix((float[]) this.single.clone());
    }

    public float getScalingFactorX() {
        if (Float.compare(this.single[1], 0.0f) != 0) {
            return (float) Math.sqrt(Math.pow(this.single[0], 2.0d) + Math.pow(this.single[1], 2.0d));
        }
        return this.single[0];
    }

    public float getScalingFactorY() {
        if (Float.compare(this.single[3], 0.0f) != 0) {
            return (float) Math.sqrt(Math.pow(this.single[3], 2.0d) + Math.pow(this.single[4], 2.0d));
        }
        return this.single[4];
    }

    public float getScaleX() {
        return this.single[0];
    }

    public float getShearY() {
        return this.single[1];
    }

    public float getShearX() {
        return this.single[3];
    }

    public float getScaleY() {
        return this.single[4];
    }

    public float getTranslateX() {
        return this.single[6];
    }

    public float getTranslateY() {
        return this.single[7];
    }

    public COSArray toCOSArray() {
        COSArray array = new COSArray();
        array.add((COSBase) new COSFloat(this.single[0]));
        array.add((COSBase) new COSFloat(this.single[1]));
        array.add((COSBase) new COSFloat(this.single[3]));
        array.add((COSBase) new COSFloat(this.single[4]));
        array.add((COSBase) new COSFloat(this.single[6]));
        array.add((COSBase) new COSFloat(this.single[7]));
        return array;
    }

    public String toString() {
        return RuntimeConstants.SIG_ARRAY + this.single[0] + DocLint.TAGS_SEPARATOR + this.single[1] + DocLint.TAGS_SEPARATOR + this.single[3] + DocLint.TAGS_SEPARATOR + this.single[4] + DocLint.TAGS_SEPARATOR + this.single[6] + DocLint.TAGS_SEPARATOR + this.single[7] + "]";
    }

    public int hashCode() {
        return Arrays.hashCode(this.single);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.single, ((Matrix) obj).single);
    }
}
