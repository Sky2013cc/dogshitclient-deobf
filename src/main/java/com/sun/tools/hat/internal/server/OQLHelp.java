package com.sun.tools.hat.internal.server;

import java.io.BufferedInputStream;

/* loaded from: target.jar:com/sun/tools/hat/internal/server/OQLHelp.class */
class OQLHelp extends QueryHandler {
    @Override // com.sun.tools.hat.internal.server.QueryHandler
    public void run() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(getClass().getResourceAsStream("/com/sun/tools/hat/resources/oqlhelp.html"));
            while (true) {
                int read = bufferedInputStream.read();
                if (read != -1) {
                    this.out.print((char) read);
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            printException(e);
        }
    }
}
