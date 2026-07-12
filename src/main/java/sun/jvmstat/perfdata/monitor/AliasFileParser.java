package sun.jvmstat.perfdata.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/AliasFileParser.class */
public class AliasFileParser {
    private static final String ALIAS = "alias";
    private static final boolean DEBUG = false;
    private URL inputfile;
    private StreamTokenizer st;
    private Token currentToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AliasFileParser(URL url) {
        this.inputfile = url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:sun/jvmstat/perfdata/monitor/AliasFileParser$Token.class */
    public class Token {
        public String sval;
        public int ttype;

        public Token(int i, String str) {
            this.ttype = i;
            this.sval = str;
        }
    }

    private void logln(String str) {
    }

    private void nextToken() throws IOException {
        this.st.nextToken();
        this.currentToken = new Token(this.st.ttype, this.st.sval);
        logln("Read token: type = " + this.currentToken.ttype + " string = " + this.currentToken.sval);
    }

    private void match(int i, String str) throws IOException, SyntaxException {
        if (this.currentToken.ttype == i && this.currentToken.sval.compareTo(str) == 0) {
            logln("matched type: " + i + " and token = " + this.currentToken.sval);
            nextToken();
            return;
        }
        throw new SyntaxException(this.st.lineno());
    }

    private void match(int i) throws IOException, SyntaxException {
        if (this.currentToken.ttype == i) {
            logln("matched type: " + i + ", token = " + this.currentToken.sval);
            nextToken();
            return;
        }
        throw new SyntaxException(this.st.lineno());
    }

    private void match(String str) throws IOException, SyntaxException {
        match(-3, str);
    }

    public void parse(Map<String, ArrayList<String>> map) throws SyntaxException, IOException {
        if (this.inputfile == null) {
            return;
        }
        this.st = new StreamTokenizer(new BufferedReader(new InputStreamReader(this.inputfile.openStream())));
        this.st.slashSlashComments(true);
        this.st.slashStarComments(true);
        this.st.wordChars(95, 95);
        nextToken();
        while (this.currentToken.ttype != -1) {
            if (this.currentToken.ttype != -3 || this.currentToken.sval.compareTo(ALIAS) != 0) {
                nextToken();
            } else {
                match(ALIAS);
                String str = this.currentToken.sval;
                match(-3);
                ArrayList<String> arrayList = new ArrayList<>();
                do {
                    arrayList.add(this.currentToken.sval);
                    match(-3);
                    if (this.currentToken.ttype == -1) {
                        break;
                    }
                } while (this.currentToken.sval.compareTo(ALIAS) != 0);
                logln("adding map entry for " + str + " values = " + arrayList);
                map.put(str, arrayList);
            }
        }
    }
}
