package com.sun.tools.corba.se.idl;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/NoPragma.class */
class NoPragma extends PragmaHandler {
    @Override // com.sun.tools.corba.se.idl.PragmaHandler
    public boolean process(String str, String str2) throws IOException {
        parseException(Util.getMessage("Preprocessor.unknownPragma", str));
        skipToEOL();
        return true;
    }
}
