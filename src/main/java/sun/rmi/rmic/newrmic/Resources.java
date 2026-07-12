package sun.rmi.rmic.newrmic;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: target.jar:sun/rmi/rmic/newrmic/Resources.class */
public final class Resources {
    private static ResourceBundle resources;
    private static ResourceBundle resourcesExt;

    static {
        resources = null;
        resourcesExt = null;
        try {
            resources = ResourceBundle.getBundle("sun.rmi.rmic.resources.rmic");
        } catch (MissingResourceException e) {
        }
        try {
            resourcesExt = ResourceBundle.getBundle("sun.rmi.rmic.resources.rmicext");
        } catch (MissingResourceException e2) {
        }
    }

    private Resources() {
        throw new AssertionError();
    }

    public static String getText(String str, String... strArr) {
        String string = getString(str);
        if (string == null) {
            string = "missing resource key: key = \"" + str + "\", arguments = \"{0}\", \"{1}\", \"{2}\"";
        }
        return MessageFormat.format(string, strArr);
    }

    private static String getString(String str) {
        if (resourcesExt != null) {
            try {
                return resourcesExt.getString(str);
            } catch (MissingResourceException e) {
            }
        }
        if (resources != null) {
            try {
                return resources.getString(str);
            } catch (MissingResourceException e2) {
                return null;
            }
        }
        return "missing resource bundle: key = \"" + str + "\", arguments = \"{0}\", \"{1}\", \"{2}\"";
    }
}
