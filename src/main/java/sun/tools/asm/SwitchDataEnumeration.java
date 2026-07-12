package sun.tools.asm;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

/* compiled from: SwitchData.java */
/* loaded from: target.jar:sun/tools/asm/SwitchDataEnumeration.class */
class SwitchDataEnumeration implements Enumeration<Integer> {
    private Integer[] table;
    private int current_index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SwitchDataEnumeration(Hashtable<Integer, Label> hashtable) {
        this.current_index = 0;
        this.table = new Integer[hashtable.size()];
        int i = 0;
        Enumeration<Integer> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            int i2 = i;
            i++;
            this.table[i2] = keys.nextElement();
        }
        Arrays.sort(this.table);
        this.current_index = 0;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.current_index < this.table.length;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Enumeration
    public Integer nextElement() {
        Integer[] numArr = this.table;
        int i = this.current_index;
        this.current_index = i + 1;
        return numArr[i];
    }
}
