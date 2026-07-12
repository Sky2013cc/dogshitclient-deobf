package sun.tools.jstat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/* loaded from: target.jar:sun/tools/jstat/OptionFinder.class */
public class OptionFinder {
    private static final boolean debug = false;
    List<URL> optionsSources;

    public OptionFinder(List<URL> list) {
        this.optionsSources = list;
    }

    public OptionFormat getOptionFormat(String str, boolean z) {
        OptionFormat optionFormat;
        OptionFormat optionFormat2 = getOptionFormat(str, this.optionsSources);
        if (optionFormat2 != null && z && (optionFormat = getOptionFormat("timestamp", this.optionsSources)) != null) {
            optionFormat2.insertSubFormat(0, (ColumnFormat) optionFormat.getSubFormat(0));
        }
        return optionFormat2;
    }

    protected OptionFormat getOptionFormat(String str, List<URL> list) {
        OptionFormat optionFormat = null;
        for (URL url : list) {
            try {
                optionFormat = new Parser(new BufferedReader(new InputStreamReader(url.openStream()))).parse(str);
            } catch (IOException e) {
            } catch (ParserException e2) {
                System.err.println(url + ": " + e2.getMessage());
                System.err.println("Parsing of " + url + " aborted");
            }
            if (optionFormat != null) {
                break;
            }
        }
        return optionFormat;
    }
}
