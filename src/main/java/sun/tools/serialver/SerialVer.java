package sun.tools.serialver;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import jdk.internal.dynalink.CallSiteDescriptor;
import sun.net.www.ParseUtil;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:sun/tools/serialver/SerialVer.class */
public class SerialVer extends Applet {
    GridBagLayout gb;
    TextField classname_t;
    Button show_b;
    TextField serialversion_t;
    Label footer_l;
    private static final long serialVersionUID = 7666909783837760853L;
    static URLClassLoader loader = null;

    public synchronized void init() {
        this.gb = new GridBagLayout();
        setLayout(this.gb);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        Label label = new Label(Res.getText("FullClassName"));
        label.setAlignment(2);
        this.gb.setConstraints(label, gridBagConstraints);
        add(label);
        this.classname_t = new TextField(20);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0d;
        this.gb.setConstraints(this.classname_t, gridBagConstraints);
        add(this.classname_t);
        this.show_b = new Button(Res.getText("Show"));
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0d;
        this.gb.setConstraints(this.show_b, gridBagConstraints);
        add(this.show_b);
        Label label2 = new Label(Res.getText("SerialVersion"));
        label2.setAlignment(2);
        gridBagConstraints.gridwidth = 1;
        this.gb.setConstraints(label2, gridBagConstraints);
        add(label2);
        this.serialversion_t = new TextField(50);
        this.serialversion_t.setEditable(false);
        gridBagConstraints.gridwidth = 0;
        this.gb.setConstraints(this.serialversion_t, gridBagConstraints);
        add(this.serialversion_t);
        this.footer_l = new Label();
        gridBagConstraints.gridwidth = 0;
        this.gb.setConstraints(this.footer_l, gridBagConstraints);
        add(this.footer_l);
        this.classname_t.requestFocus();
    }

    public void start() {
        this.classname_t.requestFocus();
    }

    public boolean action(Event event, Object obj) {
        if (event.target == this.classname_t) {
            show((String) event.arg);
            return true;
        }
        if (event.target == this.show_b) {
            show(this.classname_t.getText());
            return true;
        }
        return false;
    }

    public boolean handleEvent(Event event) {
        return super.handleEvent(event);
    }

    void show(String str) {
        try {
            this.footer_l.setText("");
            this.serialversion_t.setText("");
            if (str.equals("")) {
                return;
            }
            String serialSyntax = serialSyntax(str);
            if (serialSyntax != null) {
                this.serialversion_t.setText(serialSyntax);
            } else {
                this.footer_l.setText(Res.getText("NotSerializable", str));
            }
        } catch (ClassNotFoundException e) {
            this.footer_l.setText(Res.getText("ClassNotFound", str));
        }
    }

    static void initializeLoader(String str) throws MalformedURLException, IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, File.pathSeparator);
        int countTokens = stringTokenizer.countTokens();
        URL[] urlArr = new URL[countTokens];
        for (int i = 0; i < countTokens; i++) {
            urlArr[i] = ParseUtil.fileToEncodedURL(new File(new File(stringTokenizer.nextToken()).getCanonicalPath()));
        }
        loader = new URLClassLoader(urlArr);
    }

    static String serialSyntax(String str) throws ClassNotFoundException {
        String str2 = null;
        boolean z = false;
        if (str.indexOf(36) != -1) {
            str2 = resolveClass(str);
        } else {
            try {
                str2 = resolveClass(str);
                z = true;
            } catch (ClassNotFoundException e) {
            }
            if (!z) {
                StringBuffer stringBuffer = new StringBuffer(str);
                String stringBuffer2 = stringBuffer.toString();
                while (true) {
                    int lastIndexOf = stringBuffer2.lastIndexOf(46);
                    if (lastIndexOf == -1 || z) {
                        break;
                    }
                    stringBuffer.setCharAt(lastIndexOf, '$');
                    try {
                        stringBuffer2 = stringBuffer.toString();
                        str2 = resolveClass(stringBuffer2);
                        z = true;
                    } catch (ClassNotFoundException e2) {
                    }
                }
            }
            if (!z) {
                throw new ClassNotFoundException();
            }
        }
        return str2;
    }

    static String resolveClass(String str) throws ClassNotFoundException {
        ObjectStreamClass lookup = ObjectStreamClass.lookup(Class.forName(str, false, loader));
        if (lookup != null) {
            return "    private static final long serialVersionUID = " + lookup.getSerialVersionUID() + "L;";
        }
        return null;
    }

    private static void showWindow(Window window) {
        window.show();
    }

    public static void main(String[] strArr) {
        boolean z = false;
        String str = null;
        if (strArr.length == 0) {
            usage();
            System.exit(1);
        }
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].equals("-show")) {
                z = true;
            } else if (strArr[i].equals("-classpath")) {
                if (i + 1 == strArr.length || strArr[i + 1].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                    System.err.println(Res.getText("error.missing.classpath"));
                    usage();
                    System.exit(1);
                }
                str = new String(strArr[i + 1]);
                i++;
            } else {
                if (!strArr[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                    break;
                }
                System.err.println(Res.getText("invalid.flag", strArr[i]));
                usage();
                System.exit(1);
            }
            i++;
        }
        if (str == null) {
            str = System.getProperty("env.class.path");
            if (str == null) {
                str = Constants.NAME_SEPARATOR;
            }
        }
        try {
            initializeLoader(str);
        } catch (MalformedURLException e) {
            System.err.println(Res.getText("error.parsing.classpath", str));
            System.exit(2);
        } catch (IOException e2) {
            System.err.println(Res.getText("error.parsing.classpath", str));
            System.exit(3);
        }
        if (!z) {
            if (i == strArr.length) {
                usage();
                System.exit(1);
            }
            boolean z2 = false;
            for (int i2 = i; i2 < strArr.length; i2++) {
                try {
                    String serialSyntax = serialSyntax(strArr[i2]);
                    if (serialSyntax != null) {
                        System.out.println(strArr[i2] + CallSiteDescriptor.TOKEN_DELIMITER + serialSyntax);
                    } else {
                        System.err.println(Res.getText("NotSerializable", strArr[i2]));
                        z2 = true;
                    }
                } catch (ClassNotFoundException e3) {
                    System.err.println(Res.getText("ClassNotFound", strArr[i2]));
                    z2 = true;
                }
            }
            if (z2) {
                System.exit(1);
                return;
            }
            return;
        }
        if (i < strArr.length) {
            System.err.println(Res.getText("ignoring.classes"));
            System.exit(1);
        }
        SerialVerFrame serialVerFrame = new SerialVerFrame();
        SerialVer serialVer = new SerialVer();
        serialVer.init();
        serialVerFrame.add("Center", serialVer);
        serialVerFrame.pack();
        showWindow(serialVerFrame);
    }

    public static void usage() {
        System.err.println(Res.getText("usage"));
    }
}
