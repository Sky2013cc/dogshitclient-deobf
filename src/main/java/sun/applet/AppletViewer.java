package sun.applet;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.doclets.internal.toolkit.util.VisibleMemberMap;
import com.sun.tools.doclint.DocLint;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.print.attribute.HashPrintRequestAttributeSet;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.awt.AppContext;
import sun.awt.SunToolkit;
import sun.misc.Ref;

/* loaded from: target.jar:sun/applet/AppletViewer.class */
public class AppletViewer extends Frame implements AppletContext, Printable {
    AppletViewerPanel panel;
    Label label;
    PrintStream statusMsgStream;
    AppletViewerFactory factory;
    static AppletProps props;
    static int c;
    private static int x;
    private static int y;
    private static final int XDELTA = 30;
    private static final int YDELTA = 30;
    static String encoding;
    private static AppletMessageHandler amh;
    private static String defaultSaveFile = "Applet.ser";
    private static Map audioClips = new HashMap();
    private static Map imageRefs = new HashMap();
    static Vector appletPanels = new Vector();
    static Hashtable systemParam = new Hashtable();

    static {
        systemParam.put("codebase", "codebase");
        systemParam.put("code", "code");
        systemParam.put("alt", "alt");
        systemParam.put("width", "width");
        systemParam.put("height", "height");
        systemParam.put("align", "align");
        systemParam.put("vspace", "vspace");
        systemParam.put("hspace", "hspace");
        x = 0;
        y = 0;
        encoding = null;
        amh = new AppletMessageHandler("appletviewer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:sun/applet/AppletViewer$UserActionListener.class */
    public final class UserActionListener implements ActionListener {
        private UserActionListener() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            AppletViewer.this.processUserAction(actionEvent);
        }
    }

    public AppletViewer(int i, int i2, URL url, Hashtable hashtable, PrintStream printStream, AppletViewerFactory appletViewerFactory) {
        this.factory = appletViewerFactory;
        this.statusMsgStream = printStream;
        setTitle(amh.getMessage("tool.title", hashtable.get("code")));
        MenuBar baseMenuBar = appletViewerFactory.getBaseMenuBar();
        Menu menu = new Menu(amh.getMessage("menu.applet"));
        addMenuItem(menu, "menuitem.restart");
        addMenuItem(menu, "menuitem.reload");
        addMenuItem(menu, "menuitem.stop");
        addMenuItem(menu, "menuitem.save");
        addMenuItem(menu, "menuitem.start");
        addMenuItem(menu, "menuitem.clone");
        menu.add(new MenuItem(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR));
        addMenuItem(menu, "menuitem.tag");
        addMenuItem(menu, "menuitem.info");
        addMenuItem(menu, "menuitem.edit").disable();
        addMenuItem(menu, "menuitem.encoding");
        menu.add(new MenuItem(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR));
        addMenuItem(menu, "menuitem.print");
        menu.add(new MenuItem(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR));
        addMenuItem(menu, "menuitem.props");
        menu.add(new MenuItem(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR));
        addMenuItem(menu, "menuitem.close");
        if (appletViewerFactory.isStandalone()) {
            addMenuItem(menu, "menuitem.quit");
        }
        baseMenuBar.add(menu);
        setMenuBar(baseMenuBar);
        AppletViewerPanel appletViewerPanel = new AppletViewerPanel(url, hashtable);
        this.panel = appletViewerPanel;
        add("Center", appletViewerPanel);
        Label label = new Label(amh.getMessage("label.hello"));
        this.label = label;
        add("South", label);
        this.panel.init();
        appletPanels.addElement(this.panel);
        pack();
        move(i, i2);
        setVisible(true);
        addWindowListener(new WindowAdapter() { // from class: sun.applet.AppletViewer.1
            public void windowClosing(WindowEvent windowEvent) {
                AppletViewer.this.appletClose();
            }

            public void windowIconified(WindowEvent windowEvent) {
                AppletViewer.this.appletStop();
            }

            public void windowDeiconified(WindowEvent windowEvent) {
                AppletViewer.this.appletStart();
            }
        });
        this.panel.addAppletListener(new AppletListener(this) { // from class: sun.applet.AppletViewer.1AppletEventListener
            final Frame frame;

            {
                this.frame = this;
            }

            @Override // sun.applet.AppletListener
            public void appletStateChanged(AppletEvent appletEvent) {
                AppletPanel appletPanel = (AppletPanel) appletEvent.getSource();
                switch (appletEvent.getID()) {
                    case AppletPanel.APPLET_RESIZE /* 51234 */:
                        if (appletPanel != null) {
                            AppletViewer.this.resize(AppletViewer.this.preferredSize());
                            AppletViewer.this.validate();
                            return;
                        }
                        return;
                    case AppletPanel.APPLET_LOADING_COMPLETED /* 51236 */:
                        Applet applet = appletPanel.getApplet();
                        if (applet != null) {
                            AppletPanel.changeFrameAppContext(this.frame, SunToolkit.targetToAppContext(applet));
                            return;
                        } else {
                            AppletPanel.changeFrameAppContext(this.frame, AppContext.getAppContext());
                            return;
                        }
                    default:
                        return;
                }
            }
        });
        showStatus(amh.getMessage("status.start"));
        initEventQueue();
    }

    public MenuItem addMenuItem(Menu menu, String str) {
        MenuItem menuItem = new MenuItem(amh.getMessage(str));
        menuItem.addActionListener(new UserActionListener());
        return menu.add(menuItem);
    }

    private void initEventQueue() {
        String property = System.getProperty("appletviewer.send.event");
        if (property == null) {
            this.panel.sendEvent(1);
            this.panel.sendEvent(2);
            this.panel.sendEvent(3);
            return;
        }
        String[] splitSeparator = splitSeparator(DocLint.TAGS_SEPARATOR, property);
        for (int i = 0; i < splitSeparator.length; i++) {
            System.out.println("Adding event to queue: " + splitSeparator[i]);
            if (splitSeparator[i].equals("dispose")) {
                this.panel.sendEvent(0);
            } else if (splitSeparator[i].equals("load")) {
                this.panel.sendEvent(1);
            } else if (splitSeparator[i].equals("init")) {
                this.panel.sendEvent(2);
            } else if (splitSeparator[i].equals(VisibleMemberMap.STARTLEVEL)) {
                this.panel.sendEvent(3);
            } else if (splitSeparator[i].equals("stop")) {
                this.panel.sendEvent(4);
            } else if (splitSeparator[i].equals("destroy")) {
                this.panel.sendEvent(5);
            } else if (splitSeparator[i].equals("quit")) {
                this.panel.sendEvent(6);
            } else if (splitSeparator[i].equals(FlatClientProperties.OUTLINE_ERROR)) {
                this.panel.sendEvent(7);
            } else {
                System.out.println("Unrecognized event name: " + splitSeparator[i]);
            }
        }
        do {
        } while (!this.panel.emptyEventQueue());
        appletSystemExit();
    }

    private String[] splitSeparator(String str, String str2) {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            int indexOf = str2.indexOf(str, i);
            if (indexOf != -1) {
                vector.addElement(str2.substring(i, indexOf));
                i = indexOf + 1;
            } else {
                vector.addElement(str2.substring(i));
                String[] strArr = new String[vector.size()];
                vector.copyInto(strArr);
                return strArr;
            }
        }
    }

