package org.apache.fontbox.ttf.table.common;

import java.util.Map;

/* loaded from: target.jar:org/apache/fontbox/ttf/table/common/ScriptTable.class */
public class ScriptTable {
    private final LangSysTable defaultLangSysTable;
    private final Map<String, LangSysTable> langSysTables;

    public ScriptTable(LangSysTable defaultLangSysTable, Map<String, LangSysTable> langSysTables) {
        this.defaultLangSysTable = defaultLangSysTable;
        this.langSysTables = langSysTables;
    }

    public LangSysTable getDefaultLangSysTable() {
        return this.defaultLangSysTable;
    }

    public Map<String, LangSysTable> getLangSysTables() {
        return this.langSysTables;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(this.defaultLangSysTable != null);
        objArr[1] = Integer.valueOf(this.langSysTables.size());
        return String.format("ScriptTable[hasDefault=%s,langSysRecordsCount=%d]", objArr);
    }
}
