package org.knowm.xchart.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: target.jar:org/knowm/xchart/internal/Utils.class */
public class Utils {
    private Utils() {
    }

    public static double getTickStartOffset(double workingSpace, double tickSpace) {
        double marginSpace = workingSpace - tickSpace;
        return marginSpace / 2.0d;
    }

    public static double pow(double base, int exponent) {
        if (exponent > 0) {
            return Math.pow(base, exponent);
        }
        return 1.0d / Math.pow(base, (-1) * exponent);
    }

    public static List<Double> getNumberListFromDoubleArray(double[] data) {
        if (data == null) {
            return null;
        }
        List<Double> dataNumber = new ArrayList<>();
        for (double d : data) {
            dataNumber.add(Double.valueOf(d));
        }
        return dataNumber;
    }

    public static List<Double> getNumberListFromIntArray(int[] data) {
        if (data == null) {
            return null;
        }
        List<Double> dataNumber = new ArrayList<>();
        for (double d : data) {
            dataNumber.add(Double.valueOf(d));
        }
        return dataNumber;
    }

    public static List<Double> getGeneratedDataAsList(int length) {
        List<Double> generatedData = new ArrayList<>();
        for (int i = 1; i < length + 1; i++) {
            generatedData.add(Double.valueOf(i));
        }
        return generatedData;
    }

    public static double[] getDoubleArrayFromFloatArray(float[] data) {
        if (data == null) {
            return null;
        }
        double[] doubles = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            doubles[i] = data[i];
        }
        return doubles;
    }

    public static double[] getDoubleArrayFromIntArray(int[] data) {
        if (data == null) {
            return null;
        }
        double[] doubles = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            doubles[i] = data[i];
        }
        return doubles;
    }

    public static double[] getDoubleArrayFromNumberList(List<?> data) {
        if (data == null) {
            return null;
        }
        double[] doubles = new double[data.size()];
        int i = 0;
        for (Object number : data) {
            if (number == null) {
                int i2 = i;
                i++;
                doubles[i2] = Double.NaN;
            } else {
                int i3 = i;
                i++;
                doubles[i3] = ((Number) number).doubleValue();
            }
        }
        return doubles;
    }

    public static double[] getDoubleArrayFromDateList(List<?> data) {
        if (data == null) {
            return null;
        }
        double[] doubles = new double[data.size()];
        int i = 0;
        for (Object date : data) {
            int i2 = i;
            i++;
            doubles[i2] = ((Date) date).getTime();
        }
        return doubles;
    }

    public static double[] getGeneratedDataAsArray(int length) {
        double[] generatedData = new double[length];
        for (int i = 0; i < length; i++) {
            generatedData[i] = i + 1.0d;
        }
        return generatedData;
    }

    public static long[] getLongArrayFromIntArray(int[] data) {
        if (data == null) {
            return null;
        }
        long[] longs = new long[data.length];
        for (int i = 0; i < data.length; i++) {
            longs[i] = data[i];
        }
        return longs;
    }

    public static long[] getLongArrayFromFloatArray(float[] data) {
        if (data == null) {
            return null;
        }
        long[] longs = new long[data.length];
        for (int i = 0; i < data.length; i++) {
            longs[i] = data[i];
        }
        return longs;
    }

    public static long[] getLongArrayFromNumberList(List<?> data) {
        if (data == null) {
            return null;
        }
        long[] longs = new long[data.size()];
        int i = 0;
        for (Object number : data) {
            if (number == null) {
                int i2 = i;
                i++;
                longs[i2] = 0;
            } else {
                int i3 = i;
                i++;
                longs[i3] = ((Number) number).longValue();
            }
        }
        return longs;
    }

    public static String addFileExtension(String fileName, String fileExtension) {
        String fileNameWithFileExtension = fileName;
        if (fileName.length() <= fileExtension.length() || !fileName.substring(fileName.length() - fileExtension.length()).equalsIgnoreCase(fileExtension)) {
            fileNameWithFileExtension = fileName + fileExtension;
        }
        return fileNameWithFileExtension;
    }
}
