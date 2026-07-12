package sun.tools.asm;

/* loaded from: target.jar:sun/tools/asm/CatchData.class */
public final class CatchData {
    Object type;
    Label label = new Label();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CatchData(Object obj) {
        this.type = obj;
    }

    public Label getLabel() {
        return this.label;
    }

    public Object getType() {
        return this.type;
    }
}
