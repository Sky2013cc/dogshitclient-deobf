package com.thealtening.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/* loaded from: target.jar:com/thealtening/api/utils/HttpUtils.class */
public class HttpUtils {
    /* JADX INFO: Access modifiers changed from: protected */
    public String connect(String link) throws IOException {
        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line != null) {
                stringBuilder.append(line).append("\n");
            } else {
                return stringBuilder.toString();
            }
        }
    }
}
