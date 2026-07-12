package sun.tools.jstat;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/* loaded from: target.jar:sun/tools/jstat/OptionLister.class */
public class OptionLister {
    private static final boolean debug = false;
    private List<URL> sources;

    public OptionLister(List<URL> list) {
        this.sources = list;
    }

    public void print(PrintStream printStream) {
        TreeSet<OptionFormat> treeSet = new TreeSet(new Comparator<OptionFormat>() { // from class: sun.tools.jstat.OptionLister.1
            @Override // java.util.Comparator
            public int compare(OptionFormat optionFormat, OptionFormat optionFormat2) {
                return optionFormat.getName().compareTo(optionFormat2.getName());
            }
        });
        for (URL url : this.sources) {
            try {
                treeSet.addAll(new Parser(new BufferedReader(new InputStreamReader(url.openStream()))).parseOptions());
            } catch (IOException e) {
            } catch (ParserException e2) {
                System.err.println(url + ": " + e2.getMessage());
                System.err.println("Parsing of " + url + " aborted");
            }
        }
        for (OptionFormat optionFormat : treeSet) {
            if (optionFormat.getName().compareTo("timestamp") != 0) {
                printStream.println(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR + optionFormat.getName());
            }
        }
    }
}
