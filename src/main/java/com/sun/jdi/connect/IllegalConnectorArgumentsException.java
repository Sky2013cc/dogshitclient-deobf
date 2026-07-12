package com.sun.jdi.connect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/IllegalConnectorArgumentsException.class */
public class IllegalConnectorArgumentsException extends Exception {
    private static final long serialVersionUID = -3042212603611350941L;
    List<String> names;

    public IllegalConnectorArgumentsException(String str, String str2) {
        super(str);
        this.names = new ArrayList(1);
        this.names.add(str2);
    }

    public IllegalConnectorArgumentsException(String str, List<String> list) {
        super(str);
        this.names = new ArrayList(list);
    }

    public List<String> argumentNames() {
        return Collections.unmodifiableList(this.names);
    }
}
