package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.beans.PropertyChangeListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import jdk.nashorn.internal.runtime.PropertyDescriptor;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport.class */
public class FlatStylingSupport {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$Styleable.class */
    public @interface Styleable {
        boolean dot() default false;

        Class<?> type() default Void.class;
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableBorder.class */
    public interface StyleableBorder {
        Object applyStyleProperty(String str, Object obj);

        Map<String, Class<?>> getStyleableInfos() throws IllegalArgumentException;

        Object getStyleableValue(String str) throws IllegalArgumentException;
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(StyleableFields.class)
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableField.class */
    public @interface StyleableField {
        Class<?> cls();

        String key();

        String fieldName() default "";
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableFields.class */
    public @interface StyleableFields {
        StyleableField[] value();
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableLookupProvider.class */
    public interface StyleableLookupProvider {
        MethodHandles.Lookup getLookupForStyling();
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableUI.class */
    public interface StyleableUI {
        Map<String, Class<?>> getStyleableInfos(JComponent jComponent) throws IllegalArgumentException;

        Object getStyleableValue(JComponent jComponent, String str) throws IllegalArgumentException;
    }

    public static Object getStyle(JComponent c) {
        return c.getClientProperty(FlatClientProperties.STYLE);
    }

    public static Object getStyleClass(JComponent c) {
        return c.getClientProperty(FlatClientProperties.STYLE_CLASS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasStyleProperty(JComponent c) {
        return (getStyle(c) == null && getStyleClass(c) == null) ? false : true;
    }

    public static Object getResolvedStyle(JComponent c, String type) throws IllegalArgumentException {
        Object style = getStyle(c);
        Object styleClass = getStyleClass(c);
        Object styleForClasses = getStyleForClasses(styleClass, type);
        return joinStyles(styleForClasses, style);
    }

    public static Object getStyleForClasses(Object styleClass, String type) throws IllegalArgumentException {
        if (styleClass == null) {
            return null;
        }
        if ((styleClass instanceof String) && ((String) styleClass).indexOf(32) >= 0) {
            styleClass = StringUtils.split((String) styleClass, ' ', true, true);
        }
        if (styleClass instanceof String) {
            return getStyleForClass(((String) styleClass).trim(), type);
        }
        if (styleClass instanceof String[]) {
            Object style = null;
            for (String cls : (String[]) styleClass) {
                style = joinStyles(style, getStyleForClass(cls, type));
            }
            return style;
        }
        if (styleClass instanceof List) {
            Object style2 = null;
            for (Object cls2 : (List) styleClass) {
                style2 = joinStyles(style2, getStyleForClass((String) cls2, type));
            }
            return style2;
        }
        return null;
    }

    private static Object getStyleForClass(String styleClass, String type) throws IllegalArgumentException {
        return joinStyles(UIManager.get("[style]." + styleClass), UIManager.get("[style]" + type + '.' + styleClass));
    }

    public static Object joinStyles(Object style1, Object style2) throws IllegalArgumentException {
        Map<String, Object> map;
        Map<? extends String, ? extends Object> map2;
        if (style1 == null) {
            return style2;
        }
        if (style2 == null) {
            return style1;
        }
        if ((style1 instanceof String) && (style2 instanceof String)) {
            return style1 + "; " + style2;
        }
        if (style1 instanceof String) {
            map = parse((String) style1);
        } else {
            map = (Map) style1;
        }
        Map<String, Object> map1 = map;
        if (map1 == null) {
            return style2;
        }
        if (style2 instanceof String) {
            map2 = parse((String) style2);
        } else {
            map2 = (Map) style2;
        }
        Map<? extends String, ? extends Object> map22 = map2;
        if (map22 == null) {
            return style1;
        }
        Map<String, Object> map3 = new HashMap<>(map1);
        map3.putAll(map22);
        return map3;
    }

    public static String concatStyles(String style1, String style2) {
        if (style1 == null) {
            return style2;
        }
        if (style2 == null) {
            return style1;
        }
        return style1 + "; " + style2;
    }

    public static Map<String, Object> parseAndApply(Map<String, Object> oldStyleValues, Object style, BiFunction<String, Object, Object> applyProperty) throws UnknownStyleException, IllegalArgumentException {
        if (oldStyleValues != null) {
            for (Map.Entry<String, Object> e : oldStyleValues.entrySet()) {
                applyProperty.apply(e.getKey(), e.getValue());
            }
        }
        if (style == null) {
            return null;
        }
        if (style instanceof String) {
            String str = (String) style;
            if (StringUtils.isTrimmedEmpty(str)) {
                return null;
            }
            return applyStyle(parse(str), applyProperty);
        }
        if (style instanceof Map) {
            Map<String, Object> map = (Map) style;
            return applyStyle(map, applyProperty);
        }
        return null;
    }

    private static Map<String, Object> applyStyle(Map<String, Object> style, BiFunction<String, Object, Object> applyProperty) {
        if (style.isEmpty()) {
            return null;
        }
        Map<String, Object> oldValues = new HashMap<>();
        for (Map.Entry<String, Object> e : style.entrySet()) {
            String key = e.getKey();
            Object newValue = e.getValue();
            if (key.startsWith(RuntimeConstants.SIG_ARRAY)) {
                if ((SystemInfo.isWindows && key.startsWith("[win]")) || ((SystemInfo.isMacOS && key.startsWith("[mac]")) || ((SystemInfo.isLinux && key.startsWith("[linux]")) || ((key.startsWith("[light]") && !FlatLaf.isLafDark()) || (key.startsWith("[dark]") && FlatLaf.isLafDark()))))) {
                    key = key.substring(key.indexOf(93) + 1);
                }
            }
            Object oldValue = applyProperty.apply(key, newValue);
            oldValues.put(key, oldValue);
        }
        return oldValues;
    }

    public static Map<String, Object> parse(String style) throws IllegalArgumentException {
        if (style == null || StringUtils.isTrimmedEmpty(style)) {
            return null;
        }
        Map<String, Object> map = null;
        for (String part : StringUtils.split(style, ';', true, true)) {
            int sepIndex = part.indexOf(58);
            if (sepIndex < 0) {
                throw new IllegalArgumentException("missing colon in '" + part + OperatorName.SHOW_TEXT_LINE);
            }
            String key = StringUtils.substringTrimmed(part, 0, sepIndex);
            String value = StringUtils.substringTrimmed(part, sepIndex + 1);
            if (key.isEmpty()) {
                throw new IllegalArgumentException("missing key in '" + part + OperatorName.SHOW_TEXT_LINE);
            }
            if (value.isEmpty()) {
                throw new IllegalArgumentException("missing value in '" + part + OperatorName.SHOW_TEXT_LINE);
            }
            if (map == null) {
                map = new LinkedHashMap<>();
            }
            map.put(key, parseValue(key, value));
        }
        return map;
    }

    private static Object parseValue(String key, String value) throws IllegalArgumentException {
        if (value.startsWith("$")) {
            return UIManager.get(value.substring(1));
        }
        if (key.startsWith(RuntimeConstants.SIG_ARRAY)) {
            key = key.substring(key.indexOf(93) + 1);
        }
        return FlatLaf.parseDefaultsValue(key, value, null);
    }

    public static Object applyToAnnotatedObject(Object obj, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
        String fieldName = keyToFieldName(key);
        return applyToField(obj, fieldName, key, value, field -> {
            Styleable styleable = (Styleable) field.getAnnotation(Styleable.class);
            if (styleable != null) {
                if (styleable.dot() == (fieldName != key)) {
                    return true;
                }
            }
            return false;
        });
    }

    private static String keyToFieldName(String key) {
        int dotIndex = key.indexOf(46);
        if (dotIndex < 0) {
            return key;
        }
        return key.substring(0, dotIndex) + Character.toUpperCase(key.charAt(dotIndex + 1)) + key.substring(dotIndex + 2);
    }

    static Object applyToField(Object obj, String fieldName, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
        return applyToField(obj, fieldName, key, value, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ae, code lost:
    
        throw new com.formdev.flatlaf.ui.FlatStylingSupport.UnknownStyleException(r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object applyToField(Object obj, String fieldName, String key, Object value, Predicate<Field> predicate) throws UnknownStyleException, IllegalArgumentException {
        Class<?> cls = obj.getClass();
        while (true) {
            try {
                Field f = cls.getDeclaredField(fieldName);
                if (predicate == null || predicate.test(f)) {
                    return applyToField(f, obj, value, false);
                }
            } catch (NoSuchFieldException e) {
            }
            for (StyleableField styleableField : (StyleableField[]) cls.getAnnotationsByType(StyleableField.class)) {
                if (key.equals(styleableField.key())) {
                    return applyToField(getStyleableField(styleableField), obj, value, true);
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                throw new UnknownStyleException(key);
            }
            if (predicate != null) {
                String superclassName = cls.getName();
                if (superclassName.startsWith("java.") || superclassName.startsWith("javax.")) {
                    break;
                }
            }
        }
    }

    private static Object applyToField(Field f, Object obj, Object value, boolean useMethodHandles) throws IllegalArgumentException {
        checkValidField(f);
        if (useMethodHandles && (obj instanceof StyleableLookupProvider)) {
            try {
                MethodHandles.Lookup lookup = ((StyleableLookupProvider) obj).getLookupForStyling();
                Object oldValue = (Object) lookup.unreflectGetter(f).invoke(obj);
                (void) lookup.unreflectSetter(f).invoke(obj, convertToEnum(value, f.getType()));
                return oldValue;
            } finally {
                IllegalArgumentException newFieldAccessFailed = newFieldAccessFailed(f, ex);
            }
        }
        try {
            f.setAccessible(true);
            Object oldValue2 = f.get(obj);
            f.set(obj, convertToEnum(value, f.getType()));
            return oldValue2;
        } catch (IllegalAccessException ex) {
            throw newFieldAccessFailed(f, ex);
        }
    }

    private static Object getFieldValue(Field f, Object obj, boolean useMethodHandles) throws IllegalArgumentException {
        checkValidField(f);
        if (useMethodHandles && (obj instanceof StyleableLookupProvider)) {
            try {
                MethodHandles.Lookup lookup = ((StyleableLookupProvider) obj).getLookupForStyling();
                return (Object) lookup.unreflectGetter(f).invoke(obj);
            } finally {
                IllegalArgumentException newFieldAccessFailed = newFieldAccessFailed(f, ex);
            }
        }
        try {
            f.setAccessible(true);
            return f.get(obj);
        } catch (IllegalAccessException ex) {
            throw newFieldAccessFailed(f, ex);
        }
    }

    private static IllegalArgumentException newFieldAccessFailed(Field f, Throwable ex) {
        return new IllegalArgumentException("failed to access field '" + f.getDeclaringClass().getName() + Constants.NAME_SEPARATOR + f.getName() + OperatorName.SHOW_TEXT_LINE, ex);
    }

    private static void checkValidField(Field f) throws IllegalArgumentException {
        if (!isValidField(f)) {
            throw new IllegalArgumentException("field '" + f.getDeclaringClass().getName() + Constants.NAME_SEPARATOR + f.getName() + "' is final or static");
        }
    }

    private static boolean isValidField(Field f) {
        int modifiers = f.getModifiers();
        return (modifiers & 24) == 0 && !f.isSynthetic();
    }

    private static Field getStyleableField(StyleableField styleableField) throws IllegalArgumentException {
        String fieldName = styleableField.fieldName();
        if (fieldName.isEmpty()) {
            fieldName = styleableField.key();
        }
        try {
            return styleableField.cls().getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            throw new IllegalArgumentException("field '" + styleableField.cls().getName() + Constants.NAME_SEPARATOR + fieldName + "' not found", ex);
        }
    }

    private static Object applyToProperty(Object obj, String name, Object value) throws UnknownStyleException, IllegalArgumentException {
        Method getter;
        Class<?> cls = obj.getClass();
        String getterName = buildMethodName(PropertyDescriptor.GET, name);
        String setterName = buildMethodName(PropertyDescriptor.SET, name);
        try {
            try {
                getter = cls.getMethod(getterName, new Class[0]);
            } catch (NoSuchMethodException e) {
                getter = cls.getMethod(buildMethodName("is", name), new Class[0]);
            }
            Method setter = cls.getMethod(setterName, getter.getReturnType());
            Object oldValue = getter.invoke(obj, new Object[0]);
            setter.invoke(obj, convertToEnum(value, getter.getReturnType()));
            return oldValue;
        } catch (NoSuchMethodException e2) {
            throw new UnknownStyleException(name);
        } catch (Exception ex) {
            throw new IllegalArgumentException("failed to invoke property methods '" + cls.getName() + Constants.NAME_SEPARATOR + getterName + "()' or '" + setterName + "(...)'", ex);
        }
    }

    private static String buildMethodName(String prefix, String name) {
        int prefixLength = prefix.length();
        int nameLength = name.length();
        char[] chars = new char[prefixLength + nameLength];
        prefix.getChars(0, prefixLength, chars, 0);
        name.getChars(0, nameLength, chars, prefixLength);
        chars[prefixLength] = Character.toUpperCase(chars[prefixLength]);
        return new String(chars);
    }

    private static Object convertToEnum(Object value, Class<?> type) throws IllegalArgumentException {
        if (Enum.class.isAssignableFrom(type) && (value instanceof String)) {
            try {
                value = Enum.valueOf(type, (String) value);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("unknown enum value '" + value + "' in enum '" + type.getName() + OperatorName.SHOW_TEXT_LINE, ex);
            }
        }
        return value;
    }

    public static Object applyToAnnotatedObjectOrComponent(Object obj, Object comp, String key, Object value) throws UnknownStyleException, IllegalArgumentException {
        try {
            return applyToAnnotatedObject(obj, key, value);
        } catch (UnknownStyleException ex) {
            if (comp != null) {
                try {
                    return applyToProperty(comp, key, value);
                } catch (UnknownStyleException e) {
                    throw ex;
                }
            }
            throw ex;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object applyToAnnotatedObjectOrBorder(Object obj, String key, Object value, JComponent c, AtomicBoolean borderShared) throws IllegalArgumentException {
        try {
            return applyToAnnotatedObject(obj, key, value);
        } catch (UnknownStyleException ex) {
            Border border = c.getBorder();
            if (border instanceof StyleableBorder) {
                if (borderShared.get()) {
                    border = cloneBorder(border);
                    c.setBorder(border);
                    borderShared.set(false);
                }
                try {
                    return ((StyleableBorder) border).applyStyleProperty(key, value);
                } catch (UnknownStyleException e) {
                    return applyToProperty(c, key, value);
                }
            }
            try {
                return applyToProperty(c, key, value);
            } catch (UnknownStyleException e2) {
                throw ex;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PropertyChangeListener createPropertyChangeListener(JComponent c, Runnable installStyle, PropertyChangeListener superListener) {
        return e -> {
            if (superListener != null) {
                superListener.propertyChange(e);
            }
            String propertyName = e.getPropertyName();
            boolean z = -1;
            switch (propertyName.hashCode()) {
                case 1030195901:
                    if (propertyName.equals(FlatClientProperties.STYLE_CLASS)) {
                        z = true;
                        break;
                    }
                    break;
                case 1545413499:
                    if (propertyName.equals(FlatClientProperties.STYLE)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                case true:
                    installStyle.run();
                    c.revalidate();
                    HiDPIUtils.repaint(c);
                    return;
                default:
                    return;
            }
        };
    }

    static Border cloneBorder(Border border) throws IllegalArgumentException {
        Class<?> cls = border.getClass();
        try {
            return (Border) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception ex) {
            throw new IllegalArgumentException("failed to clone border '" + cls.getName() + OperatorName.SHOW_TEXT_LINE, ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Icon cloneIcon(Icon icon) throws IllegalArgumentException {
        Class<?> cls = icon.getClass();
        try {
            return (Icon) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception ex) {
            throw new IllegalArgumentException("failed to clone icon '" + cls.getName() + OperatorName.SHOW_TEXT_LINE, ex);
        }
    }

    public static Map<String, Class<?>> getAnnotatedStyleableInfos(Object obj) throws IllegalArgumentException {
        return getAnnotatedStyleableInfos(obj, null);
    }

    public static Map<String, Class<?>> getAnnotatedStyleableInfos(Object obj, Border border) throws IllegalArgumentException {
        Map<String, Class<?>> infos = new StyleableInfosMap<>();
        collectAnnotatedStyleableInfos(obj, infos);
        collectStyleableInfos(border, infos);
        return infos;
    }

    public static void collectAnnotatedStyleableInfos(Object obj, Map<String, Class<?>> infos) throws IllegalArgumentException {
        String superclassName;
        Styleable styleable;
        HashSet<String> processedFields = new HashSet<>();
        Class<?> cls = obj.getClass();
        do {
            for (Field f : cls.getDeclaredFields()) {
                if (isValidField(f) && (styleable = (Styleable) f.getAnnotation(Styleable.class)) != null) {
                    String name = f.getName();
                    Class<?> type = f.getType();
                    if (!processedFields.contains(name)) {
                        processedFields.add(name);
                        if (styleable.dot()) {
                            int len = name.length();
                            int i = 0;
                            while (true) {
                                if (i >= len) {
                                    break;
                                }
                                if (!Character.isUpperCase(name.charAt(i))) {
                                    i++;
                                } else {
                                    name = name.substring(0, i) + '.' + Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
                                    break;
                                }
                            }
                        }
                        if (styleable.type() != Void.class) {
                            type = styleable.type();
                        }
                        infos.put(name, type);
                    }
                }
            }
            for (StyleableField styleableField : (StyleableField[]) cls.getAnnotationsByType(StyleableField.class)) {
                String name2 = styleableField.key();
                if (!processedFields.contains(name2)) {
                    processedFields.add(name2);
                    infos.put(name2, getStyleableField(styleableField).getType());
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return;
            }
            superclassName = cls.getName();
            if (superclassName.startsWith("java.")) {
                return;
            }
        } while (!superclassName.startsWith("javax."));
    }

    public static void collectStyleableInfos(Border border, Map<String, Class<?>> infos) {
        if (border instanceof StyleableBorder) {
            infos.putAll(((StyleableBorder) border).getStyleableInfos());
        }
    }

    public static void putAllPrefixKey(Map<String, Class<?>> infos, String keyPrefix, Map<String, Class<?>> infos2) {
        for (Map.Entry<String, Class<?>> e : infos2.entrySet()) {
            infos.put(keyPrefix.concat(e.getKey()), e.getValue());
        }
    }

    public static Object getAnnotatedStyleableValue(Object obj, String key) throws IllegalArgumentException {
        String superclassName;
        String fieldName = keyToFieldName(key);
        Class<?> cls = obj.getClass();
        do {
            try {
                Field f = cls.getDeclaredField(fieldName);
                Styleable styleable = (Styleable) f.getAnnotation(Styleable.class);
                if (styleable != null) {
                    if (styleable.dot() != (fieldName != key)) {
                        throw new IllegalArgumentException("'Styleable.dot' on field '" + fieldName + "' does not match key '" + key + OperatorName.SHOW_TEXT_LINE);
                    }
                    if (styleable.type() != Void.class) {
                        throw new IllegalArgumentException("'Styleable.type' on field '" + fieldName + "' not supported");
                    }
                    return getFieldValue(f, obj, false);
                }
            } catch (NoSuchFieldException e) {
            }
            for (StyleableField styleableField : (StyleableField[]) cls.getAnnotationsByType(StyleableField.class)) {
                if (key.equals(styleableField.key())) {
                    return getFieldValue(getStyleableField(styleableField), obj, true);
                }
            }
            cls = cls.getSuperclass();
            if (cls == null) {
                return null;
            }
            superclassName = cls.getName();
            if (superclassName.startsWith("java.")) {
                return null;
            }
        } while (!superclassName.startsWith("javax."));
        return null;
    }

    public static Object getAnnotatedStyleableValue(Object obj, Border border, String key) {
        Object value;
        if ((border instanceof StyleableBorder) && (value = ((StyleableBorder) border).getStyleableValue(key)) != null) {
            return value;
        }
        return getAnnotatedStyleableValue(obj, key);
    }

    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$UnknownStyleException.class */
    public static class UnknownStyleException extends IllegalArgumentException {
        public UnknownStyleException(String key) {
            super(key);
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "unknown style '" + super.getMessage() + OperatorName.SHOW_TEXT_LINE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/formdev/flatlaf/ui/FlatStylingSupport$StyleableInfosMap.class */
    public static class StyleableInfosMap<K, V> extends LinkedHashMap<K, V> {
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public V put(K k, V v) throws IllegalArgumentException {
            V v2 = (V) super.put(k, v);
            if (v2 != null) {
                throw new IllegalArgumentException("duplicate key '" + k + OperatorName.SHOW_TEXT_LINE);
            }
            return v2;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> m) {
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                put(e.getKey(), e.getValue());
            }
        }
    }
}
