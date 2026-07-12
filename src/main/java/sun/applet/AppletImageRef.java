package sun.applet;

import java.awt.Toolkit;
import java.net.URL;
import sun.awt.image.URLImageSource;
import sun.misc.Ref;

/* loaded from: target.jar:sun/applet/AppletImageRef.class */
class AppletImageRef extends Ref {
    URL url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppletImageRef(URL url) {
        this.url = url;
    }

    public void flush() {
        super.flush();
    }

    public Object reconstitute() {
        return Toolkit.getDefaultToolkit().createImage(new URLImageSource(this.url));
    }
}
