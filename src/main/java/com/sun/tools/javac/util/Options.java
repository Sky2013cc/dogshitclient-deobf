package com.sun.tools.javac.util;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Context;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/javac/util/Options.class */
public class Options {
    private static final long serialVersionUID = 0;
    public static final Context.Key<Options> optionsKey = new Context.Key<>();
    private List<Runnable> listeners = List.nil();
    private LinkedHashMap<String, String> values = new LinkedHashMap<>();

    public static Options instance(Context context) {
        Options options = (Options) context.get(optionsKey);
        if (options == null) {
            options = new Options(context);
        }
        return options;
    }

    protected Options(Context context) {
        context.put((Context.Key<Context.Key<Options>>) optionsKey, (Context.Key<Options>) this);
    }

    public String get(String str) {
        return this.values.get(str);
    }

    public String get(Option option) {
        return this.values.get(option.text);
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        String str2 = get(str);
        return str2 == null ? z : Boolean.parseBoolean(str2);
    }

    public boolean isSet(String str) {
        return this.values.get(str) != null;
    }

    public boolean isSet(Option option) {
        return this.values.get(option.text) != null;
    }

    public boolean isSet(Option option, String str) {
        return this.values.get(new StringBuilder().append(option.text).append(str).toString()) != null;
    }

    public boolean isUnset(String str) {
        return this.values.get(str) == null;
    }

    public boolean isUnset(Option option) {
        return this.values.get(option.text) == null;
    }

    public boolean isUnset(Option option, String str) {
        return this.values.get(new StringBuilder().append(option.text).append(str).toString()) == null;
    }

    public void put(String str, String str2) {
        this.values.put(str, str2);
    }

    public void put(Option option, String str) {
        this.values.put(option.text, str);
    }

    public void putAll(Options options) {
        this.values.putAll(options.values);
    }

    public void remove(String str) {
        this.values.remove(str);
    }

    public Set<String> keySet() {
        return this.values.keySet();
    }

    public int size() {
        return this.values.size();
    }

    public void addListener(Runnable runnable) {
        this.listeners = this.listeners.prepend(runnable);
    }

    public void notifyListeners() {
        Iterator<Runnable> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public boolean lint(String str) {
        return isSet(Option.XLINT_CUSTOM, str) || ((isSet(Option.XLINT) || isSet(Option.XLINT_CUSTOM, "all")) && isUnset(Option.XLINT_CUSTOM, new StringBuilder().append(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR).append(str).toString()));
    }
}
