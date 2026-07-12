package com.sun.tools.jdi;

import com.sun.jdi.InternalException;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import sun.tools.java.RuntimeConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl.class */
public abstract class ConnectorImpl implements Connector {
    static String trueString = null;
    static String falseString;
    Map<String, Connector.Argument> defaultArguments = new LinkedHashMap();
    private ResourceBundle messages = null;

    @Override // com.sun.jdi.connect.Connector
    public Map<String, Connector.Argument> defaultArguments() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Connector.Argument> it = this.defaultArguments.values().iterator();
        while (it.hasNext()) {
            ArgumentImpl argumentImpl = (ArgumentImpl) it.next();
            linkedHashMap.put(argumentImpl.name(), (Connector.Argument) argumentImpl.clone());
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addStringArgument(String str, String str2, String str3, String str4, boolean z) {
        this.defaultArguments.put(str, new StringArgumentImpl(str, str2, str3, str4, z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBooleanArgument(String str, String str2, String str3, boolean z, boolean z2) {
        this.defaultArguments.put(str, new BooleanArgumentImpl(str, str2, str3, z, z2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addIntegerArgument(String str, String str2, String str3, String str4, boolean z, int i, int i2) {
        this.defaultArguments.put(str, new IntegerArgumentImpl(str, str2, str3, str4, z, i, i2));
    }

    void addSelectedArgument(String str, String str2, String str3, String str4, boolean z, List<String> list) {
        this.defaultArguments.put(str, new SelectedArgumentImpl(str, str2, str3, str4, z, list));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArgumentImpl argument(String str, Map<String, ? extends Connector.Argument> map) throws IllegalConnectorArgumentsException {
        ArgumentImpl argumentImpl = (ArgumentImpl) map.get(str);
        if (argumentImpl == null) {
            throw new IllegalConnectorArgumentsException("Argument missing", str);
        }
        String value = argumentImpl.value();
        if (value == null || value.length() == 0) {
            if (argumentImpl.mustSpecify()) {
                throw new IllegalConnectorArgumentsException("Argument unspecified", str);
            }
        } else if (!argumentImpl.isValid(value)) {
            throw new IllegalConnectorArgumentsException("Argument invalid", str);
        }
        return argumentImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getString(String str) {
        if (this.messages == null) {
            this.messages = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
        }
        return this.messages.getString(str);
    }

    public String toString() {
        String str = name() + " (defaults: ";
        Iterator<Connector.Argument> it = defaultArguments().values().iterator();
        boolean z = true;
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                ArgumentImpl argumentImpl = (ArgumentImpl) it.next();
                if (!z2) {
                    str = str + ", ";
                }
                str = str + argumentImpl.toString();
                z = false;
            } else {
                return str + RuntimeConstants.SIG_ENDMETHOD;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl$ArgumentImpl.class */
    public abstract class ArgumentImpl implements Connector.Argument, Cloneable, Serializable {
        private String name;
        private String label;
        private String description;
        private String value;
        private boolean mustSpecify;

        @Override // com.sun.jdi.connect.Connector.Argument
        public abstract boolean isValid(String str);

        ArgumentImpl(String str, String str2, String str3, String str4, boolean z) {
            this.name = str;
            this.label = str2;
            this.description = str3;
            this.value = str4;
            this.mustSpecify = z;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public String name() {
            return this.name;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public String label() {
            return this.label;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public String description() {
            return this.description;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public String value() {
            return this.value;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public void setValue(String str) {
            if (str == null) {
                throw new NullPointerException("Can't set null value");
            }
            this.value = str;
        }

        @Override // com.sun.jdi.connect.Connector.Argument
        public boolean mustSpecify() {
            return this.mustSpecify;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof Connector.Argument)) {
                Connector.Argument argument = (Connector.Argument) obj;
                return name().equals(argument.name()) && description().equals(argument.description()) && mustSpecify() == argument.mustSpecify() && value().equals(argument.value());
            }
            return false;
        }

        public int hashCode() {
            return description().hashCode();
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new InternalException();
            }
        }

        public String toString() {
            return name() + "=" + value();
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl$BooleanArgumentImpl.class */
    class BooleanArgumentImpl extends ArgumentImpl implements Connector.BooleanArgument {
        private static final long serialVersionUID = 1624542968639361316L;

        BooleanArgumentImpl(String str, String str2, String str3, boolean z, boolean z2) {
            super(str, str2, str3, null, z2);
            if (ConnectorImpl.trueString == null) {
                ConnectorImpl.trueString = ConnectorImpl.this.getString(Constants.TRUE);
                ConnectorImpl.falseString = ConnectorImpl.this.getString(Constants.FALSE);
            }
            setValue(z);
        }

        @Override // com.sun.jdi.connect.Connector.BooleanArgument
        public void setValue(boolean z) {
            setValue(stringValueOf(z));
        }

        @Override // com.sun.tools.jdi.ConnectorImpl.ArgumentImpl, com.sun.jdi.connect.Connector.Argument
        public boolean isValid(String str) {
            return str.equals(ConnectorImpl.trueString) || str.equals(ConnectorImpl.falseString);
        }

        @Override // com.sun.jdi.connect.Connector.BooleanArgument
        public String stringValueOf(boolean z) {
            return z ? ConnectorImpl.trueString : ConnectorImpl.falseString;
        }

        @Override // com.sun.jdi.connect.Connector.BooleanArgument
        public boolean booleanValue() {
            return value().equals(ConnectorImpl.trueString);
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl$IntegerArgumentImpl.class */
    class IntegerArgumentImpl extends ArgumentImpl implements Connector.IntegerArgument {
        private static final long serialVersionUID = 763286081923797770L;
        private final int min;
        private final int max;

        IntegerArgumentImpl(String str, String str2, String str3, String str4, boolean z, int i, int i2) {
            super(str, str2, str3, str4, z);
            this.min = i;
            this.max = i2;
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public void setValue(int i) {
            setValue(stringValueOf(i));
        }

        @Override // com.sun.tools.jdi.ConnectorImpl.ArgumentImpl, com.sun.jdi.connect.Connector.Argument
        public boolean isValid(String str) {
            if (str == null) {
                return false;
            }
            try {
                return isValid(Integer.decode(str).intValue());
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public boolean isValid(int i) {
            return this.min <= i && i <= this.max;
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public String stringValueOf(int i) {
            return "" + i;
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public int intValue() {
            if (value() == null) {
                return 0;
            }
            try {
                return Integer.decode(value()).intValue();
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public int max() {
            return this.max;
        }

        @Override // com.sun.jdi.connect.Connector.IntegerArgument
        public int min() {
            return this.min;
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl$StringArgumentImpl.class */
    class StringArgumentImpl extends ArgumentImpl implements Connector.StringArgument {
        private static final long serialVersionUID = 7500484902692107464L;

        StringArgumentImpl(String str, String str2, String str3, String str4, boolean z) {
            super(str, str2, str3, str4, z);
        }

        @Override // com.sun.tools.jdi.ConnectorImpl.ArgumentImpl, com.sun.jdi.connect.Connector.Argument
        public boolean isValid(String str) {
            return true;
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdi/ConnectorImpl$SelectedArgumentImpl.class */
    class SelectedArgumentImpl extends ArgumentImpl implements Connector.SelectedArgument {
        private static final long serialVersionUID = -5689584530908382517L;
        private final List<String> choices;

        SelectedArgumentImpl(String str, String str2, String str3, String str4, boolean z, List<String> list) {
            super(str, str2, str3, str4, z);
            this.choices = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // com.sun.jdi.connect.Connector.SelectedArgument
        public List<String> choices() {
            return this.choices;
        }

        @Override // com.sun.tools.jdi.ConnectorImpl.ArgumentImpl, com.sun.jdi.connect.Connector.Argument
        public boolean isValid(String str) {
            return this.choices.contains(str);
        }
    }
}
