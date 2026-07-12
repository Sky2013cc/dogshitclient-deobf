package sun.applet;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;

/* compiled from: AppletProps.java */
/* loaded from: target.jar:sun/applet/AppletPropsErrorDialog.class */
class AppletPropsErrorDialog extends Dialog {
    public AppletPropsErrorDialog(Frame frame, String str, String str2, String str3) {
        super(frame, str, true);
        Panel panel = new Panel();
        add("Center", new Label(str2));
        panel.add(new Button(str3));
        add("South", panel);
        pack();
        Dimension size = size();
        Rectangle bounds = frame.bounds();
        move(bounds.x + ((bounds.width - size.width) / 2), bounds.y + ((bounds.height - size.height) / 2));
    }

    public boolean action(Event event, Object obj) {
        hide();
        dispose();
        return true;
    }
}
