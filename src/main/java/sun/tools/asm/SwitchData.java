package sun.tools.asm;

import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: target.jar:sun/tools/asm/SwitchData.class */
public final class SwitchData {
    int minValue;
    int maxValue;
    Label defaultLabel = new Label();
    Hashtable<Integer, Label> tab = new Hashtable<>();
    Hashtable<Integer, Long> whereCaseTab = null;

    public Label get(int i) {
        return this.tab.get(Integer.valueOf(i));
    }

    public Label get(Integer num) {
        return this.tab.get(num);
    }

    public void add(int i, Label label) {
        if (this.tab.size() == 0) {
            this.minValue = i;
            this.maxValue = i;
        } else {
            if (i < this.minValue) {
                this.minValue = i;
            }
            if (i > this.maxValue) {
                this.maxValue = i;
            }
        }
        this.tab.put(Integer.valueOf(i), label);
    }

    public Label getDefaultLabel() {
        return this.defaultLabel;
    }

    public synchronized Enumeration<Integer> sortedKeys() {
        return new SwitchDataEnumeration(this.tab);
    }

    public void initTableCase() {
        this.whereCaseTab = new Hashtable<>();
    }

    public void addTableCase(int i, long j) {
        if (this.whereCaseTab != null) {
            this.whereCaseTab.put(Integer.valueOf(i), Long.valueOf(j));
        }
    }

    public void addTableDefault(long j) {
        if (this.whereCaseTab != null) {
            this.whereCaseTab.put("default", Long.valueOf(j));
        }
    }

    public long whereCase(Object obj) {
        Long l = this.whereCaseTab.get(obj);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public boolean getDefault() {
        return whereCase("default") != 0;
    }
}
