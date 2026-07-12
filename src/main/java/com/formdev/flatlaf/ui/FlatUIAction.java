package com.formdev.flatlaf.ui;

import java.beans.PropertyChangeListener;
import javax.swing.Action;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatUIAction.class */
public abstract class FlatUIAction implements Action {
    protected final String name;
    protected final Action delegate;

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatUIAction(String name) {
        this.name = name;
        this.delegate = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatUIAction(Action delegate) {
        this.name = null;
        this.delegate = delegate;
    }

    public Object getValue(String key) {
        if (key == "Name" && this.delegate == null) {
            return this.name;
        }
        if (this.delegate != null) {
            return this.delegate.getValue(key);
        }
        return null;
    }

    public boolean isEnabled() {
        if (this.delegate != null) {
            return this.delegate.isEnabled();
        }
        return true;
    }

    public void putValue(String key, Object value) {
    }

    public void setEnabled(boolean b) {
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }
}
