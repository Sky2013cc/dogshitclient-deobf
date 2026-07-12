package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatSeparatorUI.class */
public class FlatSeparatorUI extends BasicSeparatorUI implements FlatStylingSupport.StyleableUI, PropertyChangeListener {

    @FlatStylingSupport.Styleable
    protected int height;

    @FlatStylingSupport.Styleable
    protected int stripeWidth;

    @FlatStylingSupport.Styleable
    protected int stripeIndent;
    private final boolean shared;
    private boolean defaults_initialized = false;
    private Map<String, Object> oldStyleValues;

    public static ComponentUI createUI(JComponent c) {
        if (FlatUIUtils.canUseSharedUI(c)) {
            return FlatUIUtils.createSharedUI(FlatSeparatorUI.class, () -> {
                return new FlatSeparatorUI(true);
            });
        }
        return new FlatSeparatorUI(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FlatSeparatorUI(boolean shared) {
        this.shared = shared;
    }

    protected String getPropertyPrefix() {
        return "Separator";
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        installStyle((JSeparator) c);
    }

    protected void installDefaults(JSeparator s) {
        super.installDefaults(s);
        if (!this.defaults_initialized) {
            String prefix = getPropertyPrefix();
            this.height = UIManager.getInt(prefix + ".height");
            this.stripeWidth = UIManager.getInt(prefix + ".stripeWidth");
            this.stripeIndent = UIManager.getInt(prefix + ".stripeIndent");
            this.defaults_initialized = true;
        }
    }

    protected void uninstallDefaults(JSeparator s) {
        super.uninstallDefaults(s);
        this.defaults_initialized = false;
        this.oldStyleValues = null;
    }

    protected void installListeners(JSeparator s) {
        super.installListeners(s);
        s.addPropertyChangeListener(this);
    }

    protected void uninstallListeners(JSeparator s) {
        super.uninstallListeners(s);
        s.removePropertyChangeListener(this);
    }

    @Override // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        boolean z = -1;
        switch (propertyName.hashCode()) {
            case 1030195901:
                if (propertyName.equals(FlatClientProperties.STYLE_CLASS)) {
                    z = true;
                    break;
                }
                break;
            case 1545413499:
                if (propertyName.equals(FlatClientProperties.STYLE)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
                JSeparator s = (JSeparator) e.getSource();
                if (this.shared && FlatStylingSupport.hasStyleProperty(s)) {
                    s.updateUI();
                } else {
                    installStyle(s);
                }
                s.revalidate();
                HiDPIUtils.repaint(s);
                return;
            default:
                return;
        }
    }

    protected void installStyle(JSeparator s) {
        try {
            applyStyle(s, FlatStylingSupport.getResolvedStyle(s, getStyleType()));
        } catch (RuntimeException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    String getStyleType() {
        return "Separator";
    }

    protected void applyStyle(JSeparator s, Object style) {
        this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> {
            return applyStyleProperty(s, key, value);
        });
    }

    protected Object applyStyleProperty(JSeparator s, String key, Object value) {
        return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, s, key, value);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Map<String, Class<?>> getStyleableInfos(JComponent c) {
        return FlatStylingSupport.getAnnotatedStyleableInfos(this);
    }

    @Override // com.formdev.flatlaf.ui.FlatStylingSupport.StyleableUI
    public Object getStyleableValue(JComponent c, String key) {
        return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
    }

    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(c.getForeground());
            float width = UIScale.scale(this.stripeWidth);
            float indent = UIScale.scale(this.stripeIndent);
            if (((JSeparator) c).getOrientation() == 1) {
                g2.fill(new Rectangle2D.Float(indent, 0.0f, width, c.getHeight()));
            } else {
                g2.fill(new Rectangle2D.Float(0.0f, indent, c.getWidth(), width));
            }
        } finally {
            g2.dispose();
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        if (((JSeparator) c).getOrientation() == 1) {
            return new Dimension(UIScale.scale(this.height), 0);
        }
        return new Dimension(0, UIScale.scale(this.height));
    }
}
