package org.apache.fontbox.cmap;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.internal.dynalink.CallSiteDescriptor;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessReadBuffer;

/* loaded from: target.jar:org/apache/fontbox/cmap/CMapParser.class */
public class CMapParser {
    private static final String MARK_END_OF_DICTIONARY = ">>";
    private static final String MARK_END_OF_ARRAY = "]";
    private final byte[] tokenParserByteBuffer;
    private boolean strictMode;

    public CMapParser() {
        this.tokenParserByteBuffer = new byte[512];
        this.strictMode = false;
    }

    public CMapParser(boolean strictMode) {
        this.tokenParserByteBuffer = new byte[512];
        this.strictMode = false;
        this.strictMode = strictMode;
    }

    public CMap parsePredefined(String name) throws IOException {
        RandomAccessRead randomAccessRead = getExternalCMap(name);
        Throwable th = null;
        try {
            this.strictMode = false;
            CMap parse = parse(randomAccessRead);
            if (randomAccessRead != null) {
                if (0 != 0) {
                    try {
                        randomAccessRead.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    randomAccessRead.close();
                }
            }
            return parse;
        } catch (Throwable th3) {
            if (randomAccessRead != null) {
                if (0 != 0) {
                    try {
                        randomAccessRead.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    randomAccessRead.close();
                }
            }
            throw th3;
        }
    }

    public CMap parse(RandomAccessRead randomAcccessRead) throws IOException {
        CMap result = new CMap();
        Object previousToken = null;
        Object parseNextToken = parseNextToken(randomAcccessRead);
        while (true) {
            Object token = parseNextToken;
            if (token == null) {
                break;
            }
            if (token instanceof Operator) {
                Operator op = (Operator) token;
                if (op.op.equals("endcmap")) {
                    break;
                }
                if (op.op.equals("usecmap") && (previousToken instanceof LiteralName)) {
                    parseUsecmap((LiteralName) previousToken, result);
                } else if (previousToken instanceof Number) {
                    if (op.op.equals("begincodespacerange")) {
                        parseBegincodespacerange((Number) previousToken, randomAcccessRead, result);
                    } else if (op.op.equals("beginbfchar")) {
                        parseBeginbfchar((Number) previousToken, randomAcccessRead, result);
                    } else if (op.op.equals("beginbfrange")) {
                        parseBeginbfrange((Number) previousToken, randomAcccessRead, result);
                    } else if (op.op.equals("begincidchar")) {
                        parseBegincidchar((Number) previousToken, randomAcccessRead, result);
                    } else if (op.op.equals("begincidrange") && (previousToken instanceof Integer)) {
                        parseBegincidrange(((Integer) previousToken).intValue(), randomAcccessRead, result);
                    }
                }
            } else if (token instanceof LiteralName) {
                parseLiteralName((LiteralName) token, randomAcccessRead, result);
            }
            previousToken = token;
            parseNextToken = parseNextToken(randomAcccessRead);
        }
        return result;
    }

    private void parseUsecmap(LiteralName useCmapName, CMap result) throws IOException {
        RandomAccessRead randomAccessRead = getExternalCMap(useCmapName.name);
        Throwable th = null;
        try {
            try {
                CMap useCMap = parse(randomAccessRead);
                result.useCmap(useCMap);
                if (randomAccessRead != null) {
                    if (0 != 0) {
                        try {
                            randomAccessRead.close();
                            return;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            return;
                        }
                    }
                    randomAccessRead.close();
                }
            } catch (Throwable th3) {
                th = th3;
                throw th3;
            }
        } catch (Throwable th4) {
            if (randomAccessRead != null) {
                if (th != null) {
                    try {
                        randomAccessRead.close();
                    } catch (Throwable th5) {
                        th.addSuppressed(th5);
                    }
                } else {
                    randomAccessRead.close();
                }
            }
            throw th4;
        }
    }

    private void parseLiteralName(LiteralName literal, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        String str = literal.name;
        boolean z = -1;
        switch (str.hashCode()) {
            case -1970195329:
                if (str.equals("CMapVersion")) {
                    z = 2;
                    break;
                }
                break;
            case -989602748:
                if (str.equals("CMapName")) {
                    z = true;
                    break;
                }
                break;
            case -989400845:
                if (str.equals("CMapType")) {
                    z = 3;
                    break;
                }
                break;
            case -625568675:
                if (str.equals("Registry")) {
                    z = 4;
                    break;
                }
                break;
            case 82750106:
                if (str.equals("WMode")) {
                    z = false;
                    break;
                }
                break;
            case 1281471705:
                if (str.equals("Supplement")) {
                    z = 6;
                    break;
                }
                break;
            case 1298958836:
                if (str.equals("Ordering")) {
                    z = 5;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                Object next = parseNextToken(randomAcccessRead);
                if (next instanceof Integer) {
                    result.setWMode(((Integer) next).intValue());
                    return;
                }
                return;
            case true:
                Object next2 = parseNextToken(randomAcccessRead);
                if (next2 instanceof LiteralName) {
                    result.setName(((LiteralName) next2).name);
                    return;
                }
                return;
            case true:
                Object next3 = parseNextToken(randomAcccessRead);
                if (next3 instanceof Number) {
                    result.setVersion(next3.toString());
                    return;
                } else {
                    if (next3 instanceof String) {
                        result.setVersion((String) next3);
                        return;
                    }
                    return;
                }
            case true:
                Object next4 = parseNextToken(randomAcccessRead);
                if (next4 instanceof Integer) {
                    result.setType(((Integer) next4).intValue());
                    return;
                }
                return;
            case true:
                Object next5 = parseNextToken(randomAcccessRead);
                if (next5 instanceof String) {
                    result.setRegistry((String) next5);
                    return;
                }
                return;
            case true:
                Object next6 = parseNextToken(randomAcccessRead);
                if (next6 instanceof String) {
                    result.setOrdering((String) next6);
                    return;
                }
                return;
            case true:
                Object next7 = parseNextToken(randomAcccessRead);
                if (next7 instanceof Integer) {
                    result.setSupplement(((Integer) next7).intValue());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void checkExpectedOperator(Operator operator, String expectedOperatorName, String rangeName) throws IOException {
        if (!operator.op.equals(expectedOperatorName)) {
            throw new IOException("Error : ~" + rangeName + " contains an unexpected operator : " + operator.op);
        }
    }

    private void parseBegincodespacerange(Number cosCount, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        for (int j = 0; j < cosCount.intValue(); j++) {
            Object nextToken = parseNextToken(randomAcccessRead);
            if (nextToken instanceof Operator) {
                checkExpectedOperator((Operator) nextToken, "endcodespacerange", "codespacerange");
                return;
            }
            if (!(nextToken instanceof byte[])) {
                throw new IOException("start range missing");
            }
            byte[] startRange = (byte[]) nextToken;
            byte[] endRange = parseByteArray(randomAcccessRead);
            try {
                result.addCodespaceRange(new CodespaceRange(startRange, endRange));
            } catch (IllegalArgumentException ex) {
                throw new IOException(ex);
            }
        }
    }

    private void parseBeginbfchar(Number cosCount, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        for (int j = 0; j < cosCount.intValue(); j++) {
            Object nextToken = parseNextToken(randomAcccessRead);
            if (nextToken instanceof Operator) {
                checkExpectedOperator((Operator) nextToken, "endbfchar", "bfchar");
                return;
            }
            if (!(nextToken instanceof byte[])) {
                throw new IOException("input code missing");
            }
            byte[] inputCode = (byte[]) nextToken;
            Object nextToken2 = parseNextToken(randomAcccessRead);
            if (nextToken2 instanceof byte[]) {
                byte[] bytes = (byte[]) nextToken2;
                String value = createStringFromBytes(bytes);
                result.addCharMapping(inputCode, value);
            } else if (nextToken2 instanceof LiteralName) {
                result.addCharMapping(inputCode, ((LiteralName) nextToken2).name);
            } else {
                throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + nextToken2);
            }
        }
    }

    private void parseBegincidrange(int numberOfLines, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        for (int n = 0; n < numberOfLines; n++) {
            Object nextToken = parseNextToken(randomAcccessRead);
            if (nextToken instanceof Operator) {
                checkExpectedOperator((Operator) nextToken, "endcidrange", "cidrange");
                return;
            }
            if (!(nextToken instanceof byte[])) {
                throw new IOException("start code missing");
            }
            byte[] startCode = (byte[]) nextToken;
            byte[] endCode = parseByteArray(randomAcccessRead);
            int mappedCode = parseInteger(randomAcccessRead).intValue();
            if (startCode.length == endCode.length) {
                if (Arrays.equals(startCode, endCode)) {
                    result.addCIDMapping(startCode, mappedCode);
                } else {
                    result.addCIDRange(startCode, endCode, mappedCode);
                }
            } else {
                throw new IOException("Error : ~cidrange values must not have different byte lengths");
            }
        }
    }

    private void parseBegincidchar(Number cosCount, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        for (int j = 0; j < cosCount.intValue(); j++) {
            Object nextToken = parseNextToken(randomAcccessRead);
            if (nextToken instanceof Operator) {
                checkExpectedOperator((Operator) nextToken, "endcidchar", "cidchar");
                return;
            } else {
                if (!(nextToken instanceof byte[])) {
                    throw new IOException("input code missing");
                }
                byte[] inputCode = (byte[]) nextToken;
                int mappedCID = parseInteger(randomAcccessRead).intValue();
                result.addCIDMapping(inputCode, mappedCID);
            }
        }
    }

    private void parseBeginbfrange(Number cosCount, RandomAccessRead randomAcccessRead, CMap result) throws IOException {
        for (int j = 0; j < cosCount.intValue(); j++) {
            Object nextToken = parseNextToken(randomAcccessRead);
            if (nextToken instanceof Operator) {
                checkExpectedOperator((Operator) nextToken, "endbfrange", "bfrange");
                return;
            }
            if (!(nextToken instanceof byte[])) {
                throw new IOException("start code missing");
            }
            byte[] startCode = (byte[]) nextToken;
            Object nextToken2 = parseNextToken(randomAcccessRead);
            if (nextToken2 instanceof Operator) {
                checkExpectedOperator((Operator) nextToken2, "endbfrange", "bfrange");
                return;
            }
            if (!(nextToken2 instanceof byte[])) {
                throw new IOException("end code missing");
            }
            byte[] endCode = (byte[]) nextToken2;
            int start = CMap.toInt(startCode);
            int end = CMap.toInt(endCode);
            if (end >= start) {
                Object nextToken3 = parseNextToken(randomAcccessRead);
                if (nextToken3 instanceof List) {
                    List<byte[]> array = (List) nextToken3;
                    if (!array.isEmpty() && array.size() >= end - start) {
                        addMappingFrombfrange(result, startCode, array);
                    }
                } else if (nextToken3 instanceof byte[]) {
                    byte[] tokenBytes = (byte[]) nextToken3;
                    if (tokenBytes.length > 0) {
                        if (tokenBytes.length == 2 && start == 0 && end == 65535 && tokenBytes[0] == 0 && tokenBytes[1] == 0) {
                            for (int i = 0; i < 256; i++) {
                                startCode[0] = (byte) i;
                                startCode[1] = 0;
                                tokenBytes[0] = (byte) i;
                                tokenBytes[1] = 0;
                                addMappingFrombfrange(result, startCode, 256, tokenBytes);
                            }
                        } else {
                            addMappingFrombfrange(result, startCode, (end - start) + 1, tokenBytes);
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private void addMappingFrombfrange(CMap cmap, byte[] startCode, List<byte[]> tokenBytesList) {
        for (byte[] tokenBytes : tokenBytesList) {
            String value = createStringFromBytes(tokenBytes);
            cmap.addCharMapping(startCode, value);
            increment(startCode, startCode.length - 1, false);
        }
    }

    private void addMappingFrombfrange(CMap cmap, byte[] startCode, int values, byte[] tokenBytes) {
        for (int i = 0; i < values; i++) {
            String value = createStringFromBytes(tokenBytes);
            cmap.addCharMapping(startCode, value);
            if (increment(tokenBytes, tokenBytes.length - 1, this.strictMode)) {
                increment(startCode, startCode.length - 1, false);
            } else {
                return;
            }
        }
    }

    private RandomAccessRead getExternalCMap(String name) throws IOException {
        InputStream is = getClass().getResourceAsStream(name);
        if (is == null) {
            throw new IOException("Error: Could not find referenced cmap stream " + name);
        }
        return new RandomAccessReadBuffer(is);
    }

    private Object parseNextToken(RandomAccessRead randomAcccessRead) throws IOException {
        int nextByte;
        int read = randomAcccessRead.read();
        while (true) {
            nextByte = read;
            if (nextByte != 9 && nextByte != 32 && nextByte != 13 && nextByte != 10) {
                break;
            }
            read = randomAcccessRead.read();
        }
        switch (nextByte) {
            case -1:
                return null;
            case 37:
                return readLine(randomAcccessRead, nextByte);
            case 40:
                return readString(randomAcccessRead);
            case 47:
                return readLiteralName(randomAcccessRead);
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                return readNumber(randomAcccessRead, nextByte);
            case 60:
                return readDictionary(randomAcccessRead);
            case 62:
                if (randomAcccessRead.read() == 62) {
                    return MARK_END_OF_DICTIONARY;
                }
                throw new IOException("Error: expected the end of a dictionary.");
            case 91:
                return readArray(randomAcccessRead);
            case 93:
                return MARK_END_OF_ARRAY;
            default:
                return readOperator(randomAcccessRead, nextByte);
        }
    }

    private Integer parseInteger(RandomAccessRead randomAcccessRead) throws IOException {
        Object nextToken = parseNextToken(randomAcccessRead);
        if (nextToken == null) {
            throw new IOException("expected integer value is missing");
        }
        if (nextToken instanceof Integer) {
            return (Integer) nextToken;
        }
        throw new IOException("invalid type for next token");
    }

    private byte[] parseByteArray(RandomAccessRead randomAcccessRead) throws IOException {
        Object nextToken = parseNextToken(randomAcccessRead);
        if (nextToken == null) {
            throw new IOException("expected byte[] value is missing");
        }
        if (nextToken instanceof byte[]) {
            return (byte[]) nextToken;
        }
        throw new IOException("invalid type for next token");
    }

    private List<Object> readArray(RandomAccessRead randomAcccessRead) throws IOException {
        List<Object> list = new ArrayList<>();
        Object parseNextToken = parseNextToken(randomAcccessRead);
        while (true) {
            Object nextToken = parseNextToken;
            if (nextToken == null || MARK_END_OF_ARRAY.equals(nextToken)) {
                break;
            }
            list.add(nextToken);
            parseNextToken = parseNextToken(randomAcccessRead);
        }
        return list;
    }

    private String readString(RandomAccessRead randomAcccessRead) throws IOException {
        StringBuilder buffer = new StringBuilder();
        int read = randomAcccessRead.read();
        while (true) {
            int stringByte = read;
            if (stringByte == -1 || stringByte == 41) {
                break;
            }
            buffer.append((char) stringByte);
            read = randomAcccessRead.read();
        }
        return buffer.toString();
    }

    private String readLine(RandomAccessRead randomAcccessRead, int firstByte) throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append((char) firstByte);
        readUntilEndOfLine(randomAcccessRead, buffer);
        return buffer.toString();
    }

    private LiteralName readLiteralName(RandomAccessRead randomAcccessRead) throws IOException {
        int stringByte;
        StringBuilder buffer = new StringBuilder();
        int read = randomAcccessRead.read();
        while (true) {
            stringByte = read;
            if (isWhitespaceOrEOF(stringByte) || isDelimiter(stringByte)) {
                break;
            }
            buffer.append((char) stringByte);
            read = randomAcccessRead.read();
        }
        if (isDelimiter(stringByte)) {
            randomAcccessRead.rewind(1);
        }
        return new LiteralName(buffer.toString());
    }

    private Operator readOperator(RandomAccessRead randomAcccessRead, int firstByte) throws IOException {
        int nextByte;
        StringBuilder buffer = new StringBuilder();
        buffer.append((char) firstByte);
        int read = randomAcccessRead.read();
        while (true) {
            nextByte = read;
            if (isWhitespaceOrEOF(nextByte) || isDelimiter(nextByte) || Character.isDigit(nextByte)) {
                break;
            }
            buffer.append((char) nextByte);
            read = randomAcccessRead.read();
        }
        if (isDelimiter(nextByte) || Character.isDigit(nextByte)) {
            randomAcccessRead.rewind(1);
        }
        return new Operator(buffer.toString());
    }

    private Number readNumber(RandomAccessRead randomAcccessRead, int firstByte) throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append((char) firstByte);
        int read = randomAcccessRead.read();
        while (true) {
            int nextByte = read;
            if (isWhitespaceOrEOF(nextByte) || !(Character.isDigit((char) nextByte) || nextByte == 46)) {
                break;
            }
            buffer.append((char) nextByte);
            read = randomAcccessRead.read();
        }
        randomAcccessRead.rewind(1);
        String value = buffer.toString();
        try {
            if (value.indexOf(46) >= 0) {
                return Double.valueOf(value);
            }
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            throw new IOException("Invalid number '" + value + OperatorName.SHOW_TEXT_LINE, ex);
        }
    }

    private Object readDictionary(RandomAccessRead randomAcccessRead) throws IOException {
        int intValue;
        int i;
        int theNextByte = randomAcccessRead.read();
        if (theNextByte == 60) {
            Map<String, Object> result = new HashMap<>();
            Object parseNextToken = parseNextToken(randomAcccessRead);
            while (true) {
                Object key = parseNextToken;
                if (!(key instanceof LiteralName) || MARK_END_OF_DICTIONARY.equals(((LiteralName) key).name)) {
                    break;
                }
                Object value = parseNextToken(randomAcccessRead);
                result.put(((LiteralName) key).name, value);
                parseNextToken = parseNextToken(randomAcccessRead);
            }
            return result;
        }
        int multiplyer = 16;
        int bufferIndex = -1;
        while (theNextByte != -1 && theNextByte != 62) {
            if (isWhitespaceOrEOF(theNextByte)) {
                theNextByte = randomAcccessRead.read();
            } else {
                if (theNextByte >= 48 && theNextByte <= 57) {
                    intValue = theNextByte - 48;
                } else if (theNextByte >= 65 && theNextByte <= 70) {
                    intValue = (10 + theNextByte) - 65;
                } else if (theNextByte >= 97 && theNextByte <= 102) {
                    intValue = (10 + theNextByte) - 97;
                } else {
                    throw new IOException("Error: expected hex character and not " + ((char) theNextByte) + CallSiteDescriptor.TOKEN_DELIMITER + theNextByte);
                }
                int intValue2 = intValue * multiplyer;
                if (multiplyer == 16) {
                    bufferIndex++;
                    if (bufferIndex >= this.tokenParserByteBuffer.length) {
                        throw new IOException("cmap token ist larger than buffer size " + this.tokenParserByteBuffer.length);
                    }
                    this.tokenParserByteBuffer[bufferIndex] = 0;
                    i = 1;
                } else {
                    i = 16;
                }
                multiplyer = i;
                byte[] bArr = this.tokenParserByteBuffer;
                int i2 = bufferIndex;
                bArr[i2] = (byte) (bArr[i2] + intValue2);
                theNextByte = randomAcccessRead.read();
            }
        }
        byte[] finalResult = new byte[bufferIndex + 1];
        System.arraycopy(this.tokenParserByteBuffer, 0, finalResult, 0, bufferIndex + 1);
        return finalResult;
    }

    private void readUntilEndOfLine(RandomAccessRead randomAcccessRead, StringBuilder buf) throws IOException {
        int read = randomAcccessRead.read();
        while (true) {
            int nextByte = read;
            if (nextByte != -1 && nextByte != 13 && nextByte != 10) {
                buf.append((char) nextByte);
                read = randomAcccessRead.read();
            } else {
                return;
            }
        }
    }

    private boolean isWhitespaceOrEOF(int aByte) {
        return aByte == -1 || aByte == 32 || aByte == 13 || aByte == 10;
    }

    private boolean isDelimiter(int aByte) {
        switch (aByte) {
            case 37:
            case 40:
            case 41:
            case 47:
            case 60:
            case 62:
            case 91:
            case 93:
            case 123:
            case 125:
                return true;
            default:
                return false;
        }
    }

    private boolean increment(byte[] data, int position, boolean useStrictMode) {
        if (position > 0 && (data[position] & 255) == 255) {
            if (useStrictMode) {
                return false;
            }
            data[position] = 0;
            increment(data, position - 1, useStrictMode);
            return true;
        }
        data[position] = (byte) (data[position] + 1);
        return true;
    }

    private String createStringFromBytes(byte[] bytes) {
        return new String(bytes, bytes.length == 1 ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_16BE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:org/apache/fontbox/cmap/CMapParser$LiteralName.class */
    public static final class LiteralName {
        private final String name;

        private LiteralName(String theName) {
            this.name = theName;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:org/apache/fontbox/cmap/CMapParser$Operator.class */
    public static final class Operator {
        private final String op;

        private Operator(String theOp) {
            this.op = theOp;
        }
    }
}
