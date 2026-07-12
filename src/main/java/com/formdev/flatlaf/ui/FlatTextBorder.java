package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatStylingSupport;
import java.awt.Component;
import javax.swing.UIManager;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: target.jar:com/formdev/flatlaf/ui/FlatTextBorder.class */
public class FlatTextBorder extends FlatBorder {

    @FlatStylingSupport.Styleable
    protected int arc = UIManager.getInt("TextComponent.arc");

    @FlatStylingSupport.Styleable
    protected Boolean roundRect;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.formdev.flatlaf.ui.FlatBorder
    public int getArc(Component c) {
        if (isCellEditor(c)) {
            return 0;
        }
        Boolean roundRect = FlatUIUtils.isRoundRect(c);
        if (roundRect == null) {
            roundRect = this.roundRect;
        }
        if (roundRect == null) {
            return this.arc;
        }
        if (roundRect.booleanValue()) {
            return ShortCompanionObject.MAX_VALUE;
        }
        return 0;
    }
}
