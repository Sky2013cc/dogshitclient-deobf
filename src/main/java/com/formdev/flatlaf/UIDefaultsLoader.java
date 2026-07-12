package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.HSLColor;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SoftCache;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import javax.swing.Icon;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.tools.java.RuntimeConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/formdev/flatlaf/UIDefaultsLoader.class */
public class UIDefaultsLoader {
    private static final String TYPE_PREFIX = "{";
    private static final String TYPE_PREFIX_END = "}";
    private static final String VARIABLE_PREFIX = "@";
    private static final String PROPERTY_PREFIX = "$";
    private static final String OPTIONAL_PREFIX = "?";
    private static final String WILDCARD_PREFIX = "*.";
    static final String KEY_VARIABLES = "FlatLaf.internal.variables";
    private static int parseColorDepth;
    private static Map<String, ColorUIResource> systemColorCache;
    private static final SoftCache<String, Object> fontCache = new SoftCache<>();
    private static final ValueType[] tempResultValueType = new ValueType[1];
    private static Map<Class<?>, ValueType> javaValueTypes;
    private static Map<String, ValueType> knownValueTypes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/formdev/flatlaf/UIDefaultsLoader$ValueType.class */
    public enum ValueType {
        UNKNOWN,
        STRING,
        BOOLEAN,
        CHARACTER,
        INTEGER,
        INTEGERORFLOAT,
        FLOAT,
        BORDER,
        ICON,
        INSETS,
        DIMENSION,
        COLOR,
        FONT,
        SCALEDINTEGER,
        SCALEDFLOAT,
        SCALEDINSETS,
        SCALEDDIMENSION,
        INSTANCE,
        CLASS,
        GRAYFILTER,
        NULL,
        LAZY
    }

