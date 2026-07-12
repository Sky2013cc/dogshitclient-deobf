package com.formdev.flatlaf.ui;

import java.util.function.BiPredicate;

/* loaded from: target.jar:com/formdev/flatlaf/ui/StackUtilsImpl.class */
class StackUtilsImpl extends StackUtils {
    @Override // com.formdev.flatlaf.ui.StackUtils
    boolean wasInvokedFromImpl(BiPredicate<String, String> predicate, int limit) {
        int count = -2;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (predicate.test(stackTraceElement.getClassName(), stackTraceElement.getMethodName())) {
                return true;
            }
            count++;
            if (limit > 0 && count > limit) {
                return false;
            }
        }
        return false;
    }
}