    public AudioClip getAudioClip(URL url) {
        AudioClip audioClip;
        checkConnect(url);
        synchronized (audioClips) {
            AudioClip audioClip2 = (AudioClip) audioClips.get(url);
            if (audioClip2 == null) {
                Map map = audioClips;
                AppletAudioClip appletAudioClip = new AppletAudioClip(url);
                audioClip2 = appletAudioClip;
                map.put(url, appletAudioClip);
            }
            audioClip = audioClip2;
        }
        return audioClip;
    }

    public Image getImage(URL url) {
        return getCachedImage(url);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Image getCachedImage(URL url) {
        return (Image) getCachedImageRef(url).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Ref getCachedImageRef(URL url) {
        AppletImageRef appletImageRef;
        synchronized (imageRefs) {
            AppletImageRef appletImageRef2 = (AppletImageRef) imageRefs.get(url);
            if (appletImageRef2 == null) {
                appletImageRef2 = new AppletImageRef(url);
                imageRefs.put(url, appletImageRef2);
            }
            appletImageRef = appletImageRef2;
        }
        return appletImageRef;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void flushImageCache() {
        imageRefs.clear();
    }

    public Applet getApplet(String str) {
        String lowerCase = str.toLowerCase();
        SocketPermission socketPermission = new SocketPermission(this.panel.getCodeBase().getHost(), "connect");
        Enumeration elements = appletPanels.elements();
        while (elements.hasMoreElements()) {
            AppletPanel appletPanel = (AppletPanel) elements.nextElement();
            String parameter = appletPanel.getParameter("name");
            if (parameter != null) {
                parameter = parameter.toLowerCase();
            }
            if (lowerCase.equals(parameter) && appletPanel.getDocumentBase().equals(this.panel.getDocumentBase()) && socketPermission.implies(new SocketPermission(appletPanel.getCodeBase().getHost(), "connect"))) {
                return appletPanel.applet;
            }
        }
        return null;
    }

    public Enumeration getApplets() {
        Vector vector = new Vector();
        SocketPermission socketPermission = new SocketPermission(this.panel.getCodeBase().getHost(), "connect");
        Enumeration elements = appletPanels.elements();
        while (elements.hasMoreElements()) {
            AppletPanel appletPanel = (AppletPanel) elements.nextElement();
            if (appletPanel.getDocumentBase().equals(this.panel.getDocumentBase()) && socketPermission.implies(new SocketPermission(appletPanel.getCodeBase().getHost(), "connect"))) {
                vector.addElement(appletPanel.applet);
            }
        }
        return vector.elements();
    }

    public void showDocument(URL url) {
    }

    public void showDocument(URL url, String str) {
    }

    public void showStatus(String str) {
        this.label.setText(str);
    }

    public void setStream(String str, InputStream inputStream) throws IOException {
    }

    public InputStream getStream(String str) {
        return null;
    }

    public Iterator getStreamKeys() {
        return null;
    }

    public static void printTag(PrintStream printStream, Hashtable hashtable) {
        printStream.print("<applet");
        String str = (String) hashtable.get("codebase");
        if (str != null) {
            printStream.print(" codebase=\"" + str + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        }
        String str2 = (String) hashtable.get("code");
        if (str2 == null) {
            str2 = "applet.class";
        }
        printStream.print(" code=\"" + str2 + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        String str3 = (String) hashtable.get("width");
        if (str3 == null) {
            str3 = "150";
        }
        printStream.print(" width=" + str3);
        String str4 = (String) hashtable.get("height");
        if (str4 == null) {
            str4 = "100";
        }
        printStream.print(" height=" + str4);
        String str5 = (String) hashtable.get("name");
        if (str5 != null) {
            printStream.print(" name=\"" + str5 + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        }
        printStream.println(">");
        String[] strArr = new String[hashtable.size()];
        int i = 0;
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String str6 = (String) keys.nextElement();
            int i2 = 0;
            while (i2 < i && strArr[i2].compareTo(str6) < 0) {
                i2++;
            }
            System.arraycopy(strArr, i2, strArr, i2 + 1, i - i2);
            strArr[i2] = str6;
            i++;
        }
        for (int i3 = 0; i3 < i; i3++) {
            String str7 = strArr[i3];
            if (systemParam.get(str7) == null) {
                printStream.println("<param name=" + str7 + " value=\"" + hashtable.get(str7) + "\">");
            }
        }
        printStream.println("</applet>");
    }

    public void updateAtts() {
        Dimension size = this.panel.size();
        Insets insets = this.panel.insets();
        this.panel.atts.put("width", Integer.toString(size.width - (insets.left + insets.right)));
        this.panel.atts.put("height", Integer.toString(size.height - (insets.top + insets.bottom)));
    }

    void appletRestart() {
        this.panel.sendEvent(4);
        this.panel.sendEvent(5);
        this.panel.sendEvent(2);
        this.panel.sendEvent(3);
    }

    void appletReload() {
        this.panel.sendEvent(4);
        this.panel.sendEvent(5);
        this.panel.sendEvent(0);
        AppletPanel.flushClassLoader(this.panel.getClassLoaderCacheKey());
        try {
            this.panel.joinAppletThread();
            this.panel.release();
            this.panel.createAppletThread();
            this.panel.sendEvent(1);
            this.panel.sendEvent(2);
            this.panel.sendEvent(3);
        } catch (InterruptedException e) {
        }
    }

    void appletSave() {
        AccessController.doPrivileged(new PrivilegedAction() { // from class: sun.applet.AppletViewer.2
            /* JADX WARN: Failed to calculate best type for var: r11v1 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
             */
            /* JADX WARN: Failed to calculate best type for var: r11v1 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
             */
            /* JADX WARN: Failed to calculate best type for var: r12v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
             */
            /* JADX WARN: Failed to calculate best type for var: r12v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
             */
            /* JADX WARN: Failed to calculate best type for var: r13v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
             */
            /* JADX WARN: Failed to calculate best type for var: r13v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
             */
            /* JADX WARN: Failed to calculate best type for var: r14v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
             */
            /* JADX WARN: Failed to calculate best type for var: r14v0 ??
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
             */
            /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
            	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
            	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
            	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
             */
            /* JADX WARN: Not initialized variable reg: 11, insn: 0x0193: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:89:0x0193 */
            /* JADX WARN: Not initialized variable reg: 12, insn: 0x0198: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:91:0x0198 */
            /* JADX WARN: Not initialized variable reg: 13, insn: 0x013c: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:72:0x013c */
            /* JADX WARN: Not initialized variable reg: 14, insn: 0x0141: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:74:0x0141 */
            /* JADX WARN: Type inference failed for: r11v1, types: [java.io.FileOutputStream] */
            /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Throwable] */
            /* JADX WARN: Type inference failed for: r13v0, types: [java.io.BufferedOutputStream] */
            /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.Throwable] */
            @Override // java.security.PrivilegedAction
            public Object run() {
                ?? r11;
                ?? r12;
                ?? r13;
                ?? r14;
                AppletViewer.this.panel.sendEvent(4);
                FileDialog fileDialog = new FileDialog(AppletViewer.this, AppletViewer.amh.getMessage("appletsave.filedialogtitle"), 1);
                fileDialog.setDirectory(System.getProperty("user.dir"));
                fileDialog.setFile(AppletViewer.defaultSaveFile);
                fileDialog.show();
                String file = fileDialog.getFile();
                if (file == null) {
                    AppletViewer.this.panel.sendEvent(3);
                    return null;
                }
                File file2 = new File(fileDialog.getDirectory(), file);
                try {
                    try {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            Throwable th = null;
                            try {
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                                Throwable th2 = null;
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
                                Throwable th3 = null;
                                try {
                                    try {
                                        AppletViewer.this.showStatus(AppletViewer.amh.getMessage("appletsave.err1", AppletViewer.this.panel.applet.toString(), file2.toString()));
                                        objectOutputStream.writeObject(AppletViewer.this.panel.applet);
                                        if (objectOutputStream != null) {
                                            if (0 != 0) {
                                                try {
                                                    objectOutputStream.close();
                                                } catch (Throwable th4) {
                                                    th3.addSuppressed(th4);
                                                }
                                            } else {
                                                objectOutputStream.close();
                                            }
                                        }
                                        if (bufferedOutputStream != null) {
                                            if (0 != 0) {
                                                try {
                                                    bufferedOutputStream.close();
                                                } catch (Throwable th5) {
                                                    th2.addSuppressed(th5);
                                                }
                                            } else {
                                                bufferedOutputStream.close();
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            if (0 != 0) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (Throwable th6) {
                                                    th.addSuppressed(th6);
                                                }
                                            } else {
                                                fileOutputStream.close();
                                            }
                                        }
                                        AppletViewer.this.panel.sendEvent(3);
                                        return null;
                                    } catch (Throwable th7) {
                                        th3 = th7;
                                        throw th7;
                                    }
                                } catch (Throwable th8) {
                                    if (objectOutputStream != null) {
                                        if (th3 != null) {
                                            try {
                                                objectOutputStream.close();
                                            } catch (Throwable th9) {
                                                th3.addSuppressed(th9);
                                            }
                                        } else {
                                            objectOutputStream.close();
                                        }
                                    }
                                    throw th8;
                                }
                            } catch (Throwable th10) {
                                if (r13 != 0) {
                                    if (r14 != 0) {
                                        try {
                                            r13.close();
                                        } catch (Throwable th11) {
                                            r14.addSuppressed(th11);
                                        }
                                    } else {
                                        r13.close();
                                    }
                                }
                                throw th10;
                            }
                        } catch (Throwable th12) {
                            AppletViewer.this.panel.sendEvent(3);
                            throw th12;
                        }
                    } catch (Throwable th13) {
                        if (r11 != 0) {
                            if (r12 != 0) {
                                try {
                                    r11.close();
                                } catch (Throwable th14) {
                                    r12.addSuppressed(th14);
                                }
                            } else {
                                r11.close();
                            }
                        }
                        throw th13;
                    }
                } catch (IOException e) {
                    System.err.println(AppletViewer.amh.getMessage("appletsave.err2", e));
                    AppletViewer.this.panel.sendEvent(3);
                    return null;
                }
            }
        });
    }

    void appletClone() {
        Point location = location();
        updateAtts();
        this.factory.createAppletViewer(location.x + 30, location.y + 30, this.panel.documentURL, (Hashtable) this.panel.atts.clone());
    }

    void appletTag() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        updateAtts();
        printTag(new PrintStream(byteArrayOutputStream), this.panel.atts);
        showStatus(amh.getMessage("applettag"));
        Point location = location();
        new TextFrame(location.x + 30, location.y + 30, amh.getMessage("applettag.textframe"), byteArrayOutputStream.toString());
    }

    void appletInfo() {
        String appletInfo = this.panel.applet.getAppletInfo();
        if (appletInfo == null) {
            appletInfo = amh.getMessage("appletinfo.applet");
        }
        String str = appletInfo + "\n\n";
        String[][] parameterInfo = this.panel.applet.getParameterInfo();
        if (parameterInfo != null) {
            for (int i = 0; i < parameterInfo.length; i++) {
                str = str + parameterInfo[i][0] + " -- " + parameterInfo[i][1] + " -- " + parameterInfo[i][2] + "\n";
            }
        } else {
            str = str + amh.getMessage("appletinfo.param");
        }
        Point location = location();
        new TextFrame(location.x + 30, location.y + 30, amh.getMessage("appletinfo.textframe"), str);
    }

    void appletCharacterEncoding() {
        showStatus(amh.getMessage("appletencoding", encoding));
    }

    void appletEdit() {
    }

    void appletPrint() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        if (printerJob != null) {
            HashPrintRequestAttributeSet hashPrintRequestAttributeSet = new HashPrintRequestAttributeSet();
            if (printerJob.printDialog(hashPrintRequestAttributeSet)) {
                printerJob.setPrintable(this);
                try {
                    printerJob.print(hashPrintRequestAttributeSet);
                    this.statusMsgStream.println(amh.getMessage("appletprint.finish"));
                    return;
                } catch (PrinterException e) {
                    this.statusMsgStream.println(amh.getMessage("appletprint.fail"));
                    return;
                }
            }
            this.statusMsgStream.println(amh.getMessage("appletprint.cancel"));
            return;
        }
        this.statusMsgStream.println(amh.getMessage("appletprint.fail"));
    }

