package com.sun.tools.example.debug.tty;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/MessageOutput.class */
public class MessageOutput {
    static ResourceBundle textResources;
    private static MessageFormat messageFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fatalError(String str) {
        System.err.println();
        System.err.println(format("Fatal error"));
        System.err.println(format(str));
        Env.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(String str) {
        return textResources.getString(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(String str, String str2) {
        return format(str, new Object[]{str2});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized String format(String str, Object[] objArr) {
        if (messageFormat == null) {
            messageFormat = new MessageFormat(textResources.getString(str));
        } else {
            messageFormat.applyPattern(textResources.getString(str));
        }
        return messageFormat.format(objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printDirectln(String str) {
        System.out.println(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printDirect(String str) {
        System.out.print(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printDirect(char c) {
        System.out.print(c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void println() {
        System.out.println();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void print(String str) {
        System.out.print(format(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void println(String str) {
        System.out.println(format(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void print(String str, String str2) {
        System.out.print(format(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void println(String str, String str2) {
        System.out.println(format(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void println(String str, Object[] objArr) {
        System.out.println(format(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void lnprint(String str) {
        System.out.println();
        System.out.print(textResources.getString(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void lnprint(String str, String str2) {
        System.out.println();
        System.out.print(format(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void lnprint(String str, Object[] objArr) {
        System.out.println();
        System.out.print(format(str, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printException(String str, Exception exc) {
        if (str != null) {
            try {
                println(str);
            } catch (MissingResourceException e) {
                printDirectln(str);
            }
        }
        System.out.flush();
        exc.printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void printPrompt() {
        ThreadInfo currentThreadInfo = ThreadInfo.getCurrentThreadInfo();
        if (currentThreadInfo == null) {
            System.out.print(format("jdb prompt with no current thread"));
        } else {
            System.out.print(format("jdb prompt thread name and current stack frame", new Object[]{currentThreadInfo.getThread().name(), new Integer(currentThreadInfo.getCurrentFrameIndex() + 1)}));
        }
        System.out.flush();
    }
}
