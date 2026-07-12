package com.formdev.flatlaf.json;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: target.jar:com/formdev/flatlaf/json/Json.class */
public class Json {
    public static Object parse(Reader reader) throws IOException, ParseException {
        DefaultHandler handler = new DefaultHandler();
        new JsonParser(handler).parse(reader);
        return handler.getValue();
    }

    /* loaded from: target.jar:com/formdev/flatlaf/json/Json$DefaultHandler.class */
    static class DefaultHandler extends JsonHandler<List<Object>, Map<String, Object>> {
        private Object value;

        DefaultHandler() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.formdev.flatlaf.json.JsonHandler
        public List<Object> startArray() {
            return new ArrayList();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.formdev.flatlaf.json.JsonHandler
        public Map<String, Object> startObject() {
            return new LinkedHashMap();
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endNull() {
            this.value = "null";
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endBoolean(boolean bool) {
            this.value = bool ? Constants.TRUE : Constants.FALSE;
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endString(String string) {
            this.value = string;
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endNumber(String string) {
            this.value = string;
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endArray(List<Object> array) {
            this.value = array;
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endObject(Map<String, Object> object) {
            this.value = object;
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endArrayValue(List<Object> array) {
            array.add(this.value);
        }

        @Override // com.formdev.flatlaf.json.JsonHandler
        public void endObjectValue(Map<String, Object> object, String name) {
            object.put(name, this.value);
        }

        Object getValue() {
            return this.value;
        }
    }
}
