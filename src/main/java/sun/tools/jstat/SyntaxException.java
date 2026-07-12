package sun.tools.jstat;

import java.util.Iterator;
import java.util.Set;
import jdk.internal.dynalink.CallSiteDescriptor;

/* loaded from: target.jar:sun/tools/jstat/SyntaxException.class */
public class SyntaxException extends ParserException {
    private String message;

    public SyntaxException(String str) {
        this.message = str;
    }

    public SyntaxException(int i, String str, String str2) {
        this.message = "Syntax error at line " + i + ": Expected " + str + ", Found " + str2;
    }

    public SyntaxException(int i, String str, Token token) {
        this.message = "Syntax error at line " + i + ": Expected " + str + ", Found " + token.toMessage();
    }

    public SyntaxException(int i, Token token, Token token2) {
        this.message = "Syntax error at line " + i + ": Expected " + token.toMessage() + ", Found " + token2.toMessage();
    }

    public SyntaxException(int i, Set set, Token token) {
        StringBuilder sb = new StringBuilder();
        sb.append("Syntax error at line " + i + ": Expected one of '");
        boolean z = true;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (z) {
                sb.append(str);
                z = false;
            } else {
                sb.append(CallSiteDescriptor.OPERATOR_DELIMITER + str);
            }
        }
        sb.append("', Found " + token.toMessage());
        this.message = sb.toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
