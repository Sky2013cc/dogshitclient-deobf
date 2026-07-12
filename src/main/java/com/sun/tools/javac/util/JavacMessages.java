package com.sun.tools.javac.util;

import com.sun.tools.javac.api.Messages;
import com.sun.tools.javac.util.Context;
import java.lang.ref.SoftReference;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/javac/util/JavacMessages.class */
public class JavacMessages implements Messages {
    public static final Context.Key<JavacMessages> messagesKey = new Context.Key<>();
    private Map<Locale, SoftReference<List<ResourceBundle>>> bundleCache;
    private List<String> bundleNames;
    private Locale currentLocale;
    private List<ResourceBundle> currentBundles;
    private static final String defaultBundleName = "com.sun.tools.javac.resources.compiler";
    private static ResourceBundle defaultBundle;
    private static JavacMessages defaultMessages;

    public static JavacMessages instance(Context context) {
        JavacMessages javacMessages = (JavacMessages) context.get(messagesKey);
        if (javacMessages == null) {
            javacMessages = new JavacMessages(context);
        }
        return javacMessages;
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public void setCurrentLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.currentBundles = getBundles(locale);
        this.currentLocale = locale;
    }

    public JavacMessages(Context context) {
        this(defaultBundleName, (Locale) context.get(Locale.class));
        context.put((Context.Key<Context.Key<JavacMessages>>) messagesKey, (Context.Key<JavacMessages>) this);
    }

    public JavacMessages(String str) throws MissingResourceException {
        this(str, null);
    }

    public JavacMessages(String str, Locale locale) throws MissingResourceException {
        this.bundleNames = List.nil();
        this.bundleCache = new HashMap();
        add(str);
        setCurrentLocale(locale);
    }

    public JavacMessages() throws MissingResourceException {
        this(defaultBundleName);
    }

    @Override // com.sun.tools.javac.api.Messages
    public void add(String str) throws MissingResourceException {
        this.bundleNames = this.bundleNames.prepend(str);
        if (!this.bundleCache.isEmpty()) {
            this.bundleCache.clear();
        }
        this.currentBundles = null;
    }

    public List<ResourceBundle> getBundles(Locale locale) {
        if (locale == this.currentLocale && this.currentBundles != null) {
            return this.currentBundles;
        }
        SoftReference<List<ResourceBundle>> softReference = this.bundleCache.get(locale);
        List<ResourceBundle> list = softReference == null ? null : softReference.get();
        if (list == null) {
            list = List.nil();
            Iterator<String> it = this.bundleNames.iterator();
            while (it.hasNext()) {
                try {
                    list = list.prepend(ResourceBundle.getBundle(it.next(), locale));
                } catch (MissingResourceException e) {
                    throw new InternalError("Cannot find javac resource bundle for locale " + locale);
                }
            }
            this.bundleCache.put(locale, new SoftReference<>(list));
        }
        return list;
    }

    public String getLocalizedString(String str, Object... objArr) {
        return getLocalizedString(this.currentLocale, str, objArr);
    }

    @Override // com.sun.tools.javac.api.Messages
    public String getLocalizedString(Locale locale, String str, Object... objArr) {
        if (locale == null) {
            locale = getCurrentLocale();
        }
        return getLocalizedString(getBundles(locale), str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getDefaultLocalizedString(String str, Object... objArr) {
        return getLocalizedString((List<ResourceBundle>) List.of(getDefaultBundle()), str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static JavacMessages getDefaultMessages() {
        if (defaultMessages == null) {
            defaultMessages = new JavacMessages(defaultBundleName);
        }
        return defaultMessages;
    }

    public static ResourceBundle getDefaultBundle() {
        try {
            if (defaultBundle == null) {
                defaultBundle = ResourceBundle.getBundle(defaultBundleName);
            }
            return defaultBundle;
        } catch (MissingResourceException e) {
            throw new Error("Fatal: Resource for compiler is missing", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String getLocalizedString(List<ResourceBundle> list, String str, Object... objArr) {
        String str2 = null;
        List list2 = list;
        while (true) {
            List list3 = list2;
            if (!list3.nonEmpty() || str2 != null) {
                break;
            }
            try {
                str2 = ((ResourceBundle) list3.head).getString(str);
            } catch (MissingResourceException e) {
            }
            list2 = list3.tail;
        }
        if (str2 == null) {
            str2 = "compiler message file broken: key=" + str + " arguments={0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}";
        }
        return MessageFormat.format(str2, objArr);
    }
}