    UIDefaultsLoader() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadDefaultsFromProperties(Class<?> lookAndFeelClass, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
        ArrayList<Class<?>> lafClasses = new ArrayList<>();
        Class<?> cls = lookAndFeelClass;
        while (true) {
            Class<?> lafClass = cls;
            if (FlatLaf.class.isAssignableFrom(lafClass)) {
                lafClasses.add(0, lafClass);
                cls = lafClass.getSuperclass();
            } else {
                loadDefaultsFromProperties(lafClasses, addons, additionalDefaults, dark, defaults);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadDefaultsFromProperties(List<Class<?>> lafClasses, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
        int dot;
        String str;
        InputStream in;
        InputStream in2;
        try {
            systemColorCache = FlatLaf.getSystemColorGetter() != null ? new HashMap() : null;
            Properties properties = new Properties();
            for (Class<?> lafClass : lafClasses) {
                String propertiesName = '/' + lafClass.getName().replace('.', '/') + ".properties";
                in2 = lafClass.getResourceAsStream(propertiesName);
                if (in2 != null) {
                    try {
                        properties.load(in2);
                    } finally {
                    }
                }
                if (in2 != null) {
                    in2.close();
                }
            }
            for (FlatDefaultsAddon addon : addons) {
                for (Class<?> lafClass2 : lafClasses) {
                    InputStream in3 = addon.getDefaults(lafClass2);
                    if (in3 != null) {
                        try {
                            properties.load(in3);
                        } finally {
                        }
                    }
                    if (in3 != null) {
                        in3.close();
                    }
                }
            }
            List<ClassLoader> addonClassLoaders = new ArrayList<>();
            for (FlatDefaultsAddon addon2 : addons) {
                ClassLoader addonClassLoader = addon2.getClass().getClassLoader();
                if (!addonClassLoaders.contains(addonClassLoader)) {
                    addonClassLoaders.add(addonClassLoader);
                }
            }
            List<Object> customDefaultsSources = FlatLaf.getCustomDefaultsSources();
            int size = customDefaultsSources != null ? customDefaultsSources.size() : 0;
            int i = 0;
            loop4: while (i < size) {
                Object source = customDefaultsSources.get(i);
                if ((source instanceof String) && i + 1 < size) {
                    String packageName = (String) source;
                    i++;
                    ClassLoader classLoader = (ClassLoader) customDefaultsSources.get(i);
                    if (classLoader != null && !addonClassLoaders.contains(classLoader)) {
                        addonClassLoaders.add(classLoader);
                    }
                    String packageName2 = packageName.replace('.', '/');
                    if (classLoader == null) {
                        classLoader = FlatLaf.class.getClassLoader();
                    }
                    for (Class<?> lafClass3 : lafClasses) {
                        String propertiesName2 = packageName2 + '/' + simpleClassName(lafClass3) + ".properties";
                        in2 = classLoader.getResourceAsStream(propertiesName2);
                        if (in2 != null) {
                            try {
                                properties.load(in2);
                            } finally {
                                if (in2 != null) {
                                    try {
                                        in2.close();
                                    } catch (Throwable th) {
                                        th.addSuppressed(th);
                                    }
                                }
                            }
                        }
                        if (in2 != null) {
                            in2.close();
                        }
                    }
                } else if (source instanceof URL) {
                    URL packageUrl = (URL) source;
                    for (Class<?> lafClass4 : lafClasses) {
                        URL propertiesUrl = new URL(packageUrl + simpleClassName(lafClass4) + ".properties");
                        try {
                            in = propertiesUrl.openStream();
                        } catch (FileNotFoundException e) {
                        }
                        try {
                            properties.load(in);
                            if (in != null) {
                                in.close();
                            }
                        } catch (Throwable th2) {
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            }
                            throw th2;
                            break loop4;
                        }
                    }
                } else if (source instanceof File) {
                    File folder = (File) source;
                    for (Class<?> lafClass5 : lafClasses) {
                        File propertiesFile = new File(folder, simpleClassName(lafClass5) + ".properties");
                        if (propertiesFile.isFile()) {
                            InputStream in4 = new FileInputStream(propertiesFile);
                            try {
                                properties.load(in4);
                                in4.close();
                            } finally {
                                try {
                                    in4.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            }
                        }
                    }
                } else {
                    continue;
                }
                i++;
            }
            if (additionalDefaults != null) {
                properties.putAll(additionalDefaults);
            }
            ArrayList<String> platformSpecificKeys = new ArrayList<>();
            for (Object okey : properties.keySet()) {
                String key = (String) okey;
                if (key.startsWith(RuntimeConstants.SIG_ARRAY) && (key.startsWith("[win]") || key.startsWith("[mac]") || key.startsWith("[linux]") || key.startsWith("[light]") || key.startsWith("[dark]"))) {
                    platformSpecificKeys.add(key);
                }
            }
            if (!platformSpecificKeys.isEmpty()) {
                String lightOrDarkPrefix = dark ? "[dark]" : "[light]";
                Iterator<String> it = platformSpecificKeys.iterator();
                while (it.hasNext()) {
                    String key2 = it.next();
                    if (key2.startsWith(lightOrDarkPrefix)) {
                        properties.put(key2.substring(lightOrDarkPrefix.length()), properties.remove(key2));
                    }
                }
                if (SystemInfo.isWindows) {
                    str = "[win]";
                } else if (SystemInfo.isMacOS) {
                    str = "[mac]";
                } else {
                    str = SystemInfo.isLinux ? "[linux]" : "[unknown]";
                }
                String platformPrefix = str;
                Iterator<String> it2 = platformSpecificKeys.iterator();
                while (it2.hasNext()) {
                    String key3 = it2.next();
                    Object value = properties.remove(key3);
                    if (key3.startsWith(platformPrefix)) {
                        properties.put(key3.substring(platformPrefix.length()), value);
                    }
                }
            }
            HashMap<String, String> wildcards = new HashMap<>();
            Iterator<Map.Entry<Object, Object>> it3 = properties.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry<Object, Object> e2 = it3.next();
                String key4 = (String) e2.getKey();
                if (key4.startsWith(WILDCARD_PREFIX)) {
                    wildcards.put(key4.substring(WILDCARD_PREFIX.length()), (String) e2.getValue());
                    it3.remove();
                }
            }
            for (Object key5 : defaults.keySet()) {
                if ((key5 instanceof String) && !properties.containsKey(key5) && (dot = ((String) key5).lastIndexOf(46)) >= 0) {
                    String wildcardKey = ((String) key5).substring(dot + 1);
                    String wildcardValue = wildcards.get(wildcardKey);
                    if (wildcardValue != null) {
                        properties.put(key5, wildcardValue);
                    }
                }
            }
            Function<String, String> propertiesGetter = key6 -> {
                return properties.getProperty(key6);
            };
            Function<String, String> resolver = value2 -> {
                return resolveValue(value2, propertiesGetter);
            };
            Map<String, String> variables = new HashMap<>(50);
            for (Map.Entry<Object, Object> e3 : properties.entrySet()) {
                String key7 = (String) e3.getKey();
                if (key7.startsWith(VARIABLE_PREFIX)) {
                    variables.put(key7, (String) e3.getValue());
                } else {
                    String value3 = (String) e3.getValue();
                    try {
                        value3 = resolveValue(value3, propertiesGetter);
                        defaults.put(key7, parseValue(key7, value3, null, null, resolver, addonClassLoaders));
                    } catch (RuntimeException ex) {
                        logParseError(key7, value3, ex, true);
                    }
                }
            }
            defaults.put(KEY_VARIABLES, variables);
            systemColorCache = null;
        } catch (IOException ex2) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to load properties files.", ex2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String simpleClassName(Class<?> cls) {
        String className = cls.getName();
        return className.substring(className.lastIndexOf(46) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logParseError(String key, String value, RuntimeException ex, boolean severe) {
        String message = "FlatLaf: Failed to parse: '" + key + '=' + value + '\'';
        if (severe) {
            LoggingFacade.INSTANCE.logSevere(message, ex);
        } else {
            LoggingFacade.INSTANCE.logConfig(message, ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String resolveValue(String value, Function<String, String> propertiesGetter) throws IllegalArgumentException {
        String value2 = value.trim();
        if (value2.startsWith("$")) {
            value2 = value2.substring("$".length());
        } else if (!value2.startsWith(VARIABLE_PREFIX)) {
            return value2;
        }
        boolean optional = false;
        if (value2.startsWith(OPTIONAL_PREFIX)) {
            value2 = value2.substring(OPTIONAL_PREFIX.length());
            optional = true;
        }
        String newValue = propertiesGetter.apply(value2);
        if (newValue == null) {
            if (optional) {
                return "null";
            }
            throw new IllegalArgumentException("variable or property '" + value2 + "' not found");
        }
        if (newValue.equals(value2)) {
            throw new IllegalArgumentException("endless recursion in variable or property '" + value2 + OperatorName.SHOW_TEXT_LINE);
        }
        return resolveValue(newValue, propertiesGetter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String resolveValueFromUIManager(String value) throws IllegalArgumentException {
        if (value.startsWith(VARIABLE_PREFIX)) {
            Map<String, String> variables = (Map) UIManager.get(KEY_VARIABLES);
            String newValue = variables != null ? variables.get(value) : null;
            if (newValue == null) {
                throw new IllegalArgumentException("variable '" + value + "' not found");
            }
            return resolveValueFromUIManager(newValue);
        }
        if (!value.startsWith("$")) {
            return value;
        }
        String key = value.substring("$".length());
        Object newValue2 = UIManager.get(key);
        if (newValue2 == null) {
            throw new IllegalArgumentException("property '" + key + "' not found");
        }
        if (newValue2 instanceof Color) {
            Color color = (Color) newValue2;
            int rgb = color.getRGB() & 16777215;
            int alpha = color.getAlpha();
            if (alpha != 255) {
                return String.format("#%06x%02x", Integer.valueOf(rgb), Integer.valueOf(alpha));
            }
            return String.format("#%06x", Integer.valueOf(rgb));
        }
        throw new IllegalArgumentException("property value type '" + newValue2.getClass().getName() + "' not supported in references");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object parseValue(String key, String value, Class<?> valueType) throws IllegalArgumentException {
        return parseValue(key, value, valueType, null, v -> {
            return v;
        }, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object parseValue(String key, String value, Class<?> javaValueType, ValueType[] resultValueType, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) throws IllegalArgumentException {
        int end;
        if (resultValueType == null) {
            resultValueType = tempResultValueType;
        }
        if (key.startsWith("[style]")) {
            resultValueType[0] = ValueType.STRING;
            return value;
        }
        String value2 = value.trim();
        if (value2.equals("null") || value2.isEmpty()) {
            resultValueType[0] = ValueType.NULL;
            return null;
        }
        if (value2.startsWith("if(") && value2.endsWith(RuntimeConstants.SIG_ENDMETHOD)) {
            List<String> params = splitFunctionParams(value2.substring(3, value2.length() - 1), ',');
            if (params.size() != 3) {
                throw newMissingParametersException(value2);
            }
            boolean ifCondition = parseCondition(params.get(0), resolver, addonClassLoaders);
            String ifValue = params.get(ifCondition ? 1 : 2);
            return parseValue(key, resolver.apply(ifValue), javaValueType, resultValueType, resolver, addonClassLoaders);
        }
        ValueType valueType = ValueType.UNKNOWN;
        if (javaValueType != null) {
            if (javaValueTypes == null) {
                javaValueTypes = new HashMap();
                javaValueTypes.put(String.class, ValueType.STRING);
                javaValueTypes.put(Boolean.TYPE, ValueType.BOOLEAN);
                javaValueTypes.put(Boolean.class, ValueType.BOOLEAN);
                javaValueTypes.put(Character.TYPE, ValueType.CHARACTER);
                javaValueTypes.put(Character.class, ValueType.CHARACTER);
                javaValueTypes.put(Integer.TYPE, ValueType.INTEGER);
                javaValueTypes.put(Integer.class, ValueType.INTEGER);
                javaValueTypes.put(Float.TYPE, ValueType.FLOAT);
                javaValueTypes.put(Float.class, ValueType.FLOAT);
                javaValueTypes.put(Border.class, ValueType.BORDER);
                javaValueTypes.put(Icon.class, ValueType.ICON);
                javaValueTypes.put(Insets.class, ValueType.INSETS);
                javaValueTypes.put(Dimension.class, ValueType.DIMENSION);
                javaValueTypes.put(Color.class, ValueType.COLOR);
                javaValueTypes.put(Font.class, ValueType.FONT);
            }
            valueType = javaValueTypes.get(javaValueType);
            if (valueType == null) {
                throw new IllegalArgumentException("unsupported value type '" + javaValueType.getName() + OperatorName.SHOW_TEXT_LINE);
            }
            if (valueType == ValueType.STRING && value2.startsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && value2.endsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE)) {
                value2 = value2.substring(1, value2.length() - 1);
            }
        } else {
            boolean z = -1;
            switch (value2.hashCode()) {
                case 3569038:
                    if (value2.equals(Constants.TRUE)) {
                        z = true;
                        break;
                    }
                    break;
                case 97196323:
                    if (value2.equals(Constants.FALSE)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    resultValueType[0] = ValueType.BOOLEAN;
                    return false;
                case true:
                    resultValueType[0] = ValueType.BOOLEAN;
                    return true;
                default:
                    if (value2.startsWith("lazy(") && value2.endsWith(RuntimeConstants.SIG_ENDMETHOD)) {
                        resultValueType[0] = ValueType.LAZY;
                        String uiKey = StringUtils.substringTrimmed(value2, 5, value2.length() - 1);
                        return t -> {
                            return lazyUIManagerGet(uiKey);
                        };
                    }
                    if (value2.startsWith("#")) {
                        valueType = ValueType.COLOR;
                    } else if (value2.startsWith(TYPE_PREFIX) && (end = value2.indexOf(TYPE_PREFIX_END)) != -1) {
                        try {
                            String typeStr = value2.substring(TYPE_PREFIX.length(), end);
                            valueType = ValueType.valueOf(typeStr.toUpperCase(Locale.ENGLISH));
                            value2 = value2.substring(end + TYPE_PREFIX_END.length());
                        } catch (IllegalArgumentException e) {
                        }
                    }
                    if (valueType == ValueType.UNKNOWN) {
                        if (knownValueTypes == null) {
                            knownValueTypes = new HashMap();
                            knownValueTypes.put("activeCaptionBorder", ValueType.COLOR);
                            knownValueTypes.put("inactiveCaptionBorder", ValueType.COLOR);
                            knownValueTypes.put("windowBorder", ValueType.COLOR);
                            knownValueTypes.put("SplitPane.dividerSize", ValueType.INTEGER);
                            knownValueTypes.put("SplitPaneDivider.gripDotSize", ValueType.INTEGER);
                            knownValueTypes.put("dividerSize", ValueType.INTEGER);
                            knownValueTypes.put("gripDotSize", ValueType.INTEGER);
                            knownValueTypes.put("TabbedPane.closeCrossPlainSize", ValueType.FLOAT);
                            knownValueTypes.put("TabbedPane.closeCrossFilledSize", ValueType.FLOAT);
                            knownValueTypes.put("closeCrossPlainSize", ValueType.FLOAT);
                            knownValueTypes.put("closeCrossFilledSize", ValueType.FLOAT);
                            knownValueTypes.put("Table.intercellSpacing", ValueType.DIMENSION);
                            knownValueTypes.put("intercellSpacing", ValueType.DIMENSION);
                        }
                        valueType = knownValueTypes.getOrDefault(key, ValueType.UNKNOWN);
                    }
                    if (valueType == ValueType.UNKNOWN) {
                        if (key.endsWith("UI")) {
                            valueType = ValueType.STRING;
                            break;
                        } else if (key.endsWith("Color") || (key.endsWith("ground") && (key.endsWith(".background") || key.endsWith("Background") || key.equals("background") || key.endsWith(".foreground") || key.endsWith("Foreground") || key.equals("foreground")))) {
                            valueType = ValueType.COLOR;
                            break;
                        } else if (key.endsWith(".font") || key.endsWith("Font") || key.equals("font")) {
                            valueType = ValueType.FONT;
                            break;
                        } else if (key.endsWith(".border") || key.endsWith("Border") || key.equals("border")) {
                            valueType = ValueType.BORDER;
                            break;
                        } else if (key.endsWith(".icon") || key.endsWith("Icon") || key.equals("icon")) {
                            valueType = ValueType.ICON;
                            break;
                        } else if (key.endsWith(".margin") || key.equals("margin") || key.endsWith(".padding") || key.equals("padding") || key.endsWith("Margins") || key.endsWith("Insets")) {
                            valueType = ValueType.INSETS;
                            break;
                        } else if (key.endsWith("Size")) {
                            valueType = ValueType.DIMENSION;
                            break;
                        } else if (key.endsWith("Width") || key.endsWith("Height")) {
                            valueType = ValueType.INTEGERORFLOAT;
                            break;
                        } else if (key.endsWith("Char")) {
                            valueType = ValueType.CHARACTER;
                            break;
                        } else if (key.endsWith("grayFilter")) {
                            valueType = ValueType.GRAYFILTER;
                            break;
                        }
                    }
                    break;
            }
        }
        resultValueType[0] = valueType;
        switch (valueType) {
            case STRING:
                return value2;
            case BOOLEAN:
                return parseBoolean(value2);
            case CHARACTER:
                return parseCharacter(value2);
            case INTEGER:
                return parseInteger(value2);
            case INTEGERORFLOAT:
                return parseIntegerOrFloat(value2);
            case FLOAT:
                return parseFloat(value2);
            case BORDER:
                return parseBorder(value2, resolver, addonClassLoaders);
            case ICON:
                return parseInstance(value2, resolver, addonClassLoaders);
            case INSETS:
                return parseInsets(value2);
            case DIMENSION:
                return parseDimension(value2);
            case COLOR:
                return parseColorOrFunction(value2, resolver);
            case FONT:
                return parseFont(value2);
            case SCALEDINTEGER:
                return parseScaledInteger(value2);
            case SCALEDFLOAT:
                return parseScaledFloat(value2);
            case SCALEDINSETS:
                return parseScaledInsets(value2);
            case SCALEDDIMENSION:
                return parseScaledDimension(value2);
            case INSTANCE:
                return parseInstance(value2, resolver, addonClassLoaders);
            case CLASS:
                return parseClass(value2, addonClassLoaders);
            case GRAYFILTER:
                return parseGrayFilter(value2);
            case UNKNOWN:
            default:
                if (value2.startsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && value2.endsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE)) {
                    resultValueType[0] = ValueType.STRING;
                    return value2.substring(1, value2.length() - 1);
                }
                if (value2.startsWith("#") || value2.endsWith(RuntimeConstants.SIG_ENDMETHOD)) {
                    Object color = parseColorOrFunction(value2, resolver);
                    resultValueType[0] = color != null ? ValueType.COLOR : ValueType.NULL;
                    return color;
                }
                char firstChar = value2.charAt(0);
                if ((firstChar >= '0' && firstChar <= '9') || firstChar == '-' || firstChar == '+' || firstChar == '.') {
                    try {
                        Integer integer = parseInteger(value2);
                        resultValueType[0] = ValueType.INTEGER;
                        return integer;
                    } catch (NumberFormatException e2) {
                        try {
                            Float f = parseFloat(value2);
                            resultValueType[0] = ValueType.FLOAT;
                            return f;
                        } catch (NumberFormatException e3) {
                        }
                    }
                }
                resultValueType[0] = ValueType.STRING;
                return value2;
        }
    }

    private static boolean parseCondition(String condition, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
        try {
            Object conditionValue = parseValue("", resolver.apply(condition), null, null, resolver, addonClassLoaders);
            if (conditionValue != null && !conditionValue.equals(false)) {
                if (!conditionValue.equals(0)) {
                    return true;
                }
            }
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static Object parseBorder(String value, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) throws IllegalArgumentException {
        ColorUIResource colorUIResource;
        float f;
        int i;
        if (value.indexOf(44) >= 0) {
            List<String> parts = splitFunctionParams(value, ',');
            Insets insets = parseInsets(value);
            if (parts.size() >= 5 && !parts.get(4).isEmpty()) {
                colorUIResource = (ColorUIResource) parseColorOrFunction(resolver.apply(parts.get(4)), resolver);
            } else {
                colorUIResource = null;
            }
            ColorUIResource lineColor = colorUIResource;
            if (parts.size() >= 6 && !parts.get(5).isEmpty()) {
                f = parseFloat(parts.get(5)).floatValue();
            } else {
                f = 1.0f;
            }
            float lineThickness = f;
            if (parts.size() >= 7 && !parts.get(6).isEmpty()) {
                i = parseInteger(parts.get(6)).intValue();
            } else {
                i = -1;
            }
            int arc = i;
            return t -> {
                if (lineColor != null || arc > 0) {
                    return new FlatLineBorder(insets, lineColor, lineThickness, arc);
                }
                return new FlatEmptyBorder(insets);
            };
        }
        return parseInstance(value, resolver, addonClassLoaders);
    }

    private static Object parseInstance(String value, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
        return t -> {
            try {
                if (value.indexOf(44) >= 0) {
                    List<String> parts = splitFunctionParams(value, ',');
                    String className = parts.get(0);
                    Class<?> cls = findClass(className, addonClassLoaders);
                    Constructor<?>[] constructors = cls.getDeclaredConstructors();
                    Object result = invokeConstructorOrStaticMethod(constructors, parts, resolver);
                    if (result != null) {
                        return result;
                    }
                    LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to instantiate '" + className + "': no constructor found for parameters '" + value.substring(value.indexOf(45)) + "'.", null);
                    return null;
                }
                return findClass(value, addonClassLoaders).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception ex) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to instantiate '" + value + "'.", ex);
                return null;
            }
        };
    }

    private static Object parseClass(String value, List<ClassLoader> addonClassLoaders) {
        return t -> {
            try {
                return findClass(value, addonClassLoaders);
            } catch (ClassNotFoundException ex) {
                LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to find class '" + value + "'.", ex);
                return null;
            }
        };
    }

    private static Class<?> findClass(String className, List<ClassLoader> addonClassLoaders) throws ClassNotFoundException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            for (ClassLoader addonClassLoader : addonClassLoaders) {
                try {
                    return addonClassLoader.loadClass(className);
                } catch (ClassNotFoundException e) {
                }
            }
            throw ex;
        }
    }

    private static Insets parseInsets(String value) throws IllegalArgumentException {
        List<String> numbers = StringUtils.split(value, ',', true, false);
        try {
            return new InsetsUIResource(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)), Integer.parseInt(numbers.get(3)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid insets '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static Dimension parseDimension(String value) throws IllegalArgumentException {
        List<String> numbers = StringUtils.split(value, ',', true, false);
        try {
            return new DimensionUIResource(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid size '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static Object parseColorOrFunction(String value, Function<String, String> resolver) throws IllegalArgumentException {
        if (value.endsWith(RuntimeConstants.SIG_ENDMETHOD)) {
            return parseColorFunctions(value, resolver);
        }
        return parseColor(value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ColorUIResource parseColor(String value) throws IllegalArgumentException {
        int rgba = parseColorRGBA(value);
        if ((rgba & sun.rmi.rmic.iiop.Constants.TM_MASK) == -16777216) {
            return new ColorUIResource(rgba);
        }
        return new ColorUIResource(new Color(rgba, true));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseColorRGBA(String value) throws IllegalArgumentException {
        int i;
        int len = value.length();
        if ((len != 4 && len != 5 && len != 7 && len != 9) || value.charAt(0) != '#') {
            throw newInvalidColorException(value);
        }
        int n = 0;
        for (int i2 = 1; i2 < len; i2++) {
            char ch = value.charAt(i2);
            if (ch >= '0' && ch <= '9') {
                i = ch - '0';
            } else if (ch >= 'a' && ch <= 'f') {
                i = (ch - 'a') + 10;
            } else if (ch >= 'A' && ch <= 'F') {
                i = (ch - 'A') + 10;
            } else {
                throw newInvalidColorException(value);
            }
            int digit = i;
            n = (n << 4) | digit;
        }
        if (len <= 5) {
            int n1 = n & 61440;
            int n2 = n & 3840;
            int n3 = n & 240;
            int n4 = n & 15;
            n = (n1 << 16) | (n1 << 12) | (n2 << 12) | (n2 << 8) | (n3 << 8) | (n3 << 4) | (n4 << 4) | n4;
        }
        if (len == 4 || len == 7) {
            return (-16777216) | n;
        }
        return ((n >> 8) & 16777215) | ((n & 255) << 24);
    }

    private static IllegalArgumentException newInvalidColorException(String value) {
        return new IllegalArgumentException("invalid color '" + value + OperatorName.SHOW_TEXT_LINE);
    }

    private static Object parseColorFunctions(String value, Function<String, String> resolver) throws IllegalArgumentException {
        int paramsStart = value.indexOf(40);
        if (paramsStart < 0) {
            throw new IllegalArgumentException("missing opening parenthesis in function '" + value + OperatorName.SHOW_TEXT_LINE);
        }
        String function = StringUtils.substringTrimmed(value, 0, paramsStart);
        List<String> params = splitFunctionParams(value.substring(paramsStart + 1, value.length() - 1), ',');
        if (params.isEmpty()) {
            throw newMissingParametersException(value);
        }
        if (parseColorDepth > 100) {
            throw new IllegalArgumentException("endless recursion in color function '" + value + OperatorName.SHOW_TEXT_LINE);
        }
        parseColorDepth++;
        try {
            boolean z = -1;
            switch (function.hashCode()) {
                case -2114203985:
                    if (function.equals("saturate")) {
                        z = 8;
                        break;
                    }
                    break;
                case -1682408562:
                    if (function.equals("changeAlpha")) {
                        z = 17;
                        break;
                    }
                    break;
                case -1561144716:
                    if (function.equals("systemColor")) {
                        z = true;
                        break;
                    }
                    break;
                case -1338968417:
                    if (function.equals("darken")) {
                        z = 7;
                        break;
                    }
                    break;
                case -1282132831:
                    if (function.equals("fadein")) {
                        z = 10;
                        break;
                    }
                    break;
                case -1091405998:
                    if (function.equals("fadeout")) {
                        z = 11;
                        break;
                    }
                    break;
                case -566947070:
                    if (function.equals("contrast")) {
                        z = 21;
                        break;
                    }
                    break;
                case 3357:
                    if (function.equals("if")) {
                        z = false;
                        break;
                    }
                    break;
                case 103617:
                    if (function.equals("hsl")) {
                        z = 4;
                        break;
                    }
                    break;
                case 108124:
                    if (function.equals("mix")) {
                        z = 18;
                        break;
                    }
                    break;
                case 112845:
                    if (function.equals("rgb")) {
                        z = 2;
                        break;
                    }
                    break;
                case 3135100:
                    if (function.equals("fade")) {
                        z = 12;
                        break;
                    }
                    break;
                case 3212224:
                    if (function.equals("hsla")) {
                        z = 5;
                        break;
                    }
                    break;
                case 3423444:
                    if (function.equals("over")) {
                        z = 22;
                        break;
                    }
                    break;
                case 3498292:
                    if (function.equals("rgba")) {
                        z = 3;
                        break;
                    }
                    break;
                case 3536962:
                    if (function.equals("spin")) {
                        z = 13;
                        break;
                    }
                    break;
                case 3560187:
                    if (function.equals("tint")) {
                        z = 19;
                        break;
                    }
                    break;
                case 109399597:
                    if (function.equals("shade")) {
                        z = 20;
                        break;
                    }
                    break;
                case 170546239:
                    if (function.equals("lighten")) {
                        z = 6;
                        break;
                    }
                    break;
                case 229314466:
                    if (function.equals("changeSaturation")) {
                        z = 15;
                        break;
                    }
                    break;
                case 424179357:
                    if (function.equals("changeLightness")) {
                        z = 16;
                        break;
                    }
                    break;
                case 1455237928:
                    if (function.equals("changeHue")) {
                        z = 14;
                        break;
                    }
                    break;
                case 1839974960:
                    if (function.equals("desaturate")) {
                        z = 9;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    Object parseColorIf = parseColorIf(value, params, resolver);
                    parseColorDepth--;
                    return parseColorIf;
                case true:
                    Object parseColorSystemColor = parseColorSystemColor(value, params, resolver);
                    parseColorDepth--;
                    return parseColorSystemColor;
                case true:
                    ColorUIResource parseColorRgbOrRgba = parseColorRgbOrRgba(false, params, resolver);
                    parseColorDepth--;
                    return parseColorRgbOrRgba;
                case true:
                    ColorUIResource parseColorRgbOrRgba2 = parseColorRgbOrRgba(true, params, resolver);
                    parseColorDepth--;
                    return parseColorRgbOrRgba2;
                case true:
                    ColorUIResource parseColorHslOrHsla = parseColorHslOrHsla(false, params);
                    parseColorDepth--;
                    return parseColorHslOrHsla;
                case true:
                    ColorUIResource parseColorHslOrHsla2 = parseColorHslOrHsla(true, params);
                    parseColorDepth--;
                    return parseColorHslOrHsla2;
                case true:
                    Object parseColorHSLIncreaseDecrease = parseColorHSLIncreaseDecrease(2, true, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease;
                case true:
                    Object parseColorHSLIncreaseDecrease2 = parseColorHSLIncreaseDecrease(2, false, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease2;
                case true:
                    Object parseColorHSLIncreaseDecrease3 = parseColorHSLIncreaseDecrease(1, true, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease3;
                case true:
                    Object parseColorHSLIncreaseDecrease4 = parseColorHSLIncreaseDecrease(1, false, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease4;
                case true:
                    Object parseColorHSLIncreaseDecrease5 = parseColorHSLIncreaseDecrease(3, true, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease5;
                case true:
                    Object parseColorHSLIncreaseDecrease6 = parseColorHSLIncreaseDecrease(3, false, params, resolver);
                    parseColorDepth--;
                    return parseColorHSLIncreaseDecrease6;
                case true:
                    Object parseColorFade = parseColorFade(params, resolver);
                    parseColorDepth--;
                    return parseColorFade;
                case true:
                    Object parseColorSpin = parseColorSpin(params, resolver);
                    parseColorDepth--;
                    return parseColorSpin;
                case true:
                    Object parseColorChange = parseColorChange(0, params, resolver);
                    parseColorDepth--;
                    return parseColorChange;
                case true:
                    Object parseColorChange2 = parseColorChange(1, params, resolver);
                    parseColorDepth--;
                    return parseColorChange2;
                case true:
                    Object parseColorChange3 = parseColorChange(2, params, resolver);
                    parseColorDepth--;
                    return parseColorChange3;
                case true:
                    Object parseColorChange4 = parseColorChange(3, params, resolver);
                    parseColorDepth--;
                    return parseColorChange4;
                case true:
                    Object parseColorMix = parseColorMix(null, params, resolver);
                    parseColorDepth--;
                    return parseColorMix;
                case true:
                    Object parseColorMix2 = parseColorMix("#fff", params, resolver);
                    parseColorDepth--;
                    return parseColorMix2;
                case true:
                    Object parseColorMix3 = parseColorMix("#000", params, resolver);
                    parseColorDepth--;
                    return parseColorMix3;
                case true:
                    Object parseColorContrast = parseColorContrast(params, resolver);
                    parseColorDepth--;
                    return parseColorContrast;
                case true:
                    ColorUIResource parseColorOver = parseColorOver(params, resolver);
                    parseColorDepth--;
                    return parseColorOver;
                default:
                    parseColorDepth--;
                    throw new IllegalArgumentException("unknown color function '" + value + OperatorName.SHOW_TEXT_LINE);
            }
        } catch (Throwable th) {
            parseColorDepth--;
            throw th;
        }
    }

    private static Object parseColorIf(String value, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        if (params.size() != 3) {
            throw newMissingParametersException(value);
        }
        boolean ifCondition = parseCondition(params.get(0), resolver, Collections.emptyList());
        String ifValue = params.get(ifCondition ? 1 : 2);
        return parseColorOrFunction(resolver.apply(ifValue), resolver);
    }

    private static Object parseColorSystemColor(String value, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        if (params.size() < 1) {
            throw newMissingParametersException(value);
        }
        ColorUIResource systemColor = getSystemColor(params.get(0));
        if (systemColor != null) {
            return systemColor;
        }
        String defaultValue = params.size() > 1 ? params.get(1) : "";
        if (defaultValue.equals("null") || defaultValue.isEmpty()) {
            return null;
        }
        return parseColorOrFunction(resolver.apply(defaultValue), resolver);
    }

    private static ColorUIResource getSystemColor(String name) {
        Function<String, Color> systemColorGetter = FlatLaf.getSystemColorGetter();
        if (systemColorGetter == null) {
            return null;
        }
        if (systemColorCache != null && systemColorCache.containsKey(name)) {
            return systemColorCache.get(name);
        }
        Color color = systemColorGetter.apply(name);
        ColorUIResource uiColor = color != null ? new ColorUIResource(color) : null;
        if (systemColorCache != null) {
            systemColorCache.put(name, uiColor);
        }
        return uiColor;
    }

    private static ColorUIResource parseColorRgbOrRgba(boolean hasAlpha, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        if (hasAlpha && params.size() == 2) {
            String colorStr = params.get(0);
            int alpha = parseInteger(params.get(1), 0, 255, true).intValue();
            ColorUIResource color = (ColorUIResource) parseColorOrFunction(resolver.apply(colorStr), resolver);
            return new ColorUIResource(new Color(((alpha & 255) << 24) | (color.getRGB() & 16777215), true));
        }
        int red = parseInteger(params.get(0), 0, 255, true).intValue();
        int green = parseInteger(params.get(1), 0, 255, true).intValue();
        int blue = parseInteger(params.get(2), 0, 255, true).intValue();
        int alpha2 = hasAlpha ? parseInteger(params.get(3), 0, 255, true).intValue() : 255;
        if (hasAlpha) {
            return new ColorUIResource(new Color(red, green, blue, alpha2));
        }
        return new ColorUIResource(red, green, blue);
    }

    private static ColorUIResource parseColorHslOrHsla(boolean hasAlpha, List<String> params) throws IllegalArgumentException {
        int hue = parseInteger(params.get(0), 0, 360, false).intValue();
        int saturation = parsePercentage(params.get(1));
        int lightness = parsePercentage(params.get(2));
        int alpha = hasAlpha ? parsePercentage(params.get(3)) : 100;
        float[] hsl = {hue, saturation, lightness};
        return new ColorUIResource(HSLColor.toRGB(hsl, alpha / 100.0f));
    }

    private static Object parseColorHSLIncreaseDecrease(int hslIndex, boolean increase, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        String colorStr = params.get(0);
        int amount = parsePercentage(params.get(1));
        boolean relative = false;
        boolean autoInverse = false;
        boolean lazy = false;
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            relative = options.contains("relative");
            autoInverse = options.contains("autoInverse");
            lazy = options.contains("lazy");
            derived = options.contains("derived");
            if (derived && !options.contains("noAutoInverse")) {
                autoInverse = true;
            }
        }
        ColorFunctions.ColorFunction function = new ColorFunctions.HSLIncreaseDecrease(hslIndex, increase, amount, relative, autoInverse);
        if (lazy) {
            return t -> {
                Object color = lazyUIManagerGet(colorStr);
                if (color instanceof Color) {
                    return new ColorUIResource(ColorFunctions.applyFunctions((Color) color, function));
                }
                return null;
            };
        }
        return parseFunctionBaseColor(colorStr, function, derived, resolver);
    }

    private static Object parseColorFade(List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        String colorStr = params.get(0);
        int amount = parsePercentage(params.get(1));
        boolean derived = false;
        boolean lazy = false;
        if (params.size() > 2) {
            String options = params.get(2);
            derived = options.contains("derived");
            lazy = options.contains("lazy");
        }
        ColorFunctions.ColorFunction function = new ColorFunctions.Fade(amount);
        if (lazy) {
            return t -> {
                Object color = lazyUIManagerGet(colorStr);
                if (color instanceof Color) {
                    return new ColorUIResource(ColorFunctions.applyFunctions((Color) color, function));
                }
                return null;
            };
        }
        return parseFunctionBaseColor(colorStr, function, derived, resolver);
    }

    private static Object parseColorSpin(List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        String colorStr = params.get(0);
        int amount = parseInteger(params.get(1)).intValue();
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            derived = options.contains("derived");
        }
        ColorFunctions.ColorFunction function = new ColorFunctions.HSLIncreaseDecrease(0, true, amount, false, false);
        return parseFunctionBaseColor(colorStr, function, derived, resolver);
    }

    private static Object parseColorChange(int hslIndex, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        int parsePercentage;
        String colorStr = params.get(0);
        if (hslIndex == 0) {
            parsePercentage = parseInteger(params.get(1)).intValue();
        } else {
            parsePercentage = parsePercentage(params.get(1));
        }
        int value = parsePercentage;
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            derived = options.contains("derived");
        }
        ColorFunctions.ColorFunction function = new ColorFunctions.HSLChange(hslIndex, value);
        return parseFunctionBaseColor(colorStr, function, derived, resolver);
    }

    private static Object parseColorMix(String color1Str, List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        int i = 0;
        if (color1Str == null) {
            i = 0 + 1;
            color1Str = params.get(0);
        }
        int i2 = i;
        int i3 = i + 1;
        String color2Str = params.get(i2);
        int weight = params.size() > i3 ? parsePercentage(params.get(i3)) : 50;
        ColorUIResource color2 = (ColorUIResource) parseColorOrFunction(resolver.apply(color2Str), resolver);
        if (color2 == null) {
            return null;
        }
        ColorFunctions.ColorFunction function = new ColorFunctions.Mix(color2, weight);
        return parseFunctionBaseColor(color1Str, function, false, resolver);
    }

    private static Object parseColorContrast(List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        String str;
        String colorStr = params.get(0);
        String darkStr = params.get(1);
        String lightStr = params.get(2);
        int threshold = params.size() > 3 ? parsePercentage(params.get(3)) : 43;
        ColorUIResource color = (ColorUIResource) parseColorOrFunction(resolver.apply(colorStr), resolver);
        if (color == null) {
            return null;
        }
        if (ColorFunctions.luma(color) * 100.0f < threshold) {
            str = lightStr;
        } else {
            str = darkStr;
        }
        String darkOrLightColor = str;
        return parseColorOrFunction(resolver.apply(darkOrLightColor), resolver);
    }

    private static ColorUIResource parseColorOver(List<String> params, Function<String, String> resolver) throws IllegalArgumentException {
        String foregroundStr = params.get(0);
        String backgroundStr = params.get(1);
        ColorUIResource foreground = (ColorUIResource) parseColorOrFunction(resolver.apply(foregroundStr), resolver);
        if (foreground == null || foreground.getAlpha() == 255) {
            return foreground;
        }
        ColorUIResource foreground2 = new ColorUIResource(foreground.getRGB());
        ColorUIResource background = (ColorUIResource) parseColorOrFunction(resolver.apply(backgroundStr), resolver);
        if (background == null) {
            return foreground2;
        }
        float weight = foreground.getAlpha() / 255.0f;
        return new ColorUIResource(ColorFunctions.mix(foreground2, background, weight));
    }

    private static Object parseFunctionBaseColor(String colorStr, ColorFunctions.ColorFunction function, boolean derived, Function<String, String> resolver) throws IllegalArgumentException {
        ColorFunctions.ColorFunction[] functions;
        String resolvedColorStr = resolver.apply(colorStr);
        ColorUIResource baseColor = (ColorUIResource) parseColorOrFunction(resolvedColorStr, resolver);
        if (baseColor == null) {
            return null;
        }
        Color newColor = ColorFunctions.applyFunctions(baseColor, function);
        if (derived) {
            if ((baseColor instanceof DerivedColor) && resolvedColorStr == colorStr) {
                ColorFunctions.ColorFunction[] baseFunctions = ((DerivedColor) baseColor).getFunctions();
                functions = new ColorFunctions.ColorFunction[baseFunctions.length + 1];
                System.arraycopy(baseFunctions, 0, functions, 0, baseFunctions.length);
                functions[baseFunctions.length] = function;
            } else {
                functions = new ColorFunctions.ColorFunction[]{function};
            }
            return new DerivedColor(newColor, functions);
        }
        return new ColorUIResource(newColor);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0181 A[Catch: IOException -> 0x02b0, TryCatch #0 {IOException -> 0x02b0, blocks: (B:8:0x0060, B:10:0x0069, B:11:0x007c, B:12:0x00c0, B:15:0x00d1, B:18:0x00e2, B:21:0x00f3, B:24:0x0104, B:27:0x0115, B:30:0x0126, B:34:0x0137, B:41:0x016c, B:46:0x017a, B:48:0x0181, B:50:0x0188, B:52:0x0191, B:54:0x0198, B:56:0x01a1, B:69:0x024e, B:70:0x026f, B:66:0x0270, B:73:0x0280, B:75:0x028a, B:77:0x0295, B:78:0x02a0, B:90:0x020d, B:92:0x0218, B:94:0x0235, B:96:0x0200, B:99:0x01d0, B:100:0x01f1), top: B:7:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0188 A[Catch: IOException -> 0x02b0, TryCatch #0 {IOException -> 0x02b0, blocks: (B:8:0x0060, B:10:0x0069, B:11:0x007c, B:12:0x00c0, B:15:0x00d1, B:18:0x00e2, B:21:0x00f3, B:24:0x0104, B:27:0x0115, B:30:0x0126, B:34:0x0137, B:41:0x016c, B:46:0x017a, B:48:0x0181, B:50:0x0188, B:52:0x0191, B:54:0x0198, B:56:0x01a1, B:69:0x024e, B:70:0x026f, B:66:0x0270, B:73:0x0280, B:75:0x028a, B:77:0x0295, B:78:0x02a0, B:90:0x020d, B:92:0x0218, B:94:0x0235, B:96:0x0200, B:99:0x01d0, B:100:0x01f1), top: B:7:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0191 A[Catch: IOException -> 0x02b0, TryCatch #0 {IOException -> 0x02b0, blocks: (B:8:0x0060, B:10:0x0069, B:11:0x007c, B:12:0x00c0, B:15:0x00d1, B:18:0x00e2, B:21:0x00f3, B:24:0x0104, B:27:0x0115, B:30:0x0126, B:34:0x0137, B:41:0x016c, B:46:0x017a, B:48:0x0181, B:50:0x0188, B:52:0x0191, B:54:0x0198, B:56:0x01a1, B:69:0x024e, B:70:0x026f, B:66:0x0270, B:73:0x0280, B:75:0x028a, B:77:0x0295, B:78:0x02a0, B:90:0x020d, B:92:0x0218, B:94:0x0235, B:96:0x0200, B:99:0x01d0, B:100:0x01f1), top: B:7:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0198 A[Catch: IOException -> 0x02b0, TryCatch #0 {IOException -> 0x02b0, blocks: (B:8:0x0060, B:10:0x0069, B:11:0x007c, B:12:0x00c0, B:15:0x00d1, B:18:0x00e2, B:21:0x00f3, B:24:0x0104, B:27:0x0115, B:30:0x0126, B:34:0x0137, B:41:0x016c, B:46:0x017a, B:48:0x0181, B:50:0x0188, B:52:0x0191, B:54:0x0198, B:56:0x01a1, B:69:0x024e, B:70:0x026f, B:66:0x0270, B:73:0x0280, B:75:0x028a, B:77:0x0295, B:78:0x02a0, B:90:0x020d, B:92:0x0218, B:94:0x0235, B:96:0x0200, B:99:0x01d0, B:100:0x01f1), top: B:7:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01a1 A[Catch: IOException -> 0x02b0, TryCatch #0 {IOException -> 0x02b0, blocks: (B:8:0x0060, B:10:0x0069, B:11:0x007c, B:12:0x00c0, B:15:0x00d1, B:18:0x00e2, B:21:0x00f3, B:24:0x0104, B:27:0x0115, B:30:0x0126, B:34:0x0137, B:41:0x016c, B:46:0x017a, B:48:0x0181, B:50:0x0188, B:52:0x0191, B:54:0x0198, B:56:0x01a1, B:69:0x024e, B:70:0x026f, B:66:0x0270, B:73:0x0280, B:75:0x028a, B:77:0x0295, B:78:0x02a0, B:90:0x020d, B:92:0x0218, B:94:0x0235, B:96:0x0200, B:99:0x01d0, B:100:0x01f1), top: B:7:0x0060 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Object parseFont(String value) throws IllegalArgumentException {
        Object font = fontCache.get(value);
        if (font != null) {
            return font;
        }
        int style = -1;
        int styleChange = 0;
        int absoluteSize = 0;
        int relativeSize = 0;
        float scaleSize = 0.0f;
        List<String> families = null;
        String baseFontKey = null;
        StreamTokenizer st = new StreamTokenizer(new StringReader(value));
        st.resetSyntax();
        st.wordChars(33, 255);
        st.whitespaceChars(0, 32);
        st.whitespaceChars(44, 44);
        st.quoteChar(34);
        st.quoteChar(39);
        while (st.nextToken() != -1) {
            try {
                String param = st.sval;
                boolean z = -1;
                switch (param.hashCode()) {
                    case -1670828517:
                        if (param.equals("+italic")) {
                            z = 5;
                        }
                        switch (z) {
                            case false:
                                style = 0;
                                break;
                            case true:
                                if (style == -1) {
                                    style = 0;
                                }
                                style |= 1;
                                break;
                            case true:
                                if (style == -1) {
                                    style = 0;
                                }
                                style |= 2;
                                break;
                            case true:
                                styleChange |= 1;
                                break;
                            case true:
                                styleChange |= 65536;
                                break;
                            case true:
                                styleChange |= 2;
                                break;
                            case true:
                                styleChange |= 131072;
                                break;
                            default:
                                char firstChar = param.charAt(0);
                                if (Character.isDigit(firstChar) || firstChar == '+' || firstChar == '-') {
                                    if (absoluteSize != 0 || relativeSize != 0 || scaleSize != 0.0f) {
                                        throw new IllegalArgumentException("size specified more than once in '" + value + OperatorName.SHOW_TEXT_LINE);
                                    }
                                    if (firstChar == '+' || firstChar == '-') {
                                        relativeSize = parseInteger(param).intValue();
                                        break;
                                    } else if (param.endsWith("%")) {
                                        scaleSize = parseInteger(param.substring(0, param.length() - 1)).intValue() / 100.0f;
                                        break;
                                    } else {
                                        absoluteSize = parseInteger(param).intValue();
                                        break;
                                    }
                                } else if (firstChar == '$') {
                                    if (baseFontKey != null) {
                                        throw new IllegalArgumentException("baseFontKey specified more than once in '" + value + OperatorName.SHOW_TEXT_LINE);
                                    }
                                    baseFontKey = param.substring(1);
                                    break;
                                } else if (families == null) {
                                    families = Collections.singletonList(param);
                                    break;
                                } else {
                                    if (families.size() == 1) {
                                        families = new ArrayList<>(families);
                                    }
                                    families.add(param);
                                    break;
                                }
                                break;
                        }
                        break;
                    case -1178781136:
                        if (param.equals("italic")) {
                            z = 2;
                        }
                        switch (z) {
                        }
                        break;
                    case -1039745817:
                        if (param.equals("normal")) {
                            z = false;
                        }
                        switch (z) {
                        }
                        break;
                    case 3029637:
                        if (param.equals("bold")) {
                            z = true;
                        }
                        switch (z) {
                        }
                        break;
                    case 42741040:
                        if (param.equals("+bold")) {
                            z = 3;
                        }
                        switch (z) {
                        }
                        break;
                    case 44588082:
                        if (param.equals("-bold")) {
                            z = 4;
                        }
                        switch (z) {
                        }
                        break;
                    case 104178845:
                        if (param.equals("-italic")) {
                            z = 6;
                        }
                        switch (z) {
                        }
                        break;
                    default:
                        switch (z) {
                        }
                        break;
                }
            } catch (IOException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        if (style != -1 && styleChange != 0) {
            throw new IllegalArgumentException("can not mix absolute style (e.g. 'bold') with derived style (e.g. '+italic') in '" + value + OperatorName.SHOW_TEXT_LINE);
        }
        if (styleChange != 0) {
            if ((styleChange & 1) != 0 && (styleChange & 65536) != 0) {
                throw new IllegalArgumentException("can not use '+bold' and '-bold' in '" + value + OperatorName.SHOW_TEXT_LINE);
            }
            if ((styleChange & 2) != 0 && (styleChange & 131072) != 0) {
                throw new IllegalArgumentException("can not use '+italic' and '-italic' in '" + value + OperatorName.SHOW_TEXT_LINE);
            }
        }
        Object font2 = new FlatLaf.ActiveFont(baseFontKey, families, style, styleChange, absoluteSize, relativeSize, scaleSize);
        fontCache.put(value, font2);
        return font2;
    }

    private static int parsePercentage(String value) throws IllegalArgumentException, NumberFormatException {
        if (!value.endsWith("%")) {
            throw new NumberFormatException("invalid percentage '" + value + OperatorName.SHOW_TEXT_LINE);
        }
        try {
            int val = Integer.parseInt(value.substring(0, value.length() - 1));
            if (val < 0 || val > 100) {
                throw new IllegalArgumentException("percentage out of range (0-100%) '" + value + OperatorName.SHOW_TEXT_LINE);
            }
            return val;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid percentage '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static Boolean parseBoolean(String value) throws IllegalArgumentException {
        boolean z = -1;
        switch (value.hashCode()) {
            case 3569038:
                if (value.equals(Constants.TRUE)) {
                    z = true;
                    break;
                }
                break;
            case 97196323:
                if (value.equals(Constants.FALSE)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return false;
            case true:
                return true;
            default:
                throw new IllegalArgumentException("invalid boolean '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static Character parseCharacter(String value) throws IllegalArgumentException {
        if (value.length() != 1) {
            throw new IllegalArgumentException("invalid character '" + value + OperatorName.SHOW_TEXT_LINE);
        }
        return Character.valueOf(value.charAt(0));
    }

    private static Integer parseInteger(String value, int min, int max, boolean allowPercentage) throws IllegalArgumentException, NumberFormatException {
        if (allowPercentage && value.endsWith("%")) {
            int percent = parsePercentage(value);
            return Integer.valueOf((max * percent) / 100);
        }
        Integer integer = parseInteger(value);
        if (integer.intValue() < min || integer.intValue() > max) {
            throw new NumberFormatException("integer '" + value + "' out of range (" + min + '-' + max + ')');
        }
        return integer;
    }

    private static Integer parseInteger(String value) throws NumberFormatException {
        try {
            return Integer.valueOf(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid integer '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static Number parseIntegerOrFloat(String value) throws NumberFormatException {
        try {
            return Integer.valueOf(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            try {
                return Float.valueOf(Float.parseFloat(value));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException("invalid integer or float '" + value + OperatorName.SHOW_TEXT_LINE);
            }
        }
    }

    private static Float parseFloat(String value) throws NumberFormatException {
        try {
            return Float.valueOf(Float.parseFloat(value));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid float '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static UIDefaults.ActiveValue parseScaledInteger(String value) throws NumberFormatException {
        int val = parseInteger(value).intValue();
        return t -> {
            return Integer.valueOf(UIScale.scale(val));
        };
    }

    private static UIDefaults.ActiveValue parseScaledFloat(String value) throws NumberFormatException {
        float val = parseFloat(value).floatValue();
        return t -> {
            return Float.valueOf(UIScale.scale(val));
        };
    }

    private static UIDefaults.ActiveValue parseScaledInsets(String value) throws IllegalArgumentException {
        Insets insets = parseInsets(value);
        return t -> {
            return UIScale.scale(insets);
        };
    }

    private static UIDefaults.ActiveValue parseScaledDimension(String value) throws IllegalArgumentException {
        Dimension dimension = parseDimension(value);
        return t -> {
            return UIScale.scale(dimension);
        };
    }

    private static Object parseGrayFilter(String value) throws IllegalArgumentException {
        List<String> numbers = StringUtils.split(value, ',', true, false);
        try {
            int brightness = Integer.parseInt(numbers.get(0));
            int contrast = Integer.parseInt(numbers.get(1));
            int alpha = Integer.parseInt(numbers.get(2));
            return t -> {
                return new GrayFilter(brightness, contrast, alpha);
            };
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid gray filter '" + value + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private static List<String> splitFunctionParams(String str, char delim) {
        ArrayList<String> strs = new ArrayList<>();
        int nestLevel = 0;
        int start = 0;
        int strlen = str.length();
        for (int i = 0; i < strlen; i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                nestLevel++;
            } else if (ch == ')') {
                nestLevel--;
            } else if (nestLevel == 0 && ch == delim) {
                strs.add(StringUtils.substringTrimmed(str, start, i));
                start = i + 1;
            }
        }
        String s = StringUtils.substringTrimmed(str, start);
        if (!s.isEmpty() || !strs.isEmpty()) {
            strs.add(s);
        }
        return strs;
    }

    private static Object invokeConstructorOrStaticMethod(Executable[] constructorsOrMethods, List<String> parts, Function<String, String> resolver) throws Exception {
        Object[] params;
        Executable[] constructorsOrMethods2 = (Executable[]) constructorsOrMethods.clone();
        Arrays.sort(constructorsOrMethods2, (c1, c2) -> {
            Class<?>[] ptypes1 = c1.getParameterTypes();
            Class<?>[] ptypes2 = c2.getParameterTypes();
            if (ptypes1.length != ptypes2.length) {
                return ptypes1.length - ptypes2.length;
            }
            for (int i = 0; i < ptypes1.length; i++) {
                Class<?> pt1 = ptypes1[i];
                Class<?> pt2 = ptypes2[i];
                if (pt1 != pt2) {
                    if (pt1 == String.class) {
                        return 2;
                    }
                    if (pt2 == String.class) {
                        return -2;
                    }
                    if (pt1 == Integer.TYPE) {
                        return -1;
                    }
                    if (pt2 == Integer.TYPE) {
                        return 1;
                    }
                }
            }
            return 0;
        });
        for (Executable cm : constructorsOrMethods2) {
            if (cm.getParameterCount() == parts.size() - 1 && (params = parseMethodParams(cm.getParameterTypes(), parts, resolver)) != null) {
                if (cm instanceof Constructor) {
                    return ((Constructor) cm).newInstance(params);
                }
                return ((Method) cm).invoke(null, params);
            }
        }
        return null;
    }

    private static Object[] parseMethodParams(Class<?>[] paramTypes, List<String> parts, Function<String, String> resolver) {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < params.length; i++) {
            try {
                Class<?> paramType = paramTypes[i];
                String paramValue = parts.get(i + 1);
                if (paramType == String.class) {
                    params[i] = paramValue;
                } else if (paramType == Boolean.TYPE) {
                    params[i] = parseBoolean(paramValue);
                } else if (paramType == Integer.TYPE) {
                    params[i] = parseInteger(paramValue);
                } else if (paramType == Float.TYPE) {
                    params[i] = parseFloat(paramValue);
                } else if (paramType == Color.class) {
                    params[i] = parseColorOrFunction(resolver.apply(paramValue), resolver);
                } else {
                    return null;
                }
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return params;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object lazyUIManagerGet(String uiKey) {
        boolean optional = false;
        if (uiKey.startsWith(OPTIONAL_PREFIX)) {
            uiKey = uiKey.substring(OPTIONAL_PREFIX.length());
            optional = true;
        }
        Object value = UIManager.get(uiKey);
        if (value == null && !optional) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: '" + uiKey + "' not found in UI defaults.", null);
        }
        return value;
    }

    private static IllegalArgumentException newMissingParametersException(String value) {
        return new IllegalArgumentException("missing parameters in function '" + value + OperatorName.SHOW_TEXT_LINE);
    }
}
