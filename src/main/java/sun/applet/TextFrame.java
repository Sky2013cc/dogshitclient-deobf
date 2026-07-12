package sun.applet;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* compiled from: AppletViewer.java */
/* loaded from: target.jar:sun/applet/TextFrame.class */
final class TextFrame extends Frame {
    private static AppletMessageHandler amh = new AppletMessageHandler("textframe");

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextFrame(int i, int i2, String str, String str2) {
        setTitle(str);
        TextArea textArea = new TextArea(20, 60);
        textArea.setText(str2);
        textArea.setEditable(false);
        add("Center", textArea);
        Panel panel = new Panel();
        add("South", panel);
        Button button = new Button(amh.getMessage("button.dismiss", "Dismiss"));
        panel.add(button);
        button.addActionListener(new ActionListener() { // from class: sun.applet.TextFrame.1ActionEventListener
            public void actionPerformed(ActionEvent actionEvent) {
                TextFrame.this.dispose();
            }
        });
        pack();
        move(i, i2);
        setVisible(true);
        addWindowListener(new WindowAdapter() { // from class: sun.applet.TextFrame.1
            public void windowClosing(WindowEvent windowEvent) {
                TextFrame.this.dispose();
            }
        });
    }
}