    public int print(Graphics graphics, PageFormat pageFormat, int i) {
        if (i > 0) {
            return 1;
        }
        ((Graphics2D) graphics).translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        this.panel.applet.printAll(graphics);
        return 0;
    }

    public static synchronized void networkProperties() {
        if (props == null) {
            props = new AppletProps();
        }
        props.addNotify();
        props.setVisible(true);
    }

    void appletStart() {
        this.panel.sendEvent(3);
    }

    void appletStop() {
        this.panel.sendEvent(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appletShutdown(AppletPanel appletPanel) {
        appletPanel.sendEvent(4);
        appletPanel.sendEvent(5);
        appletPanel.sendEvent(0);
        appletPanel.sendEvent(6);
    }

    void appletClose() {
        final AppletViewerPanel appletViewerPanel = this.panel;
        new Thread(new Runnable() { // from class: sun.applet.AppletViewer.3
            @Override // java.lang.Runnable
            public void run() {
                AppletViewer.this.appletShutdown(appletViewerPanel);
                AppletViewer.appletPanels.removeElement(appletViewerPanel);
                AppletViewer.this.dispose();
                if (AppletViewer.countApplets() == 0) {
                    AppletViewer.this.appletSystemExit();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appletSystemExit() {
        if (this.factory.isStandalone()) {
            System.exit(0);
        }
    }

    protected void appletQuit() {
        new Thread(new Runnable() { // from class: sun.applet.AppletViewer.4
            @Override // java.lang.Runnable
            public void run() {
                Enumeration elements = AppletViewer.appletPanels.elements();
                while (elements.hasMoreElements()) {
                    AppletViewer.this.appletShutdown((AppletPanel) elements.nextElement());
                }
                AppletViewer.this.appletSystemExit();
            }
        }).start();
    }

    public void processUserAction(ActionEvent actionEvent) {
        String label = ((MenuItem) actionEvent.getSource()).getLabel();
        if (amh.getMessage("menuitem.restart").equals(label)) {
            appletRestart();
            return;
        }
        if (amh.getMessage("menuitem.reload").equals(label)) {
            appletReload();
            return;
        }
        if (amh.getMessage("menuitem.clone").equals(label)) {
            appletClone();
            return;
        }
        if (amh.getMessage("menuitem.stop").equals(label)) {
            appletStop();
            return;
        }
        if (amh.getMessage("menuitem.save").equals(label)) {
            appletSave();
            return;
        }
        if (amh.getMessage("menuitem.start").equals(label)) {
            appletStart();
            return;
        }
        if (amh.getMessage("menuitem.tag").equals(label)) {
            appletTag();
            return;
        }
        if (amh.getMessage("menuitem.info").equals(label)) {
            appletInfo();
            return;
        }
        if (amh.getMessage("menuitem.encoding").equals(label)) {
            appletCharacterEncoding();
            return;
        }
        if (amh.getMessage("menuitem.edit").equals(label)) {
            appletEdit();
            return;
        }
        if (amh.getMessage("menuitem.print").equals(label)) {
            appletPrint();
            return;
        }
        if (amh.getMessage("menuitem.props").equals(label)) {
            networkProperties();
            return;
        }
        if (amh.getMessage("menuitem.close").equals(label)) {
            appletClose();
        } else if (this.factory.isStandalone() && amh.getMessage("menuitem.quit").equals(label)) {
            appletQuit();
        }
    }

    public static int countApplets() {
        return appletPanels.size();
    }

    public static void skipSpace(Reader reader) throws IOException {
        while (c >= 0) {
            if (c == 32 || c == 9 || c == 10 || c == 13) {
                c = reader.read();
            } else {
                return;
            }
        }
    }

    public static String scanIdentifier(Reader reader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            if ((c >= 97 && c <= 122) || ((c >= 65 && c <= 90) || ((c >= 48 && c <= 57) || c == 95))) {
                stringBuffer.append((char) c);
                c = reader.read();
            } else {
                return stringBuffer.toString();
            }
        }
    }

    public static Hashtable scanTag(Reader reader) throws IOException {
        Hashtable hashtable = new Hashtable();
        skipSpace(reader);
        while (c >= 0 && c != 62) {
            String scanIdentifier = scanIdentifier(reader);
            String str = "";
            skipSpace(reader);
            if (c == 61) {
                int i = -1;
                c = reader.read();
                skipSpace(reader);
                if (c == 39 || c == 34) {
                    i = c;
                    c = reader.read();
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (c > 0 && ((i < 0 && c != 32 && c != 9 && c != 10 && c != 13 && c != 62) || (i >= 0 && c != i))) {
                    stringBuffer.append((char) c);
                    c = reader.read();
                }
                if (c == i) {
                    c = reader.read();
                }
                skipSpace(reader);
                str = stringBuffer.toString();
            }
            if (!str.equals("")) {
                hashtable.put(scanIdentifier.toLowerCase(Locale.ENGLISH), str);
            }
            while (c != 62 && c >= 0 && ((c < 97 || c > 122) && ((c < 65 || c > 90) && ((c < 48 || c > 57) && c != 95)))) {
                c = reader.read();
            }
        }
        return hashtable;
    }

    private static Reader makeReader(InputStream inputStream) {
        if (encoding != null) {
            try {
                return new BufferedReader(new InputStreamReader(inputStream, encoding));
            } catch (IOException e) {
            }
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        encoding = inputStreamReader.getEncoding();
        return new BufferedReader(inputStreamReader);
    }

    public static void parse(URL url, String str) throws IOException {
        encoding = str;
        parse(url, System.out, new StdAppletViewerFactory());
    }

    public static void parse(URL url) throws IOException {
        parse(url, System.out, new StdAppletViewerFactory());
    }

    public static void parse(URL url, PrintStream printStream, AppletViewerFactory appletViewerFactory) throws IOException {
        boolean z = false;
        String message = amh.getMessage("parse.warning.requiresname");
        String message2 = amh.getMessage("parse.warning.paramoutside");
        String message3 = amh.getMessage("parse.warning.applet.requirescode");
        String message4 = amh.getMessage("parse.warning.applet.requiresheight");
        String message5 = amh.getMessage("parse.warning.applet.requireswidth");
        String message6 = amh.getMessage("parse.warning.object.requirescode");
        String message7 = amh.getMessage("parse.warning.object.requiresheight");
        String message8 = amh.getMessage("parse.warning.object.requireswidth");
        String message9 = amh.getMessage("parse.warning.embed.requirescode");
        String message10 = amh.getMessage("parse.warning.embed.requiresheight");
        String message11 = amh.getMessage("parse.warning.embed.requireswidth");
        String message12 = amh.getMessage("parse.warning.appnotLongersupported");
        URLConnection openConnection = url.openConnection();
        Reader makeReader = makeReader(openConnection.getInputStream());
        URL url2 = openConnection.getURL();
        int i = 1;
        Hashtable hashtable = null;
        while (true) {
            c = makeReader.read();
            if (c != -1) {
                if (c == 60) {
                    c = makeReader.read();
                    if (c == 47) {
                        c = makeReader.read();
                        String scanIdentifier = scanIdentifier(makeReader);
                        if (scanIdentifier.equalsIgnoreCase("applet") || scanIdentifier.equalsIgnoreCase("object") || scanIdentifier.equalsIgnoreCase("embed")) {
                            if (z && hashtable.get("code") == null && hashtable.get("object") == null) {
                                printStream.println(message6);
                                hashtable = null;
                            }
                            if (hashtable != null) {
                                appletViewerFactory.createAppletViewer(x, y, url2, hashtable);
                                x += 30;
                                y += 30;
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                if (x > screenSize.width - OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT || y > screenSize.height - OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT) {
                                    x = 0;
                                    y = 2 * i * 30;
                                    i++;
                                }
                            }
                            hashtable = null;
                            z = false;
                        }
                    } else {
                        String scanIdentifier2 = scanIdentifier(makeReader);
                        if (scanIdentifier2.equalsIgnoreCase("param")) {
                            Hashtable scanTag = scanTag(makeReader);
                            String str = (String) scanTag.get("name");
                            if (str == null) {
                                printStream.println(message);
                            } else {
                                String str2 = (String) scanTag.get("value");
                                if (str2 == null) {
                                    printStream.println(message);
                                } else if (hashtable != null) {
                                    hashtable.put(str.toLowerCase(), str2);
                                } else {
                                    printStream.println(message2);
                                }
                            }
                        } else if (scanIdentifier2.equalsIgnoreCase("applet")) {
                            hashtable = scanTag(makeReader);
                            if (hashtable.get("code") == null && hashtable.get("object") == null) {
                                printStream.println(message3);
                                hashtable = null;
                            } else if (hashtable.get("width") == null) {
                                printStream.println(message5);
                                hashtable = null;
                            } else if (hashtable.get("height") == null) {
                                printStream.println(message4);
                                hashtable = null;
                            }
                        } else if (scanIdentifier2.equalsIgnoreCase("object")) {
                            z = true;
                            hashtable = scanTag(makeReader);
                            if (hashtable.get("codebase") != null) {
                                hashtable.remove("codebase");
                            }
                            if (hashtable.get("width") == null) {
                                printStream.println(message8);
                                hashtable = null;
                            } else if (hashtable.get("height") == null) {
                                printStream.println(message7);
                                hashtable = null;
                            }
                        } else if (scanIdentifier2.equalsIgnoreCase("embed")) {
                            hashtable = scanTag(makeReader);
                            if (hashtable.get("code") == null && hashtable.get("object") == null) {
                                printStream.println(message9);
                                hashtable = null;
                            } else if (hashtable.get("width") == null) {
                                printStream.println(message11);
                                hashtable = null;
                            } else if (hashtable.get("height") == null) {
                                printStream.println(message10);
                                hashtable = null;
                            }
                        } else if (scanIdentifier2.equalsIgnoreCase("app")) {
                            printStream.println(message12);
                            Hashtable scanTag2 = scanTag(makeReader);
                            String str3 = (String) scanTag2.get("class");
                            if (str3 != null) {
                                scanTag2.remove("class");
                                scanTag2.put("code", str3 + ".class");
                            }
                            String str4 = (String) scanTag2.get("src");
                            if (str4 != null) {
                                scanTag2.remove("src");
                                scanTag2.put("codebase", str4);
                            }
                            if (scanTag2.get("width") == null) {
                                scanTag2.put("width", "100");
                            }
                            if (scanTag2.get("height") == null) {
                                scanTag2.put("height", "100");
                            }
                            printTag(printStream, scanTag2);
                            printStream.println();
                        }
                    }
                }
            } else {
                makeReader.close();
                return;
            }
        }
    }

    @Deprecated
    public static void main(String[] strArr) {
        Main.main(strArr);
    }

    private static void checkConnect(URL url) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                Permission permission = url.openConnection().getPermission();
                if (permission != null) {
                    securityManager.checkPermission(permission);
                } else {
                    securityManager.checkConnect(url.getHost(), url.getPort());
                }
            } catch (IOException e) {
                securityManager.checkConnect(url.getHost(), url.getPort());
            }
        }
    }
}
