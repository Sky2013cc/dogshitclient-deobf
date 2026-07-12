package net.ccbluex.liquidbounce.injector;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.sun.tools.doclint.DocLint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicProgressBarUI;
import jdk.internal.dynalink.CallSiteDescriptor;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.util.Constants;

/* compiled from: InjectorGUI.kt */
@Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018��2\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010#\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00060\u00060\bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n��¨\u0006%"}, d2 = {"Lnet/ccbluex/liquidbounce/injector/InjectorGUI;", "Ljavax/swing/JFrame;", Constants.CTOR, "()V", "processListModel", "Ljavax/swing/DefaultListModel;", "Lnet/ccbluex/liquidbounce/injector/ProcessInfo;", "processList", "Ljavax/swing/JList;", "kotlin.jvm.PlatformType", "statusLabel", "Ljavax/swing/JLabel;", "progressBar", "Ljavax/swing/JProgressBar;", "agentPathLabel", "agentJarPath", "Ljava/io/File;", "autoDetectAgentJar", "", "setupLookAndFeel", "setupUI", "updateButtonState", "refreshProcesses", "findMinecraftProcesses", "", "selectAgentJar", "injectSelected", "findAgentJar", "injectAgent", "", "pid", "", "agentPath", "", "tryInjectWithJattach", "tryInjectWithPowerShell", "ProcessListCellRenderer", "haze"})
@SourceDebugExtension({"SMAP\nInjectorGUI.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InjectorGUI.kt\nnet/ccbluex/liquidbounce/injector/InjectorGUI\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,626:1\n4135#2,11:627\n1310#2,2:691\n1310#2,2:693\n1368#3:638\n1454#3,5:639\n808#3,11:644\n1368#3:655\n1454#3,5:656\n808#3,11:661\n295#3,2:672\n774#3:674\n865#3,2:675\n1863#3:677\n1557#3:678\n1628#3,3:679\n1864#3:682\n1663#3,8:683\n1863#3,2:695\n*S KotlinDebug\n*F\n+ 1 InjectorGUI.kt\nnet/ccbluex/liquidbounce/injector/InjectorGUI\n*L\n196#1:627,11\n444#1:691,2\n452#1:693,2\n197#1:638\n197#1:639,5\n198#1:644,11\n199#1:655\n199#1:656,5\n200#1:661,11\n201#1:672,2\n258#1:674\n258#1:675,2\n300#1:677\n302#1:678\n302#1:679,3\n300#1:682\n316#1:683,8\n224#1:695,2\n*E\n"})
/* loaded from: target.jar:net/ccbluex/liquidbounce/injector/InjectorGUI.class */
public final class InjectorGUI extends JFrame {

    @NotNull
    private final DefaultListModel<ProcessInfo> processListModel;

    @NotNull
    private final JList<ProcessInfo> processList;

    @NotNull
    private final JLabel statusLabel;

    @NotNull
    private final JProgressBar progressBar;

    @NotNull
    private final JLabel agentPathLabel;

    @Nullable
    private File agentJarPath;

    public InjectorGUI() {
        super("Haze Injector");
        this.processListModel = new DefaultListModel<>();
        this.processList = new JList<>(this.processListModel);
        this.statusLabel = new JLabel("Ready");
        this.progressBar = new JProgressBar();
        this.agentPathLabel = new JLabel("Agent JAR: Auto-detect");
        setupLookAndFeel();
        setupUI();
        refreshProcesses();
        autoDetectAgentJar();
    }

    private final void autoDetectAgentJar() {
        this.agentJarPath = findAgentJar();
        if (this.agentJarPath != null) {
            JLabel jLabel = this.agentPathLabel;
            StringBuilder append = new StringBuilder().append("Agent JAR: ");
            File file = this.agentJarPath;
            Intrinsics.checkNotNull(file);
            jLabel.setText(append.append(file.getName()).toString());
            this.agentPathLabel.setForeground(new Color(0, 150, 0));
            return;
        }
        this.agentPathLabel.setText("Agent JAR: Not found - Click 'Select JAR'");
        this.agentPathLabel.setForeground(new Color(200, 0, 0));
    }

