package sun.tools.asm;

import java.util.Vector;

/* loaded from: target.jar:sun/tools/asm/TryData.class */
public final class TryData {
    Vector<CatchData> catches = new Vector<>();
    Label endLabel = new Label();

    public CatchData add(Object obj) {
        CatchData catchData = new CatchData(obj);
        this.catches.addElement(catchData);
        return catchData;
    }

    public CatchData getCatch(int i) {
        return this.catches.elementAt(i);
    }

    public Label getEndLabel() {
        return this.endLabel;
    }
}
