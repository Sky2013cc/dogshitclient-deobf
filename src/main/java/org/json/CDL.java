package org.json;

/* loaded from: target.jar:org/json/CDL.class */
public class CDL {
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0097, code lost:
    
        throw r4.syntaxError("Missing close quote '" + r0 + "'.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String getValue(JSONTokener x) throws JSONException {
        char c;
        char nextC;
        while (true) {
            c = x.next();
            if (c != ' ' && c != '\t') {
                break;
            }
        }
        switch (c) {
            case 0:
                return null;
            case '\"':
            case '\'':
                StringBuffer sb = new StringBuffer();
                while (true) {
                    char c2 = x.next();
                    if (c2 == c && (nextC = x.next()) != '\"') {
                        if (nextC > 0) {
                            x.back();
                        }
                        return sb.toString();
                    }
                    if (c2 != 0 && c2 != '\n' && c2 != '\r') {
                        sb.append(c2);
                    }
                }
                break;
            case ',':
                x.back();
                return "";
            default:
                x.back();
                return x.nextTo(',');
        }
    }

    public static JSONArray rowToJSONArray(JSONTokener x) throws JSONException {
        JSONArray ja = new JSONArray();
        while (true) {
            String value = getValue(x);
            char c = x.next();
            if (value != null) {
                if (ja.length() == 0 && value.length() == 0 && c != ',') {
                    return null;
                }
                ja.put(value);
                while (c != ',') {
                    if (c != ' ') {
                        if (c == '\n' || c == '\r' || c == 0) {
                            return ja;
                        }
                        throw x.syntaxError("Bad character '" + c + "' (" + ((int) c) + ").");
                    }
                    c = x.next();
                }
            } else {
                return null;
            }
        }
    }

    public static JSONObject rowToJSONObject(JSONArray names, JSONTokener x) throws JSONException {
        JSONArray ja = rowToJSONArray(x);
        if (ja != null) {
            return ja.toJSONObject(names);
        }
        return null;
    }

    public static String rowToString(JSONArray ja) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ja.length(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            Object object = ja.opt(i);
            if (object != null) {
                String string = object.toString();
                if (string.length() > 0 && (string.indexOf(44) >= 0 || string.indexOf(10) >= 0 || string.indexOf(13) >= 0 || string.indexOf(0) >= 0 || string.charAt(0) == '\"')) {
                    sb.append('\"');
                    int length = string.length();
                    for (int j = 0; j < length; j++) {
                        char c = string.charAt(j);
                        if (c >= ' ' && c != '\"') {
                            sb.append(c);
                        }
                    }
                    sb.append('\"');
                } else {
                    sb.append(string);
                }
            }
        }
        sb.append('\n');
        return sb.toString();
    }

    public static JSONArray toJSONArray(String string) throws JSONException {
        return toJSONArray(new JSONTokener(string));
    }

    public static JSONArray toJSONArray(JSONTokener x) throws JSONException {
        return toJSONArray(rowToJSONArray(x), x);
    }

    public static JSONArray toJSONArray(JSONArray names, String string) throws JSONException {
        return toJSONArray(names, new JSONTokener(string));
    }

    public static JSONArray toJSONArray(JSONArray names, JSONTokener x) throws JSONException {
        if (names == null || names.length() == 0) {
            return null;
        }
        JSONArray ja = new JSONArray();
        while (true) {
            JSONObject jo = rowToJSONObject(names, x);
            if (jo == null) {
                break;
            }
            ja.put(jo);
        }
        if (ja.length() == 0) {
            return null;
        }
        return ja;
    }

    public static String toString(JSONArray ja) throws JSONException {
        JSONArray names;
        JSONObject jo = ja.optJSONObject(0);
        if (jo != null && (names = jo.names()) != null) {
            return rowToString(names) + toString(names, ja);
        }
        return null;
    }

    public static String toString(JSONArray names, JSONArray ja) throws JSONException {
        if (names == null || names.length() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.optJSONObject(i);
            if (jo != null) {
                sb.append(rowToString(jo.toJSONArray(names)));
            }
        }
        return sb.toString();
    }
}