    private final void setupLookAndFeel() {
        try {
            FlatMacLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void setupUI() {
        setDefaultCloseOperation(3);
        setSize(500, OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
        setResizable(false);
        setLayout((LayoutManager) new BorderLayout(0, 10));
        JPanel $this$setupUI_u24lambda_u242 = new JPanel(new BorderLayout());
        $this$setupUI_u24lambda_u242.setBorder(new EmptyBorder(15, 15, 10, 15));
        $this$setupUI_u24lambda_u242.setBackground(new Color(245, 245, 247));
        $this$setupUI_u24lambda_u242.setPreferredSize(new Dimension(500, 80));
        Component jLabel = new JLabel("Haze Injector");
        jLabel.setFont(new Font("Segoe UI", 1, 24));
        jLabel.setForeground(new Color(50, 50, 50));
        $this$setupUI_u24lambda_u242.add(jLabel, "West");
        Component jLabel2 = new JLabel("Select a Minecraft process to inject");
        jLabel2.setFont(new Font("Segoe UI", 0, 12));
        jLabel2.setForeground(new Color(120, 120, 120));
        $this$setupUI_u24lambda_u242.add(jLabel2, "South");
        add((Component) $this$setupUI_u24lambda_u242, "North");
        JPanel $this$setupUI_u24lambda_u246 = new JPanel(new BorderLayout());
        $this$setupUI_u24lambda_u246.setBorder(new EmptyBorder(0, 15, 0, 15));
        JList $this$setupUI_u24lambda_u246_u24lambda_u244 = this.processList;
        $this$setupUI_u24lambda_u246_u24lambda_u244.setCellRenderer(new ProcessListCellRenderer());
        $this$setupUI_u24lambda_u246_u24lambda_u244.setSelectionMode(0);
        $this$setupUI_u24lambda_u246_u24lambda_u244.setFixedCellHeight(50);
        $this$setupUI_u24lambda_u246_u24lambda_u244.addListSelectionListener((v1) -> {
            setupUI$lambda$6$lambda$4$lambda$3(r1, v1);
        });
        $this$setupUI_u24lambda_u246_u24lambda_u244.addMouseListener(new MouseAdapter() { // from class: net.ccbluex.liquidbounce.injector.InjectorGUI$setupUI$listPanel$1$1$2
            public void mouseClicked(MouseEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (e.getClickCount() == 2) {
                    InjectorGUI.this.injectSelected();
                }
            }
        });
        Component jScrollPane = new JScrollPane(this.processList);
        jScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true), new EmptyBorder(5, 5, 5, 5)));
        $this$setupUI_u24lambda_u246.add(jScrollPane, "Center");
        add((Component) $this$setupUI_u24lambda_u246, "Center");
        JPanel $this$setupUI_u24lambda_u2416 = new JPanel(new BorderLayout());
        $this$setupUI_u24lambda_u2416.setBorder(new EmptyBorder(10, 15, 15, 15));
        JLabel $this$setupUI_u24lambda_u2416_u24lambda_u247 = this.agentPathLabel;
        $this$setupUI_u24lambda_u2416_u24lambda_u247.setFont(new Font("Segoe UI", 0, 11));
        $this$setupUI_u24lambda_u2416_u24lambda_u247.setHorizontalAlignment(0);
        $this$setupUI_u24lambda_u2416_u24lambda_u247.setBorder(new EmptyBorder(5, 0, 5, 0));
        $this$setupUI_u24lambda_u2416.add(this.agentPathLabel, "North");
        JProgressBar $this$setupUI_u24lambda_u2416_u24lambda_u248 = this.progressBar;
        $this$setupUI_u24lambda_u2416_u24lambda_u248.setIndeterminate(false);
        $this$setupUI_u24lambda_u2416_u24lambda_u248.setValue(0);
        $this$setupUI_u24lambda_u2416_u24lambda_u248.setPreferredSize(new Dimension(500, 25));
        $this$setupUI_u24lambda_u2416_u24lambda_u248.setVisible(false);
        $this$setupUI_u24lambda_u2416_u24lambda_u248.setUI(new BasicProgressBarUI() { // from class: net.ccbluex.liquidbounce.injector.InjectorGUI$setupUI$bottomPanel$1$2$1
            protected Color getSelectionBackground() {
                return new Color(255, 255, 255);
            }

            protected Color getSelectionForeground() {
                return new Color(255, 255, 255);
            }
        });
        $this$setupUI_u24lambda_u2416.add(this.progressBar, "Center");
        Component jPanel = new JPanel(new FlowLayout(1, 10, 5));
        Component jButton = new JButton("Select JAR");
        jButton.setFont(new Font("Segoe UI", 0, 13));
        jButton.setPreferredSize(new Dimension(100, 35));
        jButton.addActionListener((v1) -> {
            setupUI$lambda$16$lambda$10$lambda$9(r1, v1);
        });
        jPanel.add(jButton);
        Component jButton2 = new JButton("Refresh");
        jButton2.setFont(new Font("Segoe UI", 0, 13));
        jButton2.setPreferredSize(new Dimension(100, 35));
        jButton2.addActionListener((v1) -> {
            setupUI$lambda$16$lambda$12$lambda$11(r1, v1);
        });
        jPanel.add(jButton2);
        Component jButton3 = new JButton("Inject");
        jButton3.setFont(new Font("Segoe UI", 1, 13));
        jButton3.setPreferredSize(new Dimension(120, 35));
        jButton3.setBackground(new Color(0, 122, 255));
        jButton3.setForeground(Color.WHITE);
        jButton3.setOpaque(true);
        jButton3.setBorderPainted(false);
        jButton3.setToolTipText("Inject Haze into selected process");
        jButton3.addActionListener((v1) -> {
            setupUI$lambda$16$lambda$14$lambda$13(r1, v1);
        });
        jButton3.setName("injectButton");
        jPanel.add(jButton3);
        $this$setupUI_u24lambda_u2416.add(jPanel, "South");
        JLabel $this$setupUI_u24lambda_u2416_u24lambda_u2415 = this.statusLabel;
        $this$setupUI_u24lambda_u2416_u24lambda_u2415.setFont(new Font("Segoe UI", 0, 11));
        $this$setupUI_u24lambda_u2416_u24lambda_u2415.setForeground(new Color(120, 120, 120));
        $this$setupUI_u24lambda_u2416_u24lambda_u2415.setHorizontalAlignment(0);
        $this$setupUI_u24lambda_u2416_u24lambda_u2415.setBorder(new EmptyBorder(10, 0, 0, 0));
        add((Component) $this$setupUI_u24lambda_u2416, "South");
        setLocationRelativeTo(null);
    }

