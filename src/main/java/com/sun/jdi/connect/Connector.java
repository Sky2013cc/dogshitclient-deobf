package com.sun.jdi.connect;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/Connector.class */
public interface Connector {

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/Connector$Argument.class */
    public interface Argument extends Serializable {
        String name();

        String label();

        String description();

        String value();

        void setValue(String str);

        boolean isValid(String str);

        boolean mustSpecify();
    }

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/Connector$BooleanArgument.class */
    public interface BooleanArgument extends Argument {
        void setValue(boolean z);

        @Override // com.sun.jdi.connect.Connector.Argument
        boolean isValid(String str);

        String stringValueOf(boolean z);

        boolean booleanValue();
    }

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/Connector$IntegerArgument.class */
    public interface IntegerArgument extends Argument {
        void setValue(int i);

        @Override // com.sun.jdi.connect.Connector.Argument
        boolean isValid(String str);

        boolean isValid(int i);

        String stringValueOf(int i);

        int intValue();

        int max();

        int min();
    }

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/Connector$SelectedArgument.class */
    public interface SelectedArgument extends Argument {
        List<String> choices();

        @Override // com.sun.jdi.connect.Connector.Argument
        boolean isValid(String str);
    }

    @Exported
    /* loaded from: target.jar:com/sun/jdi/connect/Connector$StringArgument.class */
    public interface StringArgument extends Argument {
        @Override // com.sun.jdi.connect.Connector.Argument
        boolean isValid(String str);
    }

    String name();

    String description();

    Transport transport();

    Map<String, Argument> defaultArguments();
}
