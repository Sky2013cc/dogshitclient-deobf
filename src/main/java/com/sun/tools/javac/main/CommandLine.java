package com.sun.tools.javac.main;

import com.sun.tools.javac.util.ListBuffer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/* loaded from: target.jar:com/sun/tools/javac/main/CommandLine.class */
public class CommandLine {
    public static String[] parse(String[] strArr) throws IOException {
        ListBuffer listBuffer = new ListBuffer();
        for (String str : strArr) {
            if (str.length() > 1 && str.charAt(0) == '@') {
                String substring = str.substring(1);
                if (substring.charAt(0) == '@') {
                    listBuffer.append(substring);
                } else {
                    loadCmdFile(substring, listBuffer);
                }
            } else {
                listBuffer.append(str);
            }
        }
        return (String[]) listBuffer.toList().toArray(new String[listBuffer.length()]);
    }

    private static void loadCmdFile(String str, ListBuffer<String> listBuffer) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        streamTokenizer.resetSyntax();
        streamTokenizer.wordChars(32, 255);
        streamTokenizer.whitespaceChars(0, 32);
        streamTokenizer.commentChar(35);
        streamTokenizer.quoteChar(34);
        streamTokenizer.quoteChar(39);
        while (streamTokenizer.nextToken() != -1) {
            listBuffer.append(streamTokenizer.sval);
        }
        bufferedReader.close();
    }
}
