package sun.tools.serialver;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

/* compiled from: SerialVer.java */
/* loaded from: target.jar:sun/tools/serialver/SerialVerFrame.class */
class SerialVerFrame extends Frame {
    MenuBar menu_mb;
    Menu file_m;
    MenuItem exit_i;
    private static final long serialVersionUID = -7248105987187532533L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerialVerFrame() {
        super(Res.getText("SerialVersionInspector"));
        this.file_m = new Menu(Res.getText("File"));
        Menu menu = this.file_m;
        MenuItem menuItem = new MenuItem(Res.getText("Exit"));
        this.exit_i = menuItem;
        menu.add(menuItem);
        this.menu_mb = new MenuBar();
        this.menu_mb.add(this.file_m);
    }

    public boolean handleEvent(Event event) {
        if (event.id == 201) {
            exit(0);
        }
        return super.handleEvent(event);
    }

    public boolean action(Event event, Object obj) {
        if (event.target == this.exit_i) {
            exit(0);
            return false;
        }
        return false;
    }

    void exit(int i) {
        System.exit(i);
    }
}
