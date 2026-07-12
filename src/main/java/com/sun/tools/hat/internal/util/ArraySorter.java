package com.sun.tools.hat.internal.util;

/* loaded from: target.jar:com/sun/tools/hat/internal/util/ArraySorter.class */
public class ArraySorter {
    public static void sort(Object[] objArr, Comparer comparer) {
        quickSort(objArr, comparer, 0, objArr.length - 1);
    }

    public static void sortArrayOfStrings(Object[] objArr) {
        sort(objArr, new Comparer() { // from class: com.sun.tools.hat.internal.util.ArraySorter.1
            @Override // com.sun.tools.hat.internal.util.Comparer
            public int compare(Object obj, Object obj2) {
                return ((String) obj).compareTo((String) obj2);
            }
        });
    }

    private static void swap(Object[] objArr, int i, int i2) {
        Object obj = objArr[i];
        objArr[i] = objArr[i2];
        objArr[i2] = obj;
    }

    private static void quickSort(Object[] objArr, Comparer comparer, int i, int i2) {
        int compare;
        if (i2 <= i) {
            return;
        }
        int i3 = (i + i2) / 2;
        if (i3 != i) {
            swap(objArr, i3, i);
        }
        Object obj = objArr[i];
        int i4 = i - 1;
        int i5 = i + 1;
        int i6 = i2;
        while (i5 <= i6) {
            int compare2 = comparer.compare(objArr[i5], obj);
            if (compare2 <= 0) {
                if (compare2 < 0) {
                    i4 = i5;
                }
                i5++;
            } else {
                do {
                    compare = comparer.compare(objArr[i6], obj);
                    if (compare <= 0) {
                        break;
                    } else {
                        i6--;
                    }
                } while (i5 <= i6);
                if (i5 <= i6) {
                    swap(objArr, i5, i6);
                    if (compare < 0) {
                        i4 = i5;
                    }
                    i5++;
                    i6--;
                }
            }
        }
        if (i4 > i) {
            swap(objArr, i, i4);
            quickSort(objArr, comparer, i, i4 - 1);
        }
        quickSort(objArr, comparer, i6 + 1, i2);
    }
}
