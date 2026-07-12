package com.sun.tools.corba.se.idl;

/* compiled from: Scanner.java */
/* loaded from: target.jar:com/sun/tools/corba/se/idl/ScannerData.class */
class ScannerData {
    String indent;
    IncludeEntry fileEntry;
    String filename;
    char[] fileBytes;
    int fileIndex;
    int oldIndex;
    char ch;
    int line;
    int oldLine;
    boolean macrodata;
    boolean includeIsImport;

    public ScannerData() {
        this.indent = "";
        this.fileEntry = null;
        this.filename = "";
        this.fileBytes = null;
        this.fileIndex = 0;
        this.oldIndex = 0;
        this.line = 1;
        this.oldLine = 1;
        this.macrodata = false;
        this.includeIsImport = false;
    }

    public ScannerData(ScannerData scannerData) {
        this.indent = "";
        this.fileEntry = null;
        this.filename = "";
        this.fileBytes = null;
        this.fileIndex = 0;
        this.oldIndex = 0;
        this.line = 1;
        this.oldLine = 1;
        this.macrodata = false;
        this.includeIsImport = false;
        this.indent = scannerData.indent;
        this.fileEntry = scannerData.fileEntry;
        this.filename = scannerData.filename;
        this.fileBytes = scannerData.fileBytes;
        this.fileIndex = scannerData.fileIndex;
        this.oldIndex = scannerData.oldIndex;
        this.ch = scannerData.ch;
        this.line = scannerData.line;
        this.oldLine = scannerData.oldLine;
        this.macrodata = scannerData.macrodata;
        this.includeIsImport = scannerData.includeIsImport;
    }
}
