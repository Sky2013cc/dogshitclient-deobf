package com.sun.tools.internal.ws.wsdl.framework;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/WSDLLocation.class */
public class WSDLLocation {
    private LocationContext[] contexts;
    private int idPos;
    private LocationContext currentContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WSDLLocation() {
        reset();
    }

    public void push() {
        int max = this.contexts.length;
        this.idPos++;
        if (this.idPos >= max) {
            LocationContext[] newContexts = new LocationContext[max * 2];
            System.arraycopy(this.contexts, 0, newContexts, 0, max);
            this.contexts = newContexts;
        }
        this.currentContext = this.contexts[this.idPos];
        if (this.currentContext == null) {
            LocationContext[] locationContextArr = this.contexts;
            int i = this.idPos;
            LocationContext locationContext = new LocationContext();
            this.currentContext = locationContext;
            locationContextArr[i] = locationContext;
        }
    }

    public void pop() {
        this.idPos--;
        if (this.idPos >= 0) {
            this.currentContext = this.contexts[this.idPos];
        }
    }

    public final void reset() {
        this.contexts = new LocationContext[32];
        this.idPos = 0;
        LocationContext[] locationContextArr = this.contexts;
        int i = this.idPos;
        LocationContext locationContext = new LocationContext();
        this.currentContext = locationContext;
        locationContextArr[i] = locationContext;
    }

    public String getLocation() {
        return this.currentContext.getLocation();
    }

    public void setLocation(String loc) {
        this.currentContext.setLocation(loc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/WSDLLocation$LocationContext.class */
    public static class LocationContext {
        private String location;

        private LocationContext() {
        }

        void setLocation(String loc) {
            this.location = loc;
        }

        String getLocation() {
            return this.location;
        }
    }
}