    private static final void setupUI$lambda$6$lambda$4$lambda$3(InjectorGUI this$0, ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            this$0.updateButtonState();
        }
    }

    private static final void setupUI$lambda$16$lambda$10$lambda$9(InjectorGUI this$0, ActionEvent it) {
        this$0.selectAgentJar();
    }

    private static final void setupUI$lambda$16$lambda$12$lambda$11(InjectorGUI this$0, ActionEvent it) {
        this$0.refreshProcesses();
    }

    private static final void setupUI$lambda$16$lambda$14$lambda$13(InjectorGUI this$0, ActionEvent it) {
        this$0.injectSelected();
    }

    private final void updateButtonState() {
        Object obj;
        Container contentPane = getContentPane();
        Component[] components = contentPane.getComponents();
        Intrinsics.checkNotNullExpressionValue(components, "getComponents(...)");
        Component[] componentArr = components;
        Collection destination$iv$iv = new ArrayList();
        for (Component component : componentArr) {
            if (component instanceof JPanel) {
                destination$iv$iv.add(component);
            }
        }
        Iterable<JPanel> $this$flatMap$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (JPanel panel : $this$flatMap$iv) {
            Component[] components2 = panel.getComponents();
            Intrinsics.checkNotNullExpressionValue(components2, "getComponents(...)");
            Iterable list$iv$iv = ArraysKt.toList(components2);
            CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
        }
        Iterable $this$filterIsInstance$iv = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof JPanel) {
                destination$iv$iv3.add(element$iv$iv);
            }
        }
        Iterable<JPanel> $this$flatMap$iv2 = (List) destination$iv$iv3;
        Collection destination$iv$iv4 = new ArrayList();
        for (JPanel panel2 : $this$flatMap$iv2) {
            Component[] components3 = panel2.getComponents();
            Intrinsics.checkNotNullExpressionValue(components3, "getComponents(...)");
            Iterable list$iv$iv2 = ArraysKt.toList(components3);
            CollectionsKt.addAll(destination$iv$iv4, list$iv$iv2);
        }
        Iterable $this$filterIsInstance$iv2 = (List) destination$iv$iv4;
        Collection destination$iv$iv5 = new ArrayList();
        for (Object element$iv$iv2 : $this$filterIsInstance$iv2) {
            if (element$iv$iv2 instanceof JButton) {
                destination$iv$iv5.add(element$iv$iv2);
            }
        }
        Iterable $this$firstOrNull$iv = (List) destination$iv$iv5;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                JButton button = (JButton) element$iv;
                if (Intrinsics.areEqual(button.getName(), "injectButton")) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        JButton injectButton = (JButton) obj;
        if (injectButton != null) {
            injectButton.setEnabled(this.processList.getSelectedValue() != null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshProcesses() {
        this.statusLabel.setText("Scanning for Minecraft processes...");
        this.processListModel.clear();
        Thread $this$refreshProcesses_u24lambda_u2424 = new Thread(() -> {
            refreshProcesses$lambda$23(r2);
        });
        $this$refreshProcesses_u24lambda_u2424.setName("Process Scanner");
        $this$refreshProcesses_u24lambda_u2424.setDaemon(true);
        $this$refreshProcesses_u24lambda_u2424.start();
    }

    private static final void refreshProcesses$lambda$23(InjectorGUI this$0) {
        try {
            List processes = this$0.findMinecraftProcesses();
            SwingUtilities.invokeLater(() -> {
                refreshProcesses$lambda$23$lambda$21(r0, r1);
            });
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                refreshProcesses$lambda$23$lambda$22(r0, r1);
            });
        }
    }

    private static final void refreshProcesses$lambda$23$lambda$21(List $processes, InjectorGUI this$0) {
        if ($processes.isEmpty()) {
            this$0.statusLabel.setText("No Minecraft processes found. Start Minecraft first.");
            JOptionPane.showMessageDialog((Component) this$0, "No Minecraft processes found.\nPlease start Minecraft with Forge before injecting.", "No Process Found", 2);
            return;
        }
        List $this$forEach$iv = $processes;
        for (Object element$iv : $this$forEach$iv) {
            ProcessInfo it = (ProcessInfo) element$iv;
            this$0.processListModel.addElement(it);
        }
        this$0.statusLabel.setText("Found " + $processes.size() + " Minecraft process(es)");
    }

    private static final void refreshProcesses$lambda$23$lambda$22(InjectorGUI this$0, Exception $e) {
        this$0.statusLabel.setText("Error: " + $e.getMessage());
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0255, code lost:
    
        if (r0 != false) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<ProcessInfo> findMinecraftProcesses() {
        Long pid;
        String str;
        boolean z;
        List processes = new ArrayList();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "-Command", "Get-Process | Where-Object { $_.Name -eq 'java' -or $_.Name -eq 'javaw' } | Select-Object Id, MainWindowTitle, @{Name='CommandLine';Expression={(Get-WmiObject Win32_Process -Filter \"ProcessId=$($_.Id)\").CommandLine}} | ConvertTo-Json");
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            String output = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
            process.waitFor();
            if ((!StringsKt.isBlank(output)) && !Intrinsics.areEqual(output, "null")) {
                Iterable $this$filter$iv = StringsKt.lines(output);
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    String it = (String) element$iv$iv;
                    if (!StringsKt.isBlank(it)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List lines = (List) destination$iv$iv;
                Long currentId = null;
                String currentTitle = null;
                Iterator it2 = lines.iterator();
                while (it2.hasNext()) {
                    String trimmed = StringsKt.trim((CharSequence) it2.next()).toString();
                    if (StringsKt.startsWith$default(trimmed, "\"Id\":", false, 2, (Object) null)) {
                        currentId = StringsKt.toLongOrNull(StringsKt.trim(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(trimmed, CallSiteDescriptor.TOKEN_DELIMITER, (String) null, 2, (Object) null)).toString(), ','));
                    } else if (StringsKt.startsWith$default(trimmed, "\"MainWindowTitle\":", false, 2, (Object) null)) {
                        currentTitle = StringsKt.trim(StringsKt.trim(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(trimmed, CallSiteDescriptor.TOKEN_DELIMITER, (String) null, 2, (Object) null)).toString(), ','), '\"');
                    } else if (StringsKt.startsWith$default(trimmed, "\"CommandLine\":", false, 2, (Object) null)) {
                        String currentCmd = StringsKt.trim(StringsKt.trim(StringsKt.trim((CharSequence) StringsKt.substringAfter$default(trimmed, CallSiteDescriptor.TOKEN_DELIMITER, (String) null, 2, (Object) null)).toString(), ','), '\"');
                        if (currentId != null) {
                            if (!(currentCmd != null ? StringsKt.contains((CharSequence) currentCmd, (CharSequence) "minecraft", true) : false)) {
                                if (currentCmd != null) {
                                    z = StringsKt.contains((CharSequence) currentCmd, (CharSequence) "forge", true);
                                } else {
                                    z = false;
                                }
                            }
                            String str2 = currentTitle;
                            if (!(str2 == null || StringsKt.isBlank(str2))) {
                                str = currentTitle;
                            } else {
                                str = "Minecraft (PID: " + currentId + ')';
                            }
                            String displayName = str;
                            processes.add(new ProcessInfo(currentId.longValue(), "Minecraft", displayName));
                        }
                        currentId = null;
                        currentTitle = null;
                    }
                }
            }
        } catch (Exception e) {
            try {
                ProcessBuilder processBuilder2 = new ProcessBuilder("tasklist", "/FI", "IMAGENAME eq java.exe", "/FO", "CSV", "/NH");
                Process process2 = processBuilder2.start();
                InputStream inputStream2 = process2.getInputStream();
                Intrinsics.checkNotNullExpressionValue(inputStream2, "getInputStream(...)");
                Reader inputStreamReader2 = new InputStreamReader(inputStream2, Charsets.UTF_8);
                String output2 = TextStreamsKt.readText(inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192));
                process2.waitFor();
                Iterable $this$forEach$iv = StringsKt.lines(output2);
                for (Object element$iv : $this$forEach$iv) {
                    String line = (String) element$iv;
                    if (StringsKt.contains$default((CharSequence) line, (CharSequence) "java.exe", false, 2, (Object) null)) {
                        Iterable $this$map$iv = StringsKt.split$default((CharSequence) line, new String[]{DocLint.TAGS_SEPARATOR}, false, 0, 6, (Object) null);
                        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            String it3 = (String) item$iv$iv;
                            destination$iv$iv2.add(StringsKt.trim(it3, '\"'));
                        }
                        List parts = (List) destination$iv$iv2;
                        if (parts.size() >= 2 && (pid = StringsKt.toLongOrNull((String) parts.get(1))) != null) {
                            processes.add(new ProcessInfo(pid.longValue(), "Java", "Java Process (PID: " + pid + ')'));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        List $this$distinctBy$iv = processes;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            ProcessInfo it4 = (ProcessInfo) e$iv;
            if (set$iv.add(Long.valueOf(it4.getPid()))) {
                list$iv.add(e$iv);
            }
        }
        return list$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectAgentJar() {
        JFileChooser $this$selectAgentJar_u24lambda_u2429 = new JFileChooser();
        $this$selectAgentJar_u24lambda_u2429.setDialogTitle("Select Haze Agent JAR");
        $this$selectAgentJar_u24lambda_u2429.setFileSelectionMode(0);
        $this$selectAgentJar_u24lambda_u2429.setFileFilter(new FileNameExtensionFilter("JAR Files (*.jar)", new String[]{"jar"}));
        $this$selectAgentJar_u24lambda_u2429.setCurrentDirectory(new File("build/libs"));
        if ($this$selectAgentJar_u24lambda_u2429.showOpenDialog((Component) this) == 0) {
            File selectedFile = $this$selectAgentJar_u24lambda_u2429.getSelectedFile();
            String name = selectedFile.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (StringsKt.endsWith$default(name, ".jar", false, 2, (Object) null)) {
                this.agentJarPath = selectedFile;
                this.agentPathLabel.setText("Agent JAR: " + selectedFile.getName());
                this.agentPathLabel.setForeground(new Color(0, 150, 0));
                return;
            }
            JOptionPane.showMessageDialog((Component) this, "Please select a valid JAR file.", "Invalid File", 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void injectSelected() {
        ProcessInfo selectedProcess = (ProcessInfo) this.processList.getSelectedValue();
        if (selectedProcess == null) {
            return;
        }
        File file = this.agentJarPath;
        if (file == null) {
            file = findAgentJar();
        }
        File agentJar = file;
        if (agentJar == null) {
            JOptionPane.showMessageDialog((Component) this, "Could not find agent JAR file.\nClick 'Select JAR' to manually choose the agent JAR.", sun.rmi.rmic.iiop.Constants.ERROR_SUFFIX, 0);
            return;
        }
        Thread $this$injectSelected_u24lambda_u2434 = new Thread(() -> {
            injectSelected$lambda$33(r2, r3, r4);
        });
        $this$injectSelected_u24lambda_u2434.setName("Injector");
        $this$injectSelected_u24lambda_u2434.setDaemon(true);
        $this$injectSelected_u24lambda_u2434.start();
    }

    private static final void injectSelected$lambda$33(InjectorGUI this$0, ProcessInfo $selectedProcess, File $agentJar) {
        try {
            SwingUtilities.invokeLater(() -> {
                injectSelected$lambda$33$lambda$30(r0, r1);
            });
            long pid = $selectedProcess.getPid();
            String absolutePath = $agentJar.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            boolean result = this$0.injectAgent(pid, absolutePath);
            SwingUtilities.invokeLater(() -> {
                injectSelected$lambda$33$lambda$31(r0, r1);
            });
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                injectSelected$lambda$33$lambda$32(r0, r1);
            });
        }
    }

    private static final void injectSelected$lambda$33$lambda$30(InjectorGUI this$0, ProcessInfo $selectedProcess) {
        this$0.progressBar.setIndeterminate(true);
        this$0.progressBar.setVisible(true);
        this$0.statusLabel.setText("Injecting into PID " + $selectedProcess.getPid() + "...");
    }

    private static final void injectSelected$lambda$33$lambda$31(InjectorGUI this$0, boolean $result) {
        this$0.progressBar.setIndeterminate(false);
        this$0.progressBar.setVisible(false);
        if ($result) {
            this$0.statusLabel.setText("Injection successful!");
            JOptionPane.showMessageDialog((Component) this$0, "Successfully injected Haze into Minecraft!\nThe client should now be loaded in the game.", "Success", 1);
        } else {
            this$0.statusLabel.setText("Injection failed");
            JOptionPane.showMessageDialog((Component) this$0, "Failed to inject Haze.\nMake sure you have the correct permissions and the process is running.", sun.rmi.rmic.iiop.Constants.ERROR_SUFFIX, 0);
        }
    }

    private static final void injectSelected$lambda$33$lambda$32(InjectorGUI this$0, Exception $e) {
        this$0.progressBar.setIndeterminate(false);
        this$0.progressBar.setVisible(false);
        this$0.statusLabel.setText("Error: " + $e.getMessage());
        JOptionPane.showMessageDialog((Component) this$0, "Injection error: " + $e.getMessage(), sun.rmi.rmic.iiop.Constants.ERROR_SUFFIX, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0140 A[LOOP:1: B:19:0x00d0->B:29:0x0140, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x013b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01e4 A[LOOP:2: B:43:0x0174->B:53:0x01e4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01df A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final File findAgentJar() {
        File file;
        boolean z;
        File file2;
        File file3;
        boolean z2;
        boolean z3;
        try {
            file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (Exception e) {
            file = null;
        }
        File injectorJarFile = file;
        List<File> possibleDirs = new ArrayList();
        if (injectorJarFile != null) {
            possibleDirs.add(injectorJarFile.getParentFile());
        }
        possibleDirs.add(new File("build/libs"));
        possibleDirs.add(new File(sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR));
        possibleDirs.add(new File(System.getProperty("user.home")));
        possibleDirs.add(new File(System.getProperty("user.dir")));
        for (File dir : possibleDirs) {
            if (dir != null) {
                z = dir.exists();
            } else {
                z = false;
            }
            if (z) {
                File[] listFiles = dir.listFiles();
                if (listFiles != null) {
                    int i = 0;
                    int length = listFiles.length;
                    while (true) {
                        if (i < length) {
                            File file4 = listFiles[i];
                            String name = file4.getName();
                            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                            if (StringsKt.contains$default((CharSequence) name, (CharSequence) "agent", false, 2, (Object) null)) {
                                String name2 = file4.getName();
                                Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                                if (StringsKt.startsWith$default(name2, "haze", false, 2, (Object) null)) {
                                    String name3 = file4.getName();
                                    Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                                    if (StringsKt.endsWith$default(name3, ".jar", false, 2, (Object) null)) {
                                        z3 = true;
                                        if (!z3) {
                                            file2 = file4;
                                            break;
                                        }
                                        i++;
                                    }
                                }
                            }
                            z3 = false;
                            if (!z3) {
                            }
                        } else {
                            file2 = null;
                            break;
                        }
                    }
                } else {
                    file2 = null;
                }
                File agentJar = file2;
                if (agentJar != null && agentJar.exists()) {
                    return agentJar;
                }
                File[] listFiles2 = dir.listFiles();
                if (listFiles2 != null) {
                    int i2 = 0;
                    int length2 = listFiles2.length;
                    while (true) {
                        if (i2 < length2) {
                            File file5 = listFiles2[i2];
                            String name4 = file5.getName();
                            Intrinsics.checkNotNullExpressionValue(name4, "getName(...)");
                            if (StringsKt.startsWith$default(name4, "haze", false, 2, (Object) null)) {
                                String name5 = file5.getName();
                                Intrinsics.checkNotNullExpressionValue(name5, "getName(...)");
                                if (StringsKt.endsWith$default(name5, ".jar", false, 2, (Object) null)) {
                                    String name6 = file5.getName();
                                    Intrinsics.checkNotNullExpressionValue(name6, "getName(...)");
                                    if (!StringsKt.contains$default((CharSequence) name6, (CharSequence) "injector", false, 2, (Object) null)) {
                                        z2 = true;
                                        if (!z2) {
                                            file3 = file5;
                                            break;
                                        }
                                        i2++;
                                    }
                                }
                            }
                            z2 = false;
                            if (!z2) {
                            }
                        } else {
                            file3 = null;
                            break;
                        }
                    }
                } else {
                    file3 = null;
                }
                File shadowJar = file3;
                if (shadowJar != null && shadowJar.exists()) {
                    return shadowJar;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01fd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x01ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean injectAgent(long j, String str) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            System.out.println((Object) "[Injector] Trying Attach API...");
            System.out.println((Object) ("[Injector] Agent path: " + str));
            System.out.println((Object) ("[Injector] Target PID: " + j));
            Class<?> cls = Class.forName("com.sun.tools.attach.VirtualMachine");
            System.out.println((Object) "[Injector] Attach class found");
            Method method = cls.getMethod("attach", String.class);
            System.out.println((Object) "[Injector] Attach method found");
            Object invoke = method.invoke(null, String.valueOf(j));
            System.out.println((Object) ("[Injector] Attached to VM: " + invoke));
            try {
                Method method2 = cls.getMethod("loadAgent", String.class);
                System.out.println((Object) "[Injector] Loading agent...");
                method2.invoke(invoke, str);
                System.out.println((Object) "[Injector] Agent loaded successfully!");
                cls.getMethod("detach", new Class[0]).invoke(invoke, new Object[0]);
                System.out.println((Object) "[Injector] Detached from VM");
                return true;
            } catch (Throwable th) {
                cls.getMethod("detach", new Class[0]).invoke(invoke, new Object[0]);
                System.out.println((Object) "[Injector] Detached from VM");
                throw th;
            }
        } catch (ClassNotFoundException e) {
            objectRef.element = "Attach API not available (tools.jar missing)";
            System.out.println((Object) ("[Injector] ClassNotFoundException: " + e.getMessage()));
            System.out.println((Object) "[Injector] Trying jattach...");
            if (tryInjectWithJattach(j, str)) {
                return true;
            }
            System.out.println((Object) "[Injector] Trying PowerShell method...");
            if (tryInjectWithPowerShell(j, str)) {
                return true;
            }
            SwingUtilities.invokeLater(() -> {
                injectAgent$lambda$37(r0, r1);
            });
            return false;
        } catch (NoSuchMethodException e2) {
            objectRef.element = "Attach method not found";
            System.out.println((Object) ("[Injector] NoSuchMethodException: " + e2.getMessage()));
            System.out.println((Object) "[Injector] Trying jattach...");
            if (tryInjectWithJattach(j, str)) {
            }
        } catch (Exception e3) {
            String message = e3.getMessage();
            T t = message;
            if (message == null) {
                t = "Unknown error";
            }
            objectRef.element = t;
            System.out.println((Object) ("[Injector] Attach API error: " + e3.getClass().getName() + " - " + e3.getMessage()));
            e3.printStackTrace();
            System.out.println((Object) "[Injector] Trying jattach...");
            if (tryInjectWithJattach(j, str)) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void injectAgent$lambda$37(InjectorGUI this$0, Ref.ObjectRef $lastError) {
        JOptionPane.showMessageDialog((Component) this$0, "Injection failed: " + ((String) $lastError.element) + "\n\nPossible causes:\n1. tools.jar not in classpath\n2. JVM version mismatch\n3. Permission denied\n\nTry running injector with same Java version as Minecraft", "Injection Error", 0);
    }

    private final boolean tryInjectWithJattach(long pid, String agentPath) {
        boolean z;
        int exitCode;
        try {
            System.out.println((Object) "[Injector] Running jattach...");
            ProcessBuilder processBuilder = new ProcessBuilder("jattach", String.valueOf(pid), "load", "instrument", com.sun.tools.internal.ws.wsdl.parser.Constants.FALSE, agentPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            String output = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
            System.out.println((Object) ("[Injector] jattach output: " + output));
            exitCode = process.waitFor();
            System.out.println((Object) ("[Injector] jattach exit code: " + exitCode));
        } catch (Exception e) {
            System.out.println((Object) ("[Injector] jattach error: " + e.getMessage()));
            z = false;
        }
        if (exitCode == 0) {
            System.out.println((Object) "[Injector] jattach injection successful!");
            return true;
        }
        z = false;
        return z;
    }

    private final boolean tryInjectWithPowerShell(long pid, String agentPath) {
        boolean z;
        try {
            System.out.println((Object) "[Injector] Using PowerShell injection...");
            System.out.println((Object) "[Injector] PowerShell injection not implemented");
            z = false;
        } catch (Exception e) {
            System.out.println((Object) ("[Injector] PowerShell error: " + e.getMessage()));
            z = false;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: InjectorGUI.kt */
    @Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018��2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J<\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0013"}, d2 = {"Lnet/ccbluex/liquidbounce/injector/InjectorGUI$ProcessListCellRenderer;", "Ljavax/swing/JPanel;", "Ljavax/swing/ListCellRenderer;", "Lnet/ccbluex/liquidbounce/injector/ProcessInfo;", Constants.CTOR, "()V", "nameLabel", "Ljavax/swing/JLabel;", "pidLabel", "getListCellRendererComponent", "Ljava/awt/Component;", com.sun.tools.internal.ws.wsdl.parser.Constants.ATTRVALUE_LIST, "Ljavax/swing/JList;", "value", "index", "", "isSelected", "", "cellHasFocus", "haze"})
    /* loaded from: target.jar:net/ccbluex/liquidbounce/injector/InjectorGUI$ProcessListCellRenderer.class */
    public static final class ProcessListCellRenderer extends JPanel implements ListCellRenderer<ProcessInfo> {

        @NotNull
        private final JLabel nameLabel = new JLabel();

        @NotNull
        private final JLabel pidLabel = new JLabel();

        public ProcessListCellRenderer() {
            setLayout((LayoutManager) new BorderLayout(5, 0));
            setBorder((Border) new EmptyBorder(8, 10, 8, 10));
            JPanel $this$_init__u24lambda_u242 = new JPanel(new BorderLayout());
            $this$_init__u24lambda_u242.setOpaque(false);
            Component component = this.nameLabel;
            component.setFont(new Font("Segoe UI", 1, 13));
            $this$_init__u24lambda_u242.add(component, "West");
            Component component2 = this.pidLabel;
            component2.setFont(new Font("Segoe UI", 0, 11));
            component2.setForeground(Color.GRAY);
            $this$_init__u24lambda_u242.add(component2, "East");
            add((Component) $this$_init__u24lambda_u242, "Center");
        }

        public /* bridge */ /* synthetic */ Component getListCellRendererComponent(JList p0, Object p1, int p2, boolean p3, boolean p4) {
            return getListCellRendererComponent((JList<? extends ProcessInfo>) p0, (ProcessInfo) p1, p2, p3, p4);
        }

        @NotNull
        public Component getListCellRendererComponent(@Nullable JList<? extends ProcessInfo> jList, @Nullable ProcessInfo value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                this.nameLabel.setText(value.getDisplayName());
                this.pidLabel.setText("PID: " + value.getPid());
            }
            setBackground(isSelected ? new Color(0, 122, 255) : Color.WHITE);
            setForeground(isSelected ? Color.WHITE : Color.BLACK);
            this.nameLabel.setForeground(isSelected ? Color.WHITE : new Color(50, 50, 50));
            this.pidLabel.setForeground(isSelected ? new Color(200, 200, 255) : Color.GRAY);
            return (Component) this;
        }
    }
}
