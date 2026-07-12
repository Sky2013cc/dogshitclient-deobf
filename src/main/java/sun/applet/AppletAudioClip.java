package sun.applet;

import com.sun.media.sound.JavaSoundAudioClip;
import java.applet.AudioClip;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: target.jar:sun/applet/AppletAudioClip.class */
public class AppletAudioClip implements AudioClip {
    private URL url;
    private AudioClip audioClip;
    boolean DEBUG;

    public AppletAudioClip(URL url) {
        this.url = null;
        this.audioClip = null;
        this.DEBUG = false;
        this.url = url;
        try {
            createAppletAudioClip(url.openStream());
        } catch (IOException e) {
            if (this.DEBUG) {
                System.err.println("IOException creating AppletAudioClip" + e);
            }
        }
    }

    public AppletAudioClip(URLConnection uRLConnection) {
        this.url = null;
        this.audioClip = null;
        this.DEBUG = false;
        try {
            createAppletAudioClip(uRLConnection.getInputStream());
        } catch (IOException e) {
            if (this.DEBUG) {
                System.err.println("IOException creating AppletAudioClip" + e);
            }
        }
    }

    public AppletAudioClip(byte[] bArr) {
        this.url = null;
        this.audioClip = null;
        this.DEBUG = false;
        try {
            createAppletAudioClip(new ByteArrayInputStream(bArr));
        } catch (IOException e) {
            if (this.DEBUG) {
                System.err.println("IOException creating AppletAudioClip " + e);
            }
        }
    }

    void createAppletAudioClip(InputStream inputStream) throws IOException {
        try {
            this.audioClip = new JavaSoundAudioClip(inputStream);
        } catch (Exception e) {
            throw new IOException("Failed to construct the AudioClip: " + e);
        }
    }

    public synchronized void play() {
        if (this.audioClip != null) {
            this.audioClip.play();
        }
    }

    public synchronized void loop() {
        if (this.audioClip != null) {
            this.audioClip.loop();
        }
    }

    public synchronized void stop() {
        if (this.audioClip != null) {
            this.audioClip.stop();
        }
    }
}
