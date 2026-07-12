package com.sun.tools.hat.internal.util;

import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/hat/internal/util/VectorSorter.class */
public class VectorSorter {
    public static void sort(Vector<Object> vector, Comparer comparer) {
        quickSort(vector, comparer, 0, vector.size() - 1);
    }

    public static void sortVectorOfStrings(Vector<Object> vector) {
        sort(vector, new Comparer() { // from class: com.sun.tools.hat.internal.util.VectorSorter.1
            @Override // com.sun.tools.hat.internal.util.Comparer
            public int compare(Object obj, Object obj2) {
                return ((String) obj).compareTo((String) obj2);
            }
        });
    }

    private static void swap(Vector<Object> vector, int i, int i2) {
        Object elementAt = vector.elementAt(i);
        vector.setElementAt(vector.elementAt(i2), i);
        vector.setElementAt(elementAt, i2);
    }

    private static void quickSort(Vector<Object> vector, Comparer comparer, int i, int i2) {
        int compare;
        if (i2 <= i) {
            return;
        }
        int i3 = (i + i2) / 2;
        if (i3 != i) {
            swap(vector, i3, i);
        }
        Object elementAt = vector.elementAt(i);
        int i4 = i - 1;
        int i5 = i + 1;
        int i6 = i2;
        while (i5 <= i6) {
            int compare2 = comparer.compare(vector.elementAt(i5), elementAt);
            if (compare2 <= 0) {
                if (compare2 < 0) {
                    i4 = i5;
                }
                i5++;
            } else {
                do {
                    compare = comparer.compare(vector.elementAt(i6), elementAt);
                    if (compare <= 0) {
                        break;
                    } else {
                        i6--;
                    }
                } while (i5 <= i6);
                if (i5 <= i6) {
                    swap(vector, i5, i6);
                    if (compare < 0) {
                        i4 = i5;
                    }
                    i5++;
                    i6--;
                }
            }
        }
        if (i4 > i) {
            swap(vector, i, i4);
            quickSort(vector, comparer, i, i4 - 1);
        }
        quickSort(vector, comparer, i6 + 1, i2);
    }
}
