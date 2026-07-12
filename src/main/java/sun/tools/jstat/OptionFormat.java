package sun.tools.jstat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/tools/jstat/OptionFormat.class */
public class OptionFormat {
    protected String name;
    protected List<OptionFormat> children = new ArrayList();

    public OptionFormat(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof OptionFormat) && this.name.compareTo(((OptionFormat) obj).name) == 0;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public void addSubFormat(OptionFormat optionFormat) {
        this.children.add(optionFormat);
    }

    public OptionFormat getSubFormat(int i) {
        return this.children.get(i);
    }

    public void insertSubFormat(int i, OptionFormat optionFormat) {
        this.children.add(i, optionFormat);
    }

    public String getName() {
        return this.name;
    }

    public void apply(Closure closure) throws MonitorException {
        Iterator<OptionFormat> it = this.children.iterator();
        while (it.hasNext()) {
            closure.visit(it.next(), it.hasNext());
        }
        Iterator<OptionFormat> it2 = this.children.iterator();
        while (it2.hasNext()) {
            it2.next().apply(closure);
        }
    }

    public void printFormat() {
        printFormat(0);
    }

    public void printFormat(int i) {
        StringBuilder sb = new StringBuilder("");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        System.out.println(((Object) sb) + this.name + " {");
        Iterator<OptionFormat> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().printFormat(i + 1);
        }
        System.out.println(((Object) sb) + "}");
    }
}
